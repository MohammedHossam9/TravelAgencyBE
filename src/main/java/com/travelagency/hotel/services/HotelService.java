package com.travelagency.hotel.services;

import com.travelagency.hotel.models.*;
import com.travelagency.hotel.storage.HotelStorage;
import com.travelagency.user.storage.UserStorage;
import com.travelagency.hotel.dtos.BookingRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/** Handles hotel searches, room bookings, and hotel viewing operations **/

@Service
public class HotelService {
    private final HotelStorage hotelStorage;
    private final UserStorage userStorage;

    
    @Autowired
    public HotelService(HotelStorage hotelStorage, UserStorage userStorage ) {
        this.hotelStorage = hotelStorage;
        this.userStorage = userStorage;
    }
    
    public Hotel searchHotelByName(String name) {
        return hotelStorage.findByName(name)
            .orElseThrow(() -> new RuntimeException("Hotel not found with name: " + name));
    }
    
    public List<Hotel> getAllHotels() {
        return hotelStorage.findAll();
    }
    
    public Booking bookRoom(String hotelName, RoomType roomType, BookingRequest request) {
        // Validate user exists
        var user = userStorage.findByUsername(request.getUsername());
        if (user == null) {
            throw new RuntimeException("User not found: " + request.getUsername());
        }

        // Validate dates
        LocalDate now = LocalDate.now();
        if (request.getCheckIn().isBefore(now)) {
            throw new RuntimeException("Check-in date cannot be in the past");
        }
        if (request.getCheckIn().isAfter(request.getCheckOut())) {
            throw new RuntimeException("Check-in date must be before check-out date");
        }

        // Find hotel and available room
        Hotel hotel = searchHotelByName(hotelName);
        Room availableRoom = hotel.getRooms().stream()
            .filter(r -> r.getRoomType().equals(roomType) && r.getIsAvailable())
            .findFirst()
            .orElseThrow(() -> new RuntimeException("No available rooms of type " + roomType));
            
        // Calculate price
        long numberOfNights = java.time.temporal.ChronoUnit.DAYS.between(
            request.getCheckIn(), 
            request.getCheckOut()
        );
        double totalPrice = availableRoom.getPrice() * numberOfNights;
        
        // Create invoice
        Invoice invoice = new Invoice();
        invoice.setAmount(totalPrice);
        invoice.setDetails("Booking for " + hotelName + " - " + roomType);
        invoice.setStatus("PENDING");
        
        // Create booking
        Booking booking = new Booking();
        booking.setUser(user);
        booking.setRoom(availableRoom);
        booking.setInvoice(invoice);
        booking.setCheckIn(request.getCheckIn());
        booking.setCheckOut(request.getCheckOut());
        
        // Update room status
        availableRoom.setIsAvailable(false);
        availableRoom.setCurrentBooking(booking);
        
        // Add booking to user's booking list
        userStorage.addBookingToUser(user.getUsername(), booking);
        
        // Save updated hotel state
        hotelStorage.save(hotel);
        
        return booking;
    }

    // Add method to get user's bookings
    public List<Booking> getUserBookings(String username) {
        var user = userStorage.findByUsername(username);
        if (user == null) {
            throw new RuntimeException("User not found: " + username);
        }
        return user.getBookings();
    }
}