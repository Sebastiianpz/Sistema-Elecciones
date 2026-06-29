$(document).ready(function() {
    
    // ==========================================
    // 1. RECUPERACIÓN Y CONTROL DE VISTAS (Pintar datos)
    // ==========================================
    var nombreGuardado = sessionStorage.getItem("nombrePersona");
    var dniGuardado    = sessionStorage.getItem("dniPersona");
    var rolGuardado    = sessionStorage.getItem("rolUsuario");

    // Inyectar datos si los elementos existen en la página actual
    if ($('#lblNombrePersona').length > 0 && nombreGuardado) {
        $('#lblNombrePersona').text(nombreGuardado);
    }
    
    if ($('#lblDniPersona').length > 0 && dniGuardado) {
        $('#lblDniPersona').text("DNI: " + dniGuardado);
    }

    // Lógica del Panel de Administrador (Solo si es ADMIN y el botón existe)
    if (rolGuardado === "ADMIN" && $('#btnPanelAdmin').length > 0) {
        $('#btnPanelAdmin').show(); 
    }

    // ==========================================
    // 2. ASIGNACIÓN DE EVENTOS (Unificados sin duplicados)
    // ==========================================
    
    // Botón VOLVER (Global)
    $('#btnVolverInicio').on('click', function(e) {
        e.preventDefault();
        sessionStorage.clear(); // Limpieza para la próxima consulta
        window.location.href = contextPath + "/home/home.jsp";
    });

    // Botón IR A VOTAR (Pantallas de habilitados)
    $('#btnIrAVotar').on('click', function(e) {
        e.preventDefault();
        
        // Verificamos de forma segura basándonos en la URL actual o en el rol guardado
        if (window.location.pathname.includes("habilitado-administrador") || rolGuardado === "ADMIN") {
            sessionStorage.setItem("rolUsuario", "ADMIN");
            window.location.href = contextPath + "/votacion/votacion.jsp?tipo=admin";
        } else {
            sessionStorage.setItem("rolUsuario", "CIUDADANO");
            window.location.href = contextPath + "/votacion/votacion.jsp?tipo=ciudadano";
        }
    });

    // Botón IR AL PANEL ADMINISTRATIVO
    $('#btnPanelAdmin').on('click', function(e) {
        e.preventDefault();
        window.location.href = contextPath + '/login-admin/login-admin.jsp'; 
    });

    // Botón VALIDAR DNI (Pantalla de Inicio / Home)
    $('#btnValidar').on('click', function (e) {
        e.preventDefault();
        
        var nroDocumento = $('#inputDni').val();
        if (nroDocumento && nroDocumento.trim() !== "") {
            ejecutarValidacion(nroDocumento.trim());
        } else {
            Swal.fire("Atención", "Por favor, ingrese un DNI", "warning");
        }
    });
});

// ==========================================
// 3. LÓGICA DE VALIDACIÓN AJAX
// ==========================================
function ejecutarValidacion(dni) {
    $.ajax({
        url: contextPath + "/validarPersonaPorDNI",
        method: "POST",
        dataType: 'json',
        data : {
            documento : dni
        },
        success: function(response) {
            if (response == null || response.id == 0) {
                sessionStorage.setItem("dniPersona", dni);
                window.location.href = contextPath + "/no-existe/no-existe.jsp";               
                return;
            }
                         
            // Guardamos los datos base devueltos por el servidor
            sessionStorage.setItem("nombrePersona", response.nombreCompleto);
            sessionStorage.setItem("dniPersona", dni);
            
            // Validación: No habilitado
            if (response.habilitadoVotar === false || response.habilitadoVotar === 0) {
                window.location.href = contextPath + "/no-habilitado/no-habilitado.jsp";
                return;
            }
            
            // Validación: Ya votó
            if (response.yaVoto === true || response.yaVoto === 1) {
                sessionStorage.setItem("rolUsuario", response.rol); 
                window.location.href = contextPath + "/ya-voto/ya-voto.jsp";
                return;
            }       
                         
            // Guardamos ID de sesión de votación y el Rol definitivo
            sessionStorage.setItem("idPersonaVotando", response.id);
            sessionStorage.setItem("rolUsuario", response.rol);
                                    
            // Redirección condicional por Rol
            if (response.rol === "ADMIN") {
                window.location.href = contextPath + "/habilitado-administrador/habilitado-administrador.jsp";
            } else {
                window.location.href = contextPath + "/habilitado-ciudadano/habilitado-ciudadano.jsp";
            }
        },
        error: function(xhr, status, error) {
            console.error("Error al obtener dni:", error);
            Swal.fire("Error", "Hubo un error al consultar el padrón en la base de datos.", "error");
        }
    });
}