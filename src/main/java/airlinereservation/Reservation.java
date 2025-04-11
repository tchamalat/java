package airlinereservation;

import java.io.Serializable;
import java.time.LocalDate;

public class Reservation implements Serializable {
    private String numeroReservation;
    private LocalDate dateReservation;
    private String statut;
    private Vol vol;
    private Passager passager;

    public Reservation(String numeroReservation, Vol vol, Passager passager) {
        this.numeroReservation = numeroReservation;
        this.dateReservation = LocalDate.now();
        this.statut = "Confirmée";
        this.vol = vol;
        this.passager = passager;
    }

    // Getters et Setters
    public String getNumeroReservation() {
        return numeroReservation;
    }

    public void setNumeroReservation(String numeroReservation) {
        this.numeroReservation = numeroReservation;
    }

    public LocalDate getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(LocalDate dateReservation) {
        this.dateReservation = dateReservation;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Vol getVol() {
        return vol;
    }

    public void setVol(Vol vol) {
        this.vol = vol;
    }

    public Passager getPassager() {
        return passager;
    }

    public void setPassager(Passager passager) {
        this.passager = passager;
    }

    // Méthodes CRUD
    public void ajouterReservation(GestionDonnees gestionDonnees) {
        gestionDonnees.ajouterReservation(this);
    }

    public static Reservation trouverReservation(String numeroReservation, GestionDonnees gestionDonnees) {
        return gestionDonnees.trouverReservation(numeroReservation);
    }

    public void modifierReservation(GestionDonnees gestionDonnees) {
        gestionDonnees.modifierReservation(this);
    }

    public void supprimerReservation(GestionDonnees gestionDonnees) {
        gestionDonnees.supprimerReservation(this.numeroReservation);
    }

    // Méthodes demandées dans le diagramme
    public boolean confirmerReservation() {
        if (!statut.equals("Confirmée")) {
            statut = "Confirmée";
            return true;
        }
        return false;
    }

    public boolean annulerReservation() {
        if (!statut.equals("Annulée")) {
            statut = "Annulée";
            return true;
        }
        return false;
    }

    public boolean modifierReservation(Vol nouveauVol) {
        if (!statut.equals("Annulée")) {
            vol.retirerPassager(passager);
            vol = nouveauVol;
            vol.ajouterPassager(passager);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return numeroReservation + "," + dateReservation + "," + statut + "," +
                vol.getNumeroVol() + "," + passager.getIdentifiant();
    }
}
