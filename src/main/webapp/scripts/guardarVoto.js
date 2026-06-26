$(function() {
<<<<<<< Updated upstream

    // ==========================================
    // 1. CARGAR DATOS DEL VOTANTE DESDE SESSIONSTORAGE (Lo que hizo ella)
    // ==========================================
    var nombreVotante = sessionStorage.getItem("nombrePersona");
    var dniVotante = sessionStorage.getItem("dniPersona");

    if (nombreVotante) {
        $("#lblNombreVotante").text(nombreVotante);
    }
    if (dniVotante) {
        $("#lblDniVotante").text("DNI " + dniVotante);
    }

    // Variables globales para recordar la selección (Tu flujo)
    var idCandidatoSeleccionado = null;
    var nombreCandidatoSeleccionado = null;

    cargarCandidatos();
	
    // ==========================================
    // 2. EVENTO AL HACER CLIC EN "VOTAR" EN UNA TARJETA
    // ==========================================
    $(document).on("click", ".btn-seleccionar-candidato", function (e) {
        e.preventDefault();
		  
        // Guardamos los datos temporalmente sin abrir carteles (Tu flujo)
        idCandidatoSeleccionado = $(this).data("id");
        nombreCandidatoSeleccionado = $(this).data("nombre");

        // Cambios visuales de selección
        $(".btn-seleccionar-candidato").removeClass("btn-success").addClass("btn-primary").html("<i class='fas fa-vote-yea me-2'></i>VOTAR");
        $(this).removeClass("btn-primary").addClass("btn-success").html("<i class='fas fa-check me-2'></i>SELECCIONADO");
        
        console.log("Candidato seleccionado temporalmente:", nombreCandidatoSeleccionado, "(ID:", idCandidatoSeleccionado + ")");
    }); 

    // ==========================================
    // 3. EVENTO AL HACER CLIC EN "CONFIRMAR VOTO" (Botón verde abajo)
    // ==========================================
    $(document).on("click", "#btnConfirmar", function (e) {
        e.preventDefault();

        // Si no seleccionó a nadie, frenamos
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

        // Abrimos el cartel con el nombre que guardamos en memoria (Tu flujo)
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
                // Si confirma, se hace el envío AJAX al servidor
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
=======
   // ==========================================
   // 1. CARGAR DATOS DEL VOTANTE DESDE SESSIONSTORAGE (Lo que hizo ella)
   // ==========================================
   var nombreVotante = sessionStorage.getItem("nombrePersona");
   var dniVotante = sessionStorage.getItem("dniPersona");
   if (nombreVotante) {
       $("#lblNombreVotante").text(nombreVotante);
   }
   if (dniVotante) {
       $("#lblDniVotante").text("DNI " + dniVotante);
   }
   // Variables globales para recordar la selección (Tu flujo)
   var idCandidatoSeleccionado = null;
   var nombreCandidatoSeleccionado = null;
   cargarCandidatos();
	
   // ==========================================
   // 2. EVENTO AL HACER CLIC EN "VOTAR" EN UNA TARJETA
   // ==========================================
   $(document).on("click", ".btn-seleccionar-candidato", function (e) {
       e.preventDefault();
		 
       // Guardamos los datos temporalmente sin abrir carteles (Tu flujo)
       idCandidatoSeleccionado = $(this).data("id");
       nombreCandidatoSeleccionado = $(this).data("nombre");
       // Cambios visuales de selección
       $(".btn-seleccionar-candidato").removeClass("btn-success").addClass("btn-primary").html("<i class='fas fa-vote-yea me-2'></i>VOTAR");
       $(this).removeClass("btn-primary").addClass("btn-success").html("<i class='fas fa-check me-2'></i>SELECCIONADO");
      
       console.log("Candidato seleccionado temporalmente:", nombreCandidatoSeleccionado, "(ID:", idCandidatoSeleccionado + ")");
   });
   // ==========================================
   // 3. EVENTO AL HACER CLIC EN "CONFIRMAR VOTO" (Botón verde abajo)
   // ==========================================
   $(document).on("click", "#btnConfirmar", function (e) {
       e.preventDefault();
       // Si no seleccionó a nadie, frenamos
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
       // Abrimos el cartel con el nombre que guardamos en memoria (Tu flujo)
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
               // Si confirma, se hace el envío AJAX al servidor
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
>>>>>>> Stashed changes
					
// ==========================================
// 4. CARGA DINÁMICA DE CANDIDATOS (Estructura de ella con tus mapeos)
// ==========================================
function cargarCandidatos() {
<<<<<<< Updated upstream
    $.ajax({
        url: contextPath + "/listarCandidatos",
        method: "GET",
        dataType: "json",
        success: function(listaCandidatos) {
            var contenedor = $("#contenedor-candidatos"); 
            
            if(contenedor.length === 0) return;

            contenedor.empty();

            $.each(listaCandidatos, function(index, candidato) {
                // Mapeo directo con los nombres de columna de tu base de datos / HeidiSQL (De ella)
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
=======
   $.ajax({
       url: contextPath + "/listarCandidatos",
       method: "GET",
       dataType: "json",
       success: function(listaCandidatos) {
           var contenedor = $("#contenedor-candidatos");
          
           if(contenedor.length === 0) return;
           contenedor.empty();
           $.each(listaCandidatos, function(index, candidato) {
               // Mapeo directo con los nombres de columna de tu base de datos / HeidiSQL (De ella)
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
>>>>>>> Stashed changes
}

