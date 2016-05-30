package com.students.services.rest.repository;

import com.students.domain.Person;
import com.students.domain.Student;
import com.students.services.common.CompareOperator;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.math.BigDecimal;
import java.util.Collection;

/**
 * Created by kkolesnichenko on 12/9/2015.
 */
@RepositoryRestResource(collectionResourceRel = "students", path = "students")
public interface StudentRestRepository  extends PagingAndSortingRepository<Person, Long> {

    //@Query(value = "SELECT u FROM User u WHERE ....")
    Collection<Student> findStudentWithScore(@Param(value="courseId") Long courseId,
                                             @Param(value="score") BigDecimal score,
                                             @Param(value="operator") CompareOperator operator);
}
