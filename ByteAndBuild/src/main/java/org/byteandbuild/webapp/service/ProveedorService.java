package org.byteandbuild.webapp.service;

//Se importan las librerias de persistencias y las librerias de los modelos
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import java.util.List;
import org.byteandbuild.webapp.model.Proveedor;
import org.byteandbuild.webapp.util.JpaUtil;

// Se crea la clase implementando las interfaces que se usarán
public class ProveedorService implements IProveedorService{

    //Se crea la clase de Manager
    private EntityManager em;

    // Se declara la variable como servicio
    public ProveedorService() {
        this.em = JpaUtil.getEntityManager();
    }

    // Se sobreescribe la clase y se crea el metodo para ingresar los datos del Proveedor
    @Override
    public void crearProveedor(Proveedor proveedor) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(proveedor);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // Se sobreescribe la clase y se crea el metodo para ingresar los datos del Proveedor

    @Override
    public Proveedor buscarProveedorId(int proveedorId) {
        return em.find(Proveedor.class, proveedorId);
    }

    // Se sobreescribe la clase y se crea el metodo para mostrar todos los datos de la clase Proveedor
    @Override
    public List<Proveedor> listarProveedores() {
        TypedQuery<Proveedor> query = em.createQuery("SELECT p FROM Proveedor p", Proveedor.class);
        return query.getResultList();
    }

    // Se sobreescribe la clase y se crea el metodo para actualizar los datos del Proveedor en especifico
    @Override
    public void editarProveedor(Proveedor proveedor) {
        em.merge(proveedor);
    }

    // Se sobreescribe la clase y se crea el metodo para eliminar los datos del Proveedor existentes
    @Override
    public void eliminarProveedor(int proveedorId) {
        Proveedor proveedor = buscarProveedorId(proveedorId);
        if (proveedor != null) {
            em.remove(proveedor);
        }
    }

}