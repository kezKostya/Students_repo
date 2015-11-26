package com.students.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by kkolesnichenko on 11/13/2015.
 */
@Entity
public class Student extends Person{

    //map that represent course and related aggregated score
    @ElementCollection(fetch = FetchType.EAGER)
    @Column(name="score")
    @JoinTable(name="students_score")
    private Map<Course, BigDecimal> courses=new HashMap<>();


    @OneToMany(mappedBy = "student", fetch = FetchType.EAGER)
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
