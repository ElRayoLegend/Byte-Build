<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html data-bs-theme="dark">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Agregar Compra - BYTE & Build</title>
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
    /* Estilos personalizados para el formulario */
    .form-container {
        margin-top: 80px;
        background-color: #2C3E50;
        padding: 20px;
        border-radius: 5px;
        color: #ECF0F1;
    }
    .btn-submit {
        background-color: #E74C3C;
        color: white;
        border-radius: 0;
        transition: background-color 0.10s;
    }
    .btn-submit:hover {
        background-color: #F1948A;
    }
</style>

<!-- Contenedor principal -->
<div class="container">
    <div class="form-container">
        <h2>Agregar Compra</h2>
        <!-- Formulario para agregar compra -->
        <form action="guardar-compra.jsp" method="post">
            <div class="mb-3">
                <label for="id_compra" class="form-label">ID Compra</label>
                <input type="text" class="form-control" id="id_compra" name="id_compra" required>
            </div>
            <div class="mb-3">
                <label for="descripcion" class="form-label">Descripción</label>
                <input type="text" class="form-control" id="descripcion" name="descripcion" required>
            </div>
            <div class="mb-3">
                <label for="fecha_compra" class="form-label">Fecha de Compra</label>
                <input type="date" class="form-control" id="fecha_compra" name="fecha_compra" required>
            </div>
            <div class="mb-3">
                <label for="total" class="form-label">Total</label>
                <input type="number" class="form-control" id="total" name="total" step="0.01" required>
            </div>
            <button type="submit" class="btn btn-submit">Guardar Compra</button>
        </form>
    </div>
</div>

<!--Script para Bootstrap-->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>
</html>
