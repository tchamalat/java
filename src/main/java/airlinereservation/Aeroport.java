package airlinereservation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Aeroport implements Serializable {
    private String nom;
    private String ville;
    private String description;
    private List<Vol> volsDepart;
    private List<Vol> volsArrivee;

    public Aeroport(String nom, String ville, String description) {
        this.nom = nom;
        this.ville = ville;
        this.description = description;
        this.volsDepart = new ArrayList<>();
        this.volsArrivee = new ArrayList<>();
    }

    // Getters et Setters
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getVille() {
        return ville;
    }

    public String getDescription() {
        return description;
    }

    public List<Vol> getVolsDepart() {
        return volsDepart;
    }

    public List<Vol> getVolsArrivee() {
        return volsArrivee;
    }

    // Méthodes CRUD
    public void ajouterAeroport(GestionDonnees gestionDonnees) {
        gestionDonnees.ajouterAeroport(this);
    }


    // Méthodes demandées dans le diagramme
    public void affecterVol(Vol vol, boolean estDepart) {
        if (estDepart) {
            if (!volsDepart.contains(vol)) {
                volsDepart.add(vol);
                vol.setAeroport(this);
            }
        } else {
            if (!volsArrivee.contains(vol)) {
                volsArrivee.add(vol);
            }
        }
    }

    @Override
    public String toString() {
        return nom + "," + ville + "," + description;
    }
}