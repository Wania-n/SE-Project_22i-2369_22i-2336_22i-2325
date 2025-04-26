package com.example.Event_Management_System.repository;
import com.example.Event_Management_System.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
    // Add all the custome queries here!
}
