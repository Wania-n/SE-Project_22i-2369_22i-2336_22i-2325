package com.example.Event_Management_System.model;
import jakarta.persistence.*;

// This is Venue class, managed by the Admin
@Entity
@Table(name = "Venue")
public class Venue {

    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int venueID;

    @Column(name = "venue_name")
    private String name;

    @Column(name = "capacity")
    private int capacity;

    @Column(name = "address")
    private String address;

    @Column(name = "price")
    private float price;

    // Constructor
    public Venue(){}

    public Venue(String name, int capacity, String address, float price){
        this.name = name;
        this.capacity = capacity;
        this.address = address;
        this.price = price;
    }

    // Add setters and getters
    // Getter and Setter for venueID
    public int getVenueID() {
        return venueID;
    }

    public void setVenueID(int venueID) {
        this.venueID = venueID;
    }

    // Getter and Setter for name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter and Setter for address
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    // Getter and Setter for capacity
    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    // Getter and Setter for price
    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
