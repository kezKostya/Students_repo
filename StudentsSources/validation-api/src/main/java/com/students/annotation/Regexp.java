package com.students.annotation;

import com.students.annotation.validation.MyConstraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by kkolesnichenko on 10/19/2015.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.CONSTRUCTOR})
@MyConstraint(validatedBy ={RegexpValidator.class})
public @interface Regexp {

    String regexp() default "";
    String message() default "value should math pattern";
}
