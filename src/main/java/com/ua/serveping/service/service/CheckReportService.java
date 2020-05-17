package com.ua.serveping.service.service;


import com.ua.serveping.service.models.domains.CheckReport;
import com.ua.serveping.service.repo.CheckReportRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckReportService {

    @Autowired
    private CheckReportRepo checkReportRepo;

    public void saveCheckReport(CheckReport checkReport) {
        checkReportRepo.save(checkReport);
    }
}
