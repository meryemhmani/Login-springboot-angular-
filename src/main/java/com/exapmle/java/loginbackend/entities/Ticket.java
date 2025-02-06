package com.exapmle.java.loginbackend.entities;
import com.exapmle.java.loginbackend.entities.Evennement;
import com.exapmle.java.loginbackend.entities.User;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tickets")
@Getter
@Setter
public class Ticket implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "date_event", nullable = false)
    private Date dateEvent;

    @Column(name = "quantite", nullable = false)
    private int quantite;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    private Evennement event;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "reservation_id")
    private ReservationOuPanier reservationOuPanier;


}