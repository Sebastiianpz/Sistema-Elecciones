// ═══════════════════════════════════════
// ALTA
// ═══════════════════════════════════════

$(document).ready(function () {

    $("#formPersona").on("submit", function (e) {

        e.preventDefault();

        const formData = new FormData(this);

        $.ajax({

            url: $(this).attr("action"),
            type: "POST",
            data: formData,
            processData: false,
            contentType: false,
            dataType: "json",

            success: function (data) {

                if (data.ok) {

                    alert(data.mensaje || "Ciudadano registrado correctamente.");

                    $("#formPersona")[0].reset();

                } else {

                    if (data.errores) {
                        alert(data.errores.join("\n"));
                    } else {
                        alert("Ocurrió un error.");
                    }

                }

            },

            error: function (xhr) {

                console.error(xhr);

                if (xhr.responseJSON && xhr.responseJSON.errores) {

                    alert(xhr.responseJSON.errores.join("\n"));

                } else {

                    alert("Error en el envío del formulario.");

                }

            }

        });

    });

});


// ═══════════════════════════════════════
// LECTURA
// ═══════════════════════════════════════

$(document).ready(function () {
	
	// ===============================
	// BÚSQUEDA POR DNI
	// ===============================

	if ($("#btn-consultar").length) {

	    $("#btn-consultar").click(function () {
	        consultar();
	    });

	    $("#input-dni").keypress(function (e) {

	        if (e.which == 13) {
	            consultar();
	        }

	    });

	}

    function cargarPadron() {

        $("#alerta-estado")
            .html("<span>⏳</span> Sincronizando...")
            .show();

        $("#contenedor-tabla").addClass("d-none");

        $.ajax({

            url: ctx + "/Listar",

            type: "GET",

            dataType: "json",

            success: function (data) {

                $("#tabla-padron-body").empty();

                let total = data.length;
                let habilitados = 0;
                let inhabilitados = 0;

                if (total === 0) {

                    $("#alerta-estado")
                        .html("<span>📭</span> No hay ciudadanos registrados.")
                        .show();

                    $("#dash-total").text("0");
                    $("#dash-habilitados").text("0");
                    $("#dash-inhabilitados").text("0");

                    return;

                }

                $.each(data, function (i, p) {

                    const esHab = p.habilitadoVotar;

                    const badgeClass = esHab ? "bhab on" : "bhab off";

                    const texto = esHab ? "Habilitado" : "Inhabilitado";

                    if (esHab) {
                        habilitados++;
                    } else {
                        inhabilitados++;
                    }

                    const fila = `

<tr id="fila-${p.nroDocumento}">

<td class="text-center" style="width:60px;vertical-align:middle;">

<img
src="../Imagen?id=${p.id}"
class="img-miniatura-dni"
alt="Foto DNI"
onerror="this.onerror=null;this.src='data:image/svg+xml;utf8,<svg xmlns=\\'http://www.w3.org/2000/svg\\' viewBox=\\'0 0 24 24\\' fill=\\'%239ca3af\\'><path d=\\'M12 12c2.21 0 4-1.79 4-4s-1.79-4-4-4-4 1.79-4 4 1.79 4 4 4zm0 2c-2.67 0-8 1.34-8 4v2h16v-2c0-2.66-5-4-8-4z\\'/></svg>'">

</td>

<td>${p.nroDocumento}</td>

<td>${p.apellido}</td>

<td>${p.nombre}</td>

<td class="text-center">

<div style="display:flex;flex-direction:column;align-items:center;gap:4px;">

<label class="form-switch">

<input
type="checkbox"
class="form-check-input toggle-habilitar"
data-id="${p.id}"
data-dni="${p.nroDocumento}"
${esHab ? "checked" : ""}>

</label>

<span class="${badgeClass} badge-estado">

<span class="badge-dot"></span>

${texto}

</span>

</div>

</td>

<td class="text-center">

<button
class="btn-accion btn-ver"
data-id="${p.id}"
data-dni="${p.nroDocumento}"
data-apellido="${p.apellido}"
data-nombre="${p.nombre}"
data-domicilio="${p.domicilio || ""}"
data-fecha="${p.fechaNacimiento || ""}"
data-sexo="${p.sexo || ""}"
data-habilitado="${p.habilitadoVotar}">

👁️ Ver

</button>

<button
class="btn-accion btn-modificar"
data-id="${p.id}"
data-dni="${p.nroDocumento}"
data-apellido="${p.apellido}"
data-nombre="${p.nombre}"
data-domicilio="${p.domicilio || ""}"
data-fecha="${p.fechaNacimiento || ""}"
data-sexo="${p.sexo || ""}"
data-habilitado="${p.habilitadoVotar}">

✏️ Modificar

</button>

<button
class="btn-accion btn-eliminar"
data-dni="${p.nroDocumento}">

🗑️ Eliminar

</button>

</td>

</tr>

`;

                    $("#tabla-padron-body").append(fila);

                });

                $("#dash-total").text(total);
                $("#dash-habilitados").text(habilitados);
                $("#dash-inhabilitados").text(inhabilitados);

                $("#alerta-estado").hide();

                $("#contenedor-tabla").removeClass("d-none");

            },

            error: function (xhr) {

                if (xhr.status === 401) {

                    window.location.href = "login.jsp";
                    return;

                }

                $("#alerta-estado")
                    .html("<span>❌</span> Error al conectar con el servidor.")
                    .show();

            }

        });

    }

    cargarPadron();

    window.recargarTablaCompleta = cargarPadron;

    $("#btn-refrescar").click(function (e) {

        e.preventDefault();

        cargarPadron();

    });

    $("#input-buscar-tabla").on("input", function () {

        const q = $(this).val().toLowerCase();

        $("#tabla-padron-body tr").each(function () {

            $(this).toggle($(this).text().toLowerCase().includes(q));

        });

    });

});

