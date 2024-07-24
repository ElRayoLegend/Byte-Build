/* Documentacion
 * Adrian Philip Posadas del Cid
 * IN5BV
 * Fecha Creacion: 21/7/24
 * Fecha Modificaciones: 21/7/24
 */
package org.byteandbuild.webapp.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Cliente {
    // @Id se utiliza como un identificador

    @Id
    /* Esta linea sirve para que la base de datos de un identificador unico
    cada vez que se inserta una nueva fila en la tabla
     */
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Creamos las variables con su respectiva encapsulacion.
    private int clienteId;
    private String nombre;
    private String direccion;
    private String telefono;
    private String correo;

    //Constructor vacio
    public Cliente() {
    }

    //Constructor lleno
    public Cliente(String nombre, String direccion, String telefono, String correo) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
    }
    
    // Los metodos setters and getters para cada variable
    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    // Nos sirve para representar en cadena de texto a un cliente
    @Override
    public String toString() {
        return "Cliente{" + "ID=" +clienteId +
                ", nombre=" + nombre +
                ", dirección=" + direccion +
                ", teléfono=" + telefono +
                ", correo=" + correo + "}";
    }
}
