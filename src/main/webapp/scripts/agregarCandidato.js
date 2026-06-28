$(function () {

    // Array de candidatos dentro del entorno de tu función principal
    var listaCandidatosBD = [];

    // Hacemos que esta función sea visible para el onclick="prepararModalAgregar()" del JSP
    window.prepararModalAgregar = function() {
        $("#formCandidato")[0].reset();
        $("#candidatoId").val(""); 
        $("#modalCandidatoLabel").text("Agregar Nuevo Candidato");
        
        var btnGuardar = $("#btnGuardarCandidato");
        btnGuardar.text("AGREGAR CANDIDATO");
        btnGuardar.removeClass("btn-primary text-white").addClass("text-muted");
        btnGuardar.css("background-color", "#e0e0e0");
    };

    // Hacemos visible la función para abrir el modal en modo edición
    window.prepararModalEditar = function(id) {
        // Buscamos el candidato por ID de forma segura
        var candidato = listaCandidatosBD.find(function(c) { return (c.id == id); });
        
        if (candidato) {
            $("#modalCandidatoLabel").text("Modificar Candidato");
            
            var btnGuardar = $("#btnGuardarCandidato");
            btnGuardar.text("MODIFICAR CANDIDATO");
            btnGuardar.removeClass("text-muted").addClass("btn-primary text-white");
            btnGuardar.css("background-color", ""); 

            // Llenamos el formulario con los datos de la lista
            $("#candidatoId").val(candidato.id);
            $("#nombreCompleto").val(candidato.nombreCompleto || candidato.nombre_completo);
            $("#partido").val(candidato.partido);
            $("#colorPartido").val(candidato.colorPartido || candidato.color_partido);
            
            // Abrimos el modal usando la API de Bootstrap 5
            var modalElement = document.getElementById("modalCandidato");
            var modalInstance = bootstrap.Modal.getOrCreateInstance(modalElement);
            modalInstance.show();
        }
    };

    // Función interna para eliminar un candidato (puedes enlazarla a tu AJAX de borrado después)
    window.eliminarCandidato = function(id) {
        Swal.fire({
            title: "¿Estás seguro?",
            text: "Se eliminará este candidato de la boleta electoral.",
            icon: "warning",
            showCancelButton: true,
            confirmButtonColor: "#dc2626",
            cancelButtonColor: "#6c757d",
            confirmButtonText: "Sí, eliminar",
            cancelButtonText: "Cancelar"
        }).then((result) => {
            if (result.isConfirmed) {
                // Aquí agregarás tu llamada AJAX para eliminar en base de datos.
                // Por ahora simulamos la eliminación local:
                listaCandidatosBD = listaCandidatosBD.filter(function(c) { return c.id != id; });
                renderizarListaCandidatos();
                Swal.fire("Eliminado", "El candidato ha sido removido.", "success");
            }
        });
    };

    // FUNCIÓN: Pintar los candidatos imitando la estética exacta de la imagen
    function renderizarListaCandidatos() {
        var contenedor = $("#contenedor-candidatos");
        contenedor.empty(); 

        if (listaCandidatosBD.length === 0) {
            contenedor.append('<div class="text-center py-4 text-muted">No hay candidatos registrados en la boleta electoral.</div>');
            return;
        }

        listaCandidatosBD.forEach(function (cand, index) {
            var nombre = cand.nombreCompleto || cand.nombre_completo;
            var color  = cand.colorPartido || cand.color_partido || "#2563eb";
            var votos  = cand.votos !== undefined ? cand.votos : 0;
            var inicial = nombre ? nombre.charAt(0).toUpperCase() : "?";
            
            var esUltimo = index === listaCandidatosBD.length - 1 ? "" : "border-bottom pb-4";

            contenedor.append(`
                <div class="row align-items-center ${esUltimo} g-3">
                    <div class="col-12 col-md-9 d-flex align-items-center gap-4">
                        <div class="rounded-circle d-flex align-items-center justify-content-center text-white fw-bold shadow-sm" 
                             style="background-color: ${color}; width: 48px; height: 48px; min-width: 48px; font-size: 1.2rem;">
                            ${inicial}
                        </div>
                        <div>
                            <h5 class="mb-0 fw-bold text-dark" style="font-size: 1.1rem;">#${index + 1} ${nombre}</h5>
                            <div class="text-muted mt-1" style="font-size: 0.85rem;">
                                <span class="me-3">Partido: <strong class="text-dark">${cand.partido}</strong></span>
                                <span class="me-3">Color: <code class="px-2 py-0.5 rounded text-dark" style="background-color: #f1f5f9;">${color}</code></span>
                                <span>Votos: <strong class="text-dark">${votos}</strong></span>
                            </div>
                        </div>
                    </div>
                    <div class="col-12 col-md-3 d-flex justify-content-md-end justify-content-start align-items-center">
                        <div class="d-flex gap-2">
                            <button class="btn btn-light btn-sm text-primary rounded-2 px-3 fw-medium border" onclick="prepararModalEditar(${cand.id})" title="Editar Candidato">
                                <i class="fas fa-edit me-1"></i> Editar
                            </button>
                            <button class="btn btn-light btn-sm text-danger rounded-2 px-3 fw-medium border" onclick="eliminarCandidato(${cand.id})" title="Eliminar Candidato">
                                <i class="fas fa-trash-alt me-1"></i> Eliminar
                            </button>
                        </div>
                    </div>
                </div>
            `);
        });
    }

    // Función interna para ir a buscar los 3 candidatos reales a la Base de Datos
    function cargarCandidatosExistentes() {
        $.ajax({
            url: contextPath + "/listarCandidatos", 
            type: "GET",
            dataType: "JSON",
            success: function (data) {
                listaCandidatosBD = data; 
                renderizarListaCandidatos(); 
            },
            error: function (xhr) {
                console.error("Error al cargar candidatos de la BD: ", xhr.responseText);
            }
        });
    }

    // AL CARGAR LA PÁGINA: Ejecutamos la búsqueda automática
    cargarCandidatosExistentes();

    // EVENTO CLICK PARA GUARDAR / ACTUALIZAR
    $("#btnGuardarCandidato").on("click", function (e) {
        e.preventDefault();
        e.stopPropagation();

        var id             = $("#candidatoId").val();
        var nombreCompleto = $("#nombreCompleto").val().trim();
        var partido        = $("#partido").val().trim();
        var colorPartido   = $("#colorPartido").val();

        if (!nombreCompleto || !partido) {
            Swal.fire("Atención", "Por favor, completa todos los campos.", "warning");
            return;
        }

        $.LoadingOverlay("show", { text: "Guardando..." });

        // Determinamos dinámicamente si es alta o modificación según la existencia del ID
        var urlDestino = id === "" ? contextPath + "/guardarCandidato" : contextPath + "/modificarCandidato";

        $.ajax({
            url: urlDestino,
            type: "POST",
            dataType: "JSON",
            data: {
                id:             id,
                nombreCompleto: nombreCompleto,
                partido:        partido,
                numPartido:     1, 
                colorPartido:   colorPartido
            },
            cache: false,
            success: function (data) {
                $.LoadingOverlay("hide");

                if (data.ok === true) {
                    var modalElement = document.getElementById("modalCandidato");
                    var modalInstance = bootstrap.Modal.getOrCreateInstance(modalElement);
                    modalInstance.hide();

                    // Volvemos a sincronizar los datos reales de MariaDB
                    cargarCandidatosExistentes();

                    Swal.fire({
                        icon: "success",
                        title: id === "" ? "Candidato agregado!" : "Candidato modificado!",
                        text: data.mensaje,
                        confirmButtonColor: "#2563eb",
                        timer: 2500,
                        showConfirmButton: false
                    });

                } else {
                    Swal.fire("Error", data.error || "No se pudo procesar la solicitud.", "error");
                }
            },
            error: function (xhr) {
                $.LoadingOverlay("hide");
                try {
                    var res = JSON.parse(xhr.responseText);
                    Swal.fire("Error", res.error, "error");
                } catch (e) {
                    Swal.fire("Error", "Código: " + xhr.status, "error");
                }
            }
        });
    });

});