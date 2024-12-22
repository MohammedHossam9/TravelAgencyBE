package com.travelagency.user.storage;

import com.travelagency.user.domain.User;
import com.travelagency.hotel.models.Booking;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

@Component
public class UserStorage {
    private final Map<String, User> users = new HashMap<>();
    
    public User save(User user) {
        users.put(user.getUsername(), user);
        return user;
    }
    
    public User findByUsername(String username) {
        return users.get(username);
    }
    
    public boolean existsByUsername(String username) {
        return users.containsKey(username);
    }
    
    public boolean existsByEmail(String email) {
        return users.values().stream()
                .anyMatch(user -> email.equals(user.getEmail()));
    }

    public void addBookingToUser(String username, Booking booking) {
        User user = users.get(username);
        if (user != null) {
            user.getBookings().add(booking);
            users.put(username, user);
        }
    }
} 