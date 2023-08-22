package com.example.CharityOrganizationSpringApp.models;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Events")
public class Event {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    @Column(name = "name")
    private String name;

    @Column(name = "date_and_time")
    private LocalDateTime dateTime;

    @Size(min = 2, max = 30, message = "Venue should be between 2 and 30 characters")
    @Column(name = "venue")
    private String venue;

    @Size(min = 2, max = 30, message = "Description should be between 2 and 30 characters")
    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "organizer_id", referencedColumnName = "id")
    private User organizer;

    @OneToMany(mappedBy = "event")
    private List<Participant> participants;

    @OneToMany(mappedBy = "event")
    private List<Donation> donations;

    public Event() {
    }

    public Event(String name, LocalDateTime dateTime, String venue, String description) {
        this.name = name;
        this.dateTime = dateTime;
        this.venue = venue;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public User getOrganizer() {
        return organizer;
    }

    public void setOrganizer(User organizer) {
        this.organizer = organizer;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Participant> participants) {
        this.participants = participants;
    }

    public List<Donation> getDonations() {
        return donations;
    }

    public void setDonations(List<Donation> donations) {
        this.donations = donations;
    }
}
