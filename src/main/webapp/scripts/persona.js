// ALTA
$(document).ready(function() {
	$('#formPersona').on('submit', function(e) {
		e.preventDefault();

		const formData = new FormData(this);

		$.ajax({
			url: $(this).attr('action'), // Ahora sí resolverá correctamente a la ruta del Servlet
			type: 'POST',
			data: formData,
			processData: false,
			contentType: false,
			dataType: 'json',
			success: function(data) {
				if (data.ok) {
					alert("Guardado!");
					$('#formPersona')[0].reset();
				} else {
					alert("Error: " + data.errores.join(", "));
				}
			},
			error: function(xhr) {
				console.error("Error en la petición:", xhr);
				alert("Error en el envío del formulario. Estado: " + xhr.status);
			}
		});
	});
});

// LECTURA

$(document).ready(function() {

	function cargarPadron() {
		$("#alerta-estado").html('<span>⏳</span> Sincronizando...').show();
		$("#contenedor-tabla").addClass("d-none");

		$.ajax({
			url: ctx + "/Listar", // Ruta relativa limpia e inmune a fallas de contexto
			type: "GET",
			dataType: "json",

			success: function(data) {
				$("#tabla-padron-body").empty();

				let total = data.length;
				let habilitados = 0;
				let inhabilitados = 0;

				if (total === 0) {
					$("#alerta-estado").html(
						'<span>📭</span> No hay ciudadanos registrados.'
					).show();
					$("#dash-total, #dash-habilitados, #dash-inhabilitados").text("0");
					return;
				}

				$.each(data, function(i, p) {
					let esHab = p.habilitadoVotar;
					let badgeClass = esHab ? "bhab on" : "bhab off";
					let texto = esHab ? "Habilitado" : "Inhabilitado";

					if (esHab) { habilitados++; } else { inhabilitados++; }

					// Construcción de la fila con metadatos para el modal y marcador SVG nativo
					let fila = `
					<tr id="fila-${p.nroDocumento}">
					          <td class="text-center" style="width: 60px; vertical-align: middle;">
					              <!-- Agregamos "../" para salir de la carpeta /vistas/ y pegarle directo al Servlet en la raíz -->
					              <img src="../Imagen?id=${p.id}" 
					                   alt="Foto DNI" 
					                   class="img-miniatura-dni"
					                   onerror="this.onerror=null; this.src='data:image/svg+xml;utf8,<svg xmlns=\'http://w3.org\' viewBox=\'0 0 24 24\' fill=\'%239ca3af\'><path d=\'M12 12c2.21 0 4-1.79 4-4s-1.79-4-4-4-4 1.79-4 4 1.79 4 4 4zm0 2c-2.67 0-8 1.34-8 4v2h16v-2c0-2.66-5-4-8-4z\'/></svg>';">
					          </td>
					          <td class="td-dni" style="vertical-align: middle;">${p.nroDocumento}</td>
					           <td class="td-nombre" style="vertical-align: middle;">${p.apellido}</td>
					           <td style="vertical-align: middle;">${p.nombre}</td>
					           <td class="text-center" style="vertical-align: middle;">
					               <span class="${badgeClass}">
					                   <span class="badge-dot"></span>
					                   ${texto}
					               </span>
					           </td>
					           <td class="text-center acciones-td" style="vertical-align: middle;">
					               <button class="btn-accion btn-modificar"
					                       data-dni="${p.nroDocumento}"
					                       data-apellido="${p.apellido}"
					                       data-nombre="${p.nombre}"
					                       data-domicilio="${p.domicilio || ''}"
					                       data-fecha="${p.fechaNacimiento || ''}"
					                       data-sexo="${p.sexo || ''}"
					                       data-habilitado="${p.habilitadoVotar}"
					                       title="Modificar ciudadano">
					                   ✏️ Modificar
					               </button>
					               <button class="btn-accion btn-eliminar"
					                       data-dni="${p.nroDocumento}"
					                       title="Eliminar ciudadano">
					                   🗑️ Eliminar
					               </button>
					           </td>
					       </tr>`;

					$("#tabla-padron-body").append(fila);
				});

				$("#dash-total").text(total);
				$("#dash-habilitados").text(habilitados);
				$("#dash-inhabilitados").text(inhabilitados);

				$("#alerta-estado").hide();
				$("#contenedor-tabla").removeClass("d-none");
			},

			error: function(xhr) {
				if (xhr.status === 401) {
					window.location.href = "index.html";
					return;
				}
				$("#alerta-estado").html(
					'<span>❌</span> Error al conectar con el servidor.'
				).show();
			}
		});
	}

	// Carga automática al entrar al dashboard
	cargarPadron();

	// Hacemos la función accesible globalmente para que los bloques de afuera puedan llamarla
	window.recargarTablaCompleta = cargarPadron;

	// Botón sincronizar
	$("#btn-refrescar").on("click", function(e) {
		e.preventDefault();
		cargarPadron();
	});

	/* ── FILTRO RÁPIDO ── */
	$('#input-buscar-tabla').on('input', function() {
		var q = $(this).val().toLowerCase();
		$('#tabla-padron-body tr').each(function() {
			$(this).toggle($(this).text().toLowerCase().includes(q));
		});
	});

});

