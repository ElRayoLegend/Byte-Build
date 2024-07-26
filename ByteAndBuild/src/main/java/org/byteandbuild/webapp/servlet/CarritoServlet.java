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
import org.byteandbuild.webapp.service.FacturaService;

// Aca ponemos el nombre del servlet y la url
@WebServlet(name = "CarritoServlet", value = {"/carrito-servlet"})
@MultipartConfig

// Se declara la clase carritoservlet
public class CarritoServlet extends HttpServlet {

    private CarritoService carritoService;
    private FacturaService facturaService;

    public void init() throws ServletException {
        // Cuando el servlet inicializa esto se manda a llamar
        super.init();
        this.carritoService = new CarritoService();
        this.facturaService = new FacturaService();

    }

    // Este http nos da la informacion que necesitamos para agregar
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Esto manda a llamar la lista carrito
        List<Carrito> carrito = carritoService.listarCarrito();
        double total = 0;
        carrito.forEach(p -> System.out.println(p));
        for (Carrito item: carrito) {
            total = total + item.getTotal();
        }
        req.setAttribute("carrito", carrito);
        req.setAttribute("total", total);
        // Dirige a jsp para mostrar la lista carrito de compras
        req.getRequestDispatcher("/page/tienda/carro.jsp").forward(req, resp);

    }

    private void crearCarrito(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    // Obtener los parámetros de la solicitud
    String cantidadStr = req.getParameter("cantidad");
    String nombreProducto = req.getParameter("nombreProducto");
    String precioProductoStr = req.getParameter("precioProducto");
    String totalStr = String.valueOf(Double.parseDouble(req.getParameter("cantidad")) * Double.parseDouble(req.getParameter("precioProducto")));

    // Verificar que los parámetros no sean nulos ni vacíos
    if (cantidadStr == null || nombreProducto == null || precioProductoStr == null || totalStr == null ||
        cantidadStr.isEmpty() || nombreProducto.isEmpty() || precioProductoStr.isEmpty() || totalStr.isEmpty()) {
        resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Faltan parámetros en la solicitud");
        return;
    }

    try {
        // Convertir los parámetros a los tipos adecuados
        int cantidad = Integer.parseInt(cantidadStr);
        double precioProducto = Double.parseDouble(precioProductoStr);
        double total = Double.parseDouble(totalStr);

        // Crear el objeto Carrito
        Carrito carrito = new Carrito(cantidad, nombreProducto, precioProducto, total);
        carritoService.crearCarrito(carrito);

        // Redirigir al usuario
        resp.sendRedirect(req.getContextPath() + "/carrito-servlet");
    } catch (NumberFormatException e) {
        // Manejar el error si no se puede convertir los parámetros
        resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Formato de número inválido");
    }
}




    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();

        if (pathInfo == null || pathInfo.equals("/")) {
            String tipo = req.getParameter("tipo");
            
            if(tipo.equals("Agregar")) {
                crearCarrito(req, resp);
            } else if(tipo.equals("Incrementar")) {
                incrementarCarrito(req, resp);
            } else if(tipo.equals("Decrementar")) {
                decrementarCarrito(req, resp);
            }
        } else {
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

    private void eliminarCarrito(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Se utiliza el servicio carritoService para buscar la entidad Carrito con el ID proporcionado (carritoId).
        int carritoId = Integer.parseInt(req.getParameter("carritoId"));
        Carrito carrito = carritoService.buscarCarrito(carritoId);
        //Si la entidad Carrito no es nula (es decir, se encontrara en la base de datos), se procede a eliminarla.
        if (carrito != null) {
            carritoService.eliminarCarrito(carritoId);
            // Se envia al cliente la nueva URL, Cuando se llama a este metodo, se envi­a una respuesta HTTP al cliente
            resp.sendRedirect(req.getContextPath() + "/");
        } else {
            //Si el carrito de compras no se encuentra, se envi­a un error 404 (Not Found) al cliente.
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    //manejar las solicitudes HTTP delete.
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();

        if (pathInfo != null && !pathInfo.equals("/carrito-servlet")) {
            String[] pathParts = pathInfo.split("/");
            if (pathParts.length == 2) {
                int carritoId = Integer.parseInt(pathParts[1]);
                //eliminarProducto(productoId, req, resp);
            } else {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
    
    
    private void incrementarCarrito(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Colocamos el servicio de carrito con la funcion de buscarCarrito
        int carritoId = Integer.parseInt(req.getParameter("carritoId"));
        Carrito carrito = carritoService.buscarCarrito(carritoId);
        /* Si este recurso de carrito es diferente a nulo procede a 
        actualiza sus atributos con los valores proporcionados en la
        solicitud y persiste los cambios en la base de datos. Si la entidad 
        no se encuentra, responde con un error 404 (No Encontrado).*/

        if (carrito != null) {

            carrito.setCantidad(carrito.getCantidad() + 1);
            carrito.setTotal(carrito.getCantidad() * carrito.getPrecioProducto());
            carritoService.editarCarrito(carrito);

            resp.sendRedirect(req.getContextPath() + "/carrito-servlet");
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
    
    private void decrementarCarrito(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Colocamos el servicio de carrito con la funcion de buscarCarrito
        int carritoId = Integer.parseInt(req.getParameter("carritoId"));
        Carrito carrito = carritoService.buscarCarrito(carritoId);
        /* Si este recurso de carrito es diferente a nulo procede a 
        actualiza sus atributos con los valores proporcionados en la
        solicitud y persiste los cambios en la base de datos. Si la entidad 
        no se encuentra, responde con un error 404 (No Encontrado).*/

            if (carrito.getCantidad() > 0){
                
            carrito.setCantidad(carrito.getCantidad() - 1); 
            carrito.setTotal(carrito.getCantidad() * carrito.getPrecioProducto());
            carritoService.editarCarrito(carrito);

            resp.sendRedirect(req.getContextPath() + "/carrito-servlet");
            } else {
                carrito.setCantidad(0);
                carrito.setTotal(0);
                carritoService.editarCarrito(carrito);
                resp.sendRedirect(req.getContextPath() + "/carrito-servlet");
            }
    }
}
