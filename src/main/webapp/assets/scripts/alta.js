$(document).ready(function() {
	$('#formPersona').on('submit', function(e) {
		e.preventDefault(); // Evita que los datos aparezcan en la URL

		// 'this' es el formulario nativo, necesario para FormData
		const formData = new FormData(this);
		
		$.ajax({
			url: window.contextPath + '/Persona', // Apunta exactamente a /Padron-Nacional/Persona
			type: 'POST',
			data: formData,
			processData: false,
			contentType: false,
			dataType: 'json',
			success: function(data) {
				if (data.ok) {
					alert("Guardado!");
					$('#formPersona')[0].reset(); // Limpia el formulario
				} else {
					alert("Error: " + data.errores.join(", "));
				}
			},
			error: function(xhr) {
				console.error("Error en la petición:", xhr);
				alert("Error en el envío del formulario.");
			}
		});
	});
});
