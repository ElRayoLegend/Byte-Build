/* Documentacion Nombre completo: Josue David Garcia Mendez Codigo Tecnico:IN5BV
 * Fecha Creacion: 19/7/24 Fecha Modificaciones: 19/7/24
 */
package org.byteandbuild.byteandbuild.service;

import java.util.List;
import org.byteandbuild.byteandbuild.model.Compra;


public interface ICompraService {
    // Creamos el crud de compra
    
    //Creamos el metodo de agregar Compra.
    public void crearCompra(Compra compra);
    
    // Creamos la funcion listar Compra con List.
    public List<Compra> listarCompra();
    
    // Creamos la funcion para buscar compra recibiendo el parametro compraId.
    public Compra buscarCompra(int compraId);
    
    // Creamos el metodo para editar Compra recibiendo la variable compra.
    public void editarCompra(Compra compra);
    
    // Creamos el metodo eliminar Compra. 
    public void eliminarCompra(int compraId);
    
}
