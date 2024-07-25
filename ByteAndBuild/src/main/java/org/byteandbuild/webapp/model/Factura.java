/* Documentacion Nombre completo: Mario Andreé Rodríguez Zamboni Codigo Tecnico:IN5BV
 * Fecha Creacion: 18/07/24 Fecha Modificaciones: 21/07/24
 */
package org.byteandbuild.webapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Date;

@Entity
public class Factura {
    
    // El @Id se utilizara como un identificador
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /* Sirve para identificar quien va a ser la primary key
     */
    
    // Creamos las variables con su respectiva encapsulacion.
    private int facturaId;
    private String descripcion;
    private Date fechaFactura;
    private double monto;

    public Factura() {
    }

    public Factura(String descripcion, Date fechaFactura, double monto) {
        this.descripcion = descripcion;
        this.fechaFactura = fechaFactura;
        this.monto = monto;
    }

    public int getFacturaId() {
        return facturaId;
    }

    public void setFacturaId(int facturaId) {
        this.facturaId = facturaId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaFactura() {
        return fechaFactura;
    }

    public void setFechaFactura(Date fechaFactura) {
        this.fechaFactura = fechaFactura;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    @Override
    public String toString() {
        return "Factura{" + "facturaId=" + facturaId + ", descripcion=" + descripcion + ", fechaFactura=" + fechaFactura + ", monto=" + monto + '}';
    }
    
}
