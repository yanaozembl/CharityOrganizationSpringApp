package com.example.CharityOrganizationSpringApp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

@Setter
@Getter
public class AuthenticationDTO {

    @Size(min = 2, max = 30, message = "Email should be between 2 and 30 characters")
    private String email;

    private String password;
}
