"use client";
import React, { useState } from "react";
import styles from "./EventLoginForm.module.css";
import { useNavigate } from "react-router-dom";

function EventLoginForm({ onBack, onLogin }) {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [showPassword, setShowPassword] = useState(false);
  const navigate = useNavigate();

  // Toggle password visibility
  const togglePasswordVisibility = () => {
    setShowPassword(!showPassword);
  };

  // Handle login form submission
  const handleSubmit = async (e) => {
    e.preventDefault(); // Prevent the default form submit action

    try {
      // Make the POST request to the backend
      const response = await fetch("http://localhost:8080/api/admin/login", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          username: username,
          password: password,
        }),
      });

      // Check the response status
      if (response.ok) {
        let data = null;
        try {
          data = await response.json(); // Try to parse the response as JSON
        } catch (err) {
          console.log("No JSON body, maybe just success message.");
        }

        console.log("Login successful:", data);

        // Execute onLogin callback (only if login was successful)
        onLogin();

        // Redirect to /organizer after successful login
        navigate("/admin");
      } else {
        // Handle failed login
        const errorData = await response.json();
        console.error("Login failed:", errorData.message);
        alert("Login failed: " + (errorData.message || "Invalid credentials"));
      }
    } catch (error) {
      console.error("Error during login:", error);
      alert("An error occurred. Please try again.");
    }
  };

  // Navigate to sign up page
  const handleSignUpClick = () => {
    navigate("/admin/signup"); // Navigate to the admin sign-up route
  };

  return (
    <main className={styles.eventLoginForm}>
      <div className={styles.loginFormBorder}>
        <div className={styles.loginFormInnerBorder}>
          <h2 className={styles.welcomeback}>ADMIN LOGIN</h2>
          <div className={styles.underline} />
          <h1 className={styles.loginHeading}>LOGIN</h1>

          <div className={styles.inputContainer}>
            <img
              src="https://cdn.builder.io/api/v1/image/assets/TEMP/bc3842aae2e895c5428531f74e0003bc729e3acd?placeholderIfAbsent=true&apiKey=ba33c041a9dc4373926b68b1f965eca1"
              className={styles.inputIcon}
              alt="Username icon"
            />
            <input
              type="text"
              placeholder="Enter Username"
              className={styles.inputField}
              value={username}
              onChange={(e) => setUsername(e.target.value)}
            />
          </div>

          <div className={styles.passwordContainer}>
            <img
              src="https://cdn.builder.io/api/v1/image/assets/TEMP/f0d8d07a0a987d3058c4d8352ff6471b3ce31431?placeholderIfAbsent=true&apiKey=ba33c041a9dc4373926b68b1f965eca1"
              className={styles.passwordIcon}
              alt="Password icon"
            />
            <input
              type={showPassword ? "text" : "password"}
              placeholder="Enter Password"
              className={styles.passwordField}
              value={password}
              onChange={(e) => setPassword(e.target.value)}
            />
            <img
              src={
                showPassword
                  ? "https://cdn.builder.io/api/v1/image/assets/TEMP/eye-open.svg?placeholderIfAbsent=true&apiKey=ba33c041a9dc4373926b68b1f965eca1"
                  : "https://cdn.builder.io/api/v1/image/assets/TEMP/eye-closed.svg?placeholderIfAbsent=true&apiKey=ba33c041a9dc4373926b68b1f965eca1"
              }
              className={styles.eyeIcon}
              alt={showPassword ? "Hide password" : "Show password"}
              onClick={togglePasswordVisibility}
            />
          </div>

          <a href="#" className={styles.forgotyourpassword}>
            Forgot your password?
          </a>

          <div className={styles.buttonContainer}>
            {/* Use onSubmit instead of onClick for form submission */}
            <form onSubmit={handleSubmit}>
              <button type="submit" className={styles.actionButton}>
                Login
              </button>
            </form>
            <button
              className={styles.actionButton}
              onClick={handleSignUpClick}
            >
              Admin SignUp
            </button>
          </div>

          <button className={styles.homeButton} onClick={onBack}>
            Home
          </button>
        </div>
      </div>
    </main>
  );
}

export default EventLoginForm;
