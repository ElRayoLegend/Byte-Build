/* Documentacion Nombre completo: Mario Andreé Rodríguez Zamboni Codigo Tecnico:IN5BV
 * Fecha Creacion: 18/07/24 Fecha Modificaciones: 21/07/24
 */
package org.byteandbuild.webapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Factura {
    
    // El @Id se utilizara como un identificador
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /* Sirve para identificar quien va a ser la primary key
     */
    
    // Creamos las variables con su respectiva encapsulacion.
    private int facturaId;
    private int compraId;
    private String fechaFactura;
    private double monto;

    public Factura() {
    }

    public Factura(int facturaId, int compraId, String fechaFactura, double monto) {
        this.facturaId = facturaId;
        this.compraId = compraId;
        this.fechaFactura = fechaFactura;
        this.monto = monto;
    }

    public Factura(int compraId, String fechaFactura, double monto) {
        this.compraId = compraId;
        this.fechaFactura = fechaFactura;
        this.monto = monto;
    }

    public int getFacturaId() {
        return facturaId;
    }

    public void setFacturaId(int facturaId) {
        this.facturaId = facturaId;
    }

    public int getCompraId() {
        return compraId;
    }

    public void setCompraId(int compraId) {
        this.compraId = compraId;
    }

    public String getFechaFactura() {
        return fechaFactura;
    }

    public void setFechaFactura(String fechaFactura) {
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
        return "Factura{" + "facturaId=" + facturaId + ", compraId=" + compraId + ", fechaFactura=" + fechaFactura + ", monto=" + monto + '}';
    }
    
}
