<%@page import="entities.Medicamento"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<link href="../style/asyncRequest.css" rel="stylesheet" type="text/css">
		<script defer src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
		<script defer src="../js/asyncRequest.js"></script>
		<link href="../style/sharedStyle.css" rel="stylesheet" type="text/css">
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
		<script defer src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>
		<meta charset="ISO-8859-1">
		<title>Modificar Medicamento</title>
		<%
			Medicamento med = (Medicamento) request.getSession().getAttribute("medicamento");
		%>
	</head>
	<body>
		<form action="updatemedicamento" method="post">
			<div class="row-sm-6">
				<label class="form-label">Nombre:</label>
				<input type="text" name="name_med" value=<%=med.getNombre() %> required>
			</div>
			<br>
			<div class="row-sm-6">
				<label class="form-label">Laboratorio:</label>
					<div class="async-search" url='../ABMC-laboratorio/getbyname' searchParameter="name_lab">
						<input autocomplete="off" name="name_lab" value=<%=med.getLaboratorio()!=null?med.getLaboratorio().getNombre():"" %>>
						<ul id="sugerencias"></ul>	
					</div>
					
			</div>
			<br>
			<div class="row-sm-6">
				<label class="form-label">Tamaño:</label>
				<input type="number" name="size_med" class="form-control" step="0.01" value=<%=med.getSize() %> required>
				
			</div>
			<br>
			<input hidden value=<%=med.getUnidad() %> id="valueUnidad">
			<select class="dropdown" name="unit_med" id="selectUnidad">
					<option value="unidades">unidades</option>
					<option value="mg">mg</option>
					<option value="ml">ml</option>
					<option value="dosis">dosis</option>
				</select>
			<br><br>
			<div class="row-sm-6">
				<button type="submit" class="w-50 btn btn-primary btn-lg d-block mx-auto">Continuar / Modificar Drogas</button>
				<a href="../index.html" class="w-50 mt-1 mb-3 btn btn-primary btn-lg d-block mx-auto">Volver al menu</a>	
			</div>
		</form>
		<div class="row-sm-6">
				<form method="post" action="deleteMedicamento">
					<label for="deleteButton" >Danger Zone!!</label>
					<button type="submit" id="deleteButton" class="w-50 btn btn-danger btn-lg d-block mx-auto">Eliminar Medicamento</button>
					<input type="text" name="name_med" value=<%=med.getNombre() %> hidden>
				</form>
			</div>
		<script type="text/javascript">
		const unidad = document.getElementById("valueUnidad").value;
		document.getElementById('selectUnidad').value=unidad;
		</script>
	</body>
</html>