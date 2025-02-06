package com.exapmle.java.loginbackend.entities;

 import com.exapmle.java.loginbackend.entities.User;
import jakarta.persistence.*;
 import lombok.*;

 import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reservations")
public class ReservationOuPanier implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "date_res", nullable = false)
    private Date dateRes;

    @Column(name = "heure_res", nullable = false)
    private String heureRes;

    @Column(name = "montant_total", nullable = false)
    private Float montantTotal;


    @OneToMany(mappedBy = "reservationOuPanier", cascade = CascadeType.ALL)
    private List<Ticket> tickets;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}