// ELIMINAR

$(document).ready(function() {

	// Función requerida para recalcular las tarjetas de control del panel tras un borrado fluido
	function recalcularContadores() {
		let total = 0;
		let habilitados = 0;
		let inhabilitados = 0;

		$("#tabla-padron-body tr:visible").each(function() {
			total++;
			if ($(this).find(".bhab").hasClass("on")) {
				habilitados++;
			} else {
				inhabilitados++;
			}
		});

		$("#dash-total").text(total);
		$("#dash-habilitados").text(habilitados);
		$("#dash-inhabilitados").text(inhabilitados);

		if (total === 0) {
			window.recargarTablaCompleta(); // Muestra el cartel de padrón vacío si no quedan filas
		}
	}

	$("#tabla-padron-body").on("click", ".btn-eliminar", function() {
		const dni = $(this).data("dni");
		const fila = $(this).closest("tr");
		const boton = $(this);

		if (!confirm(`¿Eliminar al ciudadano con DNI ${dni}?\nEsta acción no se puede deshacer.`)) {
			return;
		}

		boton.prop("disabled", true).html("⏳");

		$.ajax({
			url: ctx + "/Eliminar",
			type: "POST",
			data: { nroDocumento: dni },

			success: function() {
				fila.fadeOut(400, function() {
					$(this).remove();
					recalcularContadores();
				});
			},

			error: function(xhr) {
				boton.prop("disabled", false).html("🗑️ Eliminar");
				if (xhr.status === 500) {
					alert("Error del servidor: no se pudo eliminar el registro.");
				} else if (xhr.status === 401) {
					window.location.href = "index.jsp";
				} else {
					alert("Error inesperado al comunicarse con el servidor.");
				}
			}
		});
	});
});

/* ─── 3. MODIFICAR ───────────────────────────────────────────── */

$(document).ready(function() {

	$("#tabla-padron-body").on("click", ".btn-modificar", function() {
		const dni = $(this).data("dni");
		const apellido = $(this).data("apellido");
		const nombre = $(this).data("nombre");
		const domicilio = $(this).data("domicilio");
		const fecha = $(this).data("fecha");
		const sexo = $(this).data("sexo");
		const habilitado = $(this).data("habilitado");

		// Precarga de todos los campos mapeados en la estructura modal del JSP
		$("#modal-mod-dni").val(dni);
		$("#modal-mod-apellido").val(apellido);
		$("#modal-mod-nombre").val(nombre);
		$("#modal-mod-domicilio").val(domicilio);
		$("#modal-mod-fecha").val(fecha);
		$("#modal-mod-sexo").val(sexo);
		$("#modal-mod-habilitado").val(habilitado.toString());

		// Abre el modal de Bootstrap
		const modal = new bootstrap.Modal(document.getElementById("modalModificar"));
		modal.show();
	});

	/* ─── 4. SUBMIT DEL MODAL MODIFICAR ───────────────────────────
		   Envía el POST al servlet /Modificar de forma relativa
		─────────────────────────────────────────────────────────────── */
	$("#btn-confirmar-modificar").on("click", function() {
		const boton = $(this);
		boton.prop("disabled", true).html("⏳ Guardando...");

		$.ajax({

			url: ctx + "/Modificar",
			type: "POST",
			data: {
				nroDocumento: $("#modal-mod-dni").val(),
				apellido: $("#modal-mod-apellido").val(),
				nombre: $("#modal-mod-nombre").val(),
				domicilio: $("#modal-mod-domicilio").val(),
				fechaNacimiento: $("#modal-mod-fecha").val(),
				sexo: $("#modal-mod-sexo").val(),
				habilitadoVotar: $("#modal-mod-habilitado").val()
			},

			success: function() {
				bootstrap.Modal.getInstance(document.getElementById("modalModificar")).hide();
				boton.prop("disabled", false).html("💾 Guardar cambios");
				window.recargarTablaCompleta(); // Recarga la tabla dinámica
			},

			error: function(xhr) {
				boton.prop("disabled", false).html("💾 Guardar cambios");
				if (xhr.status === 401) {
					window.location.href = "listar.jsp";
				} else {
					alert("Error " + xhr.status + ": No se pudo guardar. Comprobá las firmas del Servlet.");
				}
			}
		});
	});


});
