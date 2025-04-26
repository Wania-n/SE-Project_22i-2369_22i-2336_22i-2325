package com.example.Event_Management_System.model;
import jakarta.persistence.*;

// This class contains Theme and info for the interior designer
@Entity
@Table(name = "InteriorDesigner_Design")
public class ThemeInfo {

    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "Theme")
    private String theme;

    @Column(name = "info")
    private String info;

    @ManyToOne
    @JoinColumn(name = "interiorDesignerID")
    private InteriorDesigner interiorDesigner;

    // constructor
    public ThemeInfo(){}

    public ThemeInfo(String Theme, String info, InteriorDesigner interiorDesigner){
        this.theme = Theme;
        this.info = info;
        this.interiorDesigner = interiorDesigner;
    }

    // add relevant setters and getters
}
