<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Dashboard — Padrón Nacional Electoral</title>

<!-- Bootstrap 5 Local -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/styles/css1.css">
</head>
<body>

	<div id="wrapper">

		<!-- ══ SIDEBAR ════════════════════════════════════════════════ -->
		<div id="sidebar-wrapper">

			<a href="#" class="sidebar-brand">
				<div class="sidebar-brand-icon">🗳️</div>
				<div class="sidebar-brand-text">
					<span class="sidebar-brand-name">PadronWeb</span> <span
						class="sidebar-brand-sub">Sistema Electoral</span>
				</div>
			</a>

			<ul class="sidebar-nav">
				<li><span class="sidebar-nav-label">Principal</span></li>
				<li><a href="#" class="sidebar-nav-link active"> <span
						class="nav-icon">📊</span> Vista General
				</a></li>
				<li><span class="sidebar-nav-label">Gestión</span></li>
				<li><a href="#" class="sidebar-nav-link"> <span
						class="nav-icon">🔍</span> Buscar Ciudadano
				</a></li>
				<li><a
					href="${pageContext.request.contextPath}/vistas/index.jsp"
					class="sidebar-nav-link"> <span class="nav-icon">➕</span> Alta
						Padrón
				</a></li>
				<li><span class="sidebar-nav-label">Sistema</span></li>
				<li><a href="#" class="sidebar-nav-link"> <span
						class="nav-icon">🚪</span> Cerrar Sesión
				</a></li>
			</ul>

			<div class="sidebar-footer">
				<div class="sidebar-footer-text">
					Sistema de Gestión Electoral <br> <span
						class="sidebar-version">v1.0.0</span>
				</div>
			</div>

		</div>
		<!-- ══ /SIDEBAR ═══════════════════════════════════════════════ -->

		<!-- ══ CONTENIDO ═════════════════════════════════════════════ -->
		<div id="page-content-wrapper">

			<!-- Topbar -->
			<nav class="topbar">
				<div>
					<div class="topbar-title">Panel de Control</div>
					<div class="topbar-breadcrumb">Inicio › Vista General</div>
				</div>
				<div class="topbar-right">
					<div class="topbar-badge">
						<div class="topbar-avatar">A</div>
						Administrador
					</div>
				</div>
			</nav>

			<!-- Contenido principal -->
			<div class="main-content">

				<!-- Page header -->
				<div class="page-header">
					<h1 class="page-title">Padrón Nacional Electoral</h1>
					<p class="page-subtitle">Gestión y control de ciudadanos
						registrados para el proceso electoral</p>
				</div>

				<!-- Stat cards -->
				<div class="row g-3 mb-4">

					<div class="col-12 col-sm-6 col-xl-4">
						<div class="stat-card">
							<div class="stat-icon" style="background: var(--blue-lt)">📋</div>
							<div class="stat-info">
								<div class="stat-label">Total Inscriptos</div>
								<div class="stat-value" style="color: var(--blue)"
									id="dash-total">—</div>
							</div>
						</div>
					</div>

					<div class="col-12 col-sm-6 col-xl-4">
						<div class="stat-card">
							<div class="stat-icon" style="background: var(--green-lt)">✅</div>
							<div class="stat-info">
								<div class="stat-label">Habilitados</div>
								<div class="stat-value" style="color: var(--green)"
									id="dash-habilitados">—</div>
							</div>
						</div>
					</div>

					<div class="col-12 col-sm-6 col-xl-4">
						<div class="stat-card">
							<div class="stat-icon" style="background: var(--red-lt)">🚫</div>
							<div class="stat-info">
								<div class="stat-label">Inhabilitados</div>
								<div class="stat-value" style="color: var(--red)"
									id="dash-inhabilitados">—</div>
							</div>
						</div>
					</div>

				</div>

				<!-- Tabla principal -->
				<div class="table-card">

					<div class="table-card-header">
						<div>
							<div class="table-card-title">Ciudadanos Registrados</div>
							<div class="table-card-sub">Listado completo del padrón
								electoral</div>
						</div>
						<div class="d-flex align-items-center gap-3">
							<!-- Busqueda rapida -->
							<div class="search-wrap d-none d-md-block">
								<span class="search-icon">🔍</span> <input type="text"
									id="input-buscar-tabla" placeholder="Filtrar por DNI o nombre…">
							</div>
							<button id="btn-refrescar" class="btn-sync">🔄
								Sincronizar</button>
						</div>
					</div>

					<div class="table-card-body">

						<!-- Estado de carga -->
						<div id="alerta-estado" class="estado-box">
							<span class="estado-icon">⏳</span>
							<p>Estableciendo conexión con el servidor…</p>
						</div>

						<!-- Tabla dinámica -->
						<div class="d-none" id="contenedor-tabla">
							<div class="table-responsive">
								<table class="padron-table">
									<thead>
										<tr>
											<th class="text-center" style="width: 70px;">Foto</th>
											<th>Documento (DNI)</th>
											<th>Apellido</th>
											<th>Nombre</th>
											<th class="text-center">Estado Electoral</th>
											<th class="text-center">Acciones</th>
											<!-- Columna de control -->
										</tr>
									</thead>
									<tbody id="tabla-padron-body">
										<!-- Inyectado desde persona.js -->
									</tbody>
								</table>
							</div>
						</div>

					</div>

				</div>
				<!-- /tabla-card -->

			</div>
			<!-- /main-content -->

		</div>
		<!-- ══ /CONTENIDO ════════════════════════════════════════════ -->

	</div>
	<!-- /wrapper -->

	<!-- ══ EL MODAL VA ACÁ AFUERA (Justo antes de los scripts) ════════════ -->
	<div class="modal fade" id="modalModificar" tabindex="-1"
		aria-labelledby="modalModificarLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content"
				style="background: #ffffff; color: #333333;">
				<!-- Forzamos fondo blanco -->
				<div class="modal-header">
					<h5 class="modal-title" id="modalModificarLabel">✏️ Modificar
						Ciudadano</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Cerrar"></button>
				</div>
				<div class="modal-body">
					<div class="mb-3">
						<label class="form-label">Número de Documento</label> <input
							type="text" id="modal-mod-dni" class="form-control" readonly>
					</div>
					<div class="mb-3">
						<label class="form-label">Apellido</label> <input type="text"
							id="modal-mod-apellido" class="form-control"
							placeholder="Apellido">
					</div>
					<div class="mb-3">
						<label class="form-label">Nombre</label> <input type="text"
							id="modal-mod-nombre" class="form-control" placeholder="Nombre">
					</div>
					<div class="mb-3">
						<label class="form-label">Domicilio</label> <input type="text"
							id="modal-mod-domicilio" class="form-control"
							placeholder="Domicilio">
					</div>
					<div class="mb-3">
						<label class="form-label">Fecha de Nacimiento</label> <input
							type="date" id="modal-mod-fecha" class="form-control">
					</div>
					<div class="mb-3">
						<label class="form-label">Sexo</label> <select id="modal-mod-sexo"
							class="form-select">
							<option value="M">M — Masculino</option>
							<option value="F">F — Femenino</option>
							<option value="X">X — No binario</option>
						</select>
					</div>
					<div class="mb-3">
						<label class="form-label">Estado en Padrón</label> <select
							id="modal-mod-habilitado" class="form-select">
							<option value="true">✅ Habilitado para votar</option>
							<option value="false">🚫 Inhabilitado</option>
						</select>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">Cancelar</button>
					<button type="button" id="btn-confirmar-modificar"
						class="btn btn-primary">💾 Guardar cambios</button>
				</div>
			</div>
		</div>
	</div>

	<!-- 1. Primero cargamos JQuery (Base de todo) -->
	<script
		src="${pageContext.request.contextPath}/assets/jquery/jquery.min.js"></script>

	<!-- 2. Segundo cargamos Bootstrap (Acá se define el objeto 'bootstrap' que te faltaba) -->
	<script
		src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.bundle.min.js"></script>


	<!-- 3. Tercero definimos el contexto global -->
	<script>
		const ctx = "${pageContext.request.contextPath}";
	</script>

	<!-- 4. Por ÚLTIMO cargamos tu lógica (Para que ya encuentre a JQuery y Bootstrap cargados) -->
	<script
		src="${pageContext.request.contextPath}/assets/scripts/persona.js?v=${pageContext.session.lastAccessedTime}"></script>

</body>
</html>
