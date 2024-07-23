/* Documentacion
 * Adrian Philip Posadas del Cid
 * IN5BV
 * Fecha Creacion: 21/7/24
 * Fecha Modificaciones: 21/7/24
 */
package org.byteandbuild.webapp.service;

import java.util.List;
import org.byteandbuild.webapp.model.Cliente;

public interface IClienteService {
    // crud de cliente
    
    //metodo agregar cliente
    public void crearCliente(Cliente cliente);
    
    //lista de clientes
    public List<Cliente>listarCliente();
    
    //metodo buscar cliente
    public Cliente buscarCliente(int clienteId);
    
    //metodo editar cliente
    public void editarCliente(Cliente cliente);
    
    //metodo eliminar cliente
    public void eliminarCliente(int clienteId);
}
