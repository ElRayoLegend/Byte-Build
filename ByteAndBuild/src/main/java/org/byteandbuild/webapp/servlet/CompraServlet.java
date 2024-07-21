/* Documentacion Nombre completo: Josue David Garcia Mendez Codigo Tecnico:IN5BV
 * Fecha Creacion: 19/7/24 Fecha Modificaciones: 20/7/24
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
import org.byteandbuild.webapp.model.Compra;
import org.byteandbuild.webapp.service.CompraService;

// nombramos el nombre del servlet y luego definimos la URL
@WebServlet(name = "CompraServlet", value = {"/compra-servlet"})
//Habilita el soporte para el manejo de solicitudes de tipo MultiPart
@MultipartConfig

// Declaramos la clase 
public class CompraServlet extends HttpServlet {

    private CompraService compraService;

    public void init() throws ServletException {
        //Este mÃ©todo se llama cuando el servlet se inicializa.
        super.init();
        this.compraService = new CompraService();

    }

    //Mandamos a llamar este metodo Http lo que nos trae la informacion oara poder agregar.
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Creamos una list de compras que va a llamar listarCompra de la clase compra servicio
        List<Compra> compra = compraService.listarCompra();

        compra.forEach(p -> System.out.println(p));
        req.setAttribute("compra", compra);
        //Redirige a la pÃ¡gina JSP para mostrar la lista de compras.
        req.getRequestDispatcher("/listar-compra/listar-compra.jsp").forward(req, resp);

    }

    private void crearCompra(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // declaramos las variables de la base de datos con los parametros de HttpServletRequest
        String descripcion = req.getParameter("descripcion");
        String fechaCompra = req.getParameter("fechaCompra");
        double total = Double.parseDouble(req.getParameter("total"));

        // obtenemos los parametros de compra
        Compra compra = new Compra(descripcion, fechaCompra, total);
        // creamos la compra
        compraService.crearCompra(compra);

        resp.sendRedirect(req.getContextPath() + "/");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Creamos el pathInfo de tipo String que tenga el HttpServletRequest con la informacion de pathInfo.
        String pathInfo = req.getPathInfo();
        // verificar que el path info este vacio o sea "/"
        if (pathInfo == null || pathInfo.equals("/")) {

            // Crea la compra con los parametros 
            crearCompra(req, resp);
        } else {

            // enviamos la respuesta del servidor en caso de un error
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }

    }

    private void editarCompra(int compraId, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Colocamos el servicio de compra con la funcion de buscarCompra
        Compra compra = compraService.buscarCompra(compraId);
        /* Si este recurso de compra es diferente a nulo procede a 
        actualiza sus atributos con los valores proporcionados en la
        solicitud y persiste los cambios en la base de datos. Si la entidad 
        no se encuentra, responde con un error 404 (No Encontrado).*/

        if (compra != null) {

            String descripcion = req.getParameter("descripcion");
            String fechaCompra = req.getParameter("fechaCompra");
            double total = Double.parseDouble(req.getParameter("total"));

            compra.setDescripcion(descripcion);
            compra.setFechaCompra(fechaCompra);
            compra.setTotal(total);

            compraService.editarCompra(compra);

            resp.sendRedirect(req.getContextPath() + "/compra/");
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

            //Se verifica que pathParts tenga una longitud de 2, lo que asegura que se haya proporcionado un ID vÃ¡lido
            if (pathParts.length == 2) {
                //El ID de la compra se extrae de pathParts y se convierte a un entero.
                int compraId = Integer.parseInt(pathParts[1]);
                /*Si todas las verificaciones anteriores son correctas,
                se llama al mÃ©todo editarCompra pasando el ID de la compra, 
                la solicitud (req), y la respuesta (resp).*/
                editarCompra(compraId, req, resp);
            } else {
                //Si pathParts no tiene una longitud de 2, se envÃ­a un error 400 (Solicitud Incorrecta).
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }
        } else {
            //Si pathInfo es nulo o igual a "/", se envÃ­a un error 400 (Solicitud Incorrecta).
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    private void eliminarCompra(int compraId, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Se utiliza el servicio compraService para buscar la entidad Compra con el ID proporcionado (compraId).
        Compra compra = compraService.buscarCompra(compraId);
        //Si la entidad Compra no es nula (es decir, se encontrÃ³ en la base de datos), se procede a eliminarla.
        if (compra != null) {
            compraService.eliminarCompra(compraId);
            // Se envia al cliente la nueva URL, Cuando se llama a este mÃ©todo, se envÃ­a una respuesta HTTP al cliente
            resp.sendRedirect(req.getContextPath() + "/compra/");
        } else {
            //Si la compra no se encuentra, se envÃ­a un error 404 (Not Found) al cliente.
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
                int compraId = Integer.parseInt(pathParts[1]);
                // procede a eliminar con los parametros requeridos como el id el pedido y la respuesta
                eliminarCompra(compraId, req, resp);
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
