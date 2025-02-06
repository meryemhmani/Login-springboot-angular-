package com.exapmle.java.loginbackend.controllers;
import com.exapmle.java.loginbackend.entities.Ticket;
import com.exapmle.java.loginbackend.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

     @PostMapping
    public ResponseEntity<Ticket> createTicket(@RequestBody Ticket ticket) {
        Ticket savedTicket = ticketService.save(ticket);
        return new ResponseEntity<>(savedTicket, HttpStatus.CREATED);
    }

     @PostMapping("/create")
    public ResponseEntity<List<Ticket>> createTickets(@RequestBody List<Ticket> tickets) {
        List<Ticket> savedTickets = ticketService.saveAll(tickets);
        return new ResponseEntity<>(savedTickets, HttpStatus.CREATED);
    }

     @GetMapping("get-Ticket/{id}")
    public ResponseEntity<Ticket> getTicketById(@PathVariable Long id) {
        Optional<Ticket> ticket = ticketService.findById(id);
        return ticket.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

     @DeleteMapping("Delete-ticket/{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable Long id) {
        if (ticketService.existsById(id)) {
            ticketService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

     @GetMapping("/exists-Ticket/{id}")
    public ResponseEntity<Boolean> ticketExists(@PathVariable Long id) {
        boolean exists = ticketService.existsById(id);
        return new ResponseEntity<>(exists, HttpStatus.OK);
    }
}