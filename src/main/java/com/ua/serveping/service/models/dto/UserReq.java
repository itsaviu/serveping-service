package com.ua.serveping.service.models.dto;


import lombok.Data;

@Data
public class UserReq {
    private String emailId;
    private String username;
    private String password;
}
