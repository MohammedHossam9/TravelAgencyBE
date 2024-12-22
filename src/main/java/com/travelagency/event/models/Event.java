package com.travelagency.event.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Event {
    private String name;
    private LocalDate date;
    private String location;
    private int totalTickets;
    private int availableTickets;
    private double ticketPrice;
} 