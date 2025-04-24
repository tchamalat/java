package airlinereservation;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GestionDonnees {
    private static final String DATA_DIR = "data/";
    private static final String PILOTES_FILE = DATA_DIR + "pilotes.dat";
    private static final String PERSONNEL_CABINE_FILE = DATA_DIR + "personnel_cabine.dat";
    private static final String PASSAGERS_FILE = DATA_DIR + "passagers.dat";
    private static final String AVIONS_FILE = DATA_DIR + "avions.dat";
    private static final String VOLS_FILE = DATA_DIR + "vols.dat";
    private static final String RESERVATIONS_FILE = DATA_DIR + "reservations.dat";
    private static final String AEROPORTS_FILE = DATA_DIR + "aeroports.dat";

    private Map<String, Pilote> pilotes;
    private Map<String, PersonnelCabine> personnelCabine;
    private Map<String, Passager> passagers;
    private Map<String, Avion> avions;
    private Map<String, Vol> vols;
    private Map<String, Reservation> reservations;
    private Map<String, Aeroport> aeroports;

    public GestionDonnees() {
        pilotes = new HashMap<>();
        personnelCabine = new HashMap<>();
        passagers = new HashMap<>();
        avions = new HashMap<>();
        vols = new HashMap<>();
        reservations = new HashMap<>();
        aeroports = new HashMap<>();

        chargerDonnees();
    }

    // Gestion de Pilote
    public void ajouterPilote(Pilote pilote) {
        pilotes.put(pilote.getIdentifiant(), pilote);
        sauvegarderPilotes();
    }

    public Pilote trouverPilote(String identifiant) {
        return pilotes.get(identifiant);
    }

    public void modifierPilote(Pilote pilote) {
        if (pilotes.containsKey(pilote.getIdentifiant())) {
            pilotes.put(pilote.getIdentifiant(), pilote);
            sauvegarderPilotes();
        }
    }

    public void supprimerPilote(String identifiant) {
        if (pilotes.containsKey(identifiant)) {
            pilotes.remove(identifiant);
            sauvegarderPilotes();
        }
    }

    // Gestion de PersonnelCabine
    public void ajouterPersonnelCabine(PersonnelCabine personnel) {
        personnelCabine.put(personnel.getIdentifiant(), personnel);
        sauvegarderPersonnelCabine();
    }

    public PersonnelCabine trouverPersonnelCabine(String identifiant) {
        return personnelCabine.get(identifiant);
    }

    public void modifierPersonnelCabine(PersonnelCabine personnel) {
        if (personnelCabine.containsKey(personnel.getIdentifiant())) {
            personnelCabine.put(personnel.getIdentifiant(), personnel);
            sauvegarderPersonnelCabine();
        }
    }

    public void supprimerPersonnelCabine(String identifiant) {
        if (personnelCabine.containsKey(identifiant)) {
            personnelCabine.remove(identifiant);
            sauvegarderPersonnelCabine();
        }
    }

    // Gestion de Passager
    public void ajouterPassager(Passager passager) {
        passagers.put(passager.getIdentifiant(), passager);
        sauvegarderPassagers();
    }

    public Passager trouverPassager(String identifiant) {
        return passagers.get(identifiant);
    }

    public void modifierPassager(Passager passager) {
        if (passagers.containsKey(passager.getIdentifiant())) {
            passagers.put(passager.getIdentifiant(), passager);
            sauvegarderPassagers();
        }
    }

    public void supprimerPassager(String identifiant) {
        if (passagers.containsKey(identifiant)) {
            passagers.remove(identifiant);
            sauvegarderPassagers();
        }
    }

    // Gestion de Avion
    public void ajouterAvion(Avion avion) {
        avions.put(avion.getImmatriculation(), avion);
        sauvegarderAvions();
    }

    public Avion trouverAvion(String immatriculation) {
        return avions.get(immatriculation);
    }

    public void modifierAvion(Avion avion) {
        if (avions.containsKey(avion.getImmatriculation())) {
            avions.put(avion.getImmatriculation(), avion);
            sauvegarderAvions();
        }
    }

    public void supprimerAvion(String immatriculation) {
        if (avions.containsKey(immatriculation)) {
            avions.remove(immatriculation);
            sauvegarderAvions();
        }
    }

    // Gestion de Vol
    public void ajouterVol(Vol vol) {
        vols.put(vol.getNumeroVol(), vol);
        sauvegarderVols();
    }

    public Vol trouverVol(String numeroVol) {
        return vols.get(numeroVol);
    }

    public void modifierVol(Vol vol) {
        if (vols.containsKey(vol.getNumeroVol())) {
            vols.put(vol.getNumeroVol(), vol);
            sauvegarderVols();
        }
    }

    public void supprimerVol(String numeroVol) {
        if (vols.containsKey(numeroVol)) {
            vols.remove(numeroVol);
            sauvegarderVols();
        }
    }

    // Gestion de Reservation
    public void ajouterReservation(Reservation reservation) {
        reservations.put(reservation.getNumeroReservation(), reservation);
        sauvegarderReservations();
    }

    public Reservation trouverReservation(String numeroReservation) {
        return reservations.get(numeroReservation);
    }

    public void modifierReservation(Reservation reservation) {
        if (reservations.containsKey(reservation.getNumeroReservation())) {
            reservations.put(reservation.getNumeroReservation(), reservation);
            sauvegarderReservations();
        }
    }

    public void supprimerReservation(String numeroReservation) {
        if (reservations.containsKey(numeroReservation)) {
            reservations.remove(numeroReservation);
            sauvegarderReservations();
        }
    }

    // Gestion de Aeroport
    public void ajouterAeroport(Aeroport aeroport) {
        aeroports.put(aeroport.getNom(), aeroport);
        sauvegarderAeroports();
    }

    public Aeroport trouverAeroport(String nom) {
        return aeroports.get(nom);
    }

    public void modifierAeroport(Aeroport aeroport) {
        if (aeroports.containsKey(aeroport.getNom())) {
            aeroports.put(aeroport.getNom(), aeroport);
            sauvegarderAeroports();
        }
    }

    public void supprimerAeroport(String nom) {
        if (aeroports.containsKey(nom)) {
            aeroports.remove(nom);
            sauvegarderAeroports();
        }
    }

    // Pour obtenir toutes les entités d'une catégorie
    public List<Pilote> getTousPilotes() {
        return new ArrayList<>(pilotes.values());
    }

    public List<PersonnelCabine> getTousPersonnelCabine() {
        return new ArrayList<>(personnelCabine.values());
    }

    public List<Passager> getTousPassagers() {
        return new ArrayList<>(passagers.values());
    }

    public List<Avion> getTousAvions() {
        return new ArrayList<>(avions.values());
    }

    public List<Vol> getTousVols() {
        return new ArrayList<>(vols.values());
    }

    public List<Reservation> getToutesReservations() {
        return new ArrayList<>(reservations.values());
    }

    public List<Aeroport> getTousAeroports() {
        return new ArrayList<>(aeroports.values());
    }

    // Gestion de la sauvegarde
    private void sauvegarderPilotes() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(PILOTES_FILE))) {
            oos.writeObject(new ArrayList<>(pilotes.values()));
        } catch (IOException e) {
            System.err.println("Erreur lors de la sauvegarde des pilotes: " + e.getMessage());
        }
    }

    private void sauvegarderPersonnelCabine() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(PERSONNEL_CABINE_FILE))) {
            oos.writeObject(new ArrayList<>(personnelCabine.values()));
        } catch (IOException e) {
            System.err.println("Erreur lors de la sauvegarde du personnel de cabine: " + e.getMessage());
        }
    }

    private void sauvegarderPassagers() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(PASSAGERS_FILE))) {
            oos.writeObject(new ArrayList<>(passagers.values()));
        } catch (IOException e) {
            System.err.println("Erreur lors de la sauvegarde des passagers: " + e.getMessage());
        }
    }

    private void sauvegarderAvions() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(AVIONS_FILE))) {
            oos.writeObject(new ArrayList<>(avions.values()));
        } catch (IOException e) {
            System.err.println("Erreur lors de la sauvegarde des avions: " + e.getMessage());
        }
    }

    private void sauvegarderVols() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(VOLS_FILE))) {
            oos.writeObject(new ArrayList<>(vols.values()));
        } catch (IOException e) {
            System.err.println("Erreur lors de la sauvegarde des vols: " + e.getMessage());
        }
    }

    private void sauvegarderReservations() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(RESERVATIONS_FILE))) {
            oos.writeObject(new ArrayList<>(reservations.values()));
        } catch (IOException e) {
            System.err.println("Erreur lors de la sauvegarde des réservations: " + e.getMessage());
        }
    }

    private void sauvegarderAeroports() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(AEROPORTS_FILE))) {
            oos.writeObject(new ArrayList<>(aeroports.values()));
        } catch (IOException e) {
            System.err.println("Erreur lors de la sauvegarde des aéroports: " + e.getMessage());
        }
    }

    // Gestion du chargement
    @SuppressWarnings("unchecked")
    public void chargerDonnees() {
        // Créer le répertoire data s'il n'existe pas
        File dataDir = new File(DATA_DIR);
        if (!dataDir.exists()) {
            boolean created = dataDir.mkdirs();
            if (!created) {
                System.err.println("Erreur lors de la création du répertoire data/");
            }
        }

        // Charger les pilotes
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(PILOTES_FILE))) {
            List<Pilote> listePilotes = (List<Pilote>) ois.readObject();
            for (Pilote pilote : listePilotes) {
                pilotes.put(pilote.getIdentifiant(), pilote);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Fichier des pilotes non trouvé. Création d'un nouveau fichier.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erreur lors du chargement des pilotes: " + e.getMessage());
        }

        // Charger le personnel de cabine
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(PERSONNEL_CABINE_FILE))) {
            List<PersonnelCabine> listePersonnel = (List<PersonnelCabine>) ois.readObject();
            for (PersonnelCabine personnel : listePersonnel) {
                personnelCabine.put(personnel.getIdentifiant(), personnel);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Fichier du personnel de cabine non trouvé. Création d'un nouveau fichier.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erreur lors du chargement du personnel de cabine: " + e.getMessage());
        }

        // Charger les passagers
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(PASSAGERS_FILE))) {
            List<Passager> listePassagers = (List<Passager>) ois.readObject();
            for (Passager passager : listePassagers) {
                passagers.put(passager.getIdentifiant(), passager);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Fichier des passagers non trouvé. Création d'un nouveau fichier.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erreur lors du chargement des passagers: " + e.getMessage());
        }

        // Charger les avions
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(AVIONS_FILE))) {
            List<Avion> listeAvions = (List<Avion>) ois.readObject();
            for (Avion avion : listeAvions) {
                avions.put(avion.getImmatriculation(), avion);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Fichier des avions non trouvé. Création d'un nouveau fichier.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erreur lors du chargement des avions: " + e.getMessage());
        }

        // Charger les vols
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(VOLS_FILE))) {
            List<Vol> listeVols = (List<Vol>) ois.readObject();
            for (Vol vol : listeVols) {
                vols.put(vol.getNumeroVol(), vol);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Fichier des vols non trouvé. Création d'un nouveau fichier.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erreur lors du chargement des vols: " + e.getMessage());
        }

        // Charger les réservations
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(RESERVATIONS_FILE))) {
            List<Reservation> listeReservations = (List<Reservation>) ois.readObject();
            for (Reservation reservation : listeReservations) {
                reservations.put(reservation.getNumeroReservation(), reservation);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Fichier des réservations non trouvé. Création d'un nouveau fichier.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erreur lors du chargement des réservations: " + e.getMessage());
        }

        // Charger les aéroports
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(AEROPORTS_FILE))) {
            List<Aeroport> listeAeroports = (List<Aeroport>) ois.readObject();
            for (Aeroport aeroport : listeAeroports) {
                aeroports.put(aeroport.getNom(), aeroport);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Fichier des aéroports non trouvé. Création d'un nouveau fichier.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erreur lors du chargement des aéroports: " + e.getMessage());
        }
    }
}