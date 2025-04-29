package com.example.Event_Management_System.service;
import com.example.Event_Management_System.dto.LoginRequest;
import com.example.Event_Management_System.factory.User;
import com.example.Event_Management_System.model.Organizer;
import com.example.Event_Management_System.model.Venue;
import com.example.Event_Management_System.repository.OrganizerRepository;
import com.example.Event_Management_System.repository.VenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.PatternMatchUtils;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

// This is the service class, which contains all the business logic, and calls to the repository layer for database handles
@Service
public class OrganizerService {

    // Repository class reference
    @Autowired
    private OrganizerRepository organizerRepo;

    @Autowired
    private VenueRepository venueRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // Add functions for the application funtionality accordingly!!
    public Organizer getOrganizerByUsername(String username) {
        return organizerRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Organizer not found"));
    }

    // SignUp  ------------------------------------------------------------
    public boolean registerOrganizer(Organizer new_organizer){

        // Now adding validation rules
        String name_regex = "^[A-Za-z ]{2,50}$";
        String username_regex = "^[A-Za-z][A-Za-z0-9._]{2,19}$";
        String password_regex = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d@#$%^&+=!]{6,20}$";
        String email_regex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
        String address_regex = "^[A-Za-z0-9 ,.-]{5,100}$";

        if(!new_organizer.getFirstname().matches(name_regex)){
            return false;
        } else if (!new_organizer.getLastname().matches(name_regex)) {
            return false;
        } else if (!new_organizer.getUsername().matches(username_regex)) {
            return false;
        } else if (!new_organizer.getPassword().matches(password_regex)) {
            return false;
        }else if(!new_organizer.getAddress().matches(address_regex)){
            return false;
        } else if (!new_organizer.getEmail().matches(email_regex)) {
            return false;
        } else if ( Period.between(new_organizer.getDOB(), LocalDate.now()).getYears() < 18) {
            return false;
        }

        String encodedPassword = passwordEncoder.encode(new_organizer.getPassword());
        new_organizer.setPassword(encodedPassword);

        // If its correct then...
        organizerRepo.save(new_organizer);
        return true;
    }

    // Login ---------------------------------------------------
    public boolean loginOrganizer(LoginRequest loginRequest) {
        // Regular expressions for username and password format validation
        String usernameRegex = "^[A-Za-z][A-Za-z0-9._]{2,19}$";
        String passwordRegex = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d@#$%^&+=!]{6,20}$";

        // Validate the username format
        if (!loginRequest.getUsername().matches(usernameRegex)) {
            return false;  // Username does not match the regex
        }
        // Validate the password format
        if (!loginRequest.getPassword().matches(passwordRegex)) {
            return false;  // Password does not match the regex
        }

        // Retrieve the organizer by username from the database
        Optional<Organizer> optionalOrganizer = organizerRepo.findByUsername(loginRequest.getUsername());
        if (optionalOrganizer.isEmpty()) {
            return false;  // Organizer not found
        }
        Organizer organizer = optionalOrganizer.get();

        // Get the encoded password from the database
        String storedPassword = organizer.getPassword();

        // Compare the raw password with the hashed password using BCrypt
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (passwordEncoder.matches(loginRequest.getPassword(), storedPassword)) {
            // Password matches, successful login
            return true;
        }
        return false;  // Password does not match
    }

    // Delete -----------------------------------------------------
    @Transactional
    public boolean deleteOrganizer(LoginRequest login){

        // Checking and then deleting
        if(organizerRepo.existsByUsername(login.getUsername())){
            organizerRepo.deleteByUsername(login.getUsername());
            return true;
        }else{
            return false;
        }
    }

    // Edit ------------------------------------------------------
    public boolean editOrganizer(Organizer organizer){

        // Now adding validation rules
        String name_regex = "^[A-Za-z ]{2,50}$";
        String username_regex = "^[A-Za-z][A-Za-z0-9._]{2,19}$";
        String password_regex = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d@#$%^&+=!]{6,20}$";
        String email_regex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
        String address_regex = "^[A-Za-z0-9 ,.-]{5,100}$";

        if(!organizer.getFirstname().matches(name_regex)){
            return false;
        } else if (!organizer.getLastname().matches(name_regex)) {
            return false;
        } else if (!organizer.getUsername().matches(username_regex)) {
            return false;
        } else if (!organizer.getPassword().matches(password_regex)) {
            return false;
        }else if(!organizer.getAddress().matches(address_regex)){
            return false;
        } else if (!organizer.getEmail().matches(email_regex)) {
            return false;
        } else if ( Period.between(organizer.getDOB(), LocalDate.now()).getYears() < 18) {
            return false;
        }

        // If its correct then...
        if(organizerRepo.existsByUsername(organizer.getUsername())){

            // get existing organizer
            Organizer existing = getOrganizerByUsername(organizer.getUsername());
            if(existing != null){
                existing.setEmail(organizer.getEmail());
                existing.setDOB(organizer.getDOB());
                existing.setAddress(organizer.getAddress());
                existing.setFirstname(organizer.getFirstname());
                existing.setLastname(organizer.getLastname());
                existing.setPhone(organizer.getPhone());

                organizerRepo.save(existing);
            }
        }
        return true;
    }

    // Getting logged in Organizer
    public Organizer getLoggedInOrganizer() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && !(authentication instanceof AnonymousAuthenticationToken)) {
            String username = authentication.getName(); // usually the username or email
            System.out.println("username: " + username);
            return organizerRepo.findByUsername(username)
                    .orElseThrow(() -> new RuntimeException("Organizer not found"));
        } else {
            throw new RuntimeException("User is not authenticated.");
        }
    }
}