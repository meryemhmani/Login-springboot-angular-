package com.exapmle.java.loginbackend.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Paiement")
@Getter
@Setter
public class Paiement implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;
    private Date Date_de_paiement;
    @OneToOne
    ReservationOuPanier reservation;

    @Enumerated(EnumType.STRING)
    @Column(name = "methode_de_paiement", nullable = false)
    private MethodeDePaiement methodeDePaiement;
    @ManyToOne
      private User user;
}

enum Status {
    EN_ATTENTE, CONFIRME, ANNULE
}

enum MethodeDePaiement {
    LIVRAISON, CARTE
}

