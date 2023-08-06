package com.vegapayInterview.CreditCard.Service.Validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author saumitra chauhan
 * @since 05-08-2023 16:12
 */

public class EnumValidator implements ConstraintValidator<ValidateEnum, String> {

    private Enum<?>[] enumValues;
    private boolean ignoreCase;
    private String[] allowedValues;

    @Override
    public void initialize(ValidateEnum constraintAnnotation) {
        enumValues = constraintAnnotation.enumClass().getEnumConstants();
        ignoreCase = constraintAnnotation.ignoreCase();
        allowedValues = constraintAnnotation.allowedValues();
    }

    @Override
    public boolean isValid(String anEnum, ConstraintValidatorContext constraintValidatorContext) {

        if (anEnum == null){
            return false;
        }
        if (allowedValues.length == 0){
            allowedValues = Arrays.stream(enumValues).map(t -> t.name()).toArray(String[]::new);
        }

        for (String enumValue : allowedValues){
            if (ignoreCase){
                if (enumValue.equalsIgnoreCase(anEnum)){
                    return true;
                }
            } else {
                if (enumValue.equals(anEnum)){
                    return true;
                }
            }
        }

        return false;
    }
}
