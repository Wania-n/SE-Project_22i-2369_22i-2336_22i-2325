package com.example.Event_Management_System.controller;
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
@RequestMapping("/api/admins")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminController {

    @Autowired
    private AdminService adminService;

    // I didnt add the venue, vendor or interior designer service because they are very small
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
    @PutMapping("/editVenue/{id}")
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
