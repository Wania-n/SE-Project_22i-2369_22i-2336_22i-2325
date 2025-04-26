// ADDVENUE/VenueForm.js
import React, { useState, useEffect } from 'react';
import styles from './VenueForm.module.css'; // Create this CSS file

function VenueForm({ onClose }) {
 const [name, setName] = useState('');
 const [capacity, setCapacity] = useState('');
 const [address, setAddress] = useState('');
 const [price, setPrice] = useState('');
 const [showVenueList, setShowVenueList] = useState(false);
 const [venues, setVenues] = useState([]);

 useEffect(() => {
   const storedVenues = localStorage.getItem('venues');
   if (storedVenues) {
     setVenues(JSON.parse(storedVenues));
   } else {
     // Add dummy venue data
     setVenues([
       { name: 'Grand Ballroom', capacity: '500', address: 'Downtown, City Center', price: '10000' },
       { name: 'Garden Terrace', capacity: '200', address: 'Suburban Area, Green Lane', price: '6000' },
     ]);
   }
 }, []);

 useEffect(() => {
   if (venues.length > 0) {
     localStorage.setItem('venues', JSON.stringify(venues));
   }
 }, [venues]);

 const handleAddVenue = () => {
   if (!name || !capacity || !address || !price) {
     alert('Please fill in all venue details.');
     return;
   }

   const newVenue = {
     name,
     capacity,
     address,
     price,
   };

   setVenues([...venues, newVenue]);

   setName('');
   setCapacity('');
   setAddress('');
   setPrice('');

   onClose();
 };

 const handleDeleteVenue = (index) => {
   const updatedVenues = [...venues];
   updatedVenues.splice(index, 1);
   setVenues(updatedVenues);
 };

 const toggleVenueList = () => {
   setShowVenueList(!showVenueList);
 };

 return (
   <div className={styles.venueForm}>
     <h2>Add/Delete Venue</h2>
     {!showVenueList && (
       <>
         <label>Name:</label>
         <input type="text" value={name} onChange={(e) => setName(e.target.value)} />

         <label>Capacity:</label>
         <input type="number" value={capacity} onChange={(e) => setCapacity(e.target.value)} />

         <label>Address:</label>
         <input type="text" value={address} onChange={(e) => setAddress(e.target.value)} />

         <label>Price:</label>
         <input type="number" value={price} onChange={(e) => setPrice(e.target.value)} />

         <button onClick={handleAddVenue}>Add Venue</button>
       </>
     )}
     <button onClick={toggleVenueList}>
       {showVenueList ? 'Hide Venues' : 'Delete Venue'}
     </button>
     {showVenueList && (
       <div className={styles.venueList}>
         <h3>Existing Venues</h3>
         {venues.length === 0 ? (
           <p>No venues added yet.</p>
         ) : (
           <ul>
             {venues.map((venue, index) => (
               <li key={index}>
                 {venue.name} - Capacity: {venue.capacity}
                 <button onClick={() => handleDeleteVenue(index)}>Delete</button>
               </li>
             ))}
           </ul>
         )}
       </div>
     )}
     <button onClick={onClose}>Close</button>
   </div>
 );
}

export default VenueForm;