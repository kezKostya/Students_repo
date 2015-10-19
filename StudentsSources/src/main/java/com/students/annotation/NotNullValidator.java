package com.students.annotation;

import com.students.annotation.validation.MyConstraintValidator;

/**
 * Created by note on 18.10.2015.
 */
public class NotNullValidator implements MyConstraintValidator<NotNull ,Object> {

    private com.students.annotation.NotNull annotation;

    public boolean isValid(Object value) {
        return value!=null;
    }

    public void initialize(com.students.annotation.NotNull constraintAnnotation) {
         this.annotation=constraintAnnotation;
    }
}
