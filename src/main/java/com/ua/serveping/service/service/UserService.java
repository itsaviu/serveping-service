package com.ua.serveping.service.service;


import com.ua.serveping.service.models.domains.Role;
import com.ua.serveping.service.models.domains.Users;
import com.ua.serveping.service.models.dto.UserRegReq;
import com.ua.serveping.service.repo.RoleRepo;
import com.ua.serveping.service.repo.UserRepo;
import com.ua.serveping.service.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private BCryptPasswordEncoder cryptPasswordEncoder;

    public void register(UserRegReq userRegReq) {
        Role role = roleRepo.findByName(Constants.ROLE_ADMIN).orElseThrow(() -> new RuntimeException("Not able to find the described role"));
        userRepo.save(new Users(userRegReq.getUsername(), userRegReq.getEmailId(), cryptPasswordEncoder.encode(userRegReq.getPassword()), true, Timestamp.valueOf(LocalDateTime.now()), Collections.singletonList(role)));
    }

    public List<Users> fetchUser() {
        return userRepo.findAll();
    }
}
