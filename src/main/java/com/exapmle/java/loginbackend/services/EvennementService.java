package com.exapmle.java.loginbackend.services;

import com.exapmle.java.loginbackend.Repository.EvennementRepository;
import com.exapmle.java.loginbackend.Repository.ReservationRepository;
import com.exapmle.java.loginbackend.entities.Evennement;
import com.exapmle.java.loginbackend.entities.ReservationOuPanier;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class EvennementService {



    @Autowired
    private EvennementRepository evennementRepository;

    @Autowired
    private ReservationRepository reservationRepository;
     public Evennement save(Evennement evennement) {
        return evennementRepository.save(evennement);
    }

     public Evennement updateEvennement(Long id, Evennement newEvennement) {
        return evennementRepository.findById(id)
                .map(evennement -> {
                    evennement.setDate(newEvennement.getDate());
                    evennement.setPrix(newEvennement.getPrix());
                    evennement.setLieu(newEvennement.getLieu());
                    evennement.setTypedeplace(newEvennement.getTypedeplace());
                    evennement.setUsers(newEvennement.getUsers());
                    return evennementRepository.save(evennement);
                })
                .orElseThrow(() -> new RuntimeException("Événement non trouvé"));
    }

     public void deleteById(Long id) {
        if (evennementRepository.existsById(id)) {
            evennementRepository.deleteById(id);
        } else {
            throw new RuntimeException("Événement introuvable");
        }
    }

     public Optional<Evennement> findById(Long id) {
        return evennementRepository.findById(id);
    }

     public List<Evennement> findAll() {
        return evennementRepository.findAll();
    }
    public ReservationOuPanier creatReservation(ReservationOuPanier reservation) {
        return reservationRepository.save(reservation);
    }



    public List<ReservationOuPanier> findByUserId(Long userId) {
        return reservationRepository.findByUserId(userId);
    }
    @Transactional
    public void deletePastEvents() {
        Date currentDate = new Date();
        List<Evennement> pastEvents = evennementRepository.findByDateBefore(currentDate);
        evennementRepository.deleteAll(pastEvents);
    }
     public boolean existsById(Long id) {
        return evennementRepository.existsById(id);
    }


     public long count() {
        return evennementRepository.count();
    }

     public List<Evennement> findByLieu(String lieu) {
        return evennementRepository.findByLieu(lieu);
    }

     public List<Evennement> FindByDate(Date date) {
        return evennementRepository.findByDate(date);
    }

}
