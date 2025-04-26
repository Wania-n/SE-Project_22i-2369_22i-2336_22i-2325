// components/MANAGEUSER/EditUserForm.js
import React, { useState } from 'react';
import styles from './EditUserForm.module.css';
import { useNavigate } from 'react-router-dom'; // If you're using React Router
import lockIcon from '../../assets/lock-icon.png'; // Assuming you have icons
import personIcon from '../../assets/person-icon.png';
import emailIcon from '../../assets/email-icon.png';
import phoneIcon from '../../assets/phone-icon.png';
import homeIcon from '../../assets/home-icon.png';
import calendarIcon from '../../assets/calendar-icon.png';

function EditUserForm({ onBack }) {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [firstName, setFirstName] = useState('');
  const [lastName, setLastName] = useState('');
  const [email, setEmail] = useState('');
  const [address, setAddress] = useState('');
  const [phoneNo, setPhoneNo] = useState('');
  const [dob, setDob] = useState('');

  const navigate = useNavigate(); // Initialize useNavigate

  const handleEditSubmit = (event) => {
    event.preventDefault();
    // Here you would typically send the updated user data to your backend
    console.log('Updated User Data:', {
      username,
      password,
      firstName,
      lastName,
      email,
      address,
      phoneNo,
      dob,
    });
    // After successful edit, you might want to navigate back
    if (onBack) {
      onBack();
    } else {
      navigate('/organizer'); // Navigate back to OrganizerHomePage
    }
  };

  const handleBackClick = () => {
    if (onBack) {
      onBack();
    } else {
      navigate('/organizer'); // Navigate back to OrganizerHomePage
    }
  };

  return (
    <div className={styles.editUserFormContainer}>
      <h2>Edit Account</h2>
      <div className={styles.divider} />
      <h2 className={styles.formSubtitle}>Edit Profile</h2>
      <form onSubmit={handleEditSubmit} className={styles.editUserForm}>
        <div className={styles.inputGrid}>
          <div className={styles.inputWrapper}>
            <input
              type="text"
              id="username"
              placeholder="username"
              value={username}
              onChange={(e) => setUsername(e.target.value)}
              className={styles.formInput}
            />
          </div>
          <div className={styles.inputWrapper}>
            <input
              type="email"
              id="email"
              placeholder="email@"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              className={styles.formInput}
            />
          </div>
          <div className={styles.inputWrapper}>
            <input
              type="password"
              id="password"
              placeholder="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              className={styles.formInput}
            />
          </div>
          <div className={styles.inputWrapper}>
            <input
              type="tel"
              id="phoneNo"
              placeholder="phoneNo."
              value={phoneNo}
              onChange={(e) => setPhoneNo(e.target.value)}
              className={styles.formInput}
            />
          </div>
          <div className={styles.inputWrapper}>
            <input
              type="text"
              id="firstName"
              placeholder="firstname"
              value={firstName}
              onChange={(e) => setFirstName(e.target.value)}
              className={styles.formInput}
            />
          </div>
          <div className={styles.inputWrapper}>
            <input
              type="text"
              id="address"
              placeholder="address"
              value={address}
              onChange={(e) => setAddress(e.target.value)}
              className={styles.formInput}
            />
          </div>
          <div className={styles.inputWrapper}>
            <input
              type="text"
              id="lastName"
              placeholder="lastname"
              value={lastName}
              onChange={(e) => setLastName(e.target.value)}
              className={styles.formInput}
            />
          </div>
          <div className={styles.inputWrapper}>
            <input
              type="date"
              id="dob"
              placeholder="mm/dd/yyyy"
              value={dob}
              onChange={(e) => setDob(e.target.value)}
              className={`${styles.formInput} ${styles.dateInput}`}
            />
          </div>
        </div>
        <div className={styles.buttonContainer}>
          <button type="button" className={styles.backButton} onClick={handleBackClick}>
            Back
          </button>
          <button type="submit" className={styles.createButton}>
            Edit
          </button>
        </div>
      </form>
    </div>
  );
}

export default EditUserForm;