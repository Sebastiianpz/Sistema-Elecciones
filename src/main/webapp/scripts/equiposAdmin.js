$(function() {
	var bootstrapModal = new bootstrap.Modal(document.getElementById('modalEquipo'));

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

			$.each(listaEquipos, function(index, equipo) {
				var esUltimo = index === listaEquipos.length - 1 ? "" : "border-bottom pb-4";
				var nombreMostrar = equipo.nombreMac || "PC Desconectada";
				var macMostrar = equipo.macAddress || "Sin MAC";
				var votosMostrar = equipo.votosEmitidos !== undefined ? equipo.votosEmitidos : 0;

				var badgeEstado = "";
				var botonEstadoHtml = "";

				if (equipo.estadoPc) {
					badgeEstado = `<span class="badge bg-success-subtle text-success border border-success-subtle rounded-pill px-2.5 py-1" style="font-size: 0.75rem;">Habilitado</span>`;
					botonEstadoHtml = `
                        <button class="btn btn-outline-warning btn-sm rounded-2 px-3 fw-medium btn-estado-equipo" data-id="${equipo.id}" data-estado="false">
                            <i class="fas fa-ban me-1"></i> Deshabilitar
                        </button>
                    `;
				} else {
					badgeEstado = `<span class="badge bg-danger-subtle text-danger border border-danger-subtle rounded-pill px-2.5 py-1" style="font-size: 0.75rem;">Deshabilitado</span>`;
					botonEstadoHtml = `
                        <button class="btn btn-outline-success btn-sm rounded-2 px-3 fw-medium btn-estado-equipo" data-id="${equipo.id}" data-estado="true">
                            <i class="fas fa-check me-1"></i> Habilitar
                        </button>
                    `;
				}

				var filaHtml = `
                    <div class="row align-items-center ${esUltimo} g-3">
                        <div class="col-12 col-md-8 d-flex align-items-center gap-4">
                            <div class="bg-light rounded-3 d-flex align-items-center justify-content-center text-secondary border shadow-sm" 
                                 style="width: 54px; height: 54px; min-width: 54px; font-size: 1.5rem;">
                                <i class="fas fa-desktop"></i>
                            </div>
                            <div>
                                <div class="d-flex align-items-center gap-2 flex-wrap">
                                    <h5 class="mb-0 fw-bold text-dark" style="font-size: 1.1rem;">${nombreMostrar}</h5>
                                    ${badgeEstado}
                                </div>
                                <div class="text-muted mt-1" style="font-size: 0.85rem;">
                                    <span class="me-3">MAC: <code class="px-2 py-0.5 rounded text-dark font-monospace" style="background-color: #f1f5f9;">${macMostrar}</code></span>
                                    <span>Votos Emitidos: <strong class="text-dark">${votosMostrar}</strong></span>
                                </div>
                            </div>
                        </div>
                        <div class="col-12 col-md-4 d-flex justify-content-md-end justify-content-start align-items-center">
                            <div class="d-flex gap-2 flex-wrap">
                                ${botonEstadoHtml}
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

	});

	$(document).on("click", ".btn-estado-equipo", function(e) {
		e.preventDefault();
		var idEquipo = $(this).data("id");
		var nuevoEstado = $(this).data("estado");

		$.ajax({
			url: contextPath + '/cambiarEstadoEquipo',
			method: 'POST',
			data: {
				id: idEquipo,
				estadoPc: nuevoEstado
			},
			dataType: 'json',
			success: function(response) {
				if (response.ok) {
					Swal.fire({
						title: "¡Estado Actualizado!",
						text: response.mensaje || "El estado de la máquina cambió correctamente.",
						icon: "success",
						timer: 1500,
						showConfirmButton: false
					}).then(() => {
						window.location.reload();
					});
				} else {
					Swal.fire("Error", response.error, "error");
				}
			},
			error: function(xhr) {
				Swal.fire("Error", "No se pudo cambiar el estado de la terminal.", "error");
			}
		});
	});

	$(document).on("click", "[data-bs-target='#modalEquipo']", function() {
		$("#modalEquipoLabel").text("Agregar Nuevo Equipo de Votación");
		$("#btnGuardarEquipo").text("AGREGAR EQUIPO");
		$("#formEquipo")[0].reset();
		$("#equipoId").val("");
	});

	// ==========================================
	// CARGAR DATOS EN EL MODAL AL EDITAR
	// ==========================================
	$(document).on("click", ".btn-editar-equipo", function(e) {
		e.preventDefault();
		var idEquipo = $(this).data("id");

		$.ajax({
			url: contextPath + '/buscarEquipoPorId',
			method: 'GET',
			data: { id: idEquipo },
			dataType: 'json',
			success: function(equipo) {
				if (equipo) {
					$("#modalEquipoLabel").text("Modificar Equipo de Votación");
					$("#btnGuardarEquipo").text("MODIFICAR EQUIPO");

					$("#equipoId").val(equipo.id);

					var nombreDefinitivo = equipo.nombreMac || equipo.nombre || "";
					var macDefinitiva = equipo.macAddress || equipo.mac_address || "";

					$("#nombreMac").val(nombreDefinitivo);
					$("#macAddress").val(macDefinitiva);

					bootstrapModal.show();
				} else {
					Swal.fire("Error", "No se recibieron datos válidos de la terminal.", "error");
				}
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

		var urlDestino = (id === "") ? '/guardarEquipo' : '/modificarEquipo';

		$.ajax({
			url: contextPath + urlDestino,
			method: 'POST',
			data: {
				id: id,
				nombreMac: nombreMac,
				macAddress: macAddress
			},
			dataType: 'json',
			success: function(response) {
				if (response.ok) {
					Swal.fire("Éxito", response.mensaje, "success").then(() => {
						window.location.reload();
					});
				} else {
					Swal.fire("Error", response.error, "error");
				}
			},
			error: function(xhr) {
				Swal.fire("Error", "Error en el servidor al procesar el equipo.", "error");
			}
		});
	});

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