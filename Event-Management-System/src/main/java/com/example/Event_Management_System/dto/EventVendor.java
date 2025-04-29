package com.example.Event_Management_System.dto;

public class EventVendor {

    // Attributes
    private String event;
    private String vendor;

    // Constructor
    public EventVendor() {}

    // setters and getters

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getEvent() {
        return event;
    }

    public String getVendor() {
        return vendor;
    }

    public void setEvent(String event) {
        this.event = event;
    }
}
