package com.students.services.dao;

import com.students.domain.common.BaseEntity;
import com.students.services.BaseService;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

/**
 * Created by kkolesnichenko on 11/25/2015.
 */
public  class GenericJpaDao<T extends BaseEntity, ID extends Serializable> implements BaseService<T,ID>{
    private Class<T> persistentClass;

    private EntityManager entityManager;

    public GenericJpaDao(Class<T> persistentClass) {
        this.persistentClass = persistentClass;
    }


    @Override
    public List<T> findAll() {
        return getEntityManager().createQuery(
                				"select x from " + getPersistentClass().getSimpleName() + " x").getResultList();
    }

    @Override
    public T findById(ID id) {
        T entity =  getEntityManager().find(getPersistentClass(), id);
        return entity;
    }

    @Transactional
    @Override
    public T save(T entity) {
        getEntityManager().persist(entity);
        return entity;
    }

    @Transactional
    @Override
    public T update(ID id, T entity) {
        T mergedEntity = getEntityManager().merge(entity);
        return mergedEntity;
    }

    @Transactional
    @Override
    public void delete(ID id) {
        delete(findById(id));
    }


    @Transactional
    public void delete(T entity) {
        //entity = getEntityManager().merge(entity);
        getEntityManager().remove(entity);
    }

    public Class<T> getPersistentClass() {
        return persistentClass;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
