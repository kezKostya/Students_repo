package com.students.validation;

import com.students.annotation.NotNull;
import com.students.annotation.Regexp;
import com.students.annotation.validation.*;
import org.junit.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by note on 18.10.2015.
 */
public class ValidationTest {


    private static @PersonValidation class Person {

        @Regexp( regexp = "[A-Z][a-z]*", message = "Name should starts with Upper case letter and contain only a-z symbols")
        @NotNull(message = "Name could not be null")
        private String name;

        @Regexp( regexp = "[A-Z][a-z(\\-[A-Z])]*",message = "Should mathc pattern: Firstlastname[-Secondlastname]. Where second last name is optional")
        @NotNull(message = "Last name could not be null")
        private String lastName;


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }
    }

    @Retention(RetentionPolicy.RUNTIME)
    @MyConstraint(validatedBy ={PersonValidator.class})
    @Target({ElementType.TYPE})
    public @interface PersonValidation{
        String message() default "Not valid Person";
    }

    public static class PersonValidator implements MyConstraintValidator<PersonValidation ,Person> {

        private PersonValidation annotation;

        @Override
        public void initialize(PersonValidation constraintAnnotation) {
            this.annotation=constraintAnnotation;
        }

        @Override
        public boolean isValid(Person value) {
            //It is incorrect person with the same name and last name
            return !value.getLastName().equals(value.getName());
        }
    }



    @Test(expected = IllegalStateException.class)
    public void testNotNull(){
        Person person=new Person();

        ValidationFabric.validate(person);
    }


    @Test
    public void testRegexp(){
        Person person=new Person();
        person.setLastName("Kolesnichenko-Oleynikova");
        person.setName("Irina");
        ValidationFabric.validate(person);
    }

    @Test(expected = IllegalStateException.class)
    public void testRegexpFail(){
        Person person=new Person();
        person.setLastName("Kolesnichenko-Oleynikova1");
        person.setName("Irina1");
        ValidationFabric.validate(person);
    }

    @Test(expected = IllegalStateException.class)
    public void testPerson(){
        Person person=new Person();
        person.setLastName("Kostya");
        person.setName("Kostya");
        ValidationFabric.validate(person);
    }
}
