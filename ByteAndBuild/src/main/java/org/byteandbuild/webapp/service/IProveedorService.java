package org.fernandotomas.webapp.service;

// Se importan las librerias a utilizar y los atributos del modelo
import java.util.List;
import org.fernandotomas.webapp.model.Proveedor;

public interface IProveedorService {

    // Se inicializan las interfaces a utilizar en el servicio
    public void crearProveedor(Proveedor proveedor);

    public Proveedor buscarProveedorId(int proveedorId);

    public List<Proveedor> listarProveedores();

    public void editarProveedor(Proveedor proveedor);

    public void eliminarProveedor(int proveedorId);
}