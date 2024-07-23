/* Documentacion Nombre completo: Josue David Garcia Mendez Codigo Tecnico:IN5BV
 * Fecha Creacion: 19/7/24 Fecha Modificaciones: 19/7/24
 */
package org.byteandbuild.byteandbuild.service;

// Importamos la persistencia que vamos a utilizar 
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import java.util.List;
import org.byteandbuild.byteandbuild.util.JpaUtil;
import org.byteandbuild.byteandbuild.model.Compra;

// Implementamos la interfaz en el servicio
public class CompraService implements ICompraService {

    // Creamos el Entitymanager y le asignamos nombre
    private EntityManager em;

    // Le asignamos el valor del JpaUtil del entityManager a la variable em 
    public CompraService() {
       // Está llamando a un método estático getEntityManager() de la clase JpaUtil
        this.em = JpaUtil.getEntityManager();
    }

    
    @Override
    public void crearCompra(Compra compra) {
        // Creamos transaction de la instancia EntityManager
        EntityTransaction transaction = em.getTransaction();
        try {
            // inicia transaccion.
            transaction.begin();
            // La entidad compra sera insertada en la base de datos
            em.persist(compra);
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
    public List<Compra> listarCompra() {
        //Creamos un query de tipo de TypedQuery con Compra 
        TypedQuery<Compra> query = em.createQuery("SELECT p FROM Compra p", Compra.class);
        // Obtenemos el resultado de la consulta de objetos. 
        return query.getResultList();
    }

    @Override
    public Compra buscarCompra(int compraId) {
        // Buscamos la entidad compra con su llave primaria compraId
        return em.find(Compra.class, compraId);
    }

    @Override
    public void editarCompra(Compra compra) {
        // merge nos sirve para actualizar compra
        em.merge(compra);
    }

    @Override
    public void eliminarCompra(int compraId) {
        // Busca el registro de la entidad compra que va a eliminar por medio de la PK
        Compra compra = buscarCompra(compraId);

        if (compra != null) {
            // Si encuentra algun registro, procede a eliminarlo.
            em.remove(compra);

        }

    }
}
