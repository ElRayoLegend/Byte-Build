package org.fernandotomas.webapp.model;

// Se importan las librerias necesarias para la persistencia (JPA)
import jakarta.persistense.Entity;
import jakarta.persistense.GeneratedValue;
import jakarta.persistense.GenerationType;
import jakarta.persistense.Id;

@Entity
public class Proveedor implements Serializable {
    //Se realiza el llamado de las librerias
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    //Se declaran privados los atributos o beans necesarios para la clase
    private int proveedorId;
    private String nombre;
    private String descripcion;
    private double precio;
    private int stock;

    //Se crea el constructor vacío
    public Proveedor() {
    }

    // Se crea el constructor lleno
    public Proveedor(int proveedorId, String nombre, String descripcion, double precio, int stock) {
    this.proveedorId = proveedorId;
    this.nombre = nombre;
    this.descripcion = descripcion;
    this.precio = precio;
    this.stock = stock;}

    //Se crea un constructor sin el identificador
    public Proveedor(String nombre, String descripcion, double precio, int stock) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    // Se emplea la sobreescritura para la facilidad de lectura de datos
    @Override
    public String getToString() {
        return "Proveedores{" + "proveedor Id: "+proveedorId+" nombre: "+nombre+" descripcion: "+descripcion+" precio: "+precio+" stock: "+stock+'}';
    }

}