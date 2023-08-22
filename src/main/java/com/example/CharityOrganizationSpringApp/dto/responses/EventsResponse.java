package com.example.CharityOrganizationSpringApp.dto.responses;

import com.example.CharityOrganizationSpringApp.dto.EventDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class EventsResponse {

    private List<EventDTO> events;
}
