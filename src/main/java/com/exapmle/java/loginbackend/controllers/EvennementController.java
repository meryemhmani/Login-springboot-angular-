package com.exapmle.java.loginbackend.controllers;

import com.exapmle.java.loginbackend.entities.Evennement;
import com.exapmle.java.loginbackend.services.EvennementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/evennements")
public class EvennementController {

    @Autowired
    private EvennementService evennementService;

     @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Evennement> createEvennement(@RequestBody Evennement evennement) {
        Evennement savedEvennement = evennementService.save(evennement);
        return new ResponseEntity<>(savedEvennement, HttpStatus.CREATED);
    }

     @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Evennement> updateEvennement(@PathVariable Long id, @RequestBody Evennement newEvennement) {
        Evennement updatedEvennement = evennementService.updateEvennement(id, newEvennement);
        return new ResponseEntity<>(updatedEvennement, HttpStatus.OK);
    }

     @DeleteMapping("/{id}/delete_event")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteEvennement(@PathVariable Long id) {
        evennementService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

     @GetMapping("/all-events")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<List<Evennement>> getAllEvennements() {
        List<Evennement> evennements = evennementService.findAll();
        return new ResponseEntity<>(evennements, HttpStatus.OK);
    }

     @GetMapping("/{id}/event")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<Evennement> getEvennementById(@PathVariable Long id) {
        Evennement evennement = evennementService.findById(id)
                .orElseThrow(() -> new RuntimeException("Événement non trouvé"));
        return new ResponseEntity<>(evennement, HttpStatus.OK);
    }
}