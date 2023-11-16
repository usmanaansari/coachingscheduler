package com.coachingproj.scheduler.dataaccess;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.coachingproj.scheduler.models.Person;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Repository
public class PersonDao {

    @PersistenceContext
    private EntityManager em;

    public List<Person> getAllStudents(){
        TypedQuery<Person> typedQuery = getEm().createQuery("SELECT p FROM Person p WHERE p.isCoach = false", Person.class);
        List<Person> results = typedQuery.getResultList();
        return results;
    }

    public List<Person> getAllCoaches(){
        TypedQuery<Person> typedQuery = getEm().createQuery("SELECT p FROM Person p WHERE p.isCoach = true", Person.class);
        List<Person> results = typedQuery.getResultList();
        return results;
    }

    public Person getPersonById(Integer id){
        TypedQuery<Person> typedQuery = getEm().createQuery("SELECT p FROM Person p WHERE p.id = :id", Person.class);
        typedQuery.setParameter("id", id);
        Person results = typedQuery.getSingleResult();
        return results;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void savePerson(Person p){
        getEm().merge(p);
    }
    
}
