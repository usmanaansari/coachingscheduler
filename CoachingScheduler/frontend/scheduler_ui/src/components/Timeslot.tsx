import React, { useState } from 'react';
import api from '../services/api';
import Person from './Person';
import RatingDialog from './RatingDialog';
interface TimeslotProps {
  timeslot: Timeslot;
  personInfo: Person | null;
  onBookNow?: () => void;
}

interface Timeslot {
  id: number;
  studentId: number | null;
  coachId: number;
  startTime: string; 
  endTime: string; 
  callCompleted: boolean;
  isBooked: boolean;
}

const formatTimeslotDate = (time: string): string => {
  return new Date(time).toLocaleString('en-US', {
    year: 'numeric',
    month: 'long',
    day: 'numeric',
    hour: 'numeric',
    minute: 'numeric',
    second: 'numeric',
    timeZoneName: 'short',
  });
};

const Timeslot: React.FC<TimeslotProps> = ({timeslot, personInfo, onBookNow}) => {
  const [isRatingPopupOpen, setRatingPopupOpen] = useState(false);
  const openRatingPopup = () => {
    setRatingPopupOpen(true);
  };

  const closeRatingPopup = () => {
    setRatingPopupOpen(false);
  };

  const handleRatingSubmit = (data: {rating: number, comment: string}) => {
    console.log('Submitted data:', data);
    api.createUserNotes(data)
  };



  const isUnbooked = timeslot.studentId === null;
  const bookedContent = isUnbooked
    ? 'Unbooked' : personInfo ? personInfo.coach && !timeslot.callCompleted ? `Booked with coach: ${personInfo.username}`
      : personInfo.coach && timeslot.callCompleted ? `Call completed with coach: ${personInfo.username}`
      : !personInfo.coach && !timeslot.callCompleted ? `Booked with student: ${personInfo.username}`
      : `Call Completed with student: ${personInfo.username}`
    : '';
  return (
    <div className="border p-4 m-4 rounded-md shadow-md bg-white">
      <h1 className={`text-lg font-semibold mb-2 ${isUnbooked ? 'text-gray-500' : 'text-black'}`}>
        {bookedContent}
      </h1>
      <p className="text-sm">Start Time: {formatTimeslotDate(timeslot.startTime)}</p>
      <p className="text-sm">End Time: {formatTimeslotDate(timeslot.endTime)}</p>
      {isUnbooked &&(
        <button onClick={onBookNow}>Book Now</button>
      )}
      {timeslot.isBooked && !timeslot.callCompleted && (
        <>
          <button style={{ backgroundColor: '#04AA6D', color: 'black' }} onClick={openRatingPopup}>Start Call</button>
          <RatingDialog isOpen={isRatingPopupOpen} onRequestClose={closeRatingPopup} onSubmit={handleRatingSubmit} />
        </>
      )}
    </div>
  );
};

export default Timeslot;