package airlinereservation;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Pilote extends Employe implements Serializable {
    private String licence;
    private int heuresDeVol;
    private List<Vol> volsAffectes;

    public Pilote(String identifiant, String nom, String adresse, String contact,
                  String numeroEmploye, LocalDate dateEmbauche, String licence, int heuresDeVol) {
        super(identifiant, nom, adresse, contact, numeroEmploye, dateEmbauche);
        this.licence = licence;
        this.heuresDeVol = heuresDeVol;
        this.volsAffectes = new ArrayList<>();
    }

    // Getters et Setters
    public String getLicence() {
        return licence;
    }

    public void setLicence(String licence) {
        this.licence = licence;
    }

    public int getHeuresDeVol() {
        return heuresDeVol;
    }

    public void setHeuresDeVol(int heuresDeVol) {
        this.heuresDeVol = heuresDeVol;
    }

    public List<Vol> getVolsAffectes() {
        return volsAffectes;
    }

    // Méthodes CRUD
    public void ajouterPilote(GestionDonnees gestionDonnees) {
        gestionDonnees.ajouterPilote(this);
    }

    public static Pilote trouverPilote(String identifiant, GestionDonnees gestionDonnees) {
        return gestionDonnees.trouverPilote(identifiant);
    }

    public void modifierPilote(GestionDonnees gestionDonnees) {
        gestionDonnees.modifierPilote(this);
    }

    public void supprimerPilote(GestionDonnees gestionDonnees) {
        gestionDonnees.supprimerPilote(this.getIdentifiant());
    }

    // Méthodes demandées dans le diagramme
    @Override
    public String obtenirRole() {
        return "Pilote";
    }

    public void affecterVol(Vol vol) {
        if (!volsAffectes.contains(vol)) {
            volsAffectes.add(vol);
            vol.setPilote(this);
        }
    }

    public Vol obtenirVol(String numeroVol) {
        for (Vol vol : volsAffectes) {
            if (vol.getNumeroVol().equals(numeroVol)) {
                return vol;
            }
        }
        return null;
    }

    @Override
    public String obtenirInfos() {
        return super.obtenirInfos() +
                "\nLicence: " + licence +
                "\nHeures de Vol: " + heuresDeVol;
    }

    @Override
    public String toString() {
        return super.toString() + "," + licence + "," + heuresDeVol;
    }
}
