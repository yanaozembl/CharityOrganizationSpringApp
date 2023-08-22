package com.example.CharityOrganizationSpringApp.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Participants")
@Setter
@Getter
@NoArgsConstructor
public class Participant {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "event_id", referencedColumnName = "id")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private User client;

    @NotNull
    @Column(name = "participated")
    private Boolean participated;

    public Participant(Event event, User client, Boolean participated) {
        this.event = event;
        this.client = client;
        this.participated = participated;
    }


}
