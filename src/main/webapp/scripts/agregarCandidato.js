$(function () {
 
    var listaCandidatosBD = [];
 
    // ---- MODAL AGREGAR ----
    window.prepararModalAgregar = function() {
        $("#formCandidato")[0].reset();
        $("#candidatoId").val("");
        $("#modalCandidatoLabel").text("Agregar Nuevo Candidato");
        var btnGuardar = $("#btnGuardarCandidato");
        btnGuardar.text("AGREGAR CANDIDATO");
        btnGuardar.removeClass("btn-primary text-white").addClass("text-muted");
        btnGuardar.removeClass("text-muted").addClass("btn-primary text-white");
		};
 
    // ---- MODAL EDITAR ----
    window.prepararModalEditar = function(id) {
        var candidato = listaCandidatosBD.find(function(c) { return (c.id == id); });
        if (candidato) {
            $("#modalCandidatoLabel").text("Actualizar Candidato");
            var btnGuardar = $("#btnGuardarCandidato");
            btnGuardar.text("ACTUALIZAR CANDIDATO");
            btnGuardar.removeClass("text-muted").addClass("btn-primary text-white");
            btnGuardar.css("background-color", "");
            $("#candidatoId").val(candidato.id);
            $("#nombreCompleto").val(candidato.nombreCompleto || candidato.nombre_completo);
            $("#partido").val(candidato.partido);
            $("#colorPartido").val(candidato.colorPartido || candidato.color_partido);
            var modalInstance = bootstrap.Modal.getOrCreateInstance(document.getElementById("modalCandidato"));
            modalInstance.show();
        }
    };
 
    // ---- ELIMINAR ----
    window.eliminarCandidato = function(id) {
        Swal.fire({
            title: "¿Estas seguro?",
            text: "Se eliminara este candidato de la boleta electoral.",
            icon: "warning",
            showCancelButton: true,
            confirmButtonColor: "#dc2626",
            cancelButtonColor: "#6c757d",
            confirmButtonText: "Si, eliminar",
            cancelButtonText: "Cancelar"
        }).then(function(result) {
            if (result.isConfirmed) {
                $.ajax({
                    url: contextPath + "/eliminarCandidato",
                    type: "POST",
                    dataType: "JSON",
                    data: { id: id },
                    cache: false,
                    success: function(data) {
                        if (data.ok === true) {
                            cargarCandidatosExistentes();
                            Swal.fire("Eliminado", "El candidato ha sido removido.", "success");
                        } else {
                            Swal.fire("Error", data.error || "No se pudo eliminar.", "error");
                        }
                    },
                    error: function(xhr) {
                        Swal.fire("Error", "Codigo: " + xhr.status, "error");
                    }
                });
            }
        });
    };
 
    // ---- RENDERIZAR LISTA ----
    // El HTML de cada fila vive en <template id="template-candidato"> dentro del JSP.
    // Acá solo clonamos esa plantilla y la llenamos con los datos (eso es lo que
    // necesita JS: traer datos por AJAX y aplicarlos al markup, no construir HTML).
    function renderizarListaCandidatos() {
        var contenedor = $("#contenedor-candidatos");
        contenedor.empty();
 
        if (listaCandidatosBD.length === 0) {
            contenedor.append($("#template-sin-candidatos").html());
            return;
        }
 
        listaCandidatosBD.forEach(function(cand, index) {
            var nombre  = cand.nombreCompleto || cand.nombre_completo;
            var color   = cand.colorPartido   || cand.color_partido || "#2563eb";
            var votos   = cand.votos !== undefined ? cand.votos : 0;
            var inicial = nombre ? nombre.charAt(0).toUpperCase() : "?";
 
            var $fila = $($("#template-candidato").html().trim());
 
            $fila.find(".avatar-candidato")
                 .css("background-color", color)
                 .text(inicial);
            $fila.find(".nombre-candidato").text("#" + (index + 1) + " " + nombre);
            $fila.find(".partido-candidato").text(cand.partido);
            $fila.find(".color-candidato").text(color);
            $fila.find(".votos-candidato").text(votos);
 
            if (index < listaCandidatosBD.length - 1) {
                $fila.addClass("border-bottom pb-4");
            }
 
            $fila.find(".btn-editar-candidato").on("click", function() {
                prepararModalEditar(cand.id);
            });
            $fila.find(".btn-eliminar-candidato").on("click", function() {
                eliminarCandidato(cand.id);
            });
 
            contenedor.append($fila);
        });
    }
 
    // ---- CARGAR DESDE BD ----
    function cargarCandidatosExistentes() {
        $.ajax({
            url: contextPath + "/listarCandidatos",
            type: "GET",
            dataType: "JSON",
            success: function(data) {
                listaCandidatosBD = data;
                renderizarListaCandidatos();
            },
            error: function(xhr) {
                console.error("Error al cargar candidatos:", xhr.responseText);
            }
        });
    }
 
    // ---- AL CARGAR LA PAGINA ----
    cargarCandidatosExistentes();
 
    // ---- GUARDAR / MODIFICAR ----
    $("#btnGuardarCandidato").on("click", function(e) {
        e.preventDefault();
        e.stopPropagation();
 
        var id             = $("#candidatoId").val();
        var nombreCompleto = $("#nombreCompleto").val().trim();
        var partido        = $("#partido").val().trim();
        var colorPartido   = $("#colorPartido").val();
 
        if (!nombreCompleto || !partido) {
            Swal.fire("Atencion", "Por favor, completa todos los campos.", "warning");
            return;
        }
 
        $.LoadingOverlay("show", { text: "Guardando..." });
 
        var urlDestino = id === "" ? contextPath + "/guardarCandidato" : contextPath + "/actualizarCandidato";
 
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
            success: function(data) {
                $.LoadingOverlay("hide");
                if (data.ok === true) {
                    bootstrap.Modal.getOrCreateInstance(document.getElementById("modalCandidato")).hide();
                    cargarCandidatosExistentes();
                    Swal.fire({
                        icon: "success",
                        title: id === "" ? "Candidato agregado!" : "Candidato actualizado!",
                        text: data.mensaje,
                        confirmButtonColor: "#2563eb",
                        timer: 2500,
                        showConfirmButton: false
                    });
                } else {
                    Swal.fire("Error", data.error || "No se pudo procesar la solicitud.", "error");
                }
            },
            error: function(xhr) {
                $.LoadingOverlay("hide");
                try {
                    var res = JSON.parse(xhr.responseText);
                    Swal.fire("Error", res.error, "error");
                } catch(e) {
                    Swal.fire("Error", "Codigo: " + xhr.status, "error");
                }
            }
        });
    });
 
});