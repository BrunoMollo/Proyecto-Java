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
	
	<link href="/lafarmacia/style/asyncRequest.css" rel="stylesheet">
	<script defer src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
	<script defer src="/lafarmacia/js/asyncRequest.js"></script>
	
	
	

</head>
<body>
<div class="form-group">
	<form action="cargadosis" method="post">
	
		
			<div class="async-search" url="/lafarmacia/ABMC-droga/getbyname" searchParameter="name_droga" >
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
				<form action="guardarmedicamento" method="post">
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