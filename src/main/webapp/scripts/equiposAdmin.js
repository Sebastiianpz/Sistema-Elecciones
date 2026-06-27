$(function() {
    var bootstrapModal = new bootstrap.Modal(document.getElementById('modalEquipo'));

    // 🔍 CARGAR EQUIPOS AL ENTRAR A LA PÁGINA
    $.ajax({
        url: contextPath + '/listarEquipos',
        method: 'GET',
        dataType: 'json',
        success: function(listaEquipos) {
            var contenedor = $("#contenedor-equipos");
            if (contenedor.length === 0) return;
            contenedor.empty();

            if (listaEquipos.length === 0) {
                contenedor.html('<div class="text-center py-4 text-muted">No hay terminales de votación registradas.</div>');
                return;
            }

            // Recorremos la lista que viene de tu ListarEquiposController
            // Recorremos la lista que viene de tu ListarEquiposController
$.each(listaEquipos, function(index, equipo) {
    var esUltimo = index === listaEquipos.length - 1 ? "" : "border-bottom pb-4";
    
    // Evitamos que pinte un null en el diseño por si las moscas
    var nombreMostrar = equipo.nombreMac || "PC Desconectada";
    var macMostrar = equipo.macAddress || "Sin MAC";
    var votosMostrar = equipo.votosEmitidos !== undefined ? equipo.votosEmitidos : 0;
    
    var filaHtml = `
        <div class="row align-items-center ${esUltimo} g-3">
            <div class="col-12 col-md-8 d-flex align-items-center gap-4">
                <div class="bg-light rounded-3 d-flex align-items-center justify-content-center text-secondary border shadow-sm" 
                     style="width: 54px; height: 54px; min-width: 54px; font-size: 1.5rem;">
                    <i class="fas fa-desktop"></i>
                </div>
                <div>
                    <div class="d-flex align-items-center gap-2">
                        <h5 class="mb-0 fw-bold text-dark" style="font-size: 1.1rem;">${nombreMostrar}</h5>
                    </div>
                    <div class="text-muted mt-1" style="font-size: 0.85rem;">
                        <span class="me-3">MAC: <code class="px-2 py-0.5 rounded text-dark font-monospace" style="background-color: #f1f5f9;">${macMostrar}</code></span>
                        <span>Votos Emitidos: <strong class="text-dark">${votosMostrar}</strong></span>
                    </div>
                </div>
            </div>
            <div class="col-12 col-md-4 d-flex justify-content-md-end justify-content-start align-items-center">
                <div class="d-flex gap-2">
                    <button class="btn btn-outline-primary btn-sm rounded-2 px-3 fw-medium btn-editar-equipo" data-id="${equipo.id}">
                        <i class="fas fa-edit me-1"></i> Editar
                    </button>
                    <button class="btn btn-outline-danger btn-sm rounded-2 px-3 fw-medium btn-eliminar-equipo" data-id="${equipo.id}">
                        <i class="fas fa-trash-alt me-1"></i> Eliminar
                    </button>
                </div>
            </div>
        </div>
    `;
    contenedor.append(filaHtml);
});
        },
        error: function(xhr) {
            console.log("Error al listar los equipos.");
        }
    });

    $(document).on("click", "[data-bs-target='#modalEquipo']", function() {
        $("#modalEquipoLabel").text("Agregar Nuevo Equipo de Votación");
        $("#btnGuardarEquipo").text("AGREGAR EQUIPO");
        $("#formEquipo")[0].reset();
        $("#equipoId").val(""); // Vacío porque es nuevo
    });

    $(document).on("click", ".btn-editar-equipo", function(e) {
        e.preventDefault();
        var idEquipo = $(this).data("id");

        $.ajax({
            url: contextPath + '/buscarEquipoPorId',
            method: 'GET',
            data: { id: idEquipo },
            dataType: 'json',
            success: function(equipo) {
                $("#modalEquipoLabel").text("Modificar Equipo de Votación");
                $("#btnGuardarEquipo").text("MODIFICAR EQUIPO");

                // Cargamos los datos en las cajas de texto del modal
                $("#equipoId").val(equipo.id);
                $("#nombreMac").val(equipo.nombreMac);
                $("#macAddress").val(equipo.macAddress || "00-00-00-00-00-00"); // Respaldo por si no viene la MAC
                
                bootstrapModal.show();
            },
            error: function(xhr) {
                Swal.fire("Error", "No se pudo traer los datos del equipo.", "error");
            }
        });
    });

    $(document).on("click", "#btnGuardarEquipo", function(e) {
        e.preventDefault();

        var id = $("#equipoId").val();
        var nombreMac = $("#nombreMac").val().trim();
        var macAddress = $("#macAddress").val().trim();

        if (!nombreMac || !macAddress) {
            Swal.fire("Atención", "Por favor, complete todos los campos.", "warning");
            return;
        }

        var urlDestino = (id === "") ? '/guardarEquipo' : '/cambiarEstadoEquipo';

        $.ajax({
            url: contextPath + urlDestino,
            method: 'POST',
            data: {
                id: id,
                nombreMac: nombreMac,
                macAddress: macAddress,
                estadoPc: true,      // Parámetro que pide tu servlet
                votosEmitidos: 0     // Parámetro que pide tu servlet
            },
            dataType: 'json',
            success: function(response) {
                // Tus servlets responden con "ok": true
                if (response.ok) {
                    Swal.fire("Éxito", response.mensaje, "success").then(() => {
                        window.location.reload(); // Recarga la página para ver los cambios
                    });
                } else {
                    Swal.fire("Error", response.error, "error");
                }
            },
            error: function(xhr) {
                Swal.fire("Error", "Error en el servidor al guardar.", "error");
            }
        });
    });

    // ❌ CLIC EN EL BOTÓN "ELIMINAR"
    $(document).on("click", ".btn-eliminar-equipo", function(e) {
        e.preventDefault();
        var idEquipo = $(this).data("id");

        Swal.fire({
            title: "¿Está seguro?",
            text: "El equipo será eliminado del sistema.",
            icon: "warning",
            showCancelButton: true,
            confirmButtonColor: "#d33",
            cancelButtonColor: "#3085d6",
            confirmButtonText: "Sí, eliminar",
            cancelButtonText: "Cancelar"
        }).then((result) => {
            if (result.isConfirmed) {
                $.ajax({
                    url: contextPath + '/eliminarEquipo',
                    method: 'POST',
                    data: { id: idEquipo },
                    dataType: 'json',
                    success: function(response) {
                        if (response.ok) {
                            Swal.fire("Eliminado", response.mensaje, "success").then(() => {
                                window.location.reload();
                            });
                        } else {
                            Swal.fire("Error", response.error, "error");
                        }
                    },
                    error: function(xhr) {
                        Swal.fire("Error", "No se pudo eliminar el equipo.", "error");
                    }
                });
            }
        });
    });
});