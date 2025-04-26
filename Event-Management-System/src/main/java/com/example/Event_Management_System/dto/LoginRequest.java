package com.example.Event_Management_System.dto;

public class LoginRequest {

    // Attributes
    private String username;
    private String password;

    // Constructor
    public LoginRequest() {}

    // Getters and Setters
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
}

