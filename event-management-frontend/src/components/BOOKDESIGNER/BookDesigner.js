import React, { useState, useEffect } from 'react';
import styles from './BookDesigner.module.css';

function BookDesigner({ onBack }) {
  const [selectedEvent, setSelectedEvent] = useState('');
  const [selectedDesigner, setSelectedDesigner] = useState('');
  const [availableEvents, setAvailableEvents] = useState([]);
  const [availableDesigners, setAvailableDesigners] = useState([]);
  const [loadingEvents, setLoadingEvents] = useState(true);
  const [loadingDesigners, setLoadingDesigners] = useState(true);

  useEffect(() => {
    // Fetch events from backend
    fetch('http://localhost:8080/api/organizer/getEvents')
      .then((response) => response.json())
      .then((data) => {
        setAvailableEvents(data);
        setLoadingEvents(false);
      })
      .catch((error) => {
        console.error('Error fetching events:', error);
        setLoadingEvents(false);
      });

    // Fetch designers from backend
    fetch('http://localhost:8080/api/organizer/getInteriorDesigners')
      .then((response) => response.json())
      .then((data) => {
        setAvailableDesigners(data);
        setLoadingDesigners(false);
      })
      .catch((error) => {
        console.error('Error fetching designers:', error);
        setLoadingDesigners(false);
      });
  }, []);

  const handleEventChange = (event) => {
    setSelectedEvent(event.target.value);
  };

  const handleDesignerChange = (event) => {
    setSelectedDesigner(event.target.value);
  };

  const handleProceed = () => {
    if (selectedEvent && selectedDesigner) {
      console.log('Booking Designer:', { event: selectedEvent, designer: selectedDesigner });

      fetch('http://localhost:8080/api/organizer/bookInterior', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          event: selectedEvent,
          designer: selectedDesigner,
        }),
      })
        .then((response) => response.json())
        .then((data) => {
          if (data.success) {
            alert(`Designer "${selectedDesigner}" successfully booked for "${selectedEvent}"!`);
          } else {
            alert('Booking failed. Please try again.');
          }
        })
        .catch((error) => {
          console.error('Error booking designer:', error);
        });
    } else {
      alert('Please select both an event and a designer.');
    }
  };

  return (
    <div className={styles.bookDesignerContainer}>
      <h2>Book a Designer</h2>

      <div className={styles.formGroup}>
        <label htmlFor="event">Select Event:</label>
        {loadingEvents ? (
          <p>Loading events...</p>
        ) : (
          <select id="event" value={selectedEvent} onChange={handleEventChange}>
            <option value="">Select an event</option>
            {availableEvents.map((event) => (
              <option key={event.eventID} value={event.name}>
                {event.name}
              </option>
            ))}
          </select>
        )}
      </div>

      <div className={styles.formGroup}>
        <label htmlFor="designer">Select Designer:</label>
        {loadingDesigners ? (
          <p>Loading designers...</p>
        ) : (
          <select id="designer" value={selectedDesigner} onChange={handleDesignerChange}>
            <option value="">Select a designer</option>
            {availableDesigners.map((designer) => (
              <option key={designer.serviceID} value={designer.serviceName}>
                {designer.serviceName}
              </option>
            ))}
          </select>
        )}
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
