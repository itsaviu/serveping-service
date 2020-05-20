package com.ua.serveping.service.service;


import com.ua.serveping.service.models.domains.Role;
import com.ua.serveping.service.models.domains.Users;
import com.ua.serveping.service.models.dto.UserRegReq;
import com.ua.serveping.service.repo.RoleRepo;
import com.ua.serveping.service.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private BCryptPasswordEncoder cryptPasswordEncoder;

    public void register(UserRegReq userRegReq) {
        List<Role> roles = roleRepo.findByNameIn(userRegReq.getRoles());
        Optional<Users> user = userRepo.findByEmailId(userRegReq.getEmail());
        if (user.isPresent()) {
            throw new RuntimeException("User Already exist");
        }
        userRepo.save(new Users(userRegReq.getUsername(), userRegReq.getEmail(), cryptPasswordEncoder.encode(userRegReq.getPassword()), true, Timestamp.valueOf(LocalDateTime.now()), roles));
    }

    public List<Users> fetchUser() {
        return userRepo.findAll();
    }
}