function consultar() {

    let dni = $("#input-dni").val().trim();

    if (dni == "") {

        $("#resultado").html(
            '<div class="resultado-error">' +
            'Debe ingresar un número de documento.' +
            '</div>'
        );

        return;

    }

    $("#btn-consultar")
        .prop("disabled", true)
        .text("Buscando...");

    $.ajax({

        url: ctx + "/Busqueda",

        type: "GET",

        data: {
            dni: dni
        },

        dataType: "json",

        success: function (data) {

            if (data.ok) {

                let clase = data.habilitado ? "habilitado" : "inhabilitado";

                let icono = data.habilitado ? "✅" : "❌";

                let estado = data.habilitado
                    ? "Habilitado para votar"
                    : "No habilitado para votar";

                $("#resultado").html(

                    '<div class="resultado-box ' + clase + '">' +

                    '<div class="resultado-icono">' +
                    icono +
                    '</div>' +

                    '<div class="resultado-nombre">' +
                    data.apellido + ', ' + data.nombre +
                    '</div>' +

                    '<div class="resultado-dni">' +
                    'DNI: ' + data.dni +
                    '</div>' +

                    '<div class="resultado-estado">' +
                    estado +
                    '</div>' +

                    '</div>'

                );

            } else {

                $("#resultado").html(

                    '<div class="resultado-error">' +
                    data.error +
                    '</div>'

                );

            }

        },

        error: function (xhr) {

            let mensaje = "Error al conectar con el servidor.";

            if (xhr.responseJSON) {
                mensaje = xhr.responseJSON.error;
            }

            $("#resultado").html(

                '<div class="resultado-error">' +
                mensaje +
                '</div>'

            );

        },

        complete: function () {

            $("#btn-consultar")
                .prop("disabled", false)
                .text("Buscar");

        }

    });

}


// ═══════════════════════════════════════
// ELIMINAR
// ═══════════════════════════════════════

