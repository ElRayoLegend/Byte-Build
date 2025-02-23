<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html data-bs-theme="dark">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Carrito - BYTE & Build</title>
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/assets/brand/logo.svg">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
          crossorigin="anonymous">
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <style>
        /* Estilo para el encabezado de la tabla */
        .table thead th {
            background-color: #214CCE;
            color: white;
            text-align: center; /* Centrar el texto del encabezado */
        }
        /* Centrar el texto en las celdas del cuerpo de la tabla */
        .table tbody th, .table tbody td {
            vertical-align: middle;
            text-align: center;
        }
        /* Estilo personalizado para botones azules */
        .btn-custom-blue {
            background-color: #214CCE;
            color: white;
            border: none;
        }
        .btn-custom-blue:hover {
            background-color: #1b3a9e; /* Color más oscuro al pasar el cursor */
        }
        .btn-custom-green {
            background-color: #28a745;
            color: white;
            border: none;
        }
        .btn-custom-green:hover {
            background-color: #218838; /* Color más oscuro al pasar el cursor */
        }
    </style>
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
                    <a class="nav-link" href="${pageContext.request.contextPath}/producto-servlet">Tienda</a>
                    <a class="nav-link" aria-current="page" href="${pageContext.request.contextPath}/soporteTecnico-servlet">Soporte Técnico</a>
                    <a class="nav-link" aria-current="page" href="${pageContext.request.contextPath}/cliente-servlet">Clientes</a>
                    <a class="nav-link" aria-current="page" href="${pageContext.request.contextPath}/proveedor-servlet">Proveedores</a>
                    <a class="nav-link" aria-current="page" href="${pageContext.request.contextPath}/compra-servlet">Compras</a>
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
    <br>
    <br>
    <br>
    <div class="container">
        <h1>Mi Carrito</h1>

        <nav aria-label="breadcrumb">
          <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/index.jsp">Inicio</a></li>
            <li class="breadcrumb-item active" aria-current="page">Carrito</li>
          </ol>
        </nav>
            <div class="card">
                <table class="table">
                    <thead>
                      <tr>
                        <th scope="col">#</th>
                        <th scope="col">Producto</th>
                        <th scope="col">Cantidad</th>
                        <th scope="col">Precio</th>
                        <th scope="col">Subtotal</th>
                        <th scope="col">Opciones</th>
                      </tr>
                    </thead>
                   <tbody>
                <c:forEach var="producto" items="${carrito}" >
                <tr>
                    <th scope="row">${producto.carritoId}</th>
                    <td>${producto.nombreProducto}</td>
                    <td>${producto.cantidad}</td>
                    <td>${producto.precioProducto}</td>
                    <td>${producto.total}</td>
                    <td>
                        <div class="btn-group" role="group" aria-label="Basic example">
                            <form style="margin-bottom: 0px; padding: 0px;" class="btn btn-custom-blue" action="${pageContext.request.contextPath}/carrito-servlet" method="POST">
                                <input hidden="true" id="carritoId" name="carritoId" value="${producto.carritoId}">
                                <input hidden="true" id="tipo" name="tipo" value="Incrementar">
                                <button type="submit" class="btn btn-custom-blue">
                                    <i class="bi bi-plus-circle"></i>
                                </button>
                            </form>
                            <form style="margin-bottom: 0px; padding: 0px;" class="btn btn-custom-blue" action="${pageContext.request.contextPath}/carrito-servlet" method="POST">
                                <input hidden="true" id="carritoId" name="carritoId" value="${producto.carritoId}">
                                <input hidden="true" id="tipo" name="tipo" value="Decrementar">
                                <button type="submit" class="btn btn-custom-blue">
                                    <i class="bi bi-dash-circle"></i>
                                </button>
                            </form>
                            <form style="margin-bottom: 0px; padding: 0px;" class="btn btn-custom-green" action="${pageContext.request.contextPath}/factura-servlet" method="POST">
                                <input type="hidden" name="descripcion" value="${producto.nombreProducto}"/>
                                <input type="hidden" name="fechaFactura" value="<%= new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()) %>"/>
                                <input type="hidden" name="monto" value="${producto.precioProducto}"/> <!-- Se obtiene dinámicamente -->
                                <button type="submit" class="btn btn-custom-green">
                                    FINALIZAR COMPRA
                                </button>
                            </form>
                        </div>
                    </td>
                </tr>
                </c:forEach>
              <tr>
                <th class="text-center h3">TOTAL</th>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <th class="text-center h3">Q ${total}</th>
              </tr>
              <tr>
                
              </tr>
            </tbody>
                </table>
            </div>
    </div>

    <!--Script pa' que se vea bonito UwU-->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
            crossorigin="anonymous"></script>
</body>
</html>
