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

</head>
<body>
	<form action="/lafarmacia/AltaMedicamento/cargadosis" method="post">
		<label>Nombre Droga:</label>
		<input type="text" name="codDrug">
		<label>Cantidad Droga:</label>
		<input type="number" name="cantDrug">
		<select name="select">
		  <option value="mg"selected>mg</option>
		  <option value="ml">ml</option>
		</select>
		<button type="submit">Agregar droga</button>
	</form>
	<table>
		<thead>
			<tr>
				<th>Droga</th> 
				<th>Cantidad</th>	
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