package com.students.annotation;

import com.students.annotation.validation.MyConstraintValidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by kkolesnichenko on 10/19/2015.
 */
public class RegexpValidator implements MyConstraintValidator<Regexp,String> {

    private Regexp annotation;

    @Override
    public void initialize(Regexp constraintAnnotation) {
        this.annotation=constraintAnnotation;
    }

    @Override
    public boolean isValid(String value) {
        String regexp=annotation.regexp();
        Pattern p = Pattern.compile(regexp);
        Matcher m = p.matcher(value);
        return m.matches();
    }
}
