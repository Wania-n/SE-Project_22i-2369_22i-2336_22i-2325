package com.example.Event_Management_System.controller;
import com.example.Event_Management_System.model.Vendor;

import com.example.Event_Management_System.dto.LoginRequest;
import com.example.Event_Management_System.model.Admin;
import com.example.Event_Management_System.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.Event_Management_System.model.Venue;
import com.example.Event_Management_System.model.InteriorDesigner;

import com.example.Event_Management_System.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    @GetMapping("/getAllVenues")
    public ResponseEntity<List<Venue>> getAllVenues() {
        List<Venue> all = adminService.getAllVenues();
        return ResponseEntity.ok(all);
    }
    @PostMapping("/createVenue")
    public ResponseEntity<String> createVenue(@RequestBody Venue venue) {
        // Checking for correct data passing
        if (venue == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nothing received!!");
        } else {
            // Log data for debugging
            System.out.println("Received Venue Data: " + venue);
            System.out.println("Venue name: " + venue.getName());
            System.out.println("Venue address: " + venue.getAddress());
            System.out.println("Venue capacity: " + venue.getCapacity());
            System.out.println("Venue price: " + venue.getPrice());
            // Add other venue properties as needed
        }

        // Call the admin service layer
        boolean isCreated = adminService.createVenue(venue);
        if (isCreated) {
            return ResponseEntity.ok().body("Venue created successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create Venue.");
        }
    }

    @PostMapping("/deleteVenue")
    public ResponseEntity<String> deleteVenue(@RequestBody Map<String, String> payload) {
        // Extract the venue name from the request body
        String venueName = payload.get("name");

        // Checking for correct data passing
        if (venueName == null || venueName.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid venue name received!!");
        } else {
            // Log data for debugging
            System.out.println("Received Venue name for deletion: " + venueName);
        }

        // Call the admin service layer
        boolean isDeleted = adminService.deleteVenueByName(venueName);
        if (isDeleted) {
            return ResponseEntity.ok().body("Venue deleted successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete Venue. Venue may not exist.");
        }
    }


    //VENDOR
//    @PostMapping("/createVendor")
//    public ResponseEntity<String> createVendor(@RequestBody Vendor vendor) {
//        if (vendor == null) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nothing received!!");
//        } else {
//            System.out.println("Received Vendor Data: " + vendor);
//        }
//
//        boolean isCreated = adminService.createVendor(vendor);
//        if (isCreated) {
//            return ResponseEntity.ok().body("Vendor created successfully!");
//        } else {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create Vendor. Vendor may already exist.");
//        }
//    }
//
//    @PostMapping("/deleteVendor")
//    public ResponseEntity<String> deleteVendor(@RequestBody Map<String, String> payload) {
//        String vendorName = payload.get("name");
//
//        if (vendorName == null || vendorName.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid vendor name received!!");
//        } else {
//            System.out.println("Received Vendor name for deletion: " + vendorName);
//        }
//
//        boolean isDeleted = adminService.deleteVendorByName(vendorName);
//        if (isDeleted) {
//            return ResponseEntity.ok().body("Vendor deleted successfully!");
//        } else {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete Vendor. Vendor may not exist.");
//        }
//    }

}
