<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Panel Administrativo</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
<<<<<<< Updated upstream
 <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" rel="stylesheet">
 <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap" rel="stylesheet">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/Style.css">
 <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
 <script type="text/javascript">
      var contextPath = '<%=request.getContextPath()%>';
 </script>
 <script src="<%=request.getContextPath()%>/scripts/loginAdmin.js"></script>
 <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
=======
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" rel="stylesheet">
    <link rel="stylesheet" href="style.css">
>>>>>>> Stashed changes
</head>
<body>

<header class="header-primary">
    <nav class="navbar navbar-expand-lg navbar-dark">
        <div class="container-fluid px-4">
            <a class="navbar-brand" href="#">
                <i class="fas fa-shield-halved me-3"></i>
                Panel Administrativo
            </a>

            <div class="d-flex gap-2">
<<<<<<< Updated upstream
                <a href="dashboard-padron.html" class="btn btn-logout px-3 text-decoration-none d-inline-flex align-items-center">
                    <i class="fas fa-chart-line me-2"></i>
                    Dashboard Padrón
                </a>
                <button class="btn btn-logout">
                    <i class="fas fa-right-from-bracket me-2"></i>
                    Cerrar Sesión
=======
                <a href="inicio.html" class="btn btn-logout px-3 d-inline-flex align-items-center">
                  <i class="fas fa-right-from-bracket me-2"></i>
                   Cerrar Sesión
                </a>
>>>>>>> Stashed changes
                </button>
            </div>
        </div>
    </nav>
</header>

<main class="main-content">
    <div class="container px-4">
        <div class="mb-5 mt-4">
            <h1 class="titulo-dashboard">Dashboard Electoral</h1>
            <p class="subtitulo">Control y gestión del sistema electoral nacional</p>
        </div>

        <div class="row g-4 mb-5 row-metricas">
            <div class="col-lg-4 col-md-6 col-12">
                <div class="card-metrica border-blue">
                    <div class="d-flex justify-content-between align-items-center">
                        <div>
                            <div class="numero">1247</div>
                            <div class="texto">Total en Padrón</div>
                        </div>
                        <div class="icon-box blue">
                            <i class="fas fa-users"></i>
                        </div>
                    </div>
                </div>
            </div>

            <div class="col-lg-4 col-md-6 col-12">
                <div class="card-metrica border-green">
                    <div class="d-flex justify-content-between align-items-center">
                        <div>
                            <div class="numero">1089</div>
                            <div class="texto">Habilitados para Votar</div>
                        </div>
                        <div class="icon-box green">
                            <i class="fas fa-square-check"></i>
                        </div>
                    </div>
                </div>
            </div>

            <div class="col-lg-4 col-md-6 col-12">
                <div class="card-metrica border-orange">
                    <div class="d-flex justify-content-between align-items-center">
                        <div>
                            <div class="numero">523</div>
                            <div class="texto">Votos Emitidos</div>
                        </div>
                        <div class="icon-box icon-orange">
                            <i class="fas fa-check-to-slot"></i>
                        </div>
                    </div>
                </div>
            </div>

            <div class="col-lg-4 col-md-6 col-12">
                <div class="card-metrica border-red">
                    <div class="d-flex justify-content-between align-items-center">
                        <div>
                            <div class="numero">158</div>
                            <div class="texto">Deshabilitados</div>
                        </div>
                        <div class="icon-box red">
                            <i class="fas fa-user-slash"></i>
                        </div>
                    </div>
                </div>
            </div>

            <div class="col-lg-4 col-md-6 col-12">
                <div class="card-metrica border-cyan">
                    <div class="d-flex justify-content-between align-items-center">
                        <div>
                            <div class="numero">8</div>
                            <div class="texto">Equipos Activos</div>
                        </div>
                        <div class="icon-box cyan">
                            <i class="fas fa-laptop"></i>
                        </div>
                    </div>
                </div>
            </div>

            <div class="col-lg-4 col-md-6 col-12">
                <div class="card-metrica border-purple">
                    <div class="d-flex justify-content-between align-items-center">
                        <div>
                            <div class="numero">48.0%</div>
                            <div class="texto">Participación</div>
                        </div>
                        <div class="icon-box purple">
                            <i class="fas fa-chart-pie"></i>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="row g-4 mb-5">
            <div class="col-lg-6">
                <div class="card-custom">
                    <h3>Resultados en Tiempo Real</h3>
                    <div class="d-flex justify-content-center mb-4">
                        <div class="grafico-circular" id="graficoCircular">
                            <div class="centro-grafico"></div>
                        </div>
                    </div>
                    <div id="listaResultados"></div>
                </div>
            </div>

            <div class="col-lg-6">
                <div class="card-custom">
                    <div class="badge-top">Candidato en Primer Lugar</div>
                    <div class="icono-ganador">
                        <i class="fas fa-trophy"></i>
                    </div>
                    <div class="ganador-nombre" id="ganadorNombre"></div>
                    <div class="ganador-partido" id="ganadorPartido"></div>
                    <div class="row">
                        <div class="col-6">
                            <div class="dato">Total de Votos</div>
                            <div class="valor text-blue" id="ganadorVotos"></div>
                        </div>
                        <div class="col-6">
                            <div class="dato">Porcentaje</div>
                            <div class="valor text-green" id="ganadorPorcentaje"></div>
                        </div>
                    </div>
                    <div class="alert-victoria">
                        <i class="fas fa-arrow-trend-up me-2"></i> Ventaja: 16 votos sobre el segundo lugar
                    </div>
                </div>
            </div>
        </div>

        <h2 class="titulo-seccion">Gestión del Sistema</h2>
        <div class="row justify-content-center">
            
            <div class="col-lg-6 mb-4">
