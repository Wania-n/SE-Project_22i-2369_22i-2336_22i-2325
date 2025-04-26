// ADDVENDOR/VendorForm.js
import React, { useState, useEffect } from 'react';
import styles from './VendorForm.module.css';

function VendorForm({ onClose }) {
 const [name, setName] = useState('');
 const [phoneNumber, setPhoneNumber] = useState('');
 const [email, setEmail] = useState('');
 const [address, setAddress] = useState('');
 const [price, setPrice] = useState('');
 const [serviceType, setServiceType] = useState('');
 const [showVendorList, setShowVendorList] = useState(false);
 const [vendors, setVendors] = useState([]);

 useEffect(() => {
   const storedVendors = localStorage.getItem('vendors');
   if (storedVendors) {
     setVendors(JSON.parse(storedVendors));
   } else {
     // Add dummy data if no vendors exist
     setVendors([
       { name: 'Floral Dreams', phoneNumber: '0300-1234567', email: 'floral@example.com', address: 'Street 1, Islamabad', price: '5000', serviceType: 'Decor' },
       { name: 'Catering Delights', phoneNumber: '0311-9876543', email: 'catering@example.com', address: 'Avenue 2, Rawalpindi', price: '1500', serviceType: 'Food' },
     ]);
   }
 }, []);

 useEffect(() => {
   if (vendors.length > 0) {
     localStorage.setItem('vendors', JSON.stringify(vendors));
   }
 }, [vendors]);

 const handleAddVendor = () => {
   if (!name || !phoneNumber || !email || !address || !price || !serviceType) {
     alert('Please fill in all fields.');
     return;
   }

   const newVendor = {
     name,
     phoneNumber,
     email,
     address,
     price,
     serviceType,
   };

   setVendors([...vendors, newVendor]);

   setName('');
   setPhoneNumber('');
   setEmail('');
   setAddress('');
   setPrice('');
   setServiceType('');

   onClose();
 };

 const handleDeleteVendor = (index) => {
   const updatedVendors = [...vendors];
   updatedVendors.splice(index, 1);
   setVendors(updatedVendors);
 };

 const toggleVendorList = () => {
   setShowVendorList(!showVendorList);
 };

 return (
   <div className={styles.vendorForm}>
     <h2>Add/Delete Vendor</h2>
     {!showVendorList && (
       <>
         <label>Name:</label>
         <input type="text" value={name} onChange={(e) => setName(e.target.value)} />

         <label>Phone Number:</label>
         <input type="text" value={phoneNumber} onChange={(e) => setPhoneNumber(e.target.value)} />

         <label>Email:</label>
         <input type="email" value={email} onChange={(e) => setEmail(e.target.value)} />

         <label>Address:</label>
         <input type="text" value={address} onChange={(e) => setAddress(e.target.value)} />

         <label>Price:</label>
         <input type="number" value={price} onChange={(e) => setPrice(e.target.value)} />

         <label>Service Type:</label>
         <input type="text" value={serviceType} onChange={(e) => setServiceType(e.target.value)} />

         <button onClick={handleAddVendor}>Add Vendor</button>
       </>
     )}
     <button onClick={toggleVendorList}>
       {showVendorList ? 'Hide Vendors' : 'Delete Vendor'}
     </button>
     {showVendorList && (
       <div className={styles.vendorList}>
         <h3>Existing Vendors</h3>
         {vendors.length === 0 ? (
           <p>No vendors added yet.</p>
         ) : (
           <ul>
             {vendors.map((vendor, index) => (
               <li key={index}>
                 {vendor.name} - {vendor.serviceType}
                 <button onClick={() => handleDeleteVendor(index)}>Delete</button>
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

export default VendorForm;