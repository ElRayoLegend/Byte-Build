/* Documentacion Nombre completo: Steven Adrián Soto Morataya Codigo Tecnico:IN5BV
 * Fecha Creacion: 15/07/24 Fecha Modificaciones: 23/07/24
 */
package org.byteandbuild.webapp.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import java.util.List;
import org.byteandbuild.webapp.model.Producto;
import org.byteandbuild.webapp.util.JpaUtil;

public class ProductoService implements IProductoService{

    private EntityManager em;

    public ProductoService() {
        this.em = JpaUtil.getEntityManager();
    }
    
    @Override
    public void crearProducto(Producto producto) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(producto);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public Producto buscarProductoId(int productoId) {
        return em.find(Producto.class, productoId);
    }

    @Override
    public List<Producto> listarProductos() {
        TypedQuery<Producto> query = em.createQuery("SELECT p FROM Producto p", Producto.class);
        return query.getResultList();
    }

    @Override
    public void editarProducto(Producto producto) {
        em.merge(producto);
    }

    @Override
    public void eliminarProducto(int productoId) {
        Producto producto = buscarProductoId(productoId);
        if (producto != null) {
            em.remove(producto);
        }
    }
    
}
