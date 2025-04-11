package airlinereservation;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Vol implements Serializable {
    private String numeroVol;
    private String origine;
    private String destination;
    private LocalDateTime dateHeureDepart;
    private LocalDateTime dateHeureArrivee;
    private String etat;
    private Avion avion;
    private Pilote pilote;
    private List<PersonnelCabine> equipageCabine;
    private List<Passager> passagers;
    private Aeroport aeroport;

    public Vol(String numeroVol, String origine, String destination,
               LocalDateTime dateHeureDepart, LocalDateTime dateHeureArrivee) {
        this.numeroVol = numeroVol;
        this.origine = origine;
        this.destination = destination;
        this.dateHeureDepart = dateHeureDepart;
        this.dateHeureArrivee = dateHeureArrivee;
        this.etat = "Planifié";
        this.equipageCabine = new ArrayList<>();
        this.passagers = new ArrayList<>();
    }

    // Getters et Setters
    public String getNumeroVol() {
        return numeroVol;
    }

    public void setNumeroVol(String numeroVol) {
        this.numeroVol = numeroVol;
    }

    public String getOrigine() {
        return origine;
    }

    public void setOrigine(String origine) {
        this.origine = origine;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDateTime getDateHeureDepart() {
        return dateHeureDepart;
    }

    public void setDateHeureDepart(LocalDateTime dateHeureDepart) {
        this.dateHeureDepart = dateHeureDepart;
    }

    public LocalDateTime getDateHeureArrivee() {
        return dateHeureArrivee;
    }

    public void setDateHeureArrivee(LocalDateTime dateHeureArrivee) {
        this.dateHeureArrivee = dateHeureArrivee;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Avion getAvion() {
        return avion;
    }

    public void setAvion(Avion avion) {
        this.avion = avion;
    }

    public Pilote getPilote() {
        return pilote;
    }

    public void setPilote(Pilote pilote) {
        this.pilote = pilote;
    }

    public List<PersonnelCabine> getEquipageCabine() {
        return equipageCabine;
    }

    public List<Passager> getPassagers() {
        return passagers;
    }

    public Aeroport getAeroport() {
        return aeroport;
    }

    public void setAeroport(Aeroport aeroport) {
        this.aeroport = aeroport;
    }

    // Méthodes CRUD
    public void ajouterVol(GestionDonnees gestionDonnees) {
        gestionDonnees.ajouterVol(this);
    }

    public static Vol trouverVol(String numeroVol, GestionDonnees gestionDonnees) {
        return gestionDonnees.trouverVol(numeroVol);
    }

    public void modifierVol(GestionDonnees gestionDonnees) {
        gestionDonnees.modifierVol(this);
    }

    public void supprimerVol(GestionDonnees gestionDonnees) {
        gestionDonnees.supprimerVol(this.numeroVol);
    }

    // Méthodes pour gérer l'équipage et les passagers
    public void ajouterPersonnelCabine(PersonnelCabine personnel) {
        if (!equipageCabine.contains(personnel)) {
            equipageCabine.add(personnel);
        }
    }

    public void ajouterPassager(Passager passager) {
        if (!passagers.contains(passager)) {
            passagers.add(passager);
        }
    }

    public void retirerPassager(Passager passager) {
        passagers.remove(passager);
    }

    // Méthodes demandées dans le diagramme
    public static void planifierVol(Vol vol, GestionDonnees gestionDonnees) {
        vol.ajouterVol(gestionDonnees);
    }

    public boolean annulerVol(GestionDonnees gestionDonnees) {
        if (!etat.equals("Annulé")) {
            etat = "Annulé";
            modifierVol(gestionDonnees);
            return true;
        }
        return false;
    }

    public boolean modifierVol(String origine, String destination,
                               LocalDateTime dateHeureDepart, LocalDateTime dateHeureArrivee,
                               GestionDonnees gestionDonnees) {
        this.origine = origine;
        this.destination = destination;
        this.dateHeureDepart = dateHeureDepart;
        this.dateHeureArrivee = dateHeureArrivee;
        modifierVol(gestionDonnees);
        return true;
    }

    public List<Passager> listingPassager() {
        return new ArrayList<>(passagers);
    }

    @Override
    public String toString() {
        return numeroVol + "," + origine + "," + destination + "," +
                dateHeureDepart + "," + dateHeureArrivee + "," + etat;
    }
}
