package com.travelagency.event.storage;

import com.travelagency.event.models.Event;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Component
public class EventStorage {
    private final Map<String, Event> events = new HashMap<>();
    
    public Event save(Event event) {
        events.put(event.getName(), event);
        return event;
    }
    
    public List<Event> findAll() {
        return new ArrayList<>(events.values());
    }
    
    public Event findByName(String name) {
        return events.get(name);
    }
    
    public List<Event> findByLocation(String location) {
        return events.values().stream()
            .filter(event -> event.getLocation().equalsIgnoreCase(location))
            .collect(Collectors.toList());
    }
} 