package com.example.CharityOrganizationSpringApp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;

@Setter
@Getter
public class UserDTO {

    @Email
    private String email;
}
