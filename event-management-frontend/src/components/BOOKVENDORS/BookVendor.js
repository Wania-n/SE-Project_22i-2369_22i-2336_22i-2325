import React, { useState } from 'react';
import styles from './BookVendor.module.css';

function BookVendor({ onBack }) {
  const [selectedEvent, setSelectedEvent] = useState('');
  const [selectedVendor, setSelectedVendor] = useState('');

  // Dummy data for events and vendors - replace with your actual data
  const availableEvents = ['Wedding Ceremony', 'Birthday Party', 'Corporate Gala', 'Product Launch'];
  const availableVendors = ['Catering Services Inc.', 'Floral Designs Ltd.', 'Sound & Light Pro', 'Photography Studio X'];

  const handleEventChange = (event) => {
    setSelectedEvent(event.target.value);
  };

  const handleVendorChange = (event) => {
    setSelectedVendor(event.target.value);
  };

  const handleProceed = () => {
    if (selectedEvent && selectedVendor) {
      console.log('Booking Vendor:', { event: selectedEvent, vendor: selectedVendor });
      alert(`Vendor "${selectedVendor}" booked for "${selectedEvent}"!`);
      // In a real application, you would handle the booking logic here
    } else {
      alert('Please select both an event and a vendor.');
    }
  };

  return (
    <div className={styles.bookVendorContainer}>
      <h2>Book a Vendor</h2>
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
        <label htmlFor="vendor">Select Vendor:</label>
        <select id="vendor" value={selectedVendor} onChange={handleVendorChange}>
          <option value="">Select a vendor</option>
          {availableVendors.map((vendor) => (
            <option key={vendor} value={vendor}>
              {vendor}
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

export default BookVendor;