<<<<<<< Updated upstream
                <a href="/gestion-equipos/gestion-equipos.jsp" class="text-decoration-none d-block card-clickable">
=======
                <a href="gestion-equipos.html" class="text-decoration-none d-block card-clickable">
>>>>>>> Stashed changes
                    <div class="card-action action-purple">
                        <div class="icon-action purple">
                            <i class="fas fa-computer"></i>
                        </div>
                        <h5>Gestión de Equipos</h5>
                        <p>Control de PCs por MAC Address</p>
                        <div class="flecha">
                            <i class="fas fa-chevron-right"></i>
                        </div>
                    </div>
                </a>
            </div>

            <div class="col-lg-6 col-md-6 mb-4">
                <a href="gestion-candidatos.html" class="text-decoration-none d-block card-clickable">
                    <div class="card-action action-orange">
                        <div class="icon-action orange">
                            <i class="fas fa-users"></i>
                        </div>
                        <h5>Gestión de Candidatos</h5>
                        <p>Administrar candidatos de la boleta electoral</p>
                        <div class="flecha">
                            <i class="fas fa-chevron-right"></i>
                        </div>
                    </div>
                </a>
            </div>
            
        </div>
    </div>
</main>
    </div>
</main>

<script>
function cargarResultados(){
    const candidatos = [
        { nombre:"María Rodríguez", partido:"Partido Progresista", votos:203, color:"#2563eb" },
        { nombre:"Carlos Fernández", partido:"Unión Nacional", votos:187, color:"#ef4444" },
        { nombre:"Ana Martínez", partido:"Frente Popular", votos:133, color:"#16a34a" }
    ];

    let total = 0;
    candidatos.forEach(c => { total += c.votos; });

    let acumulado = 0;
    let gradient = "";
    candidatos.forEach(c => {
        let porcentaje = (c.votos * 100) / total;
        gradient += `${c.color} ${acumulado}% ${acumulado + porcentaje}%,`;
        acumulado += porcentaje;
    });

    gradient = gradient.slice(0,-1);
    document.getElementById('graficoCircular').style.background = `conic-gradient(${gradient})`;

    let html = "";
    candidatos.forEach(c => {
        let porcentaje = ((c.votos * 100) / total).toFixed(1);
        html += `
        <div class="resultado-item">
            <div class="d-flex justify-content-between align-items-center">
                <div class="d-flex align-items-center">
                    <span class="color" style="background:${c.color}"></span>
                    ${c.nombre}
                </div>
                <strong>${porcentaje}%</strong>
            </div>
        </div>`;
    });

    document.getElementById('listaResultados').innerHTML = html;
    candidatos.sort((a,b)=> b.votos - a.votos);

    let ganador = candidatos[0];
    let porcentajeGanador = ((ganador.votos * 100) / total).toFixed(1);

    document.getElementById('ganadorNombre').innerText = ganador.nombre;
    document.getElementById('ganadorPartido').innerText = ganador.partido;
    document.getElementById('ganadorVotos').innerText = ganador.votos;
    document.getElementById('ganadorPorcentaje').innerText = porcentajeGanador + "%";
}
cargarResultados();
</script>

</body>
</html>