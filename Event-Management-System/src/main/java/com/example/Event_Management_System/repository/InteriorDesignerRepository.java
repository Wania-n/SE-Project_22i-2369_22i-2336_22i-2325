package com.example.Event_Management_System.repository;

import com.example.Event_Management_System.model.InteriorDesigner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InteriorDesignerRepository extends JpaRepository<InteriorDesigner, Integer> {
    // Add all the custome queries here!
}
