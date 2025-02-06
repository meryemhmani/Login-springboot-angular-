package com.exapmle.java.loginbackend.Repository;

import com.exapmle.java.loginbackend.entities.Paiement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface PaiementRepository extends JpaRepository<Paiement, Long> {
    @Override
    <S extends Paiement> S save(S entity);

    @Override
    <S extends Paiement> List<S> saveAll(Iterable<S> entities);

    @Override
    void deleteById(Long id);

    @Override
    Optional<Paiement> findById(Long aLong);

}
