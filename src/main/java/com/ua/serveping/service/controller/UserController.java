package com.ua.serveping.service.controller;

import com.ua.serveping.service.models.dto.UserReq;
import com.ua.serveping.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody UserReq userReq) {
        userService.register(userReq);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity fetchUsers() {
        return ResponseEntity.ok(userService.fetchUser());
    }
}
