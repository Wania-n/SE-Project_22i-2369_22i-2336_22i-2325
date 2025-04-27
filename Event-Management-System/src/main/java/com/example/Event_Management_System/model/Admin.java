package com.example.Event_Management_System.model;
import com.example.Event_Management_System.factory.User;
import jakarta.persistence.*;

import java.time.LocalDate;

// This is admin class
@Entity
@Table(name = "Admin")
public class Admin extends User {

    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userID;

    @Column(name = "admin_username", nullable = false, unique = true, length = 50)
    private String username;

    @Column(name = "admin_password", nullable = false, length = 50)
    private String password;

    @Column(name = "dob")
    private LocalDate DOB;

    // Default constructor
    public Admin() {}

    // Parametrized Constructor
    public Admin(String phone, String first, String last, String email, String address, String username, String password, LocalDate dob){
        super(phone, first, last, email, address);
        this.username = username;
        this.password = password;
        this.DOB = dob;
    }

    // Setters and Getters
    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getDOB() {
        return this.DOB;
    }

    public void setDOB(LocalDate DOB) {
        this.DOB = DOB;
    }

    public String getPassword() {
        return password;
    }

    public int getUserID() {
        return userID;
    }

    @Override
    public String getAddress() {
        return super.getAddress();
    }

    @Override
    public String getEmail() {
        return super.getEmail();
    }

    @Override
    public String getFirstname() {
        return super.getFirstname();
    }

    @Override
    public String getLastname() {
        return super.getLastname();
    }

    @Override
    public String getPhone() {
        return super.getPhone();
    }
}
