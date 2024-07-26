/* Documentacion Nombre completo: Steven Adrián Soto Morataya Codigo Tecnico:IN5BV
 * Fecha Creacion: 15/07/24 Fecha Modificaciones: 23/07/24
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
import org.byteandbuild.webapp.model.Producto;
import org.byteandbuild.webapp.service.ProductoService;

@WebServlet(name = "ProductoServlet", value = {"/producto-servlet"})
@MultipartConfig
public class ProductoServlet extends HttpServlet {
    
    private ProductoService productoService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.productoService = new ProductoService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Producto> productos = productoService.listarProductos();
        productos.forEach(p -> System.out.println(p));
        req.setAttribute("productos", productos);
        req.getRequestDispatcher("/page/listar-producto.jsp").forward(req, resp);
    }
    
    private void crearProducto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombre = request.getParameter("nombreProducto");
        String marca = request.getParameter("marca");
        String descripcion = request.getParameter("descripcionProducto");
        double precio = Double.parseDouble(request.getParameter("precioProducto"));

        Producto producto = new Producto(nombre, marca, descripcion, precio);
        productoService.crearProducto(producto);

        response.sendRedirect(request.getContextPath() + "/producto-servlet");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();

        if (pathInfo == null || pathInfo.equals("/")) {
            crearProducto(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
    
    private void editarProducto(int productoId, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Producto producto = productoService.buscarProductoId(productoId);
        if (producto != null) {
            String nombre = req.getParameter("nombreProducto");
            String marca = req.getParameter("marca");
            String descripcion = req.getParameter("descripcionProducto");
            double precio = Double.parseDouble(req.getParameter("precioProducto"));

            producto.setNombreProducto(nombre);
            producto.setMarca(marca);
            producto.setDescripcionProducto(descripcion);
            producto.setPrecioProducto(precio);

            productoService.editarProducto(producto);

            resp.sendRedirect(req.getContextPath() + "/productos/");
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
    
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();

        if (pathInfo != null && !pathInfo.equals("/")) {
            String[] pathParts = pathInfo.split("/");
            if (pathParts.length == 2) {
                int productoId = Integer.parseInt(pathParts[1]);
                editarProducto(productoId, req, resp);
            } else {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    private void eliminarProducto(int productoId, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Producto producto = productoService.buscarProductoId(productoId);
        if (producto != null) {
            productoService.eliminarProducto(productoId);
            resp.sendRedirect(req.getContextPath() + "/productos/");
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
    
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();

        if (pathInfo != null && !pathInfo.equals("/")) {
            String[] pathParts = pathInfo.split("/");
            if (pathParts.length == 2) {
                int productoId = Integer.parseInt(pathParts[1]);
                eliminarProducto(productoId, req, resp);
            } else {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
    
}
