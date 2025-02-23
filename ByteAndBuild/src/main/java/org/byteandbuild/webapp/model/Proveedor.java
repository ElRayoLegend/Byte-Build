package org.byteandbuild.webapp.model;

// Se importan las librerias necesarias para la persistencia (JPA)
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.io.Serializable;

@Entity
public class Proveedor implements Serializable {
    //Se realiza el llamado de las librerias
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    //Se declaran privados los atributos o beans necesarios para la clase
    private int proveedorId;
    private String nombre;
    private String direccion;
    private String telefono;
    private String correo;

    //Se crea el constructor vacío
    public Proveedor() {
    }

    // Se crea el constructor lleno

    public Proveedor(String nombre, String direccion, String telefono, String correo) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
    }

    //Se realizan los getters y setters de todos los atributos privados instanciados anteriormente

    public int getProveedorId() {
        return proveedorId;
    }

    public void setProveedorId(int proveedorId) {
        this.proveedorId = proveedorId;
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
    

    // Se emplea la sobreescritura para la facilidad de lectura de datos
    @Override
    public String toString() {
        return "Proveedor{" + "proveedorId=" + proveedorId + ", nombre=" + nombre + ", direccion=" + direccion + ", telefono=" + telefono + ", correo=" + correo + '}';
    }

}