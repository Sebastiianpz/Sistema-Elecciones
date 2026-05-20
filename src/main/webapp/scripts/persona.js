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
