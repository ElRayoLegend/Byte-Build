/* Documentacion Nombre completo: Steven Adrián Soto Morataya Codigo Tecnico:IN5BV
 * Fecha Creacion: 14/07/24 Fecha Modificaciones: 21/07/24
 */
package org.byteandbuild.webapp.service;

// Persistencia
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import java.util.List;
import org.byteandbuild.webapp.util.JpaUtil;
import org.byteandbuild.webapp.model.Carrito;

// Se implementa toda la interfaz en el service
public class CarritoService implements ICarritoService {

    // Creacion de entity manager
    private EntityManager em;

    // Se asigna el valor de jpa a em
    public CarritoService() {
        this.em = JpaUtil.getEntityManager();
    }

    @Override
    public void crearCarrito(Carrito carrito) {
        // Transaction en la instancia EntityManager
        EntityTransaction transaction = em.getTransaction();
        try {
            // Transaccion empieza
            transaction.begin();
            // Se inserta la entidad carrito en la db
            em.persist(carrito);
            // Se confirma la transaccion en la db
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public List<Carrito> listarCarrito() {
        //Creamos el query con carrito
        TypedQuery<Carrito> query = em.createQuery("SELECT p FROM Carrito p", Carrito.class);
        // Se obtiene el resultado
        return query.getResultList();
    }

    @Override
    public Carrito buscarCarrito(int carritoId) {
        // Se busca la entidad carrito con la PK carritoId
        return em.find(Carrito.class, carritoId);
    }

    @Override
    public void editarCarrito(Carrito carrito) {
        // Esto nos sirve para actualizar carrito (Merge)
        em.merge(carrito);
    }

    @Override
    public void eliminarCarrito(int carritoId) {
        // Se busca el registro a eliminar
        Carrito carrito = buscarCarrito(carritoId);

        if (carrito != null) {
            // Si el registro se encuentra lo elimina
            em.remove(carrito);

        }

    }
}
