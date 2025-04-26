package com.example.Event_Management_System.model;
import com.example.Event_Management_System.factory.User;
import jakarta.persistence.*;

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

    // Default constructor
    public Admin() {}

    // Parametrized Constructor
    public Admin(String phone, String first, String last, String email, String address, String username, String password){
        super(phone, first, last, email, address);
        this.username = username;
        this.password = password;
    }
}
