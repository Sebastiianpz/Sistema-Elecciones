<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Padrón Nacional Electoral</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">
</head>

<body class="bg-light">

<div class="container vh-100 d-flex justify-content-center align-items-center">

    <div class="card shadow p-4" style="width: 400px;">

        <h2 class="text-center mb-4">
            Padrón Nacional Electoral
        </h2>

        <form id="loginForm">

            <div class="mb-3">
                <label class="form-label">Usuario</label>
                <input type="text"
                       class="form-control"
                       id="usuario"
                       required>
            </div>

            <div class="mb-3">
                <label class="form-label">Contraseña</label>
                <input type="password"
                       class="form-control"
                       id="password"
                       required>
            </div>

            <button type="submit"
                    class="btn btn-primary w-100">
                Ingresar
            </button>

        </form>

        <div id="mensaje" class="mt-3"></div>

    </div>

</div>

<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.bundle.min.js"></script>

</body>
</html>