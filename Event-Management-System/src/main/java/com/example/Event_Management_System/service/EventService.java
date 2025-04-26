package com.example.Event_Management_System.service;
import com.example.Event_Management_System.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// This is the service class, which contains all the business logic, and calls to the repository layer for database handles
@Service
public class EventService {

    @Autowired
    private EventRepository eventRepo;

    // Add functions for the application funtionality accordingly!!
}
