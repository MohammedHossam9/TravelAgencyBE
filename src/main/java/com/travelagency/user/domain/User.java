package com.travelagency.user.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.travelagency.hotel.models.Booking;
import com.travelagency.event.models.Ticket;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String phone;
    private String username;
    private String email;
    @JsonIgnore
    private String password;
    @JsonIgnore
    private List<Booking> bookings = new ArrayList<>();
    @JsonIgnore
    private List<Ticket> tickets = new ArrayList<>();
} 