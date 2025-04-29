package com.example.Event_Management_System.model;
import com.example.Event_Management_System.factory.ServiceProvider;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

// Child class: InteriorDesigner
@Entity
@Table(name = "InteriorDesigner")
public class InteriorDesigner extends ServiceProvider{

    // Attributes

    @OneToMany(mappedBy = "interiorDesigner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ThemeInfo> themeInfos = new ArrayList<>();

    // Constructor
    public InteriorDesigner(){}

    public InteriorDesigner(int serviceID, String name, String phone, String email, String address, String serviceType, float price){
        super(serviceID, name, phone, email, address, serviceType, price);
    }

    // Adding ThemeInfos here!
    public void add_themeInfos(ThemeInfo themeInfo){
        themeInfos.add(themeInfo);
    }

    // Implementation of abstract functions here!
    public void provide_service(){
        // add functionality here!
    }
}
