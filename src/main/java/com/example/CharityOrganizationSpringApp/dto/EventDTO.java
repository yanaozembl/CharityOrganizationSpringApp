package com.example.CharityOrganizationSpringApp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Date;

@Setter
@Getter
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
}
