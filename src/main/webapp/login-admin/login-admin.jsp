<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Administrador</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" rel="stylesheet">
    <link class="styles" rel="stylesheet" href="<%=request.getContextPath()%>/css/Style.css">

    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/gasparesganga-jquery-loading-overlay@2.1.7/dist/loadingoverlay.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

    <script type="text/javascript">
        var contextPath = '<%=request.getContextPath()%>';
    </script>
</head>
<body>

    <header class="header-primary">
        <nav class="navbar navbar-expand-lg navbar-dark">
            <div class="container">
                <a class="navbar-brand" href="#">
                    <i class="fas fa-user-shield me-2"></i>
                    Acceso Administrativo
                </a>
            </div>
        </nav>
    </header>

    <main class="main-content">
        <div class="container d-flex justify-content-center">
            <div class="card-auth p-5 col-lg-5">
                
                <div class="auth-icon mb-4">
                    <i class="fas fa-lock"></i>
                </div>

                <h1 class="display-5 text-center mb-3">Panel Administrativo</h1>
                <p class="subtitle-auth text-center">Ingrese sus credenciales para acceder al sistema</p>

                <form id="formAdmin">
                    <div class="mb-4">
                        <label class="form-label">Usuario</label>
                        <input type="text" id="username" class="form-control" placeholder="Ingrese usuario" required>
                    </div>

                    <div class="mb-4" id="grupo-password">
                        <label class="form-label">Contraseña</label>
                        <input type="password" id="password" class="form-control" placeholder="Ingrese contraseña" required>
                    </div>

                    <button type="button" id="btn-login" class="btn btn-auth w-100">
                        <i class="fas fa-right-to-bracket me-2"></i> INGRESAR
                    </button>

                    <button type="button" class="btn btn-outline-secondary w-100 mt-3"
                            onclick="window.location.href=contextPath + '/habilitado-administrador/habilitado-administrador.jsp'">
                        <i class="fas fa-arrow-left me-2"></i> VOLVER
                    </button>
                </form>

            </div>
        </div>
    </main>

<script src="<%=request.getContextPath()%>/scripts/loginAdmin.js?v=2"></script>
</body>
</html>