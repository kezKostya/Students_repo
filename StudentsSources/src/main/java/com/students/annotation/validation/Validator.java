package com.students.annotation.validation;

import java.util.Set;

/**
 * Created by note on 18.10.2015.
 */
public interface Validator {

    <T> Set<ConstraintViolation<T>> validate(T object);
}
