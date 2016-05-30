package com.students.services.rest;

import com.students.domain.Course;
import com.students.services.BaseRestService;
import com.students.services.BaseService;
import com.students.services.StudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * Created by kkolesnichenko on 11/18/2015.
 */
//@Controller
//@RequestMapping("courses")
public class CursesRestService extends BaseRestService<Course,Long, BaseService<Course,Long>> {


    //@Autowired
    //@Qualifier("coursesData")
    //@Resource(name="studentsService")
    public void setDelegate(BaseService delegate) {
        super.setDelegate(delegate);
    }

}
