package org.byteandbuild.webapp.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.byteandbuild.webapp.model.Carrito;
import org.byteandbuild.webapp.service.CarritoService;
import org.byteandbuild.webapp.service.FacturaService;

// Aca ponemos el nombre del servlet y la url
@WebServlet(name = "EliminarCarrito", value = {"/EliminarCarrito"})
@MultipartConfig

// Se declara la clase carritoservlet
public class EliminarCarrito extends HttpServlet {

    private CarritoService carritoService;
    private FacturaService facturaService;

    public void init() throws ServletException {
        // Cuando el servlet inicializa esto se manda a llamar
        super.init();
        this.carritoService = new CarritoService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();

        if (pathInfo == null || pathInfo.equals("/")) {
            eliminarCarrito(req, resp);
        } else {
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
            resp.sendRedirect(req.getContextPath() + "/carrito-servlet");
        } else {
            //Si el carrito de compras no se encuentra, se envi­a un error 404 (Not Found) al cliente.
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

}
