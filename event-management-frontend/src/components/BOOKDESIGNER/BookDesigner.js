import React, { useState } from 'react';
import styles from './BookDesigner.module.css';

function BookDesigner({ onBack }) {
  const [selectedEvent, setSelectedEvent] = useState('');
  const [selectedDesigner, setSelectedDesigner] = useState('');

  // Dummy data for events and designers - replace with your actual data
  const availableEvents = ['Wedding Ceremony', 'Birthday Party', 'Corporate Gala', 'Product Launch'];
  const availableDesigners = ['Elegant Decor', 'Creative Spaces', 'Themed Events Co.', 'Visionary Designs'];

  const handleEventChange = (event) => {
    setSelectedEvent(event.target.value);
  };

  const handleDesignerChange = (event) => {
    setSelectedDesigner(event.target.value);
  };

  const handleProceed = () => {
    if (selectedEvent && selectedDesigner) {
      console.log('Booking Designer:', { event: selectedEvent, designer: selectedDesigner });
      alert(`Designer "${selectedDesigner}" booked for "${selectedEvent}"!`);
      // In a real application, you would handle the booking logic here
    } else {
      alert('Please select both an event and a designer.');
    }
  };

  return (
    <div className={styles.bookDesignerContainer}>
      <h2>Book a Designer</h2>
      <div className={styles.formGroup}>
        <label htmlFor="event">Select Event:</label>
        <select id="event" value={selectedEvent} onChange={handleEventChange}>
          <option value="">Select an event</option>
          {availableEvents.map((event) => (
            <option key={event} value={event}>
              {event}
            </option>
          ))}
        </select>
      </div>
      <div className={styles.formGroup}>
        <label htmlFor="designer">Select Designer:</label>
        <select id="designer" value={selectedDesigner} onChange={handleDesignerChange}>
          <option value="">Select a designer</option>
          {availableDesigners.map((designer) => (
            <option key={designer} value={designer}>
              {designer}
            </option>
          ))}
        </select>
      </div>
      <div className={styles.buttonGroup}>
        <button type="button" onClick={onBack}>
          Back
        </button>
        <button type="button" onClick={handleProceed}>
          Proceed
        </button>
      </div>
    </div>
  );
}

export default BookDesigner;