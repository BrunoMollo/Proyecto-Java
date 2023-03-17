<%@page import="entities.Medicamento"%>
<%@page import="entities.Precio"%>
<%@page import="java.util.LinkedList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
<link href="../style/asyncRequest.css " rel="stylesheet" type="text/css">
	<script defer src="../js/apiLocalidades.js"></script>
<title>Ingresar Nuevo Precio</title>

<%LinkedList<Precio> listaPrecios = (LinkedList<Precio>) request.getAttribute("listaPrecios");%>
<%Medicamento med = (Medicamento) request.getAttribute("med");%>

</head>
<body>
<header class="head-list">
	<h1 class="title-list">Precios</h1>
	<a href="../index" class="btn btn-lg btn-primary btn-c">Volver al menu</a>
</header>
<form style="margin:40px 5% " action="addnuevoprecio" method="post">
	<label class="h4">Ingrese el nuevo precio del medicamento</label><br>
	<input type="number" name="precioNuevo" step=0.01 min=0 required>
	<input type="date" name="fechaNuevo" required>
	<button class="btn btn-lg btn-primary btn-s" type="submit">Guardar</button>
</form>
<br>
<br><br>
<label style="margin-left:5%"class="h4" >Precios anteriores:</label>
<table class="table table-responsive">
	<thead class="t-head">
		<tr>
			<th>Fecha Vigencia</th>
			<th>Monto</th>
		</tr>
	</thead>
	<tbody>
		<% for(Precio p: listaPrecios){ %>
			<tr>
				<td> <%=p.getFecha()%> </td>
				<td> <%=p.getMonto()%> </td>
			</tr>
		<% }%>
	</tbody>
</table>
</body>
</html>