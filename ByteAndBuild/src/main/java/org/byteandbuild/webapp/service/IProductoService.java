/* Documentacion Nombre completo: Steven Adrián Soto Morataya Codigo Tecnico:IN5BV
 * Fecha Creacion: 15/07/24 Fecha Modificaciones: 23/07/24
 */
package org.byteandbuild.webapp.service;

import java.util.List;
import org.byteandbuild.webapp.model.Producto;

public interface IProductoService {
    public void crearProducto(Producto producto);
    
    public Producto buscarProductoId(int productoId);
    
    public List<Producto> listarProductos();
    
    public void editarProducto(Producto producto);
    
    public void eliminarProducto(int productoId);
}
