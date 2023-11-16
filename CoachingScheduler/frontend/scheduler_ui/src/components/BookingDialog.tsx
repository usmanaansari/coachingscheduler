import React, { useState } from 'react';
import Modal from 'react-modal';
import '../styles/BookingDialog.css';
import Person from './Person';

interface BookingDialogProps {
  isOpen: boolean;
  onRequestClose: () => void;
  onAssign: (coachId: number, selectedStartDate:string, selectedStartTime:string, selectedEndTime:string) => void;
  coaches: Person[] | null;
  selectedCoach: number | null;
}

const BookingDialog: React.FC<BookingDialogProps> = ({
  isOpen,
  onRequestClose,
  onAssign,
  coaches,
  selectedCoach,
}) => {
  const [selectedStartDate, setSelectedStartDate] = useState<string>('');
  const [selectedStartTime, setSelectedStartTime] = useState<string>('');
  const [selectedEndTime, setSelectedEndTime] = useState<string>('');

  function getCurrentDate(): string {
    const today = new Date();
    const year = today.getFullYear();
    const month = String(today.getMonth() + 1).padStart(2, '0');
    const day = String(today.getDate()).padStart(2, '0');
    return `${year}-${month}-${day}`;
  }

  function handleStartTimeChange(value: string): void {
    setSelectedStartTime(value);
    const startTime = new Date(`${selectedStartDate}T${value}`);
    const endTime = new Date(startTime);
    endTime.setHours(startTime.getHours() + 2);
    const formattedEndTime = endTime.toTimeString().slice(0, 5);
    setSelectedEndTime(formattedEndTime);
  }

  const isOverlap = (): boolean => {
    if (selectedCoach && selectedStartDate && selectedStartTime && selectedEndTime) {
      const newStartTime = new Date(`${selectedStartDate}T${selectedStartTime}`);
      const newEndTime = new Date(`${selectedStartDate}T${selectedEndTime}`);

      return coaches?.some((coach) => {
        return (
          coach.id === selectedCoach &&
          coach.timeslots?.some((timeslot) => {
            const timeslotStartTime = new Date(timeslot.startTime);
            const timeslotEndTime = new Date(timeslot.endTime);

            return (
              (newStartTime >= timeslotStartTime && newStartTime < timeslotEndTime) ||
              (newEndTime > timeslotStartTime && newEndTime <= timeslotEndTime) ||
              (newStartTime <= timeslotStartTime && newEndTime >= timeslotEndTime)
            );
          })
        );
      }) || false;
    }
    return false;
  };

  const handleCreateTimeslot = () => {
    if (isOverlap()) {
      console.error('Overlap detected. Please choose a different timeslot.');
    } else {
      if (selectedCoach !== null && selectedStartDate && selectedStartTime && selectedEndTime) {
        onAssign(selectedCoach, selectedStartDate, selectedStartTime, selectedEndTime);
        onRequestClose();
      }
    }
  };

  return (
    <Modal
      isOpen={isOpen}
      onRequestClose={onRequestClose}
      contentLabel="Booking Modal"
      style={{
        content: {
          width: '80vw', 
          margin: 'auto',
        },
      }}
      className="booking-dialog"
      overlayClassName="booking-dialog-overlay" 
    >
      <div className="booking-dialog-header">
        <h1>{selectedCoach ? `Assign Timeslot for ${selectedCoach}` : 'Create Timeslot'}</h1>
        <button className="close-button" onClick={onRequestClose}>
          X
        </button>
      </div>
      <div className="datetime-input-row">
        <div>
          <label>Select Date:</label>
          <input
            type="date"
            value={selectedStartDate}
            onChange={(e) => setSelectedStartDate(e.target.value)}
            min={getCurrentDate()}
          />
        </div>
        <div>
          <label>Start Time:</label>
          <input
            type="time"
            value={selectedStartTime}
            onChange={(e) => handleStartTimeChange(e.target.value)}
          />
        </div>
        <div>
          <label>End Time:</label>
          <input type="time" value={selectedEndTime} readOnly />
        </div>
      </div>
      <button onClick={handleCreateTimeslot}>Create</button>
    </Modal>
  );
};

export default BookingDialog;