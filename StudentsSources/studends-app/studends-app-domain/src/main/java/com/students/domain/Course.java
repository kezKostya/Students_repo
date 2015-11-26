package com.students.domain;

import com.students.domain.common.BaseEntity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by kkolesnichenko on 11/13/2015.
 */
@Entity
public class Course extends BaseEntity {

    private String name;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "course", fetch = FetchType.EAGER)
    private Set<Teacher> teachers=new HashSet<>();



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(Set<Teacher> teachers) {
        this.teachers = teachers;
    }

    @Override
    public boolean equals(Object another){
        if(another instanceof Course){
            return this.getName().equals(((Course) another).getName());
        }
        return false;
    }

    public int hashCode(){
        return getName().hashCode();
    }

    @Override
    public String toString(){
        return getClass().getSimpleName() +" : "+getName();
    }

}
