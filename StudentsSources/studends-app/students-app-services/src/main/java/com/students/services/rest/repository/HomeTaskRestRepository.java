package com.students.services.rest.repository;

import com.students.domain.HomeTask;
import com.students.domain.Person;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by kkolesnichenko on 12/9/2015.
 */
@RepositoryRestResource(collectionResourceRel = "homeTasks", path = "homeTasks")
public interface HomeTaskRestRepository extends PagingAndSortingRepository<HomeTask, Long> {
}
