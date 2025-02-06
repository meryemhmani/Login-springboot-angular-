package com.exapmle.java.loginbackend.services;

import com.exapmle.java.loginbackend.Repository.ReservationRepository;
import com.exapmle.java.loginbackend.entities.ReservationOuPanier;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public ReservationOuPanier createReservation(ReservationOuPanier reservation) {
        return reservationRepository.save(reservation);
    }

    public List<ReservationOuPanier> createReservations(List<ReservationOuPanier> reservations) {
        return reservationRepository.saveAll(reservations);
    }

    public List<ReservationOuPanier> findAll() {
        return reservationRepository.findAll();
    }

    public ReservationOuPanier findById(Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Réservation non trouvée"));
    }

    public void deleteById(Long id) {
        reservationRepository.deleteById(id);
    }

    public List<ReservationOuPanier> findByUserId(Long userId) {
        return reservationRepository.findByUserId(userId);
    }

    public long count() {
        return reservationRepository.count();
    }
}