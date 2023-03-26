<%@page import="java.util.HashMap"%>
<%@page import="entities.Dosis"%>
<%@page import="entities.Medicamento"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>La Farmacia</title>
	<link href="../style/sharedStyle.css" rel="stylesheet" type="text/css">
	<link href="../style/asyncRequest.css " rel="stylesheet" type="text/css">
	
	<%Medicamento med = (Medicamento)request.getSession().getAttribute("medicamento"); %>
	<%HashMap<Integer, Dosis> lista=med.getAllDosis(); %>
	
	<script defer src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
	<script defer src="../js/asyncRequest.js"></script>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
	<script defer src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>
	
</head>
<body>
		<div class="p-2">
<div class="form-group">
	<form action="cargadosis" method="post">
	
	<header>
		<h1 class="d-inline ml-3">Cargar Dosis</h1>
		<a href="../index" class="w-20 mt-1 mb-3 mr-2 btn btn-success btn-lg float-right">Volver al menu</a>
	</header>
		
			<div class="async-search" url="../ABMC-droga/getbyname" searchParameter="name_droga">
				<label>Nombre Droga:</label>
				<input name="name_droga" autocomplete="off" required>
				<ul id="sugerencias"></ul>	
			</div>
			
			
		<label>Cantidad Droga:</label>
		<input type="number" name="cantDrug" required>
		<select name="unit_dose">
		  <option value="mg"selected>mg</option>
		  <option value="ml">ml</option>
		</select>
		
		
		<button type="submit" class="w-20 mt-1 mb-3 mr-2 btn btn-success btn-lg">Agregar droga</button>
		
	</form>
	
	<br> <br> <hr>
	<form action="guardarmedicamento" method="post">
					<button type="submit" class="w-20 mt-1 mb-3 mr-2 btn btn-success btn-lg">Guardar medicamento</button>
				</form>
		</div></div>
		
	<table class="table-responsive m-4">
		<thead>
			<tr>
				<th>Droga</th> 
				<th>Cantidad</th>
				<th>
				</th>	
			</tr>
		</thead>
		<tbody>
			<%for (Dosis dose : lista.values()) {%>
			<tr>
				<td><%=dose.getDroga().getNombre()%></td>
				<td><%=dose.getCant()+dose.getUnidad()%></td>
			</tr>
			<%} %>
		</tbody>
	</table>

</body>
</html>