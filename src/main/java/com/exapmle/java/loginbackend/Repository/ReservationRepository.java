package com.exapmle.java.loginbackend.Repository;

import com.exapmle.java.loginbackend.entities.ReservationOuPanier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ReservationRepository extends JpaRepository<ReservationOuPanier,Long> {
    @Override
    <S extends ReservationOuPanier> S save(S entity);

    List<ReservationOuPanier> findByUserId(Long userId);
    @Override
    void deleteById(Long aLong);

    @Override
    Optional<ReservationOuPanier> findById(Long aLong);

    @Override
    <S extends ReservationOuPanier> List<S> saveAll(Iterable<S> entities);
}
