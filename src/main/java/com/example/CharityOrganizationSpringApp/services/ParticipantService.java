package com.example.CharityOrganizationSpringApp.services;

import com.example.CharityOrganizationSpringApp.models.Participant;
import com.example.CharityOrganizationSpringApp.repositories.ParticipantsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ParticipantService {

    public final ParticipantsRepository participantsRepository;

    @Autowired
    public ParticipantService(ParticipantsRepository participantsRepository) {
        this.participantsRepository = participantsRepository;
    }

    public List<Participant> findAll() {
        return participantsRepository.findAll();
    }

    public Participant findOne(int id) {
        Optional<Participant> foundParticipant = participantsRepository.findById(id);
        return foundParticipant.orElse(null);
    }

    @Transactional
    public void save(Participant participant) {
        participantsRepository.save(participant);
    }

    @Transactional
    public void update(int id, Participant updatedParticipant) {
        Participant participantToBeUpdated = participantsRepository.findById(id).get();
        // Произведите нужные изменения в updatedParticipant
        // ...

        participantsRepository.save(updatedParticipant);
    }

    @Transactional
    public void delete(int id) {
        participantsRepository.deleteById(id);
    }

}
