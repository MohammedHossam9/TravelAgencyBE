package com.travelagency.event.controllers;

import com.travelagency.event.models.Event;
import com.travelagency.event.models.Ticket;
import com.travelagency.event.dtos.SearchRequest;
import com.travelagency.event.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/events")
public class EventController {
    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllEvents() {
        return ResponseEntity.ok(eventService.getAllEvents());
    }

    @GetMapping("/search/location")
    public ResponseEntity<?> searchByLocation(@RequestBody SearchRequest request) {
        return ResponseEntity.ok(eventService.searchEventsByLocation(request.getSearchTerm()));
    }

    @GetMapping("/search/name")
    public ResponseEntity<?> searchByName(@RequestBody SearchRequest request) {
        return ResponseEntity.ok(eventService.searchEventsByName(request.getSearchTerm()));
    }

    @PostMapping("/{eventName}/book")
    public ResponseEntity<?> bookTicket(
            @PathVariable String eventName,
            @RequestParam String username,
            @RequestParam int numberOfTickets) {
        try {
            Ticket ticket = eventService.bookTicket(username, eventName, numberOfTickets);
            return ResponseEntity.ok(ticket);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/user/{username}/tickets")
    public ResponseEntity<?> getUserTickets(@PathVariable String username) {
        try {
            return ResponseEntity.ok(eventService.getUserTickets(username));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
} 