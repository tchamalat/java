package airlinereservation;

import java.io.Serializable;

public abstract class Personne implements Serializable {
    private String identifiant;
    private String nom;
    private String adresse;
    private String contact;

    public Personne(String identifiant, String nom, String adresse, String contact) {
        this.identifiant = identifiant;
        this.nom = nom;
        this.adresse = adresse;
        this.contact = contact;
    }

    // Getters et Setters
    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    // Méthode demandée dans le diagramme
    public String obtenirInfos() {
        return "Identifiant: " + identifiant +
                "\nNom: " + nom +
                "\nAdresse: " + adresse +
                "\nContact: " + contact;
    }

    @Override
    public String toString() {
        return identifiant + "," + nom + "," + adresse + "," + contact;
    }
}
