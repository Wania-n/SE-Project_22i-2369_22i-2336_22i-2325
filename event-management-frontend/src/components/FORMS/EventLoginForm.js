"use client";
import * as React from "react";
import { useState } from "react";
import styles from "./EventLoginForm.module.css";
import { useNavigate } from 'react-router-dom'; // Import useNavigate

function EventLoginForm({ onBack, onLogin }) {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [showPassword, setShowPassword] = useState(false);
  const navigate = useNavigate(); // Initialize useNavigate

  const togglePasswordVisibility = () => {
    setShowPassword(!showPassword);
  };

  // code changed for connecting to backend
  const handleSubmit = async () => {
    try {
      const response = await fetch('http://localhost:8080/api/organizer/login', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          username: username,
          password: password
        })
      });

      if (response.ok) {
        let data = null;
        try {
            data = await response.json(); // Try to parse JSON
        } catch (err) {
            console.log('No JSON body, maybe just success message.');
        }
        console.log('Login successful:', data);

        onLogin(); // Only call onLogin if login was successful
        navigate('/organizer'); // Navigate after successful login
      } else {
        const errorData = await response.json();
        console.error('Login failed:', errorData.message);
        alert('Login failed: ' + (errorData.message || 'Invalid credentials'));
      }
    } catch (error) {
      console.error('Error during login:', error);
      alert('An error occurred. Please try again.');
    }
  };


  const handleSignUpClick = () => {
    navigate('/signup'); // Navigate to the /signup route
  };

  return React.createElement(
    "main",
    { className: styles.eventLoginForm },
    React.createElement(
      "div",
      { className: styles.loginFormBorder },
      React.createElement(
        "div",
        { className: styles.loginFormInnerBorder },
        React.createElement("h2", { className: styles.welcomeback }, "WELCOME BACK"),
        React.createElement("div", { className: styles.underline }),
        React.createElement("h1", { className: styles.loginHeading }, "LOGIN"),

        React.createElement(
          "div",
          { className: styles.inputContainer },
          React.createElement("img", {
            src: "https://cdn.builder.io/api/v1/image/assets/TEMP/bc3842aae2e895c5428531f74e0003bc729e3acd?placeholderIfAbsent=true&apiKey=ba33c041a9dc4373926b68b1f965eca1",
            className: styles.inputIcon,
            alt: "Username icon",
          }),
          React.createElement("input", {
            type: "text",
            placeholder: "Enter Username",
            className: styles.inputField,
            value: username,
            onChange: (e) => setUsername(e.target.value),
          })
        ),

        React.createElement(
          "div",
          { className: styles.passwordContainer },
          React.createElement("img", {
            src: "https://cdn.builder.io/api/v1/image/assets/TEMP/f0d8d07a0a987d3058c4d8352ff6471b3ce31431?placeholderIfAbsent=true&apiKey=ba33c041a9dc4373926b68b1f965eca1",
            className: styles.passwordIcon,
            alt: "Password icon",
          }),
          React.createElement("input", {
            type: showPassword ? "text" : "password",
            placeholder: "Enter Password",
            className: styles.passwordField,
            value: password,
            onChange: (e) => setPassword(e.target.value),
          }),
          React.createElement("img", {
            src: showPassword
              ? "https://cdn.builder.io/api/v1/image/assets/TEMP/eye-open.svg?placeholderIfAbsent=true&apiKey=ba33c041a9dc4373926b68b1f965eca1"
              : "https://cdn.builder.io/api/v1/image/assets/TEMP/eye-closed.svg?placeholderIfAbsent=true&apiKey=ba33c041a9dc4373926b68b1f965eca1",
            className: styles.eyeIcon,
            alt: showPassword ? "Hide password" : "Show password",
            onClick: togglePasswordVisibility,
          })
        ),

        React.createElement(
          "a",
          { href: "#", className: styles.forgotyourpassword },
          "Forgot your password?"
        ),

        React.createElement(
          "div",
          { className: styles.buttonContainer },
          React.createElement(
            "button",
            { className: styles.actionButton, onClick: handleSubmit },
            "Login"
          ),
          React.createElement(
            "button",
            { className: styles.actionButton, onClick: handleSignUpClick }, // Added onClick handler
            "SignUp"
          )
        ),

        React.createElement(
          "button",
          { className: styles.homeButton, onClick: onBack },
          "Home"
        )
      )
    )
  );
}

export default EventLoginForm;