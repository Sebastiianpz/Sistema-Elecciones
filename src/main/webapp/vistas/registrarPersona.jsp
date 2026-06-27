<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="es">

<head>
<meta charset="UTF-8">
<title>Registrar Persona</title>

<!-- Bootstrap -->
<link rel="stylesheet"
      href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">

<!-- Tu CSS custom -->
<link rel="stylesheet"
      href="${pageContext.request.contextPath}/css/style.css">
</head>

<body>

<!-- NAVBAR (tu diseño original) -->
<nav class="navbar-custom">

    <div class="logo">Padrón Nacional Electoral</div>

    <div class="menu">
        <a href="${pageContext.request.contextPath}/vistas/listar.jsp">Dashboard</a>
        <a href="${pageContext.request.contextPath}/vistas/busqueda.jsp">Buscar Persona</a>
        <a href="${pageContext.request.contextPath}/vistas/registrarPersona.jsp">Registrar Persona</a>
        <a href="${pageContext.request.contextPath}/vistas/consultaEstado.jsp">Consultar Estado</a>
    </div>

    <button class="btn-logout">Cerrar Sesión</button>

</nav>

<div class="container mt-4">

    <div class="card shadow-sm p-4 form-card">

        <h3 class="mb-4">Registrar Persona</h3>

        <form id="formPersona"
              action="${pageContext.request.contextPath}/Persona"
              method="post"
              enctype="multipart/form-data">

            <!-- DNI -->
            <div class="mb-3">
                <label class="form-label">DNI</label>
                <input type="text" name="dni" class="form-control" required>
            </div>

            <!-- Apellido -->
            <div class="mb-3">
                <label class="form-label">Apellido</label>
                <input type="text" name="apellido" class="form-control" required>
            </div>

            <!-- Nombre -->
            <div class="mb-3">
                <label class="form-label">Nombre</label>
                <input type="text" name="nombre" class="form-control" required>
            </div>

            <!-- Fecha -->
            <div class="mb-3">
                <label class="form-label">Fecha Nacimiento</label>
                <input type="date" name="fechaNac" class="form-control" required>
            </div>

            <!-- Sexo -->
            <div class="mb-3">
                <label class="form-label">Sexo</label>
                <select name="sexo" class="form-select">
                    <option value="M">Masculino</option>
                    <option value="F">Femenino</option>
                    <option value="X">X</option>
                </select>
            </div>

            <!-- Domicilio -->
            <div class="mb-3">
                <label class="form-label">Domicilio</label>
                <input type="text" name="domicilio" class="form-control">
            </div>

            <!-- Imagen -->
            <div class="mb-3">
                <label class="form-label">Foto</label>
                <input type="file" name="imagen" class="form-control" accept="image/*">
            </div>

            <!-- Botón -->
            <button type="submit" class="btn btn-success w-100">
                Registrar Persona
            </button>

        </form>

        <!-- Mensaje backend -->
        <div id="mensaje" class="mt-3"></div>

    </div>

</div>

<!-- JS -->
<script>
    const ctx = "${pageContext.request.contextPath}";
</script>

<script src="${pageContext.request.contextPath}/scripts/jquery/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/scripts/persona.js"></script>

</body>
</html>