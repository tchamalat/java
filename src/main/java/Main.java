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
            System.out.println("1. supprimer des données");
            System.out.println("2. importer des données");
            System.out.println("3. exporter les données");
            System.out.println("0. Retour au menu principal");
            System.out.println("\n ");

            int choix = lireEntier("Entrez votre choix : ");

            switch (choix) {
                case 1:
                    SuppressionAdministration();
                    break;
                case 2:
                    ImportationAdministration();
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

    private static void ImportationAdministration() {
        System.out.println("\n=== IMPORTER DES DONNÉES DEPUIS UN FICHIER TEXTE ===");

        // Vérifier si le répertoire exports existe
        File exportsDir = new File("exports");
        if (!exportsDir.exists() || !exportsDir.isDirectory()) {
            System.out.println("Le répertoire 'exports' n'existe pas. Veuillez d'abord exporter des données.");
            return;
        }

        // Lister les fichiers d'exportation disponibles
        File[] exportFiles = exportsDir.listFiles((dir, name) -> name.startsWith("export_") && name.endsWith(".txt"));

        if (exportFiles == null || exportFiles.length == 0) {
            System.out.println("Aucun fichier d'exportation trouvé dans le répertoire 'exports'.");
            return;
        }

        System.out.println("Fichiers d'exportation disponibles:");
        for (int i = 0; i < exportFiles.length; i++) {
            System.out.println((i + 1) + ". " + exportFiles[i].getName());
        }

        int choix = lireEntier("Sélectionnez un fichier (1-" + exportFiles.length + "): ");
        if (choix < 1 || choix > exportFiles.length) {
            System.out.println("Choix invalide.");
            return;
        }

        File selectedFile = exportFiles[choix - 1];
        System.out.println("Importation des données depuis: " + selectedFile.getName());

        try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) {
            String line;
            String currentSection = null;

            // Variables pour stocker temporairement les données d'une entité
            String id = null;
            String nom = null;
            String adresse = null;
            String contact = null;
            String numeroEmploye = null;
            String dateEmbaucheStr = null;
            String licence = null;
            String heuresDeVolStr = null;
            String qualification = null;
            String passeport = null;
            String immatriculation = null;
            String modele = null;
            String capaciteStr = null;
            String numeroVol = null;
            String origine = null;
            String destination = null;
            String departStr = null;
            String arriveeStr = null;
            String etat = null;
            String avionRef = null;
            String piloteRef = null;
            String ville = null;
            String description = null;
            String numeroReservation = null;
            String dateReservationStr = null;
            String statut = null;
            String volRef = null;
            String passagerRef = null;

            // Sauter les lignes d'en-tête
            while ((line = reader.readLine()) != null && !line.equals("PILOTES")) {
                // Ignorer les lignes d'en-tête
            }

            currentSection = "PILOTES";

            while ((line = reader.readLine()) != null) {
                // Vérifier si on change de section
                if (line.equals("PERSONNEL DE CABINE") || line.equals("PASSAGERS") ||
                        line.equals("AVIONS") || line.equals("VOLS") ||
                        line.equals("RÉSERVATIONS") || line.equals("AÉROPORTS")) {

                    // Traiter l'entité précédente si nécessaire
                    if (currentSection.equals("PILOTES") && id != null) {
                        ajouterPiloteImport(id, nom, adresse, contact, numeroEmploye, dateEmbaucheStr, licence, heuresDeVolStr);
                        // Réinitialiser les variables
                        id = nom = adresse = contact = numeroEmploye = dateEmbaucheStr = licence = heuresDeVolStr = null;
                    } else if (currentSection.equals("PERSONNEL DE CABINE") && id != null) {
                        ajouterPersonnelCabineImport(id, nom, adresse, contact, numeroEmploye, dateEmbaucheStr, qualification);
                        // Réinitialiser les variables
                        id = nom = adresse = contact = numeroEmploye = dateEmbaucheStr = qualification = null;
                    } else if (currentSection.equals("PASSAGERS") && id != null) {
                        ajouterPassagerImport(id, nom, adresse, contact, passeport);
                        // Réinitialiser les variables
                        id = nom = adresse = contact = passeport = null;
                    } else if (currentSection.equals("AVIONS") && immatriculation != null) {
                        ajouterAvionImport(immatriculation, modele, capaciteStr);
                        // Réinitialiser les variables
                        immatriculation = modele = capaciteStr = null;
                    } else if (currentSection.equals("VOLS") && numeroVol != null) {
                        ajouterVolImport(numeroVol, origine, destination, departStr, arriveeStr, etat);
                        // Réinitialiser les variables
                        numeroVol = origine = destination = departStr = arriveeStr = etat = avionRef = piloteRef = null;
                    } else if (currentSection.equals("AÉROPORTS") && nom != null) {
                        ajouterAeroportImport(nom, ville, description);
                        // Réinitialiser les variables
                        nom = ville = description = null;
                    } else if (currentSection.equals("RÉSERVATIONS") && numeroReservation != null) {
                        ajouterReservationImport(numeroReservation, dateReservationStr, statut, volRef, passagerRef);
                        // Réinitialiser les variables
                        numeroReservation = dateReservationStr = statut = volRef = passagerRef = null;
                    }

                    currentSection = line;
                    continue;
                }

                // Ignorer les lignes de séparation
                if (line.startsWith("----") || line.trim().isEmpty()) {
                    continue;
                }

                // Traiter les données selon la section actuelle
                if (currentSection.equals("PILOTES")) {
                    if (line.startsWith("ID: ")) {
                        // Si on a déjà un ID, c'est qu'on commence un nouveau pilote
                        if (id != null) {
                            ajouterPiloteImport(id, nom, adresse, contact, numeroEmploye, dateEmbaucheStr, licence, heuresDeVolStr);
                            // Réinitialiser les variables
                            id = nom = adresse = contact = numeroEmploye = dateEmbaucheStr = licence = heuresDeVolStr = null;
                        }
                        id = line.substring(4).trim();
                    } else if (line.startsWith("Nom: ")) {
                        nom = line.substring(5).trim();
                    } else if (line.startsWith("Adresse: ")) {
                        adresse = line.substring(9).trim();
                    } else if (line.startsWith("Contact: ")) {
                        contact = line.substring(9).trim();
                    } else if (line.startsWith("Numéro Employé: ")) {
                        numeroEmploye = line.substring(16).trim();
                    } else if (line.startsWith("Date Embauche: ")) {
                        dateEmbaucheStr = line.substring(15).trim();
                    } else if (line.startsWith("Licence: ")) {
                        licence = line.substring(9).trim();
                    } else if (line.startsWith("Heures de Vol: ")) {
                        heuresDeVolStr = line.substring(15).trim();
                    }
                } else if (currentSection.equals("PERSONNEL DE CABINE")) {
                    if (line.startsWith("ID: ")) {
                        // Si on a déjà un ID, c'est qu'on commence un nouveau personnel
                        if (id != null) {
                            ajouterPersonnelCabineImport(id, nom, adresse, contact, numeroEmploye, dateEmbaucheStr, qualification);
                            // Réinitialiser les variables
                            id = nom = adresse = contact = numeroEmploye = dateEmbaucheStr = qualification = null;
                        }
                        id = line.substring(4).trim();
                    } else if (line.startsWith("Nom: ")) {
                        nom = line.substring(5).trim();
                    } else if (line.startsWith("Adresse: ")) {
                        adresse = line.substring(9).trim();
                    } else if (line.startsWith("Contact: ")) {
                        contact = line.substring(9).trim();
                    } else if (line.startsWith("Numéro Employé: ")) {
                        numeroEmploye = line.substring(16).trim();
                    } else if (line.startsWith("Date Embauche: ")) {
                        dateEmbaucheStr = line.substring(15).trim();
                    } else if (line.startsWith("Qualification: ")) {
                        qualification = line.substring(15).trim();
                    }
                } else if (currentSection.equals("PASSAGERS")) {
                    if (line.startsWith("ID: ")) {
                        // Si on a déjà un ID, c'est qu'on commence un nouveau passager
                        if (id != null) {
                            ajouterPassagerImport(id, nom, adresse, contact, passeport);
                            // Réinitialiser les variables
                            id = nom = adresse = contact = passeport = null;
                        }
                        id = line.substring(4).trim();
                    } else if (line.startsWith("Nom: ")) {
                        nom = line.substring(5).trim();
                    } else if (line.startsWith("Adresse: ")) {
                        adresse = line.substring(9).trim();
                    } else if (line.startsWith("Contact: ")) {
                        contact = line.substring(9).trim();
                    } else if (line.startsWith("Passeport: ")) {
                        passeport = line.substring(11).trim();
                    }
                } else if (currentSection.equals("AVIONS")) {
                    if (line.startsWith("Immatriculation: ")) {
                        // Si on a déjà une immatriculation, c'est qu'on commence un nouvel avion
                        if (immatriculation != null) {
                            ajouterAvionImport(immatriculation, modele, capaciteStr);
                            // Réinitialiser les variables
                            immatriculation = modele = capaciteStr = null;
                        }
                        immatriculation = line.substring(17).trim();
                    } else if (line.startsWith("Modèle: ")) {
                        modele = line.substring(8).trim();
                    } else if (line.startsWith("Capacité: ")) {
                        capaciteStr = line.substring(10).trim();
                    }
                } else if (currentSection.equals("VOLS")) {
                    if (line.startsWith("Numéro: ")) {
                        // Si on a déjà un numéro, c'est qu'on commence un nouveau vol
                        if (numeroVol != null) {
                            ajouterVolImport(numeroVol, origine, destination, departStr, arriveeStr, etat);
                            // Réinitialiser les variables
                            numeroVol = origine = destination = departStr = arriveeStr = etat = avionRef = piloteRef = null;
                        }
                        numeroVol = line.substring(8).trim();
                    } else if (line.startsWith("Origine: ")) {
                        origine = line.substring(9).trim();
                    } else if (line.startsWith("Destination: ")) {
                        destination = line.substring(13).trim();
                    } else if (line.startsWith("Départ: ")) {
                        departStr = line.substring(8).trim();
                    } else if (line.startsWith("Arrivée: ")) {
                        arriveeStr = line.substring(9).trim();
                    } else if (line.startsWith("État: ")) {
                        etat = line.substring(6).trim();
                    } else if (line.startsWith("Avion: ")) {
                        avionRef = line.substring(7).trim();
                        // Extraire l'immatriculation de l'avion (avant la parenthèse)
                        if (avionRef.contains("(")) {
                            avionRef = avionRef.substring(0, avionRef.indexOf("(")).trim();
                        }
                    } else if (line.startsWith("Pilote: ")) {
                        piloteRef = line.substring(8).trim();
                        // Extraire l'ID du pilote (entre parenthèses)
                        if (piloteRef.contains("(ID: ") && piloteRef.contains(")")) {
                            piloteRef = piloteRef.substring(piloteRef.indexOf("(ID: ") + 5, piloteRef.indexOf(")")).trim();
                        }
                    }
                } else if (currentSection.equals("AÉROPORTS")) {
                    if (line.startsWith("Nom: ")) {
                        // Si on a déjà un nom, c'est qu'on commence un nouvel aéroport
                        if (nom != null) {
                            ajouterAeroportImport(nom, ville, description);
                            // Réinitialiser les variables
                            nom = ville = description = null;
                        }
                        nom = line.substring(5).trim();
                    } else if (line.startsWith("Ville: ")) {
                        ville = line.substring(7).trim();
                    } else if (line.startsWith("Description: ")) {
                        description = line.substring(13).trim();
                    }
                } else if (currentSection.equals("RÉSERVATIONS")) {
                    if (line.startsWith("Numéro: ")) {
                        // Si on a déjà un numéro, c'est qu'on commence une nouvelle réservation
                        if (numeroReservation != null) {
                            ajouterReservationImport(numeroReservation, dateReservationStr, statut, volRef, passagerRef);
                            // Réinitialiser les variables
                            numeroReservation = dateReservationStr = statut = volRef = passagerRef = null;
                        }
                        numeroReservation = line.substring(8).trim();
                    } else if (line.startsWith("Date: ")) {
                        dateReservationStr = line.substring(6).trim();
                    } else if (line.startsWith("Statut: ")) {
                        statut = line.substring(8).trim();
                    } else if (line.startsWith("Vol: ")) {
                        volRef = line.substring(5).trim();
                    } else if (line.startsWith("Passager: ")) {
                        passagerRef = line.substring(10).trim();
                        // Extraire l'ID du passager (entre parenthèses)
                        if (passagerRef.contains("(ID: ") && passagerRef.contains(")")) {
                            passagerRef = passagerRef.substring(passagerRef.indexOf("(ID: ") + 5, passagerRef.indexOf(")")).trim();
                        }
                    }
                }
            }

            // Traiter la dernière entité si nécessaire
            if (currentSection.equals("PILOTES") && id != null) {
                ajouterPiloteImport(id, nom, adresse, contact, numeroEmploye, dateEmbaucheStr, licence, heuresDeVolStr);
            } else if (currentSection.equals("PERSONNEL DE CABINE") && id != null) {
                ajouterPersonnelCabineImport(id, nom, adresse, contact, numeroEmploye, dateEmbaucheStr, qualification);
            } else if (currentSection.equals("PASSAGERS") && id != null) {
                ajouterPassagerImport(id, nom, adresse, contact, passeport);
            } else if (currentSection.equals("AVIONS") && immatriculation != null) {
                ajouterAvionImport(immatriculation, modele, capaciteStr);
            } else if (currentSection.equals("VOLS") && numeroVol != null) {
                ajouterVolImport(numeroVol, origine, destination, departStr, arriveeStr, etat);
            } else if (currentSection.equals("AÉROPORTS") && nom != null) {
                ajouterAeroportImport(nom, ville, description);
            } else if (currentSection.equals("RÉSERVATIONS") && numeroReservation != null) {
                ajouterReservationImport(numeroReservation, dateReservationStr, statut, volRef, passagerRef);
            }

            System.out.println("Importation terminée avec succès!");

        } catch (IOException e) {
            System.err.println("Erreur lors de l'importation des données: " + e.getMessage());
        }
    }

    // Méthodes auxiliaires pour l'importation
    private static void ajouterPiloteImport(String id, String nom, String adresse, String contact,
                                            String numeroEmploye, String dateEmbaucheStr, String licence, String heuresDeVolStr) {
        try {
            // Vérifier si le pilote existe déjà
            if (gestionDonnees.trouverPilote(id) != null) {
                System.out.println("Le pilote avec l'ID " + id + " existe déjà. Ignoré.");
                return;
            }

            LocalDate dateEmbauche = LocalDate.parse(dateEmbaucheStr);
            int heuresDeVol = Integer.parseInt(heuresDeVolStr);

            Pilote pilote = new Pilote(id, nom, adresse, contact, numeroEmploye, dateEmbauche, licence, heuresDeVol);
            pilote.ajouterPilote(gestionDonnees);
            System.out.println("Pilote importé: " + nom + " (ID: " + id + ")");
        } catch (Exception e) {
            System.err.println("Erreur lors de l'importation du pilote " + id + ": " + e.getMessage());
        }
    }

    private static void ajouterPersonnelCabineImport(String id, String nom, String adresse, String contact,
                                                     String numeroEmploye, String dateEmbaucheStr, String qualification) {
        try {
            // Vérifier si le personnel existe déjà
            if (gestionDonnees.trouverPersonnelCabine(id) != null) {
                System.out.println("Le personnel de cabine avec l'ID " + id + " existe déjà. Ignoré.");
                return;
            }

            LocalDate dateEmbauche = LocalDate.parse(dateEmbaucheStr);

            PersonnelCabine personnel = new PersonnelCabine(id, nom, adresse, contact, numeroEmploye, dateEmbauche, qualification);
            personnel.ajouterPersonnelCabine(gestionDonnees);
            System.out.println("Personnel de cabine importé: " + nom + " (ID: " + id + ")");
        } catch (Exception e) {
            System.err.println("Erreur lors de l'importation du personnel de cabine " + id + ": " + e.getMessage());
        }
    }

    private static void ajouterPassagerImport(String id, String nom, String adresse, String contact, String passeport) {
        try {
            // Vérifier si le passager existe déjà
            if (gestionDonnees.trouverPassager(id) != null) {
                System.out.println("Le passager avec l'ID " + id + " existe déjà. Ignoré.");
                return;
            }

            Passager passager = new Passager(id, nom, adresse, contact, passeport);
            passager.ajouterPassager(gestionDonnees);
            System.out.println("Passager importé: " + nom + " (ID: " + id + ")");
        } catch (Exception e) {
            System.err.println("Erreur lors de l'importation du passager " + id + ": " + e.getMessage());
        }
    }

    private static void ajouterAvionImport(String immatriculation, String modele, String capaciteStr) {
        try {
            // Vérifier si l'avion existe déjà
            if (gestionDonnees.trouverAvion(immatriculation) != null) {
                System.out.println("L'avion avec l'immatriculation " + immatriculation + " existe déjà. Ignoré.");
                return;
            }

            int capacite = Integer.parseInt(capaciteStr);

            Avion avion = new Avion(immatriculation, modele, capacite);
            avion.ajouterAvion(gestionDonnees);
            System.out.println("Avion importé: " + modele + " (Immatriculation: " + immatriculation + ")");
        } catch (Exception e) {
            System.err.println("Erreur lors de l'importation de l'avion " + immatriculation + ": " + e.getMessage());
        }
    }

    private static void ajouterVolImport(String numeroVol, String origine, String destination,
                                         String departStr, String arriveeStr, String etat) {
        try {
            // Vérifier si le vol existe déjà
            if (gestionDonnees.trouverVol(numeroVol) != null) {
                System.out.println("Le vol avec le numéro " + numeroVol + " existe déjà. Ignoré.");
                return;
            }

            LocalDateTime depart = LocalDateTime.parse(departStr, dateTimeFormatter);
            LocalDateTime arrivee = LocalDateTime.parse(arriveeStr, dateTimeFormatter);

            Vol vol = new Vol(numeroVol, origine, destination, depart, arrivee);
            vol.setEtat(etat);
            vol.ajouterVol(gestionDonnees);
            System.out.println("Vol importé: " + numeroVol + " (" + origine + " -> " + destination + ")");
        } catch (Exception e) {
            System.err.println("Erreur lors de l'importation du vol " + numeroVol + ": " + e.getMessage());
        }
    }

    private static void ajouterAeroportImport(String nom, String ville, String description) {
        try {
            // Vérifier si l'aéroport existe déjà
            if (gestionDonnees.trouverAeroport(nom) != null) {
                System.out.println("L'aéroport avec le nom " + nom + " existe déjà. Ignoré.");
                return;
            }

            Aeroport aeroport = new Aeroport(nom, ville, description);
            aeroport.ajouterAeroport(gestionDonnees);
            System.out.println("Aéroport importé: " + nom + " (" + ville + ")");
        } catch (Exception e) {
            System.err.println("Erreur lors de l'importation de l'aéroport " + nom + ": " + e.getMessage());
        }
    }

    private static void ajouterReservationImport(String numeroReservation, String dateReservationStr,
                                                 String statut, String volRef, String passagerRef) {
        try {
            // Vérifier si la réservation existe déjà
            if (gestionDonnees.trouverReservation(numeroReservation) != null) {
                System.out.println("La réservation avec le numéro " + numeroReservation + " existe déjà. Ignorée.");
                return;
            }

            // Trouver le vol et le passager référencés
            Vol vol = gestionDonnees.trouverVol(volRef);
            Passager passager = gestionDonnees.trouverPassager(passagerRef);

            if (vol == null || passager == null) {
                System.out.println("Impossible de trouver le vol ou le passager pour la réservation " + numeroReservation + ". Ignorée.");
                return;
            }

            // Créer la réservation
            Reservation reservation = passager.reserverVol(vol, numeroReservation, gestionDonnees);

            // Mettre à jour le statut si nécessaire
            if (statut.equals("Annulée")) {
                reservation.annulerReservation();
                reservation.modifierReservation(gestionDonnees);
            }

            System.out.println("Réservation importée: " + numeroReservation);
        } catch (Exception e) {
            System.err.println("Erreur lors de l'importation de la réservation " + numeroReservation + ": " + e.getMessage());
        }
    }

    private static void SuppressionAdministration() {
        boolean retour = false;
        while (!retour) {
            System.out.println("\nsupprimer des données");
            System.out.println("1. supprimer toutes les données");
            System.out.println("2. supprimer un fichier");
            System.out.println("3. retour");
            System.out.println("0. Retour au menu principal");
            System.out.println("\n ");

            int choix = lireEntier("Entrez votre choix : ");

            switch (choix) {
                case 1:
                    SuppressionTout();
                    break;
                case 2:
                    SuppressionFichier();
                    break;
                case 3:
                    Administration();
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
        System.out.println("\n=== SUPPRIMER TOUTES LES DONNÉES ===");
        System.out.println("ATTENTION: Cette opération supprimera définitivement toutes les données du système.");
        System.out.print("Êtes-vous sûr de vouloir continuer? (oui/non): ");
        String confirmation = scanner.nextLine().trim().toLowerCase();

        if (!confirmation.equals("oui")) {
            System.out.println("Opération annulée.");
            return;
        }

        // Supprimer les fichiers .dat
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

        // Réinitialiser l'instance de GestionDonnees
        gestionDonnees = new GestionDonnees();

        System.out.println("Toutes les données ont été supprimées avec succès!");
    }

    private static void SuppressionFichier() {
        boolean retour = false;
        while (!retour) {
            System.out.println("\nSuppression d'un fichier");
            System.out.println("1. Suppression des Personnes");
            System.out.println("2. Suppression des Vols");
            System.out.println("3. Suppression des Réservations");
            System.out.println("4. Suppression des Avions");
            System.out.println("5. Suppression des Aéroports");
            System.out.println("0. Quitter");
            System.out.println("\n ");

            int choix = lireEntier("Entrez votre choix : ");

            switch (choix) {
                case 1:
                    //SuppressionPersonnes();
                    break;
                case 2:
                    //SuppressionVols();
                    break;
                case 3:
                    //SuppressionReservations();
                    break;
                case 4:
                    //SuppressionAvions();
                    break;
                case 5:
                    //SuppressionAeroports();
                    break;
                case 0:
                    retour = true;
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
        }
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