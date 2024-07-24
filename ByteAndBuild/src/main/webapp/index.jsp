<%@page contentType="text/html" pageEncoding="UTF-8" %>
<html data-bs-theme="dark">
<head>
    <!--Asegúrate de tener ESTO MISMO en tu <head>. Si no, nada funcionará-->
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>BYTE & Build</title>
    <link rel="icon" type="image/png" href="assets/brand/logo.svg">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
          crossorigin="anonymous">
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">

    <!--Esto no lo tienes que colocar. Yo lo coloqué porque me ayudó ChatGPT-->
    <style>
        .half-screen {
            height: 100vh;
            background-size: cover;
            background-position: center;
            position: relative;
            overflow: hidden;
            display: flex;
            justify-content: center;
            align-items: center;
        }
        .bg-start {
            background-image: url("assets/image/start.jpg");
        }
        .bg-cases {
            background-image: url('assets/image/cases.png');
        }
        .bg-prebuilt {
            background-image: url('assets/image/prebuild.png');
        }
        .bg-components {
            background-image: url('assets/image/components.jpg');
        }
        .bg-bottom {
            background-image: url("assets/image/bottom.jpg");
        }
        .bg-transparent {
            position: relative;
            z-index: 1;
        }
        .bg-transparent::before {
            content: "";
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(28, 28, 30, 40%); /* Semi-transparent overlay */
            backdrop-filter: blur(5px); /* Add blur effect here */
            z-index: -1;
        }
        .content {
            position: relative;
            z-index: 2;
        }
    </style>
</head>
<body>
<!--Barra de Navegación-->
<nav class="navbar fixed-top navbar-expand-lg bg-body-tertiary bs-">
    <div class="container-fluid">
        <a class="navbar-brand">
            <img src="assets/brand/logo-long.svg" alt="Logo" width="168" height="20" class="d-inline-block align-text-center">
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarByteAndBuild" aria-controls="navbarTogglerDemo01" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarByteAndBuild">
            <div class="navbar-nav">
                <a class="nav-link active" aria-current="page" href="index.jsp">Inicio</a>
                <a class="nav-link" href="page/listar-producto.jsp">Tienda</a>
                <a class="nav-link" href="page/ayuda/soporte-tecnico.jsp">Soporte Técnico</a>
                <a class="nav-link" href="page/listar-producto.jsp">Producto</a>
            </div>
            <div class="navbar-nav ms-auto">
                <a class="nav-link" href="page/tienda/carro.jsp">
                    <i class="bi-cart"></i>
                </a>
                <a class="nav-link" href="page/listar-cliente.jsp">
                    <i class="bi-person-circle"></i>
                </a>
            </div>
        </div>
    </div>
</nav>

<!--El contenido para que se vea bien (copia esto y te funo)-->
<main>
    <div class="position-relative overflow-hidden p-3 p-md-5 m-md-3 text-center bg-transparent bg-start">
        <div class="col-md-6 p-lg-5 mx-auto my-5">
            <h1 class="display-3 fw-bold">Construye la PC de tus Sueños</h1>
            <h3 class="fw-normal text-muted mb-3">Encuentra el Mejor Hardware en<br>BYTE & Build</h3>
        </div>
        <div class="product-device shadow-sm d-none d-md-block"></div>
        <div class="product-device product-device-2 shadow-sm d-none d-md-block"></div>
    </div>

    <div class="row mx-0 my-md-3 ps-md-3">
        <div class="col-md-6 text-bg-dark pt-3 px-3 pt-md-5 px-md-5 text-center overflow-hidden half-screen bg-transparent bg-cases">
            <div class="my-3 py-3">
                <h2 class="display-5">Cases</h2>
                <p class="lead">De Todas Formas y Tamaños</p>
            </div>
        </div>
        <div class="col-md-6 bg-body-tertiary pt-3 px-3 pt-md-5 px-md-5 text-center overflow-hidden half-screen bg-transparent bg-prebuilt">
            <div class="my-3 p-3">
                <h2 class="display-5">PCs Ya Construidas</h2>
                <p class="lead"></p>
            </div>
        </div>
    </div>

    <div class="row mx-0 my-md-3 ps-md-3">
        <div class="col-md-6 bg-body-tertiary pt-3 px-3 pt-md-5 px-md-5 text-center overflow-hidden half-screen bg-transparent bg-components">
            <div class="my-3 p-3">
                <h2 class="display-5">Componentes de Primera Clase</h2>
                <p class="lead">De Marcas Reconocidas a Nivel Mundial</p>
            </div>
        </div>
        <div class="col-md-6 bg-body-tertiary pt-3 px-3 pt-md-5 px-md-5 text-center overflow-hidden half-screen bg-transparent bg-bottom">
            <div class="my-3 py-3">
                <h2 class="display-5">Marcas Confiables Asociadas</h2>
                <p class="lead">
                    <i class="bi-amd"></i>
                    <i class="bi-nvidia"></i>
                    <i class="bi-amazon"></i>
                    <i class="bi-apple"></i>
                    <i class="bi-android"></i>
                </p>
            </div>
        </div>
    </div>
</main>

<!--Script pa' que se vea bonito UwU-->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>
</html>