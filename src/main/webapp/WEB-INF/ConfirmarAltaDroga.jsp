<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>La farmacia</title>
	<%
		String cod= request.getParameter("cod");
		String name= request.getParameter("name");
	%>
</head>
<body>
	<h1>Se dio de alta la droga</h1>
		<div>Codigo: <%=cod %></div>
		<div>Nombre: <%=name %></div>
		<a href="index.html">Volver al menu</a>

</body>
</html>