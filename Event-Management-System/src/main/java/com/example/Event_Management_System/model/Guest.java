package com.example.Event_Management_System.model;
import jakarta.persistence.*;

// This is the guest class, for every event
@Entity
@Table(name = "Guest")
public class Guest {

    // Attribute
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int guestID;

    @Column(name = "guest_name")
    private String name;

    @Column(name = "email")
    private String email;

    @ManyToOne
    @JoinColumn(name = "organizerID", nullable = false)
    private Organizer organizer;

    @ManyToOne
    @JoinColumn(name = "eventID", nullable = false)
    private Event event;

    // Constructor
    public Guest(){}

    public Guest(String name, String email){
        this.name = name;
        this.email = email;
    }
}
