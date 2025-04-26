import React, { useState } from 'react';
 import { BrowserRouter as Router, Routes, Route } from 'react-router-dom'; // Removed unused Link and useNavigate
 import Sidebar from './components/OrganizerHomePage/Sidebar';
 import HomePage from './components/OrganizerHomePage/HomePage';
 import styles from './App.module.css';
 import EventHomePage from './components/HOMEPAGE/EventHomePage';
 import AdminLoginForm from './components/ADMINLOGIN/EventLoginForm';
 import SignUpForm from './components/SIGNUP/SignUpForm'; // User sign-up
 import ASignUpForm from './components/ADMINSIGUP/ASignUpForm'; // Admin sign-up (Corrected import)
 import EditUserForm from './components/MANAGEUSER/EditUserForm'; // Import EditUserForm
 import AdminHomePage from './components/ADMINPAGE/AdminHomePage'; // Import AdminHomePage

 function App() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [isAdminLoggedIn, setIsAdminLoggedIn] = useState(false);
  const [showBookEventPage, setShowBookEventPage] = useState(false);
  const [showBookVendorPage, setShowBookVendorPage] = useState(false);
  const [showBookDesignerPage, setShowBookDesignerPage] = useState(false);
  const [showEditUserForm, setShowEditUserForm] = useState(false); // State for Edit User Form

  const handleUserLogin = () => {
    setIsLoggedIn(true);
  };

  const handleAdminLogin = () => {
    setIsAdminLoggedIn(true);
  };

  const handleLogout = () => {
    setIsLoggedIn(false);
    setIsAdminLoggedIn(false);
    setShowBookEventPage(false);
    setShowBookVendorPage(false);
    setShowBookDesignerPage(false);
    setShowEditUserForm(false); // Reset Edit User Form visibility
  };

  const handleBookEventClick = () => {
    setShowBookEventPage(true);
    setShowBookVendorPage(false);
    setShowBookDesignerPage(false);
    setShowEditUserForm(false);
  };

  const handleBackToOrganizerHome = () => {
    setShowBookEventPage(false);
    setShowBookVendorPage(false);
    setShowBookDesignerPage(false);
    setShowEditUserForm(false);
  };

  const handleBookVendorClick = () => {
    setShowBookVendorPage(true);
    setShowBookEventPage(false);
    setShowBookDesignerPage(false);
    setShowEditUserForm(false);
  };

  const handleBookDesignerClick = () => {
    setShowBookDesignerPage(true);
    setShowBookEventPage(false);
    setShowBookVendorPage(false);
    setShowEditUserForm(false);
  };

  const handleUserClick = () => {
    setShowBookEventPage(false);
    setShowBookVendorPage(false);
    setShowBookDesignerPage(false);
    setShowEditUserForm(true);
  };

  const handleBackFromEditUser = () => {
    setShowEditUserForm(false);
  };

  return (
    <Router>
      <div className={styles.appContainer}>
        <Routes>
          <Route path="/" element={<EventHomePage onUserLogin={handleUserLogin} onAdminLogin={handleAdminLogin} />} />
          <Route path="/login" element={<EventHomePage />} /> {/* You might want a dedicated login route */}
          <Route path="/admin/login" element={<AdminLoginForm onLogin={handleAdminLogin} onBack={handleLogout} />} />
          <Route path="/signup" element={<SignUpForm />} /> {/* User sign-up route */}
          <Route path="/admin/signup" element={<ASignUpForm />} /> {/* Admin sign-up route (Updated component name) */}
          {isLoggedIn && (
            <>
              <Route
                path="/organizer"
                element={
                  <>
                    <Sidebar
                      onBookEvent={handleBookEventClick}
                      onBookVendor={handleBookVendorClick}
                      onBookDesigner={handleBookDesignerClick}
                      onUserClick={handleUserClick}
                    />
                    <HomePage
                      showBookEvent={showBookEventPage}
                      showBookVendor={showBookVendorPage}
                      showBookDesigner={showBookDesignerPage}
                      onBack={handleBackToOrganizerHome}
                      showEditUserForm={showEditUserForm}
                      onEditUserBack={handleBackFromEditUser}
                    />
                  </>
                }
              />
              {/* Optional dedicated route for EditUserForm */}
              {/* <Route
                path="/organizer/user/edit"
                element={<EditUserForm onBack={handleBackFromEditUser} />}
              /> */}
            </>
          )}
          {/* Add the route for AdminHomePage */}
          {isAdminLoggedIn && (
            <Route path="/admin" element={<AdminHomePage />} />
          )}
        </Routes>
      </div>
    </Router>
  );
 }

 export default App;