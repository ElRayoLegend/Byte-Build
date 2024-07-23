/* Documentacion Nombre completo: Josue David Garcia Mendez Codigo Tecnico:IN5BV
 * Fecha Creacion: 19/7/24 Fecha Modificaciones: 19/7/24
 */
package org.byteandbuild.byteandbuild.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Compra {

    // @Id se utiliza como un identificador
    @Id
    /* Esta linea sirve para que la base de datos de un identificador unico
    cada vez que se inserta una nueva fila en la tabla
     */

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Creamos las variables con su respectiva encapsulacion.
    private int compraId;
    private String descripcion;
    private String fechaCompra;
    private double total;

    //Constructor vacio
    public Compra() {
    }

    //Constructor lleno
    public Compra(String descripcion, String fechaCompra, double total) {

        this.descripcion = descripcion;
        this.fechaCompra = fechaCompra;
        this.total = total;
    }

    // Los metodos setters and getters para cada variable
    public int getCompraId() {
        return compraId;
    }

    public void setCompraId(int compraId) {
        this.compraId = compraId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(String fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    // Nos sirve para representar en cadena lo que es Compra
    @Override
    public String toString() {
        return "Compra{" + "compraId=" + compraId + ", descripcion=" + descripcion + ", fechaCompra=" + fechaCompra + ", total=" + total + '}';
    }

}
