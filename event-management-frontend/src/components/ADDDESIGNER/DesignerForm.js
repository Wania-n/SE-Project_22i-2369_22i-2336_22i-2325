// ADDDESIGNER/DesignerForm.js
import React, { useState, useEffect } from 'react';
import styles from './DesignerForm.module.css'; // Create this CSS file

function DesignerForm({ onClose }) {
 const [name, setName] = useState('');
 const [phoneNo, setPhoneNo] = useState('');
 const [email, setEmail] = useState('');
 const [address, setAddress] = useState('');
 const [price, setPrice] = useState('');
 const [serviceType, setServiceType] = useState('');
 const [showDesignerList, setShowDesignerList] = useState(false);
 const [designers, setDesigners] = useState([]);

 useEffect(() => {
   const storedDesigners = localStorage.getItem('designers');
   if (storedDesigners) {
     setDesigners(JSON.parse(storedDesigners));
   } else {
     // Add dummy designer data
     setDesigners([
       { name: 'Elegant Designs', phoneNo: '0322-1122334', email: 'elegant@example.com', address: 'Studio A, Lahore', price: '8000', serviceType: 'Bridal Wear' },
       { name: 'Creative Styles', phoneNo: '0345-5566778', email: 'creative@example.com', address: 'Plaza B, Karachi', price: '4500', serviceType: 'Party Outfits' },
     ]);
   }
 }, []);

 useEffect(() => {
   if (designers.length > 0) {
     localStorage.setItem('designers', JSON.stringify(designers));
   }
 }, [designers]);

 const handleAddDesigner = () => {
   if (!name || !phoneNo || !email || !address || !price || !serviceType) {
     alert('Please fill in all designer details.');
     return;
   }

   const newDesigner = {
     name,
     phoneNo,
     email,
     address,
     price,
     serviceType,
   };

   setDesigners([...designers, newDesigner]);

   setName('');
   setPhoneNo('');
   setEmail('');
   setAddress('');
   setPrice('');
   setServiceType('');

   onClose();
 };

 const handleDeleteDesigner = (index) => {
   const updatedDesigners = [...designers];
   updatedDesigners.splice(index, 1);
   setDesigners(updatedDesigners);
 };

 const toggleDesignerList = () => {
   setShowDesignerList(!showDesignerList);
 };

 return (
   <div className={styles.designerForm}>
     <h2>Add/Delete Designer</h2>
     {!showDesignerList && (
       <>
         <label>Name:</label>
         <input type="text" value={name} onChange={(e) => setName(e.target.value)} />

         <label>Phone No:</label>
         <input type="text" value={phoneNo} onChange={(e) => setPhoneNo(e.target.value)} />

         <label>Email:</label>
         <input type="email" value={email} onChange={(e) => setEmail(e.target.value)} />

         <label>Address:</label>
         <input type="text" value={address} onChange={(e) => setAddress(e.target.value)} />

         <label>Price:</label>
         <input type="number" value={price} onChange={(e) => setPrice(e.target.value)} />

         <label>Service Type:</label>
         <input type="text" value={serviceType} onChange={(e) => setServiceType(e.target.value)} />

         <button onClick={handleAddDesigner}>Add Designer</button>
       </>
     )}
     <button onClick={toggleDesignerList}>
       {showDesignerList ? 'Hide Designers' : 'Delete Designer'}
     </button>
     {showDesignerList && (
       <div className={styles.designerList}>
         <h3>Existing Designers</h3>
         {designers.length === 0 ? (
           <p>No designers added yet.</p>
         ) : (
           <ul>
             {designers.map((designer, index) => (
               <li key={index}>
                 {designer.name} - {designer.serviceType}
                 <button onClick={() => handleDeleteDesigner(index)}>Delete</button>
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

export default DesignerForm;