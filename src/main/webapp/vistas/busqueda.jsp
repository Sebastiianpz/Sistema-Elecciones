<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<title>Buscar Persona</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">

<script src="${pageContext.request.contextPath}/assets/jquery/jquery.min.js"></script>
</head>

<body class="bg-light">

<!-- NAVBAR BOOTSTRAP BASE -->
<nav class="navbar navbar-dark bg-primary">
    <div class="container-fluid">

        <span class="navbar-brand fw-bold">
            Padrón Nacional Electoral
        </span>

        <div class="d-flex gap-2">

            <a class="btn btn-outline-light btn-sm" href="dashboard.jsp">Dashboard</a>
            <a class="btn btn-outline-light btn-sm" href="buscarPersona.jsp">Buscar</a>
            <a class="btn btn-outline-light btn-sm" href="registrarPersona.jsp">Registrar</a>
            <a class="btn btn-outline-light btn-sm" href="consultaEstado.jsp">Consultar</a>

        </div>

        <button class="btn btn-light btn-sm text-primary fw-bold">
            Cerrar sesión
        </button>

    </div>
</nav>

<div class="container py-4">

    <!-- BUSQUEDA -->
    <div class="card shadow-sm p-3">

        <h4 class="mb-3">Buscar Persona por DNI</h4>

        <div class="input-group">

            <input type="text"
                   id="dni-input"
                   class="form-control"
                   placeholder="Ingrese DNI">

            <button class="btn btn-primary" id="btn-buscar">
                Buscar
            </button>

        </div>

    </div>

    <!-- RESULTADOS -->
    <div class="card shadow-sm mt-3">

        <div class="table-responsive">

            <table class="table table-hover mb-0">

                <thead class="table-dark">
                    <tr>
                        <th>DNI</th>
                        <th>Apellido</th>
                        <th>Nombre</th>
                        <th>Estado</th>
                        <th>Acciones</th>
                    </tr>
                </thead>

                <tbody id="resultado-body"></tbody>

            </table>

        </div>

    </div>

</div>

<script>
const ctx = "${pageContext.request.contextPath}";

function buscarPersona() {

    const dni = $("#dni-input").val().trim();

    if (!dni) {
        alert("Ingresá un DNI");
        return;
    }

    $.ajax({
        url: ctx + "/Busqueda",
        type: "GET",
        data: { dni: dni },
        dataType: "json",

        success: function(data) {

            if (!data.ok) {
                $("#resultado-body").html(`<tr><td colspan="5">❌ ${data.error}</td></tr>`);
                return;
            }

            const estado = data.habilitado
                ? '<span class="badge bg-success">Habilitado</span>'
                : '<span class="badge bg-danger">Inhabilitado</span>';

            const fila = `
                <tr>
                    <td>${data.dni}</td>
                    <td>${data.apellido}</td>
                    <td>${data.nombre}</td>
                    <td>${estado}</td>
                    <td>

                        <a class="btn btn-warning btn-sm"
                           href="${ctx}/vistas/editarPersona.jsp?dni=${data.dni}">
                           Editar
                        </a>

                        <button class="btn btn-danger btn-sm"
                                onclick="toggleEstado('${data.dni}', ${data.habilitado})">
                            ${data.habilitado ? "Inhabilitar" : "Habilitar"}
                        </button>

                    </td>
                </tr>
            `;

            $("#resultado-body").html(fila);
        }
    });
}

function toggleEstado(dni, estadoActual) {
    $.post(ctx + "/Habilitar", {
        dni: dni,
        habilitado: !estadoActual
    }, function() {
        buscarPersona();
    });
}

$("#btn-buscar").on("click", buscarPersona);

$("#dni-input").on("keypress", function(e) {
    if (e.which === 13) buscarPersona();
});
</script>

</body>
</html>