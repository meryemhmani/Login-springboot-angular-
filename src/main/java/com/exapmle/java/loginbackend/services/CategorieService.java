package com.exapmle.java.loginbackend.services;

import com.exapmle.java.loginbackend.Repository.CategorieRepository;
import com.exapmle.java.loginbackend.entities.Categorie;
import jdk.jfr.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategorieService {
    @Autowired
    private CategorieRepository categorieRepository;

    public Categorie save(Categorie categorie) {
        return categorieRepository.save(categorie);
    }
    public void delete(Long id) {
        categorieRepository.deleteById(id);
    }
    public Categorie updateCategorie(Long id, Categorie newCategorie) {
        return categorieRepository.findById(id)
                .map(categorie -> {
                    categorie.setNom(newCategorie.getNom()); // Modifier les attributs nécessaires
                    return categorieRepository.save(categorie);
                })
                .orElseThrow(() -> new RuntimeException("Catégorie non trouvée"));
    }
     public List<Categorie> findAll() {
        return categorieRepository.findAll();
    }
}
