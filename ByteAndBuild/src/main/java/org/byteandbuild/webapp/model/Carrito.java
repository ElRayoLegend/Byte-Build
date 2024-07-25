/* Documentacion Nombre completo: Steven Adrián Soto Morataya Codigo Tecnico:IN5BV
 * Fecha Creacion: 14/07/24 Fecha Modificaciones: 21/07/24
 */
package org.byteandbuild.webapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Carrito {

    // @Id se utiliza como un identificador
    @Id
    /* La db identifica con un id unico a cada fila
     */

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Variables con encapsulamiento
    private int carritoId;
    private int cantidad;
    private String nombreProducto;
    private double precioProducto;
    private double total;

    // Constructor vacio
    public Carrito() {
    }

    // Constructor lleno

    public Carrito(int cantidad, String nombreProducto, double precioProducto, double total) {
        this.cantidad = cantidad;
        this.nombreProducto = nombreProducto;
        this.precioProducto = precioProducto;
        this.total = total;
    }
    
    // Getters and Setter
    public int getCarritoId() {    
        return carritoId;
    }

    public void setCarritoId(int carritoId) {
        this.carritoId = carritoId;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public double getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(double precioProducto) {
        this.precioProducto = precioProducto;
    }

    public double getTotal() {
        return total;
    }
    
    public void setTotal(double total) {    
        this.total = total;
    }

    // Concatenar los atributos del carrito de compras
    @Override
    public String toString() {
        return "Carrito{" + "carritoId=" + carritoId + ", cantidad=" + cantidad + ", nombreProducto=" + nombreProducto + ", precioProducto=" + precioProducto + ", total=" + total + '}';
    }
    

}
