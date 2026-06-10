<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<title>Consultar Estado</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">

<script src="${pageContext.request.contextPath}/assets/jquery/jquery.min.js"></script>
</head>

<body>

<nav class="navbar-custom">
    <div class="logo">Padrón Nacional Electoral</div>

    <div class="menu">
        <a href="${pageContext.request.contextPath}/vistas/dashboard.jsp">Dashboard</a>
        <a href="${pageContext.request.contextPath}/vistas/buscarPersona.jsp">Buscar Persona</a>
        <a href="${pageContext.request.contextPath}/vistas/registrarPersona.jsp">Registrar Persona</a>
        <a href="${pageContext.request.contextPath}/vistas/consultaEstado.jsp">Consultar Estado</a>
    </div>

    <button class="btn-logout">Cerrar Sesión</button>
</nav>

<div class="container mt-4">

    <div class="card shadow-sm p-4">

        <h3>Consultar Estado Electoral</h3>

        <div class="input-group mt-3">
            <input type="text" class="form-control" id="dni-input" placeholder="Ingrese DNI">
            <button class="btn btn-primary" id="btn-consultar">
                Consultar
            </button>
        </div>

    </div>

    <div class="card shadow-sm p-4 mt-3 d-none" id="resultado">

        <h5>Resultado</h5>

        <p><strong>DNI:</strong> <span id="res-dni"></span></p>
        <p><strong>Apellido:</strong> <span id="res-apellido"></span></p>
        <p><strong>Nombre:</strong> <span id="res-nombre"></span></p>

        <p id="res-estado" class="fw-bold"></p>

    </div>

</div>

<script>
const ctx = "${pageContext.request.contextPath}";

function consultarEstado() {

    const dni = $("#dni-input").val().trim();

    if (!dni) return alert("Ingresá un DNI");

    $.ajax({
        url: ctx + "/Busqueda",
        type: "GET",
        data: { dni },
        dataType: "json",

        success: function(data) {

            if (!data.ok) {
                $("#resultado").addClass("d-none");
                return alert(data.error);
            }

            $("#res-dni").text(data.dni);
            $("#res-apellido").text(data.apellido);
            $("#res-nombre").text(data.nombre);

            $("#res-estado")
                .text(data.habilitado ? "HABILITADO PARA VOTAR" : "INHABILITADO")
                .css("color", data.habilitado ? "#198754" : "#dc3545");

            $("#resultado").removeClass("d-none");
        }
    });
}

$("#btn-consultar").click(consultarEstado);
$("#dni-input").keypress(e => e.which === 13 && consultarEstado());
</script>

</body>
</html>