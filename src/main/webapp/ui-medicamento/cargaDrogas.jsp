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
	
	<%Medicamento med = (Medicamento)request.getSession().getAttribute("medicamento"); %>
	<%HashMap<Integer, Dosis> lista=med.getAllDosis(); %>
	
	<link href="../style/asyncRequest.css" rel="stylesheet">
	<script defer src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
	<script defer src="/lafarmacia/js/asyncRequest.js"></script>
	
	
	

</head>
<body>
	<form action="/lafarmacia/AltaMedicamento/cargadosis" method="post">
	
		<label>Nombre Droga:</label>
			<div class="async-search" url="/lafarmacia/ABMC-droga/getbyname" searchParameter="name_droga" >
				<input name="name_droga" autocomplete="off">
				<ul id="sugerencias"></ul>	
			</div>
			
			
		<label>Cantidad Droga:</label>
		<input type="number" name="cantDrug">
		<select name="select">
		  <option value="mg"selected>mg</option>
		  <option value="ml">ml</option>
		</select>
		
		
		<button type="submit">Agregar droga</button>
		
	</form>
	
	<br> <br> <hr>
	
	<table>
		<thead>
			<tr>
				<th>Droga</th> 
				<th>Cantidad</th>
				<th>
				<form action="/lafarmacia/AltaMedicamento/guardarmedicamento" method="post">
					<button type="submit">Guardar medicamento</button>
				</form>
				</th>	
			</tr>
		</thead>
		<tbody>
			<%for (Dosis dose : lista.values()) {%>
			<tr>
				<td><%=dose.getDroga().getNombre()%></td>
				<td><%=dose.getCant()%></td>
			</tr>
			<%} %>
		</tbody>
	</table>

</body>
</html>