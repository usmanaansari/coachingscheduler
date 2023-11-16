import React, { useEffect, useState } from "react";
import api from '../services/api';
import '../styles/CoachingSchedule.css';
import BookingDialog from "./BookingDialog";
import Person from "./Person";
import Timeslot from './Timeslot';
interface CoachingScheduleProps {

}

const CoachingSchedule: React.FC<CoachingScheduleProps> = () => {
    const [coachSchedule, setCoachSchedule] = useState<Timeslot[] | null>(null);
    const [filteredCoachSchedule, setFilteredCoachSchedule] = useState<Timeslot[] | null>(null);
    const [studentSchedule, setStudentSchedule] = useState<Timeslot[] | null>(null);
    const[filteredStudentSchedule, setFilteredStudentSchedule] = useState<Timeslot[] | null>(null);
    const [coaches, setCoaches] = useState<Person[] | null>(null);
    const [students, setStudents] = useState<Person[]| null>(null);
    const [isBookingDialogOpen, setIsBookingDialogOpen] = useState(false);
    const [selectedCoach, setSelectedCoach] = useState<number | null>(null);
    const [selectedStudent, setSelectedStudent] = useState<number | null>(null);

    useEffect(() => {
        api.getAllTimeslotsForCoaches()
          .then(data => setCoachSchedule(data))
          .catch(error => console.error('Error fetching coach schedule:', error));
    }, []);

    useEffect(() => {
      api.getAllTimeslotsForStudents()
        .then(data => setStudentSchedule(data))
        .catch(error => console.error('Error fetching student schedule:', error));
  }, []);

    useEffect(() => {
      api.getAllCoaches().then(data => setCoaches(data)).catch(error => console.error('Error fetching all coaches:', error));
    }, [])

    const renderCoachOptions = () => {
      return coaches?.map((coach) => (
        <option key={coach.id} value={coach.username}>
          {coach.username}
        </option>
      ));
    };

    useEffect(() => {
      api.getAllStudents().then(data => setStudents(data)).catch(error => console.error('Error fetching all students:', error));
    }, [])

    const renderStudentOptions = () => {
      return students?.map((student) => (
        <option key={student.id} value={student.username}>
          {student.username}
        </option>
      ));
    };

    const getStudentInfo = (studentId: number | null): Person | null => {
      return studentId !== null && students
        ? students.find((student) => student.id === studentId) || null
        : null;
    }

    const getCoachInfo = (coachId: number | null): Person | null => {
      return coachId !== null && coaches
        ? coaches.find((coach) => coach.id === coachId) || null
        : null;
    };

    if (coachSchedule === null) {
    return <div>Loading...</div>;
    }

    const handleFilterCoach = (coachUsername: string) => {
      const relevantCoach = coaches?.find((coach) => coach.username === coachUsername)
      const numId = relevantCoach?.id

      if(numId !== undefined){
        const selectedCoachId = numId;
    
        const filteredTimeslots: Timeslot[] = (coachSchedule as Timeslot[])
        .filter((timeslot) => timeslot.coachId === selectedCoachId);
      
        setSelectedCoach(selectedCoachId);
        setFilteredCoachSchedule(filteredTimeslots.length > 0 ? filteredTimeslots : null);
      }
      
    };


    const handleFilterStudent = (studentUsername: string) => {
      const relevantStudent = students?.find((student) => student.username === studentUsername)
      const numId = relevantStudent?.id

      if(numId !== undefined){
        const selectedStudentId = numId;
    
        const filteredTimeslots: Timeslot[] = (studentSchedule as Timeslot[])
        .filter((timeslot) => timeslot.studentId === selectedStudentId);
      
        setSelectedStudent(selectedStudentId);
        setFilteredStudentSchedule(filteredTimeslots.length > 0 ? filteredTimeslots : null);
      }
      
    };

  const openBookingDialog = () => {
    setIsBookingDialogOpen(true);
  };

  const closeBookingDialog = () => {
    setIsBookingDialogOpen(false);
  };

  const handleCreateTimeslot = (coachId: number, selectedStartDate:string, selectedStartTime:string, selectedEndTime:string) => {
    console.log(coachId);
    console.log(selectedStartDate);
    console.log(selectedStartTime);
    console.log(selectedEndTime);
    api.createTimeslotEntry({coachId: coachId, startTime: selectedStartDate, 
      endTime: selectedEndTime, isBooked: false, callCompleted: false})
    closeBookingDialog();
  };

  const onBookNow = () => {
    openBookingDialog()
    //console.log(coachId)
  };

    return (
        <div className="coaching-schedule-container">
          <div className="coaching-schedule-section">
            <h1>Coach Schedule</h1>
            <label>Select Coach:</label>
            <select onChange={(e) => handleFilterCoach(e.target.value)}>
                {renderCoachOptions()}
            </select>


            {filteredCoachSchedule?.map((timeslot: Timeslot) => {
                return (
                <div key={timeslot.id}>
                    <Timeslot key={timeslot.id} 
                    timeslot={timeslot} 
                    personInfo={getStudentInfo(timeslot.studentId)} />
                </div>
                );
            })}

        <BookingDialog
            isOpen={isBookingDialogOpen}
            onRequestClose={closeBookingDialog}
            onAssign={handleCreateTimeslot}
            coaches={coaches}
            selectedCoach={selectedCoach}
        />

        {/* Button to Open Booking Dialog */}
        <button onClick={openBookingDialog}>Add Available Timeslot</button>
        </div>
        <div>
          <h1>Student Schedule</h1>
              <label>Select Student:</label>
              <select onChange={(e) => handleFilterStudent(e.target.value)}>
                  {renderStudentOptions()}
              </select>


              {filteredStudentSchedule?.map((timeslot: Timeslot) => {
                  return (
                  <div key={timeslot.id}>
                      <Timeslot key={timeslot.id} 
                      timeslot={timeslot} 
                      personInfo={getCoachInfo(timeslot.coachId)} 
                      onBookNow={() => onBookNow}
                      />
                  </div>
                  );
              })}
        </div>
      </div>
    )
}
export default CoachingSchedule;