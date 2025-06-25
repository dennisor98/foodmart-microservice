package com.opensoft.foodmart.aspects.validator;

import java.util.Base64;

import com.opensoft.foodmart.annotations.ValidBase64;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class Base64Validator  implements ConstraintValidator<ValidBase64, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) return true; // Treat null/empty as valid or handle with @NotNull/@NotBlank

        try {
            Base64.getDecoder().decode(value);
            return true;
        } catch (IllegalArgumentException ex) {
            return false;
        }
    }


}
