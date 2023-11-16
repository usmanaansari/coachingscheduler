package com.coachingproj.scheduler.repositories;
import org.springframework.data.repository.CrudRepository;

import com.coachingproj.scheduler.models.UserNotes;

public interface UserNotesRepository extends CrudRepository<UserNotes, Integer>{
    
}
