package com.coachingproj.scheduler.models;

import com.coachingproj.scheduler.dto.UserNotesDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "note")
public class UserNotes {

    @Id
    @Setter(AccessLevel.PROTECTED)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer timeslotId;
    private Integer studentRating;
    private String meetingNotes;

    public UserNotes(UserNotesDTO userNotesInfo) {
        this.studentRating = userNotesInfo.getStudentRating();
        this.meetingNotes = userNotesInfo.getMeetingNotes();
        this.timeslotId = userNotesInfo.getTimeslotId();
    }
}
