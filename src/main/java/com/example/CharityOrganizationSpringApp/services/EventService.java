package com.example.CharityOrganizationSpringApp.services;

import com.example.CharityOrganizationSpringApp.models.Event;
import com.example.CharityOrganizationSpringApp.repositories.EventsRepository;
import com.example.CharityOrganizationSpringApp.util.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class EventService {

    private final EventsRepository eventsRepository;
    private final UsersService usersService;

    public List<Event> findAll() {
        return eventsRepository.findAll();
    }

    public Event findById(int id) {
        Optional<Event> foundEvent = eventsRepository.findById(id);
        return foundEvent.orElseThrow(() -> new ObjectNotFoundException("This event not found"));
    }

    @Transactional
    public void save(Event event) {
        enrichEvent(event);
        eventsRepository.save(event);
    }

    @Transactional
    public void update(int id, Event updatedEvent) {
        updatedEvent.setId(id);

        enrichEvent(updatedEvent);

        eventsRepository.save(updatedEvent);
    }

    @Transactional
    public void delete(int id) {
        eventsRepository.deleteById(id);
    }

    public List<Event> findByDate(LocalDate searchDate) {
        return eventsRepository.findByDate(searchDate);
    }

    private void enrichEvent(Event event) {
        String organizerEmail = event.getOrganizer().getEmail();
        event.setOrganizer(usersService.findByEmail(organizerEmail)
                .orElseThrow(() -> new ObjectNotFoundException("This user not found")));
    }
}
