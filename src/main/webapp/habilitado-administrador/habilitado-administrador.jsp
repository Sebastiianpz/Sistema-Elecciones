<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Documento Validado</title>

    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- FontAwesome -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" rel="stylesheet">

    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap" rel="stylesheet">

    <!-- CSS -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/Style.css">
</head>

<body>

    <!-- HEADER -->

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

    <!-- CONTENIDO -->

    <main class="main-content">

        <div class="container d-flex justify-content-center">

            <div class="card-validado">

                <div class="icon-success">
                    <i class="fas fa-user-check"></i>
                </div>

                <h1 class="titulo">
                    Documento Habilitado
                </h1>

                <p class="subtitulo">
                    El ciudadano se encuentra habilitado para participar en las elecciones nacionales.
                </p>

                <div class="datos">

                    <h5>
                        Juan Carlos Pérez González
                    </h5>

                    <p>
                        DNI: 12345678
                    </p>

                </div>

                 <button class="btn btn-custom btn-votar"
                  onclick="window.location.href='/votacion/votacion.jsp?tipo=admin'">

                 <i class="fas fa-vote-yea me-2"></i>
                 IR A VOTAR

                   </button>
 
                 <button class="btn btn-custom btn-admin"
                 onclick="window.location.href='/login-admin/login-admin.jsp'">

                    <i class="fas fa-user-shield me-2"></i>
                    PANEL ADMINISTRATIVO

                 </button>

                 <button class="btn btn-custom btn-salir"
                 onclick="window.location.href='/home/home.jsp'">

                    <i class="fas fa-arrow-left me-2"></i>
                    VOLVER

                 </button>

            </div>

        </div>

    </main>

</body>
</html>