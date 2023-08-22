package com.example.CharityOrganizationSpringApp.dto;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Date;

public class EventDTO {

    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String name;

    private LocalDateTime dateTime;

    @Size(min = 2, max = 30, message = "Venue should be between 2 and 30 characters")
    private String venue;

    @Size(min = 2, max = 30, message = "Description should be between 2 and 30 characters")
    private String description;

    @Valid
    @NotNull
    private UserDTO organizer;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserDTO getOrganizer() {
        return organizer;
    }

    public void setOrganizer(UserDTO organizer) {
        this.organizer = organizer;
    }
}
