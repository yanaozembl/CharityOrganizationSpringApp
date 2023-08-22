package com.example.CharityOrganizationSpringApp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
public class ParticipantDTO {

    @NotNull
    private Integer eventIdentifier;

    @NotNull
    private Integer clientIdentifier;

    @NotNull
    private Boolean participated;
}
