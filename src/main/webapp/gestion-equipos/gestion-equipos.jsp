<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Control de Equipos de Votación</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<header class="header-primary">
    <nav class="navbar navbar-expand-lg navbar-dark" style="background-color: #0d1b2a;">
        <div class="container-fluid px-4">
            <a class="navbar-brand fw-bold text-warning" href="dashboard.html">
                <i class="fas fa-th-large me-3"></i>
                Panel Administrativo Electoral
            </a>
            
        </div>
    </nav>
</header>

<main class="main-content py-4">
    <div class="container">
        
        <div class="mb-4">
            <a href="dashboard.html" class="btn btn-outline-primary btn-sm rounded-2 px-3">
                <i class="fas fa-arrow-left me-2"></i> VOLVER AL DASHBOARD
            </a>
        </div>

        <div class="d-flex justify-content-between align-items-center mb-4 flex-wrap gap-3">
            <div>
                <h1 class="h3 mb-1 fw-bold text-dark">Control de Equipos de Votación</h1>
                <p class="text-muted mb-0" style="font-size: 0.9rem;">Gestione los equipos habilitados por MAC Address</p>
            </div>
            <button class="btn btn-primary px-4 fw-medium shadow-sm d-inline-flex align-items-center rounded-3" data-bs-toggle="modal" data-bs-target="#modalEquipo">
                <i class="fas fa-plus me-2"></i> AGREGAR EQUIPO
            </button>
        </div>

        <div class="card border-0 shadow-sm rounded-4 p-4 mb-4 bg-white">
            <div class="d-flex flex-column gap-4" id="contenedor-equipos"></div>
        </div>

        <div class="alert alert-info border-0 shadow-sm rounded-3 d-flex align-items-start gap-3 p-3" role="alert" style="background-color: #e0f2fe; border-left: 4px solid #0ea5e9 !important;">
            <div class="text-info fs-5 mt-0"><i class="fas fa-info-circle text-primary"></i></div>
            <div style="font-size: 0.88rem; color: #0369a1; line-height: 1.5;">
                <strong>Información:</strong> Lista de terminales actualmente registradas en la red del sistema electoral. Cada equipo está identificado de manera única por su dirección física de red (MAC Address).
            </div>
        </div>

    </div>
</main>

<div class="modal fade" id="modalEquipo" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" style="max-width: 480px;">
        <div class="modal-content border-0 shadow-lg rounded-3 p-4 bg-white">
            
            <div class="modal-header border-0 p-0 mb-4">
                <h4 class="modal-title fw-bold text-dark" id="modalEquipoLabel" style="font-size: 1.3rem;">Agregar Nuevo Equipo de Votación</h4>
            </div>
            
            <div class="modal-body p-0 mb-4">
                <form id="formEquipo">
                    <input type="hidden" id="equipoId">
                    
                    <div class="mb-4">
                        <label class="form-label text-muted small fw-bold">Nombre del Equipo</label>
                        <input type="text" class="form-control px-3 py-2 border rounded" id="nombreMac" placeholder="Ej: PC Mesa 1" style="font-size: 0.95rem;" required>
                    </div>
                    
                    <div class="mb-2">
                        <label class="form-label text-muted small fw-bold">MAC Address</label>
                        <input type="text" class="form-control px-3 py-2 border rounded font-monospace" id="macAddress" placeholder="Ej: 00:1B:44:11:3A:B7" style="font-size: 0.95rem;" required>
                    </div>
                    <small class="text-muted d-block ps-1" style="font-size: 0.75rem;">Identificador único físico del equipo.</small>
                </form>
            </div>
            
            <div class="modal-footer border-0 p-0 d-flex justify-content-end gap-3">
                <button type="button" class="btn btn-link text-decoration-none fw-semibold text-primary px-2" data-bs-dismiss="modal" style="font-size: 0.88rem; letter-spacing: 0.5px;">CANCELAR</button>
                <button type="button" class="btn btn-primary px-4 py-2 fw-semibold border-0 rounded shadow-sm" id="btnGuardarEquipo" style="font-size: 0.88rem; letter-spacing: 0.5px;">AGREGAR EQUIPO</button>
            </div>

        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

<script type="text/javascript">
    var contextPath = '<%= request.getContextPath() %>';
</script>

<script src="<%= request.getContextPath() %>/scripts/equiposAdmin.js"></script>

</body>
</html>