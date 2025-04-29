import React, { useState, useEffect } from 'react';
import styles from './BookVendor.module.css';

function BookVendor({ onBack }) {
  const [selectedEvent, setSelectedEvent] = useState('');
  const [selectedVendor, setSelectedVendor] = useState('');
  const [availableEvents, setAvailableEvents] = useState([]);
  const [availableVendors, setAvailableVendors] = useState([]);
  const [loadingEvents, setLoadingEvents] = useState(true);
  const [loadingVendors, setLoadingVendors] = useState(true);

  useEffect(() => {
    // Fetch available events
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

    // Fetch available vendors
    fetch('http://localhost:8080/api/organizer/getVendors')
      .then((response) => response.json())
      .then((data) => {
        setAvailableVendors(data);
        setLoadingVendors(false);
      })
      .catch((error) => {
        console.error('Error fetching vendors:', error);
        setLoadingVendors(false);
      });
  }, []);

  const handleEventChange = (event) => {
    setSelectedEvent(event.target.value);
  };

  const handleVendorChange = (event) => {
    setSelectedVendor(event.target.value);
  };

  const handleProceed = () => {
    if (selectedEvent && selectedVendor) {
      console.log('Booking Vendor:', { event: selectedEvent, vendor: selectedVendor });

      // Sending the booking data to the backend
      fetch('http://localhost:8080/api/organizer/bookVendor', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          event: selectedEvent,
          vendor: selectedVendor,
        }),
      })
        .then((response) => response.json())
        .then((data) => {
          // Handle the response from the backend (optional)
          if (data.success) {
            alert(`Vendor "${selectedVendor}" successfully booked for "${selectedEvent}"!`);
          }
        })
        .catch((error) => {
          console.error('Error booking vendor:', error);
        });
    } else {
      alert('Please select both an event and a vendor.');
    }
  };

  return (
    <div className={styles.bookVendorContainer}>
      <h2>Book a Vendor</h2>

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
        <label htmlFor="vendor">Select Vendor:</label>
        {loadingVendors ? (
          <p>Loading vendors...</p>
        ) : (
          <select id="vendor" value={selectedVendor} onChange={handleVendorChange}>
            <option value="">Select a vendor</option>
            {availableVendors.map((vendor) => (
              <option key={vendor.serviceID} value={vendor.serviceName}>
                {vendor.serviceName}
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

export default BookVendor;
