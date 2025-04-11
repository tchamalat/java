package airlinereservation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Passager extends Personne implements Serializable {
    private String passeport;
    private List<Reservation> reservations;

    public Passager(String identifiant, String nom, String adresse, String contact, String passeport) {
        super(identifiant, nom, adresse, contact);
        this.passeport = passeport;
        this.reservations = new ArrayList<>();
    }

    // Getters et Setters
    public String getPasseport() {
        return passeport;
    }

    public void setPasseport(String passeport) {
        this.passeport = passeport;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    // Méthodes CRUD
    public void ajouterPassager(GestionDonnees gestionDonnees) {
        gestionDonnees.ajouterPassager(this);
    }

    public static Passager trouverPassager(String identifiant, GestionDonnees gestionDonnees) {
        return gestionDonnees.trouverPassager(identifiant);
    }

    public void modifierPassager(GestionDonnees gestionDonnees) {
        gestionDonnees.modifierPassager(this);
    }

    public void supprimerPassager(GestionDonnees gestionDonnees) {
        gestionDonnees.supprimerPassager(this.getIdentifiant());
    }

    // Méthodes demandées dans le diagramme
    public Reservation reserverVol(Vol vol, String numeroReservation, GestionDonnees gestionDonnees) {
        Reservation reservation = new Reservation(numeroReservation, vol, this);
        reservations.add(reservation);
        vol.ajouterPassager(this);
        reservation.ajouterReservation(gestionDonnees);
        return reservation;
    }

    public boolean annulerReservation(String numeroReservation, GestionDonnees gestionDonnees) {
        Reservation reservation = trouverReservation(numeroReservation);
        if (reservation != null) {
            reservation.setStatut("Annulée");
            Vol vol = reservation.getVol();
            vol.retirerPassager(this);
            reservations.remove(reservation);
            reservation.modifierReservation(gestionDonnees);
            return true;
        }
        return false;
    }

    public List<Reservation> obtenirReservations() {
        return reservations;
    }

    private Reservation trouverReservation(String numeroReservation) {
        for (Reservation reservation : reservations) {
            if (reservation.getNumeroReservation().equals(numeroReservation)) {
                return reservation;
            }
        }
        return null;
    }

    @Override
    public String obtenirInfos() {
        return super.obtenirInfos() +
                "\nPasseport: " + passeport;
    }

    @Override
    public String toString() {
        return super.toString() + "," + passeport;
    }
}
