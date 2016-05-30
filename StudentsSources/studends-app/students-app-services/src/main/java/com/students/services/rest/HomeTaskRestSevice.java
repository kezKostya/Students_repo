package com.students.services.rest;

import com.students.domain.HomeTask;
import com.students.domain.Teacher;
import com.students.services.BaseRestService;
import com.students.services.BaseService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * Created by kkolesnichenko on 11/18/2015.
 */
//@Controller
//@RequestMapping("homeTasks")
public class HomeTaskRestSevice  extends BaseRestService<HomeTask,Long, BaseService<HomeTask,Long>> {

    //@Resource(name="homeTaskData")
    public void setDelegate(BaseService delegate) {
        super.setDelegate(delegate);
    }
}
