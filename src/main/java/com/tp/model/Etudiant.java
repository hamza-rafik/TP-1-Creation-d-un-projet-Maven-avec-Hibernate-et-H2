package com.tp.model;

import jakarta.persistence.*;

/**
 * Entité JPA représentant un Étudiant.
 */
@Entity
@Table(name = "etudiants")
public class Etudiant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom", nullable = false, length = 50)
    private String nom;

    @Column(name = "prenom", nullable = false, length = 50)
    private String prenom;

    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "filiere", length = 50)
    private String filiere;

    // Constructeur sans argument (requis par la spécification JPA/Hibernate)
    public Etudiant() {
    }

    // Constructeur avec paramètres
    public Etudiant(String nom, String prenom, String email, String filiere) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.filiere = filiere;
    }

    // Getters et Setters
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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFiliere() {
        return filiere;
    }

    public void setFiliere(String filiere) {
        this.filiere = filiere;
    }

    @Override
    public String toString() {
        return String.format("Etudiant[ID=%d, Nom=%s %s, Email=%s, Filière=%s]",
                id, prenom, nom, email, filiere);
    }
}
