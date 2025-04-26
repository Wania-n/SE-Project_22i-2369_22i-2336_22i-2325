package com.example.Event_Management_System.repository;

import com.example.Event_Management_System.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Integer> {
    // Add all the custome queries here!
}
