package com.students.services.rest.repository;

import com.students.domain.Course;
import com.students.domain.Teacher;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by kkolesnichenko on 12/9/2015.
 */
@RepositoryRestResource(collectionResourceRel = "courses", path = "courses")
public interface CoursesRestRepository extends PagingAndSortingRepository<Course, Long> {
}
