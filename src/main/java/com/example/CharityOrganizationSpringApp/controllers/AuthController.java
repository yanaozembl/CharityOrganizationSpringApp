package com.example.CharityOrganizationSpringApp.controllers;

import com.example.CharityOrganizationSpringApp.dto.RegistrationDTO;
import com.example.CharityOrganizationSpringApp.dto.AuthenticationDTO;
import com.example.CharityOrganizationSpringApp.dto.TokenDTO;
import com.example.CharityOrganizationSpringApp.models.User;
import com.example.CharityOrganizationSpringApp.security.JWTUtil;
import com.example.CharityOrganizationSpringApp.services.RegistrationService;
import com.example.CharityOrganizationSpringApp.util.ErrorResponse;
import com.example.CharityOrganizationSpringApp.util.ObjectIsAlreadyUsedException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final RegistrationService registrationService;
    private final JWTUtil jwtUtil;
    private final ModelMapper modelMapper;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/registration")
    public TokenDTO performRegistration(@RequestBody @Valid RegistrationDTO registrationDTO,
                                                          BindingResult bindingResult) {
        User user = convertToUser(registrationDTO);

        if (bindingResult.hasErrors()) {
            return new TokenDTO(Map.of("message", "Error!"));
        }
        registrationService.register(user);

        String token = jwtUtil.generateToken(user.getEmail());
        return new TokenDTO(Map.of("jwt-token", token)); // sent token to client
    }

    @PostMapping("/login")
    public TokenDTO performLogin(@RequestBody AuthenticationDTO authenticationDTO) {
        UsernamePasswordAuthenticationToken authInputToken =  // standard class for encapsulation login and password
                new UsernamePasswordAuthenticationToken(authenticationDTO.getEmail(),
                        authenticationDTO.getPassword());

        try {
            authenticationManager.authenticate(authInputToken);
        } catch (BadCredentialsException e) {
            return new TokenDTO(Map.of("message", "Incorrect credentials!"));
        }

        String token = jwtUtil.generateToken(authenticationDTO.getEmail());
        return new TokenDTO(Map.of("jwt-token", token));
    }

    @PostMapping("/logout")
    public ResponseEntity logoutUser() {
        return ResponseEntity.ok("Successfully logged out");
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(ObjectIsAlreadyUsedException e) {
        ErrorResponse response = new ErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    public User convertToUser(RegistrationDTO registrationDTO) {
        return this.modelMapper.map(registrationDTO, User.class);
    }
}
