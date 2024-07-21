/* Documentacion Nombre completo: Steven Adrián Soto Morataya Codigo Tecnico:IN5BV
 * Fecha Creacion: 14/07/24 Fecha Modificaciones: 21/07/24
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
import org.byteandbuild.webapp.model.Carrito;
import org.byteandbuild.webapp.service.CarritoService;

// Aca ponemos el nombre del servlet y la url
@WebServlet(name = "CarritoServlet", value = {"/carrito-servlet"})
@MultipartConfig

// Se declara la clase carritoservlet
public class CarritoServlet extends HttpServlet {

    private CarritoService carritoService;

    public void init() throws ServletException {
        // Cuando el servlet inicializa esto se manda a llamar
        super.init();
        this.carritoService = new CarritoService();

    }

    // Este http nos da la informacion que necesitamos para agregar
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Esto manda a llamar la lista carrito
        List<Carrito> carrito = carritoService.listarCarrito();

        carrito.forEach(p -> System.out.println(p));
        req.setAttribute("carrito", carrito);
        // Dirige a jsp para mostrar la lista carrito de compras
        req.getRequestDispatcher("/listar-carrito/listar-carrito.jsp").forward(req, resp);

    }

    private void crearCarrito(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Con los parametros de HttpServletRequest declaramos en la db
        int cantidad = Integer.parseInt(req.getParameter("cantidad"));
        String nombreProducto = req.getParameter("nombreProducto");
        double precioProducto = Double.parseDouble(req.getParameter("precioProducto"));
        double total = Double.parseDouble(req.getParameter("total"));

        // Se obtienen los parametros de carrito
        Carrito carrito = new Carrito(cantidad, nombreProducto, precioProducto, total);
        // Se crea el carrito de compras
        carritoService.crearCarrito(carrito);

        resp.sendRedirect(req.getContextPath() + "/");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Creamos el pathInfo de tipo String que tenga el HttpServletRequest con la informacion de pathInfo.
        String pathInfo = req.getPathInfo();
        // verificar que el path info este vacio o sea "/"
        if (pathInfo == null || pathInfo.equals("/")) {

            // Crea el carrito con los parametros 
            crearCarrito(req, resp);
        } else {

            // enviamos la respuesta del servidor en caso de un error
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }

    }

    private void editarCarrito(int carritoId, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Colocamos el servicio de carrito con la funcion de buscarCarrito
        Carrito carrito = carritoService.buscarCarrito(carritoId);
        /* Si este recurso de carrito es diferente a nulo procede a 
        actualiza sus atributos con los valores proporcionados en la
        solicitud y persiste los cambios en la base de datos. Si la entidad 
        no se encuentra, responde con un error 404 (No Encontrado).*/

        if (carrito != null) {

            int cantidad = Integer.parseInt(req.getParameter("cantidad"));
            String nombreProducto = req.getParameter("nombreProducto");
            double precioProducto = Double.parseDouble(req.getParameter("precioProducto"));
            double total = Double.parseDouble(req.getParameter("total"));

            carrito.setCantidad(cantidad);
            carrito.setNombreProducto(nombreProducto);
            carrito.setPrecioProducto(precioProducto);
            carrito.setTotal(total);

            carritoService.editarCarrito(carrito);

            resp.sendRedirect(req.getContextPath() + "/carrito/");
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
            //Se divide pathInfo en partes utilizando el caracter "/" como delimitador.
            String[] pathParts = pathInfo.split("/");

            //Se verifica que pathParts tenga una longitud de 2, lo que asegura que se haya proporcionado un ID valido
            if (pathParts.length == 2) {
                //El ID del carrito se extrae de pathParts y se convierte a un entero.
                int carritoId = Integer.parseInt(pathParts[1]);
                /*Si todas las verificaciones anteriores son correctas,
                se llama al metodo editarCarrito pasando el ID del carrito, 
                la solicitud (req), y la respuesta (resp).*/
                editarCarrito(carritoId, req, resp);
            } else {
                //Si pathParts no tiene una longitud de 2, se envi­a un error 400 (Solicitud Incorrecta).
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }
        } else {
            //Si pathInfo es nulo o igual a "/", se envi­a un error 400 (Solicitud Incorrecta).
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    private void eliminarCarrito(int carritoId, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Se utiliza el servicio carritoService para buscar la entidad Carrito con el ID proporcionado (carritoId).
        Carrito carrito = carritoService.buscarCarrito(carritoId);
        //Si la entidad Carrito no es nula (es decir, se encontrara en la base de datos), se procede a eliminarla.
        if (carrito != null) {
            carritoService.eliminarCarrito(carritoId);
            // Se envia al cliente la nueva URL, Cuando se llama a este metodo, se envi­a una respuesta HTTP al cliente
            resp.sendRedirect(req.getContextPath() + "/carrito/");
        } else {
            //Si el carrito de compras no se encuentra, se envi­a un error 404 (Not Found) al cliente.
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
                int carritoId = Integer.parseInt(pathParts[1]);
                // procede a eliminar con los parametros requeridos como el id el pedido y la respuesta
                eliminarCarrito(carritoId, req, resp);
            } else {
                //Si pathParts no tiene exactamente dos elementos, envi­a una respuesta de error HTTP 400 (Bad Request)
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }
        } else {
            //Si pathInfo es nulo o igual a /, envÃ­a una respuesta de error HTTP 400 (Bad Request) al cliente.
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}