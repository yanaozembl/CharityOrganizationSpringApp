package com.example.CharityOrganizationSpringApp.dto;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.Date;

public class DonationDTO {

    @DecimalMin(value = "0.0")
    private BigDecimal amount;

    @NotNull
    private Integer eventIdentifier;

    @NotNull
    private Integer clientIdentifier;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
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
