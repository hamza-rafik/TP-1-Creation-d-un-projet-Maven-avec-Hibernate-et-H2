package com.tp.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * Classe utilitaire pour initialiser et fournir l'instance unique (Singleton) de SessionFactory.
 */
public class HibernateUtil {

    private static StandardServiceRegistry registry;
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                // Création du registre de services à partir de hibernate.cfg.xml
                registry = new StandardServiceRegistryBuilder().configure().build();

                // Création des sources de métadonnées
                MetadataSources sources = new MetadataSources(registry);

                // Création des métadonnées
                Metadata metadata = sources.getMetadataBuilder().build();

                // Création de la SessionFactory
                sessionFactory = metadata.getSessionFactoryBuilder().build();

            } catch (Exception e) {
                System.err.println("Échec de l'initialisation de la SessionFactory: " + e.getMessage());
                e.printStackTrace();
                if (registry != null) {
                    StandardServiceRegistryBuilder.destroy(registry);
                }
                throw new ExceptionInInitializerError(e);
            }
        }
        return sessionFactory;
    }

    public static void shutdown() {
        if (registry != null) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
