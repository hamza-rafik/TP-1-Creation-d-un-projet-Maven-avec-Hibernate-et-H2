package com.tp;

import com.tp.dao.EtudiantDao;
import com.tp.model.Etudiant;
import com.tp.util.HibernateUtil;

import java.util.List;

/**
 * Application principale démontrant le fonctionnement d'Hibernate avec H2.
 * TP 1 : Création d'un projet Maven avec Hibernate et H2.
 */
public class Main {

    public static void main(String[] args) {
        printBanner();

        EtudiantDao dao = new EtudiantDao();

        // ===============================================================
        // 1. CRÉATION ET INSERTION D'ÉTUDIANTS (CREATE)
        // ===============================================================
        System.out.println("\n=======================================================");
        System.out.println(">>> ÉTAPE 1 : Insertion d'étudiants dans la base H2");
        System.out.println("=======================================================");

        Etudiant e1 = new Etudiant("Rafik", "Hamza", "hamza.rafik@example.com", "Génie Logiciel & IA");
        Etudiant e2 = new Etudiant("Alami", "Yassine", "yassine.alami@example.com", "Systèmes & Réseaux");
        Etudiant e3 = new Etudiant("Bennani", "Sara", "sara.bennani@example.com", "Data Science & Big Data");

        dao.saveEtudiant(e1);
        dao.saveEtudiant(e2);
        dao.saveEtudiant(e3);

        // ===============================================================
        // 2. RÉCUPÉRATION ET AFFICHAGE DE TOUS LES ÉTUDIANTS (READ ALL)
        // ===============================================================
        System.out.println("\n=======================================================");
        System.out.println(">>> ÉTAPE 2 : Liste de tous les étudiants (Requête HQL)");
        System.out.println("=======================================================");

        List<Etudiant> etudiants = dao.getAllEtudiants();
        afficherTableEtudiants(etudiants);

        // ===============================================================
        // 3. RECHERCHE D'UN ÉTUDIANT PAR ID (READ BY ID)
        // ===============================================================
        System.out.println("\n=======================================================");
        System.out.println(">>> ÉTAPE 3 : Recherche d'un étudiant par son ID = 1");
        System.out.println("=======================================================");

        Etudiant trouve = dao.getEtudiantById(1L);
        if (trouve != null) {
            System.out.println("  [RÉSULTAT] Étudiant trouvé avec succès :");
            System.out.println("  -> " + trouve);
        } else {
            System.out.println("  [RÉSULTAT] Aucun étudiant trouvé.");
        }

        // ===============================================================
        // 4. MISE À JOUR DES DONNÉES D'UN ÉTUDIANT (UPDATE)
        // ===============================================================
        System.out.println("\n=======================================================");
        System.out.println(">>> ÉTAPE 4 : Mise à jour de l'étudiant ID = 1");
        System.out.println("=======================================================");

        if (trouve != null) {
            System.out.println("  Avant modification : " + trouve);
            trouve.setFiliere("Architecture Cloud & Cybersécurité");
            trouve.setEmail("hamza.rafik.pro@gmail.com");
            dao.updateEtudiant(trouve);

            Etudiant verifUpdate = dao.getEtudiantById(1L);
            System.out.println("  Après modification  : " + verifUpdate);
        }

        // ===============================================================
        // 5. SUPPRESSION D'UN ÉTUDIANT (DELETE)
        // ===============================================================
        System.out.println("\n=======================================================");
        System.out.println(">>> ÉTAPE 5 : Suppression de l'étudiant ID = 2");
        System.out.println("=======================================================");

        dao.deleteEtudiant(2L);

        // ===============================================================
        // 6. VÉRIFICATION DE L'ÉTAT FINAL DE LA BASE DE DONNÉES
        // ===============================================================
        System.out.println("\n=======================================================");
        System.out.println(">>> ÉTAPE 6 : État final de la base de données H2");
        System.out.println("=======================================================");

        List<Etudiant> etudiantsFinaux = dao.getAllEtudiants();
        afficherTableEtudiants(etudiantsFinaux);

        // Fermeture propre des ressources Hibernate
        HibernateUtil.shutdown();

        System.out.println("\n=======================================================");
        System.out.println(" [SUCCÈS] Fin de l'exécution du TP 1 avec succès !");
        System.out.println("=======================================================\n");
    }

    private static void printBanner() {
        System.out.println("*****************************************************************");
        System.out.println("*                                                               *");
        System.out.println("*      TP 1 : Création d'un projet Maven avec Hibernate et H2   *");
        System.out.println("*      Technologie : Hibernate ORM 6.4 + Base de données H2     *");
        System.out.println("*                                                               *");
        System.out.println("*****************************************************************");
    }

    private static void afficherTableEtudiants(List<Etudiant> liste) {
        if (liste == null || liste.isEmpty()) {
            System.out.println("  (Aucun enregistrement dans la base)");
            return;
        }

        System.out.println("  +----+----------------------+---------------------------+-----------------------------------+");
        System.out.printf("  | %-2s | %-20s | %-25s | %-33s |\n", "ID", "Nom & Prénom", "Email", "Filière");
        System.out.println("  +----+----------------------+---------------------------+-----------------------------------+");

        for (Etudiant e : liste) {
            String nomComplet = e.getPrenom() + " " + e.getNom();
            System.out.printf("  | %-2d | %-20s | %-25s | %-33s |\n",
                    e.getId(),
                    nomComplet,
                    e.getEmail(),
                    e.getFiliere());
        }

        System.out.println("  +----+----------------------+---------------------------+-----------------------------------+");
        System.out.println("  Total : " + liste.size() + " étudiant(s).");
    }
}
