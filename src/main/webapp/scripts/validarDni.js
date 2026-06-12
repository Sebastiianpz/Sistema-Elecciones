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
			                Swal.fire("Error", "El DNI ingresado no figura en el padrón electoral.", "error");
			                return;
			            }
			if (response.yaVoto === true || response.yaVoto === 1) {
						    Swal.fire("Acceso Denegado", "Usted ya ha realizado su voto.", "warning");
						    return;
						}		
			sessionStorage.setItem("idPersonaVotando", response.id);
			
			
						            
		    window.location.href = contextPath + "/votar.jsp";				
			
        },
        error: function(xhr) {
            console.log("Error al obtener mesas:", xhr);
			Swal.fire("Error", "Hubo un error al consultar el padrón.", "error");
        }
    });
}

