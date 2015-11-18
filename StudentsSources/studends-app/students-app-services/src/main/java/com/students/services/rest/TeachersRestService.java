package com.students.services.rest;

import com.students.domain.Course;
import com.students.domain.Teacher;
import com.students.services.BaseRestService;
import com.students.services.BaseService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * Created by kkolesnichenko on 11/18/2015.
 */
@Controller
@RequestMapping("teachers")
public class TeachersRestService  extends BaseRestService<Teacher,Long, BaseService<Teacher,Long>> {

    @Resource(name="teacherData")
    public void setDelegate(BaseService delegate) {
        super.setDelegate(delegate);
    }
}
