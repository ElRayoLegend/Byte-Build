/* Documentacion Nombre completo: Josue David Garcia Mendez Codigo Tecnico:IN5BV
 * Fecha Creacion: 19/7/24 Fecha Modificaciones: 19/7/24
 */
package org.byteandbuild.buteandbuild.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

// Creacion de la clase JPA el cual nos permite conectarnos con hibernate y la persistencia
public class JpaUtil {

    private static final EntityManagerFactory emf = buildEntityManagerFactory();    

    /**
     * Crea la instancia de EntityManagerFactory utilizando la unidad de
     * persistencia definida en el archivo persistence.xml.
     *
     */
    private static EntityManagerFactory buildEntityManagerFactory() {
        try {

            // @return la instancia configurada de EntityManagerFactory
            return Persistence.createEntityManagerFactory("CRUDVespertinaProductoDB");

        } catch (Throwable ex) {

            // @throws ExceptionInInitializerError si no se puede crear la EntityManagerFactory 
            throw new ExceptionInInitializerError(ex);

        }
    }

    // Aqui proporcionamos la instancia la retornamos
    public static EntityManager getEntityManager() {
        return emf.createEntityManager();

    }

    // Cerramos la instancia cuando cerremos el programa.
    public static void close() {
        emf.close();

    }

}
