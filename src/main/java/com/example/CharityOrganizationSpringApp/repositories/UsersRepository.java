package com.example.CharityOrganizationSpringApp.repositories;

import com.example.CharityOrganizationSpringApp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String username);
}
