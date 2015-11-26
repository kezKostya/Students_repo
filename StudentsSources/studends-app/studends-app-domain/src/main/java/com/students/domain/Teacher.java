package com.students.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

/**
 * Created by kkolesnichenko on 11/13/2015.
 */
@Entity
public class Teacher extends Person{

    @ManyToOne(cascade =  {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    private Course course;

    @JsonIgnore
    public Course getCourse() {
        return course;
    }

    public Long getCourseId(){
        return course.getId();
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
