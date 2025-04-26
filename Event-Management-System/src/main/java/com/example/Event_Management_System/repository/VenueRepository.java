package com.example.Event_Management_System.repository;

import com.example.Event_Management_System.model.Venue;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Event_Management_System.model.Venue;
import org.springframework.data.jpa.repository.JpaRepository;
public interface VenueRepository extends JpaRepository<Venue, Integer> {
    // Add all the custome queries here!
    boolean existsByName(String name);
}
