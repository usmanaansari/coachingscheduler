package com.coachingproj.scheduler.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coachingproj.scheduler.models.Timeslot;

public interface TimeslotRepository extends JpaRepository<Timeslot, Integer>{
    
    // @Query(nativeQuery = true, value="SELECT ts FROM Timeslot ts")
    // List<Timeslot> findAll();

    // @Query(nativeQuery = true, value="SELECT ts from Timeslot ts WHERE ts.username =?1")
    // List<Timeslot> findByUsername(String username);

    // @Query(nativeQuery = true, value="SELECT t from Timeslot ts WHERE ts.student_id =?1")
    // List<Timeslot> findByStudentId(Integer studentId);

}
