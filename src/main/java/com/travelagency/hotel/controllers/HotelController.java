package com.travelagency.hotel.controllers;

import com.travelagency.hotel.services.HotelService;
import com.travelagency.hotel.dtos.BookingRequest;
import com.travelagency.hotel.models.RoomType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/** endpoint for hotel-related activities **/
@RestController
@RequestMapping("/hotels")
public class HotelController {
    private final HotelService hotelService;
    
    @Autowired
    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }
    
    @GetMapping("/SearchHotel")
    public ResponseEntity<?> searchHotel(@RequestParam String name) {
        try {
            return ResponseEntity.ok(hotelService.searchHotelByName(name));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/ViewHotels")
    public ResponseEntity<?> getAllHotels() {
        return ResponseEntity.ok(hotelService.getAllHotels());
    }
    
    @PostMapping("/BookHotel")
    public ResponseEntity<?> bookRoom(@RequestBody BookingRequest request) {
        try {
            return ResponseEntity.ok(hotelService.bookRoom(
                request.getHotelName(), 
                request.getRoomType(), 
                request
            ));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @GetMapping("/user/bookings")
    public ResponseEntity<?> getUserBookings(@RequestParam String username) {
        try {
            return ResponseEntity.ok(hotelService.getUserBookings(username));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
} 