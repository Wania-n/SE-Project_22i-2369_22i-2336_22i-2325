package com.example.Event_Management_System.model;
import com.example.Event_Management_System.factory.ServiceProvider;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

// Child class: Vendor
@Entity
@Table(name = "Vendor")
public class Vendor extends ServiceProvider {

    @OneToMany(mappedBy = "vendor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CuisineInfo> cuisineInfos = new ArrayList<>();

    // Constructor
    public Vendor(){}

    public Vendor(int serviceID, String name, String phone, String email, String address, String serviceType, float price){
        super(serviceID, name, phone, email, address, serviceType, price);
    }

    // Adding cuisineInfos here!
    public void add_cuisineInfos(CuisineInfo cuisineInfo){
        cuisineInfos.add(cuisineInfo);
    }

    // Implementation of abstract functions here!
    public void provide_service(){
        // add functionality here!
    }

}
