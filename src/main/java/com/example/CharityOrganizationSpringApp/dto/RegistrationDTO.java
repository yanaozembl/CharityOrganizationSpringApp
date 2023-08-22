package com.example.CharityOrganizationSpringApp.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

public class RegistrationDTO {

    @Email
    private String email;

    @Size(min = 2, max = 30, message = "First name should be between 2 and 30 characters")
    private String firstName;

    @Size(min = 2, max = 30, message = "Last name should be between 2 and 30 characters")
    private String lastName;

    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
