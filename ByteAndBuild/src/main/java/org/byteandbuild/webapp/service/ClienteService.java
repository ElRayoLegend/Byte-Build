/* Documentacion
 * Adrian Philip Posadas del Cid
 * IN5BV
 * Fecha Creacion: 21/7/24
 * Fecha Modificaciones: 21/7/24
 */
package org.byteandbuild.webapp.service;

//se importan las librerías que vamos a usar
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import java.util.List;
import org.byteandbuild.webapp.model.Cliente;
import org.byteandbuild.webapp.util.JpaUtil;

//se implementa la interfaz IClienteService
public class ClienteService implements IClienteService{
    
    // Creamos el Entitymanager y le asignamos nombre
    private EntityManager em;
    
    // Le asignamos el valor del JpaUtil del entityManager a la variable em 
    public ClienteService(){
        //llamamos al metodo getEntityManager() de JpaUtil
        this.em = JpaUtil.getEntityManager();
    }
    
    @Override
    public void crearCliente(Cliente cliente){
        // Creamos la transaccion de la instancia EntityManager
        EntityTransaction transaction = em.getTransaction();
        try {
            // inicia transaccion.
            transaction.begin();
            // La entidad compra sera insertada en la base de datos
            em.persist(cliente);
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
    public List<Cliente> listarCliente() {
        //Creamos un query de tipo de TypedQuery con Cliente
        TypedQuery<Cliente> query = em.createQuery("SELECT p FROM Cliente p", Cliente.class);
        // Obtenemos el resultado de la consulta de objetos. 
        return query.getResultList();
    }
    
    @Override
    public Cliente buscarCliente(int clienteId) {
        // Buscamos la entidad cliente con su llave primaria clienteId
        return em.find(Cliente.class, clienteId);
    }
    
    @Override
    public void editarCliente(Cliente cliente) {
        // merge nos sirve para actualizar cliente
        em.merge(cliente);
    }

    @Override
    public void eliminarCliente(int clienteId) {
        // Busca el registro de la entidad cliente que va a eliminar por medio de la PK
        Cliente cliente = buscarCliente(clienteId);

        if (cliente != null) {
            // Si encuentra algun registro, procede a eliminarlo.
            em.remove(cliente);
        }
    }
}
