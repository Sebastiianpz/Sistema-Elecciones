<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Dashboard</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">
</head>

<body>

<nav class="navbar navbar-dark bg-primary">
    <div class="container-fluid">

        <span class="navbar-brand">
            Padrón Nacional Electoral
        </span>

        <button class="btn btn-outline-light">
            Cerrar Sesión
        </button>

    </div>
</nav>

<div class="container mt-4">

    <div class="row">

        <div class="col-md-4">
            <div class="card text-center shadow">
                <div class="card-body">
                    <h5>Registrados</h5>
                    <h2>0</h2>
                </div>
            </div>
        </div>

        <div class="col-md-4">
            <div class="card text-center shadow">
                <div class="card-body">
                    <h5>Habilitados</h5>
                    <h2>0</h2>
                </div>
            </div>
        </div>

        <div class="col-md-4">
            <div class="card text-center shadow">
                <div class="card-body">
                    <h5>Inhabilitados</h5>
                    <h2>0</h2>
                </div>
            </div>
        </div>

    </div>

    <div class="mt-4">

        <div class="d-flex justify-content-between">

            <h3>Padrón Electoral</h3>

            <button class="btn btn-success">
                Registrar Persona
            </button>

        </div>

    </div>

    <div class="mt-4">

        <input type="text"
               class="form-control"
               placeholder="Buscar por DNI">

    </div>

    <div class="table-responsive mt-4">

        <table class="table table-striped table-hover">

            <thead class="table-dark">
                <tr>
                    <th>DNI</th>
                    <th>Apellido</th>
                    <th>Nombre</th>
                    <th>Estado</th>
                    <th>Acciones</th>
                </tr>
            </thead>

            <tbody id="tablaPersonas">

            </tbody>

        </table>

    </div>

</div>

<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.bundle.min.js"></script>

</body>
</html>