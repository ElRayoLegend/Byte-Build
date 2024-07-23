/* Documentacion Nombre completo: Mario Andreé Rodríguez Zamboni Codigo Tecnico:IN5BV
 * Fecha Creacion: 18/07/24 Fecha Modificaciones: 21/07/24
 */
package org.byteandbuild.webapp.service;

// Persistencia
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import java.util.List;
import org.byteandbuild.webapp.model.Factura;
import org.byteandbuild.webapp.util.JpaUtil;

// Se implementa toda la interfaz en el service
public class FacturaService implements IFacturaService{
    // Creacion de entity manager
    private EntityManager em;

    public FacturaService(){
        this.em = JpaUtil.getEntityManager();
    }
    
    @Override
    public void crearFactura(Factura factura) {
        // Transaction en la instancia EntityManager
        EntityTransaction transaction = em.getTransaction();
        
        try{
            // Transaccion empieza
            transaction.begin();
            // Se inserta la entidad carrito en la db
            em.persist(factura);
            // Se confirma la transaccion en la db
            transaction.commit();
        }catch(Exception e){
            if(transaction.isActive()){
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public List<Factura> listarFactura() {
        //Creamos el query con factura
        TypedQuery<Factura> query = em.createQuery("SELECT p FROM Factura p", Factura.class);
        // Se retorna el resultado
        return query.getResultList();
    }

    @Override
    public Factura buscarFactura(int facturaId) {
        // Se busca la entidad factura con la PK facturaId
        return em.find(Factura.class, facturaId);
    }

    @Override
    public void editarFactura(Factura factura) {
        // Esto nos sirve para actualizar factura (Merge)
        em.merge(factura);
    }

    @Override
    public void eliminarFactura(int facturaId) {
        // Se busca el registro a eliminar
        Factura factura = buscarFactura(facturaId);
        
        // Si el registro se encuentra lo elimina
        if(factura != null){
            em.remove(factura);
        }
    }
    
}
