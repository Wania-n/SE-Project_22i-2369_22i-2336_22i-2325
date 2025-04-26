package com.example.Event_Management_System.factory;
import jakarta.persistence.*;

// This is an abstract class for child classes Vendor and Interior Designer
@MappedSuperclass
public abstract class ServiceProvider {

    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int serviceID;

    @Column(nullable = false, length = 100)
    private String service_name;

    @Column(nullable = false, length = 11)
    private String phone;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(length = 200)
    private String address;

    @Column(nullable = false, length = 50)
    private String serviceType;

    @Column(nullable = false)
    private float price;

    // Default Constructor
    public ServiceProvider(){}

    // Parametrized Constructor
    public ServiceProvider(int serviceID, String name, String phone, String email, String address, String serviceType, float price){
        this.serviceID = serviceID;
        this.service_name = name;
        this.email = email;
        this.address = address;
        this.serviceType = serviceType;
        this.phone = phone;
        this.price = price;
    }

    // Add setter and getters as needed

    // Abstract functions to be added here!
    public abstract void provide_service();
}
