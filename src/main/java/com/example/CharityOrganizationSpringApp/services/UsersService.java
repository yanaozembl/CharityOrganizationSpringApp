package com.example.CharityOrganizationSpringApp.services;

import com.example.CharityOrganizationSpringApp.models.User;
import com.example.CharityOrganizationSpringApp.repositories.UsersRepository;
import com.example.CharityOrganizationSpringApp.util.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true) //все публ методы автомат transactional(readonly = true)
public class UsersService {

    public final UsersRepository usersRepository;

    @Autowired
    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public User findById(int id) {
        Optional<User> foundUser = usersRepository.findById(id);
        return foundUser.orElseThrow(() -> new ObjectNotFoundException("This user not found"));
    }

    public Optional<User> findByEmail(String email) {
        return usersRepository.findByEmail(email);
    }
}
