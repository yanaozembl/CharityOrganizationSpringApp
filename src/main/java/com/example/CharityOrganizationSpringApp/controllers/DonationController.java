package com.example.CharityOrganizationSpringApp.controllers;

import com.example.CharityOrganizationSpringApp.dto.DonationDTO;
import com.example.CharityOrganizationSpringApp.models.Donation;
import com.example.CharityOrganizationSpringApp.services.DonationService;
import com.example.CharityOrganizationSpringApp.services.EventService;
import com.example.CharityOrganizationSpringApp.services.UsersService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Currency;

import static com.example.CharityOrganizationSpringApp.util.ErrorsUtil.returnErrorsToClient;

@RestController
@RequestMapping("/donations")
@RequiredArgsConstructor
public class DonationController {

    private final DonationService donationService;
    private final ModelMapper modelMapper;
    private final UsersService usersService;
    private final EventService eventService;

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PostMapping
    public void becomeParticipant(@RequestBody @Valid DonationDTO donationDTO,
                                                        BindingResult bindingResult) {
        Donation donation = convertToDonation(donationDTO);

        if (bindingResult.hasErrors())
            returnErrorsToClient(bindingResult);

        donationService.save(donation);
    }

    private Donation convertToDonation(DonationDTO donationDTO) {
        Donation donation = new Donation();

        donation.setClient(usersService.findById(donationDTO.getClientIdentifier()));
        donation.setEvent(eventService.findById(donationDTO.getEventIdentifier()));

        donation.setAmount(donationDTO.getAmount());
        donation.setDate(LocalDate.now());

        return donation;
    }
}
