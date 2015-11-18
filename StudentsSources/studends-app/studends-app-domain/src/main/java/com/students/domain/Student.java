package com.students.domain;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by kkolesnichenko on 11/13/2015.
 */
public class Student extends Person{

    //map that represent course and related aggregated score
    private Map<Course, BigDecimal> courses=new HashMap<>();


    private Set<HomeTask> homeTasks=new HashSet<>();

    public Map<Course, BigDecimal> getCourses() {
        return courses;
    }

    public void setCourses(Map<Course, BigDecimal> courses) {
        this.courses = courses;
    }

    public Set<HomeTask> getHomeTasks() {
        return homeTasks;
    }

    public void setHomeTasks(Set<HomeTask> homeTasks) {
        this.homeTasks = homeTasks;
    }
}
