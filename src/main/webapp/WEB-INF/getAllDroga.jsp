<%@page import="entities.Droga"%>
<%@page import="java.util.LinkedList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Mostrar todas las drogas</title>
<%
LinkedList<Droga> arr = (LinkedList<Droga>)request.getAttribute("listadroga");
%>
</head>
<body>
<h2>Drogas</h2>
<table class="table">
	<thead>
		<tr>
			<th>Codigo</th>
			<th>Nombre</th>
		</tr>
	</thead>
	<tbody>
		<% for(Droga d: arr){ %>
			<tr>
				<td><%=d.getCod()%></td>
				<td><%=d.getNombre()%></td>
			</tr>
		<%}%>
	</tbody>
</table>



</body>
</html>