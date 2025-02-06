package com.exapmle.java.loginbackend.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class Evennement implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private Date date;
    @Column(nullable = false)
    private Float prix;
    @Column(nullable = false)
    private String lieu;
    @Column(nullable = false)
    private Type typedeplace;
    @ManyToOne
      @JoinColumn(name = "categorie_id",nullable = false)
    private Categorie categorie;
    @ManyToMany
    List<User> users;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Float getPrix() {
        return prix;
    }

    public void setPrix(Float prix) {
        this.prix = prix;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public Type getTypedeplace() {
        return typedeplace;
    }

    public void setTypedeplace(Type typedeplace) {
        this.typedeplace = typedeplace;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

}
