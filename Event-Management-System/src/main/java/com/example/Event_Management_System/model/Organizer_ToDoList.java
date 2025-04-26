package com.example.Event_Management_System.model;
import jakarta.persistence.*;

// This class is for maintaining all the ToDoLists for the Organizer
@Entity
@Table(name = "Organizer_ToDoList")
public class Organizer_ToDoList {

    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "task", nullable = false, length = 100)
    private String task;

    @Column(name = "task_completed")
    private boolean isCompleted;

    @ManyToOne
    @JoinColumn(name = "organizerID")
    private Organizer organizer;

    // Contructor
    public Organizer_ToDoList(){
        this.isCompleted = false;
    }

    public Organizer_ToDoList(String task, boolean isCompleted){
        this.task = task;
        this.isCompleted = isCompleted;
    }
}
