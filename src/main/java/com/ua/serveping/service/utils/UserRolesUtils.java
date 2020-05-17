package com.ua.serveping.service.utils;

import java.util.Arrays;
import java.util.List;

public class UserRolesUtils {

    public static String ROLE_ADMIN = "ROLE_ADMIN";
    public static String ROLE_USER = "ROLE_USER";


    public static boolean validate(List<String> roles) {
        List<String> PREDEFINED_ROLES = Arrays.asList(ROLE_ADMIN, ROLE_USER);
        return PREDEFINED_ROLES.containsAll(roles);
    }
}
