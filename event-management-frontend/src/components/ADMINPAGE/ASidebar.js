import React from 'react';
 import styles from './ASidebar.module.css';
 import profileIcon from '../../assets/profile-icon.png'; // Corrected path

 function Sidebar({ onBookVendor, onBookVenue, onBookDesigner }) {
  return (
    <div className={styles.sidebar}>
      <div className={styles.logoContainer}>
        <img src={profileIcon} alt="Profile" className={styles.profileIcon} />
      </div>
      <div className={styles.menu}>
        <button className={styles.menuButton} onClick={onBookVendor}>
          Vendor
        </button>
        <button className={styles.menuButton} onClick={onBookVenue}>
          Venue
        </button>
        <button className={styles.menuButton} onClick={onBookDesigner}>
          Designer
        </button>
      </div>
    </div>
  );
 }

 export default Sidebar;