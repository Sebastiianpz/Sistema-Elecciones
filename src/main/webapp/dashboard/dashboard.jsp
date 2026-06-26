<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Panel Administrativo</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/Style.css">

    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <script>var contextPath = '<%=request.getContextPath()%>';</script>
    <script src="<%=request.getContextPath()%>/scripts/loginAdmin.js"></script>

    <style>

        /* ── Skeleton loader ─────────────────────────────── */
        .skeleton {
            background: linear-gradient(90deg, #e2e8f0 25%, #f1f5f9 50%, #e2e8f0 75%);
            background-size: 200% 100%;
            animation: shimmer 1.4s infinite;
            border-radius: 8px;
            display: inline-block;
        }
        @keyframes shimmer {
            0%   { background-position: 200% 0; }
            100% { background-position: -200% 0; }
        }
        .sk-numero  { width: 80px;  height: 32px; }
        .sk-texto   { width: 120px; height: 16px; margin-top: 6px; }
        .sk-line    { width: 100%;  height: 18px; margin-bottom: 10px; }
        .sk-circle  { width: 220px; height: 220px; border-radius: 50%; }
        .sk-ganador-nombre  { width: 180px; height: 28px; margin: 12px auto; }
        .sk-ganador-partido { width: 130px; height: 16px; margin: 0 auto 16px; }
        .sk-stat    { width: 60px;  height: 28px; }

        /* ── Fade-in al revelar contenido ────────────────── */
        .fade-in {
            animation: fadeInUp .5s ease forwards;
        }
        @keyframes fadeInUp {
            from { opacity: 0; transform: translateY(12px); }
            to   { opacity: 1; transform: translateY(0); }
        }

        /* ── Indicador de refresh ────────────────────────── */
        .refresh-badge {
            font-size: 0.75rem;
            color: #64748b;
            display: flex;
            align-items: center;
            gap: 6px;
        }
        .refresh-dot {
            width: 8px;
            height: 8px;
            border-radius: 50%;
            background: #22c55e;
            animation: pulse-dot 2s infinite;
        }
        @keyframes pulse-dot {
            0%, 100% { opacity: 1; transform: scale(1); }
            50%       { opacity: .5; transform: scale(1.4); }
        }

        /* ── Contador de próximo refresh ─────────────────── */
        .countdown-text {
            font-size: 0.7rem;
            color: #94a3b8;
        }

    </style>
</head>
<body>

<!-- ══════════════ HEADER ══════════════ -->
<header class="header-primary">
    <nav class="navbar navbar-expand-lg navbar-dark">
        <div class="container-fluid px-4">
            <a class="navbar-brand" href="#">
                <i class="fas fa-shield-halved me-3"></i>
                Panel Administrativo
            </a>
            <div class="d-flex gap-2">
                <a href="dashboard-padron.html"
                   class="btn btn-logout px-3 text-decoration-none d-inline-flex align-items-center">
                    <i class="fas fa-chart-line me-2"></i>
                    Dashboard Padrón
                </a>
                <button class="btn btn-logout">
                    <i class="fas fa-right-from-bracket me-2"></i>
                    Cerrar Sesión
                </button>
            </div>
        </div>
    </nav>
</header>

<!-- ══════════════ MAIN ══════════════ -->
<main class="main-content">
    <div class="container px-4">

        <!-- Título + indicador tiempo real -->
        <div class="mb-5 mt-4 d-flex justify-content-between align-items-start flex-wrap gap-3">
            <div>
                <h1 class="titulo-dashboard">Dashboard Electoral</h1>
                <p class="subtitulo">Control y gestión del sistema electoral nacional</p>
            </div>
            <div class="d-flex flex-column align-items-end gap-1">
                <div class="refresh-badge">
                    <span class="refresh-dot"></span>
                    Actualizando en tiempo real
                </div>
                <span class="countdown-text" id="countdownText">Próxima actualización en 30s</span>
            </div>
        </div>

        <!-- ══ MÉTRICAS ══ -->
        <div class="row g-4 mb-5 row-metricas">

            <div class="col-lg-4 col-md-6 col-12">
                <div class="card-metrica border-blue">
                    <div class="d-flex justify-content-between align-items-center">
                        <div>
                            <div class="numero" id="totalPadron">
                                <span class="skeleton sk-numero"></span>
                            </div>
                            <div class="texto">Total en Padrón</div>
                        </div>
                        <div class="icon-box blue"><i class="fas fa-users"></i></div>
                    </div>
                </div>
            </div>

            <div class="col-lg-4 col-md-6 col-12">
                <div class="card-metrica border-green">
                    <div class="d-flex justify-content-between align-items-center">
                        <div>
                            <div class="numero" id="habilitados">
                                <span class="skeleton sk-numero"></span>
                            </div>
                            <div class="texto">Habilitados para Votar</div>
                        </div>
                        <div class="icon-box green"><i class="fas fa-square-check"></i></div>
                    </div>
                </div>
            </div>

            <div class="col-lg-4 col-md-6 col-12">
                <div class="card-metrica border-orange">
                    <div class="d-flex justify-content-between align-items-center">
                        <div>
                            <div class="numero" id="votosEmitidos">
                                <span class="skeleton sk-numero"></span>
                            </div>
                            <div class="texto">Votos Emitidos</div>
                        </div>
                        <div class="icon-box icon-orange"><i class="fas fa-check-to-slot"></i></div>
                    </div>
                </div>
            </div>

            <div class="col-lg-4 col-md-6 col-12">
                <div class="card-metrica border-red">
                    <div class="d-flex justify-content-between align-items-center">
                        <div>
                            <div class="numero" id="deshabilitados">
                                <span class="skeleton sk-numero"></span>
                            </div>
                            <div class="texto">Deshabilitados</div>
                        </div>
                        <div class="icon-box red"><i class="fas fa-user-slash"></i></div>
                    </div>
                </div>
            </div>

            <div class="col-lg-4 col-md-6 col-12">
                <div class="card-metrica border-cyan">
                    <div class="d-flex justify-content-between align-items-center">
                        <div>
                            <div class="numero" id="equiposActivos">
                                <span class="skeleton sk-numero"></span>
                            </div>
                            <div class="texto">Equipos Activos</div>
                        </div>
                        <div class="icon-box cyan"><i class="fas fa-laptop"></i></div>
                    </div>
                </div>
            </div>

            <div class="col-lg-4 col-md-6 col-12">
                <div class="card-metrica border-purple">
                    <div class="d-flex justify-content-between align-items-center">
                        <div>
                            <div class="numero" id="participacion">
                                <span class="skeleton sk-numero"></span>
                            </div>
                            <div class="texto">Participación</div>
                        </div>
                        <div class="icon-box purple"><i class="fas fa-chart-pie"></i></div>
                    </div>
                </div>
            </div>

        </div>

        <!-- ══ RESULTADOS + GANADOR ══ -->
        <div class="row g-4 mb-5">

            <!-- Gráfico de torta -->
            <div class="col-lg-6">
                <div class="card-custom">
                    <h3>Resultados en Tiempo Real</h3>

                    <!-- Skeleton gráfico -->
                    <div id="skeletonGrafico" class="d-flex justify-content-center mb-4">
                        <span class="skeleton sk-circle"></span>
                    </div>
                    <div id="skeletonLista">
                        <span class="skeleton sk-line d-block"></span>
                        <span class="skeleton sk-line d-block"></span>
                        <span class="skeleton sk-line d-block"></span>
                    </div>

                    <!-- Contenido real (oculto hasta que carga) -->
                    <div id="contenidoGrafico" style="display:none;">
                        <div class="d-flex justify-content-center mb-4">
                            <div class="grafico-circular" id="graficoCircular">
                                <div class="centro-grafico"></div>
                            </div>
                        </div>
                        <div id="listaResultados"></div>
                    </div>
                </div>
            </div>

            <!-- Ganador -->
            <div class="col-lg-6">
                <div class="card-custom">
                    <div class="badge-top">Candidato en Primer Lugar</div>
                    <div class="icono-ganador">
                        <i class="fas fa-trophy"></i>
                    </div>

                    <!-- Skeleton ganador -->
                    <div id="skeletonGanador" class="text-center">
                        <span class="skeleton sk-ganador-nombre d-block"></span>
                        <span class="skeleton sk-ganador-partido d-block"></span>
                        <div class="row mt-3">
                            <div class="col-6 text-center">
                                <div class="dato">Total de Votos</div>
                                <span class="skeleton sk-stat d-inline-block mt-1"></span>
                            </div>
                            <div class="col-6 text-center">
                                <div class="dato">Porcentaje</div>
                                <span class="skeleton sk-stat d-inline-block mt-1"></span>
                            </div>
                        </div>
                    </div>

                    <!-- Contenido real ganador -->
                    <div id="contenidoGanador" style="display:none;">
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
                        <div class="alert-victoria" id="alertaVentaja">
                            <i class="fas fa-arrow-trend-up me-2"></i>
                            <span id="textoVentaja"></span>
                        </div>
                    </div>

                </div>
            </div>
        </div>

        <!-- ══ GESTIÓN DEL SISTEMA ══ -->
        <h2 class="titulo-seccion">Gestión del Sistema</h2>
        <div class="row justify-content-center">

            <div class="col-lg-6 mb-4">
                <a href="/gestion-equipos/gestion-equipos.jsp"
                   class="text-decoration-none d-block card-clickable">
                    <div class="card-action action-purple">
                        <div class="icon-action purple">
                            <i class="fas fa-computer"></i>
                        </div>
                        <h5>Gestión de Equipos</h5>
                        <p>Control de PCs por MAC Address</p>
                        <div class="flecha"><i class="fas fa-chevron-right"></i></div>
                    </div>
                </a>
            </div>

            <div class="col-lg-6 col-md-6 mb-4">
                <a href="gestion-candidatos.html"
                   class="text-decoration-none d-block card-clickable">
                    <div class="card-action action-orange">
                        <div class="icon-action orange">
                            <i class="fas fa-users"></i>
                        </div>
                        <h5>Gestión de Candidatos</h5>
                        <p>Administrar candidatos de la boleta electoral</p>
                        <div class="flecha"><i class="fas fa-chevron-right"></i></div>
                    </div>
                </a>
            </div>

        </div>
    </div>
</main>

<script src="<%=request.getContextPath()%>/scripts/dashboard.js?v=2">
</script>

</body>
</html>
