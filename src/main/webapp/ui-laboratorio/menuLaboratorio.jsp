<%@page import="entities.Laboratorio"%>
<%@page import="java.util.LinkedList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<style>
table.center {
  margin-left: auto; 
  margin-right: auto;
}
</style>
	<meta charset="ISO-8859-1">
	<title>Listado de laboratorios</title>
	<%
	LinkedList<Laboratorio> arr = (LinkedList<Laboratorio>)request.getAttribute("listalab");
	%>
</head>
<body>
<h2 align="center">Laboratorios</h2>

<p align="center">[ <a href="../index.html">Volver al menu</a> ] </p>

<table class="center" border="1px solid black">
	<thead>
		<tr>
			<th>Codigo</th>
			<th>Nombre</th>
			<th>Email</th>
			<th>Telefono</th>
			<th></th>
			<th></th>
		</tr>
	</thead>
	<tbody>
		<% for(Laboratorio l: arr){ %>
				<tr>
					<td><%=l.getCodigo()%> </td>
					<td><input value=<%=l.getNombre()%> type="text" id=<%="name-"+l.getCodigo()%> ></td>
					<td><input value=<%=l.getEmail()%> type="email" id=<%="email-"+l.getCodigo()%> ></td>
					<td><input value=<%=l.getTelefono()%> type="number" id=<%="telefono-"+l.getCodigo()%> ></td>
					<td><input type="button" value="Guardar" onclick=<%="MarcarParaModificar("+l.getCodigo()+")" %> /></td>
					<td><input type="button" value="Eliminar" onclick=<%="MarcarParaBorrar("+l.getCodigo()+")" %> /></td>
				</tr>
		<%}%>
	</tbody>
</table>

<form hidden="true" id="miForm" action="modifylab" method="post">
	<input name="codModifiedLab" id="codModifiedLab">
	<input name="newName" id="newName">
	<input name="newEmail" id="newEmail">
	<input name="newTelefono" id="newTelefono">
</form>

<script type="text/javascript">
	function MarcarParaModificar(oldCodLab) {
		newName=document.getElementById("name-"+oldCodLab).value;
		newEmail=document.getElementById("email-"+oldCodLab).value;
		newTelefono=document.getElementById("telefono-"+oldCodLab).value;
		
		document.getElementById("codModifiedLab").value=oldCodLab;
		document.getElementById("newName").value = newName;
		document.getElementById("newEmail").value = newEmail;
		document.getElementById("newTelefono").value = newTelefono;
		
		document.getElementById("miForm").submit();
	}
</script>

<form hidden="true" id="ourForm" action="deletelab" method="post">
	<input name="codLab" id="codLab">
	
</form>

<script type="text/javascript">
	function MarcarParaBorrar(codLab) {
		
		document.getElementById("codLab").value=codLab;
	
		document.getElementById("ourForm").submit();
	}
</script>

</body>
</html>