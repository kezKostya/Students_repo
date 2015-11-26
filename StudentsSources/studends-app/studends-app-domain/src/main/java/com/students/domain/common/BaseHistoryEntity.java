package com.students.domain.common;

import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * Created by kkolesnichenko on 11/13/2015.
 */
@MappedSuperclass
public class BaseHistoryEntity extends BaseEntity {

    private Date createdAt;

    private Date updatedAt;

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
