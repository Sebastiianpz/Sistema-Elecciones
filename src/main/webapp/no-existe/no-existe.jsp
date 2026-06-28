<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
   <meta charset="UTF-8">
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
   <title>No Existe en Padrón</title>
  
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
       <div class="card-ya-voto">
           <div class="icon-warning text-center my-3" style="font-size: 4rem; color: #dc3545;">
               <i class="fas fa-user-slash"></i>
           </div>
           <h1 class="titulo text-center fw-bold">
               No Existe en Padrón
           </h1>
           <p class="subtitulo text-center text-muted">
               El número de documento ingresado no coincide con ningún ciudadano registrado en el sistema.
           </p>
           <div class="datos text-center my-4 p-3 rounded shadow-sm" style="background-color: #f8f9fa;">
               <h5 class="fw-bold text-danger">
                   No registrado
               </h5>
               <p id="lblDniPersona" class="text-muted mb-0">
                   DNI: --
               </p>
           </div>
           <div class="alert alert-danger text-center d-flex align-items-center justify-content-center" role="alert">
               <i class="fas fa-exclamation-triangle me-2"></i>
               Verifique el número ingresado e intente nuevamente.
           </div>
           <button class="btn btn-primary w-100 py-2" id="btnVolverInicio">
               <i class="fas fa-arrow-left me-2"></i>
               VOLVER AL INICIO
           </button>
       </div>
   </div>
</main>
</body>
</html>
