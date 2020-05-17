package com.ua.serveping.service.models.dto;


import com.ua.serveping.service.validators.constraints.RoleConstraint;
import lombok.Data;

import java.util.List;

@Data
public class UserRegReq {
    private String emailId;
    private String username;
    private String password;
    @RoleConstraint
    private List<String> roles;
}
