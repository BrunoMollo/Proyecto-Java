<%@page import="entities.Droga"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>La farmacia</title>
	<%
		Droga drug= (Droga)request.getAttribute("droga");
	%>
</head>
<body>
	<h1>Se dio de alta la droga</h1>
		<div>Codigo: <%=drug.getCod() %></div>
		<div>Nombre: <%=drug.getNombre() %></div>
		<a href="../index.html">Volver al menu</a>

</body>
</html>