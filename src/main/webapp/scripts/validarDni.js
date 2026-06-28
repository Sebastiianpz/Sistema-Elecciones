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
   $('#btnIrAVotar').on('click', function(e) {
       e.preventDefault();
      
       if (window.location.pathname.includes("habilitado-administrador")) {
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
               // 🌟 Guardamos el DNI antes de irnos para que aparezca en pantalla
               sessionStorage.setItem("dniPersona", dni);
              
               // 🚀 Corregimos para que no te dé 404 (usa la carpeta correcta)
               window.location.href = contextPath + "/no-existe/no-existe.jsp";               
               return;
           }
						
           // 🌟 GUARDAMOS EN EL SESSIONSTORAGE ANTES DE REDIRIGIR
           sessionStorage.setItem("nombrePersona", response.nombreCompleto);
           sessionStorage.setItem("dniPersona", dni);
           if (response.habilitadoVotar === false || response.habilitadoVotar === 0) {
               window.location.href = contextPath + "/no-habilitado/no-habilitado.jsp";
               return;
           }
			if (response.yaVoto === true || response.yaVoto === 1) {
    sessionStorage.setItem("nombrePersona", response.nombreCompleto);
    sessionStorage.setItem("dniPersona", response.nroDocumento);
    sessionStorage.setItem("rolUsuario", response.rol); // ← guardá el rol ACÁ
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
$(document).ready(function() {
    
    // Recuperamos los datos guardados
    var nombreGuardado = sessionStorage.getItem("nombrePersona");
    var dniGuardado = sessionStorage.getItem("dniPersona");
    var rolGuardado = sessionStorage.getItem("rolUsuario"); // Recuperamos el rol

    // Pintar los textos en las etiquetas correspondientes
    if ($('#lblNombrePersona').length > 0 && nombreGuardado) {
        $('#lblNombrePersona').text(nombreGuardado);
    }
    
    if ($('#lblDniPersona').length > 0 && dniGuardado) {
        $('#lblDniPersona').text("DNI: " + dniGuardado);
    }

    // 🌟 LOGICA NUEVA: Si el usuario es ADMIN, mostramos el botón del panel
    if (rolGuardado === "ADMIN" && $('#btnPanelAdmin').length > 0) {
        $('#btnPanelAdmin').show(); // Esto le quita el 'display: none' y lo hace visible
    }

    // Configuración del botón IR AL PANEL ADMINISTRATIVO
    $('#btnPanelAdmin').on('click', function(e) {
        e.preventDefault();
        // Rediriges a la ruta de tu dashboard administrativo
        window.location.href = contextPath + '/login-admin/login-admin.jsp'; 
    });

    // Configuración del botón VOLVER original
    $('#btnVolverInicio').on('click', function(e) {
        e.preventDefault();
        sessionStorage.clear(); // Limpiamos los datos para la siguiente consulta
        window.location.href = contextPath + "/home/home.jsp";
    });
});
