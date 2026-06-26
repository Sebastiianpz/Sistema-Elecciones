<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Voto Confirmado</title>

    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- FontAwesome -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" rel="stylesheet">

    <!-- CSS -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/Style.css">

    <style>

        .card-confirmacion{

            background:white;
            border-radius:32px;
            padding:60px 45px;
            text-align:center;
            box-shadow:var(--shadow-xl);
            border:1px solid var(--border);

            animation:fadeInUp .6s ease;
        }

        .icono-confirmado{

            width:130px;
            height:130px;
            margin:auto;
            margin-bottom:30px;

            border-radius:50%;

            display:flex;
            align-items:center;
            justify-content:center;

            background:linear-gradient(135deg,#22c55e,#16a34a);

            box-shadow:0 15px 35px rgba(34,197,94,.35);

            animation:float 4s ease-in-out infinite;
        }

        .icono-confirmado i{

            color:white;
            font-size:60px;
        }

        .titulo-confirmado{

            font-size:2.3rem;
            font-weight:800;
            color:var(--dark);
            margin-bottom:15px;
        }

        .texto-confirmado{

            color:var(--mid);
            font-size:1rem;
            margin-bottom:35px;
        }

        .card-candidato-voto{

            background:var(--lighter);
            border-radius:20px;
            padding:25px;
            margin-bottom:35px;

            border:1px solid var(--border);
        }

        .foto-mini{

            width:90px;
            height:90px;

            border-radius:50%;
            overflow:hidden;

            margin:auto;
            margin-bottom:15px;

            border:4px solid white;

            box-shadow:var(--shadow);
        }

        .foto-mini img{

            width:100%;
            height:100%;
            object-fit:cover;
        }

        .btn-inicio{

            height:58px;
            border:none;
            border-radius:16px;

            font-weight:700;
            letter-spacing:1px;

            background:linear-gradient(135deg,var(--primary),var(--accent));
            color:white;

            box-shadow:0 10px 25px rgba(26,86,219,.3);

            transition:.3s;

            position:relative;
            overflow:hidden;
        }

        .btn-inicio::after{

            content:"";

            position:absolute;
            top:0;
            left:-100%;

            width:100%;
            height:100%;

            background:linear-gradient(
                90deg,
                transparent,
                rgba(255,255,255,.25),
                transparent
            );

            transition:.6s;
        }

        .btn-inicio:hover::after{
            left:100%;
        }

        .btn-inicio:hover{

            transform:translateY(-3px);

            box-shadow:0 15px 30px rgba(26,86,219,.4);
        }

    </style>

</head>

<body>

    <!-- HEADER -->

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

    <!-- MAIN -->

    <main class="main-content">

        <div class="container">

            <div class="row justify-content-center">

                <div class="col-lg-6">

                    <div class="card-confirmacion">

                        <!-- ICONO -->

                        <div class="icono-confirmado">

                            <i class="fas fa-check"></i>

                        </div>

                        <!-- TITULO -->

                        <h1 class="titulo-confirmado">
                            ¡Voto Registrado!
                        </h1>

                        <p class="texto-confirmado">

                            Su voto fue emitido correctamente y quedó registrado en el sistema electoral nacional.

                        </p>

                        <!-- CANDIDATO -->

                        <div class="card-candidato-voto">

                            <div class="foto-mini">

                                <img id="fotoCandidato">

                            </div>

                            <h3 id="nombreCandidato"></h3>

                            <p class="text-muted mb-0" id="partidoCandidato"></p>

                        </div>

                        <!-- BOTON -->

                        <button class="btn btn-inicio w-100"
                         onclick="window.location.href='inicio.html'">

                            <i class="fas fa-home me-2"></i>
                            VOLVER AL INICIO

                        </button>


                        <script>

                           const params = new URLSearchParams(window.location.search);

                           const candidato = params.get('candidato');
                           const partido = params.get('partido');
                           const foto = params.get('foto');

                           document.getElementById('nombreCandidato').innerText =
                           candidato;

                           document.getElementById('partidoCandidato').innerText =
                           partido;

                           document.getElementById('fotoCandidato').src =
                           foto;

                         </script>
                    </div>

                </div>

            </div>

        </div>

    </main>

</body>
</html>