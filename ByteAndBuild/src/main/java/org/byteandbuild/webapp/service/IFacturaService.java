/* Documentacion Nombre completo: Mario Andreé Rodríguez Zamboni Codigo Tecnico:IN5BV
 * Fecha Creacion: 18/07/24 Fecha Modificaciones: 21/07/24
 */
package org.byteandbuild.webapp.service;

import java.util.List;
import org.byteandbuild.webapp.model.Factura;


public interface IFacturaService {
    // Crud de Factura
    
    // Creacion del metodo agregar
    public void crearFactura(Factura factura);
    
    // Creacion del metodo listar
    public List<Factura> listarFactura();
    
    // Creacion del metodo para buscar mediante facturaId como valor que se debe recibir
    public Factura buscarFactura(int facturaId);
    
    // Creacion del metodo para editar carrito recibiendo como parametro factura
    public void editarFactura (Factura factura);
    
    // Metodo para eliminar
    public void eliminarFactura(int facturaId);
    
}
