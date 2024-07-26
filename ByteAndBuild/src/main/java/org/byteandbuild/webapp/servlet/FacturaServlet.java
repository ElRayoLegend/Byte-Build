/* Documentacion Nombre completo: Mario Andreé Rodríguez Zamboni Codigo Tecnico:IN5BV
 * Fecha Creacion: 18/07/24 Fecha Modificaciones: 21/07/24
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
import org.byteandbuild.webapp.model.Factura;
import org.byteandbuild.webapp.service.FacturaService;

// Aca ponemos el nombre del servlet y la url
@WebServlet(name = "FacturaServlet", urlPatterns = {"/factura-servlet"})
@MultipartConfig

// Se declara la clase facturaservlet
public class FacturaServlet extends HttpServlet {
    // Creamos variable con su respectiva encapsulacion.
    private FacturaService facturaService;
    
    public void init() throws ServletException {
        // Cuando el servlet inicializa esto se manda a llamar
        super.init();
        this.facturaService = new FacturaService();

    }
    
   // Este http nos da la informacion que necesitamos para agregar
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Esto manda a llamar la lista
        List<Factura> factura = facturaService.listarFactura();

        factura.forEach(p -> System.out.println(p));
        req.setAttribute("factura", factura);
        // Dirige a jsp para mostrar la lista factura de compras
        req.getRequestDispatcher("/page/listar-factura.jsp").forward(req, resp);

    }

    private void crearFactura(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Con los parametros de HttpServletRequest declaramos en la db
        String descripcion = req.getParameter("descripcion");
        String fechaFactura = req.getParameter("fechaFactura");
        double monto = Double.parseDouble(req.getParameter("monto"));


        // Se obtienen los parametros de factura
        Factura factura = new Factura(descripcion, fechaFactura, monto);
        // Se crea la factura
        facturaService.crearFactura(factura);

        resp.sendRedirect(req.getContextPath() + "/factura-servlet");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Creamos el pathInfo de tipo String que tenga el HttpServletRequest con la informacion de pathInfo.
        String pathInfo = req.getPathInfo();
        // Verificar que el path info este vacio o sea "/"
        if (pathInfo == null || pathInfo.equals("/")) {

            // Crea el carrito con los parametros 
            crearFactura(req, resp);
        } else {

            // enviamos la respuesta del servidor en caso de un error
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }

    }

    private void editarFactura(int facturaId, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Colocamos el servicio de factura con la funcion de buscar
        Factura factura = facturaService.buscarFactura(facturaId);
        /* Si este recurso de factura es diferente a nulo procede a 
        actualiza sus atributos con los valores proporcionados en la
        solicitud y persiste los cambios en la base de datos. Si la entidad 
        no se encuentra, responde con un error 404 (No Encontrado).*/

        if (factura != null) {

            String descripcion = req.getParameter("descripcion");
            String fechaFactura = req.getParameter("fechaFactura");
            double monto = Double.parseDouble(req.getParameter("monto"));

            factura.setDescripcion(descripcion);
            factura.setFechaFactura(fechaFactura);
            factura.setMonto(monto);
            
            resp.sendRedirect(req.getContextPath() + "/factura/");
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
                //El ID de factura se extrae de pathParts y se convierte a un entero.
                int facturaId = Integer.parseInt(pathParts[1]);
                /*Si todas las verificaciones anteriores son correctas,
                se llama al metodo editarFactura pasando el ID de factura, 
                la solicitud (req), y la respuesta (resp).*/
                editarFactura(facturaId, req, resp);
            } else {
                //Si pathParts no tiene una longitud de 2, se envi­a un error 400 (Solicitud Incorrecta).
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }
        } else {
            //Si pathInfo es nulo o igual a "/", se envi­a un error 400 (Solicitud Incorrecta).
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    private void eliminarFactura(int facturaId, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Se utiliza el servicio facturaService para buscar la entidad factura con el ID proporcionado (facturaId).
        Factura factura = facturaService.buscarFactura(facturaId);
        //Si la entidad factura no es nula (es decir, se encontrara en la base de datos), se procede a eliminarla.
        if (factura != null) {
            facturaService.eliminarFactura(facturaId);
            // Se envia al cliente la nueva URL, Cuando se llama a este metodo, se envi­a una respuesta HTTP al cliente
            resp.sendRedirect(req.getContextPath() + "/factura/");
        } else {
            //Si la factura no se encuentra, se envi­a un error 404 (Not Found) al cliente.
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
                int facturaId = Integer.parseInt(pathParts[1]);
                // procede a eliminar con los parametros requeridos como el id el pedido y la respuesta
                eliminarFactura(facturaId, req, resp);
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