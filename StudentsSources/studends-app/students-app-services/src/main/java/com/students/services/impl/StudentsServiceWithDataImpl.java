package com.students.services.impl;

import com.students.domain.Course;
import com.students.domain.Student;
import com.students.services.StudentsService;
import com.students.services.common.CompareOperator;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by kkolesnichenko on 11/13/2015.
 */
public class StudentsServiceWithDataImpl extends GenericDataService<Student,Long> implements StudentsService {


   /* private static List<Student> initialaizeStudents(){
        ArrayList<Student> students=new ArrayList<>();
        final Course mathematics=new Course();
        mathematics.setId(1l);
        mathematics.setName("mathematics");

        final Course physics=new Course();
        physics.setId(2l);
        physics.setName("physics");
        students.add(new Student(){{
            this.setId(1l);
            this.setFirtsName("Nicola");
            this.setLastName("Tesla");
            this.getCourses().put(mathematics, new BigDecimal("90"));
            this.getCourses().put(physics, new BigDecimal("100"));
        }});

        students.add(new Student(){{
            this.setId(2l);
            this.setFirtsName("Vasya");
            this.setLastName("Pupkin");
            this.getCourses().put(mathematics, new BigDecimal("50"));
        }});
        return students;
    }*/



    @Override
    public Collection<Student> findStudentWithScore(Long courseId, BigDecimal score, CompareOperator operator) {
        return  getData().stream().filter(student -> student.getCourses().entrySet().stream().
                filter((en) -> en.getKey().getId().equals(courseId) &&
                        operator.test(en.getValue(),score)).findAny().isPresent()).
                collect(Collectors.toList());
    }






}
