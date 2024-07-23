/* Documentacion Nombre completo: Steven Adrián Soto Morataya Codigo Tecnico:IN5BV
 * Fecha Creacion: 15/07/24 Fecha Modificaciones: 23/07/24
 */
package org.byteandbuild.webapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productoId;
    private String nombreProducto;
    private String marca;
    private String descripcionProducto;
    private double precioProducto; 

    public Producto() {
    }

    public Producto(String nombreProducto, String marca, String descripcionProducto, double precioProducto) {
        this.nombreProducto = nombreProducto;
        this.marca = marca;
        this.descripcionProducto = descripcionProducto;
        this.precioProducto = precioProducto;
    }

    public Producto(int productoId, String nombreProducto, String marca, String descripcionProducto, double precioProducto) {
        this.productoId = productoId;
        this.nombreProducto = nombreProducto;
        this.marca = marca;
        this.descripcionProducto = descripcionProducto;
        this.precioProducto = precioProducto;
    }

    public int getProductoId() {
        return productoId;
    }

    public void setProductoId(int productoId) {
        this.productoId = productoId;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }

    public double getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(double precioProducto) {
        this.precioProducto = precioProducto;
    }

    @Override
    public String toString() {
        return "Producto{" + "productoId=" + productoId + ", nombreProducto=" + nombreProducto + ", marca=" + marca + ", descripcionProducto=" + descripcionProducto + ", precioProducto=" + precioProducto + '}';
    }
}
