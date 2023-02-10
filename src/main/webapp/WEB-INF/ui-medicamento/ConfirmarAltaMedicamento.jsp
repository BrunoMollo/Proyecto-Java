<%@page import="entities.Dosis"%>
<%@page import="entities.Medicamento"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>La farmacia</title>
	<%
		Medicamento med= (Medicamento)request.getSession().getAttribute("medicamento");
	%>
</head>
<body>
	<h1>Datos del medicamento</h1>
		<div>Codigo: <%=med.getCodigoBarra()%> </div>
		<div>Nombre: <%=med.getNombre()%> </div>
		<div>Laboratorio: <%=med.getLaboratorio().getNombre() %> </div>
		<div>Tamaño: <%=med.getSize()+" "+med.getUnidad()%> </div>
		<div>Precio: <%=med.getPrecio()%> </div>
		<h2>Dosis</h2>
		<table>
			<thead>
				<tr>
					<th>Droga</th> 
					<th>Cantidad</th>	
				</tr>
			</thead>
			<tbody>
				<%for (Dosis dose : med.getAllDosis().values()) {%>
				<tr>
					<td><%=dose.getDroga().getNombre()%></td>
					<td><%=dose.getCant()+dose.getUnidad()%></td>
				</tr>
				<%} %>
			</tbody>
		</table>
		<br>		
		<a href="../index.html">Volver al menu</a>
		
</body>
</html>