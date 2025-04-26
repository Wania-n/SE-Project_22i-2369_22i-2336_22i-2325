"use client";
import React, { useState } from "react";
import styles from "./ASignUpForm.module.css"; // Assuming all styles are in ASignUpForm.module.css
import { useNavigate } from 'react-router-dom'; // Import useNavigate

// Input Field Component
const InputField = ({ type, placeholder }) => {
  return (
    <div className={styles.inputWrapper}>
      <input
        type={type}
        placeholder={placeholder}
        className={styles.formInput}
      />
    </div>
  );
};

// Form Header Component
const FormHeader = () => {
  return (
    <header>
      <h1 className={styles.formTitle}>Create Admin Account</h1>
      <div className={styles.divider} />
      <h2 className={styles.formSubtitle}>SignUp</h2>
    </header>
  );
};

// Input Grid Component
const InputGrid = () => {
  return (
    <div className={styles.inputGrid}>
      <InputField type="text" placeholder="username" />
      <InputField type="email" placeholder="email@" />
      <InputField type="password" placeholder="password" />
      <InputField type="tel" placeholder="phoneNo." />
      <InputField type="text" placeholder="firstname" />
      <InputField type="text" placeholder="address" />
      <InputField type="text" placeholder="lastname" />
      <InputField type="date" placeholder="DOB" /> {/* Changed to date type here as well */}
    </div>
  );
};

// Form Actions Component
const FormActions = () => {
  const navigate = useNavigate(); // Initialize useNavigate

  const handleBackClick = () => {
    // You can define what happens when the "Back" button is clicked
    navigate('/'); // Go back to the EventHomePage
  };

  const handleCreateClick = () => {
    // Add your logic to handle the creation of the admin account here
    console.log('Admin account created!');
    // After successful creation, navigate to the EventHomePage
    navigate('/');
  };

  return (
    <div className={styles.buttonContainer}>
      <button className={styles.backButton} onClick={handleBackClick}>Back</button>
      <button className={styles.createButton} onClick={handleCreateClick}>Create</button>
    </div>
  );
};

// Main Signup Component
function Signup() {
  const [dob, setDob] = useState("");

  const handleDateChange = (e) => {
    setDob(e.target.value);
  };

  return (
    <section className={styles.container}>
      <div className={styles.card}>
        <form className={styles.formContainer}>
          {/* Form Header */}
          <FormHeader />

          {/* Input Grid */}
          <div className={styles.inputGrid}>
            <InputField type="text" placeholder="username" />
            <InputField type="email" placeholder="email@" />
            <InputField type="password" placeholder="password" />
            <InputField type="tel" placeholder="phoneNo." />
            <InputField type="text" placeholder="firstname" />
            <InputField type="text" placeholder="address" />
            <InputField type="text" placeholder="lastname" />

            {/* Date of Birth Field with Calendar */}
            <div className={styles.inputWrapper}>
              <input
                type="date"
                placeholder="DOB"
                className={`${styles.formInput} ${styles.dateInput}`}
                value={dob}
                onChange={handleDateChange}
              />
            </div>
          </div>

          {/* Form Actions */}
          <FormActions />
        </form>
      </div>
    </section>
  );
}

export default Signup;