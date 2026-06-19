<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>🗳️ Padrón Electoral Nacional</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/style.css">
    
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <script type="text/javascript">
         var contextPath = '<%=request.getContextPath()%>';
    </script>
    <script src="<%=request.getContextPath()%>/scripts/validarDni.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>

<body>

    <header class="header-primary">
        <nav class="navbar navbar-expand-lg navbar-dark">
            <div class="container">
                <a class="navbar-brand" href="#">
                    <i class="fas fa-vote-yea me-2"></i>
                    Sistema Electoral Nacional
                </a>
            </div>
        </nav>
    </header>

    <main class="main-content">
        <div class="container">
            <div class="row justify-content-center align-items-center">
                <div class="col-lg-6 col-xl-5">

                    <div class="card card-auth">
                        <div class="card-body p-5 text-center">

                            <div class="auth-icon mb-4">
                                <i class="fas fa-id-card"></i>
                            </div>

                            <h1 class="display-5 mb-3">
                                Padrón Electoral
                            </h1>

                            <p class="subtitle-auth">
                                Ingrese su número de DNI para acceder al sistema de votación nacional
                            </p>

                            <div class="input-group mb-4">
                                <span class="input-group-text">
                                    <i class="fas fa-address-card"></i>
                                </span>

                                <input
                                    type="text"
                                    class="form-control dni-input"
                                    id="inputDni"
                                    placeholder="12.345.678"
                                    maxlength="8"
                                    autocomplete="off"
                                >
                            </div>

                            <button class="btn btn-auth" id="btnValidar">
                                <span class="btn-text">
                                    <i class="fas fa-search me-2"></i>
                                    VALIDAR DNI
                                </span>
                            </button>

                        </div>
                    </div>
                    </div>
            </div>
        </div>
    </main>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>