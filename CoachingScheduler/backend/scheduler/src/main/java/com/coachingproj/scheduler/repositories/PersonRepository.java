package com.coachingproj.scheduler.repositories;

import org.springframework.data.repository.CrudRepository;

import com.coachingproj.scheduler.models.Person;

public interface PersonRepository extends CrudRepository<Person, Integer>{
    
}
