package com.coachingproj.scheduler.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.coachingproj.scheduler.dataaccess.PersonDao;
import com.coachingproj.scheduler.dataaccess.TimeslotDao;
import com.coachingproj.scheduler.dto.TimeslotDTO;
import com.coachingproj.scheduler.models.Person;
import com.coachingproj.scheduler.models.Timeslot;
import com.coachingproj.scheduler.repositories.PersonRepository;
import com.coachingproj.scheduler.repositories.TimeslotRepository;

import jakarta.transaction.Transactional;

@Service
public class TimeslotService {

    @Autowired
    private final TimeslotRepository timeslotRepository;
    private final TimeslotDao timeslotDao;
    private final PersonDao personDao;
    private final PersonRepository personRepository;

    public TimeslotService(TimeslotRepository timeslotRepository, TimeslotDao timeslotDao, PersonDao personDao,
            PersonRepository personRepository) {
        this.timeslotRepository = timeslotRepository;
        this.timeslotDao = timeslotDao;
        this.personDao = personDao;
        this.personRepository = personRepository;
    }

    @Transactional
    public List<Timeslot> findAllTimeslots() {
        return (List<Timeslot>) this.timeslotRepository.findAll();
    }

    @Transactional
    public List<Timeslot> findAllTimeslotsForCoaches() {
        return this.timeslotDao.findAllTimeslotsForCoaches();
    }

    @Transactional
    public List<Timeslot> findAllTimeslotsForStudents() {
        return this.timeslotDao.findAllTimeslotsForStudents();
    }

    @Transactional
    public Optional<Timeslot> findAllTimeslotsForStudentId(@RequestParam Integer coachId) {
        return this.timeslotRepository.findById(coachId);
    }

    @Transactional
    public List<Timeslot> getTimeslotsByUsernameForCoach(String username) {
        return this.timeslotDao.getTimeslotsByUsernameForCoach(username);
    }

    @Transactional
    public List<Timeslot> getFutureTimeslotsByUsernameForCoach(String username) {
        return this.timeslotDao.getFutureTimeslotsByUsernameForCoach(username);
    }

    @Transactional
    public List<Timeslot> getCompletedTimeslotsByUsernameForCoach(String username) {
        return this.timeslotDao.getCompletedTimeslotsByUsernameForCoach(username);
    }

    @Transactional
    public List<Timeslot> getTimeslotsByUsernameForStudent(String username) {
        return this.timeslotDao.getTimeslotsByUsernameForStudent(username);
    }

    @Transactional
    public List<Timeslot> getBookedFutureTimeslotsByUsernameForStudent(String username) {
        return this.timeslotDao.getBookedFutureTimeslotsByUsernameForStudent(username);
    }

    @Transactional
    public Timeslot createTimeslot(TimeslotDTO newTimeslotInfo) {
        Timeslot newTimeslot = new Timeslot(newTimeslotInfo);
        Optional<Person> existingCoachOptional = this.personRepository.findById(newTimeslotInfo.getCoachId());
        if (existingCoachOptional.isPresent()) {
            Person existingCoach = existingCoachOptional.get();
            existingCoach.getTimeslots().add(newTimeslot);

            try {
                this.personDao.savePerson(existingCoach);
                return newTimeslot;
            } catch (DataIntegrityViolationException e) {
                System.out.println(e.getLocalizedMessage());
            }
        }
        return null;
    }

    @Transactional
    public void markTimeslotComplete(TimeslotDTO completedTimeslotInfo) {
        Timeslot completedTimeslot = this.timeslotDao.getTimeslotById(completedTimeslotInfo.getId());
        this.timeslotDao.markTimeslotAsComplete(completedTimeslot);

    }

    @Transactional
    public void assignTimeslot(TimeslotDTO modifiedTimeslot) {
        if (validateExistingTimeslot(modifiedTimeslot.getId())) {
            Timeslot timeslotToAssignTo = this.timeslotDao.getTimeslotById(modifiedTimeslot.getId());
            timeslotToAssignTo.setStudentId(modifiedTimeslot.getStudentId());
            timeslotToAssignTo.setIsBooked(true);
            this.timeslotDao.saveAssignedTimeslot(timeslotToAssignTo);
        }
    }

    private boolean validateTimeslotUsers(TimeslotDTO newTimeslot) {
        boolean existingStudent = personRepository.existsById(newTimeslot.getStudentId());
        boolean existingCoach = personRepository.existsById(newTimeslot.getCoachId());

        if (existingStudent && existingCoach) {
            return true;
        } else {
            // Exception; select valid student or coach
        }

        return false;
    }

    private boolean validateExistingTimeslot(int timeslotId) {
        return timeslotRepository.findById(timeslotId) != null;
    }
}