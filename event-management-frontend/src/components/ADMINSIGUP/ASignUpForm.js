"use client";
import React, { useState } from "react";
import styles from "./ASignUpForm.module.css";
import { useNavigate } from "react-router-dom";

// Input Field Component
const InputField = ({ type, placeholder, name, value, onChange }) => {
  return (
    <div className={styles.inputWrapper}>
      <input
        type={type}
        name={name}
        placeholder={placeholder}
        className={styles.formInput}
        value={value}
        onChange={onChange}
        required
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

// Main Signup Component
function Signup() {
  const navigate = useNavigate();

  // Form data state
  const [formData, setFormData] = useState({
    username: "",
    email: "",
    password: "",
    phoneNo: "",
    firstname: "",
    address: "",
    lastname: "",
    dob: ""
  });

  // Handle input change
  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prevState) => ({
      ...prevState,
      [name]: value
    }));
  };

  // Handle form submission
  const handleSubmit = async (e) => {
    e.preventDefault();

        console.log("Sending Data:", formData);

        try {
            const response = await fetch('http://localhost:8080/api/admin/signup', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(formData),
                mode: "cors",
            });

            console.log('Response Status:', response.status); // Check the actual status code
            const data = await response.json(); // Assuming backend sends JSON response
            console.log("Response from Server:", data);

            if (response.ok) {
                // If the response is successful
                alert(data.message || "Admin registered successfully!");
                navigate("/"); // Navigate to home on success
            } else {
                // If response status code is not in 2xx range, handle as failure
                alert(`Failed to register admin: ${data.message || 'Unknown error'}`);
            }
        } catch (error) {
            console.error('Error creating admin account:', error);
            alert('Failed to create admin account!');
        }
  };

  // Handle back button
  const handleBack = () => {
    navigate('/');
  };

  return (
    <section className={styles.container}>
      <div className={styles.card}>
        <form className={styles.formContainer} onSubmit={handleSubmit}>
          {/* Form Header */}
          <FormHeader />

          {/* Input Grid */}
          <div className={styles.inputGrid}>
            <InputField
              type="text"
              placeholder="Username"
              name="username"
              value={formData.username}
              onChange={handleChange}
            />
            <InputField
              type="email"
              placeholder="Email"
              name="email"
              value={formData.email}
              onChange={handleChange}
            />
            <InputField
              type="password"
              placeholder="Password"
              name="password"
              value={formData.password}
              onChange={handleChange}
            />
            <InputField
              type="tel"
              placeholder="Phone No."
              name="phone"
              value={formData.phone}
              onChange={handleChange}
            />
            <InputField
              type="text"
              placeholder="First Name"
              name="firstname"
              value={formData.firstname}
              onChange={handleChange}
            />
            <InputField
              type="text"
              placeholder="Address"
              name="address"
              value={formData.address}
              onChange={handleChange}
            />
            <InputField
              type="text"
              placeholder="Last Name"
              name="lastname"
              value={formData.lastname}
              onChange={handleChange}
            />

            {/* Date of Birth */}
            <div className={styles.inputWrapper}>
              <input
                type="date"
                name="dob"
                placeholder="DOB"
                className={`${styles.formInput} ${styles.dateInput}`}
                value={formData.dob}
                onChange={handleChange}
                required
              />
            </div>
          </div>

          {/* Form Actions */}
          <div className={styles.buttonContainer}>
            <button type="button" className={styles.backButton} onClick={handleBack}>
              Back
            </button>
            <button type="submit" className={styles.createButton}>
              Create
            </button>
          </div>
        </form>
      </div>
    </section>
  );
}

export default Signup;
