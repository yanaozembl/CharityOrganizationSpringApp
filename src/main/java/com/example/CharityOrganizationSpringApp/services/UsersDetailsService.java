package com.example.CharityOrganizationSpringApp.services;

import com.example.CharityOrganizationSpringApp.models.User;
import com.example.CharityOrganizationSpringApp.repositories.UsersRepository;
import com.example.CharityOrganizationSpringApp.security.UsersDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsersDetailsService implements UserDetailsService {

    private final UsersRepository usersRepository;

    @Autowired
    public UsersDetailsService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = usersRepository.findByEmail(username);

        if(user.isEmpty())
            throw new UsernameNotFoundException("User not found");

        return new UsersDetails(user.get());
    }
}
