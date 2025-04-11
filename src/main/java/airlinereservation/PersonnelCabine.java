package airlinereservation;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PersonnelCabine extends Employe implements Serializable {
    private String qualification;
    private List<Vol> volsAffectes;

    public PersonnelCabine(String identifiant, String nom, String adresse, String contact,
                           String numeroEmploye, LocalDate dateEmbauche, String qualification) {
        super(identifiant, nom, adresse, contact, numeroEmploye, dateEmbauche);
        this.qualification = qualification;
        this.volsAffectes = new ArrayList<>();
    }

    // Getters et Setters
    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public List<Vol> getVolsAffectes() {
        return volsAffectes;
    }

    // Méthodes CRUD
    public void ajouterPersonnelCabine(GestionDonnees gestionDonnees) {
        gestionDonnees.ajouterPersonnelCabine(this);
    }

    public static PersonnelCabine trouverPersonnelCabine(String identifiant, GestionDonnees gestionDonnees) {
        return gestionDonnees.trouverPersonnelCabine(identifiant);
    }

    public void modifierPersonnelCabine(GestionDonnees gestionDonnees) {
        gestionDonnees.modifierPersonnelCabine(this);
    }

    public void supprimerPersonnelCabine(GestionDonnees gestionDonnees) {
        gestionDonnees.supprimerPersonnelCabine(this.getIdentifiant());
    }

    // Méthodes demandées dans le diagramme
    @Override
    public String obtenirRole() {
        return "Personnel de Cabine";
    }

    public void affecterVol(Vol vol) {
        if (!volsAffectes.contains(vol)) {
            volsAffectes.add(vol);
            vol.ajouterPersonnelCabine(this);
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
                "\nQualification: " + qualification;
    }

    @Override
    public String toString() {
        return super.toString() + "," + qualification;
    }
}
