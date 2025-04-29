import React, { useState, useEffect } from 'react';
import styles from './BookEvent.module.css';

function BookEvent({ onBack, onBookVendor, onBookDesigner }) {
  const [dateTime, setDateTime] = useState('');
  const [endttime, setEndTime] = useState('');
  const [name, setEventName] = useState('');
  const [username, setUserName] = useState('');
  const [theme, setTheme] = useState('');
  const [venue, setVenue] = useState('');
  const [venues, setVenues] = useState([]);
  const [loading, setLoading] = useState(true);
  const themes = ['Wedding', 'Birthday', 'Corporate', 'Party', 'Other'];

  useEffect(() => {
    // Corrected URL: http (not https)
    fetch('http://localhost:8080/api/organizer/addVenues')
      .then((response) => {
        if (!response.ok) {
          throw new Error(`HTTP error! Status: ${response.status}`);
        }
        return response.json();
      })
      .then((data) => {
        setVenues(data);
        setLoading(false);
      })
      .catch((error) => {
        console.error('Error fetching venues:', error);
        setLoading(false);
      });
  }, []);

  const handleSubmit = (event) => {
    event.preventDefault();

    // Find the selected venue object based on its name
    const selectedVenue = venues.find(v => v.name === venue);

    const eventData = {
      name,
      dateTime,
      endttime,
      theme,
      venue: selectedVenue.name,
      price: selectedVenue.totalprice,
      username
    };

    // Corrected URL: http (not https)
    fetch("http://localhost:8080/api/organizer/bookEvent", {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(eventData),
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error(`HTTP error! Status: ${response.status}`);
        }
        return response.json();
      })
      .then((data) => {
        console.log('Event booked:', data);
        alert('Event booked successfully!');
      })
      .catch((error) => {
        console.error('Error booking event:', error);
        alert('Failed to book the event. Please try again later.');
      });
  };

  return (
    <div className={styles.bookEventContainer}>
      <h2>Book Your Event</h2>
      <form onSubmit={handleSubmit}>
        <div className={styles.formGroup}>
          <label htmlFor="dateTime">Date and Start Time:</label>
          <input
            type="datetime-local"
            id="dateTime"
            value={dateTime}
            onChange={(e) => setDateTime(e.target.value)}
            required
          />
        </div>
        <div className={styles.formGroup}>
          <label htmlFor="endTime">End Time:</label>
          <input
            type="time"
            id="endTime"
            value={endttime}
            onChange={(e) => setEndTime(e.target.value)}
            required
          />
        </div>
        <div className={styles.formGroup}>
                  <label htmlFor="UserName">Username:</label>
                  <input
                    type="text"
                    id="UserName"
                    value={username}
                    onChange={(e) => setUserName(e.target.value)}
                    required
                  />
                </div>
        <div className={styles.formGroup}>
          <label htmlFor="eventName">Event Name:</label>
          <input
            type="text"
            id="eventName"
            value={name}
            onChange={(e) => setEventName(e.target.value)}
            required
          />
        </div>
        <div className={styles.formGroup}>
          <label htmlFor="theme">Theme:</label>
          <select
            id="theme"
            value={theme}
            onChange={(e) => setTheme(e.target.value)}
            required
          >
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
          {loading ? (
            <p>Loading venues...</p>
          ) : (
            <select
              id="venue"
              value={venue}
              onChange={(e) => setVenue(e.target.value)}
              required
            >
              <option value="">Select a venue</option>
              {venues.map((option) => (
                <option key={option.venueID} value={option.name}>
                  {option.name}
                </option>
              ))}
            </select>
          )}
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
          <button type="submit">Submit Booking</button>
        </div>
      </form>
    </div>
  );
}

export default BookEvent;
