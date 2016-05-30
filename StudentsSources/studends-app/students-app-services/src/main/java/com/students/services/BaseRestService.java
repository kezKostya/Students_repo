package com.students.services;

import com.students.domain.common.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

/**
 * Created by kkolesnichenko on 11/14/2015.
 */
//@RequestMapping(value = "/")
public abstract class BaseRestService<T extends BaseEntity, ID extends Serializable, DELEGATE extends BaseService<T, ID>> implements BaseService<T, ID>  {


    //@Autowired
    private DELEGATE delegate;

    //@RequestMapping(method = RequestMethod.GET)
    public  @ResponseBody
    List<T> findAll(){
        return delegate.findAll();
    }

    //@RequestMapping(value="{id}",method = RequestMethod.GET)
    public @ResponseBody T findById(@PathVariable ID id) {
        return delegate.findById(id);
    }

    //@RequestMapping(method = RequestMethod.POST)
    @Override
    public @ResponseBody T save(@RequestBody T request) {
        return delegate.save(request);
    }

    //@RequestMapping(value="{id}",method = RequestMethod.PUT)
    public @ResponseBody T update(@PathVariable ID id, @RequestBody T request) {
        return delegate.update(id,request);
    }

    //@RequestMapping(value="{id}",method = RequestMethod.DELETE)
    @Override
    public void delete(@PathVariable ID id) {
        delegate.delete(id);
    }


    public DELEGATE getDelegate() {
        return delegate;
    }

    public void setDelegate(DELEGATE delegate) {
        this.delegate = delegate;
    }
}
