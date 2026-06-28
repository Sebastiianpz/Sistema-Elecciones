<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Documento No Habilitado</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
<<<<<<< HEAD
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/style.css">
    
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <script type="text/javascript">
         var contextPath = '<%=request.getContextPath()%>';
    </script>
    <script src="<%=request.getContextPath()%>/scripts/validarDni.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
=======
 <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" rel="stylesheet">
 <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap" rel="stylesheet">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/Style.css">
 <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
 <script type="text/javascript">
      var contextPath = '<%=request.getContextPath()%>';
 </script>
 <script src="<%=request.getContextPath()%>/scripts/validarDni.js"></script>
 <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
>>>>>>> 85bc9d122377a8f1d8974d443ae6714e13cba2bd
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

            <div class="icon-danger">
                <i class="fas fa-user-times"></i>
            </div>

            <h1 class="titulo">
                Documento No Habilitado
            </h1>

            <p class="subtitulo">
                El ciudadano no se encuentra habilitado para participar en las elecciones nacionales.
            </p>

            <div class="datos">
                <h5 id="lblNombrePersona">
                    Cargando...
                </h5>
                <p id="lblDniPersona">
                    </p>
            </div>

            <div class="alerta-voto">
                <i class="fas fa-circle-exclamation me-2"></i>
                No puede emitir su voto.
            </div>

<<<<<<< HEAD
            <button class="btn btn-custom btn-salir" id="btnVolverInicio">
=======
            <!-- BOTON -->

            <button class="btn btn-custom btn-salir"
                  onclick="window.location.href='/home/home.jsp'">

>>>>>>> 85bc9d122377a8f1d8974d443ae6714e13cba2bd
                <i class="fas fa-arrow-left me-2"></i>
                VOLVER
            </button>

        </div>

    </div>
</main>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>