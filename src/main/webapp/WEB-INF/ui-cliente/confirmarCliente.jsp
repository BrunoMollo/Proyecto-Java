<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="entities.Cliente"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<title>La farmacia</title>
	<%
		Cliente cli= (Cliente)request.getAttribute("addedObject");
	%>
</head>
<body>
	<h1>Se dio de alta nuevo Cliente</h1>
		<div>Id: <%=cli.getDni() %></div>
		<div>Nombre: <%=cli.getNombre() %></div>
		<div>Nombre: <%=cli.getApellido() %></div>
		<div>Fecha Nacimiento: <%=cli.getFechaNacimiento().format(Cliente.dFormat) %></div>
		<div>Telefono: <%=cli.getTelefono() %></div>
		<div>Email: <%=cli.getEmail() %></div>
		<div>Localidad: <%=cli.getLocalidad() %></div>
		<div>Provincia: <%=cli.getProvincia() %></div>
		
		<a href="../index.html">Volver al menu</a>

</body>
</html>