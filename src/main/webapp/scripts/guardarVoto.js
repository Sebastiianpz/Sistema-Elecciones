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
                text: "Esta apunto de votar por: " + nombreCandidato,
                icon: "warning",
                showCancelButton: true,
                confirmButtonText: "Si, confirmar voto",
                cancelButtonText: "No, cancelar!",
                reverseButtons: true
              }).then((result) => {
	
				if (result.isConfirmed) {
					
				   $.ajax({
				        url: contextPath + '/registrarVoto', 
				        method: 'POST',
				        data: {
				            idPersona: idPersonaVotando,  
				            idCandidato: idCandidato       
				        },
				        success: function (response) {
				            sessionStorage.clear();
				            
				            window.location.href = contextPath + "/confirmacion.html"; 
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
					
function cargarCandidatos() {
    $.ajax({
        url: contextPath + "/listarCandidatos",
        method: "GET",
        dataType: "json",
        success: function(listaCandidatos) {
            
            var contenedor = $("#contenedor-candidatos"); 
            contenedor.empty();

            $.each(listaCandidatos, function(index, candidato) {
                
                var tarjetaHTML = `
                    <div class="col-md-4 mb-4">
                        <div class="card text-center p-3">
                            <img src="${contextPath}/assets/img/avatar.png" class="card-img-top mx-auto" style="width: 100px;">
                            <div class="card-body">
                                <h5 class="card-title">${candidato.nombre} ${candidato.apellido}</h5>
                                <p class="card-text text-muted">${candidato.partidoPolitico}</p>
                                
                                <button class="btn btn-primary btn-seleccionar-candidato" 
                                        data-id="${candidato.id}" 
                                        data-nombre="${candidato.nombre} ${candidato.apellido}">
                                    VOTAR
                                </button>
                            </div>
                        </div>
                    </div>
                `;
                
                contenedor.append(tarjetaHTML);
            });
        },
        error: function(xhr) {
            console.log("Error crítico al cargar los candidatos en la pantalla:", xhr);
        }
    });
}