package com.example.CharityOrganizationSpringApp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Setter
@Getter
public class RegistrationDTO {

    @Email
    private String email;

    @Size(min = 2, max = 30, message = "First name should be between 2 and 30 characters")
    private String firstName;

    @Size(min = 2, max = 30, message = "Last name should be between 2 and 30 characters")
    private String lastName;

    private String password;
}
