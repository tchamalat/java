import airlinereservation.*;
import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class TestSystemeReservation {

    private static GestionDonnees gestionDonnees;

    public static void main(String[] args) {
        System.out.println("Exécution des tests du système de réservation aérienne...");

        // Création du répertoire data s'il n'existe pas
        File dataDir = new File("data");
        if (!dataDir.exists()) {
            System.out.println("Création du répertoire data/ pour le stockage des fichiers...");
            boolean created = dataDir.mkdirs();
            if (!created) {
                System.err.println("Erreur lors de la création du répertoire data/");
            }
        }

        gestionDonnees = new GestionDonnees();

        // Nettoyage des données existantes
        nettoyerDonnees();

        // Exécution des tests
        testCreationPersonne();
        testCreationAvion();
        testCreationVol();
        testAffectationAvionVol();
        testAffectationEquipageVol();
        testReservationVol();
        testModificationVol();
        testAnnulationVol();
        testCreationAeroport();
        testAffectationVolAeroport();
        testListingPassagersVol();

        System.out.println("Tous les tests ont été exécutés avec succès!");
    }

    private static void nettoyerDonnees() {
        // Supprimer les fichiers de données existants
        File dataDir = new File("data");
        if (dataDir.exists()) {
            File[] files = dataDir.listFiles();
            if (files != null) {
                for (File file : files) {
                    file.delete();
                }
            }
        }
    }

    private static void testCreationPersonne() {
        System.out.println("Test: Création de personnes...");

        // Création d'un pilote
        Pilote pilote = new Pilote("P001", "Jean Dupont", "123 Rue de Paris", "0123456789",
                "EMP001", LocalDate.now(), "LIC123", 1000);
        pilote.ajouterPilote(gestionDonnees);

        // Vérification
        Pilote piloteTrouve = gestionDonnees.trouverPilote("P001");
        assert piloteTrouve != null : "Le pilote n'a pas été trouvé";
        assert "Jean Dupont".equals(piloteTrouve.getNom()) : "Le nom du pilote est incorrect";
        assert "Pilote".equals(piloteTrouve.obtenirRole()) : "Le rôle du pilote est incorrect";

        // Création d'un personnel de cabine
        PersonnelCabine personnel = new PersonnelCabine("PC001", "Marie Martin", "456 Avenue de Lyon", "0987654321",
                "EMP002", LocalDate.now(), "Chef de Cabine");
        personnel.ajouterPersonnelCabine(gestionDonnees);

        // Vérification
        PersonnelCabine personnelTrouve = gestionDonnees.trouverPersonnelCabine("PC001");
        assert personnelTrouve != null : "Le personnel de cabine n'a pas été trouvé";
        assert "Marie Martin".equals(personnelTrouve.getNom()) : "Le nom du personnel est incorrect";
        assert "Personnel de Cabine".equals(personnelTrouve.obtenirRole()) : "Le rôle du personnel est incorrect";

        // Création d'un passager
        Passager passager = new Passager("PA001", "Pierre Durand", "789 Boulevard de Marseille", "0567891234", "PASS123");
        passager.ajouterPassager(gestionDonnees);

        // Vérification
        Passager passagerTrouve = gestionDonnees.trouverPassager("PA001");
        assert passagerTrouve != null : "Le passager n'a pas été trouvé";
        assert "Pierre Durand".equals(passagerTrouve.getNom()) : "Le nom du passager est incorrect";
        assert "PASS123".equals(passagerTrouve.getPasseport()) : "Le passeport du passager est incorrect";

        System.out.println("Test de création de personnes réussi!");
    }

    private static void testCreationAvion() {
        System.out.println("Test: Création d'avions...");

        // Création d'un avion
        Avion avion = new Avion("F-ABCD", "Boeing 737", 150);
        avion.ajouterAvion(gestionDonnees);

        // Vérification
        Avion avionTrouve = gestionDonnees.trouverAvion("F-ABCD");
        assert avionTrouve != null : "L'avion n'a pas été trouvé";
        assert "Boeing 737".equals(avionTrouve.getModele()) : "Le modèle de l'avion est incorrect";
        assert 150 == avionTrouve.getCapacite() : "La capacité de l'avion est incorrecte";

        System.out.println("Test de création d'avions réussi!");
    }

    private static void testCreationVol() {
        System.out.println("Test: Création de vols...");

        // Création d'un vol
        LocalDateTime depart = LocalDateTime.now().plusDays(1);
        LocalDateTime arrivee = depart.plusHours(2);
        Vol vol = new Vol("AF123", "Paris", "Nice", depart, arrivee);
        vol.ajouterVol(gestionDonnees);

        // Vérification
        Vol volTrouve = gestionDonnees.trouverVol("AF123");
        assert volTrouve != null : "Le vol n'a pas été trouvé";
        assert "Paris".equals(volTrouve.getOrigine()) : "L'origine du vol est incorrecte";
        assert "Nice".equals(volTrouve.getDestination()) : "La destination du vol est incorrecte";
        assert "Planifié".equals(volTrouve.getEtat()) : "L'état du vol est incorrect";

        System.out.println("Test de création de vols réussi!");
    }

    private static void testAffectationAvionVol() {
        System.out.println("Test: Affectation d'avions aux vols...");

        // Création d'un avion et d'un vol
        Avion avion = new Avion("F-WXYZ", "Airbus A320", 180);
        avion.ajouterAvion(gestionDonnees);

        LocalDateTime depart = LocalDateTime.now().plusDays(2);
        LocalDateTime arrivee = depart.plusHours(3);
        Vol vol = new Vol("AF456", "Lyon", "Toulouse", depart, arrivee);
        vol.ajouterVol(gestionDonnees);

        // Affectation de l'avion au vol
        boolean resultat = avion.affecterVol(vol);
        assert resultat : "L'affectation de l'avion au vol a échoué";

        // Vérification
        assert avion.equals(vol.getAvion()) : "L'avion n'a pas été correctement affecté au vol";
        assert vol.getAvion().getVols().contains(vol) : "Le vol n'a pas été ajouté à la liste des vols de l'avion";

        // Test de disponibilité
        assert avion.verifierDisponibilite(depart.minusDays(1), depart.minusHours(1)) : "L'avion devrait être disponible pour cette période";
        assert !avion.verifierDisponibilite(depart, arrivee) : "L'avion ne devrait pas être disponible pour cette période";

        System.out.println("Test d'affectation d'avions aux vols réussi!");
    }

    private static void testAffectationEquipageVol() {
        System.out.println("Test: Affectation d'équipage aux vols...");

        // Création d'un pilote, d'un personnel de cabine et d'un vol
        Pilote pilote = new Pilote("P002", "Jacques Martin", "123 Rue de Paris", "0123456789",
                "EMP003", LocalDate.now(), "LIC456", 2000);
        pilote.ajouterPilote(gestionDonnees);

        PersonnelCabine personnel = new PersonnelCabine("PC002", "Sophie Petit", "456 Avenue de Lyon", "0987654321",
                "EMP004", LocalDate.now(), "Hôtesse");
        personnel.ajouterPersonnelCabine(gestionDonnees);

        LocalDateTime depart = LocalDateTime.now().plusDays(3);
        LocalDateTime arrivee = depart.plusHours(4);
        Vol vol = new Vol("AF789", "Paris", "Madrid", depart, arrivee);
        vol.ajouterVol(gestionDonnees);

        // Affectation de l'équipage au vol
        pilote.affecterVol(vol);
        personnel.affecterVol(vol);

        // Vérification
        assert pilote.equals(vol.getPilote()) : "Le pilote n'a pas été correctement affecté au vol";
        assert vol.getEquipageCabine().contains(personnel) : "Le personnel de cabine n'a pas été correctement affecté au vol";
        assert vol.equals(pilote.obtenirVol("AF789")) : "Le vol n'a pas été correctement associé au pilote";
        assert vol.equals(personnel.obtenirVol("AF789")) : "Le vol n'a pas été correctement associé au personnel de cabine";

        System.out.println("Test d'affectation d'équipage aux vols réussi!");
    }

    private static void testReservationVol() {
        System.out.println("Test: Réservation de vols...");

        // Création d'un passager et d'un vol
        Passager passager = new Passager("PA002", "Lucie Dubois", "789 Boulevard de Marseille", "0567891234", "PASS456");
        passager.ajouterPassager(gestionDonnees);

        LocalDateTime depart = LocalDateTime.now().plusDays(4);
        LocalDateTime arrivee = depart.plusHours(5);
        Vol vol = new Vol("AF101", "Nice", "Paris", depart, arrivee);
        vol.ajouterVol(gestionDonnees);

        // Réservation du vol
        Reservation reservation = passager.reserverVol(vol, "RES001", gestionDonnees);

        // Vérification
        assert reservation != null : "La réservation n'a pas été créée";
        assert "Confirmée".equals(reservation.getStatut()) : "Le statut de la réservation est incorrect";
        assert passager.getReservations().contains(reservation) : "La réservation n'a pas été ajoutée à la liste des réservations du passager";
        assert vol.getPassagers().contains(passager) : "Le passager n'a pas été ajouté à la liste des passagers du vol";

        // Annulation de la réservation
        boolean resultatAnnulation = passager.annulerReservation("RES001", gestionDonnees);
        assert resultatAnnulation : "L'annulation de la réservation a échoué";

        // Vérification après annulation
        Reservation reservationTrouvee = gestionDonnees.trouverReservation("RES001");
        assert "Annulée".equals(reservationTrouvee.getStatut()) : "Le statut de la réservation après annulation est incorrect";
        assert !passager.getReservations().contains(reservation) : "La réservation n'a pas été retirée de la liste des réservations du passager";
        assert !vol.getPassagers().contains(passager) : "Le passager n'a pas été retiré de la liste des passagers du vol";

        System.out.println("Test de réservation de vols réussi!");
    }

    private static void testModificationVol() {
        System.out.println("Test: Modification de vols...");

        // Création d'un vol
        LocalDateTime depart = LocalDateTime.now().plusDays(5);
        LocalDateTime arrivee = depart.plusHours(6);
        Vol vol = new Vol("AF202", "Marseille", "Bordeaux", depart, arrivee);
        vol.ajouterVol(gestionDonnees);

        // Modification du vol
        LocalDateTime nouveauDepart = depart.plusHours(1);
        LocalDateTime nouvelleArrivee = arrivee.plusHours(1);
        boolean resultatModification = vol.modifierVol("Marseille", "Nantes", nouveauDepart, nouvelleArrivee, gestionDonnees);
        assert resultatModification : "La modification du vol a échoué";

        // Vérification
        Vol volModifie = gestionDonnees.trouverVol("AF202");
        assert "Nantes".equals(volModifie.getDestination()) : "La destination du vol après modification est incorrecte";
        assert nouveauDepart.equals(volModifie.getDateHeureDepart()) : "La date de départ du vol après modification est incorrecte";
        assert nouvelleArrivee.equals(volModifie.getDateHeureArrivee()) : "La date d'arrivée du vol après modification est incorrecte";

        System.out.println("Test de modification de vols réussi!");
    }

    private static void testAnnulationVol() {
        System.out.println("Test: Annulation de vols...");

        // Création d'un vol
        LocalDateTime depart = LocalDateTime.now().plusDays(6);
        LocalDateTime arrivee = depart.plusHours(7);
        Vol vol = new Vol("AF303", "Strasbourg", "Lille", depart, arrivee);
        vol.ajouterVol(gestionDonnees);

        // Annulation du vol
        boolean resultatAnnulation = vol.annulerVol(gestionDonnees);
        assert resultatAnnulation : "L'annulation du vol a échoué";

        // Vérification
        Vol volAnnule = gestionDonnees.trouverVol("AF303");
        assert "Annulé".equals(volAnnule.getEtat()) : "L'état du vol après annulation est incorrect";

        // Tentative d'annulation d'un vol déjà annulé
        boolean resultatSecondAnnulation = vol.annulerVol(gestionDonnees);
        assert !resultatSecondAnnulation : "La seconde annulation du vol aurait dû échouer";

        System.out.println("Test d'annulation de vols réussi!");
    }

    private static void testCreationAeroport() {
        System.out.println("Test: Création d'aéroports...");

        // Création d'un aéroport
        Aeroport aeroport = new Aeroport("CDG", "Paris", "Aéroport Charles de Gaulle");
        aeroport.ajouterAeroport(gestionDonnees);

        // Vérification
        Aeroport aeroportTrouve = gestionDonnees.trouverAeroport("CDG");
        assert aeroportTrouve != null : "L'aéroport n'a pas été trouvé";
        assert "Paris".equals(aeroportTrouve.getVille()) : "La ville de l'aéroport est incorrecte";
        assert "Aéroport Charles de Gaulle".equals(aeroportTrouve.getDescription()) : "La description de l'aéroport est incorrecte";

        System.out.println("Test de création d'aéroports réussi!");
    }

    private static void testAffectationVolAeroport() {
        System.out.println("Test: Affectation de vols aux aéroports...");

        // Création d'un aéroport et d'un vol
        Aeroport aeroportDepart = new Aeroport("ORY", "Paris", "Aéroport d'Orly");
        aeroportDepart.ajouterAeroport(gestionDonnees);

        Aeroport aeroportArrivee = new Aeroport("NCE", "Nice", "Aéroport de Nice Côte d'Azur");
        aeroportArrivee.ajouterAeroport(gestionDonnees);

        LocalDateTime depart = LocalDateTime.now().plusDays(7);
        LocalDateTime arrivee = depart.plusHours(8);
        Vol vol = new Vol("AF404", "Paris", "Nice", depart, arrivee);
        vol.ajouterVol(gestionDonnees);

        // Affectation du vol aux aéro ports
        aeroportDepart.affecterVol(vol, true);
        aeroportArrivee.affecterVol(vol, false);

        // Vérification
        assert aeroportDepart.getVolsDepart().contains(vol) : "Le vol n'a pas été ajouté à la liste des vols au départ de l'aéroport";
        assert aeroportArrivee.getVolsArrivee().contains(vol) : "Le vol n'a pas été ajouté à la liste des vols à l'arrivée de l'aéroport";
        assert aeroportDepart.equals(vol.getAeroport()) : "L'aéroport de départ n'a pas été correctement associé au vol";

        System.out.println("Test d'affectation de vols aux aéroports réussi!");
    }

    private static void testListingPassagersVol() {
        System.out.println("Test: Listing des passagers d'un vol...");

        // Création d'un vol et de passagers
        LocalDateTime depart = LocalDateTime.now().plusDays(8);
        LocalDateTime arrivee = depart.plusHours(9);
        Vol vol = new Vol("AF505", "Toulouse", "Lyon", depart, arrivee);
        vol.ajouterVol(gestionDonnees);

        Passager passager1 = new Passager("PA003", "Thomas Blanc", "123 Rue de Paris", "0123456789", "PASS789");
        passager1.ajouterPassager(gestionDonnees);

        Passager passager2 = new Passager("PA004", "Julie Noir", "456 Avenue de Lyon", "0987654321", "PASS101");
        passager2.ajouterPassager(gestionDonnees);

        // Réservation du vol par les passagers
        passager1.reserverVol(vol, "RES002", gestionDonnees);
        passager2.reserverVol(vol, "RES003", gestionDonnees);

        // Vérification du listing des passagers
        List<Passager> passagers = vol.listingPassager();
        assert passagers.size() == 2 : "Le nombre de passagers du vol est incorrect";
        assert passagers.contains(passager1) : "Le premier passager n'est pas dans la liste des passagers du vol";
        assert passagers.contains(passager2) : "Le second passager n'est pas dans la liste des passagers du vol";

        System.out.println("Test de listing des passagers d'un vol réussi!");
    }
}