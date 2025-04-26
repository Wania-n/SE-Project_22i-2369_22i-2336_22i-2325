package com.example.Event_Management_System.repository;

import com.example.Event_Management_System.model.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestRepository extends JpaRepository<Guest, Integer> {
    // Add all the custome queries here!
}
