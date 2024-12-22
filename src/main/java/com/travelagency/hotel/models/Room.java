package com.travelagency.hotel.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Room {
    private RoomType roomType;
    private Double price;
    private Boolean isAvailable;
    
    @JsonIgnore  // Prevent infinite loop in JSON serialization
    private Booking currentBooking;
} 