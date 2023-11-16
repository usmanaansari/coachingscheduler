package com.coachingproj.scheduler.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class UserNotesDTO {
    private Integer id;
    private int studentRating;
    private String meetingNotes;
    private int timeslotId;
}
