package com.example.Event_Management_System.service;
import com.example.Event_Management_System.dto.LoginRequest;
import com.example.Event_Management_System.model.Organizer;
import com.example.Event_Management_System.repository.OrganizerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.PatternMatchUtils;

import java.time.LocalDate;
import java.time.Period;

// This is the service class, which contains all the business logic, and calls to the repository layer for database handles
@Service
public class OrganizerService {

    // Repository class reference
    @Autowired
    private OrganizerRepository organizerRepo;

    // Add functions for the application funtionality accordingly!!

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

        // If its correct then...
        organizerRepo.save(new_organizer);
        return true;
    }

    // Login ---------------------------------------------------
    public boolean loginOrganizer(LoginRequest login){

        // Now adding validation rules
        String username_regex = "^[A-Za-z][A-Za-z0-9._]{2,19}$";
        String password_regex = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d@#$%^&+=!]{6,20}$";

        if (!login.getUsername().matches(username_regex)) {
            return false;
        } else if (!login.getPassword().matches(password_regex)) {
            return false;
        }

        // If it's correct then...
        return organizerRepo.existsByUsernameAndPassword(login.getUsername(), login.getPassword());
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
            Organizer existing = organizerRepo.findByUsername(organizer.getUsername());
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



}