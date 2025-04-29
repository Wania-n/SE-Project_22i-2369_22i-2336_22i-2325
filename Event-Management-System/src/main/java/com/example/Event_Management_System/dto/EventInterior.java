package com.example.Event_Management_System.dto;

public class EventInterior {

    // Attributes
    private String event;
    private String designer;

    // Constructor
    public EventInterior() {}

    // setters and getters

    public String getEvent() {
        return event;
    }

    public void setEventID(String eventID) {
        this.event = eventID;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getDesigner() {
        return designer;
    }

    public void setDesigner(String designer) {
        this.designer = designer;
    }
}
