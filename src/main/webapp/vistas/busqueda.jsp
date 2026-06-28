<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="es">

<head>

<meta charset="UTF-8">

<title>Consulta de Habilitación</title>

<link rel="stylesheet"
      href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">

<link rel="stylesheet"
      href="${pageContext.request.contextPath}/css/style.css">

</head>

<body>

<div class="container">

    <div class="card busqueda-card">

        <h2>Consulta Electoral</h2>

        <p>
            Ingresá un número de documento para verificar si la persona
            se encuentra habilitada para votar.
        </p>

        <div class="input-wrap">

            <input
                type="text"
                id="input-dni"
                class="form-control"
                placeholder="Ingrese DNI">

            <button
                id="btn-consultar"
                class="btn btn-primary">

                Buscar

            </button>

        </div>

        <div id="resultado"></div>

    </div>

</div>


<script>
    const ctx = "${pageContext.request.contextPath}";
</script>

<script src="${pageContext.request.contextPath}/scripts/jquery/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/scripts/persona.js"></script>

</body>

</html>