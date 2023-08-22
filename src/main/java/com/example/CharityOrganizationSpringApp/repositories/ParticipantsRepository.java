package com.example.CharityOrganizationSpringApp.repositories;

import com.example.CharityOrganizationSpringApp.models.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipantsRepository extends JpaRepository<Participant, Integer> {
}
