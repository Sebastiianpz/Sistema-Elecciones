<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Documento Habilitado</title>
 
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
  <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" rel="stylesheet">
  <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap" rel="stylesheet">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/css/Style.css">
 
  <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
  <script type="text/javascript">
     var contextPath = '<%=request.getContextPath()%>';
  </script>
 
  <script src="<%=request.getContextPath()%>/scripts/validarDni.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
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
      <div class="card-validado">
          <div class="icon-success">
              <i class="fas fa-user-check"></i>
          </div>
          <h1 class="titulo">Documento Habilitado</h1>
          <p class="subtitulo">El ciudadano se encuentra habilitado para votar.</p>
         
          <div class="datos">
              <%-- Estas etiquetas recibirán los datos dinámicamente --%>
              <h5 id="lblNombrePersona">Cargando...</h5>
              <p id="lblDniPersona"></p>
          </div>
         
          <%-- Quitamos los onclick directos para manejarlos de forma segura con JS --%>
          <button class="btn btn-custom btn-votar" id="btnIrAVotar">
                <i class="fas fa-vote-yea me-2"></i>
                IR A VOTAR
          </button>
         
          <button class="btn btn-custom btn-salir" id="btnVolverInicio">
                   <i class="fas fa-arrow-left me-2"></i>
                   VOLVER
          </button>
      </div>
  </div>
</main>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>