package com.example.CharityOrganizationSpringApp.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Events")
@Setter
@Getter
@NoArgsConstructor
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

    public Event(String name, LocalDateTime dateTime, String venue, String description) {
        this.name = name;
        this.dateTime = dateTime;
        this.venue = venue;
        this.description = description;
    }
}
