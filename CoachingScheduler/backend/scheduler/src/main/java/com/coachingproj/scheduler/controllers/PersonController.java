package com.coachingproj.scheduler.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coachingproj.scheduler.models.Person;
import com.coachingproj.scheduler.services.PersonService;

import jakarta.ws.rs.core.MediaType;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON)
public class PersonController {
   
     private PersonService personService;

     public PersonController(PersonService personService){
      this.personService = personService;
     }

     @GetMapping("/allCoaches")
     public Iterable<Person> findAllCoaches (){
      return this.personService.findAllCoaches();
     }

     @GetMapping("/allStudents")
     public Iterable<Person> findAllStudents (){
        return this.personService.findAllStudents();
     }

     @GetMapping("/getPersonById")
     public Person findPerson(Integer id){
         return this.personService.findPersonById(id);
     }

   public PersonService getPersonService() {
      return personService;
   }

   public void setPersonService(PersonService personService) {
      this.personService = personService;
   }     
}
