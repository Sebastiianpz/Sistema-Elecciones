<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="es">

<head>
<meta charset="UTF-8">
<title>Padrón Nacional Electoral</title>

<!-- Bootstrap -->
<link rel="stylesheet"
      href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">

<!-- Tu CSS custom (opcional para fondo/login-card) -->
<link rel="stylesheet"
      href="${pageContext.request.contextPath}/css/style.css">
</head>

<body class="fondo-login bg-light">

<div class="container vh-100 d-flex justify-content-center align-items-center">

    <div class="card shadow login-card p-4" style="width: 400px;">

        <h2 class="text-center mb-4">
            Padrón Nacional Electoral
        </h2>

        <form id="loginForm">

            <!-- Usuario -->
            <div class="mb-3">
                <label class="form-label">Usuario</label>
                <input type="text"
                       class="form-control"
                       id="usuario"
                       placeholder="Ingrese usuario"
                       required>
            </div>

            <!-- Password -->
            <div class="mb-3">
                <label class="form-label">Contraseña</label>
                <input type="password"
                       class="form-control"
                       id="password"
                       placeholder="Ingrese contraseña"
                       required>
            </div>

            <!-- Botón -->
            <button type="submit"
                    class="btn btn-primary w-100">
                Ingresar
            </button>

        </form>

        <!-- Mensajes login -->
        <div id="mensaje" class="mt-3 text-center"></div>

    </div>

</div>

<!-- Bootstrap JS -->
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.bundle.min.js"></script>

</body>
</html>