<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Voto Confirmado</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/Style.css">
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <script type="text/javascript">
         var contextPath = '<%=request.getContextPath()%>';
    </script>
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
                <div class="col-lg-6">
                    <div class="card-confirmacion">
                        <div class="icono-confirmado">
                            <i class="fas fa-check"></i>
                        </div>
                        <h1 class="titulo-confirmado">¡Voto Registrado!</h1>
                        <p class="texto-confirmado">
                            Su voto fue emitido correctamente y quedó registrado en el sistema electoral nacional.
                        </p>

                        <div class="alert alert-light border mb-4 text-center">
                            <small class="text-muted d-block">Ciudadano / Documento</small>
                            <strong id="lblNombreVotante" class="d-block text-dark">Cargando...</strong>
                            <span id="lblDniVotante" class="text-muted small">Cargando...</span>
                        </div>

                        <div class="card-candidato-voto p-3 border rounded shadow-sm text-center" id="bordeCandidato">
                            <div class="my-2 d-inline-flex align-items-center justify-content-center rounded-circle mx-auto text-white" 
                                 id="circuloIcono" style="width: 70px; height: 70px; font-size: 2rem;">
                                <i class="fas fa-user"></i>
                            </div>
                            <h3 id="nombreCandidato" class="fw-bold mt-2">Cargando...</h3>
                            <p class="mb-0 fw-semibold" id="partidoCandidato">Cargando...</p>
                        </div>

                        <button class="btn btn-primary w-100 py-3 mt-4" id="btnVolverFin">
                            <i class="fas fa-home me-2"></i> VOLVER AL INICIO
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </main>

    <script>
    var nombre = sessionStorage.getItem("nombrePersona") || "Ciudadano";
    var dni    = sessionStorage.getItem("dniPersona")    || "-";
    var cand   = sessionStorage.getItem("candidatoVotado") || "Candidato";

    document.getElementById("lblNombreVotante").textContent = nombre;
    document.getElementById("lblDniVotante").textContent    = "DNI " + dni;
    document.getElementById("nombreCandidato").textContent  = cand;
    document.getElementById("partidoCandidato").textContent = "";

    sessionStorage.clear();

    document.getElementById("btnVolverFin").addEventListener("click", function() {
        window.location.href = contextPath + "/home/home.jsp";
    });
</script>
</body>
</html>