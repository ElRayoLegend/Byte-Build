/* Documentacion Nombre completo: Josue David Garcia Mendez Codigo Tecnico:IN5BV
 * Fecha Creacion: 23/7/24 Fecha Modificaciones: 23/7/24
 */
package org.byteandbuild.webapp.service;

// Importamos la persistencia que vamos a utilizar 
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import java.util.List;
import org.byteandbuild.webapp.model.SoporteTecnico;
import org.byteandbuild.webapp.util.JpaUtil;


public class SoporteTecnicoService implements ISoporteTecnicoService{
 
     // Creamos el Entitymanager y le asignamos nombre
    private EntityManager em;

    // Le asignamos el valor del JpaUtil del entityManager a la variable em 
    public SoporteTecnicoService() {
        // Esta llamando a un metodo estatico getEntityManager() de la clase JpaUtil
        this.em = JpaUtil.getEntityManager();
    }
    
    @Override
    public void crearSoporteTecnico(SoporteTecnico soporteTecnico) {
        // Creamos transaction de la instancia EntityManager
        EntityTransaction transaction = em.getTransaction();
        try {
            // inicia transaccion.
            transaction.begin();
            // La entidad SoporteTecnico sera insertada en la base de datos
            em.persist(soporteTecnico);
            // Confirma la transaccion en la base de datos.
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    
    @Override
    public List<SoporteTecnico> listarSoporteTecnico() {
        //Creamos un query de tipo de TypedQuery con SoporteTecnico. 
        TypedQuery<SoporteTecnico> query = em.createQuery("SELECT p FROM SoporteTecnico p", SoporteTecnico.class);
        // Obtenemos el resultado de la consulta de objetos. 
        return query.getResultList();
    }

    @Override
    public SoporteTecnico buscarSoporteTecnico(int soporteId) {
        // Buscamos la entidad SoporteTecnico con su llave primaria soporteId
        return em.find(SoporteTecnico.class, soporteId);
    }

    @Override
    public void editarSoporteTecnico(SoporteTecnico soporteTecnico) {
        // merge nos sirve para actualizar SoporteTecnico.
        em.merge(soporteTecnico);
        
    }

    @Override
    public void eliminarSoporteTecnico(int soporteId) {
        // Busca el registro de la entidad SoporteTecnico que va a eliminar por medio de la PK
        SoporteTecnico soporteTecnico = buscarSoporteTecnico(soporteId);

        if (soporteTecnico != null) {
            // Si encuentra algun registro, procede a eliminarlo.
            em.remove(soporteTecnico);

        }

    }
    
    
}
