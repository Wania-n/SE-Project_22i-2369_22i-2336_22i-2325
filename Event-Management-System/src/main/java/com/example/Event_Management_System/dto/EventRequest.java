package com.example.Event_Management_System.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class EventRequest {

    // Attributes
    private String name;
    private String theme;
    private LocalDate date;
    private LocalTime starttime;
    private LocalTime endttime;
    private String venue;
    private float totalprice;
    private String username;

    // Constructor
    public EventRequest() {}

    @JsonProperty("dateTime") // <-- this is the field coming from frontend
    public void setDateTime(String dateTime) {
        if (dateTime != null) {
            LocalDateTime parsed = LocalDateTime.parse(dateTime);
            this.date = parsed.toLocalDate();
            this.starttime = parsed.toLocalTime();
        }
    }

    // Setters and Getters

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public float getTotalprice() {
        return totalprice;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setTotalprice(float totalprice) {
        this.totalprice = totalprice;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getTheme() {
        return theme;
    }

    public String getName() {
        return name;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getEndttime() {
        return endttime;
    }

    public LocalTime getStarttime() {
        return starttime;
    }

    public String getVenue() {
        return venue;
    }

    public void setEndttime(LocalTime endttime) {
        this.endttime = endttime;
    }

    public void setStarttime(LocalTime starttime) {
        this.starttime = starttime;
    }
}
