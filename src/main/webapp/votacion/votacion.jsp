<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sistema de Votación</title>
    
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/Style.css">
    
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <script type="text/javascript">
         var contextPath = '<%=request.getContextPath()%>';
    </script>
    
    <%-- Tu script encargado de cargar todo dinámicamente --%>
    <script src="<%=request.getContextPath()%>/scripts/guardarVoto.js"></script>
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
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-lg-11 col-xl-10">

                    <div class="card-votacion">
                        <div class="text-center p-5 bg-gradient-success text-white">
                            <div class="auth-icon mb-4">
                                <i class="fas fa-check-circle"></i>
                            </div>
                            <h1 class="display-6 mb-3">Sistema de Votación</h1>
                            <p class="mb-0 opacity-75">Seleccione el candidato de su preferencia</p>
                        </div>

                        <div class="card-body p-5">
                            
                            <div class="alert alert-light border mb-5">
                                <div class="row">
                                    <div class="col-md-6 mb-3 mb-md-0">
                                        <small class="text-muted d-block">Ciudadano</small>
                                        <strong id="lblNombreVotante">Cargando...</strong>
                                    </div>
                                    <div class="col-md-6">
                                        <small class="text-muted d-block">Documento</small>
                                        <strong id="lblDniVotante">Cargando...</strong>
                                    </div>
                                </div>
                            </div>

                            <div class="text-center mb-5">
                                <h3 class="fw-bold">Candidatos Presidenciales</h3>
                                <p class="text-muted">Haga clic sobre una tarjeta para votar</p>
                            </div>

                            <div class="row g-4" id="contenedor-candidatos">
                                <div class="text-center w-100 p-4">
                                    <div class="spinner-border text-primary" role="status"></div>
                                    <p class="mt-2 text-muted">Cargando candidatos desde el padrón...</p>
                                </div>
                            </div>

                            <div class="text-center mt-5">
                                <%-- Volvemos a activar el evento que tenías con onclick seguro --%>
                                <button class="btn btn-outline-secondary px-5 py-3 ms-3" onclick="volverPagina()">
                                    <i class="fas fa-arrow-left me-2"></i> VOLVER
                                </button>
                            </div>

                        </div>
                    </div>

                </div>
            </div>
        </div>
    </main>
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>