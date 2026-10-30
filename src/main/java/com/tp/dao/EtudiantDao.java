package com.tp.dao;

import com.tp.model.Etudiant;
import com.tp.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Data Access Object (DAO) gérant les opérations CRUD sur l'entité Etudiant.
 */
public class EtudiantDao {

    /**
     * Enregistrer un nouvel étudiant (CREATE).
     */
    public void saveEtudiant(Etudiant etudiant) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(etudiant);
            transaction.commit();
            System.out.println("  [DAO] Étudiant enregistré avec succès : ID=" + etudiant.getId());
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("  [DAO Error] Erreur lors de l'enregistrement : " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Récupérer un étudiant par son identifiant (READ by ID).
     */
    public Etudiant getEtudiantById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Etudiant.class, id);
        } catch (Exception e) {
            System.err.println("  [DAO Error] Erreur lors de la recherche par ID : " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Récupérer tous les étudiants via une requête HQL (READ ALL).
     */
    public List<Etudiant> getAllEtudiants() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Etudiant", Etudiant.class).list();
        } catch (Exception e) {
            System.err.println("  [DAO Error] Erreur lors de la récupération des étudiants : " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Mettre à jour les informations d'un étudiant (UPDATE).
     */
    public void updateEtudiant(Etudiant etudiant) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(etudiant);
            transaction.commit();
            System.out.println("  [DAO] Étudiant mis à jour avec succès : ID=" + etudiant.getId());
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("  [DAO Error] Erreur lors de la mise à jour : " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Supprimer un étudiant par son identifiant (DELETE).
     */
    public void deleteEtudiant(Long id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Etudiant etudiant = session.get(Etudiant.class, id);
            if (etudiant != null) {
                session.remove(etudiant);
                System.out.println("  [DAO] Étudiant supprimé avec succès : ID=" + id);
            } else {
                System.out.println("  [DAO Warn] Aucun étudiant trouvé avec l'ID=" + id);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("  [DAO Error] Erreur lors de la suppression : " + e.getMessage());
            e.printStackTrace();
        }
    }
}
