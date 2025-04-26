package com.example.Event_Management_System.service;
import com.example.Event_Management_System.repository.AdminRepository;
import com.example.Event_Management_System.repository.InteriorDesignerRepository;
import com.example.Event_Management_System.repository.VendorRepository;
import com.example.Event_Management_System.repository.VenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Event_Management_System.model.Venue;
import com.example.Event_Management_System.repository.VenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    // Create/Add Venue functionality
    public boolean createVenue(Venue venue) {
        if (venueRepo.existsByName(venue.getName())) {
            return false; // Venue already exists
        }
        venueRepo.save(venue);  // Save the new venue
        return true;
    }
    public boolean updateVenue(int id, Venue venue) {
        if (!venueRepo.existsById(id)) {
            return false; // If the venue does not exist
        }

        // Retrieve the venue
        Venue existingVenue = venueRepo.findById(id).orElse(null);

        // Update the venue details
        if (existingVenue != null) {
            existingVenue.setName(venue.getName());
            existingVenue.setAddress(venue.getAddress());
            existingVenue.setCapacity(venue.getCapacity());
            existingVenue.setPrice(venue.getPrice());

            venueRepo.save(existingVenue); // Save updated venue
            return true;
        }

        return false; // Return false if venue doesn't exist
    }
    public boolean deleteVenue(int id) {
        if (!venueRepo.existsById(id)) {
            return false; // If the venue doesn't exist
        }

        venueRepo.deleteById(id);  // Delete venue by ID
        return true;
    }

}
