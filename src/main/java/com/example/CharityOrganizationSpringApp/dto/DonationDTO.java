package com.example.CharityOrganizationSpringApp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Setter
@Getter
public class DonationDTO {

    @DecimalMin(value = "0.0")
    private BigDecimal amount;

    @NotNull
    private Integer eventIdentifier;

    @NotNull
    private Integer clientIdentifier;
}
