$(document).ready(function() {
    
    // ==========================================
    // 1. CONTROL DE VISTAS (Pintar datos en los JSP)
    // ==========================================
    
    // Intentamos recuperar los datos del ciudadano del sessionStorage
    var nombreGuardado = sessionStorage.getItem("nombrePersona");
    var dniGuardado = sessionStorage.getItem("dniPersona");

    // Si existen elementos con estos ID en la página actual, les metemos la info
    if ($('#lblNombrePersona').length > 0 && nombreGuardado) {
        $('#lblNombrePersona').text(nombreGuardado);
    }
    
    if ($('#lblDniPersona').length > 0 && dniGuardado) {
        $('#lblDniPersona').text("DNI: " + dniGuardado);
    }

    // Configuración del botón VOLVER de forma global
    $('#btnVolverInicio').on('click', function(e) {
        e.preventDefault();
        sessionStorage.clear(); // Limpiamos para la siguiente consulta
        window.location.href = contextPath + "/home/home.jsp";
    });

    // Configuración del botón IR A VOTAR (para la pantalla de habilitado)
	// Configuración del botón IR A VOTAR (para la pantalla de habilitado)
	$('#btnIrAVotar').on('click', function(e) {
	    e.preventDefault();
	    
	    // Verificamos si estás en la página de administrador mirando la ruta actual
	    if (window.location.pathname.includes("habilitado-administrador")) {
	        // Guardamos el rol como respaldo en la sesión del navegador
	        sessionStorage.setItem("rolUsuario", "ADMIN");
	        window.location.href = contextPath + "/votacion/votacion.jsp?tipo=admin";
	    } else {
	        sessionStorage.setItem("rolUsuario", "CIUDADANO");
	        window.location.href = contextPath + "/votacion/votacion.jsp?tipo=ciudadano";
	    }
	});


    // ==========================================
    // 2. LOGICA DE VALIDACIÓN (Tu AJAX original)
    // ==========================================
    
    $('#btnValidar').on('click', function (e) {
        e.preventDefault(); 
        
        var nroDocumento = $('#inputDni').val();

        if (nroDocumento !== "") {
            ejecutarValidacion(nroDocumento);
        } else {
            Swal.fire("Atención", "Por favor, ingrese un DNI", "warning");
        }
    });
});
	
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
                window.location.href = contextPath + "/no-existe.jsp";                
                return;
            }
						
            // 🌟 GUARDAMOS EL NOMBRE: Usamos la propiedad exacta del mapa que envía el Controller
            sessionStorage.setItem("nombrePersona", response.nombreCompleto);
            sessionStorage.setItem("dniPersona", dni);

            if (response.habilitadoVotar === false || response.habilitadoVotar === 0) {
                window.location.href = contextPath + "/no-habilitado/no-habilitado.jsp";
                return;
            }
						
            if (response.yaVoto === true || response.yaVoto === 1) {
                window.location.href = contextPath + "/ya-voto/ya-voto.jsp";
                return;
            }		
						
            sessionStorage.setItem("idPersonaVotando", response.id);
			                        
            if (response.rol === "ADMIN") {
                window.location.href = contextPath + "/habilitado-administrador/habilitado-administrador.jsp";
            } else {
                window.location.href = contextPath + "/habilitado-ciudadano/habilitado-ciudadano.jsp";
            }
        },
        error: function(xhr, status, error) {
            console.log("Error al obtener dni:", error);
            Swal.fire("Error", "Hubo un error al consultar el padrón en la base de datos.", "error");
        }
    });
}