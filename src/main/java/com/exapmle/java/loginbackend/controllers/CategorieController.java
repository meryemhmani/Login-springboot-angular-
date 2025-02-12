package com.exapmle.java.loginbackend.controllers;

import com.exapmle.java.loginbackend.entities.Categorie;
import com.exapmle.java.loginbackend.services.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
 import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategorieController {

    @Autowired
    private CategorieService categorieService;

    @PostMapping
     public ResponseEntity<Categorie> createCategorie(@RequestBody Categorie categorie) {
        Categorie savedCategorie = categorieService.save(categorie);
        return new ResponseEntity<>(savedCategorie, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
     public ResponseEntity<Void> deleteCategorie(@PathVariable Long id) {
        categorieService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
     public ResponseEntity<Categorie> updateCategorie(@PathVariable Long id, @RequestBody Categorie newCategorie) {
        Categorie updatedCategorie = categorieService.updateCategorie(id, newCategorie);
        return new ResponseEntity<>(updatedCategorie, HttpStatus.OK);
    }

    @GetMapping

    public ResponseEntity<List<Categorie>> getAllCategories() {
        List<Categorie> categories = categorieService.findAll();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }
}