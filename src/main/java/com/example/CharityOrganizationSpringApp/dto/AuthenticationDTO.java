package com.example.CharityOrganizationSpringApp.dto;

import javax.validation.constraints.Size;

public class AuthenticationDTO {

    @Size(min = 2, max = 30, message = "Email should be between 2 and 30 characters")
    private String email;

    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
