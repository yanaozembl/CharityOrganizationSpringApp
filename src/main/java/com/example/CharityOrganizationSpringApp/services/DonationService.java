package com.example.CharityOrganizationSpringApp.services;

import com.example.CharityOrganizationSpringApp.models.Donation;
import com.example.CharityOrganizationSpringApp.repositories.DonationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class DonationService {

    public final DonationsRepository donationsRepository;

    @Autowired
    public DonationService(DonationsRepository donationsRepository) {
        this.donationsRepository = donationsRepository;
    }

    public List<Donation> findAll() {
        return donationsRepository.findAll();
    }

    public Donation findOne(int id) {
        Optional<Donation> foundDonation = donationsRepository.findById(id);
        return foundDonation.orElse(null);
    }

    @Transactional
    public void save(Donation donation) {
        donationsRepository.save(donation);
    }

    @Transactional
    public void update(int id, Donation updatedDonation) {
        Donation donationToBeUpdated = donationsRepository.findById(id).get();
        // Произведите нужные изменения в updatedDonation
        // ...

        donationsRepository.save(updatedDonation);
    }

    @Transactional
    public void delete(int id) {
        donationsRepository.deleteById(id);
    }

}
