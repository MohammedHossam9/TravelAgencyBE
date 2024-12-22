package com.travelagency.hotel.dtos;

import com.travelagency.hotel.models.RoomType;
import lombok.Data;
import java.time.LocalDate;

@Data
public class BookingRequest {
    private String username;
    private String hotelName;
    private RoomType roomType;
    private LocalDate checkIn;
    private LocalDate checkOut;
} 