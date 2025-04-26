import React, { useState, useEffect } from 'react';
 import styles from './AdminHomePage.module.css';
 import { useNavigate } from 'react-router-dom';
 import ASidebar from './ASidebar';
 import VendorForm from '../ADDVENDOR/VendorForm';
 import VenueForm from '../ADDVENUE/VenueForm';
 import DesignerForm from '../ADDDESIGNER/DesignerForm';

 function AdminHomePage() {
  const navigate = useNavigate();
  const [showVendorForm, setShowVendorForm] = useState(false);
  const [showVenueForm, setShowVenueForm] = useState(false);
  const [showDesignerForm, setShowDesignerForm] = useState(false);
  const [vendorList, setVendorList] = useState([]);
  const [venueList, setVenueList] = useState([]);
  const [designerList, setDesignerList] = useState([]);

  useEffect(() => {
    const storedVendors = localStorage.getItem('vendors');
    if (storedVendors) {
      setVendorList(JSON.parse(storedVendors));
    }

    const storedVenues = localStorage.getItem('venues');
    if (storedVenues) {
      setVenueList(JSON.parse(storedVenues));
    }

    const storedDesigners = localStorage.getItem('designers');
    if (storedDesigners) {
      setDesignerList(JSON.parse(storedDesigners));
    }
  }, []);

  const handleLogout = () => {
    navigate('/');
  };

  const handleBookVendorClick = () => {
    setShowVendorForm(true);
  };

  const handleBookVenueClick = () => {
    setShowVenueForm(true);
  };

  const handleBookDesignerClick = () => {
    setShowDesignerForm(true);
  };

  const handleCloseVendorForm = () => {
    setShowVendorForm(false);
    // Optionally refresh the vendor list after closing the form
    const storedVendors = localStorage.getItem('vendors');
    if (storedVendors) {
      setVendorList(JSON.parse(storedVendors));
    }
  };

  const handleCloseVenueForm = () => {
    setShowVenueForm(false);
    // Optionally refresh the venue list
    const storedVenues = localStorage.getItem('venues');
    if (storedVenues) {
      setVenueList(JSON.parse(storedVenues));
    }
  };

  const handleCloseDesignerForm = () => {
    setShowDesignerForm(false);
    // Optionally refresh the designer list
    const storedDesigners = localStorage.getItem('designers');
    if (storedDesigners) {
      setDesignerList(JSON.parse(storedDesigners));
    }
  };

  return (
    <div className={styles.homePage}>
      <ASidebar
        onBookVendor={handleBookVendorClick}
        onBookVenue={handleBookVenueClick}
        onBookDesigner={handleBookDesignerClick}
      />
      <div className={styles.content}>
        <h1 className={styles.heading}>Welcome to Admin Homepage</h1>
        <button className={styles.logoutButton} onClick={handleLogout}>
          Logout
        </button>

        {showVendorForm && (
          <div className={styles.modalOverlay}>
            <VendorForm onClose={handleCloseVendorForm} />
          </div>
        )}

        {showVenueForm && (
          <div className={styles.modalOverlay}>
            <VenueForm onClose={handleCloseVenueForm} />
          </div>
        )}

        {showDesignerForm && (
          <div className={styles.modalOverlay}>
            <DesignerForm onClose={handleCloseDesignerForm} />
          </div>
        )}

        <div className={styles.listsContainer}>
          <div className={styles.list}>
            <h3>Added Vendors</h3>
            {vendorList.length === 0 ? (
              <p>No vendors added yet.</p>
            ) : (
              <ul>
                {vendorList.map((vendor, index) => (
                  <li key={index}>
                    {vendor.name} - {vendor.serviceType}
                  </li>
                ))}
              </ul>
            )}
          </div>

          <div className={styles.list}>
            <h3>Added Venues</h3>
            {venueList.length === 0 ? (
              <p>No venues added yet.</p>
            ) : (
              <ul>
                {venueList.map((venue, index) => (
                  <li key={index}>
                    {venue.name} - Capacity: {venue.capacity}
                  </li>
                ))}
              </ul>
            )}
          </div>

          <div className={styles.list}>
            <h3>Added Designers</h3>
            {designerList.length === 0 ? (
              <p>No designers added yet.</p>
            ) : (
              <ul>
                {designerList.map((designer, index) => (
                  <li key={index}>
                    {designer.name} - {designer.serviceType}
                  </li>
                ))}
              </ul>
            )}
          </div>
        </div>
      </div>
    </div>
  );
 }

 export default AdminHomePage;