package com.students.services.rest;

import com.students.domain.Course;
import com.students.domain.HomeTask;
import com.students.domain.Student;
import com.students.domain.Teacher;
import com.students.domain.common.BaseEntity;
import com.students.services.impl.GenericDataService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by kkolesnichenko on 11/26/2015.
 */
@Controller
@RequestMapping("loadData")
public class DefaultDataFiller {


    private EntityManager entityManager;






    private static Collection<? extends BaseEntity> initialaizeData(){

        ArrayList<BaseEntity> retValues=new ArrayList<>();
        Teacher teacher1=new Teacher();
        teacher1.setFirtsName("Denis");
        teacher1.setLastName("Popov");

        final Course mathematics=new Course();
        mathematics.setName("mathematics");
        mathematics.getTeachers().add(teacher1);


        Teacher teacher2=new Teacher();
        teacher2.setFirtsName("Albert");
        teacher2.setLastName("Einstein");

        final Course physics=new Course();
        physics.setName("physics");
        physics.getTeachers().add(teacher2);

        teacher1.setCourse(mathematics);
        teacher2.setCourse(physics);

        retValues.add(teacher1);
        retValues.add(teacher2);

        Student nicolaTesla= new Student();
        nicolaTesla.setFirtsName("Nicola");
        nicolaTesla.setLastName("Tesla");
        nicolaTesla.getCourses().put(mathematics, new BigDecimal("90"));
        nicolaTesla.getCourses().put(physics, new BigDecimal("100"));


        Student vasyaPupkin=new Student();
        vasyaPupkin.setFirtsName("Vasya");
        vasyaPupkin.setLastName("Pupkin");
        vasyaPupkin.getCourses().put(mathematics, new BigDecimal("50"));

        HomeTask homeTask1= new HomeTask();
        homeTask1.setCourse(teacher1.getCourse());
        homeTask1.setReviewer(teacher1);
        homeTask1.setStudent(nicolaTesla);
        homeTask1.setDeliveryDate(new Date());
        homeTask1.setScore(new BigDecimal("50"));
        homeTask1.setDueDate(new Date());

        retValues.add(homeTask1);

        HomeTask homeTask2= new HomeTask();
        homeTask2.setCourse(teacher1.getCourse());
        homeTask2.setReviewer(teacher1);
        homeTask2.setStudent(vasyaPupkin);

        homeTask2.setDeliveryDate(new Date());
        homeTask2.setScore(new BigDecimal("30"));
        homeTask2.setDueDate(new Date());
        retValues.add(homeTask2);


         return retValues;
        }


    @Transactional
    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    String fillDatabase(){


        for(BaseEntity entity:initialaizeData()){

            entityManager.persist(entity);
        }


        return "Data loaded";

    }

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }



}
