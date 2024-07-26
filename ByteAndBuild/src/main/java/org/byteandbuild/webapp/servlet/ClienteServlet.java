/* Documentacion
 * Adrian Philip Posadas del Cid
 * IN5BV
 * Fecha Creacion: 23/7/2024
 * Fecha Modificaciones: 23/7/2024
 */
package org.byteandbuild.webapp.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import org.byteandbuild.webapp.model.Cliente;
import org.byteandbuild.webapp.service.ClienteService;

// nombramos el servlet y definimos la URL
@WebServlet(name = "ClienteServlet", value = {"/cliente-servlet"})
//Habilita el soporte para el manejo de solicitudes de tipo MultiPart
@MultipartConfig

public class ClienteServlet extends HttpServlet{
    private ClienteService clienteService;
    
    //se llama este método al inicializar el servlet
    public void init() throws ServletException{
        super.init();
        this.clienteService = new ClienteService();
    }
    
    //Metodo http que trae información para poder agregar
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        //Creamos una lista de Clientes, que va a llamar a la lista de clientes en la clase ClienteService
        List<Cliente> cliente = clienteService.listarCliente();

        cliente.forEach(p -> System.out.println(p));
        req.setAttribute("Clientes", cliente);
        //Redirige al JSP para mostrar la lista de clientes
        req.getRequestDispatcher("/page/listar-cliente.jsp").forward(req, resp);
    }
    
    private void crearCliente(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // declaramos las variables de la base de datos con los parametros de HttpServletRequest
        String nombre = req.getParameter("nombre");
        String direccion = req.getParameter("direccion");
        String telefono = req.getParameter("telefono");
        String correo = req.getParameter("correo");

        // obtenemos los parametros de cliente
        Cliente cliente = new Cliente(nombre, direccion, telefono, correo);
        // creamos el cliente
        clienteService.crearCliente(cliente);

        resp.sendRedirect(req.getContextPath() + "/cliente-servlet");
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Creamos el pathInfo de tipo String que tenga el HttpServletRequest con la informacion de pathInfo.
        String pathInfo = req.getPathInfo();
        // verificar que el path info este vacio o sea "/"
        if (pathInfo == null || pathInfo.equals("/")) {
            // Crea al cliente con los parametros 
            crearCliente(req, resp);
        } else {
            // enviamos la respuesta del servidor en caso de un error
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
    
    private void editarCliente(int clienteId, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Colocamos el servicio de cliente con la funcion de buscarCliente
        Cliente cliente = clienteService.buscarCliente(clienteId);
        /* Si este recurso no es nulo entonces actualiza sus atributos con
        los valores proporcionados en la solicitud */
        
        if (cliente != null) {
            String nombre = req.getParameter("nombre");
            String direccion = req.getParameter("direccion");
            String telefono = req.getParameter("telefono");
            String correo = req.getParameter("correo");

            cliente.setNombre(nombre);
            cliente.setDireccion(direccion);
            cliente.setTelefono(telefono);
            cliente.setCorreo(correo);

            clienteService.editarCliente(cliente);

            resp.sendRedirect(req.getContextPath() + "/cliente/");
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
    
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        //pathInfo obtiene la parte de la URL que sigue al nombre del servlet
        String pathInfo = req.getPathInfo();

        //Se verifica que pathInfo no sea nulo o igual a "/" para asegurar que exista unl ID en la URL.
        if (pathInfo != null && !pathInfo.equals("/")) {
            //se utiliza "/" como delimitador para dividir pathInfo.
            String[] pathParts = pathInfo.split("/");
            //Se verifica que pathParts haya proporcionado un ID válido
            if (pathParts.length == 2) {
                //El ID del cliente se extrae de pathParts y se convierte a un entero.
                int clienteId = Integer.parseInt(pathParts[1]);
                /*Si todas las verificaciones anteriores son correctas,
                se llama al método editarCliente pasando el ID de la compra, 
                la solicitud (req), y la respuesta (resp).*/
                editarCliente(clienteId, req, resp);
            } else {
                //Si pathParts no tiene una longitud de 2, se envía un error 400 (Solicitud Incorrecta).
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }
        } else {
            //Si pathInfo es nulo o igual a "/", se envía un error 400 (Solicitud Incorrecta).
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
    
    private void eliminarCliente(int clienteId, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Se utiliza el servicio para buscar la entidad Cliente con el ID proporcionado.
        Cliente cliente = clienteService.buscarCliente(clienteId);
        //Se se encuentra el cliente en la base de datos, se procede a eliminarlo.
        if (cliente != null) {
            clienteService.eliminarCliente(clienteId);
            // Se envia al cliente la nueva URL, Cuando se llama a este método se envía una respuesta HTTP al cliente
            resp.sendRedirect(req.getContextPath() + "/cliente/");
        } else {
            //Si el cliente no se encuentra, se envía un error 404 al cliente.
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
    
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Obtiene informacion de la URL
        String pathInfo = req.getPathInfo();

        //Si la URL es diferente a null y contiene "/" entrara a la funcionalidad de eliminar
        if (pathInfo != null && !pathInfo.equals("/")) {
            //se utiliza "/" como delimitador para dividir pathInfo.
            String[] pathParts = pathInfo.split("/");
            // Si pathParts es igual a 2 procede a eliminar
            if (pathParts.length == 2) {
                //se convierte pathParts a int
                int clienteId = Integer.parseInt(pathParts[1]);
                // se elimina mandando los parametros al método eliminarCliente
                eliminarCliente(clienteId, req, resp);
            } else {
                //Si pathParts no tiene exactamente dos elementos, envía una respuesta de error HTTP 400 (Bad Request)
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }
        } else {
            //Si pathInfo es nulo o igual a /, envía una respuesta de error HTTP 400 (Bad Request) al cliente.
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
