$(function() {

    // ==========================================
    // 1. CARGAR DATOS DEL VOTANTE DESDE SESSIONSTORAGE
    // ==========================================
    var nombreVotante = sessionStorage.getItem("nombrePersona");
    var dniVotante = sessionStorage.getItem("dniPersona");

    if (nombreVotante) {
        $("#lblNombreVotante").text(nombreVotante);
    }
    if (dniVotante) {
        $("#lblDniVotante").text("DNI " + dniVotante);
    }

    // ==========================================
    // 2. CARGAR LISTA DE CANDIDATOS (Campos corregidos)
    // ==========================================
    cargarCandidatos();
	
    // ==========================================
    // 3. EVENTO AL HACER CLIC EN "VOTAR"
    // ==========================================
    $(document).on("click", ".btn-seleccionar-candidato", function (e) {
        e.preventDefault();
		  
        var idCandidato = $(this).data("id");
        var nombreCandidato = $(this).data("nombre");
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
            text: "Está a punto de votar por: " + nombreCandidato,
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
                        candidatoId: idCandidato,   
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
            contenedor.empty();

            $.each(listaCandidatos, function(index, candidato) {
                // Mapeo directo con los nombres de columna de tu base de datos (HeidiSQL)
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
            console.log("Error crítico al cargar los candidatos en la pantalla:", xhr);
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