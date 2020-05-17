package com.ua.serveping.service.validators.constraints;

import com.ua.serveping.service.validators.RoleValidaor;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;


@Documented
@Constraint(validatedBy = RoleValidaor.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface RoleConstraint {
    String message() default "Invalid Role Provided";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
