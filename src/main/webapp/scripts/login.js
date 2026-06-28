function login() {

	const user = $("#input-user").val().trim();
	const pass = $("#input-pass").val().trim();

	if (user === "" || pass === "") {
		mostrarError("Debe completar usuario y contraseña.");
		return;
	}

	$("#btn-login")
		.prop("disabled", true)
		.text("Ingresando...");

	$("#alerta-error").hide();

	$.ajax({

		url: ctx + "/Login",

		type: "POST",

		data: {
			username: user,
			password: pass
		},

		dataType: "json",

		success: function(data) {

			if (data.ok) {

				window.location.href = ctx + "/vistas/listar.jsp";

			} else {

				mostrarError(data.error);
				resetBoton();

			}

		},

		error: function(xhr) {

			if (xhr.responseJSON && xhr.responseJSON.error) {

				mostrarError(xhr.responseJSON.error);

			} else {

				mostrarError("Error al conectar con el servidor.");

			}

			resetBoton();

		}

	});

}

function mostrarError(msg) {

	$("#alerta-error")
		.text(msg)
		.show();

}

function resetBoton() {

	$("#btn-login")
		.prop("disabled", false)
		.text("Ingresar");

}

$(document).ready(function () {

	$("#btn-login").click(function () {

		login();

	});

	$("#input-user, #input-pass").keypress(function (e) {

		if (e.which == 13) {

			login();

		}

	});

});