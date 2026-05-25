
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
			url: ctx + "/Listar",
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

					let fila = `
                        <tr>
                          <td class="td-dni">${p.nroDocumento}</td>
                          <td class="td-nom">${p.apellido}</td>
                          <td>${p.nombre}</td>
                          <td class="text-center">
                            <span class="${badgeClass}">
                              <span class="dot"></span> ${texto}
                            </span>
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

	// Botón sincronizar
	$("#btn-refrescar").on("click", function(e) {
		e.preventDefault();
		cargarPadron();
	});

	/* ── NUEVO: Filtro rápido metido adentro del archivo JS ── */
	$('#input-buscar-tabla').on('input', function () {
		var q = $(this).val().toLowerCase();
		$('#tabla-padron-body tr').each(function () {
			$(this).toggle($(this).text().toLowerCase().includes(q));
		});
	});

});
