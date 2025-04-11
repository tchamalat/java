import airlinereservation.*;
import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class InitialisationDonnees {

    /**
     * Initialise les données de test pour le système de réservation aérienne
     * @param gestionDonnees L'instance de GestionDonnees à utiliser
     * @return true si les données ont été initialisées, false si des données existaient déjà
     */
    public static boolean initialiserDonnees(GestionDonnees gestionDonnees) {
        // Créer le répertoire data s'il n'existe pas
        File dataDir = new File("data");
        if (!dataDir.exists()) {
            System.out.println("Création du répertoire data/ pour le stockage des fichiers...");
            boolean created = dataDir.mkdirs();
            if (!created) {
                System.err.println("Erreur lors de la création du répertoire data/");
            }
        }

        // Vérifier si des données existent déjà
        if (!gestionDonnees.getTousPilotes().isEmpty() ||
                !gestionDonnees.getTousAvions().isEmpty() ||
                !gestionDonnees.getTousVols().isEmpty()) {
            return false; // Des données existent déjà, ne pas initialiser
        }

        System.out.println("Initialisation des données de test...");

        // Créer des pilotes
        Pilote pilote1 = new Pilote("P001", "Jean Dupont", "123 Rue de Paris", "0123456789",
                "EMP001", LocalDate.now(), "LIC123", 1000);
        pilote1.ajouterPilote(gestionDonnees);

        Pilote pilote2 = new Pilote("P002", "Jacques Martin", "456 Avenue de Lyon", "0234567891",
                "EMP002", LocalDate.now().minusYears(2), "LIC456", 3000);
        pilote2.ajouterPilote(gestionDonnees);

        // Créer du personnel de cabine
        PersonnelCabine pc1 = new PersonnelCabine("PC001", "Marie Durand", "789 Boulevard de Marseille", "0345678912",
                "EMP003", LocalDate.now().minusMonths(6), "Chef de Cabine");
        pc1.ajouterPersonnelCabine(gestionDonnees);

        PersonnelCabine pc2 = new PersonnelCabine("PC002", "Sophie Petit", "101 Rue de Lille", "0456789123",
                "EMP004", LocalDate.now().minusYears(1), "Hôtesse");
        pc2.ajouterPersonnelCabine(gestionDonnees);

        // Créer des passagers
        Passager passager1 = new Passager("PA001", "Pierre Blanc", "202 Avenue de Nice", "0567891234", "PASS123");
        passager1.ajouterPassager(gestionDonnees);

        Passager passager2 = new Passager("PA002", "Lucie Noir", "303 Boulevard de Bordeaux", "0678912345", "PASS456");
        passager2.ajouterPassager(gestionDonnees);

        // Créer des avions
        Avion avion1 = new Avion("F-ABCD", "Boeing 737", 150);
        avion1.ajouterAvion(gestionDonnees);

        Avion avion2 = new Avion("F-WXYZ", "Airbus A320", 180);
        avion2.ajouterAvion(gestionDonnees);

        // Créer des aéroports
        Aeroport aeroport1 = new Aeroport("CDG", "Paris", "Aéroport Charles de Gaulle");
        aeroport1.ajouterAeroport(gestionDonnees);

        Aeroport aeroport2 = new Aeroport("NCE", "Nice", "Aéroport de Nice Côte d'Azur");
        aeroport2.ajouterAeroport(gestionDonnees);

        // Créer des vols
        LocalDateTime depart1 = LocalDateTime.now().plusDays(1);
        LocalDateTime arrivee1 = depart1.plusHours(2);
        Vol vol1 = new Vol("AF123", "Paris", "Nice", depart1, arrivee1);
        vol1.ajouterVol(gestionDonnees);

        LocalDateTime depart2 = LocalDateTime.now().plusDays(2);
        LocalDateTime arrivee2 = depart2.plusHours(3);
        Vol vol2 = new Vol("AF456", "Nice", "Paris", depart2, arrivee2);
        vol2.ajouterVol(gestionDonnees);

        // Affecter des avions aux vols
        avion1.affecterVol(vol1);
        avion2.affecterVol(vol2);

        // Affecter des pilotes et du personnel aux vols
        pilote1.affecterVol(vol1);
        pc1.affecterVol(vol1);

        pilote2.affecterVol(vol2);
        pc2.affecterVol(vol2);

        // Affecter des vols aux aéroports
        aeroport1.affecterVol(vol1, true);
        aeroport2.affecterVol(vol1, false);

        aeroport2.affecterVol(vol2, true);
        aeroport1.affecterVol(vol2, false);

        // Créer des réservations
        passager1.reserverVol(vol1, "RES001", gestionDonnees);
        passager2.reserverVol(vol2, "RES002", gestionDonnees);

        System.out.println("Données de test initialisées avec succès!");
        return true;
    }
}