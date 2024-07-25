<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html data-bs-theme="dark">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Productos - BYTE & Build</title>
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
                <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/producto-servlet">Tienda</a>
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

<!--Tu Contenido Va Aquí-->
    <style>
        /* Estilos personalizados para la tabla */
        .table-custom {
            background-color: #214CCE;
            border-radius: 15px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
        }
        /* Estilos personalizados para las celdas de la tabla */
        .table-custom th, .table-custom td {
            vertical-align: middle;
            text-align: center;
        }
        /* Estilo para el encabezado de la tabla */
        .table-custom thead th {
            background-color: #214CCE;
            color: white;
        }
        /* Estilos para el botón */
        .btn-add {
            background-color: #28a745;
            color: white;
            border-radius: 50px;
            transition: background-color 0.3s;
        }
        .btn-add:hover {
            background-color: #218838;
        }
        /* Estilos para el contenedor del botón */
        .btn-container {
            text-align: center;
            margin-bottom: 20px;
        }
    </style>
    <br>
    <!-- Contenedor principal -->
    <div class="container mt-5">
        
        <!-- Productos -->
        
        <div class="row">
            <c:forEach var="producto" items="${productos}" >
                <div class="col-md-4">
                    <div class="card border-secondary mb-3">
                        <div class="card-body text-secondary">
                          <h5 class="card-title">${producto.nombreProducto}</h5>
                          <p class="card-text mb-0"><i class="bi bi-info-circle-fill"></i> Descripcion: ${producto.descripcionProducto}</p>
                          <p class="card-text mb-0"><i class="bi bi-cpu"></i> Marca: ${producto.marca}</p>
                          <p class="card-text mb-0"><i class="bi bi-currency-dollar"></i> Precio: ${producto.precioProducto}</p>
                        </div>                        
                        <form style="padding-bottom: 0px;" class="card-footer bg-transparent border-secondary" action="${pageContext.request.contextPath}/carrito-servlet" method="post" enctype="mutipart/form-data">
                            <input hidden="true" id="nombreProducto" name="nombreProducto" value="${producto.nombreProducto}">
                            <input hidden="true" id="precioProducto" name="precioProducto" value="${producto.precioProducto}">
                            <input hidden="true" id="cantidad" name="cantidad" value="1">
                            <button type="submit" class="btn btn-primary">
                                <i class="bi bi-cart"></i>
                                Añadir al carrito
                            </button>
                        </form>
                    </div>
                </div>
            </c:forEach>
        </div>
        
        
        <!-- Tabla personalizada -->
        
        <!-- Contenedor del botón -->
        <div class="btn-container">
            <a class="btn btn-add" href="${pageContext.request.contextPath}/page/agregar-producto.jsp" >Agregar Producto</a>
        </div>
    </div>

<!--Script pa' que se vea bonito UwU-->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>
</html>
