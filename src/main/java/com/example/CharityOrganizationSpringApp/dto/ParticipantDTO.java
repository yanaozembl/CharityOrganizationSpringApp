package com.example.CharityOrganizationSpringApp.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ParticipantDTO {

    @NotNull
    private Integer eventIdentifier;

    @NotNull
    private Integer clientIdentifier;

    @NotNull
    private Boolean participated;

    public Boolean getParticipated() {
        return participated;
    }

    public void setParticipated(Boolean participated) {
        this.participated = participated;
    }

    public Integer getEventIdentifier() {
        return eventIdentifier;
    }

    public void setEventIdentifier(Integer eventIdentifier) {
        this.eventIdentifier = eventIdentifier;
    }

    public Integer getClientIdentifier() {
        return clientIdentifier;
    }

    public void setClientIdentifier(Integer clientIdentifier) {
        this.clientIdentifier = clientIdentifier;
    }
}
