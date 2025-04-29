package com.example.Event_Management_System.repository;
import com.example.Event_Management_System.model.Organizer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrganizerRepository extends JpaRepository<Organizer, Integer> {

    // Implement all the custom queries here
    boolean existsByUsernameAndPassword(String username, String password);

    boolean existsByUsername(String username);

    void deleteByUsername(String username);

    Optional<Organizer> findByUsername(String username);

}