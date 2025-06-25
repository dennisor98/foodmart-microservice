package com.opensoft.foodmart.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

import com.opensoft.foodmart.aspects.validator.Base64Validator;

@Documented
@Constraint(validatedBy = Base64Validator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidBase64 {
    String message() default "Invalid Base64 format";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}