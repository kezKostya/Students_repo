package com.students.annotation;

import com.students.annotation.validation.MyConstraint;

import java.lang.annotation.*;


/**
 * Created by note on 18.10.2015.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.CONSTRUCTOR})
@MyConstraint(validatedBy ={NotNullValidator.class})
public @interface NotNull{

    String message() default "value should not be null";




}
