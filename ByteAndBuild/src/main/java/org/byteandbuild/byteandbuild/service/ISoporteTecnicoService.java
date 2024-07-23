/* Documentacion Nombre completo: Josue David Garcia Mendez Codigo Tecnico:IN5BV
 * Fecha Creacion: 23/7/24 Fecha Modificaciones: 23/7/24
 */
package org.byteandbuild.byteandbuild.service;

import java.util.List;
import org.byteandbuild.byteandbuild.model.SoporteTecnico;

public interface ISoporteTecnicoService {

    // Creamos el crud de SoporteTecnico
    //Creamos el metodo de agregar SoporteTecnico.
    public void crearSoporteTecnico(SoporteTecnico soporteTecnico);

    // Creamos la funcion listar SoporteTecnico con List.
    public List<SoporteTecnico> listarSoporteTecnico();

    // Creamos la funcion para buscar SoporteTecnico recibiendo el parametro compraId.
    public SoporteTecnico buscarSoporteTecnico(int soporteId);

    // Creamos el metodo para editar SoporteTecnico recibiendo la variable compra.
    public void editarSoporteTecnico(SoporteTecnico soporteTecnico);

    // Creamos el metodo eliminar SoporteTecnico. 
    public void eliminarSoporteTecnico(int soporteId);
}
