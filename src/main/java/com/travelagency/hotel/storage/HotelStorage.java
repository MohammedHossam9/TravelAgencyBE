package com.travelagency.hotel.storage;

import com.travelagency.hotel.models.Hotel;
import com.travelagency.hotel.models.Room;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

@Component
public class HotelStorage {
    private final Map<String, Hotel> hotels = new HashMap<>();
    
    public Hotel save(Hotel hotel) {
        hotels.put(hotel.getName(), hotel);
        return hotel;
    }
    
    public Optional<Hotel> findByName(String name) {
        return Optional.ofNullable(hotels.get(name));
    }
    
    public List<Hotel> findAll() {
        return new ArrayList<>(hotels.values());
    }
    
    public void saveAll(List<Hotel> hotelList) {
        hotelList.forEach(hotel -> hotels.put(hotel.getName(), hotel));
    }
} 