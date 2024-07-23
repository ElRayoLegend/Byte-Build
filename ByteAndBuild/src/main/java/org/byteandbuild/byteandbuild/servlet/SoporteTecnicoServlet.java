/* Documentacion Nombre completo: Josue David Garcia Mendez Codigo Tecnico:IN5BV
 * Fecha Creacion: 23/7/24 Fecha Modificaciones: 23/7/24
 */
package org.byteandbuild.byteandbuild.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import org.byteandbuild.byteandbuild.model.SoporteTecnico;
import org.byteandbuild.byteandbuild.service.SoporteTecnicoService;

// nombramos el nombre del servlet y luego definimos la URL
@WebServlet(name = "SoporteTecnicoServlet", value = {"/soporteTecnico-servlet"})
//Habilita el soporte para el manejo de solicitudes de tipo MultiPart
@MultipartConfig

// Declaramos la clase 
public class SoporteTecnicoServlet extends HttpServlet {
    
    private SoporteTecnicoService soporteTecnicoService;
    
    public void init() throws ServletException {
        //Este metodo se llama cuando el servlet se inicializa.
        super.init();
        this.soporteTecnicoService = new SoporteTecnicoService();
        
    }

    //Mandamos a llamar este metodo Http lo que nos trae la informacion oara poder agregar.
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Creamos una list de compras que va a llamar listarsoporteTecnico de la clase soporteTecnicoService
        List<SoporteTecnico> soporteTecnico = soporteTecnicoService.listarSoporteTecnico();
        
        soporteTecnico.forEach(p -> System.out.println(p));
        req.setAttribute("soporteTecnico", soporteTecnico);
        //Redirige a la pÃ¡gina JSP para mostrar la lista de soporteTecnico.
        req.getRequestDispatcher("/listar-soporteTecnico/listar-soporteTecnico.jsp").forward(req, resp);
        
    }

    private void crearSoporteTecnico(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // declaramos las variables de la base de datos con los parametros de HttpServletRequest
        String numeroTelefono = req.getParameter("numeroTelefono");
        String correoElectronico = req.getParameter("correoElectronico");
        String descripcionProblema = req.getParameter("descripcionProblema");        
        String fechaProblema = req.getParameter("fechaProblema");

        // obtenemos los parametros de soporteTecnicoServlet
        SoporteTecnico soporteTecnico = new SoporteTecnico(numeroTelefono, correoElectronico, descripcionProblema, fechaProblema);
        // creamos la soporteTecnicoServlet
        soporteTecnicoService.crearSoporteTecnico(soporteTecnico);
        
        resp.sendRedirect(req.getContextPath() + "/");
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Creamos el pathInfo de tipo String que tenga el HttpServletRequest con la informacion de pathInfo.
        String pathInfo = req.getPathInfo();
        // verificar que el path info este vacio o sea "/"
        if (pathInfo == null || pathInfo.equals("/")) {

            // Crea la compra con los parametros 
            crearSoporteTecnico(req, resp);
        } else {

            // enviamos la respuesta del servidor en caso de un error
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        
    }
    
    private void editarSoporteTecnico(int soporteId, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Colocamos el servicio de soporteTecnico con la funcion de buscarCompra
        SoporteTecnico soporteTecnico = soporteTecnicoService.buscarSoporteTecnico(soporteId);
        /* Si este recurso de soporteTecnico es diferente a nulo procede a 
        actualiza sus atributos con los valores proporcionados en la
        solicitud y persiste los cambios en la base de datos. Si la entidad 
        no se encuentra, responde con un error 404 (No Encontrado).*/
        
        if (soporteTecnico != null) {
            
            String numeroTelefono = req.getParameter("numeroTelefono");
            String correoElectronico = req.getParameter("correoElectronico");
            String descripcionProblema = req.getParameter("descripcionProblema");            
            String fechaProblema = req.getParameter("fechaProblema");
            
            soporteTecnico.setNumeroTelefono(numeroTelefono);
            soporteTecnico.setCorreoElectronico(correoElectronico);
            soporteTecnico.setDescripcionProblema(descripcionProblema);
            soporteTecnico.setFechaProblema(fechaProblema);
            
            soporteTecnicoService.editarSoporteTecnico(soporteTecnico);
            
            resp.sendRedirect(req.getContextPath() + "/soporteTecnico/");
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
    
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //pathInfo obtiene la parte de la URL que sigue al nombre del servlet
        String pathInfo = req.getPathInfo();

        //Se verifica que pathInfo no sea nulo ni igual a "/". Esto asegura que se ha proporcionado un ID en la URL.
        if (pathInfo != null && !pathInfo.equals("/")) {
            //Se divide pathInfo en partes utilizando el carÃ¡cter "/" como delimitador.
            String[] pathParts = pathInfo.split("/");

            //Se verifica que pathParts tenga una longitud de 2, lo que asegura que se haya proporcionado un ID valido
            if (pathParts.length == 2) {
                //El ID de la SoporteTecnico se extrae de pathParts y se convierte a un entero.
                int soporteId = Integer.parseInt(pathParts[1]);
                /*Si todas las verificaciones anteriores son correctas,
                se llama al metodo editarSoporteTecnico pasando el ID de la SoporteTecnico, 
                la solicitud (req), y la respuesta (resp).*/
                editarSoporteTecnico(soporteId, req, resp);
            } else {
                //Si pathParts no tiene una longitud de 2, se envia un error 400 (Solicitud Incorrecta).
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }
        } else {
            //Si pathInfo es nulo o igual a "/", se envÃ­a un error 400 (Solicitud Incorrecta).
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
    
    private void eliminarSoporteTecnico(int soporteId, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Se utiliza el servicio SoporteTecnicoService para buscar la entidad SoporteTecnico con el ID proporcionado (compraId).
        SoporteTecnico soporte = soporteTecnicoService.buscarSoporteTecnico(soporteId);
        //Si la entidad SoporteTecnico no es nula (es decir, se encontro en la base de datos), se procede a eliminarla.
        if (soporte != null) {
            soporteTecnicoService.eliminarSoporteTecnico(soporteId);
            // Se envia al cliente la nueva URL, Cuando se llama a este metodo, se envia una respuesta HTTP al cliente
            resp.sendRedirect(req.getContextPath() + "/soporteTecnico/");
        } else {
            //Si la SoporteTecnico no se encuentra, se enviaa un error 404 (Not Found) al cliente.
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    //manejar las solicitudes HTTP delete.
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Obtiene informacion de la URL
        String pathInfo = req.getPathInfo();

        //Si la URL es diferente a null y contiene "/" entrara a la funcionalidad de eliminar
        if (pathInfo != null && !pathInfo.equals("/")) {
            //Divide pathInfo en partes con el metodo split utilizando como separador "/"
            String[] pathParts = pathInfo.split("/");
            // Si pathParts es igual a 2 elementos procede a eliminar
            if (pathParts.length == 2) {
                //Parsea pathParts a tipo Int debido a que pathParts es String
                int soporteId = Integer.parseInt(pathParts[1]);
                // procede a eliminar con los parametros requeridos como el id el pedido y la respuesta
                eliminarSoporteTecnico(soporteId, req, resp);
            } else {
                //Si pathParts no tiene exactamente dos elementos, envÃ­a una respuesta de error HTTP 400 (Bad Request)
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }
        } else {
            //Si pathInfo es nulo o igual a /, envÃ­a una respuesta de error HTTP 400 (Bad Request) al cliente.
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
    
    
}
