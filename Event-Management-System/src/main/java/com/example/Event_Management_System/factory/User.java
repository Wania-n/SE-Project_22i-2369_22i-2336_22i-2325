package com.example.Event_Management_System.factory;
import jakarta.persistence.*;

// This is an abstract class User with child class as Organizer and Admin
@MappedSuperclass
public abstract class User {

    // Attributes
    @Column(name = "phone", nullable = false, length = 11)
    private String phone;

    @Column(name = "firstname", length = 50)
    private String firstname;

    @Column(name = "lastname", length = 50)
    private String lastname;

    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "address", length = 100)
    private String address;

    // Constructor
    public User(){}

    public User(String phone, String first, String last, String email, String address){
        this.phone = phone;
        this.firstname = first;
        this.lastname = last;
        this.email = email;
        this.address = address;
    }

    // Adding setters and getters

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getPhone() {
        return phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
