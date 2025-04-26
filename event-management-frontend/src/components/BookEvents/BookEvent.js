import React, { useState } from 'react';
import styles from './BookEvent.module.css';

function BookEvent({ onBack, onBookVendor, onBookDesigner }) {
  const [dateTime, setDateTime] = useState('');
  const [eventName, setEventName] = useState('');
  const [theme, setTheme] = useState('');
  const [venue, setVenue] = useState('');

  const themes = ['Wedding', 'Birthday', 'Corporate', 'Party', 'Other'];
  const venues = ['Grand Hall', 'City Center', 'Beach Resort', 'Private Garden'];

  const handleDateTimeChange = (event) => {
    setDateTime(event.target.value);
  };

  const handleEventNameChange = (event) => {
    setEventName(event.target.value);
  };

  const handleThemeChange = (event) => {
    setTheme(event.target.value);
  };

  const handleVenueChange = (event) => {
    setVenue(event.target.value);
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    // In a real application, you would handle the form submission here
    console.log('Event Details:', { dateTime, eventName, theme, venue });
    alert('Event details submitted!');
  };

  return (
    <div className={styles.bookEventContainer}>
      <h2>Book Your Event</h2>
      <form onSubmit={handleSubmit}>
        <div className={styles.formGroup}>
          <label htmlFor="dateTime">Date and Time:</label>
          <input
            type="datetime-local"
            id="dateTime"
            value={dateTime}
            onChange={handleDateTimeChange}
            required
          />
        </div>
        <div className={styles.formGroup}>
          <label htmlFor="eventName">Event Name:</label>
          <input
            type="text"
            id="eventName"
            value={eventName}
            onChange={handleEventNameChange}
            required
          />
        </div>
        <div className={styles.formGroup}>
          <label htmlFor="theme">Theme:</label>
          <select id="theme" value={theme} onChange={handleThemeChange}>
            <option value="">Select a theme</option>
            {themes.map((option) => (
              <option key={option} value={option}>
                {option}
              </option>
            ))}
          </select>
        </div>
        <div className={styles.formGroup}>
          <label htmlFor="venue">Venue:</label>
          <select id="venue" value={venue} onChange={handleVenueChange}>
            <option value="">Select a venue</option>
            {venues.map((option) => (
              <option key={option} value={option}>
                {option}
              </option>
            ))}
          </select>
        </div>
        <div className={styles.buttonGroup}>
          <button type="button" onClick={onBack}>
            Back
          </button>
          <button type="button" onClick={onBookVendor}>
            Book Vendor
          </button>
          <button type="button" onClick={onBookDesigner}>
            Book Designer
          </button>
          <button type="submit">Submit Booking</button> {/* Added a submit button */}
        </div>
      </form>
    </div>
  );
}

export default BookEvent;