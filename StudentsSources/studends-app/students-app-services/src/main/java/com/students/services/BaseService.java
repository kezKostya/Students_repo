package com.students.services;

import com.students.domain.common.BaseEntity;


import java.io.Serializable;
import java.util.List;

/**
 * Created by kkolesnichenko on 11/13/2015.
 */
public interface BaseService<T extends BaseEntity, ID extends Serializable> {

    List<T>  findAll();

    T findById( ID id);

    T save(T var1);

    T update(ID id,T var1);


    void delete( ID id);
}
