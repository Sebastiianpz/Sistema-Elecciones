$(function() {

  cargarCandidatos();
	
  $(document).on("click", ".btn-seleccionar-candidato", function (e) {
          e.preventDefault();
		  
            var idCandidato =$(this).data("id");
			var nombreCandidato = $(this).data("nombre");
			var idPersonaVotando = sessionStorage.getItem("idPersonaVotando");
            
            const swalWithBootstrapButtons = Swal.mixin({
                customClass: {
                  confirmButton: "btn btn-success",
                  cancelButton: "btn btn-danger"
                },
                buttonsStyling: false
              });


              swalWithBootstrapButtons.fire({
                title: "¿Confirma su voto?",
                text: "Esta apunto de votar por: " + nombreCandidato,,
                icon: "warning",
                showCancelButton: true,
                confirmButtonText: "Si, confirmar voto",
                cancelButtonText: "No, cancelar!",
                reverseButtons: true
              }).then((result) => {
				if (result.isConfirmed) {
}				   $.ajax({
				        url: contextPath + '/registrarVoto', 
				        method: 'POST',
				        data: {
				            idPersona: idPersonaVotando,  
				            idCandidato: idCandidato       
				        },
				        success: function (response) {
}}				            sessionStorage.clear();
				            
}				            window.location.href = contextPath + "/confirmacion.html"; 
				        },
				        error: function(xhr) {
				            console.log("Error al registrar el voto:", xhr);
				            Swal.fire("Error", "No se pudo registrar el voto en el servidor.", "error");
				        }
					}); 
					                
					            }
					            
					        });
					    }); 
					}); 