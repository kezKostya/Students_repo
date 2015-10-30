package com.students;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by kkolesnichenko on 10/28/2015.
 */
public class StreamExamplesTest {

    public  Collection<Integer> initializeIntCollection(Integer element, int capacity){
        List<Integer> list=new ArrayList<>(capacity);
        for(int i=0; i<capacity;i++){
            list.add(element+i);
        }

        return list;

    }

    @Test
    public void streamApiTest(){

        //create collection with values -[3,4,5,6,7,8]
        Collection<Integer> myCollection=initializeIntCollection(3,5);

        long sumOfOddValues3times=myCollection.stream().
                filter(o -> o % 2 ==1). // for all odd numbers
                mapToInt(o -> o*3).     // multiply their value on 3
                sum();                  // and return their sum (reduce operation)
        Assert.assertEquals((3+5+7)*3, sumOfOddValues3times);

        Optional<Integer> sumOfModulesOn3= myCollection.stream().
                filter( o -> o % 3 ==0).
                reduce((sum, o) -> sum = sum + o);
        Assert.assertEquals(new Integer(3+6), sumOfModulesOn3.get());



        Collection<Integer> evenCollection=new ArrayList<>();
        myCollection.
                stream().
                filter(o -> o % 2 == 0).
                forEach((i) -> evenCollection.add(i));
    }

    public static class Person{

        public Person(String name, Sex gender, Integer age){
            this.name=name;
            this.gender=gender;
            this.age=age;
        }

        private Sex gender;

        private String name;

        private Integer age;

        public Sex getGender() {
            return gender;
        }

        public void setGender(Sex gender) {
            this.gender = gender;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public enum Sex{
            male,
            female;
        }

    }

    private Collection<Person> initializePersonCollection(int capacity){
        List<Person> list=new ArrayList<>(capacity);
        for(int i=0; i<capacity;i++){
            list.add(new Person("name"+i, i%3==0? Person.Sex.male: Person.Sex.female, 20 +i%2 -i%3));
        }
        return list;
    }

    @Test
    public void collectorsTest(){
        Collection<Person> persons=initializePersonCollection(10);
           Map<Person.Sex, List<String>> boysAndGirls=persons.stream().
                        collect(Collectors.groupingBy(Person::getGender,
                                Collectors.mapping(Person::getName, Collectors.toList())));
        Assert.assertTrue(boysAndGirls.get(Person.Sex.female).size()>boysAndGirls.get(Person.Sex.male).size());

        Map<Person.Sex,Double>boysAndGirlsAverageAge= persons.stream().
                collect(Collectors.groupingBy(Person::getGender , //the same as (p) -> p.getGender()
                        Collectors.averagingInt(Person::getAge)));//the same as (p) -> getAge()


        Assert.assertTrue(boysAndGirlsAverageAge.get(Person.Sex.female)<boysAndGirlsAverageAge.get(Person.Sex.male));


        //Find max using anonymous classes
        Map<Person.Sex,Integer> boysAndGirlsMaxAge= persons.stream().
                collect(Collectors.groupingBy(Person::getGender,
                        new Collector<Person,  Integer[], Integer>() {
                            @Override
                            public Supplier< Integer[]> supplier() {
                                return new Supplier< Integer[]>() {
                                    @Override
                                    public  Integer[] get() {
                                        Integer[] ret={0};
                                        return ret;
                                    }
                                };
                            }

                            @Override
                            public BiConsumer< Integer[], Person> accumulator() {
                                return new BiConsumer< Integer[], Person>() {
                                    @Override
                                    public void accept( Integer[] o, Person o2) {
                                        if(o2.getAge()>o[0]){
                                            o[0]=o2.getAge();
                                        }

                                    }
                                };
                            }

                            @Override
                            public BinaryOperator< Integer[]> combiner() {
                                return new BinaryOperator< Integer[]>() {
                                    @Override
                                    public  Integer[] apply( Integer[] o,  Integer[] o2) {
                                        return null;
                                    }
                                };
                            }

                            @Override
                            public Function< Integer[], Integer> finisher() {
                                return new Function< Integer[], Integer>() {
                                    @Override
                                    public Integer apply( Integer[] o) {
                                        return  o[0];
                                    }


                                };
                            }

                            @Override
                            public Set<Characteristics> characteristics() {
                                return Collections.EMPTY_SET;
                            }
                        }
                            ));

        Assert.assertTrue(boysAndGirlsMaxAge.get(Person.Sex.female)<boysAndGirlsMaxAge.get(Person.Sex.male));

    }


}
