package com.example.CharityOrganizationSpringApp.services;

import com.example.CharityOrganizationSpringApp.models.Role;
import com.example.CharityOrganizationSpringApp.models.User;
import com.example.CharityOrganizationSpringApp.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
public class RegistrationService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${admin_email}")
    private String adminEmail;

    @Value("${admin_password}")
    private String adminPassword;

    @Autowired
    public RegistrationService(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void register(User user) {
        String userPassword = user.getPassword();

        if (Objects.equals(user.getEmail(), adminEmail) && Objects.equals(userPassword, adminPassword))
            user.setRole(Role.ROLE_ADMIN);
        else {
            user.setRole(Role.ROLE_USER);
        }

        user.setPassword(passwordEncoder.encode(userPassword));

        usersRepository.save(user);
    }
}
