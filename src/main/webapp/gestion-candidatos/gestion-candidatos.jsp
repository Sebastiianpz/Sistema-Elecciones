<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestión de Candidatos Electoral</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" rel="stylesheet">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/Style.css">

    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <script src="https://cdn.jsdelivr.net/npm/gasparesganga-jquery-loading-overlay@2.1.7/dist/loadingoverlay.min.js"></script>

 	<script>
 		var contextPath = "${pageContext.request.contextPath}";
 	</script>
</head>
<body class="bg-light">

<header class="header-primary">
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container-fluid px-4">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/dashboard/dashboard.jsp">
                <i class="fas fa-th-large me-3"></i> Panel Administrativo Electoral
            </a>
        </div>
    </nav>
</header>

<main class="main-content py-4">
    <div class="container">
        <div class="mb-4">
            <a href="${pageContext.request.contextPath}/dashboard/dashboard.jsp" class="btn btn-outline-primary btn-sm rounded-2 px-3">
                <i class="fas fa-arrow-left me-2"></i> VOLVER AL DASHBOARD
            </a>
        </div>

        <div class="d-flex justify-content-between align-items-center mb-4 flex-wrap gap-3">
            <div>
                <h1 class="h3 mb-1 fw-bold text-dark">Gestión de Candidatos</h1>
                <p class="text-muted mb-0" style="font-size: 0.9rem;">Administre los candidatos que aparecerán en la boleta electoral</p>
            </div>
            <button class="btn btn-primary px-4 fw-medium shadow-sm d-inline-flex align-items-center rounded-3" data-bs-toggle="modal" data-bs-target="#modalCandidato" onclick="prepararModalAgregar()">
                <i class="fas fa-plus me-2"></i> AGREGAR CANDIDATO
            </button>
        </div>

        <div class="card border-0 shadow-sm rounded-4 p-4 mb-4 bg-white">
            <div class="d-flex flex-column gap-4" id="contenedor-candidatos"></div>

            <template id="template-candidato">
                <div class="row align-items-center g-3">
                    <div class="col-12 col-md-9 d-flex align-items-center gap-4">
                        <div class="rounded-circle d-flex align-items-center justify-content-center text-white fw-bold shadow-sm avatar-candidato"
                             style="width:48px;height:48px;min-width:48px;font-size:1.2rem;"></div>
                        <div>
                            <h5 class="mb-0 fw-bold text-dark nombre-candidato" style="font-size:1.1rem;"></h5>
                            <div class="text-muted mt-1" style="font-size:0.85rem;">
                                <span class="me-3">Partido: <strong class="text-dark partido-candidato"></strong></span>
                                <span class="me-3">Color: <code class="px-2 rounded text-dark color-candidato" style="background-color:#f1f5f9;"></code></span>
                                <span>Votos: <strong class="text-dark votos-candidato"></strong></span>
                            </div>
                        </div>
                    </div>
                    <div class="col-12 col-md-3 d-flex justify-content-md-end justify-content-start align-items-center">
                        <div class="d-flex gap-2">
                            <button class="btn btn-light btn-sm text-primary rounded-2 px-3 fw-medium border btn-editar-candidato">
                                <i class="fas fa-edit me-1"></i> Editar
                            </button>
                            <button class="btn btn-light btn-sm text-danger rounded-2 px-3 fw-medium border btn-eliminar-candidato">
                                <i class="fas fa-trash-alt me-1"></i> Eliminar
                            </button>
                        </div>
                    </div>
                </div>
            </template>

            <template id="template-sin-candidatos">
                <div class="text-center py-4 text-muted">No hay candidatos registrados en la boleta electoral.</div>
            </template>
        </div>
    </div>
</main>

<div class="modal fade" id="modalCandidato" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" style="max-width: 480px;">
        <div class="modal-content border-0 shadow-lg rounded-3 p-4 bg-white">
            <div class="modal-header border-0 p-0 mb-4">
                <h4 class="modal-title fw-bold text-dark" id="modalCandidatoLabel" style="font-size: 1.3rem;">Agregar Nuevo Candidato</h4>
            </div>
            <div class="modal-body p-0 mb-4">
                <form id="formCandidato" onsubmit="return false;">
                    <input type="hidden" id="candidatoId">
                    <div class="mb-4">
                        <input type="text" class="form-control px-3 py-2.5 border rounded" id="nombreCompleto" placeholder="Nombre del Candidato" required autocomplete="off">
                    </div>
                    <div class="mb-4">
                        <input type="text" class="form-control px-3 py-2.5 border rounded" id="partido" placeholder="Partido Político" required autocomplete="off">
                    </div>
                    <div class="mb-1 d-flex align-items-center gap-3 p-2 border rounded bg-light-subtle">
                        <label for="colorPartido" class="form-label mb-0 text-muted ps-1" style="font-size: 0.9rem;">Color identificador:</label>
                        <input type="color" class="form-control form-control-color border-0 rounded-circle" id="colorPartido" value="#2563eb" style="width: 38px; height: 38px; cursor: pointer;">
                    </div>
                </form>
            </div>
            <div class="modal-footer border-0 p-0 d-flex justify-content-end gap-3">
                <button type="button" class="btn btn-link text-decoration-none fw-semibold text-primary px-2" data-bs-dismiss="modal">CANCELAR</button>
                <button type="submit" form="formCandidato" class="btn px-4 py-2 fw-semibold border-0 text-muted rounded shadow-sm" id="btnGuardarCandidato" style="background-color: #e0e0e0;">AGREGAR CANDIDATO</button>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

<script src="${pageContext.request.contextPath}/scripts/agregarCandidato.js"></script>
</body>
</html>
