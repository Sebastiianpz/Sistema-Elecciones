<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<title>Registro de Persona</title>
</head>
	<!-- 1. Declaramos la variable global para que el script externo conozca el contexto -->
	<script>
		window.contextPath = "${pageContext.request.contextPath}";
	</script>
<body>

	<form id="formPersona">
		<input type="text" name="dni" placeholder="DNI" required> <input
			type="text" name="apellido" placeholder="Apellido" required>
		<input type="text" name="nombre" placeholder="Nombre" required>
		<input type="date" name="fechaNac" required> <select
			name="sexo">
			<option value="M">M</option>
			<option value="F">F</option>
			<option value="X">X</option>
		</select> <input type="text" name="domicilio" placeholder="Domicilio">
		<input type="file" name="imagen" accept="image/*">

		<button type="submit">Registrar Persona</button>
	</form>

	<div id="mensaje"></div>



	<!-- 2. Importación de librerías según tu estructura de carpetas en Eclipse -->
	<script
		src="${pageContext.request.contextPath}/assets/jquery/jquery.min.js"></script>

	<!-- 3. Importación de tu script personalizado (asumiendo que está en assets/scripts) -->
	<script
		src="${pageContext.request.contextPath}/assets/scripts/persona.js"></script>

</body>
</html>
