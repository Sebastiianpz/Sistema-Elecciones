<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Documento No Habilitado</title>

    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- FontAwesome -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" rel="stylesheet">

    <!-- CSS -->
    <link rel="stylesheet" href="style.css">

</head>

<body>

<header class="header-primary">

    <nav class="navbar navbar-expand-lg navbar-dark">

        <div class="container">

            <a class="navbar-brand" href="#">
                <i class="fas fa-vote-yea me-2"></i>
                Sistema Electoral Nacional
            </a>

        </div>

    </nav>

</header>

<main class="main-content">

    <div class="container d-flex justify-content-center">

        <div class="card-no-habilitado">

            <!-- ICONO -->

            <div class="icon-danger">

                <i class="fas fa-user-times"></i>

            </div>

            <!-- TITULO -->

            <h1 class="titulo">
                Documento No Habilitado
            </h1>

            <p class="subtitulo">
                El ciudadano no se encuentra habilitado para participar en las elecciones nacionales.
            </p>

            <!-- DATOS -->

            <div class="datos">

                <h5>
                    Juan Carlos Pérez González
                </h5>

                <p>
                    DNI: 12345678
                </p>

            </div>

            <!-- ALERTA -->

            <div class="alerta-voto">

                <i class="fas fa-circle-exclamation me-2"></i>

                No puede emitir su voto.

            </div>

            <!-- BOTON -->

            <button class="btn btn-custom btn-salir"
                  onclick="window.location.href='inicio.html'">

                <i class="fas fa-arrow-left me-2"></i>

                VOLVER

            </button>

        </div>

    </div>

</main>

</body>
</html>