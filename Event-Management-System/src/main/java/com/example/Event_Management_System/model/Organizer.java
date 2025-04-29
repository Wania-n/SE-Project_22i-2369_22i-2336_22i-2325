package com.example.Event_Management_System.model;
import com.example.Event_Management_System.factory.User;
import jakarta.persistence.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// This is the Organizer class
@Entity
@Table(name = "Organizer")
public class Organizer extends User {

    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userID;

    @Column(name = "org_username", nullable = false, unique = true, length = 50)
    private String username;

    @Column(name = "org_password", nullable = false, length = 255)
    private String password;

    @Column(name = "DOB")
    private LocalDate DOB;

    @OneToMany(mappedBy = "organizer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Organizer_ToDoList> todoLists = new ArrayList<>();

    // Default constructor
    public Organizer() {}

    // Parametrized constructor
    public Organizer(String phone, String first, String last, String email, String address, String username, String password, LocalDate DOB){
        super(phone, first, last, email, address);
        this.username = username;
        this.password = password;
        this.DOB = DOB;
    }

    // Setter and getters
    public void setUsername(String username){
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public LocalDate getDOB() {
        return DOB;
    }

    public void setDOB(LocalDate DOB) {
        this.DOB = DOB;
    }

    // Adding ToDoLists here!
    public void add_Todos(Organizer_ToDoList list){
        todoLists.add(list);
    }


    public void book_event(){
        // add functionality!
    }

    public void invite_guest(){
        // add funtionality!
    }

}

