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

    @Column(name = "event_end_time")
    private LocalTime end_time;

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

    public Event(String name, LocalDate date, LocalTime time, float price, LocalTime end_time){
        this.name = name;
        this.date = date;
        this.time = time;
        this.end_time = end_time;
        this.total_price = price;
    }

    // add setters and getters

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public float getTotal_price() {
        return total_price;
    }

    public String getTheme() {
        return theme;
    }

    public LocalTime getEnd_time() {
        return this.end_time;
    }

    public LocalDate getDate() {
        return date;
    }

    public InteriorDesigner getInteriorDesigner() {
        return interiorDesigner;
    }

    public void setInteriorDesigner(InteriorDesigner interiorDesigner) {
        this.interiorDesigner = interiorDesigner;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public void setTotal_price(float total_price) {
        this.total_price = total_price;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public void setOrganizer(Organizer organizer) {
        this.organizer = organizer;
    }

    public void setEnd_time(LocalTime end_time) {
        this.end_time = end_time;
    }

    public LocalTime getTime() {
        return time;
    }

    public Organizer getOrganizer() {
        return organizer;
    }

    public String getName() {
        return name;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public Venue getVenue() {
        return venue;
    }
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