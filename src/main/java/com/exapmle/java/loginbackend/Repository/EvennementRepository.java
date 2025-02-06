package com.exapmle.java.loginbackend.Repository;

import com.exapmle.java.loginbackend.entities.Evennement;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Repository
public interface EvennementRepository extends JpaRepository<Evennement, Long> {
    @Override
    <S extends Evennement> S save(S entity);

    @Override
    Optional<Evennement> findById(Long aLong);

    @Override
    void deleteById(Long aLong);
     List<Evennement> findAll();
    List<Evennement> findByDateBefore(Date date);
    List<Evennement> findByLieu(String lieu);

    List<Evennement> findByDate(Date date);

    List<Evennement> findByNomContainingIgnoreCase(String nom);

    @Query("SELECT e FROM Evennement e WHERE e.date = :date AND e.lieu = :lieu")
    List<Evennement> findByDateAndLieu(Date date, String lieu);
}
