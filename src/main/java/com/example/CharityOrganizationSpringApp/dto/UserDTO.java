package com.example.CharityOrganizationSpringApp.dto;

import javax.validation.constraints.Email;

public class UserDTO {

    @Email
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
