package com.coachingproj.scheduler.services;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coachingproj.scheduler.dataaccess.PersonDao;
import com.coachingproj.scheduler.models.Person;
import com.coachingproj.scheduler.repositories.PersonRepository;

import jakarta.transaction.Transactional;

@Service
public class PersonService{
    @Autowired
    private final PersonRepository personRepository;
    private final PersonDao personDao;

    public PersonService(PersonRepository personRepository, PersonDao personDao){
        this.personDao = personDao;
        this.personRepository = personRepository;
    }

    @Transactional
    public List<Person> findAllStudents(){
        return this.personDao.getAllStudents();
    }

    @Transactional
    public List<Person> findAllCoaches(){
        return this.personDao.getAllCoaches();
    }

    @Transactional
    public Person findPersonById(Integer id){
        return this.personDao.getPersonById(id);
    }

}