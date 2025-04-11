package airlinereservation;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Avion implements Serializable {
    private String immatriculation;
    private String modele;
    private int capacite;
    private List<Vol> vols;

    public Avion(String immatriculation, String modele, int capacite) {
        this.immatriculation = immatriculation;
        this.modele = modele;
        this.capacite = capacite;
        this.vols = new ArrayList<>();
    }

    // Getters et Setters
    public String getImmatriculation() {
        return immatriculation;
    }

    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public List<Vol> getVols() {
        return vols;
    }

    // Méthodes CRUD
    public void ajouterAvion(GestionDonnees gestionDonnees) {
        gestionDonnees.ajouterAvion(this);
    }

    public static Avion trouverAvion(String immatriculation, GestionDonnees gestionDonnees) {
        return gestionDonnees.trouverAvion(immatriculation);
    }

    public void modifierAvion(GestionDonnees gestionDonnees) {
        gestionDonnees.modifierAvion(this);
    }

    public void supprimerAvion(GestionDonnees gestionDonnees) {
        gestionDonnees.supprimerAvion(this.immatriculation);
    }

    // Méthodes demandées dans le diagramme
    public boolean affecterVol(Vol vol) {
        if (verifierDisponibilite(vol.getDateHeureDepart(), vol.getDateHeureArrivee())) {
            vols.add(vol);
            vol.setAvion(this);
            return true;
        }
        return false;
    }

    public boolean verifierDisponibilite(LocalDateTime debut, LocalDateTime fin) {
        for (Vol vol : vols) {
            // Vérifier si l'avion est déjà affecté à un vol qui chevauche la période demandée
            if ((debut.isBefore(vol.getDateHeureArrivee()) && fin.isAfter(vol.getDateHeureDepart())) ||
                    debut.isEqual(vol.getDateHeureDepart()) || fin.isEqual(vol.getDateHeureArrivee())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return immatriculation + "," + modele + "," + capacite;
    }
}
