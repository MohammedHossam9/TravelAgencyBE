package com.travelagency.event.services;

import com.travelagency.event.models.*;
import com.travelagency.event.storage.EventStorage;
import com.travelagency.user.storage.UserStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class EventService {
    private final EventStorage eventStorage;
    private final UserStorage userStorage;

    @Autowired
    public EventService(EventStorage eventStorage, UserStorage userStorage) {
        this.eventStorage = eventStorage;
        this.userStorage = userStorage;
    }

    public List<Event> getAllEvents() {
        return eventStorage.findAll();
    }

    public List<Event> searchEventsByLocation(String location) {
        return eventStorage.findByLocation(location);
    }

    public Event searchEventsByName(String name) {
        Event event = eventStorage.findByName(name);
        if (event == null) {
            throw new RuntimeException("Event not found with name: " + name);
        }
        return event;
    }

    public Ticket bookTicket(String username, String eventName, int numberOfTickets) {
        // Verify user exists
        var user = userStorage.findByUsername(username);
        if (user == null) {
            throw new RuntimeException("User not found: " + username);
        }

        // Get event and verify tickets are available
        Event event = searchEventsByName(eventName);
        if (event.getAvailableTickets() < numberOfTickets) {
            throw new RuntimeException("Not enough tickets available");
        }

        // Calculate total price
        double totalPrice = event.getTicketPrice() * numberOfTickets;

        // Create invoice
        Invoice invoice = new Invoice();
        invoice.setInvoiceId(UUID.randomUUID().toString());
        invoice.setAmount(totalPrice);
        invoice.setDetails("Ticket purchase for " + event.getName());
        invoice.setStatus("PAID");

        // Create ticket
        Ticket ticket = new Ticket();
        ticket.setTicketId(UUID.randomUUID().toString());
        ticket.setUser(user);
        ticket.setEvent(event);
        ticket.setInvoice(invoice);

        // Update available tickets
        event.setAvailableTickets(event.getAvailableTickets() - numberOfTickets);
        eventStorage.save(event);

        // Add ticket to user's tickets
        user.getTickets().add(ticket);
        userStorage.save(user);

        return ticket;
    }

    public List<Ticket> getUserTickets(String username) {
        var user = userStorage.findByUsername(username);
        if (user == null) {
            throw new RuntimeException("User not found: " + username);
        }
        return user.getTickets();
    }
} 