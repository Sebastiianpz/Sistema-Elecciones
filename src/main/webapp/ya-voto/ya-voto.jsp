<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ya Votó</title>

    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- FontAwesome -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" rel="stylesheet">

    <!-- CSS global -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/Style.css">

    <style>
        .card-ya-voto {
            background: white;
            border-radius: 32px;
            padding: 60px 45px;
            text-align: center;
            box-shadow: var(--shadow-xl);
            border: 1px solid var(--border);
            animation: fadeInUp .6s ease;
            max-width: 500px;
            width: 100%;
        }

        .icon-warning {
            width: 130px;
            height: 130px;
            margin: auto;
            margin-bottom: 30px;
            border-radius: 50%;
            display: flex;
            align-items: center;
            justify-content: center;
            background: linear-gradient(135deg, #f59e0b, #d97706);
            box-shadow: 0 15px 35px rgba(245,158,11,.35);
            animation: float 4s ease-in-out infinite;
        }

        .icon-warning i {
            color: white;
            font-size: 60px;
        }

        .titulo {
            font-size: 2rem;
            font-weight: 800;
            color: var(--dark);
            margin-bottom: 15px;
        }

        .subtitulo {
            color: var(--mid);
            font-size: 1rem;
            margin-bottom: 30px;
        }

        .datos {
            background: var(--lighter);
            border-radius: 16px;
            padding: 20px 25px;
            margin-bottom: 25px;
            border: 1px solid var(--border);
        }

        .datos h5 {
            font-weight: 700;
            color: var(--dark);
            margin-bottom: 5px;
        }

        .datos p {
            color: var(--mid);
            margin: 0;
        }

        .alerta-voto-realizado {
            background: #fef3c7;
            border: 1px solid #fbbf24;
            border-radius: 12px;
            padding: 14px 20px;
            color: #92400e;
            font-size: 0.9rem;
            font-weight: 500;
            margin-bottom: 30px;
        }

        .btn-salir {
            height: 58px;
            border: none;
            border-radius: 16px;
            font-weight: 700;
            letter-spacing: 1px;
            background: linear-gradient(135deg, #64748b, #475569);
            color: white;
            width: 100%;
            box-shadow: 0 10px 25px rgba(100,116,139,.3);
            transition: .3s;
        }

        .btn-salir:hover {
            transform: translateY(-3px);
            box-shadow: 0 15px 30px rgba(100,116,139,.4);
        }
    </style>
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
    <div class="container d-flex justify-content-center">
        <div class="card-ya-voto">

            <!-- ÍCONO -->
            <div class="icon-warning">
                <i class="fas fa-check-double"></i>
            </div>

            <!-- TÍTULO -->
            <h1 class="titulo">Voto Registrado</h1>

            <p class="subtitulo">
                El ciudadano ya realizó su voto anteriormente en el sistema electoral.
            </p>

            <!-- DATOS dinámicos desde sesión -->
            <div class="datos">
                <h5><%=
                    session.getAttribute("nombrePersona") != null
                        ? session.getAttribute("nombrePersona")
                        : "Ciudadano"
                %></h5>
                <p>DNI: <%=
                    session.getAttribute("dniPersona") != null
                        ? session.getAttribute("dniPersona")
                        : "-"
                %></p>
            </div>

            <!-- ALERTA -->
            <div class="alerta-voto-realizado">
                <i class="fas fa-circle-info me-2"></i>
                Este ciudadano ya emitió su voto.
            </div>

            <!-- BOTÓN -->
            <button class="btn btn-salir"
                    onclick="window.location.href='<%=request.getContextPath()%>/home/home.jsp'">
                <i class="fas fa-arrow-left me-2"></i>
                VOLVER
            </button>

        </div>
    </div>
</main>

</body>
</html>