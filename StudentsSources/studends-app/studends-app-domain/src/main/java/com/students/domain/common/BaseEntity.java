package com.students.domain.common;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Created by kkolesnichenko on 11/13/2015.
 */
@MappedSuperclass
public class BaseEntity {

    @Id
    @GenericGenerator(name="incremetGenerator" , strategy="increment")
    @GeneratedValue(generator="incremetGenerator")
    private Long id;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
