package com.example.Event_Management_System.factory;
import jakarta.persistence.*;

// This is an abstract class for child classes Vendor and Interior Designer
@MappedSuperclass
public abstract class ServiceProvider {

    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int serviceID;

    @Column(name = "service_name", nullable = false, length = 100)
    private String serviceName;

    @Column(nullable = false, length = 11)
    private String phone;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(length = 200)
    private String address;

    @Column(name= "service_type", nullable = false, length = 50)
    private String serviceType;

    @Column(nullable = false)
    private float price;

    // Default Constructor
    public ServiceProvider(){}

    // Parametrized Constructor
    public ServiceProvider(int serviceID, String name, String phone, String email, String address, String serviceType, float price){
        this.serviceID = serviceID;
        this.serviceName = name;
        this.email = email;
        this.address = address;
        this.serviceType = serviceType;
        this.phone = phone;
        this.price = price;
    }

    // Add setter and getters as needed


    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getServiceID() {
        return serviceID;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public void setServiceID(int serviceID) {
        this.serviceID = serviceID;
    }

    public String getServiceType() {
        return serviceType;
    }

    public float getPrice() {
        return price;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    // Abstract functions to be added here!
    public abstract void provide_service();
}
