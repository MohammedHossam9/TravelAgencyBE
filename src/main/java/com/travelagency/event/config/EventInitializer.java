package com.travelagency.event.config;

import com.travelagency.event.models.Event;
import com.travelagency.event.storage.EventStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.time.LocalDate;

@Component
public class EventInitializer implements CommandLineRunner {
    private final EventStorage eventStorage;

    @Autowired
    public EventInitializer(EventStorage eventStorage) {
        this.eventStorage = eventStorage;
    }

    @Override
    public void run(String... args) {
        // Add some sample events
        createEvent("Cairo Jazz Festival", 
            LocalDate.now().plusDays(30), 
            "Cairo", 1000, 1000, 50.0);
            
        createEvent("Alexandria Film Festival", 
            LocalDate.now().plusDays(45), 
            "Alexandria", 500, 500, 75.0);
            
        createEvent("Luxor Cultural Event", 
            LocalDate.now().plusDays(60), 
            "Luxor", 300, 300, 100.0);
    }

    private void createEvent(String name, LocalDate date, String location, 
            int total, int available, double price) {
        Event event = new Event(name, date, location, total, available, price);
        eventStorage.save(event);
    }
} 