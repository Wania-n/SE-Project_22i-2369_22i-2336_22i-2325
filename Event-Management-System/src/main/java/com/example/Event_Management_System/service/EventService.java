package com.example.Event_Management_System.service;
import com.example.Event_Management_System.model.Event;
import com.example.Event_Management_System.model.InteriorDesigner;
import com.example.Event_Management_System.model.Vendor;
import com.example.Event_Management_System.model.Venue;
import com.example.Event_Management_System.repository.EventRepository;
import com.example.Event_Management_System.repository.InteriorDesignerRepository;
import com.example.Event_Management_System.repository.VendorRepository;
import com.example.Event_Management_System.repository.VenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

// This is the service class, which contains all the business logic, and calls to the repository layer for database handles
@Service
public class EventService {

    @Autowired
    private EventRepository eventRepo;

    @Autowired
    private VenueRepository venueRepo;

    @Autowired
    private VendorRepository vendorRepo;

    @Autowired
    private InteriorDesignerRepository interiorDesignerRepo;

    // Date and Time Validation function
    private boolean isTimeWithinAllowedRange(LocalTime time) {
        LocalTime earliest = LocalTime.of(9, 0);     // 9:00 AM
        LocalTime latest = LocalTime.of(23, 59);     // 11:59 PM

        return !time.isBefore(earliest) && !time.isAfter(latest);
    }
    public boolean DateTimeValidation(LocalDate date, LocalTime start_time, LocalTime end_time){

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime selectedStartDateTime = LocalDateTime.of(date, start_time);

        // 1. Start datetime must not be in the past
        if (selectedStartDateTime.isBefore(now)) {
            System.out.println("Cannot book for a past date and time.");
            return false;
        }

        // 2. Start and end time must be between 9:00 AM and 12:00 AM
        if (!isTimeWithinAllowedRange(start_time) || !isTimeWithinAllowedRange(end_time)) {
            System.out.println("Time must be between 9:00 AM and 12:00 AM (midnight).");
            return false;
        }

        // 3. End time must be at least 1 hour after start time
        if (!end_time.isAfter(start_time.plusHours(1))) {
            System.out.println("There must be at least a 1-hour gap between start and end time.");
            return false;
        }

        return true; // All checks passed
    }

    // Book Event ----------------------------
    public boolean bookEvent(Event event){

        // Now adding validation rules
        String name_regex = "^[A-Za-z0-9\\s'\\-]+$";

        boolean Isdate_time = DateTimeValidation(event.getDate(), event.getTime(), event.getEnd_time());
        if(!Isdate_time){
            System.out.println("Incorrect Date and time");
            return false;
        }else if(!event.getName().matches(name_regex)){
            System.out.println("Incorrect name");
            return false;
        }else if(!event.getTheme().matches(name_regex)){
            System.out.println("Incorrect theme name");
            return false;
        } else if(event.getVenue() == null) {
            System.out.println("venue null");
            return false;
        }

        try {
            eventRepo.save(event);
            return true;
        } catch (Exception e) {
            System.out.println("insertion problem");
            e.printStackTrace();  // <-- See full error in console
            return false;
        }

    }

    // Getting venue
    public Venue getVenue(String name){
        return venueRepo.findByName(name);
    }

    // Getting event
    public Event getEvent(String name){
        return eventRepo.findByName(name);
    }

    // Getting vendor
    public Vendor getVendor(String serviceName){
        return vendorRepo.findByServiceName(serviceName);
    }

    // Getting vendor
    public InteriorDesigner getInterior(String serviceName){
        return interiorDesignerRepo.findByServiceName(serviceName);
    }

    // Getting All Venue
    public List<Venue> getAllVenues(){
        return venueRepo.findAll();
    }

    // Getting All Events
    public List<Event> getAllEvents(){
        return eventRepo.findAll();
    }

    // Getting All Vendors
    public List<Vendor> getAllVendors(){
        return vendorRepo.findAll();
    }

    // Getting All Venue
    public List<InteriorDesigner> getAllInteriorDesigners(){
        return interiorDesignerRepo.findAll();
    }

}
