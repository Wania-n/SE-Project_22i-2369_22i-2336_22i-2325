package com.example.Event_Management_System.model;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

// This is event class, resposible for booking events
@Entity
@Table(name = "Event")
public class Event {

    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int eventID;

    @Column(name = "event_name")
    private String name;

    @Column(name = "event_date")
    private LocalDate date;

    @Column(name = "event_time")
    private LocalTime time;

    @Column(name = "total_price")
    private float total_price;

    @Column(name = "event_theme")
    private String theme;

    @ManyToOne
    @JoinColumn(name = "organizerID", nullable = false)
    private Organizer organizer;

    @ManyToOne
    @JoinColumn(name = "venueID", nullable = true)
    private Venue venue;

    @ManyToOne
    @JoinColumn(name = "vendorID", nullable = true)
    private Vendor vendor;

    @ManyToOne
    @JoinColumn(name = "interiorDesignerID", nullable = true)
    private InteriorDesigner interiorDesigner;

    // Constructor
    public Event(){}

    public Event(String name, LocalDate date, LocalTime time, float price){
        this.name = name;
        this.date = date;
        this.time = time;
        this.total_price = price;
    }

    // add setters and getters

    // relevant functions

    // Adding venue to the event
    public void add_venue(){
        // Add functionality here!
    }

    // Adding vendor to the event
    public void add_vendor(){
        // Add functionality here!
    }

    // Adding interior designer to the event
    public void add_interiordesigner(){
        // Add functionality here!
    }


}