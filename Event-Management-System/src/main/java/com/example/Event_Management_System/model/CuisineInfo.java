package com.example.Event_Management_System.model;
import jakarta.persistence.*;

// This class contains cusineType and info so it is easier to store
@Entity
@Table(name = "Vendor_Menu")
public class CuisineInfo {

    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "cuisine_type")
    private String cuisineType;

    @Column(name = "info")
    private String info;

    @ManyToOne
    @JoinColumn(name = "vendorID")
    private Vendor vendor;

    // constructors
    public CuisineInfo(){}

    public CuisineInfo(String cuisineType, String info, Vendor vendor){
        this.cuisineType = cuisineType;
        this.info = info;
        this.vendor = vendor;
    }

    // Add relevant setters and getters

}
