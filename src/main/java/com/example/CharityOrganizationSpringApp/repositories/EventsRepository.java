package com.example.CharityOrganizationSpringApp.repositories;

import com.example.CharityOrganizationSpringApp.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface EventsRepository extends JpaRepository<Event, Integer> {
    @Query("SELECT e FROM Event e WHERE DATE(e.dateTime) = :searchDate")
    List<Event> findByDate(@Param("searchDate") LocalDate searchDate);
}
