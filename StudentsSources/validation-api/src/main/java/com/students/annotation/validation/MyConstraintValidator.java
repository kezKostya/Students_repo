package com.students.annotation.validation;

import java.lang.annotation.Annotation;

/**
 * Created by note on 18.10.2015.
 */
public interface MyConstraintValidator<A extends Annotation, T> {

    void initialize(A constraintAnnotation);

    boolean isValid(T value);
}
