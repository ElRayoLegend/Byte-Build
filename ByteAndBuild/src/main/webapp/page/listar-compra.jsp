<%@page import="org.byteandbuild.webapp.model.Compra"%>
<%@page import="org.byteandbuild.webapp.model.Proveedor"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html data-bs-theme="dark">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Proveedores - BYTE & Build</title>
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/assets/brand/logo.svg">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
          crossorigin="anonymous">
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
</head>
<body>
<!--Barra de Navegación-->
<nav class="navbar fixed-top navbar-expand-lg bg-body-tertiary">
    <div class="container-fluid">
        <a class="navbar-brand">
            <img src="${pageContext.request.contextPath}/assets/brand/logo-long.svg" alt="Logo" width="168" height="20" class="d-inline-block align-text-center">
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarByteAndBuild" aria-controls="navbarTogglerDemo01" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarByteAndBuild">
            <div class="navbar-nav">
                <a class="nav-link" href="${pageContext.request.contextPath}/index.jsp">Inicio</a>
                <a class="nav-link" aria-current="page" href="${pageContext.request.contextPath}/page/listar-producto.jsp">Tienda</a>
                <a class="nav-link" aria-current="page" href="${pageContext.request.contextPath}/soporteTecnico-servlet">Soporte Técnico</a>
                <a class="nav-link" aria-current="page" href="${pageContext.request.contextPath}/cliente-servlet">Clientes</a>
                <a class="nav-link" aria-current="page" href="${pageContext.request.contextPath}/proveedor-servlet">Proveedores</a>
                <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/compra-servlet">Compras</a>
            </div>
            <div class="navbar-nav ms-auto">
                <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/carrito-servlet">
                    <i class="bi-cart"></i>
                </a>
                <a class="nav-link" href="${pageContext.request.contextPath}/page/login.jsp">
                    <i class="bi-person-circle"></i>
                </a>
                <a class="nav-link" href="${pageContext.request.contextPath}/factura-servlet">
                    <i class="bi bi-basket"></i>
                </a>
            </div>
        </div>
    </div>
</nav>

<!--Tu Contenido Va Aquí-->

<div class="container mt-5 pt-5">
    <div class="card shadow-lg rounded">
        <div class="card-header d-flex justify-content-between align-items-center">
            <h2 class="m-0">Listado de Compras</h2>
            <a href="${pageContext.request.contextPath}/page/agregar-compra.jsp" class="btn btn-primary"><i class="bi-plus-lg"></i> Agregar Compra</a>
        </div>
        <div class="card-body">
            <div class="list-group">
                <%
                    List<Compra> compra = (List<Compra>) request.getAttribute("compra");
                    if (compra != null) {
                        for (Compra compras : compra) {
                %>
                <a href="#" class="list-group-item list-group-item-action d-flex justify-content-between align-items-center mb-2 shadow-sm rounded">
                    <div>
                        <h5 class="mb-1"><%= compras.getDescripcion() %></h5>
                        <p class="mb-1">Fecha: <%= compras.getFechaCompra() %></p>
                        <small>Total: <%= compras.getTotal() %></small><br>
                    </div>
                    <i class="bi-chevron-right"></i>
                </a>
                <%
                        }
                    }
                %>
            </div>
        </div>
    </div>
</div>


<!--Script pa' que se vea bonito UwU-->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>
</html>
