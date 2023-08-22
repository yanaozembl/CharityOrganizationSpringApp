package com.example.CharityOrganizationSpringApp.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "Users")
@Setter
@Getter
@NoArgsConstructor
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Email
    @Column(name = "email")
    private String email;

    @Size(min = 2, max = 30, message = "First name should be between 2 and 30 characters")
    @Column(name = "first_name")
    private String firstName;

    @Size(min = 2, max = 30, message = "Last name should be between 2 and 30 characters")
    @Column(name = "last_name")
    private String lastName;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

/*
    @OneToMany(mappedBy = "client")
    private List<Donation> donations;
*/
/*

    @OneToMany(mappedBy = "client")
    private List<Participant> participants;
*/

    public User(String firstName, String lastName, String password, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.role = role;
    }
}
