package com.example.Event_Management_System.controller;
import com.example.Event_Management_System.dto.LoginRequest;
import com.example.Event_Management_System.model.Organizer;
import com.example.Event_Management_System.service.EventService;
import com.example.Event_Management_System.service.GuestService;
import com.example.Event_Management_System.service.OrganizerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// This is the controller class for organizer, it will control the events, guests and all of that
@RestController
@RequestMapping("/api/organizer")
@CrossOrigin(origins = "http://localhost:3000")
public class OrganizerController {


    // These are the respective references of Service ------------------
    @Autowired
    private OrganizerService organizerService;

    @Autowired
    private EventService eventService;

    @Autowired
    private GuestService guestService;

    // Add relevants functions here! -----------------------------------

    // Sign up organizer
    @PostMapping("/signup")
    public ResponseEntity<String> registerOrganizer(@RequestBody Organizer organizer) {

        // Checking for correct data passing
        if (organizer == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nothing received!!");
        }else {
            // Log data for debugging
            System.out.println("Received Organizer Data: " + organizer);
            System.out.println("Organizer username: " + organizer.getUsername());
            System.out.println("Organizer password: " + organizer.getPassword());
            System.out.println("Organizer address: " + organizer.getAddress());
            System.out.println("Organizer email: " + organizer.getEmail());
            System.out.println("Organizer firstname: " + organizer.getFirstname());
            System.out.println("Organizer lastname: " + organizer.getLastname());
            System.out.println("Organizer phone: " + organizer.getPhone());
        }

        // Call the organizer service layer
        boolean isSaved = organizerService.registerOrganizer(organizer);
        if (isSaved) {
            return ResponseEntity.ok("Organizer registered successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to register organizer.");
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
            System.out.println("Receiving Organizer Login data: " + login);
            System.out.println("Organizer Username: " + login.getUsername());
            System.out.println("Organizer Password: " + login.getPassword());
        }

        // Call the Organizer Layer
        boolean isLogged = organizerService.loginOrganizer(login);
        if(isLogged){
            return ResponseEntity.ok("Organizer logged-in successfully!");
        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to login organizer.");
        }

    }

    // Delete Organizer
    @PostMapping("/delete")
    public ResponseEntity<String> deleteOrganizer(@RequestBody LoginRequest login){

        // Checking for data retrieval
        if(login == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nothing received!!");
        }else{
            // Log in for debugging
            System.out.println("Receiving Organizer Login data: " + login);
            System.out.println("Organizer Username: " + login.getUsername());
            System.out.println("Organizer Password: " + login.getPassword());
        }

        // Call the Organizer Layer
        boolean isdeleted = organizerService.deleteOrganizer(login);
        if(isdeleted){
            return ResponseEntity.ok("Organizer deleted successfully!");
        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete organizer.");
        }
    }

    // Edit Organizer
    @PostMapping("/edit")
    public ResponseEntity<String> editOrganizer(@RequestBody Organizer organizer){

        // Checking for correct data passing
        if (organizer == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nothing received!!");
        }else {
            // Log data for debugging
            System.out.println("Received Organizer Data: " + organizer);
            System.out.println("Organizer username: " + organizer.getUsername());
            System.out.println("Organizer password: " + organizer.getPassword());
            System.out.println("Organizer address: " + organizer.getAddress());
            System.out.println("Organizer email: " + organizer.getEmail());
            System.out.println("Organizer firstname: " + organizer.getFirstname());
            System.out.println("Organizer lastname: " + organizer.getLastname());
            System.out.println("Organizer phone: " + organizer.getPhone());
            System.out.println("Organizer DOB: " + organizer.getDOB());
        }

        // Call the organizer service layer
        boolean isEdited = organizerService.editOrganizer(organizer);
        if (isEdited) {
            return ResponseEntity.ok("Organizer edited successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to edit organizer.");
        }
    }

    // Event-Related Functions here -----------------------------------
    //@PostMapping("/bookEvent")
    //public ResponseEntity<String> bookEvent(@)

}