package com.example.CharityOrganizationSpringApp.controllers;

import com.example.CharityOrganizationSpringApp.dto.EventDTO;
import com.example.CharityOrganizationSpringApp.dto.responses.EventsResponse;
import com.example.CharityOrganizationSpringApp.models.Donation;
import com.example.CharityOrganizationSpringApp.models.Event;
import com.example.CharityOrganizationSpringApp.services.EventService;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.CharityOrganizationSpringApp.util.ErrorsUtil.returnErrorsToClient;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;
    private final ModelMapper modelMapper;


    @GetMapping()
    public EventsResponse getAllEvents(@RequestParam("date") Optional<String> dateString) {
        List<Event> events;

        if(dateString.isPresent()) {
            LocalDate date = LocalDate.parse(dateString.get());
            events = eventService.findByDate(date);
        } else events = eventService.findAll();

        return new EventsResponse(events.stream()
                .map(this::convertToEventDTO)
                .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public EventDTO getEvent(@PathVariable("id") int id) {
        return convertToEventDTO(eventService.findById(id));
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("/{id}/participantsCount")
    public Integer getParticipantsCount(@PathVariable("id") int id) {
        Event event = eventService.findById(id);
        return event.getParticipants().size();
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("/{id}/donationsAmount")
    public BigDecimal getDonationsAmount(@PathVariable("id") int id) {
        Event event = eventService.findById(id);
        List<Donation> donations = event.getDonations();

        BigDecimal totalAmount = donations.stream()
                .map(Donation::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return totalAmount;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid EventDTO eventDTO,
                                             BindingResult bindingResult) {
        Event event = convertToEvent(eventDTO);

        if (bindingResult.hasErrors())
            returnErrorsToClient(bindingResult);

        eventService.save(event);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}/edit")
    public ResponseEntity<HttpStatus> update(@PathVariable("id") int id,
                                             @RequestBody @Valid EventDTO eventDTO){
        Event event = convertToEvent(eventDTO);

        eventService.update(id, event);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") int id){
        eventService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private Event convertToEvent(EventDTO eventDTO) {
        return modelMapper.map(eventDTO, Event.class);
    }

    private EventDTO convertToEventDTO(Event event) {
        return modelMapper.map(event, EventDTO.class);
    }
}
