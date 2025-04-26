import React from 'react';
import styles from './Sidebar.module.css';
import profileIcon from '../../assets/profile-icon.png'; // Corrected path

function Sidebar({ onBookEvent, onBookVendor, onBookDesigner, onUserClick }) {
  return (
    <div className={styles.sidebar}>
      <div className={styles.logoContainer}>
        <img src={profileIcon} alt="Profile" className={styles.profileIcon} />
      </div>
      <div className={styles.menu}>
        <button className={styles.menuButton} onClick={onUserClick}>
          User
        </button>
        <button className={styles.menuButton} onClick={onBookEvent}>
          Book Event
        </button>
        <button className={styles.menuButton} onClick={onBookVendor}>
          Book Vendor
        </button>
        <button className={styles.menuButton} onClick={onBookDesigner}>
          Book Designer
        </button>
      </div>
    </div>
  );
}

export default Sidebar;