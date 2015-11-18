package com.students.services.impl;

import com.students.domain.Student;
import com.students.domain.common.BaseEntity;
import com.students.services.BaseService;

import java.io.Serializable;
import java.util.List;

/**
 * Created by kkolesnichenko on 11/18/2015.
 */
public  class GenericDataService<T extends BaseEntity, ID extends Long> implements BaseService<T, ID> {


    private List<T> data;

    @Override
    public List<T> findAll() {
        return  getData();
    }

    @Override
    public T findById(ID id) {
        return  getData().stream().filter((s)-> s.getId().equals(id)).findAny().get();
    }

    @Override
    public T save(T entity) {
        getData().add(entity);
        return entity;
    }

    @Override
    public T update(ID id, T entity) {
        getData().stream().map((s)->s.getId().equals(id)? entity: s);
        entity.setId(id);
        return entity;
    }

    @Override
    public void delete(ID id) {
        getData().stream().filter((s)-> s.getId().equals(id)).peek(data::remove);
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
