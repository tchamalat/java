package airlinereservation;

import java.io.Serializable;
import java.time.LocalDate;

public abstract class Employe extends Personne implements Serializable {
    private String numeroEmploye;
    private LocalDate dateEmbauche;

    public Employe(String identifiant, String nom, String adresse, String contact,
                   String numeroEmploye, LocalDate dateEmbauche) {
        super(identifiant, nom, adresse, contact);
        this.numeroEmploye = numeroEmploye;
        this.dateEmbauche = dateEmbauche;
    }

    // Getters et Setters
    public String getNumeroEmploye() {
        return numeroEmploye;
    }

    public void setNumeroEmploye(String numeroEmploye) {
        this.numeroEmploye = numeroEmploye;
    }

    public LocalDate getDateEmbauche() {
        return dateEmbauche;
    }

    public void setDateEmbauche(LocalDate dateEmbauche) {
        this.dateEmbauche = dateEmbauche;
    }

    // Méthode demandée dans le diagramme
    public abstract String obtenirRole();

    @Override
    public String obtenirInfos() {
        return super.obtenirInfos() +
                "\nNuméro Employé: " + numeroEmploye +
                "\nDate d'Embauche: " + dateEmbauche;
    }

    @Override
    public String toString() {
        return super.toString() + "," + numeroEmploye + "," + dateEmbauche;
    }
}
