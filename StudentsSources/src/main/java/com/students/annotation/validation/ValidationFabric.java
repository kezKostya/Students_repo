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


}
