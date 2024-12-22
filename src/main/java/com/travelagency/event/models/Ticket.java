package com.travelagency.event.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.travelagency.user.domain.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
    private String ticketId;
    @JsonIgnoreProperties({"bookings", "password", "tickets"})
    private User user;
    private Event event;
    private Invoice invoice;
} 