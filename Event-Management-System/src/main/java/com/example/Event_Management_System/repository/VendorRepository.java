package com.example.Event_Management_System.repository;

import com.example.Event_Management_System.model.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VendorRepository extends JpaRepository<Vendor, Integer> {
    // Add all the custome queries here!
//    boolean existsByService_name(String serviceName);  // Updated query method
//
//    Optional<Vendor> findByService_name(String serviceName);  // Updated quer

}
