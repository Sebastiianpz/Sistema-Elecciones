<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="es">

<head>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>Iniciar Sesión — Padrón Nacional Electoral</title>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.min.css">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css">

</head>

<body class="fondo-login">

	<div class="login-card">

		<div class="login-logo">🗳️</div>

		<div class="login-titulo">
			Padrón Nacional Electoral
		</div>

		<div class="login-sub">
			Sistema de Gestión Electoral
		</div>

		<label class="login-label" for="input-user">
			Usuario
		</label>

		<input
			type="text"
			id="input-user"
			class="login-input"
			placeholder="Ingrese su usuario"
			autocomplete="username"
			autofocus>

		<label class="login-label" for="input-pass">
			Contraseña
		</label>

		<input
			type="password"
			id="input-pass"
			class="login-input"
			placeholder="Ingrese su contraseña"
			autocomplete="current-password">

		<button
			type="button"
			id="btn-login"
			class="btn-login">

			Ingresar

		</button>

		<div
			id="alerta-error"
			class="alerta-error">
		</div>

		<a
			href="${pageContext.request.contextPath}/vistas/busqueda.jsp"
			class="link-ciudadano">

			Consultar habilitación electoral

		</a>

	</div>

	<script
		src="${pageContext.request.contextPath}/scripts/jquery/jquery.min.js"></script>

	<script>

		const ctx = "${pageContext.request.contextPath}";

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

						window.location.href =
								ctx + "/vistas/listar.jsp";

					} else {

						mostrarError(data.error);

						resetBoton();

					}

				},

				error: function(xhr) {

					if (xhr.responseJSON &&
							xhr.responseJSON.error) {

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

		$("#btn-login").click(function() {

			login();

		});

		$("#input-user, #input-pass").keypress(function(e) {

			if (e.which == 13) {

				login();

			}

		});

	</script>

</body>

</html>