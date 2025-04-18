import airlinereservation.*;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Main {
    //écriture de toutes les fonctionnalités pour modifier le contenu des classes
    private static GestionDonnees gestionDonnees = new GestionDonnees();
    private static Scanner scanner = new Scanner(System.in);
    private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public static void main(String[] args) {
        InitialisationDonnees.initialiserDonnees(gestionDonnees);


        boolean continuer = true;
        while (continuer) {
            afficherMenuPrincipal();
            int choix = lireEntier("Entrez votre choix : ");

            switch (choix) {
                case 1:
                    gestionPersonnes();
                    break;
                case 2:
                    gestionVols();
                    break;
                case 3:
                    gestionReservations();
                    break;
                case 4:
                    gestionAvions();
                    break;
                case 5:
                    gestionAeroports();
                    break;
                case 6:
                    Administration();
                    break;
                case 0:
                    continuer = false;
                    System.out.println("Au revoir !");
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
        }
        scanner.close();
    }

    private static void afficherMenuPrincipal() {
        System.out.println("\nSYSTÈME DE RÉSERVATION AÉRIENNE");
        System.out.println("1. Gestion des Personnes");
        System.out.println("2. Gestion des Vols");
        System.out.println("3. Gestion des Réservations");
        System.out.println("4. Gestion des Avions");
        System.out.println("5. Gestion des Aéroports");
        System.out.println("6. Administration");
        System.out.println("0. Quitter");
        System.out.println("\n ");
    }

    private static void gestionPersonnes() {
        boolean retour = false;
        while (!retour) {
            System.out.println("\nGESTION DES PERSONNES");
            System.out.println("1. Ajouter un Pilote");
            System.out.println("2. Ajouter un Personel de Cabine");
            System.out.println("3. Ajouter un Passager");
            System.out.println("4. Afficher les informations d'une Personne");
            System.out.println("5. Afficher le rôle d'un Employé");
            System.out.println("6. Lister tous les Pilotes");
            System.out.println("7. Lister tout le Personnel de Cabine");
            System.out.println("8. Lister tous les Passagers");
            System.out.println("0. Retour au menu principal");
            System.out.println("\n");

            int choix = lireEntier("Entrez votre choix : ");

            switch (choix) {
                case 1:
                    ajouterPilote();
                    break;
                case 2:
                    ajouterPersonnelCabine();
                    break;
                case 3:
                    ajouterPassager();
                    break;
                case 4:
                    afficherInfosPersonne();
                    break;
                case 5:
                    afficherRoleEmploye();
                    break;
                case 6:
                    listerPilotes();
                    break;
                case 7:
                    listerPersonnelCabine();
                    break;
                case 8:
                    listerPassagers();
                    break;
                case 0:
                    retour = true;
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
        }
    }

    private static void ajouterPilote() {
        System.out.println("\nAJOUTER UN PILOTE");
        String identifiant = lireChaine("Identifiant : ");
        String nom = lireChaine("Nom : ");
        String adresse = lireChaine("Adresse : ");
        String contact = lireChaine("Contact : ");
        String numeroEmploye = lireChaine("Numéro d'employé : ");
        LocalDate dateEmbauche = lireDate("Date d'embauche (jj/mm/aaaa) : ");
        String licence = lireChaine("Licence : ");
        int heuresDeVol = lireEntier("Heures de vol : ");

        Pilote pilote = new Pilote(identifiant, nom, adresse, contact, numeroEmploye, dateEmbauche, licence, heuresDeVol);
        pilote.ajouterPilote(gestionDonnees);
        System.out.println("Pilote ajouté avec succès !");
    }

    private static void ajouterPersonnelCabine() {
        System.out.println("\nAJOUTER UN PERSONNEL DE CABINE");
        String identifiant = lireChaine("Identifiant : ");
        String nom = lireChaine("Nom : ");
        String adresse = lireChaine("Adresse : ");
        String contact = lireChaine("Contact : ");
        String numeroEmploye = lireChaine("Numéro d'employé : ");
        LocalDate dateEmbauche = lireDate("Date d'embauche (jj/mm/aaaa) : ");
        String qualification = lireChaine("Qualification : ");

        PersonnelCabine personnel = new PersonnelCabine(identifiant, nom, adresse, contact, numeroEmploye, dateEmbauche, qualification);
        personnel.ajouterPersonnelCabine(gestionDonnees);
        System.out.println("Personnel de cabine ajouté avec succès !");
    }

    private static void ajouterPassager() {
        System.out.println("\nAJOUTER UN PASSAGER");
        String identifiant = lireChaine("Identifiant : ");
        String nom = lireChaine("Nom : ");
        String adresse = lireChaine("Adresse : ");
        String contact = lireChaine("Contact : ");
        String passeport = lireChaine("Numéro de passeport : ");

        Passager passager = new Passager(identifiant, nom, adresse, contact, passeport);
        passager.ajouterPassager(gestionDonnees);
        System.out.println("Passager ajouté avec succès !");
    }

    private static void afficherInfosPersonne() {
        System.out.println("\nAFFICHER LES INFORMATIONS D'UNE PERSONNE");
        String identifiant = lireChaine("Identifiant de la personne : ");

        Pilote pilote = gestionDonnees.trouverPilote(identifiant);
        if (pilote != null) {
            System.out.println(pilote.obtenirInfos());
            return;
        }

        PersonnelCabine personnel = gestionDonnees.trouverPersonnelCabine(identifiant);
        if (personnel != null) {
            System.out.println(personnel.obtenirInfos());
            return;
        }

        Passager passager = gestionDonnees.trouverPassager(identifiant);
        if (passager != null) {
            System.out.println(passager.obtenirInfos());
            return;
        }

        System.out.println("Aucune personne trouvée avec cet identifiant.");
    }

    private static void afficherRoleEmploye() {
        System.out.println("\nAFFICHER LE RÔLE D'UN EMPLOYÉ");
        String identifiant = lireChaine("Identifiant de l'employé : ");

        Pilote pilote = gestionDonnees.trouverPilote(identifiant);
        if (pilote != null) {
            System.out.println("Rôle : " + pilote.obtenirRole());
            return;
        }

        PersonnelCabine personnel = gestionDonnees.trouverPersonnelCabine(identifiant);
        if (personnel != null) {
            System.out.println("Rôle : " + personnel.obtenirRole());
            return;
        }

        System.out.println("Aucun employé trouvé avec cet identifiant.");
    }

    private static void listerPilotes() {
        System.out.println("\nLISTE DES PILOTES");
        List<Pilote> pilotes = gestionDonnees.getTousPilotes();
        if (pilotes.isEmpty()) {
            System.out.println("Aucun pilote enregistré.");
        } else {
            for (Pilote pilote : pilotes) {
                System.out.println(pilote.obtenirInfos());
                System.out.println("\n ");
            }
        }
    }

    private static void listerPersonnelCabine() {
        System.out.println("\nLISTE DU PERSONNEL DE CABINE");
        List<PersonnelCabine> personnels = gestionDonnees.getTousPersonnelCabine();
        if (personnels.isEmpty()) {
            System.out.println("Aucun personnel de cabine enregistré.");
        } else {
            for (PersonnelCabine personnel : personnels) {
                System.out.println(personnel.obtenirInfos());
                System.out.println("\n ");
            }
        }
    }

    private static void listerPassagers() {
        System.out.println("\nLISTE DES PASSAGERS");
        List<Passager> passagers = gestionDonnees.getTousPassagers();
        if (passagers.isEmpty()) {
            System.out.println("Aucun passager enregistré.");
        } else {
            for (Passager passager : passagers) {
                System.out.println(passager.obtenirInfos());
                System.out.println("\n ");
            }
        }
    }

    private static void gestionVols() {
        boolean retour = false;
        while (!retour) {
            System.out.println("\nGESTION DES VOLS");
            System.out.println("1. Planifier un Vol");
            System.out.println("2. Afficher les informations d'un Vol");
            System.out.println("3. Annuler un Vol");
            System.out.println("4. Modifier un Vol");
            System.out.println("5. Affecter un Pilote à un Vol");
            System.out.println("6. Affecter un Personnel de Cabine à un Vol");
            System.out.println("7. Lister tous les Vols");
            System.out.println("8. Lister les Passagers d'un Vol");
            System.out.println("0. Retour au menu principal");
            System.out.println("\n");

            int choix = lireEntier("Entrez votre choix : ");

            switch (choix) {
                case 1:
                    planifierVol();
                    break;
                case 2:
                    afficherInfosVol();
                    break;
                case 3:
                    annulerVol();
                    break;
                case 4:
                    modifierVol();
                    break;
                case 5:
                    affecterPiloteVol();
                    break;
                case 6:
                    affecterPersonnelCabineVol();
                    break;
                case 7:
                    listerVols();
                    break;
                case 8:
                    listerPassagersVol();
                    break;
                case 0:
                    retour = true;
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
        }
    }

    private static void planifierVol() {
        System.out.println("\nPLANIFIER UN VOL");
        String numeroVol = lireChaine("Numéro de vol : ");
        String origine = lireChaine("Origine : ");
        String destination = lireChaine("Destination : ");
        LocalDateTime dateHeureDepart = lireDateTime("Date et heure de départ (jj/mm/aaaa HH:mm) : ");
        LocalDateTime dateHeureArrivee = lireDateTime("Date et heure d'arrivée (jj/mm/aaaa HH:mm) : ");

        Vol vol = new Vol(numeroVol, origine, destination, dateHeureDepart, dateHeureArrivee);
        Vol.planifierVol(vol, gestionDonnees);
        System.out.println("Vol planifié avec succès !");
    }

    private static void afficherInfosVol() {
        System.out.println("\nAFFICHER LES INFORMATIONS D'UN VOL");
        String numeroVol = lireChaine("Numéro de vol : ");

        Vol vol = gestionDonnees.trouverVol(numeroVol);
        if (vol != null) {
            System.out.println("Numéro de vol : " + vol.getNumeroVol());
            System.out.println("Origine : " + vol.getOrigine());
            System.out.println("Destination : " + vol.getDestination());
            System.out.println("Date et heure de départ : " + vol.getDateHeureDepart().format(dateTimeFormatter));
            System.out.println("Date et heure d'arrivée : " + vol.getDateHeureArrivee().format(dateTimeFormatter));
            System.out.println("État : " + vol.getEtat());

            if (vol.getAvion() != null) {
                System.out.println("Avion : " + vol.getAvion().getImmatriculation() + " (" + vol.getAvion().getModele() + ")");
            }

            if (vol.getPilote() != null) {
                System.out.println("Pilote : " + vol.getPilote().getNom());
            }

            System.out.println("Nombre de passagers : " + vol.getPassagers().size());
        } else {
            System.out.println("Aucun vol trouvé avec ce numéro.");
        }
    }

    private static void annulerVol() {
        System.out.println("\nANNULER UN VOL");
        String numeroVol = lireChaine("Numéro de vol : ");

        Vol vol = gestionDonnees.trouverVol(numeroVol);
        if (vol != null) {
            if (vol.annulerVol(gestionDonnees)) {
                System.out.println("Vol annulé avec succès !");
            } else {
                System.out.println("Le vol est déjà annulé.");
            }
        } else {
            System.out.println("Aucun vol trouvé avec ce numéro.");
        }
    }

    private static void modifierVol() {
        System.out.println("\nMODIFIER UN VOL");
        String numeroVol = lireChaine("Numéro de vol : ");

        Vol vol = gestionDonnees.trouverVol(numeroVol);

        if (vol != null) {
            String origine = lireChaine("Nouvelle origine (actuelle : " + vol.getOrigine() + ") : ");
            String destination = lireChaine("Nouvelle destination (actuelle : " + vol.getDestination() + ") : ");
            LocalDateTime dateHeureDepart = lireDateTime("Nouvelle date et heure de départ (actuelle : " +
                    vol.getDateHeureDepart().format(dateTimeFormatter) + ") : ");
            LocalDateTime dateHeureArrivee = lireDateTime("Nouvelle date et heure d'arrivée (actuelle : " +
                    vol.getDateHeureArrivee().format(dateTimeFormatter) + ") : ");

            if (vol.modifierVol(origine, destination, dateHeureDepart, dateHeureArrivee, gestionDonnees)) {
                System.out.println("Vol modifié avec succès !");
            } else {
                System.out.println("Erreur lors de la modification du vol.");
            }
        } else {
            System.out.println("Aucun vol trouvé avec ce numéro.");
        }
    }

    private static void affecterPiloteVol() {
        System.out.println("\nAFFECTER UN PILOTE À UN VOL");
        String numeroVol = lireChaine("Numéro de vol : ");

        Vol vol = gestionDonnees.trouverVol(numeroVol);
        if (vol != null) {
            String idPilote = lireChaine("Identifiant du pilote : ");
            Pilote pilote = gestionDonnees.trouverPilote(idPilote);

            if (pilote != null) {
                pilote.affecterVol(vol);
                vol.modifierVol(gestionDonnees);
                System.out.println("Pilote affecté au vol avec succès !");
            } else {
                System.out.println("Pilote non trouvé avec cet identifiant.");
            }
        } else {
            System.out.println("Vol non trouvé avec ce numéro.");
        }
    }

    private static void affecterPersonnelCabineVol() {
        System.out.println("\nAFFECTER UN PERSONNEL DE CABINE À UN VOL");
        String numeroVol = lireChaine("Numéro de vol : ");

        Vol vol = gestionDonnees.trouverVol(numeroVol);
        if (vol != null) {
            String idPersonnel = lireChaine("Identifiant du personnel de cabine : ");
            PersonnelCabine personnel = gestionDonnees.trouverPersonnelCabine(idPersonnel);

            if (personnel != null) {
                personnel.affecterVol(vol);
                vol.modifierVol(gestionDonnees);
                System.out.println("Personnel de cabine affecté au vol avec succès!");
            } else {
                System.out.println("Personnel de cabine non trouvé avec cet identifiant.");
            }
        } else {
            System.out.println("Vol non trouvé avec ce numéro.");
        }
    }

    private static void listerVols() {
        System.out.println("\nLISTE DES VOLS");
        List<Vol> vols = gestionDonnees.getTousVols();
        if (vols.isEmpty()) {
            System.out.println("Aucun vol enregistré.");
        } else {
            for (Vol vol : vols) {
                System.out.println("Numéro : " + vol.getNumeroVol());
                System.out.println("Origine : " + vol.getOrigine());
                System.out.println("Destination : " + vol.getDestination());
                System.out.println("Départ : " + vol.getDateHeureDepart().format(dateTimeFormatter));
                System.out.println("Arrivée : " + vol.getDateHeureArrivee().format(dateTimeFormatter));
                System.out.println("État : " + vol.getEtat());
                System.out.println("\n");
            }
        }
    }

    private static void listerPassagersVol() {
        System.out.println("\nLISTER LES PASSAGERS D'UN VOL");
        String numeroVol = lireChaine("Numéro de vol : ");

        Vol vol = gestionDonnees.trouverVol(numeroVol);
        if (vol != null) {
            List<Passager> passagers = vol.listingPassager();
            if (passagers.isEmpty()) {
                System.out.println("Aucun passager enregistré pour ce vol.");
            } else {
                System.out.println("Passagers du vol " + vol.getNumeroVol() + " :");
                for (Passager passager : passagers) {
                    System.out.println("- " + passager.getNom() + " (ID : " + passager.getIdentifiant() + ")");
                }
            }
        } else {
            System.out.println("Aucun vol trouvé avec ce numéro.");
        }
    }

    private static void gestionReservations() {
        boolean retour = false;
        while (!retour) {
            System.out.println("\nGESTION DES RÉSERVATIONS");
            System.out.println("1. Réserver un Vol");
            System.out.println("2. Annuler une Réservation");
            System.out.println("3. Afficher les informations d'une Réservation");
            System.out.println("4. Lister toutes les Réservations");
            System.out.println("5. Lister les Réservations d'un Passager");
            System.out.println("0. Retour au menu principal");
            System.out.println("\n");

            int choix = lireEntier("Entrez votre choix : ");

            switch (choix) {
                case 1:
                    reserverVol();
                    break;
                case 2:
                    annulerReservation();
                    break;
                case 3:
                    afficherInfosReservation();
                    break;
                case 4:
                    listerReservations();
                    break;
                case 5:
                    listerReservationsPassager();
                    break;
                case 0:
                    retour = true;
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
        }
    }

    private static void reserverVol() {
        System.out.println("\nRÉSERVER UN VOL");
        String idPassager = lireChaine("Identifiant du passager : ");

        Passager passager = gestionDonnees.trouverPassager(idPassager);
        if (passager != null) {
            String numeroVol = lireChaine("Numéro de vol : ");
            Vol vol = gestionDonnees.trouverVol(numeroVol);

            if (vol != null) {
                if (vol.getEtat().equals("Annulé")) {
                    System.out.println("Ce vol a été annulé et ne peut pas être réservé.");
                    return;
                }

                String numeroReservation = "RES" + System.currentTimeMillis();
                Reservation reservation = passager.reserverVol(vol, numeroReservation, gestionDonnees);
                System.out.println("Réservation effectuée avec succès !");
                System.out.println("Numéro de réservation : " + reservation.getNumeroReservation());
            } else {
                System.out.println("Vol non trouvé avec ce numéro.");
            }
        } else {
            System.out.println("Passager non trouvé avec cet identifiant.");
        }
    }

    private static void annulerReservation() {
        System.out.println("\nANNULER UNE RÉSERVATION");
        String idPassager = lireChaine("Identifiant du passager : ");

        Passager passager = gestionDonnees.trouverPassager(idPassager);
        if (passager != null) {
            String numeroReservation = lireChaine("Numéro de réservation : ");

            if (passager.annulerReservation(numeroReservation, gestionDonnees)) {
                System.out.println("Réservation annulée avec succès !");
            } else {
                System.out.println("Réservation non trouvée ou déjà annulée.");
            }
        } else {
            System.out.println("Aucun passager trouvé avec cet identifiant.");
        }
    }

    private static void afficherInfosReservation() {
        System.out.println("\nAFFICHER LES INFORMATIONS D'UNE RÉSERVATION");
        String numeroReservation = lireChaine("Numéro de réservation : ");

        Reservation reservation = gestionDonnees.trouverReservation(numeroReservation);
        if (reservation != null) {
            System.out.println("Numéro de réservation : " + reservation.getNumeroReservation());
            System.out.println("Date de réservation : " + reservation.getDateReservation().format(dateFormatter));
            System.out.println("Statut : " + reservation.getStatut());
            System.out.println("Vol : " + reservation.getVol().getNumeroVol());
            System.out.println("Passager : " + reservation.getPassager().getNom());
        } else {
            System.out.println("Réservation non trouvée avec ce numéro.");
        }
    }

    private static void listerReservations() {
        System.out.println("\nLISTE DES RÉSERVATIONS");
        List<Reservation> reservations = gestionDonnees.getToutesReservations();
        if (reservations.isEmpty()) {
            System.out.println("Aucune réservation enregistrée.");
        } else {
            for (Reservation reservation : reservations) {
                System.out.println("Numéro : " + reservation.getNumeroReservation());
                System.out.println("Date : " + reservation.getDateReservation().format(dateFormatter));
                System.out.println("Statut : " + reservation.getStatut());
                System.out.println("Vol : " + reservation.getVol().getNumeroVol());
                System.out.println("Passager : " + reservation.getPassager().getNom());
                System.out.println("\n ");
            }
        }
    }

    private static void listerReservationsPassager() {
        System.out.println("\nLISTER LES RÉSERVATIONS D'UN PASSAGER");
        String idPassager = lireChaine("Identifiant du passager : ");

        Passager passager = gestionDonnees.trouverPassager(idPassager);
        if (passager != null) {
            List<Reservation> reservations = passager.obtenirReservations();
            if (reservations.isEmpty()) {
                System.out.println("Aucune réservation enregistrée pour ce passager.");
            } else {
                System.out.println("Réservations de " + passager.getNom() + " :");
                for (Reservation reservation : reservations) {
                    System.out.println("- Numéro : " + reservation.getNumeroReservation());
                    System.out.println("  Vol : " + reservation.getVol().getNumeroVol());
                    System.out.println("  Statut : " + reservation.getStatut());
                    System.out.println("\n ");
                }
            }
        } else {
            System.out.println("Passager non trouvé avec cet identifiant.");
        }
    }

    private static void gestionAvions() {
        boolean retour = false;
        while (!retour) {
            System.out.println("\nGESTION DES AVIONS");
            System.out.println("1. Ajouter un Avion");
            System.out.println("2. Affecter un Avion à un Vol");
            System.out.println("3. Vérifier la Disponibilité d'un Avion");
            System.out.println("4. Lister tous les Avions");
            System.out.println("0. Retour au menu principal");
            System.out.println("\n ");

            int choix = lireEntier("Entrez votre choix : ");

            switch (choix) {
                case 1:
                    ajouterAvion();
                    break;
                case 2:
                    affecterAvionVol();
                    break;
                case 3:
                    verifierDisponibiliteAvion();
                    break;
                case 4:
                    listerAvions();
                    break;
                case 0:
                    retour = true;
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
        }
    }

    private static void ajouterAvion() {
        System.out.println("\nAJOUTER UN AVION");
        String immatriculation = lireChaine("Immatriculation : ");
        String modele = lireChaine("Modèle : ");
        int capacite = lireEntier("Capacité : ");

        Avion avion = new Avion(immatriculation, modele, capacite);
        avion.ajouterAvion(gestionDonnees);
        System.out.println("Avion ajouté avec succès !");
    }

    private static void affecterAvionVol() {
        System.out.println("\nAFFECTER UN AVION À UN VOL");
        String numeroVol = lireChaine("Numéro de vol : ");

        Vol vol = gestionDonnees.trouverVol(numeroVol);
        if (vol != null) {
            String immatriculation = lireChaine("Immatriculation de l'avion : ");
            Avion avion = gestionDonnees.trouverAvion(immatriculation);

            if (avion != null) {
                if (avion.affecterVol(vol)) {
                    vol.modifierVol(gestionDonnees);
                    System.out.println("Avion affecté au vol avec succès !");
                } else {
                    System.out.println("L'avion n'est pas disponible pour ce vol.");
                }
            } else {
                System.out.println("Avion non trouvé avec cette immatriculation.");
            }
        } else {
            System.out.println("Aucun vol trouvé avec ce numéro.");
        }
    }

    private static void verifierDisponibiliteAvion() {
        System.out.println("\nVÉRIFIER LA DISPONIBILITÉ D'UN AVION");
        String immatriculation = lireChaine("Immatriculation de l'avion : ");

        Avion avion = gestionDonnees.trouverAvion(immatriculation);
        if (avion != null) {
            LocalDateTime debut = lireDateTime("Date et heure de début (jj/mm/aaaa HH:mm) : ");
            LocalDateTime fin = lireDateTime("Date et heure de fin (jj/mm/aaaa HH:mm) : ");

            if (avion.verifierDisponibilite(debut, fin)) {
                System.out.println("L'avion est disponible pour cette période.");
            } else {
                System.out.println("L'avion n'est pas disponible pour cette période.");
            }
        } else {
            System.out.println("Aucun avion trouvé avec cette immatriculation.");
        }
    }

    private static void listerAvions() {
        System.out.println("\nLISTE DES AVIONS");
        List<Avion> avions = gestionDonnees.getTousAvions();
        if (avions.isEmpty()) {
            System.out.println("Aucun avion enregistré.");
        } else {
            for (Avion avion : avions) {
                System.out.println("Immatriculation : " + avion.getImmatriculation());
                System.out.println("Modèle : " + avion.getModele());
                System.out.println("Capacité : " + avion.getCapacite());
                System.out.println("\n ");
            }
        }
    }

    private static void gestionAeroports() {
        boolean retour = false;
        while (!retour) {
            System.out.println("\nGESTION DES AÉROPORTS");
            System.out.println("1. Ajouter un Aéroport");
            System.out.println("2. Affecter un Vol à un Aéroport");
            System.out.println("3. Lister tous les Aéroports");
            System.out.println("0. Retour au menu principal");
            System.out.println("\n ");

            int choix = lireEntier("Entrez votre choix : ");

            switch (choix) {
                case 1:
                    ajouterAeroport();
                    break;
                case 2:
                    affecterVolAeroport();
                    break;
                case 3:
                    listerAeroports();
                    break;
                case 0:
                    retour = true;
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
        }
    }

    private static void ajouterAeroport() {
        System.out.println("\nAJOUTER UN AÉROPORT");
        String nom = lireChaine("Nom : ");
        String ville = lireChaine("Ville : ");
        String description = lireChaine("Description : ");

        Aeroport aeroport = new Aeroport(nom, ville, description);
        aeroport.ajouterAeroport(gestionDonnees);
        System.out.println("Aéroport ajouté avec succès !");
    }

    private static void affecterVolAeroport() {
        System.out.println("\nAFFECTER UN VOL À UN AÉROPORT");
        String numeroVol = lireChaine("Numéro de vol : ");

        Vol vol = gestionDonnees.trouverVol(numeroVol);
        if (vol != null) {
            System.out.println("1. Aéroport de départ");
            System.out.println("2. Aéroport d'arrivée");
            int type = lireEntier("Type d'aéroport : ");

            if (type == 1 || type == 2) {
                String nomAeroport = lireChaine("Nom de l'aéroport : ");
                Aeroport aeroport = gestionDonnees.trouverAeroport(nomAeroport);

                if (aeroport != null) {
                    aeroport.affecterVol(vol, type == 1);
                    vol.modifierVol(gestionDonnees);
                    System.out.println("Vol affecté à l'aéroport avec succès !");
                } else {
                    System.out.println("Aucun aéroport trouvé avec ce nom.");
                }
            } else {
                System.out.println("Type d'aéroport invalide.");
            }
        } else {
            System.out.println("Vol non trouvé avec ce numéro.");
        }
    }

    private static void listerAeroports() {
        System.out.println("\nLISTE DES AÉROPORTS");
        List<Aeroport> aeroports = gestionDonnees.getTousAeroports();
        if (aeroports.isEmpty()) {
            System.out.println("Aucun aéroport enregistré.");
        } else {
            for (Aeroport aeroport : aeroports) {
                System.out.println("Nom : " + aeroport.getNom());
                System.out.println("Ville : " + aeroport.getVille());
                System.out.println("Description : " + aeroport.getDescription());
                System.out.println("\n ");
            }
        }
    }

    private static void Administration() {
        boolean retour = false;
        while (!retour) {
            System.out.println("\nAdministration");
            System.out.println("1. supprimer toutes les données");
            System.out.println("2. importer des données");
            System.out.println("3. exporter les données");
            System.out.println("0. Retour au menu principal");
            System.out.println("\n ");

            int choix = lireEntier("Entrez votre choix : ");

            switch (choix) {
                case 1:
                    SuppressionTout();
                    break;
                case 2:
                    //ImportationAdministration();
                    break;
                case 3:
                    exporterDonnees();
                    break;
                case 0:
                    retour = true;
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
        }
    }

    private static void SuppressionTout() {
        System.out.println("\nSUPPRIMER TOUTES LES DONNÉES");
        System.out.println("ATTENTION: Cette opération supprimera définitivement toutes les données du système.");
        System.out.print("Êtes-vous sûr de vouloir continuer? (oui/non): ");
        String confirmation = scanner.nextLine().trim().toLowerCase();

        if (!confirmation.equals("oui")) {
            System.out.println("Opération annulée.");
            return;
        }

        File dataDir = new File("data");
        if (dataDir.exists()) {
            File[] files = dataDir.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.getName().endsWith(".dat")) {
                        boolean deleted = file.delete();
                        if (!deleted) {
                            System.err.println("Impossible de supprimer le fichier: " + file.getName());
                        }
                    }
                }
            }
        }

        gestionDonnees = new GestionDonnees();

        System.out.println("Toutes les données ont été supprimées avec succès!");
    }

    private static String lireChaine(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

    private static int lireEntier(String message) {
        while (true) {
            try {
                System.out.print(message);
                int valeur = Integer.parseInt(scanner.nextLine());
                return valeur;
            } catch (NumberFormatException e) {
                System.out.println("Veuillez entrer un nombre entier valide.");
            }
        }
    }

    private static LocalDate lireDate(String message) {
        while (true) {
            try {
                System.out.print(message);
                String dateStr = scanner.nextLine();
                return LocalDate.parse(dateStr, dateFormatter);
            } catch (Exception e) {
                System.out.println("Format de date invalide. Utilisez le format jj/mm/aaaa.");
            }
        }
    }

    private static LocalDateTime lireDateTime(String message) {
        while (true) {
            try {
                System.out.print(message);
                String dateTimeStr = scanner.nextLine();
                return LocalDateTime.parse(dateTimeStr, dateTimeFormatter);
            } catch (Exception e) {
                System.out.println("Format de date et heure invalide. Utilisez le format jj/mm/aaaa HH:mm.");
            }
        }
    }

    private static void exporterDonnees() {
        System.out.println("\nEXPORTER LES DONNÉES EN FICHIER TEXTE");

        // On crée un répertoire "exports" s'il n'existe pas
        File exportsDir = new File("exports");
        if (!exportsDir.exists() && !exportsDir.mkdirs()) {
            System.err.println("Erreur lors de la création du répertoire exports.");
            return;
        }

        // On génère un nom de fichier avec la date et l'heure de la création du fichier
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        String timestamp = LocalDateTime.now().format(formatter);
        String fileName = "exports/export_" + timestamp + ".txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            // En-tête du fichier créé
            writer.write("EXPORT DES DONNÉES DU SYSTÈME DE RÉSERVATION AÉRIENNE");
            writer.newLine();
            writer.write("Date d'exportation : " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
            writer.newLine();
            writer.write("=".repeat(60));
            writer.newLine();
            writer.newLine();

            // Exportation les pilotes
            writer.write("PILOTES");
            writer.newLine();
            writer.write("-".repeat(30));
            writer.newLine();
            List<Pilote> pilotes = gestionDonnees.getTousPilotes();
            if (pilotes.isEmpty()) {
                writer.write("Aucun pilote enregistré.");
            } else {
                for (Pilote pilote : pilotes) {
                    writer.write("ID : " + pilote.getIdentifiant());
                    writer.newLine();
                    writer.write("Nom : " + pilote.getNom());
                    writer.newLine();
                    writer.write("Adresse : " + pilote.getAdresse());
                    writer.newLine();
                    writer.write("Contact : " + pilote.getContact());
                    writer.newLine();
                    writer.write("Numéro Employé : " + pilote.getNumeroEmploye());
                    writer.newLine();
                    writer.write("Date Embauche : " + pilote.getDateEmbauche());
                    writer.newLine();
                    writer.write("Licence : " + pilote.getLicence());
                    writer.newLine();
                    writer.write("Heures de Vol : " + pilote.getHeuresDeVol());
                    writer.newLine();
                    writer.write("-".repeat(30));
                    writer.newLine();
                }
            }
            writer.newLine();

            // Exportation le personnel de cabine
            writer.write("PERSONNEL DE CABINE");
            writer.newLine();
            writer.write("-".repeat(30));
            writer.newLine();
            List<PersonnelCabine> personnels = gestionDonnees.getTousPersonnelCabine();
            if (personnels.isEmpty()) {
                writer.write("Aucun personnel de cabine enregistré.");
            } else {
                for (PersonnelCabine personnel : personnels) {
                    writer.write("ID : " + personnel.getIdentifiant());
                    writer.newLine();
                    writer.write("Nom : " + personnel.getNom());
                    writer.newLine();
                    writer.write("Adresse : " + personnel.getAdresse());
                    writer.newLine();
                    writer.write("Contact : " + personnel.getContact());
                    writer.newLine();
                    writer.write("Numéro Employé : " + personnel.getNumeroEmploye());
                    writer.newLine();
                    writer.write("Date Embauche : " + personnel.getDateEmbauche());
                    writer.newLine();
                    writer.write("Qualification : " + personnel.getQualification());
                    writer.newLine();
                    writer.write("-".repeat(30));
                    writer.newLine();
                }
            }
            writer.newLine();

            // Exportation les passagers
            writer.write("PASSAGERS");
            writer.newLine();
            writer.write("-".repeat(30));
            writer.newLine();
            List<Passager> passagers = gestionDonnees.getTousPassagers();
            if (passagers.isEmpty()) {
                writer.write("Aucun passager enregistré.");
            } else {
                for (Passager passager : passagers) {
                    writer.write("ID : " + passager.getIdentifiant());
                    writer.newLine();
                    writer.write("Nom : " + passager.getNom());
                    writer.newLine();
                    writer.write("Adresse : " + passager.getAdresse());
                    writer.newLine();
                    writer.write("Contact : " + passager.getContact());
                    writer.newLine();
                    writer.write("Passeport : " + passager.getPasseport());
                    writer.newLine();
                    writer.write("-".repeat(30));
                    writer.newLine();
                }
            }
            writer.newLine();

            // Exportation les avions
            writer.write("AVIONS");
            writer.newLine();
            writer.write("-".repeat(30));
            writer.newLine();
            List<Avion> avions = gestionDonnees.getTousAvions();
            if (avions.isEmpty()) {
                writer.write("Aucun avion enregistré.");
            } else {
                for (Avion avion : avions) {
                    writer.write("Immatriculation : " + avion.getImmatriculation());
                    writer.newLine();
                    writer.write("Modèle : " + avion.getModele());
                    writer.newLine();
                    writer.write("Capacité : " + avion.getCapacite());
                    writer.newLine();
                    writer.write("-".repeat(30));
                    writer.newLine();
                }
            }
            writer.newLine();

            // Exportation les vols
            writer.write("VOLS");
            writer.newLine();
            writer.write("-".repeat(30));
            writer.newLine();
            List<Vol> vols = gestionDonnees.getTousVols();
            if (vols.isEmpty()) {
                writer.write("Aucun vol enregistré.");
            } else {
                for (Vol vol : vols) {
                    writer.write("Numéro : " + vol.getNumeroVol());
                    writer.newLine();
                    writer.write("Origine : " + vol.getOrigine());
                    writer.newLine();
                    writer.write("Destination : " + vol.getDestination());
                    writer.newLine();
                    writer.write("Départ : " + vol.getDateHeureDepart().format(dateTimeFormatter));
                    writer.newLine();
                    writer.write("Arrivée : " + vol.getDateHeureArrivee().format(dateTimeFormatter));
                    writer.newLine();
                    writer.write("État : " + vol.getEtat());
                    writer.newLine();

                    if (vol.getAvion() != null) {
                        writer.write("Avion : " + vol.getAvion().getImmatriculation() + " (" + vol.getAvion().getModele() + ")");
                        writer.newLine();
                    }

                    if (vol.getPilote() != null) {
                        writer.write("Pilote : " + vol.getPilote().getNom() + " (ID: " + vol.getPilote().getIdentifiant() + ")");
                        writer.newLine();
                    }

                    writer.write("Nombre de passagers : " + vol.getPassagers().size());
                    writer.newLine();
                    writer.write("-".repeat(30));
                    writer.newLine();
                }
            }
            writer.newLine();

            // Exportation les réservations
            writer.write("RÉSERVATIONS");
            writer.newLine();
            writer.write("-".repeat(30));
            writer.newLine();
            List<Reservation> reservations = gestionDonnees.getToutesReservations();
            if (reservations.isEmpty()) {
                writer.write("Aucune réservation enregistrée.");
            } else {
                for (Reservation reservation : reservations) {
                    writer.write("Numéro : " + reservation.getNumeroReservation());
                    writer.newLine();
                    writer.write("Date : " + reservation.getDateReservation().format(dateFormatter));
                    writer.newLine();
                    writer.write("Statut : " + reservation.getStatut());
                    writer.newLine();
                    writer.write("Vol : " + reservation.getVol().getNumeroVol());
                    writer.newLine();
                    writer.write("Passager : " + reservation.getPassager().getNom() + " (ID : " + reservation.getPassager().getIdentifiant() + ")");
                    writer.newLine();
                    writer.write("-".repeat(30));
                    writer.newLine();
                }
            }
            writer.newLine();

            // Exportation les aéroports
            writer.write("AÉROPORTS");
            writer.newLine();
            writer.write("-".repeat(30));
            writer.newLine();
            List<Aeroport> aeroports = gestionDonnees.getTousAeroports();
            if (aeroports.isEmpty()) {
                writer.write("Aucun aéroport enregistré.");
            } else {
                for (Aeroport aeroport : aeroports) {
                    writer.write("Nom : " + aeroport.getNom());
                    writer.newLine();
                    writer.write("Ville : " + aeroport.getVille());
                    writer.newLine();
                    writer.write("Description : " + aeroport.getDescription());
                    writer.newLine();
                    writer.write("-".repeat(30));
                    writer.newLine();
                }
            }

            System.out.println("Exportation réussie ! Fichier créé : " + fileName);

        } catch (IOException e) {
            System.err.println("Erreur lors de l'exportation des données : " + e.getMessage());
        }
    }

}