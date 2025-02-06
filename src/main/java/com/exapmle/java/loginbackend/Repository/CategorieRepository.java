package com.exapmle.java.loginbackend.Repository;

import com.exapmle.java.loginbackend.entities.Categorie;
import com.exapmle.java.loginbackend.entities.Evennement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface CategorieRepository extends JpaRepository<Categorie, Long> {
    @Override
    Optional<Categorie> findById(Long id);

    @Override
    void deleteAllById(Iterable<? extends Long> longs);

    @Override
    void delete(Categorie categorie);

    @Override
    <S extends Categorie> S save(S entity);


}
