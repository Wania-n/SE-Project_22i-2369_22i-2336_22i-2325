package com.example.Event_Management_System.service;
import com.example.Event_Management_System.dto.LoginRequest;
import com.example.Event_Management_System.model.Admin;
import com.example.Event_Management_System.model.Vendor;
import com.example.Event_Management_System.repository.AdminRepository;
import com.example.Event_Management_System.repository.InteriorDesignerRepository;
import com.example.Event_Management_System.repository.VendorRepository;
import com.example.Event_Management_System.repository.VenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Event_Management_System.model.Venue;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

// This is the service class, which contains all the business logic, and calls to the repository layer for database handles
@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepo;

    @Autowired
    private VendorRepository vendorRepo;

    @Autowired
    private InteriorDesignerRepository interiorDesignerRepo;

    @Autowired
    private VenueRepository venueRepo;

    // Add functions for the application funtionality accordingly!!

    // Sign up ----------------------------------------------------------------
    public boolean registerAdmin(Admin new_admin){

        // Now adding validation rules
        String name_regex = "^[A-Za-z ]{2,50}$";
        String username_regex = "^[A-Za-z][A-Za-z0-9._]{2,19}$";
        String password_regex = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d@#$%^&+=!]{6,20}$";
        String email_regex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
        String address_regex = "^[A-Za-z0-9 ,.-]{5,100}$";

        if(!new_admin.getFirstname().matches(name_regex)){
            return false;
        } else if (!new_admin.getLastname().matches(name_regex)) {
            return false;
        } else if (!new_admin.getUsername().matches(username_regex)) {
            return false;
        } else if (!new_admin.getPassword().matches(password_regex)) {
            return false;
        }else if(!new_admin.getAddress().matches(address_regex)){
            return false;
        } else if (!new_admin.getEmail().matches(email_regex)) {
            return false;
        } else if ( Period.between(new_admin.getDOB(), LocalDate.now()).getYears() < 18) {
            return false;
        }

        // If its correct then...
        adminRepo.save(new_admin);
        return true;
    }

    // Login ------------------------------------------------------------------
    public boolean loginAdmin(LoginRequest login){

        // Now adding validation rules
        String username_regex = "^[A-Za-z][A-Za-z0-9._]{2,19}$";
        String password_regex = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d@#$%^&+=!]{6,20}$";

        if (!login.getUsername().matches(username_regex)) {
            return false;
        } else if (!login.getPassword().matches(password_regex)) {
            return false;
        }

        // If it's correct then...
        return adminRepo.existsByUsernameAndPassword(login.getUsername(), login.getPassword());
    }

    // Create/Add Venue functionality ------------------------------------------
    public List<Venue> getAllVenues() {
        // JpaRepository provides findAll() out of the box
        return venueRepo.findAll();
    }
    public boolean createVenue(Venue venue) {
        if (venueRepo.existsByName(venue.getName())) {
            return false; // Venue already exists
        }
        venueRepo.save(venue);  // Save the new venue
        return true;
    }
    public boolean deleteVenueByName(String name) {
        try {
            // Find the venue by name
            Optional<Venue> venueOptional = venueRepo.findByName(name);

            if (venueOptional.isEmpty()) {
                return false; // If the venue doesn't exist
            }

            // Delete the venue
            venueRepo.delete(venueOptional.get());
            return true;
        } catch (Exception e) {
            System.err.println("Error deleting venue: " + e.getMessage());
            return false;
        }
    }
//    public boolean createVendor(Vendor vendor) {
//        // Use service_name instead of serviceName
//        if (vendorRepo.existsByService_name(vendor.getServiceName())) {  // Updated to service_name
//            return false; // Vendor already exists
//        }
//        vendorRepo.save(vendor);  // Save the new vendor
//        return true;
//    }
//
//
//    public boolean deleteVendorByName(String name) {
//        try {
//            // Use service_name instead of serviceName
//            Optional<Vendor> vendorOptional = vendorRepo.findByService_name(name);  // Updated to service_name
//
//            if (vendorOptional.isEmpty()) {
//                return false; // Vendor doesn't exist
//            }
//
//            // Delete the vendor
//            vendorRepo.delete(vendorOptional.get());
//            return true;
//        } catch (Exception e) {
//            System.err.println("Error deleting vendor: " + e.getMessage());
//            return false;
//        }
//    }

}
