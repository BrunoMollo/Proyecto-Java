<%@page import="entities.ObraSocial"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>La farmacia</title>
	<%
		ObraSocial os= (ObraSocial)request.getAttribute("os");
	%>
</head>
<body>
	<h1>Se dio de alta la droga</h1>
		<div>Id: <%=os.getId() %></div>
		<div>Nombre: <%=os.getNombre() %></div>
		<div>Telefono: <%=os.getTelefono() %></div>
		<div>Email: <%=os.getEmail() %></div>
		<div>Descuento: <%=os.getDescuento() %></div>
		
		<a href="../index.html">Volver al menu</a>

</body>
</html>