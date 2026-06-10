<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Consulta Electoral — Padrón Nacional</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.min.css">
<style>
    body {
        min-height: 100vh;
        display: flex;
        align-items: center;
        justify-content: center;
        background: #f4f6fb;
        margin: 0;
    }
    .consulta-card {
        background: #ffffff;
        border-radius: 16px;
        box-shadow: 0 4px 32px rgba(0,0,0,0.10);
        padding: 2.5rem 2rem;
        width: 100%;
        max-width: 480px;
    }
    .consulta-logo {
        font-size: 2.5rem;
        text-align: center;
        margin-bottom: 0.25rem;
    }
    .consulta-titulo {
        text-align: center;
        font-size: 1.25rem;
        font-weight: 700;
        color: #1a202c;
        margin-bottom: 0.25rem;
    }
    .consulta-sub {
        text-align: center;
        font-size: 0.875rem;
        color: #718096;
        margin-bottom: 2rem;
    }
    .consulta-input {
        font-size: 1.1rem;
        border-radius: 10px;
        border: 1.5px solid #cbd5e0;
        padding: 0.65rem 1rem;
        width: 100%;
        outline: none;
        box-sizing: border-box;
    }
    .consulta-input:focus {
        border-color: #4f8ef7;
        box-shadow: 0 0 0 3px rgba(79,142,247,0.15);
    }
    .btn-consultar {
        width: 100%;
        background: #4f8ef7;
        color: #fff;
        border: none;
        border-radius: 10px;
        padding: 0.7rem;
        font-size: 1rem;
        font-weight: 600;
        cursor: pointer;
        margin-top: 0.75rem;
        transition: background 0.2s;
    }
    .btn-consultar:hover { background: #2563eb; }
    .btn-consultar:disabled { background: #a0aec0; cursor: not-allowed; }

    .resultado-box {
        border-radius: 12px;
        padding: 1.25rem 1.5rem;
        text-align: center;
        margin-top: 1.5rem;
    }
    .resultado-box.habilitado {
        background: #f0fdf4;
        border: 1.5px solid #86efac;
    }
    .resultado-box.inhabilitado {
        background: #fff1f2;
        border: 1.5px solid #fca5a5;
    }
    .resultado-icono { font-size: 2.5rem; margin-bottom: 0.5rem; }
    .resultado-nombre {
        font-size: 1.1rem;
        font-weight: 700;
        color: #1a202c;
        margin-bottom: 0.25rem;
    }
    .resultado-dni {
        font-size: 0.85rem;
        color: #718096;
        margin-bottom: 0.75rem;
    }
    .resultado-estado { font-size: 1rem; font-weight: 600; }
    .resultado-box.habilitado .resultado-estado { color: #16a34a; }
    .resultado-box.inhabilitado .resultado-estado { color: #dc2626; }

    .resultado-error {
        background: #fff7ed;
        border: 1.5px solid #fed7aa;
        border-radius: 12px;
        padding: 1rem 1.5rem;
        text-align: center;
        color: #c2410c;
        font-weight: 600;
        margin-top: 1.5rem;
    }
    .volver-link {
        display: block;
        text-align: center;
        margin-top: 1.5rem;
        font-size: 0.85rem;
        color: #718096;
        text-decoration: none;
    }
    .volver-link:hover { color: #4f8ef7; }
</style>
</head>
<body>

    <div class="consulta-card">

        <div class="consulta-logo">🗳️</div>
        <div class="consulta-titulo">Consulta Electoral</div>
        <div class="consulta-sub">Ingresá tu DNI para verificar si estás habilitado para votar</div>

        <input type="text"
               id="input-dni"
               class="consulta-input"
               placeholder="Ej: 12345678"
               maxlength="20"
               autofocus>

        <button class="btn-consultar" id="btn-consultar">
            🔍 Verificar habilitación
        </button>

        <div id="resultado"></div>

        <a href="${pageContext.request.contextPath}/vistas/listar.jsp" class="volver-link">
            ← Volver al panel de administración
        </a>

    </div>

    <script src="${pageContext.request.contextPath}/assets/jquery/jquery.min.js"></script>
    <script>
        const ctx = "${pageContext.request.contextPath}";

        function consultar() {
            const dni = $("#input-dni").val().trim();

            if (!dni) {
                $("#resultado").html('<div class="resultado-error">⚠️ Ingresá un número de documento.</div>');
                return;
            }

            $("#btn-consultar").prop("disabled", true).text("⏳ Consultando...");
            $("#resultado").html("");

            $.ajax({
                url: ctx + "/Busqueda",
                type: "GET",
                data: { dni: dni },
                dataType: "json",

                success: function(data) {
                    if (data.ok) {
                        var clase  = data.habilitado ? "habilitado"            : "inhabilitado";
                        var icono  = data.habilitado ? "✅"                    : "🚫";
                        var estado = data.habilitado ? "Habilitado para votar" : "No habilitado para votar";

                        $("#resultado").html(
                            '<div class="resultado-box ' + clase + '">' +
                                '<div class="resultado-icono">' + icono + '</div>' +
                                '<div class="resultado-nombre">' + data.apellido + ', ' + data.nombre + '</div>' +
                                '<div class="resultado-dni">DNI: ' + data.dni + '</div>' +
                                '<div class="resultado-estado">' + estado + '</div>' +
                            '</div>'
                        );
                    } else {
                        $("#resultado").html('<div class="resultado-error">⚠️ ' + data.error + '</div>');
                    }
                },

                error: function(xhr) {
                    var msg = "Error al conectar con el servidor.";
                    if (xhr.responseJSON && xhr.responseJSON.error) {
                        msg = xhr.responseJSON.error;
                    }
                    $("#resultado").html('<div class="resultado-error">⚠️ ' + msg + '</div>');
                },

                complete: function() {
                    $("#btn-consultar").prop("disabled", false).text("🔍 Verificar habilitación");
                }
            });
        }

        $("#btn-consultar").on("click", consultar);

        $("#input-dni").on("keypress", function(e) {
            if (e.which === 13) consultar();
        });
    </script>

</body>
</html>
