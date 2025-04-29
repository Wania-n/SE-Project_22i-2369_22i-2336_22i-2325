package com.example.Event_Management_System.controller;
import com.example.Event_Management_System.dto.EventRequest;
import com.example.Event_Management_System.dto.LoginRequest;
import com.example.Event_Management_System.model.Event;
import com.example.Event_Management_System.model.Organizer;
import com.example.Event_Management_System.model.Venue;
import com.example.Event_Management_System.service.EventService;
import com.example.Event_Management_System.service.GuestService;
import com.example.Event_Management_System.service.OrganizerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    private AuthenticationManager authenticationManager;

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
    public ResponseEntity<String> loginOrganizer(@RequestBody LoginRequest login) {
        if (login == null || login.getUsername() == null || login.getPassword() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Missing login credentials.");
        }

        try {
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword());

            Authentication authentication = authenticationManager.authenticate(authenticationToken);

            SecurityContextHolder.getContext().setAuthentication(authentication);

            return ResponseEntity.ok("Organizer logged-in successfully!");
        } catch (AuthenticationException e) {
            System.out.println("Authentication failed: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password.");
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
    @PostMapping("/bookEvent")
    public ResponseEntity<String> bookEvent(@RequestBody EventRequest eventRequest){

        // Checking for correct data passing
        if (eventRequest == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nothing received!!");
        }else {
            // Log data for debugging
            System.out.println("Received Event Data: " + eventRequest);
            System.out.println("Event name: " + eventRequest.getName());
            System.out.println("Event date: " + eventRequest.getDate());
            System.out.println("Event time: " + eventRequest.getStarttime());
            System.out.println("Event endtime: " + eventRequest.getEndttime());
            System.out.println("Event theme: " + eventRequest.getTheme());
            System.out.println("Event venue: " + eventRequest.getVenue());
            System.out.println("Event price: " + eventRequest.getTotalprice());
        }

        // Call the event service layer
        Event new_event = new Event();
        new_event.setName(eventRequest.getName());
        new_event.setTheme(eventRequest.getTheme());
        new_event.setDate(eventRequest.getDate());
        new_event.setTime(eventRequest.getStarttime());
        new_event.setEnd_time(eventRequest.getEndttime());
        new_event.setTotal_price(eventRequest.getTotalprice());

        // getting logged in Organizer
        Organizer organizer = organizerService.getOrganizerByUsername(eventRequest.getUsername());
        if(organizer == null){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Incorrect Username!");
        }
        new_event.setOrganizer(organizer);

        // Getting venue
        Venue venue = eventService.getVenue(eventRequest.getVenue());
        new_event.setVenue(venue);

        boolean isBooked = eventService.bookEvent(new_event);
        if(isBooked){
            return ResponseEntity.ok("Event booked successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to book Event");
        }
    }

    // Adding the Venues
    @GetMapping("/addVenues")
    public ResponseEntity<List<Venue>> getVenue(){
        try{
            return ResponseEntity.ok(eventService.getAllVenues());
        } catch (Exception e) {
            return null;
        }

    }

}