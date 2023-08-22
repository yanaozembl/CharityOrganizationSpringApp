package com.example.CharityOrganizationSpringApp.util;

import com.example.CharityOrganizationSpringApp.models.User;
import com.example.CharityOrganizationSpringApp.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    private final UsersService usersService;

    @Autowired
    public UserValidator(UsersService usersService) {
        this.usersService = usersService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;

        if (usersService.findByEmail(user.getEmail()).isPresent()) {
            errors.rejectValue("email", "", "This email is already taken");
        }
    }
}
