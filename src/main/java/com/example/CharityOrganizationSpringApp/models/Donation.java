package com.example.CharityOrganizationSpringApp.models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "Donations")
public class Donation {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "amount")
    @DecimalMin(value = "0.0")
    private BigDecimal amount;

    @Column(name = "date")
    @NotNull
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "event_id", referencedColumnName = "id")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private User client;

    public Donation() {}

    public Donation(BigDecimal amount, LocalDate date/*, Event event, User client*/) {
        this.amount = amount;
        this.date = date;
//        this.event = event;
//        this.client = client;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }
}
