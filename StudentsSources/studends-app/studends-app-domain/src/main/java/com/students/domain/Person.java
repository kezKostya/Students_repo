package com.students.domain;

import com.students.domain.common.BaseEntity;
import com.students.domain.common.BaseHistoryEntity;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by kkolesnichenko on 11/13/2015.
 */
@Entity
@Table(name="Persons")
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Person extends BaseHistoryEntity {

    private String firtsName;

    private String secondName;

    private String lastName;

    private Date dateOfBith;


    public String getFirtsName() {
        return firtsName;
    }

    public void setFirtsName(String firtsName) {
        this.firtsName = firtsName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBith() {
        return dateOfBith;
    }

    public void setDateOfBith(Date dateOfBith) {
        this.dateOfBith = dateOfBith;
    }
}
