package com.exapmle.java.loginbackend.controllers;
import com.exapmle.java.loginbackend.entities.Paiement;
import com.exapmle.java.loginbackend.services.PaiementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/paiements")
public class PaiementController {

    @Autowired
    private PaiementService paiementService;

     @PostMapping
    public ResponseEntity<Paiement> createPaiement(@RequestBody Paiement paiement) {
        Paiement savedPaiement = paiementService.savePaiement(paiement);
        return new ResponseEntity<>(savedPaiement, HttpStatus.CREATED);
    }

     @PostMapping("/batch")
    public ResponseEntity<List<Paiement>> createPaiements(@RequestBody List<Paiement> paiements) {
        List<Paiement> savedPaiements = paiementService.saveAllPaiements(paiements);
        return new ResponseEntity<>(savedPaiements, HttpStatus.CREATED);
    }

     @GetMapping
    public ResponseEntity<List<Paiement>> getAllPaiements() {
        List<Paiement> paiements = paiementService.findAllPaiements();
        return new ResponseEntity<>(paiements, HttpStatus.OK);
    }

     @GetMapping("/{id}")
    public ResponseEntity<Paiement> getPaiementById(@PathVariable Long id) {
        Paiement paiement = paiementService.findPaiementById(id);
        return new ResponseEntity<>(paiement, HttpStatus.OK);
    }

     @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaiement(@PathVariable Long id) {
        paiementService.deletePaiementById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

     @GetMapping("/count")
    public ResponseEntity<Long> countPaiements() {
        long count = paiementService.countPaiements();
        return new ResponseEntity<>(count, HttpStatus.OK);
    }
}