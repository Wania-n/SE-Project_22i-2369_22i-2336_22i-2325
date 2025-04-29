package com.example.Event_Management_System.repository;

import com.example.Event_Management_System.model.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorRepository extends JpaRepository<Vendor, Integer> {
    // Add all the custome queries here!

    Vendor findByServiceName(String serviceName);
}
