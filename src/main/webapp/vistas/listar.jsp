<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>Dashboard — Padrón Nacional Electoral</title>

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

					<span class="sidebar-brand-name"> PadronWeb </span> <span
						class="sidebar-brand-sub"> Sistema Electoral </span>

				</div>

			</a>

			<ul class="sidebar-nav">

				<li><span class="sidebar-nav-label"> Principal </span></li>

				<li><a
					href="${pageContext.request.contextPath}/vistas/listar.jsp"
					class="sidebar-nav-link active"> <span class="nav-icon">📊</span>

						Dashboard

				</a></li>

				<li><span class="sidebar-nav-label"> Gestión </span></li>

				<li><a
					href="${pageContext.request.contextPath}/vistas/busqueda.jsp"
					class="sidebar-nav-link"> <span class="nav-icon">🔍</span>

						Buscar Ciudadano

				</a></li>

				<li><a
					href="${pageContext.request.contextPath}/vistas/registrarPersona.jsp"
					class="sidebar-nav-link"> <span class="nav-icon">➕</span>

						Registrar Ciudadano

				</a></li>


				<li><span class="sidebar-nav-label"> Sistema </span></li>

				<li><a href="${pageContext.request.contextPath}/Logout"
					class="sidebar-nav-link"> <span class="nav-icon">🚪</span>

						Cerrar sesión

				</a></li>

			</ul>

			<div class="sidebar-footer">

				<div class="sidebar-footer-text">

					Sistema de Gestión Electoral <br> <span
						class="sidebar-version"> v1.0.0 </span>

				</div>

			</div>

		</div>

		<!-- ═════════════ CONTENIDO ═════════════ -->

		<div id="page-content-wrapper">

			<div class="main-content">

				<div class="page-header">

					<h1 class="page-title">Padrón Nacional Electoral</h1>

					<p class="page-subtitle">Gestión y control de ciudadanos
						registrados para el proceso electoral</p>

				</div>

				<!-- TARJETAS -->

				<div class="row g-3 mb-4">

					<div class="col-12 col-sm-6 col-xl-4">

						<div class="stat-card">

							<div class="stat-icon" style="background: var(--blue-lt)">

								📋</div>

							<div class="stat-info">

								<div class="stat-label">Total Inscriptos</div>

								<div class="stat-value" style="color: var(--blue)"
									id="dash-total">—</div>

							</div>

						</div>

					</div>

					<div class="col-12 col-sm-6 col-xl-4">

						<div class="stat-card">

							<div class="stat-icon" style="background: var(--green-lt)">

								✅</div>

							<div class="stat-info">

								<div class="stat-label">Habilitados</div>

								<div class="stat-value" style="color: var(--green)"
									id="dash-habilitados">—</div>

							</div>

						</div>

					</div>

					<div class="col-12 col-sm-6 col-xl-4">

						<div class="stat-card">

							<div class="stat-icon" style="background: var(--red-lt)">

								🚫</div>

							<div class="stat-info">

								<div class="stat-label">Inhabilitados</div>

								<div class="stat-value" style="color: var(--red)"
									id="dash-inhabilitados">—</div>

							</div>

						</div>

					</div>

				</div>

				<!-- TABLA -->

				<div class="table-card">

					<div class="table-card-header">

						<div>

							<div class="table-card-title">Ciudadanos Registrados</div>

							<div class="table-card-sub">Listado completo del padrón
								electoral</div>

						</div>

						<div class="d-flex align-items-center gap-3">

							<div class="search-wrap d-none d-md-block">

								<span class="search-icon"> 🔍 </span> <input type="text"
									id="input-buscar-tabla"
									placeholder="Filtrar por DNI o nombre...">

							</div>

							<button id="btn-refrescar" class="btn-sync">🔄
								Sincronizar</button>

						</div>

					</div>

					<div class="table-card-body">

						<div id="alerta-estado" class="estado-box">

							<span class="estado-icon"> ⏳ </span>

							<p>Estableciendo conexión con el servidor...</p>

						</div>

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

										</tr>

									</thead>

									<tbody id="tabla-padron-body">

										<!-- AJAX -->

									</tbody>

								</table>

							</div>

						</div>

					</div>

				</div>

			</div>

		</div>

	</div>

	<!-- ==========================
     MODAL DETALLE
=========================== -->

