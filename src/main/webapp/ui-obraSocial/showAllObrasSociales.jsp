<%@page import="entities.ObraSocial"%>
<%@page import="java.util.LinkedList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Listado de drogas</title>
	<%
	LinkedList<ObraSocial> arr = (LinkedList<ObraSocial>)request.getAttribute("obrasSociales");
	%>
</head>
<body>
<h2>Drogas</h2>

<p>[ <a href="../index.html">Volver al menu</a> ] </p>

<table class="table" border="1">
	<thead>
		<tr>
			<th>Id</th>
			<th>Nombre</th>
			<th>Telefono</th>
			<th>Email</th>
			<th>Descuento</th>
			<th></th>
			<th></th>
		</tr>
	</thead>
	<tbody>
		<% for(ObraSocial os: arr){ %>
				<tr border="0">
					<td><%=os.getId() %> </td>
					<td><input value=<%=os.getNombre() %> type="text" id=<%="name-"+os.getId()%>></td>
					<td><input value=<%=os.getTelefono() %> type="number" id=<%="tel-"+os.getId()%>></td>
					<td><input value=<%=os.getEmail() %> type="email" id=<%="email-"+os.getId()%>></td>
					<td><input value=<%=os.getDescuento() %> type="number" step="0.001" min="0" max="100" id=<%="discount-"+os.getId()%>></td>
					<td><input type="button" value="Guardar" onclick=<%="updateObraSocial("+os.getId()+")" %> id="btn-update"/></td>
					<td><input type="button" value="Eliminar" onclick=<%="MarcarParaBorrar("+os.getId()+")" %> id="btn-delete"/></td>
					<td width="200px" id=<%="logger-"+os.getId()%>></td>
					
					
				</tr>
		<%}%>
	</tbody>
</table>

<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script src="axiosObraSocial.js"></script>

</body>
</html>