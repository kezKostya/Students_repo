package com.students.annotation.validation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by note on 18.10.2015.
 */
public class ValidatorImpl implements Validator {

    public <T> Set<ConstraintViolation<T>> validate(T object) {
        Class objectClass = object.getClass();

        Map<Object, List<Annotation>> validationMap=new HashMap<Object, List<Annotation>>();

       // List<Annotation> annotationList=new ArrayList<Annotation>();
        for(java.lang.reflect.Field field :objectClass.getDeclaredFields()){
            if(field.getAnnotations().length>0) {
                validationMap.put(field, Arrays.asList(field.getAnnotations()));
       //         annotationList.addAll(Arrays.asList(field.getAnnotations()));
            }
        }


        for(java.lang.reflect.Method method :objectClass.getMethods()){
            if(method.getAnnotations().length>0) {
                validationMap.put(method, Arrays.asList(method.getAnnotations()));
     //           annotationList.addAll(Arrays.asList(method.getAnnotations()));
            }
        }

        validationMap.put(object,Arrays.asList(objectClass.getAnnotations()));
       // annotationList.addAll(Arrays.asList(objectClass.getAnnotations()));
        Set<ConstraintViolation<T>> violations=new HashSet<ConstraintViolation<T>>();
        for(final Map.Entry<Object, List<Annotation>> validationEntry:validationMap.entrySet()){
            for(final Annotation annotation:validationEntry.getValue()){


            MyConstraint constraint;
            if(annotation instanceof MyConstraint){
                 constraint= (MyConstraint) annotation;
            }
            else{
                //or it is some specific validator constraint
                constraint=annotation.annotationType().getAnnotation(MyConstraint.class);
            }

            if(constraint!=null){
                for(Class<? extends MyConstraintValidator<?, ?>> validatorClasses:constraint.validatedBy()){
                    try {
                        MyConstraintValidator validator=validatorClasses.getConstructor().newInstance();
                        validator.initialize(annotation);
                        Object validationObject=getValidationObject(validationEntry.getKey(),object);

                        if(!validator.isValid(validationObject)){

                            try{
                                final Method method=annotation.annotationType().getMethod("message");
                                final String message=method.invoke(annotation).toString();
                                violations.add(new ConstraintViolation<T>() {
                                    public String getMessage() {
                                        return validationEntry.getKey()+" violated constraint with message: "+message;
                                    }
                                });
                            }catch (Exception ex){

                                violations.add(new ConstraintViolation<T>() {
                                    public String getMessage() {
                                        return "Violated constraint: "+annotation.getClass().getName();
                                    }
                                });
                            }


                        }
                    } catch (Exception e) {
                        //currently we ignore such behavior, but possibly it is better to log it.
                    }
                }
                }
            }
        }

        return violations;
    }

    private Object getValidationObject(Object key, Object instance) throws IllegalAccessException {
        if(key instanceof java.lang.reflect.Field){
            //we should validate value of property
            ((Field) key).setAccessible(true);
            return ((Field) key).get(instance);
        }
        else if(key instanceof java.lang.reflect.Method){
            //we could validate methods
            return key;
        }
        else   return key;
    }
}
