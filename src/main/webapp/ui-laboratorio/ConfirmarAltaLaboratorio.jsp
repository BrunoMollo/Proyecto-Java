<%@page import="entities.Laboratorio"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>La farmacia</title>
	<%
		Laboratorio lab= (Laboratorio)request.getAttribute("lab");
	%>
</head>
<body>
	<h1>Se dio de alta el laboratorio</h1>
		<div>Codigo: <%=lab.getCodigo()%> </div>
		<div>Nombre: <%=lab.getNombre()%> </div>
		<div>Telefono: <%=lab.getTelefono() %> </div>
		<div>Email: <%=lab.getEmail()%> </div>		
		<a href="../index.html">Volver al menu</a>
</body>
</html>