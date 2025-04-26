package com.example.Event_Management_System.repository;
import com.example.Event_Management_System.model.Organizer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizerRepository extends JpaRepository<Organizer, Integer> {

    // Implement all the custom queries here
    boolean existsByUsernameAndPassword(String username, String password);

}
