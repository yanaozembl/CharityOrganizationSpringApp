package com.example.CharityOrganizationSpringApp.dto.responses;

import com.example.CharityOrganizationSpringApp.dto.EventDTO;

import java.util.List;

public class EventsResponse {

    private List<EventDTO> events;

    public EventsResponse(List<EventDTO> events) {
        this.events = events;
    }

    public List<EventDTO> getEvents() {
        return events;
    }

    public void setEvents(List<EventDTO> events) {
        this.events = events;
    }
}
