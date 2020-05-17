package com.ua.serveping.service.validators;

import com.ua.serveping.service.utils.UserRolesUtils;
import com.ua.serveping.service.validators.constraints.RoleConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class RoleValidaor implements ConstraintValidator<RoleConstraint, List<String>> {


    @Override
    public boolean isValid(List<String> value, ConstraintValidatorContext context) {
        boolean validate = UserRolesUtils.validate(value);
        return validate;
    }
}