$(document).ready(function () {

    function recalcularContadores() {

        let total = 0;
        let habilitados = 0;
        let inhabilitados = 0;

        $("#tabla-padron-body tr:visible").each(function () {

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

            window.recargarTablaCompleta();

        }

    }

    $("#tabla-padron-body").on("click", ".btn-eliminar", function () {

        const dni = $(this).data("dni");

        const fila = $(this).closest("tr");

        const boton = $(this);

        if (!confirm("¿Eliminar al ciudadano con DNI " + dni + "?")) {

            return;

        }

        boton.prop("disabled", true).html("⏳");

        $.ajax({

            url: ctx + "/Eliminar",

            type: "POST",

            data: {

                nroDocumento: dni

            },

            success: function () {

                fila.fadeOut(400, function () {

                    $(this).remove();

                    recalcularContadores();

                });

            },

            error: function (xhr) {

                boton.prop("disabled", false).html("🗑️ Eliminar");

                if (xhr.status === 401) {

                    window.location.href = "login.jsp";

                } else {

                    alert("No se pudo eliminar el ciudadano.");

                }

            }

        });

    });

});

// ===============================
// VER DETALLE
// ===============================

$(document).ready(function () {

    $("#tabla-padron-body").on("click", ".btn-ver", function () {

        const id = $(this).data("id");
        const dni = $(this).data("dni");
        const apellido = $(this).data("apellido");
        const nombre = $(this).data("nombre");
        const domicilio = $(this).data("domicilio") || "—";
        const fecha = $(this).data("fecha") || "—";
        const sexo = $(this).data("sexo") || "—";
        const habilitado = $(this).data("habilitado");

        let sexoTexto = "No binario";

        if (sexo === "M") sexoTexto = "Masculino";
        if (sexo === "F") sexoTexto = "Femenino";

        const estado = habilitado
            ? '<span style="color:#16a34a;font-weight:700;">✅ Habilitado para votar</span>'
            : '<span style="color:#dc2626;font-weight:700;">🚫 Inhabilitado</span>';

        $("#detalle-foto")
            .attr("src", ctx + "/Imagen?id=" + id)
            .on("error", function () {

                $(this).attr("src",
                    "data:image/svg+xml;utf8,<svg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 24 24' fill='%239ca3af'><path d='M12 12c2.21 0 4-1.79 4-4s-1.79-4-4-4-4 1.79-4 4 1.79 4 4 4zm0 2c-2.67 0-8 1.34-8 4v2h16v-2c0-2.66-5-4-8-4z'/></svg>");

            });

		$("#detalle-apellido").text(apellido);
		$("#detalle-nombre").text(nombre);
        $("#detalle-dni").text("DNI: " + dni);
        $("#detalle-domicilio").text(domicilio);
        $("#detalle-fecha").text(fecha);
        $("#detalle-sexo").text(sexoTexto);
        $("#detalle-estado").html(estado);

        new bootstrap.Modal(document.getElementById("modalDetalle")).show();

    });

});


// ===============================
// MODIFICAR
// ===============================

$(document).ready(function () {

    $("#tabla-padron-body").on("click", ".btn-modificar", function () {

		$("#modal-mod-foto").attr(
		    "src",
		    ctx + "/Imagen?id=" + $(this).data("id")
		);
        $("#modal-mod-dni").val($(this).data("dni"));
        $("#modal-mod-apellido").val($(this).data("apellido"));
        $("#modal-mod-nombre").val($(this).data("nombre"));
        $("#modal-mod-domicilio").val($(this).data("domicilio"));
        $("#modal-mod-fecha").val($(this).data("fecha"));
        $("#modal-mod-sexo").val($(this).data("sexo"));

        new bootstrap.Modal(document.getElementById("modalModificar")).show();

    });

    $("#btn-confirmar-modificar").on("click", function () {

        const boton = $(this);

        boton.prop("disabled", true);
        boton.text("⏳ Guardando...");

        $.ajax({

            url: ctx + "/Modificar",
            type: "POST",

            data: {

                nroDocumento: $("#modal-mod-dni").val(),
                apellido: $("#modal-mod-apellido").val(),
                nombre: $("#modal-mod-nombre").val(),
                domicilio: $("#modal-mod-domicilio").val(),
                fechaNacimiento: $("#modal-mod-fecha").val(),
                sexo: $("#modal-mod-sexo").val()

            },

            success: function () {

                bootstrap.Modal.getInstance(document.getElementById("modalModificar")).hide();

                boton.prop("disabled", false);
                boton.text("💾 Guardar cambios");

                window.recargarTablaCompleta();

            },

            error: function () {

                boton.prop("disabled", false);
                boton.text("💾 Guardar cambios");

                alert("No se pudieron guardar los cambios.");

            }

        });

    });

});


// ===============================
// HABILITAR / INHABILITAR
// ===============================

$(document).ready(function () {

    $("#tabla-padron-body").on("change", ".toggle-habilitar", function () {

        const id = $(this).data("id");
        const estado = $(this).is(":checked");

        const fila = $(this).closest("tr");
        const badge = fila.find(".badge-estado");
        const toggle = $(this);

        toggle.prop("disabled", true);

        $.ajax({

            url: ctx + "/Habilitar",
            type: "POST",

            data: {

                nroDocumento: id,
                habilitadoVotar: estado

            },

            dataType: "json",

            success: function (response) {

                if (response.habilitado) {

                    badge
                        .removeClass("off")
                        .addClass("on")
                        .html('<span class="badge-dot"></span>Habilitado');

                } else {

                    badge
                        .removeClass("on")
                        .addClass("off")
                        .html('<span class="badge-dot"></span>Inhabilitado');

                }

                fila.find(".btn-ver").data("habilitado", response.habilitado);

                toggle.prop("disabled", false);

            },

            error: function () {

                toggle.prop("checked", !estado);
                toggle.prop("disabled", false);

                alert("No se pudo actualizar el estado.");

            }

        });

    });

});