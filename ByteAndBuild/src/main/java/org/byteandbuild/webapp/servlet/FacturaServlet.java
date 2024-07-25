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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.byteandbuild.webapp.model.Factura;
import org.byteandbuild.webapp.service.FacturaService;

// Aca ponemos el nombre del servlet y la url
@WebServlet(name = "FacturaServlet", urlPatterns = {"/factura-servlet"})
@MultipartConfig
public class FacturaServlet extends HttpServlet {
    private FacturaService facturaService;
    
    @Override
    public void init() throws ServletException {
        super.init();
        this.facturaService = new FacturaService();
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Factura> factura = facturaService.listarFactura();
        req.setAttribute("factura", factura);
        req.getRequestDispatcher("/page/listar-factura.jsp").forward(req, resp);
    }

    private void crearFactura(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String descripcion = req.getParameter("compraId");
            String fechaFacturaStr = req.getParameter("fechaFactura");
            double monto = Double.parseDouble(req.getParameter("monto"));

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaFactura = dateFormat.parse(fechaFacturaStr);

            Factura factura = new Factura(descripcion, fechaFactura, monto);
            facturaService.crearFactura(factura);

            resp.sendRedirect(req.getContextPath() + "/");
        } catch (ParseException | NumberFormatException e) {
            throw new ServletException("Error al procesar la solicitud", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        if ("/finalizar-compra".equals(pathInfo)) {
            // Implementa la lógica para finalizar la compra
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
                int facturaId = Integer.parseInt(pathParts[1]);
                editarFactura(facturaId, req, resp);
            } else {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    private void editarFactura(int facturaId, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Factura factura = facturaService.buscarFactura(facturaId);

        if (factura != null) {
            try {
                String descripcion = req.getParameter("descripcion");
                String fechaFacturaStr = req.getParameter("fechaFactura");
                double monto = Double.parseDouble(req.getParameter("monto"));

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date fechaFactura = dateFormat.parse(fechaFacturaStr);

                factura.setDescripcion(descripcion);
                factura.setFechaFactura(fechaFactura);
                factura.setMonto(monto);

                facturaService.editarFactura(factura);

                resp.sendRedirect(req.getContextPath() + "/confirmacion");
            } catch (NumberFormatException | ParseException e) {
                throw new ServletException("Error al procesar la solicitud", e);
            }
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void eliminarFactura(int facturaId, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Factura factura = facturaService.buscarFactura(facturaId);
        if (factura != null) {
            facturaService.eliminarFactura(facturaId);
            resp.sendRedirect(req.getContextPath() + "/factura/");
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
                int facturaId = Integer.parseInt(pathParts[1]);
                eliminarFactura(facturaId, req, resp);
            } else {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
