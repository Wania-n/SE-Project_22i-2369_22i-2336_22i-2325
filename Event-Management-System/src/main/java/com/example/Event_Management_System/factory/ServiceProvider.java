//package com.example.Event_Management_System.factory;
//import jakarta.persistence.*;
//
//// This is an abstract class for child classes Vendor and Interior Designer
//@MappedSuperclass
//public abstract class ServiceProvider {
//
//    // Attributes
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int serviceID;
//
//    @Column(nullable = false, length = 100)
//    private String service_name;
//
//    @Column(nullable = false, length = 11)
//    private String phone;
//
//    @Column(nullable = false, length = 100)
//    private String email;
//
//    @Column(length = 200)
//    private String address;
//
//    @Column(nullable = false, length = 50)
//    private String serviceType;
//
//    @Column(nullable = false)
//    private float price;
//
//    // Default Constructor
//    public ServiceProvider(){}
//
//    // Parametrized Constructor
//    public ServiceProvider(int serviceID, String name, String phone, String email, String address, String serviceType, float price){
//        this.serviceID = serviceID;
//        this.service_name = name;
//        this.email = email;
//        this.address = address;
//        this.serviceType = serviceType;
//        this.phone = phone;
//        this.price = price;
//    }
//
//    // Add setter and getters as needed
//    public String getName() {
//        return service_name;
//    }
//    // Abstract functions to be added here!
//    public abstract void provide_service();
//}
package com.example.Event_Management_System.factory;

import jakarta.persistence.*;

@MappedSuperclass
public abstract class ServiceProvider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int serviceID;

    @Column(name = "service_name")  // This is where we map the service_name column
    protected
    String serviceName;

    private String phone;
    private String email;
    private String address;
    private String serviceType;
    private float price;

    // Constructor, getters, and setters
    public ServiceProvider() {}

    public ServiceProvider(int serviceID, String serviceName, String phone, String email, String address, String serviceType, float price) {
        this.serviceID = serviceID;
        this.serviceName = serviceName;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.serviceType = serviceType;
        this.price = price;
    }

    // Getters and setters for the fields

    public int getServiceID() {
        return serviceID;
    }

    public void setServiceID(int serviceID) {
        this.serviceID = serviceID;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    // Abstract method
    public abstract void provide_service();
}
