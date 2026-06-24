<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Login Administrador</title>

    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- FontAwesome -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" rel="stylesheet">

    <!-- CSS -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/Style.css">

</head>

<body>

    <!-- HEADER -->

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

    <!-- MAIN -->

    <main class="main-content">

        <div class="container d-flex justify-content-center">

            <div class="card-auth p-5 col-lg-5">

                <!-- ICONO -->

                <div class="auth-icon mb-4">

                    <i class="fas fa-lock"></i>

                </div>

                <!-- TITULO -->

                <h1 class="display-5 text-center mb-3">

                    Panel Administrativo

                </h1>

                <p class="subtitle-auth text-center">

                    Ingrese sus credenciales para acceder al sistema

                </p>

                <!-- FORM -->

                <form id="formAdmin">

                    <!-- USUARIO -->

                    <div class="mb-4">

                        <label class="form-label">

                            Usuario

                        </label>

                        <input type="text"
                               id="usuario"
                               class="form-control"
                               placeholder="Ingrese usuario">

                    </div>

                    <!-- PASSWORD -->

                    <div class="mb-4">

                        <label class="form-label">

                            Contraseña

                        </label>

                        <input type="password"
                               id="password"
                               class="form-control"
                               placeholder="Ingrese contraseña">

                    </div>

                    <!-- BOTONES -->

                    <button type="submit"
                            class="btn btn-auth">

                        <i class="fas fa-right-to-bracket me-2"></i>
                        INGRESAR

                    </button>

                    <button type="button"
                            class="btn btn-outline-secondary w-100 mt-3"
                            onclick="window.location.href='habilitado-Administrador.html'">

                        <i class="fas fa-arrow-left me-2"></i>
                        VOLVER

                    </button>

                </form>

            </div>

        </div>

    </main>

    <script>

        const formAdmin =
        document.getElementById('formAdmin');

        formAdmin.addEventListener('submit', function(e){

            e.preventDefault();

            const usuario =
            document.getElementById('usuario').value;

            const password =
            document.getElementById('password').value;

            // USUARIO Y CONTRASEÑA

            if(usuario === "admin" && password === "1234"){

                window.location.href = "dashboard.html";

            }else{

                alert("Usuario o contraseña incorrectos");

            }

        });

    </script>

</body>
</html>