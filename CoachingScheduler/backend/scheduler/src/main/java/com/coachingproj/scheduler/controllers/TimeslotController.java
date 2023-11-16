package com.coachingproj.scheduler.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.coachingproj.scheduler.dto.TimeslotDTO;
import com.coachingproj.scheduler.models.Timeslot;
import com.coachingproj.scheduler.services.TimeslotService;

import jakarta.ws.rs.core.MediaType;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON)
public class TimeslotController {

   private TimeslotService timeslotService;

   public TimeslotController(TimeslotService timeslotService) {
      this.timeslotService = timeslotService;
   }

   @GetMapping("/allTimeslots")
   public Iterable<Timeslot> findAllTimeslots() {
      return this.timeslotService.findAllTimeslots();
   }

   @GetMapping("/allTimeslotsForCoaches")
   public Iterable<Timeslot> findAllTimeslotsForCoaches() {
      return this.timeslotService.findAllTimeslotsForCoaches();
   }

   @GetMapping("/allTimeslotsForStudents")
   public Iterable<Timeslot> findAllTimeslotsForStudents() {
      return this.timeslotService.findAllTimeslotsForStudents();
   }

   @GetMapping("/timeslot")
   public List<Timeslot> findAllTimeslotsForCoach(@RequestParam Integer coachId) {
      // this.timeslotService.findAllTimeslotsForCoach(coachId);
      return new ArrayList<>();
   }

   @GetMapping("/allTimeslotsForCoach")
   public Object getTimeslotsByUsernameForCoach(@RequestParam String username) {
      return this.timeslotService.getTimeslotsByUsernameForCoach(username);
   }

   @GetMapping("/completedTimeslotsForCoach")
   public Object getCompletedTimeslotsByUsernameForCoach(@RequestParam String username) {
      return this.timeslotService.getCompletedTimeslotsByUsernameForCoach(username);
   }

   @GetMapping("/allFutureTimeslotsForCoach")
   public Object findFutureTimeslotsByUsernameForCoach(@RequestParam String username) {
      return this.timeslotService.getFutureTimeslotsByUsernameForCoach(username);
   }

   @GetMapping("/allTimeslotsForStudent")
   public Object getTimeslotsByUsernameForStudent(@RequestParam String username) {
      return this.timeslotService.getTimeslotsByUsernameForStudent(username);
   }

   @GetMapping("/bookedFutureTimeslotsForStudent")
   public Object getBookedFutureTimeslotsByUsernameForStudent(@RequestParam String username) {
      return this.timeslotService.getBookedFutureTimeslotsByUsernameForStudent(username);
   }

   @PostMapping(value = "/createTimeslot", consumes = MediaType.APPLICATION_JSON)
   public Object createTimeslotForUser(@RequestBody TimeslotDTO newTimeslot) {
      return this.timeslotService.createTimeslot(newTimeslot);
   }

   @PostMapping("/markTimeslotComplete")
   public void markTimeslotComplete(@RequestBody TimeslotDTO newTimeslot) {
      this.timeslotService.markTimeslotComplete(newTimeslot);
   }

   @PostMapping(value = "/assignTimeslot", consumes = MediaType.APPLICATION_JSON)
   public void assignTimeslot(@RequestBody TimeslotDTO modifiedTimeslot) {
      this.timeslotService.assignTimeslot(modifiedTimeslot);
   }

   public TimeslotService getTimeslotService() {
      return timeslotService;
   }

   public void setTimeslotService(TimeslotService timeslotService) {
      this.timeslotService = timeslotService;
   }

}
