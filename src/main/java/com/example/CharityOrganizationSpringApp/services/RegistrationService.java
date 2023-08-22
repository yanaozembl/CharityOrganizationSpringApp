package com.example.CharityOrganizationSpringApp.services;

import com.example.CharityOrganizationSpringApp.models.Role;
import com.example.CharityOrganizationSpringApp.models.User;
import com.example.CharityOrganizationSpringApp.repositories.UsersRepository;
import com.example.CharityOrganizationSpringApp.util.ObjectIsAlreadyUsedException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final UsersService usersService;

    @Value("${admin_email}")
    private String adminEmail;

    @Value("${admin_password}")
    private String adminPassword;

    @Transactional
    public void register(User user) {
        String email = user.getEmail();
        String password = user.getPassword();

        if (usersService.findByEmail(email).isPresent()) {
            throw new ObjectIsAlreadyUsedException("This email is already taken");
        }

        if (Objects.equals(user.getEmail(), adminEmail) && Objects.equals(password, adminPassword))
            user.setRole(Role.ROLE_ADMIN);
        else {
            user.setRole(Role.ROLE_USER);
        }

        user.setPassword(passwordEncoder.encode(password));

        usersRepository.save(user);
    }
}
