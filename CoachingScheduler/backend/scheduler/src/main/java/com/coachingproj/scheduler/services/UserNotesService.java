package com.coachingproj.scheduler.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coachingproj.scheduler.dataaccess.UserNotesDao;
import com.coachingproj.scheduler.dto.UserNotesDTO;
import com.coachingproj.scheduler.models.UserNotes;
import com.coachingproj.scheduler.repositories.UserNotesRepository;

import jakarta.transaction.Transactional;

@Service
public class UserNotesService {

    @Autowired
    private final UserNotesRepository userNotesRepository;
    private final UserNotesDao userNotesDao;

    public UserNotesService(UserNotesRepository userNotesRepository, UserNotesDao userNotesDao) {
        this.userNotesRepository = userNotesRepository;
        this.userNotesDao = userNotesDao;
    }

    @Transactional
    public List<UserNotes> findAllUserNotes() {
        return (List<UserNotes>) this.userNotesRepository.findAll();
    }

    @Transactional
    public UserNotes createUserNotes(UserNotesDTO userNotesInfo) {
        UserNotes newUserNote = new UserNotes(userNotesInfo);
        this.userNotesRepository.save(newUserNote);
        return newUserNote;
    }
}
