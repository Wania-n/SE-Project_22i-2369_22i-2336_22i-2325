package com.example.Event_Management_System.service;

import com.example.Event_Management_System.model.Organizer;
import com.example.Event_Management_System.repository.OrganizerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private OrganizerRepository organizerRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Organizer organizer = organizerRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // Do NOT call getLoggedInOrganizer() or any method using SecurityContext here

        return new org.springframework.security.core.userdetails.User(
                organizer.getUsername(),
                organizer.getPassword(),
                Collections.emptyList() // or populate with authorities if needed
        );
    }
}
