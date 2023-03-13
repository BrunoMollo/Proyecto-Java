<%@page import="entities.Dosis"%>
<%@page import="java.util.HashMap"%>
<%@page import="entities.Medicamento"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Modificar Drogas</title>
		<%Medicamento med = (Medicamento) request.getSession().getAttribute("medicamento"); %>
		<%HashMap<Integer, Dosis> lista=med.getAllDosis(); %>
		<link href="../style/sharedStyle.css" rel="stylesheet" type="text/css">
		<link href="../style/asyncRequest.css " rel="stylesheet" type="text/css">
		<script defer src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
		<script defer src="../js/asyncRequest.js"></script>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
		
	</head>
	<body>
	<header class="p-4">
		<h1 class="d-inline ml-3">Cargar Dosis</h1>
		<a href="../index" class="w-20 mt-1 mb-3 mr-2 btn btn-success btn-lg float-right">Volver al menu</a>
	</header>
		<div class="form-group p-4">
			<form action="cargadosis" method="post">
				<input hidden name="update" value="update">
				<div class="async-search" url="../ABMC-droga/getbyname" searchParameter="name_droga" >
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
				
				
				<button type="submit">Agregar droga</button>
				
			</form>
		</div>	
			<br> <br> <hr>
			
		<table>
			<thead>
				<tr>
					<th>Droga</th> 
					<th>Cantidad</th>
					<th>
					<form action="finishupdatemedicamento" method="post">
						<button type="submit">Guardar medicamento</button>
					</form>
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