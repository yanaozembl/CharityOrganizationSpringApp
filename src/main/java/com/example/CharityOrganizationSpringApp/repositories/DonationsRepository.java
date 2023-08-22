package com.example.CharityOrganizationSpringApp.repositories;

import com.example.CharityOrganizationSpringApp.models.Donation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface DonationsRepository extends JpaRepository<Donation, Integer> {

}
