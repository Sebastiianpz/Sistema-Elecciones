<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<title>Padrón Nacional Electoral - Listado</title>

<!-- Bootstrap -->
<link rel="stylesheet"
      href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">

<link rel="stylesheet"
      href="${pageContext.request.contextPath}/css/style.css">

<!-- jQuery -->
<script src="${pageContext.request.contextPath}/scripts/jquery/jquery.min.js"></script>

<!-- Chart (si después lo querés usar) -->
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

</head>

<body>

<div id="wrapper">

<!-- ================= SIDEBAR ================= -->
<div id="sidebar-wrapper">

    <a href="#" class="sidebar-brand">
        <div class="sidebar-brand-icon">🗳️</div>
        <div class="sidebar-brand-text">
            <span class="sidebar-brand-name">PadronWeb</span>
            <span class="sidebar-brand-sub">Sistema Electoral</span>
        </div>
    </a>

    <ul class="sidebar-nav">

        <li><span class="sidebar-nav-label">Principal</span></li>

        <li>
            <a href="${pageContext.request.contextPath}/vistas/listar.jsp"
               class="sidebar-nav-link active">
                📊 Dashboard
            </a>
        </li>

        <li>
            <a href="${pageContext.request.contextPath}/vistas/busqueda.jsp"
               class="sidebar-nav-link">
                🔍 Buscar Ciudadano
            </a>
        </li>

        <li>
            <a href="${pageContext.request.contextPath}/vistas/registrarPersona.jsp"
               class="sidebar-nav-link">
                ➕ Alta Padrón
            </a>
        </li>

        <li>
            <a href="${pageContext.request.contextPath}/LogoutServlet.java" class="sidebar-nav-link">
                🚪 Cerrar Sesión
            </a>
        </li>

    </ul>

</div>

<!-- ================= CONTENIDO ================= -->
<div id="page-content-wrapper">

<!-- TOPBAR -->
<nav class="navbar navbar-dark bg-primary px-3">
    <span class="navbar-brand fw-bold">
        Padrón Nacional Electoral
    </span>
</nav>

<div class="container py-4">

    <!-- ================= ESTADÍSTICAS (DEL DASHBOARD 1) ================= -->
    <div class="row g-3 mb-4">

        <div class="col-md-4">
            <div class="card text-center shadow-sm">
                <div class="card-body">
                    <h6 class="text-muted">Registrados</h6>
                    <h2 class="text-primary">1250</h2>
                </div>
            </div>
        </div>

        <div class="col-md-4">
            <div class="card text-center shadow-sm">
                <div class="card-body">
                    <h6 class="text-muted">Habilitados</h6>
                    <h2 class="text-success">1180</h2>
                </div>
            </div>
        </div>

        <div class="col-md-4">
            <div class="card text-center shadow-sm">
                <div class="card-body">
                    <h6 class="text-muted">Inhabilitados</h6>
                    <h2 class="text-danger">70</h2>
                </div>
            </div>
        </div>

    </div>

    <!-- ================= GRAFICO ================= -->
    <div class="card shadow-sm p-3 mb-4 text-center">

        <h5>Estado del Padrón</h5>

        <canvas id="grafico" style="max-width:300px;margin:auto;"></canvas>

    </div>

    <!-- ================= TABLA ================= -->
    <div class="card shadow-sm">

        <div class="card-header bg-white d-flex justify-content-between">

            <h5 class="mb-0">Ciudadanos Registrados</h5>

            <input type="text"
                   class="form-control w-25"
                   placeholder="Buscar DNI...">

        </div>

        <div class="table-responsive">

            <table class="table table-striped table-hover mb-0">

                <thead class="table-dark">
                    <tr>
                        <th>DNI</th>
                        <th>Apellido</th>
                        <th>Nombre</th>
                        <th>Estado</th>
                        <th>Acciones</th>
                    </tr>
                </thead>

                <tbody id="tabla-padron-body">
                    <!-- AJAX o datos backend -->
                </tbody>

            </table>

        </div>

    </div>

</div>

</div>
</div>

<!-- ================= SCRIPT GRAFICO ================= -->
<script>

let chart;

function cargarGrafico() {

    const habilitados = 1180;
    const inhabilitados = 70;

    const ctx = document.getElementById('grafico');

    chart = new Chart(ctx, {
        type: 'pie',
        data: {
            labels: ['Habilitados', 'Inhabilitados'],
            datasets: [{
                data: [habilitados, inhabilitados]
            }]
        }
    });
}

$(document).ready(function () {
    cargarGrafico();
});

</script>

</body>
</html>