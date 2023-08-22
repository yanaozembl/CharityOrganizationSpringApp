package com.example.CharityOrganizationSpringApp.controllers;

import com.example.CharityOrganizationSpringApp.services.EventService;
import com.example.CharityOrganizationSpringApp.services.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final EventService eventService;
    private final ParticipantService participantService;

    @Autowired
    public UserController(EventService eventService, ParticipantService participantService) {
        this.eventService = eventService;
        this.participantService = participantService;
    }

}
