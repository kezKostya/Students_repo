package com.students.services;

import com.students.domain.Course;
import com.students.domain.Student;
import com.students.services.common.CompareOperator;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Collection;



/**
 * Created by kkolesnichenko on 11/13/2015.
 */

public interface StudentsService extends BaseService<Student,Long> {


    Collection<Student> findStudentWithScore(Long courseId, BigDecimal score,CompareOperator operator);
}
