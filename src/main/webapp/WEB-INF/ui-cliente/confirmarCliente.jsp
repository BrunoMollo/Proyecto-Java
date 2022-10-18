<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
	<%@page import="entities.Cliente"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300&display=swap" rel="stylesheet">
<link href="../style/addCliente.css" rel="stylesheet" type="text/css">
<link href="../style/sharedStyle.css" rel="stylesheet" type="text/css">
	<title>La farmacia</title>
	<%
		Cliente cli= (Cliente)request.getAttribute("addedObject");
	%>
</head>
<body>
	<h1>Se dio de alta nuevo Cliente</h1>
		<div>DNI <%=cli.getDni() %></div>
		<div>Nombre: <%=cli.getNombre() %></div>
		<div>Nombre: <%=cli.getApellido() %></div>
		<div>Fecha Nacimiento: <%=cli.getFechaNacimiento().format(Cliente.dFormat) %></div>
		<div>Telefono: <%=cli.getTelefono() %></div>
		<div>Email: <%=cli.getEmail() %></div>
		<div>Localidad: <%=cli.getLocalidad() %></div>
		<div>Provincia: <%=cli.getProvincia() %></div>
		
		<a href="../indexLog.html">Volver al menu</a>

</body>
</html>