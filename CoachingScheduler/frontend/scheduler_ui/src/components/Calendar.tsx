import { addDays, addMonths, endOfMonth, endOfWeek, format, isSameDay, isSameMonth, startOfMonth, startOfWeek, subMonths } from 'date-fns';
import React, { useState } from 'react';
import '../index.css';

interface CalendarProps {
}

const Calendar: React.FC<CalendarProps> = () => {
  const [currentMonth, setCurrentMonth] = useState<Date>(new Date());

  const startDate = startOfWeek(startOfMonth(currentMonth));
  const endDate = endOfWeek(endOfMonth(currentMonth));

  const days: Date[] = [];
  let currentDate = startDate;

  while (currentDate <= endDate) {
    days.push(currentDate);
    currentDate = addDays(currentDate, 1);
  }

  const handlePrevMonth = () => {
    setCurrentMonth(subMonths(currentMonth, 1));
  };

  const handleNextMonth = () => {
    setCurrentMonth(addMonths(currentMonth, 1));
  };

  const handleDateClick = (date: Date) => {
    console.log('Selected date:', date);
  };

  return (
    <div className="container mx-auto mt-8">
      <div className="mb-4 flex justify-between items-center">
        <button className="text-lg font-bold" onClick={handlePrevMonth}>
          {"<"}
        </button>
        <h1 className="text-2xl font-bold">
          {format(currentMonth, "MMMM yyyy")}
        </h1>
        <button className="text-lg font-bold" onClick={handleNextMonth}>
          {">"}
        </button>
      </div>
      <div className="grid grid-cols-7 gap-2">
        {days.map((date) => (
          <div
            key={date.toString()}
            className={`p-2 text-center ${
              !isSameMonth(date, currentMonth) ? "text-gray-400" : ""
            } ${isSameDay(date, new Date()) ? "bg-blue-200" : ""}`}
            onClick={() => handleDateClick(date)}
          >
            {format(date, "d")}
          </div>
        ))}
      </div>
    </div>
  );
};

export default Calendar;
