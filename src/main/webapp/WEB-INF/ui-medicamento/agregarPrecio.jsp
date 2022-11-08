<%@page import="entities.Medicamento"%>
<%@page import="entities.Precio"%>
<%@page import="java.util.LinkedList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Ingresar Nuevo Precio</title>

<%LinkedList<Precio> listaPrecios = (LinkedList<Precio>) request.getAttribute("listaPrecios");%>
<%Medicamento med = (Medicamento) request.getAttribute("med");%>

</head>
<body>

<form action="/lafarmacia/AltaMedicamento/addnuevoprecio" method="post">
	<label>Ingrese el nuevo precio del medicamento:</label>
	<input type="number" name="precioNuevo" step=0.01 min=0 required>
	<input type="date" name="fechaNuevo" required>
	<button type="submit">Guardar</button>
</form>
<br>
<a href="../index.html">Cancelar</a>
<br><br>
<label>Precios anteriores:</label>
<table>
	<thead>
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