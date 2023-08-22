package com.example.CharityOrganizationSpringApp.services;

import com.example.CharityOrganizationSpringApp.models.Participant;
import com.example.CharityOrganizationSpringApp.repositories.ParticipantsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ParticipantService {

    public final ParticipantsRepository participantsRepository;

    @Transactional
    public void save(Participant participant) {
        participantsRepository.save(participant);
    }
}
