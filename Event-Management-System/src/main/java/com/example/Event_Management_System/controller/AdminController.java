package com.example.Event_Management_System.controller;
import com.example.Event_Management_System.dto.LoginRequest;
import com.example.Event_Management_System.model.Admin;
import com.example.Event_Management_System.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.Event_Management_System.model.Venue;
import com.example.Event_Management_System.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// This is the admin controller, it will control the admins, venues, vendors and interior designers
@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminController {

    @Autowired
    private AdminService adminService;

    // I didnt add the venue, vendor or interior designer service because they are very small

    // Sign up --------------------------------------------------
    @PostMapping("/signup")
    public ResponseEntity<String> registerOrganizer(@RequestBody Admin admin) {

        // Checking for correct data passing
        if (admin == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nothing received!!");
        }else {
            // Log data for debugging
            System.out.println("Received Admin Data: " + admin);
            System.out.println("Admin username: " + admin.getUsername());
            System.out.println("Admin password: " + admin.getPassword());
            System.out.println("Admin address: " + admin.getAddress());
            System.out.println("Admin email: " + admin.getEmail());
            System.out.println("Admin firstname: " + admin.getFirstname());
            System.out.println("Admin lastname: " + admin.getLastname());
            System.out.println("Admin phone: " + admin.getPhone());
            System.out.println("Admin DOB: " + admin.getDOB());
        }

        // Call the organizer service layer
        boolean isSaved = adminService.registerAdmin(admin);
        if (isSaved) {
            return ResponseEntity.ok().body("Admin registered successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to register Admin.");
        }
    }

    // Login Organizer
    @PostMapping("/login")
    public ResponseEntity<String> loginOrganizer(@RequestBody LoginRequest login){

        // Checking for data retrieval
        if(login == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nothing received!!");
        }else{
            // Log in for debugging
            System.out.println("Receiving Admin Login data: " + login);
            System.out.println("Admin Username: " + login.getUsername());
            System.out.println("Admin Password: " + login.getPassword());
        }

        // Call the Organizer Layer
        boolean isLogged = adminService.loginAdmin(login);
        if(isLogged){
            return ResponseEntity.ok("Admin logged-in successfully!");
        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to login admin.");
        }
    }

    // Venue ------------------------------------------------------------------

    @PostMapping("/createVenue")
    public ResponseEntity<String> createVenue(@RequestBody Venue venue) {
        try {
            boolean isAdded = adminService.createVenue(venue);
            if (isAdded) {
                return ResponseEntity.status(HttpStatus.CREATED).body("Venue added successfully.");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Venue with the same name already exists.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add venue.");
        }
    }

    // Add functionality accordingly!
    @PostMapping("/editVenue/{id}")
    public ResponseEntity<String> editVenue(@PathVariable int id, @RequestBody Venue venue) {
        try {
            boolean isUpdated = adminService.updateVenue(id, venue);
            if (isUpdated) {
                return ResponseEntity.status(HttpStatus.OK).body("Venue updated successfully.");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Venue not found.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update venue.");
        }
    }
    @DeleteMapping("/deleteVenue/{id}")
    public ResponseEntity<String> deleteVenue(@PathVariable int id) {
        try {
            boolean isDeleted = adminService.deleteVenue(id);
            if (isDeleted) {
                return ResponseEntity.status(HttpStatus.OK).body("Venue deleted successfully.");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Venue not found.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete venue.");
        }
    }

}
