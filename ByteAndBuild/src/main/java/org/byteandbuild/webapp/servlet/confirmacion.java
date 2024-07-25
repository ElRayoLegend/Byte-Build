/* Documentacion Nombre completo: Steven Adrián Soto Morataya Codigo Tecnico:IN5BV
 * Fecha Creacion: 14/07/24 Fecha Modificaciones: 21/07/24
 */
package org.byteandbuild.webapp.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.byteandbuild.webapp.model.Factura;
import org.byteandbuild.webapp.service.FacturaService;

public class confirmacion extends HttpServlet {

    private FacturaService facturaService;

    public void init() throws ServletException {
        super.init();
        this.facturaService = new FacturaService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Factura factura = (Factura) req.getSession().getAttribute("factura");
        req.setAttribute("factura", factura);
        req.getRequestDispatcher("/page/tienda/listar-factura.jsp").forward(req, resp);
    }
}
