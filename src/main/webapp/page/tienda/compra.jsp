<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html data-bs-theme="dark">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Compras - BYTE & Build</title>
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
        <a class="navbar-brand" href="#">
            <img src="${pageContext.request.contextPath}/assets/brand/logo-long.svg" alt="Logo" width="168" height="20" class="d-inline-block align-text-center">
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarByteAndBuild" aria-controls="navbarByteAndBuild" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarByteAndBuild">
            <div class="navbar-nav">
                <a class="nav-link" href="${pageContext.request.contextPath}/index.jsp">Inicio</a>
                <a class="nav-link" href="${pageContext.request.contextPath}/page/compra.jsp">Tienda</a>
                <a class="nav-link" href="${pageContext.request.contextPath}/page/ayuda/soporte-tecnico.jsp">Soporte Técnico</a>
            </div>
            <div class="navbar-nav ms-auto">
                <a class="nav-link" href="${pageContext.request.contextPath}/page/tienda/carro.jsp">
                    <i class="bi-cart"></i>
                </a>
                <a class="nav-link" href="${pageContext.request.contextPath}/page/listar-cliente.jsp">
                    <i class="bi-person-circle"></i>
                </a>
            </div>
        </div>
    </div>
</nav>

<!-- Estilos personalizados -->
<style>
    /* Estilos personalizados para la tabla */
    .table-custom {
        background-color: #C0392B;
    }
    /* Estilos personalizados para las celdas de la tabla */
    .table-custom th, .table-custom td {
        text-align: center;
    }
    /* Estilo para el encabezado de la tabla */
    .table-custom thead th {
        background-color: #C0392B;
        color: black;
    }
    /* Estilos para el botón */
    .btn-add {
        background-color: #E74C3C;
        color: black;
        border-radius: 0; /* Hacer que el botón sea rectangular */
        transition: background-color 0.10s;
    }
    .btn-add:hover {
        background-color: #F1948A;
    }
    /* Estilos para el contenedor del botón */
    .btn-container {
        text-align: right;
        margin-bottom: 20px;
    }
</style>

<!-- Contenedor principal -->
<div class="container mt-5">
    <!-- Contenedor del botón -->
    <div class="btn-container">
        <a class="btn btn-add" href="agregar-compra.jsp">Agregar Compra</a>
    </div>
    <!-- Tabla personalizada -->
    <table class="table table-custom">
        <thead>
            <tr>
                <th scope="col">ID Compra</th>
                <th scope="col">Descripción</th>
                <th scope="col">Fecha de Compra</th>
                <th scope="col">Total</th>
            </tr>
        </thead>
        <tbody>
            <!-- Bucle para iterar sobre las compras y mostrar cada una en una fila -->
            <c:forEach var="compra" items="${compras}">
                <tr>
                    <td>${compra.id_compra}</td>
                    <td>${compra.descripcion}</td>
                    <td>${compra.fecha_compra}</td>
                    <td>${compra.total}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

<!--Script para Bootstrap-->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>
</html>
