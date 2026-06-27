<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="es">

<head>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>Registrar Ciudadano</title>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css">

</head>

<body>

<div id="wrapper">

	<!-- ═════════════ SIDEBAR ═════════════ -->

	<div id="sidebar-wrapper">

		<a href="${pageContext.request.contextPath}/vistas/listar.jsp"
			class="sidebar-brand">

			<div class="sidebar-brand-icon">🗳️</div>

			<div class="sidebar-brand-text">

				<span class="sidebar-brand-name">
					PadronWeb
				</span>

				<span class="sidebar-brand-sub">
					Sistema Electoral
				</span>

			</div>

		</a>

		<ul class="sidebar-nav">

			<li>

				<span class="sidebar-nav-label">
					Principal
				</span>

			</li>

			<li>

				<a href="${pageContext.request.contextPath}/vistas/listar.jsp"
					class="sidebar-nav-link">

					<span class="nav-icon">📊</span>

					Dashboard

				</a>

			</li>

			<li>

				<span class="sidebar-nav-label">
					Gestión
				</span>

			</li>

			<li>

				<a href="${pageContext.request.contextPath}/vistas/busqueda.jsp"
					class="sidebar-nav-link">

					<span class="nav-icon">🔍</span>

					Buscar Ciudadano

				</a>

			</li>

			<li>

				<a href="${pageContext.request.contextPath}/vistas/registrarPersona.jsp"
					class="sidebar-nav-link active">

					<span class="nav-icon">➕</span>

					Registrar Ciudadano

				</a>

			</li>

			<li>

				<span class="sidebar-nav-label">
					Sistema
				</span>

			</li>

			<li>

				<a href="${pageContext.request.contextPath}/Logout"
					class="sidebar-nav-link">

					<span class="nav-icon">🚪</span>

					Cerrar sesión

				</a>

			</li>

		</ul>

		<div class="sidebar-footer">

			<div class="sidebar-footer-text">

				Sistema de Gestión Electoral

				<br>

				<span class="sidebar-version">
					v1.0.0
				</span>

			</div>

		</div>

	</div>

	<!-- ═════════════ CONTENIDO ═════════════ -->

	<div id="page-content-wrapper">

		<div class="main-content">

			<div class="page-header">

				<h1 class="page-title">

					Registrar Ciudadano

				</h1>

				<p class="page-subtitle">

					Complete el formulario para incorporar un nuevo ciudadano al padrón electoral.

				</p>

			</div>

			<div class="table-card">

				<div class="table-card-header">

					<div>

						<div class="table-card-title">

							Formulario de Registro

						</div>

						<div class="table-card-sub">

							Complete todos los datos del ciudadano.

						</div>

					</div>

				</div>

				<div class="table-card-body">

					<form id="formPersona"
					      action="${pageContext.request.contextPath}/Persona"
					      method="post"
					      enctype="multipart/form-data">
					      						<!-- DNI -->
						<div class="mb-3">
							<label class="form-label">DNI</label>
							<input type="text"
								   name="dni"
								   class="form-control"
								   required>
						</div>

						<!-- Apellido -->
						<div class="mb-3">
							<label class="form-label">Apellido</label>
							<input type="text"
								   name="apellido"
								   class="form-control"
								   required>
						</div>

						<!-- Nombre -->
						<div class="mb-3">
							<label class="form-label">Nombre</label>
							<input type="text"
								   name="nombre"
								   class="form-control"
								   required>
						</div>

						<!-- Fecha -->
						<div class="mb-3">
							<label class="form-label">Fecha de nacimiento</label>
							<input type="date"
								   name="fechaNac"
								   class="form-control"
								   required>
						</div>

						<!-- Sexo -->
						<div class="mb-3">
							<label class="form-label">Sexo</label>

							<select name="sexo"
									class="form-select">

								<option value="M">Masculino</option>
								<option value="F">Femenino</option>
								<option value="X">No binario</option>

							</select>

						</div>

						<!-- Domicilio -->
						<div class="mb-3">
							<label class="form-label">Domicilio</label>
							<input type="text"
								   name="domicilio"
								   class="form-control">
						</div>

						<!-- Foto -->
						<div class="mb-4">
							<label class="form-label">Fotografía del DNI</label>
							<input type="file"
								   name="imagen"
								   class="form-control"
								   accept="image/*">
						</div>

						<div class="d-grid">

							<button type="submit"
									class="btn btn-success">

								Registrar Persona

							</button>

						</div>

					</form>

					<div id="mensaje" class="mt-4"></div>

				</div>

			</div>

		</div>

	</div>

</div>

<script>
	const ctx = "${pageContext.request.contextPath}";
</script>

<script src="${pageContext.request.contextPath}/scripts/jquery/jquery.min.js"></script>

<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.bundle.min.js"></script>

<script src="${pageContext.request.contextPath}/scripts/persona.js"></script>

</body>
</html>