package com.exapmle.java.loginbackend.controllers;

import com.exapmle.java.loginbackend.entities.ReservationOuPanier;
import com.exapmle.java.loginbackend.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

     @PostMapping
    public ResponseEntity<ReservationOuPanier> createReservation(@RequestBody ReservationOuPanier reservation) {
        ReservationOuPanier savedReservation = reservationService.createReservation(reservation);
        return new ResponseEntity<>(savedReservation, HttpStatus.CREATED);
    }

     @PostMapping("/batch")
    public ResponseEntity<List<ReservationOuPanier>> createReservations(@RequestBody List<ReservationOuPanier> reservations) {
        List<ReservationOuPanier> savedReservations = reservationService.createReservations(reservations);
        return new ResponseEntity<>(savedReservations, HttpStatus.CREATED);
    }

     @GetMapping
    public ResponseEntity<List<ReservationOuPanier>> getAllReservations() {
        List<ReservationOuPanier> reservations = reservationService.findAll();
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

     @GetMapping("/{id}")
    public ResponseEntity<ReservationOuPanier> getReservationById(@PathVariable Long id) {
        ReservationOuPanier reservation = reservationService.findById(id);
        return new ResponseEntity<>(reservation, HttpStatus.OK);
    }

     @DeleteMapping("/{id}")
     public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
        reservationService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

     @GetMapping("/user/{userId}")
     public ResponseEntity<List<ReservationOuPanier>> getReservationsByUser(@PathVariable Long userId) {
        List<ReservationOuPanier> reservations = reservationService.findByUserId(userId);
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @GetMapping("/count")
     public ResponseEntity<Long> countReservations() {
        long count = reservationService.count();
        return new ResponseEntity<>(count, HttpStatus.OK);
    }
}