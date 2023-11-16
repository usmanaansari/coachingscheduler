package com.coachingproj.scheduler.dataaccess;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.coachingproj.scheduler.models.Timeslot;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Repository
public class TimeslotDao {

    @PersistenceContext
    private EntityManager em;

    public Timeslot getTimeslotById(Integer timeslotId) {
        TypedQuery<Timeslot> typedQuery = getEm().createQuery("SELECT ts FROM Timeslot ts WHERE ts.id = :id",
                Timeslot.class);
        typedQuery.setParameter("id", timeslotId);
        Timeslot result = typedQuery.getSingleResult();
        return result;
    }

    public List<Timeslot> findAllTimeslotsForCoaches() {
        TypedQuery<Timeslot> typedQuery = getEm()
                .createQuery("SELECT ts FROM Timeslot ts INNER JOIN Person p ON p.isCoach = true", Timeslot.class);
        List<Timeslot> results = typedQuery.getResultList();
        return results;
    }

    public List<Timeslot> findAllTimeslotsForStudents() {
        TypedQuery<Timeslot> typedQuery = getEm().createQuery(
                "SELECT ts FROM Timeslot ts INNER JOIN Person p ON p.isCoach = false WHERE ts.studentId != null",
                Timeslot.class);
        List<Timeslot> results = typedQuery.getResultList();
        return results;
    }

    public List<Timeslot> getTimeslotsByUsernameForCoach(String username) {
        TypedQuery<Timeslot> typedQuery = getEm().createQuery(
                "SELECT ts FROM Timeslot ts INNER JOIN Person p ON p.username = :username AND p.isCoach = true",
                Timeslot.class);
        typedQuery.setParameter("username", username);
        List<Timeslot> results = typedQuery.getResultList();
        return results;
    }

    public List<Timeslot> getTimeslotsByUsernameForStudent(String username) {
        TypedQuery<Timeslot> typedQuery = getEm().createQuery(
                "SELECT ts FROM Timeslot ts INNER JOIN Person p ON p.username = :username WHERE p.id = ts.studentId",
                Timeslot.class);
        typedQuery.setParameter("username", username);
        List<Timeslot> results = typedQuery.getResultList();
        return results;
    }

    public List<Timeslot> getCompletedTimeslotsByUsernameForCoach(String username) {
        TypedQuery<Timeslot> typedQuery = getEm().createQuery(
                "SELECT ts FROM Timeslot ts INNER JOIN Person p ON p.username = :username WHERE ts.callCompleted = true AND p.isCoach = true)",
                Timeslot.class);
        typedQuery.setParameter("username", username);
        List<Timeslot> results = typedQuery.getResultList();
        return results;
    }

    public List<Timeslot> getFutureTimeslotsByUsernameForCoach(String username) {
        TypedQuery<Timeslot> typedQuery = getEm().createQuery(
                "SELECT ts FROM Timeslot ts INNER JOIN Person p ON p.username = :username WHERE ts.startTime > now() AND p.isCoach = true",
                Timeslot.class);
        typedQuery.setParameter("username", username);
        List<Timeslot> results = typedQuery.getResultList();
        return results;
    }

    public List<Timeslot> getBookedFutureTimeslotsByUsernameForStudent(String username) {
        TypedQuery<Timeslot> typedQuery = getEm().createQuery(
                "SELECT ts FROM Timeslot ts INNER JOIN Person p ON p.username = :username WHERE ts.startTime > now() AND ts.isBooked = true AND p.id = ts.studentId",
                Timeslot.class);
        typedQuery.setParameter("username", username);
        List<Timeslot> results = typedQuery.getResultList();
        return results;
    }

    public void markTimeslotAsComplete(Timeslot completedTimeslot) {
        completedTimeslot.setCallCompleted(true);
        getEm().merge(completedTimeslot);
    }

    public void saveAssignedTimeslot(Timeslot assignedTimeslot) {
        getEm().merge(assignedTimeslot);
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
}
