$(document).ready(function() {
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
						
            sessionStorage.setItem("nombrePersona", response.nombre + " " + response.apellido);
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