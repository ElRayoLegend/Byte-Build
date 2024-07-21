/* Documentacion Nombre completo: Steven Adrián Soto Morataya Codigo Tecnico:IN5BV
 * Fecha Creacion: 14/07/24 Fecha Modificaciones: 21/07/24
 */
package org.byteandbuild.webapp.service;

import java.util.List;
import org.byteandbuild.webapp.model.Carrito;


public interface ICarritoService {
    // Crud carrito
    
    // se crea el metodo agregar carrito
    public void crearCarrito(Carrito carrito);
    
    // Se crea el metodo listar carrito
    public List<Carrito> listarCarrito();
    
    // Metodo para buscar carrito mediante carritoId como valor que debe recibir
    public Carrito buscarCarrito(int carritoId);
    
    // Metodo para editar carrito recibiendo como parametro carrito
    public void editarCarrito(Carrito carrito);
    
    // Metodo para eliminar carrito
    public void eliminarCarrito(int carritoId);
    
}
