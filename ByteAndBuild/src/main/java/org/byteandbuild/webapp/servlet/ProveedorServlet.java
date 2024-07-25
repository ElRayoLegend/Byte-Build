package org.byteandbuild.webapp.servlet;

// Se importan las librerias de Persistencia, Los gestores Https y los modelos
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import org.byteandbuild.webapp.model.Proveedor;
import org.byteandbuild.webapp.service.ProveedorService;

@WebServlet(name = "ProveedorServlet", value = {"/proveedor-servlet"})
@MultipartConfig
public class ProveedorServlet extends HttpServlet {

    private ProveedorService proveedorService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.proveedorService = new ProveedorService();
    }

    //Se crea la clase con la que podremos ver los datos ingresador anteriormente
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Proveedor> proveedores = proveedorService.listarProveedores();
        proveedores.forEach(p -> System.out.println(p));
        req.setAttribute("proveedores", proveedores);
        req.getRequestDispatcher("/page/lista-proveedor.jsp").forward(req, resp);
    }

    // Se crea la clase para ingresar los datos y generar las transacciones
    private void crearProveedor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String direccion = request.getParameter("direccion");
        String telefono = request.getParameter("telefono");
        String correo = request.getParameter("correo");

        Proveedor proveedor = new Proveedor(nombre, direccion, telefono, correo);
        proveedorService.crearProveedor(proveedor);

        response.sendRedirect(request.getContextPath() + "/");
    }

    // Con esta clase se realiza la accion crearProveedor y registrarlos en los comandos https
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();

        if (pathInfo == null || pathInfo.equals("/")) {
            crearProveedor(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    // Se crea la clase para editar los datos y generar las transacciones
    private void editarProveedor(int proveedorId, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Proveedor proveedor = proveedorService.buscarProveedorId(proveedorId);
            if (proveedor != null) {
                String nombre = req.getParameter("nombre");
                String direccion = req.getParameter("direccion");
                String telefono = req.getParameter("telefono");
                String correo = req.getParameter("correo");

                proveedor.setNombre(nombre);
                proveedor.setDireccion(direccion);
                proveedor.setTelefono(telefono);
                proveedor.setCorreo(correo);

                proveedorService.editarProveedor(proveedor);

            resp.sendRedirect(req.getContextPath() + "/proveedores/");
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    // Con esta clase se realiza la accion editarProveedor y registrarlos en los comandos https
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();

        if (pathInfo != null && !pathInfo.equals("/")) {
            String[] pathParts = pathInfo.split("/");
            if (pathParts.length == 2) {
                int proveedorId = Integer.parseInt(pathParts[1]);
                editarProveedor(proveedorId, req, resp);
            } else {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    // Se crea la clase para eliminar los datos y generar las transacciones
    private void eliminarProveedor(int proveedorId, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Proveedor proveedor = proveedorService.buscarProveedorId(proveedorId);
        if (proveedor != null) {
            proveedorService.eliminarProveedor(proveedorId);
            resp.sendRedirect(req.getContextPath() + "/proveedores/");
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    // Con esta clase se realiza la accion eliminarProducto y registrarlos en los comandos https
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();

        if (pathInfo != null && !pathInfo.equals("/")) {
            String[] pathParts = pathInfo.split("/");
            if (pathParts.length == 2) {
                int proveedorId = Integer.parseInt(pathParts[1]);
                eliminarProveedor(proveedorId, req, resp);
            } else {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

}