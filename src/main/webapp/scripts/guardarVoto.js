$(function() {

    var nombreVotante = sessionStorage.getItem("nombrePersona");
    var dniVotante = sessionStorage.getItem("dniPersona");

    if (nombreVotante) {
        $("#lblNombreVotante").text(nombreVotante);
    }
    if (dniVotante) {
        $("#lblDniVotante").text("DNI " + dniVotante);
    }

    var idCandidatoSeleccionado = null;
    var nombreCandidatoSeleccionado = null;

    cargarCandidatos();
	
    $(document).on("click", ".btn-seleccionar-candidato", function (e) {
        e.preventDefault();
		  
        idCandidatoSeleccionado = $(this).data("id");
        nombreCandidatoSeleccionado = $(this).data("nombre");

        $(".btn-seleccionar-candidato").removeClass("btn-success").addClass("btn-primary").html("<i class='fas fa-vote-yea me-2'></i>VOTAR");
        $(this).removeClass("btn-primary").addClass("btn-success").html("<i class='fas fa-check me-2'></i>SELECCIONADO");
        
        console.log("Candidato seleccionado temporalmente:", nombreCandidatoSeleccionado, "(ID:", idCandidatoSeleccionado + ")");
    }); 

    $(document).on("click", "#btnConfirmar", function (e) {
        e.preventDefault();

        if (!idCandidatoSeleccionado) {
            Swal.fire("Atención", "Por favor, seleccione un candidato antes de confirmar su voto.", "warning");
            return;
        }

        var idPersonaVotando = sessionStorage.getItem("idPersonaVotando");
        var idPC = sessionStorage.getItem("idPcMesa");
            
        const swalWithBootstrapButtons = Swal.mixin({
            customClass: {
                confirmButton: "btn btn-success",
                cancelButton: "btn btn-danger"
            },
            buttonsStyling: false
        });

        swalWithBootstrapButtons.fire({
            title: "¿Confirma su voto?",
            text: "Está a punto de votar por: " + nombreCandidatoSeleccionado,
            icon: "warning",
            showCancelButton: true,
            confirmButtonText: "Sí, confirmar voto",
            cancelButtonText: "No, cancelar!",
            reverseButtons: true
        }).then((result) => {
            if (result.isConfirmed) {
                $.ajax({
                    url: contextPath + '/guardarVoto', 
                    method: 'POST',
                    data: {
                        personaId: idPersonaVotando,  
                        candidatoId: idCandidatoSeleccionado,   
                        pcId: idPC
                    },
                    success: function (response) {
                        sessionStorage.clear();
                        window.location.href = contextPath + "/confirmacion/confirmacion.jsp"; 
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
            
            if(contenedor.length === 0) return;

            contenedor.empty();

            $.each(listaCandidatos, function(index, candidato) {
                var nombreMostrar = candidato.nombre_completo; 
                var partidoMostrar = candidato.partido;

                var tarjetaHTML = `
                    <div class="col-md-4 mb-4">
                        <div class="card text-center p-3">
                            <img src="${contextPath}/assets/img/avatar.png" class="card-img-top mx-auto" style="width: 100px;">
                            <div class="card-body">
                                <h5 class="card-title">${nombreMostrar}</h5>
                                <p class="card-text text-muted">${partidoMostrar}</p>
                                
                                <button class="btn btn-primary btn-seleccionar-candidato" 
                                        data-id="${candidato.id}" 
                                        data-nombre="${nombreMostrar}">
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
            console.log("Error al cargar los candidatos remotos.");
        }
    });
} 


function volverPagina() {
    const params = new URLSearchParams(window.location.search);
    const tipo = params.get('tipo');
  
    if (tipo === 'admin') {
        window.location.href = contextPath + '/habilitado-administrador/habilitado-administrador.jsp';
    } else {
        window.location.href = contextPath + '/habilitado-ciudadano/habilitado-ciudadano.jsp';
    }
}