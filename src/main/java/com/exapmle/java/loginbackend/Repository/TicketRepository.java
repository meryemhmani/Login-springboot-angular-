package com.exapmle.java.loginbackend.Repository;

import com.exapmle.java.loginbackend.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    @Override
    Optional<Ticket> findById(Long aLong);

    @Override
    <S extends Ticket> List<S> saveAll(Iterable<S> entities);

    @Override
    <S extends Ticket> S save(S entity);

    @Override
    void deleteById(Long aLong);
}