<div class="modal fade"
     id="modalDetalle"
     tabindex="-1"
     aria-labelledby="modalDetalleLabel"
     aria-hidden="true">

    <div class="modal-dialog modal-lg modal-dialog-centered">

        <div class="modal-content">

            <div class="modal-header">

                <h5 class="modal-title" id="modalDetalleLabel">
                    Detalle del Ciudadano
                </h5>

                <button type="button"
                        class="btn-close"
                        data-bs-dismiss="modal">
                </button>

            </div>

            <div class="modal-body">

                <div class="row">

                    <div class="col-md-4 text-center">

                        <img id="detalle-foto"
                             src=""
                             alt="Foto DNI"
                             class="img-fluid rounded shadow"
                             style="max-width:220px;">

                    </div>

                    <div class="col-md-8">

                        <table class="table table-bordered align-middle">

                            <tr>
                                <th style="width:35%;">DNI</th>
                                <td id="detalle-dni"></td>
                            </tr>

                            <tr>
                                <th>Apellido</th>
                                <td id="detalle-apellido"></td>
                            </tr>

                            <tr>
                                <th>Nombre</th>
                                <td id="detalle-nombre"></td>
                            </tr>

                            <tr>
                                <th>Domicilio</th>
                                <td id="detalle-domicilio"></td>
                            </tr>

                            <tr>
                                <th>Fecha de nacimiento</th>
                                <td id="detalle-fecha"></td>
                            </tr>

                            <tr>
                                <th>Sexo</th>
                                <td id="detalle-sexo"></td>
                            </tr>

                            <tr>
                                <th>Estado</th>
                                <td id="detalle-estado"></td>
                            </tr>

                        </table>

                    </div>

                </div>

            </div>

            <div class="modal-footer">

                <button class="btn btn-secondary"
                        data-bs-dismiss="modal">
                    Cerrar
                </button>

            </div>

        </div>

    </div>

</div>

<!-- ==========================
     MODAL MODIFICAR
=========================== -->

<div class="modal fade"
     id="modalModificar"
     tabindex="-1"
     aria-labelledby="modalModificarLabel"
     aria-hidden="true">

    <div class="modal-dialog modal-lg modal-dialog-centered">

        <div class="modal-content">

            <div class="modal-header">

                <h5 class="modal-title"
                    id="modalModificarLabel">

                    Modificar Ciudadano

                </h5>

                <button type="button"
                        class="btn-close"
                        data-bs-dismiss="modal">
                </button>

            </div>

            <div class="modal-body">

                <div class="row">

                    <div class="col-md-4 text-center">

                        <img id="modal-mod-foto"
                             src=""
                             alt="Foto DNI"
                             class="img-fluid rounded shadow"
                             style="max-width:220px;">

                    </div>

                    <div class="col-md-8">

                        <div class="mb-3">

                            <label class="form-label">
                                Número de Documento
                            </label>

                            <input
                                type="text"
                                id="modal-mod-dni"
                                class="form-control"
                                readonly>

                        </div>

                        <div class="mb-3">

                            <label class="form-label">
                                Apellido
                            </label>

                            <input
                                type="text"
                                id="modal-mod-apellido"
                                class="form-control">

                        </div>

                        <div class="mb-3">

                            <label class="form-label">
                                Nombre
                            </label>

                            <input
                                type="text"
                                id="modal-mod-nombre"
                                class="form-control">

                        </div>

                        <div class="mb-3">

                            <label class="form-label">
                                Domicilio
                            </label>

                            <input
                                type="text"
                                id="modal-mod-domicilio"
                                class="form-control">

                        </div>
                                                <div class="mb-3">

                            <label class="form-label">
                                Fecha de nacimiento
                            </label>

                            <input
                                type="date"
                                id="modal-mod-fecha"
                                class="form-control">

                        </div>

                        <div class="mb-3">

                            <label class="form-label">
                                Sexo
                            </label>

                            <select
                                id="modal-mod-sexo"
                                class="form-select">

                                <option value="M">Masculino</option>
                                <option value="F">Femenino</option>
                                <option value="X">No binario</option>

                            </select>

                        </div>

                    </div>

                </div>

            </div>

            <div class="modal-footer">

                <button
                    type="button"
                    class="btn btn-secondary"
                    data-bs-dismiss="modal">

                    Cancelar

                </button>

                <button
                    type="button"
                    id="btn-confirmar-modificar"
                    class="btn btn-primary">

                    Guardar cambios

                </button>

            </div>

        </div>

    </div>

</div>

<!-- ==========================
     SCRIPTS
=========================== -->

<script
src="${pageContext.request.contextPath}/scripts/jquery/jquery.min.js"></script>

<script
src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.bundle.min.js"></script>

<script>

const ctx = "${pageContext.request.contextPath}";

</script>

<script
src="${pageContext.request.contextPath}/scripts/persona.js?v=${pageContext.session.lastAccessedTime}">
</script>

<div class="toast-container position-fixed bottom-0 end-0 p-3">

    <div id="toastMensaje"
         class="toast align-items-center text-bg-success border-0"
         role="alert"
         aria-live="assertive"
         aria-atomic="true">

        <div class="d-flex">

            <div class="toast-body" id="toastTexto">
                Mensaje
            </div>

            <button
                type="button"
                class="btn-close btn-close-white me-2 m-auto"
                data-bs-dismiss="toast">
            </button>

        </div>

    </div>

</div>

</body>

</html>