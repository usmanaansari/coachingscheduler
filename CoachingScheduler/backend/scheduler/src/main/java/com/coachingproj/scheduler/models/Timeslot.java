package com.coachingproj.scheduler.models;

import java.time.LocalDateTime;

import com.coachingproj.scheduler.dto.TimeslotDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString()
@EqualsAndHashCode
@Entity
@Table(name = "timeslot", schema = "public")
public class Timeslot {

    @Id
    @Setter(AccessLevel.PROTECTED)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Column(name = "student_id")
    private Integer studentId;

    @Column(name = "slot_start")
    private LocalDateTime startTime;
    @Column(name = "slot_end")
    private LocalDateTime endTime;
    @Column(name = "is_booked")
    private Boolean isBooked;
    @Column(name = "is_call_completed")
    private Boolean callCompleted;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "coach_id", insertable = false, updatable = false)
    private Person person;

    @Column(name = "coach_id", nullable = false)
    private Integer coachId;

    public Timeslot(TimeslotDTO timeslotInfo) {
        this.studentId = timeslotInfo.getStudentId();
        this.isBooked = timeslotInfo.getIsBooked();
        this.startTime = timeslotInfo.getStartTime();
        this.endTime = timeslotInfo.getEndTime();
        this.coachId = timeslotInfo.getCoachId();
    }
}
