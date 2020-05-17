package com.ua.serveping.service.service;


import com.ua.serveping.service.models.CheckStatus;
import com.ua.serveping.service.models.domains.CheckType;
import com.ua.serveping.service.models.domains.Checks;
import com.ua.serveping.service.models.domains.Users;
import com.ua.serveping.service.models.dto.CheckReq;
import com.ua.serveping.service.repo.CheckTypeRepo;
import com.ua.serveping.service.repo.ChecksRepo;
import com.ua.serveping.service.security.threadlocal.LocalContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class CheckService {

    @Autowired
    private CheckTypeRepo checkTypeRepo;

    @Autowired
    private ChecksRepo checksRepo;

    @Autowired
    private SchedulerService schedulerService;

    @Transactional(rollbackFor = Exception.class)
    public void create(CheckReq checkReq) {
        Users users = LocalContextHolder.getLocalContext().getUsers();
        CheckType checkType = checkTypeRepo.findByCheckValue(checkReq.getCheckValue()).orElseThrow(() -> new RuntimeException(String.format("Invalid Check value %s", checkReq.getCheckValue().getName())));
        Checks checks = checksRepo.save(new Checks(checkType, users, checkReq.getName(), checkReq.getCheckInterval(), checkReq.getEndpoint(), Timestamp.valueOf(LocalDateTime.now()), CheckStatus.STARTED, false));
        schedulerService.scheduleTask(checks.getId(), checkReq.getCheckValue(), checks.getCheckInterval());
        log.info("Scheduled....");
    }


    public List<Checks> fetchAllChecks() {
        return checksRepo.findAll();
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteCheck(Long id) {
        checksRepo.softDeleteById(id);
        schedulerService.cancelTask(id);
    }


}
