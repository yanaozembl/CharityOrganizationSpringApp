package com.example.CharityOrganizationSpringApp.controllers;

import com.example.CharityOrganizationSpringApp.dto.RegistrationDTO;
import com.example.CharityOrganizationSpringApp.dto.UserDTO;
import com.example.CharityOrganizationSpringApp.dto.AuthenticationDTO;
import com.example.CharityOrganizationSpringApp.models.User;
import com.example.CharityOrganizationSpringApp.security.JWTUtil;
import com.example.CharityOrganizationSpringApp.services.RegistrationService;
import com.example.CharityOrganizationSpringApp.util.UserValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserValidator userValidator;
    private final RegistrationService registrationService;
    private final JWTUtil jwtUtil;
    private final ModelMapper modelMapper;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthController(UserValidator userValidator,
                          RegistrationService registrationService,
                          JWTUtil jwtUtil,
                          ModelMapper modelMapper,
                          AuthenticationManager authenticationManager) {
        this.userValidator = userValidator;
        this.registrationService = registrationService;
        this.jwtUtil = jwtUtil;
        this.modelMapper = modelMapper;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/registration")
    public Map<String, String> performRegistration(@RequestBody @Valid RegistrationDTO registrationDTO,
                                                          BindingResult bindingResult) {
        User user = convertToUser(registrationDTO);

        userValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors()) {
            return Map.of("message", "Error!");
        }
        registrationService.register(user);

        String token = jwtUtil.generateToken(user.getEmail());
        return Map.of("jwt-token", token); // sent token to client
    }

    @PostMapping("/login")
    public Map<String, String> performLogin(@RequestBody AuthenticationDTO authenticationDTO) {
        UsernamePasswordAuthenticationToken authInputToken =  // standard class for encapsulation login and password
                new UsernamePasswordAuthenticationToken(authenticationDTO.getEmail(),
                        authenticationDTO.getPassword());

        // check whether credentials are correct
        try {
            authenticationManager.authenticate(authInputToken);
        } catch (BadCredentialsException e) {
            return Map.of("message", "Incorrect credentials!");
        }

        String token = jwtUtil.generateToken(authenticationDTO.getEmail());
        return Map.of("jwt-token", token);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logoutUser() {
        return ResponseEntity.ok("Successfully logged out");
    }

    public User convertToUser(RegistrationDTO registrationDTO) {
        return this.modelMapper.map(registrationDTO, User.class);
    }
}
