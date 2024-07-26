/* Documentacion Nombre completo: Josue David Garcia Mendez Codigo Tecnico:IN5BV
 * Fecha Creacion: 23/7/24 Fecha Modificaciones: 23/7/24
 */
package org.byteandbuild.webapp.model;

// @Id se utiliza como un identificador
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;



@Entity
public class SoporteTecnico {

    @Id
    /* Esta linea sirve para que la base de datos de un identificador unico
    cada vez que se inserta una nueva fila en la tabla
     */

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Creamos las variables con su respectiva encapsulacion.

    private int soporteId;
    private String numeroTelefono;
    private String correoElectronico;
    private String descripcionProblema;
    private String fechaProblema;

    // Creamos constructor vacio
    public SoporteTecnico() {
    }

    // Creamos constructor lleno sin la id por que es auto_increment
    public SoporteTecnico(String numeroTelefono, String correoElectronico, String descripcionProblema, String fechaProblema) {

        this.numeroTelefono = numeroTelefono;
        this.correoElectronico = correoElectronico;
        this.descripcionProblema = descripcionProblema;
        this.fechaProblema = fechaProblema;
    }

    //Creamos los setters and getters (metodos y funciones)
    public int getSoporteId() {
        return soporteId;
    }

    public void setSoporteId(int soporteId) {
        this.soporteId = soporteId;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getDescripcionProblema() {
        return descripcionProblema;
    }

    public void setDescripcionProblema(String descripcionProblema) {
        this.descripcionProblema = descripcionProblema;
    }

    public String getFechaProblema() {
        return fechaProblema;
    }

    public void setFechaProblema(String fechaProblema) {
        this.fechaProblema = fechaProblema;
    }

    // Nos sirve para representar en cadena lo que es SoporteTecnico
    @Override
    public String toString() {
        return "SoporteTecnico{" + "soporteId=" + soporteId + ", numeroTelefono=" + numeroTelefono + ", correoElectronico=" + correoElectronico + ", descripcionProblema=" + descripcionProblema + ", fechaProblema=" + fechaProblema + '}';
    }

}
