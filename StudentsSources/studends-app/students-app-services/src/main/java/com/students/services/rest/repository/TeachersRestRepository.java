package com.students.services.rest.repository;

import com.students.domain.Teacher;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by kkolesnichenko on 12/9/2015.
 */
@RepositoryRestResource(collectionResourceRel = "teacherData", path = "teacherData")
public interface TeachersRestRepository  extends PagingAndSortingRepository<Teacher, Long> {
}
