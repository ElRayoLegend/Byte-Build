<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html data-bs-theme="dark">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login - BYTE & Build</title>
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/assets/brand/logo.svg">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
          crossorigin="anonymous">
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <style>
        body {
            background-color: #212529; /* Fondo oscuro */
            color: #f8f9fa; /* Texto claro */
        }
        .login-container {
            max-width: 400px;
            margin: 0 auto;
            margin-top: 100px; /* Ajuste para centrar verticalmente */
        }
        .login-card {
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            background-color: #343a40; /* Fondo oscuro para la tarjeta */
        }
        .btn-custom-blue {
            background-color: #214CCE;
            color: white;
            border: none;
        }
        .btn-custom-blue:hover {
            background-color: #1b3a9e; /* Color más oscuro al pasar el cursor */
        }
    </style>
</head>
<body>
    <!--Barra de Navegación-->
    <nav class="navbar fixed-top navbar-expand-lg bg-body-tertiary">
        <div class="container-fluid">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/index.jsp">
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
                    <a class="nav-link" aria-current="page" href="${pageContext.request.contextPath}/page/listar-cliente.jsp">
                        <i class="bi-person-circle"></i>
                    </a>
                    <a class="nav-link" href="${pageContext.request.contextPath}/factura-servlet">
                    <i class="bi bi-basket"></i>
                </a>
                </div>
            </div>
        </div>
    </nav>

    <!--Contenido de Login-->
    <div class="container login-container">
        <div class="card login-card">
            <h2 class="text-center mb-4">Login</h2>
            <form action="${pageContext.request.contextPath}/index.jsp" method="post">
                <div class="mb-3">
                    <label for="username" class="form-label">Usuario</label>
                    <input type="text" class="form-control" id="username" name="username" required>
                </div>
                <div class="mb-3">
                    <label for="password" class="form-label">Contraseña</label>
                    <input type="password" class="form-control" id="password" name="password" required>
                </div>
                <button type="submit" class="btn btn-custom-blue w-100">Login</button>
            </form>
        </div>
    </div>

    <!--Script pa' que se vea bonito UwU-->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
            crossorigin="anonymous"></script>
</body>
</html>
