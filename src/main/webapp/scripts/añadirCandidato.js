$(function () {
 
    $("#btnGuardarCandidato").on("click", function (e) {
        e.preventDefault();
        e.stopPropagation();
 
        var nombreCompleto = $("#nombreCompleto").val().trim();
        var partido        = $("#partido").val().trim();
        var numPartido     = $("#numPartido").val().trim();
        var colorPartido   = $("#colorPartido").val();
 
        // --- Validación ---
        if (!nombreCompleto || !partido || !numPartido) {
            Swal.fire("Atención", "Por favor, completá todos los campos.", "warning");
            return;
        }
 
        if (isNaN(numPartido) || parseInt(numPartido) <= 0) {
            Swal.fire("Atención", "El número de partido debe ser un valor positivo.", "warning");
            return;
        }
 
        $.LoadingOverlay("show", { text: "Guardando..." });
 
        $.ajax({
            url: contextPath + "/guardarCandidato",
            type: "POST",
            dataType: "JSON",
            data: {
                nombreCompleto: nombreCompleto,
                partido:        partido,
                numPartido:     parseInt(numPartido),
                colorPartido:   colorPartido
            },
            cache: false,
            success: function (data) {
                $.LoadingOverlay("hide");
 
                if (data.ok === true) {
                    // Cierra el modal
                    bootstrap.Modal.getInstance(document.getElementById("modalCandidato")).hide();
 
                    // Agrega a la lista local y re-renderiza la página
                    listaCandidatosBD.push({
                        id:             Date.now(),
                        nombreCompleto: nombreCompleto,
                        partido:        partido,
                        numPartido:     parseInt(numPartido),
                        colorPartido:   colorPartido,
                        votos:          0
                    });
                    actualizarAlmacenamiento();
                    renderizarListaCandidatos();
 
                    Swal.fire({
                        icon: "success",
                        title: "¡Candidato agregado!",
                        text: data.mensaje,
                        confirmButtonColor: "#2563eb",
                        timer: 2500,
                        showConfirmButton: false
                    });
 
                } else {
                    Swal.fire("Error", data.error || "No se pudo guardar el candidato.", "error");
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