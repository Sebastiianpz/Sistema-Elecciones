<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Ya Votó</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap"
	rel="stylesheet">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/Style.css">

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
				<a class="navbar-brand" href="#"> <i
					class="fas fa-vote-yea me-2"></i> Sistema Electoral Nacional
				</a>
			</div>
		</nav>
	</header>
	<main class="main-content">
		<div class="container d-flex justify-content-center">
			<div class="card-ya-voto">
				<div class="icon-warning text-center my-3"
					style="font-size: 4rem; color: #ffc107;">
					<i class="fas fa-check-double"></i>
				</div>
				<h1 class="titulo text-center fw-bold">Voto Registrado</h1>
				<p class="subtitulo text-center text-muted">El ciudadano ya
					realizó su voto anteriormente en el sistema electoral.</p>
				<div class="datos text-center my-4 p-3 rounded shadow-sm"
					style="background-color: #f8f9fa;">
					<h5 id="lblNombrePersona" class="fw-bold">Cargando nombre...</h5>
					<p id="lblDniPersona" class="text-muted mb-0">DNI: --</p>
				</div>
				<div
					class="alert alert-warning text-center d-flex align-items-center justify-content-center"
					role="alert">
					<i class="fas fa-circle-info me-2"></i> Este ciudadano ya emitió su
					voto.
				</div>
				<div class="botones-container">
					<button id="btnPanelAdmin" class="btn btn-primary"
						style="display: none;">← IR AL PANEL ADMINISTRATIVO</button>

					<button id="btnVolverInicio" class="btn btn-secondary">←
						VOLVER</button>
				</div>
			</div>
		</div>
	</main>
</body>
</html>
