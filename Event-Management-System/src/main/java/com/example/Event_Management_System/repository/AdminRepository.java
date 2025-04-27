package com.example.Event_Management_System.repository;
import com.example.Event_Management_System.model.Admin;
import com.example.Event_Management_System.model.Organizer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Integer> {

    // Add all the custom queries here!
    boolean existsByUsernameAndPassword(String username, String password);

    boolean existsByUsername(String username);

    void deleteByUsername(String username);

    Admin findByUsername(String username);
}
