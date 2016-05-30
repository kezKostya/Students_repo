package com.students.services.rest;

import com.students.domain.Student;
import com.students.services.BaseRestService;
import com.students.services.StudentsService;

import com.students.services.common.CompareOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import java.math.BigDecimal;
import java.util.Collection;

/**
 * Created by kkolesnichenko on 11/13/2015.
 */
//@Controller
//@RequestMapping("students")
public class StudentsRestService extends BaseRestService<Student,Long, StudentsService> implements StudentsService {



    //@Autowired
    //@Qualifier("studentsService")
    //@Resource(name="studentsService")
    private StudentsService delegate;

    @Override
    @RequestMapping(method = RequestMethod.GET, params = {"courseId","score","operator"})
    public @ResponseBody
    Collection<Student> findStudentWithScore(@RequestParam(value="courseId") Long courseId,
                                             @RequestParam(value="score")  BigDecimal score,
                                             @RequestParam(value="operator", required=false) CompareOperator operator) {
        return delegate.findStudentWithScore(courseId,score,operator);
    }


    //@Autowired()
    //@Qualifier("studentsService")
    //@Resource(name="studentsService")
    public void setDelegate(StudentsService delegate) {
        super.setDelegate(delegate);
        this.delegate = delegate;
    }


}
