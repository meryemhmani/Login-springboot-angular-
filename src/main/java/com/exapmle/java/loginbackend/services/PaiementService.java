package com.exapmle.java.loginbackend.services;

import com.exapmle.java.loginbackend.Repository.PaiementRepository;
import com.exapmle.java.loginbackend.entities.Paiement;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaiementService {

    @Autowired
    private final PaiementRepository paiementRepository;

    public PaiementService(PaiementRepository paiementRepository) {
        this.paiementRepository = paiementRepository;
    }

    @Transactional
    public Paiement savePaiement(Paiement paiement) {
        return paiementRepository.save(paiement);
    }

    @Transactional
    public List<Paiement> saveAllPaiements(List<Paiement> paiements) {
        return paiementRepository.saveAll(paiements);
    }

    public List<Paiement> findAllPaiements() {
        return paiementRepository.findAll();
    }

    public Paiement findPaiementById(Long id) {
        return paiementRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Paiement non trouvé avec l'ID: " + id));
    }

    @Transactional
    public void deletePaiementById(Long id) {
        if (!paiementRepository.existsById(id)) {
            throw new EntityNotFoundException("Impossible de supprimer, paiement non trouvé avec l'ID: " + id);
        }
        paiementRepository.deleteById(id);
    }

    public long countPaiements() {
        return paiementRepository.count();
    }
}
