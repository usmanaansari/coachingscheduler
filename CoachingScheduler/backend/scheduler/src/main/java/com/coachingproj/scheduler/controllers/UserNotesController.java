package com.coachingproj.scheduler.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.coachingproj.scheduler.dto.UserNotesDTO;
import com.coachingproj.scheduler.models.UserNotes;
import com.coachingproj.scheduler.services.UserNotesService;

import jakarta.ws.rs.core.MediaType;

@RestController
public class UserNotesController {
   private final UserNotesService userNotesService;

   public UserNotesController(UserNotesService userNotesService) {
      this.userNotesService = userNotesService;
   }

   @GetMapping("/allUserNotes")
   public Iterable<UserNotes> findAllUserNotes() {
      return this.userNotesService.findAllUserNotes();
   }

   @PostMapping(value = "/createUserNotes", consumes = MediaType.APPLICATION_JSON)
   public UserNotes createUserNotes(@RequestBody UserNotesDTO newUserNotes) {
      return this.userNotesService.createUserNotes(newUserNotes);
   }

   // findAllUserNotesByACoach
   // findAllUserNotesForOneStudent

}
