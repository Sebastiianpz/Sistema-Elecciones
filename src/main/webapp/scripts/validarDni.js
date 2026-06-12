$(document).ready(function() {
	
		$('#btnValidar').on('click', function () {
		var nroDocumento = $('#inputDni').val();

		if(nroDocumento !== "") {
		            ejecutarValidacion(nroDocumento);
		        } else {
		            alert("Por favor, ingrese un DNI");
		        }
	    });
	    
	});
	
function ejecutarValidacion(dni) {
    $.ajax({
        url: contextPath + "/",
        method: "POST",
		data : {
			documento : dni
		},
        success: function(response) {
			
			if (response == null || response.id == 0) {
			                window.location.href = contextPath + "/no-existe.html";
			                return;
			            }
						
			sessionStorage.setItem("nombrePersona", response.nombre + " " + response.apellido);

			if (response.habilitadoVotar === false || response.habilitadoVotar === 0) {
			                window.location.href = contextPath + "/no-habilitado.html";
			                return;
			            }
						
			if (response.yaVoto === true || response.yaVoto === 1) {
						    window.location.href = contextPath + "/ya-voto.html";
						    return;
						}		
						
			sessionStorage.setItem("idPersonaVotando", response.id);
			                        
			            if (response.rol === "ADMIN") {
			                window.location.href = contextPath + "/habilitado-administrador.html"; 
			            } 
			            else {
			                window.location.href = contextPath + "/habilitado-ciudadano.html"; 
			            }
        },
        error: function(xhr) {
            console.log("Error al obtener dni:", xhr);
			Swal.fire("Error", "Hubo un error al consultar el padrón.", "error");
        }
    });
}

