"use client";
import * as React from "react";
import { useState } from "react";
import styles from "./EventHomePage.module.css";
import EventLoginForm from "../FORMS/EventLoginForm"; // User Login
import AdminLoginForm from "../ADMINLOGIN/EventLoginForm"; // Admin Login
import SignUpForm from "../SIGNUP/SignUpForm"; // User Signup - IMPORTED THIS
import { useNavigate } from 'react-router-dom'; // IMPORT useNavigate

function Header({ onLoginButtonClick }) {
  const navigate = useNavigate(); // Initialize useNavigate within Header

  const handleLoginClick = () => {
    onLoginButtonClick(); // Call the prop to set the state in EventHomePage
  };

  const handleSignupClick = () => {
    navigate('/signup'); // Navigate to the /signup route
  };

  return React.createElement(
    "header",
    { className: styles.header },
    React.createElement("h1", { className: styles.plantIt }, "Plan- IT"),
    React.createElement(
      "nav",
      { className: styles.div },
      React.createElement("button", { className: styles.buttonBackground }, "About Us"),
      React.createElement("button", { className: styles.buttonBackground2, onClick: handleLoginClick }, "Login"),
      React.createElement(
        "div",
        { className: styles.div2 },
        React.createElement("button", { className: styles.buttonBackground3, onClick: handleSignupClick }, "SignUp"),
        React.createElement("img", {
          src: "https://cdn.builder.io/api/v1/image/assets/TEMP/6f8d3095c03fb3beef1852ee45bfd67166792abf?placeholderIfAbsent=true&apiKey=ba33c041a9dc4373926b68b1f965eca1",
          alt: "Profile icon",
          className: styles.img,
        })
      )
    )
  );
}

function ActionPanel({ onUserClick, onAdminClick }) { // Added onAdminClick
  return React.createElement(
    "section",
    { className: styles.div5 },
    React.createElement("h2", null, "Plan your event today!"),
    React.createElement("img", {
      src: "https://cdn.builder.io/api/v1/image/assets/TEMP/366fa60e28aaa894b32c929b076407c486f21d38?placeholderIfAbsent=true&apiKey=ba33c041a9dc4373926b68b1f965eca1",
      alt: "Event planning illustration",
      className: styles.img3,
    }),
    React.createElement(
      "button",
      { className: styles.buttonBackground4, onClick: onUserClick },
      "User"
    ),
    React.createElement(
      "button",
      { className: styles.buttonBackground5, onClick: onAdminClick }, // Added onClick for Admin
      "Admin"
    ),
    React.createElement("p", { className: styles.version }, "1.0.0 version")
  );
}

function MainContent({ onUserClick, onAdminClick }) {  // Added onAdminClick
  return React.createElement(
    "main",
    { className: styles.div3 },
    React.createElement(
      "div",
      { className: styles.div4 },
      React.createElement(
        "section",
        { className: styles.column },
        React.createElement("img", {
          src: "https://cdn.builder.io/api/v1/image/assets/TEMP/8494ddc42e80d79d2620c79c71f1776148dcb85c?placeholderIfAbsent=true&apiKey=ba33c041a9dc4373926b68b1f965eca1",
          alt: "Event planning showcase",
          className: styles.img2,
        })
      ),
      React.createElement(
        "aside",
        { className: styles.column2 },
        React.createElement(ActionPanel, { onUserClick: onUserClick, onAdminClick: onAdminClick }) // Pass onAdminClick
      )
    )
  );
}

function EventHomePage({ onUserLogin, onAdminLogin }) { // Renamed onLogin to onUserLogin, added onAdminLogin
  const [showUserLoginForm, setShowUserLoginForm] = useState(false);
  const [showAdminLoginForm, setShowAdminLoginForm] = useState(false); // State for Admin Login Form
  // const navigate = useNavigate(); // Removed unused navigate here

  const handleUserButtonClick = () => {
    setShowUserLoginForm(true);
    setShowAdminLoginForm(false); // Ensure only one form is shown
  };

  const handleAdminButtonClick = () => {
    setShowAdminLoginForm(true);
    setShowUserLoginForm(false); // Ensure only one form is shown
  };

  const handleHeaderLoginClick = () => {
    setShowUserLoginForm(true); // Trigger the same state change as the "User" button
    setShowAdminLoginForm(false);
  };

  const handleBackToHomePage = () => {
    setShowUserLoginForm(false);
    setShowAdminLoginForm(false);
  };

  const handleUserLoginFormSubmit = () => {
    // In a real application, you would validate user credentials here.
    // For this example, we'll just simulate a successful login.
    onUserLogin(); // Call the onUserLogin function passed from App.js
  };

  const handleAdminLoginFormSubmit = () => {
    onAdminLogin();
  };

  return React.createElement(
    "div",
    { className: styles.eventHomePage },
    !showUserLoginForm && !showAdminLoginForm ? ( // Both forms are hidden
      React.createElement(React.Fragment, null,
        React.createElement(Header, { onLoginButtonClick: handleHeaderLoginClick }), // Pass the handler function
        React.createElement(MainContent, { onUserClick: handleUserButtonClick, onAdminClick: handleAdminButtonClick }) // Pass handleAdminButtonClick
      )
    ) : showUserLoginForm ? (
      React.createElement(EventLoginForm, {  // User Login Form
        onBack: handleBackToHomePage,
        onLogin: handleUserLoginFormSubmit,
      })
    ) : showAdminLoginForm ? (
      React.createElement(AdminLoginForm, { // Admin Login Form -  Use AdminLoginForm here
        onBack: handleBackToHomePage,
        onLogin: handleAdminLoginFormSubmit,
      })
    ) : null // Added a null case for completeness
  );
}

export default EventHomePage;