<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
<<<<<<< Updated upstream
   <meta charset="UTF-8">
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
   <title>Documento Validado</title>
   <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
   <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" rel="stylesheet">
   <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap" rel="stylesheet">
   <link rel="stylesheet" href="<%=request.getContextPath()%>/css/Style.css">
   <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
   <script type="text/javascript">
       // Definimos la variable global contextPath que usa tu JS
       var contextPath = '<%=request.getContextPath()%>';
   </script>
   <script src="<%=request.getContextPath()%>/scripts/validarDni.js"></script>
   <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
=======
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Documento Validado</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/Style.css">
 
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <script type="text/javascript">
        // Definimos la variable global contextPath que usa tu JS
        var contextPath = '<%=request.getContextPath()%>';
    </script>
 
    <script src="<%=request.getContextPath()%>/scripts/validarDni.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
>>>>>>> Stashed changes
</head>
<body>
<<<<<<< Updated upstream
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
                   <h5 id="lblNombrePersona">Cargando nombre...</h5>
                   <p id="lblDniPersona">Cargando DNI...</p>
               </div>
               <button class="btn btn-custom btn-votar" id="btnIrAVotar">
                   <i class="fas fa-vote-yea me-2"></i>
                   IR A VOTAR
               </button>
               <button class="btn btn-custom btn-admin"
                       onclick="window.location.href='<%=request.getContextPath()%>/login-admin/login-admin.jsp'">
                   <i class="fas fa-user-shield me-2"></i>
                   PANEL ADMINISTRATIVO
               </button>
               <button class="btn btn-custom btn-salir" id="btnVolverInicio">
                   <i class="fas fa-arrow-left me-2"></i>
                   VOLVER
               </button>
           </div>
       </div>
   </main>
=======

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
                    <h5 id="lblNombrePersona">Cargando nombre...</h5>
                    <p id="lblDniPersona">Cargando DNI...</p>
                </div>

                <button class="btn btn-custom btn-votar" id="btnIrAVotar">
                    <i class="fas fa-vote-yea me-2"></i>
                    IR A VOTAR
                </button>
 
                <button class="btn btn-custom btn-admin" 
                        onclick="window.location.href='<%=request.getContextPath()%>/login-admin/login-admin.jsp'">
                    <i class="fas fa-user-shield me-2"></i>
                    PANEL ADMINISTRATIVO
                </button>

                <button class="btn btn-custom btn-salir" id="btnVolverInicio">
                    <i class="fas fa-arrow-left me-2"></i>
                    VOLVER
                </button>

            </div>
        </div>
    </main>

>>>>>>> Stashed changes
</body>
</html>

