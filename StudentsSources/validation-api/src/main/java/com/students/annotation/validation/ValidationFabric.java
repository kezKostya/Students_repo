package com.students.annotation.validation;

import com.students.annotation.validation.Validator;


/**
 * Created by note on 18.10.2015.
 */
public class ValidationFabric {

    private static Validator validator= new ValidatorImpl() ;

    public static Validator buildDefaultValidation(){
        return validator;
    }

    public static <T>  void validate(T object, Validator validator) {
        java.util.Set<ConstraintViolation<T>> constraintViolations = validator.validate(object);
        if (!constraintViolations.isEmpty()) {
            StringBuilder msg = new StringBuilder();
            for (ConstraintViolation<T> violation : constraintViolations) {
                msg.append(violation.getMessage()).append(". ");
            }
            throw new IllegalStateException(msg.toString());
        }
    }

    public static <T>  void validate(T object){
        Validator validator = ValidationFabric.buildDefaultValidation();
        validate(object,validator);
    }

}
