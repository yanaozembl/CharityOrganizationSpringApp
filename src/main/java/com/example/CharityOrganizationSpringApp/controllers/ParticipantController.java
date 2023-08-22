package com.example.CharityOrganizationSpringApp.controllers;

import com.example.CharityOrganizationSpringApp.dto.ParticipantDTO;
import com.example.CharityOrganizationSpringApp.models.Event;
import com.example.CharityOrganizationSpringApp.models.Participant;
import com.example.CharityOrganizationSpringApp.models.User;
import com.example.CharityOrganizationSpringApp.services.EventService;
import com.example.CharityOrganizationSpringApp.services.ParticipantService;
import com.example.CharityOrganizationSpringApp.services.UsersService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.example.CharityOrganizationSpringApp.util.ErrorsUtil.returnErrorsToClient;

@RestController
@RequestMapping("/participants")
@RequiredArgsConstructor
public class ParticipantController {

    private final ModelMapper modelMapper;
    private final ParticipantService participantService;
    private final UsersService usersService;
    private final EventService eventService;

    @PreAuthorize("hasRole('USER')")
    @PostMapping
    public ResponseEntity<HttpStatus> becomeParticipant(@RequestBody @Valid ParticipantDTO participantDTO,
                                             BindingResult bindingResult) {
        Participant participant = convertToParticipant(participantDTO);

        if (bindingResult.hasErrors())
            returnErrorsToClient(bindingResult);

        participantService.save(participant);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private Participant convertToParticipant(ParticipantDTO participantDTO) {
        Participant participant = new Participant();

        Event event = eventService.findById(participantDTO.getEventIdentifier());
        User client = usersService.findById(participantDTO.getClientIdentifier());

        participant.setEvent(event);
        participant.setClient(client);
        participant.setParticipated(participantDTO.getParticipated());

        return participant;
    }
}
