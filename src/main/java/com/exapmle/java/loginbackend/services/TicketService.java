package com.exapmle.java.loginbackend.services;

import com.exapmle.java.loginbackend.Repository.TicketRepository;
import com.exapmle.java.loginbackend.entities.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

     public Ticket save(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

     public <S extends Ticket> List<S> saveAll(Iterable<S> tickets) {
        return ticketRepository.saveAll(tickets);
    }

     public Optional<Ticket> findById(Long id) {
        return ticketRepository.findById(id);
    }

     public void deleteById(Long id) {
        if (ticketRepository.existsById(id)) {
            ticketRepository.deleteById(id);
        } else {
            throw new RuntimeException("Ticket introuvable");
        }
    }

     public boolean existsById(Long id) {
        return ticketRepository.existsById(id);
    }
}







