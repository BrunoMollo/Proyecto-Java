<%@page import="entities.Droga"%>
<%@page import="java.util.LinkedList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Listado de drogas</title>
	<%
	LinkedList<Droga> arr = (LinkedList<Droga>)request.getAttribute("all");
	System.out.println(request);
	%>
</head>
<body>
<h2>Drogas</h2>

<p>[ <a href="../index.html">Volver al menu</a> ] </p>

<table class="table" border="1px solid black">
	<thead>
		<tr>
			<th>Codigo</th>
			<th>Nombre</th>
			<th></th>
			<th></th>
		</tr>
	</thead>
	<tbody>
		<% for(Droga d: arr){ %>
				<tr>
					<td><%=d.getCod()%> </td>
					<td><input value=<%=d.getNombre()%> type="text" id=<%="name-"+d.getCod()%>></td>
					<td><input type="button" value="Guardar" onclick=<%="MarcarParaModificar("+d.getCod()+")" %> /></td>
					<td><input type="button" value="Eliminar" onclick=<%="MarcarParaBorrar("+d.getCod()+")" %> /></td>
				</tr>
		<%}%>
	</tbody>
</table>

<form hidden="true" id="miForm" action="/lafarmacia/ABMC-droga/update" method="post">
	<input name="cod-droga" id="codModifiedDrug">
	<input name="name-droga" id="newName">
	
</form>

<script type="text/javascript">
	function MarcarParaModificar(oldCodDroga) {
		newName=document.getElementById("name-"+oldCodDroga).value;
		
		document.getElementById("codModifiedDrug").value=oldCodDroga;
		document.getElementById("newName").value = newName;
		
		document.getElementById("miForm").submit();
	}
</script>

<form hidden="true" id="ourForm" action="/lafarmacia/ABMC-droga/delete" method="post">
	<input name="cod-droga" id="codDrug">
	
</form>

<script type="text/javascript">
	function MarcarParaBorrar(codDroga) {
		
		document.getElementById("codDrug").value=codDroga;
	
		document.getElementById("ourForm").submit();
	}
</script>

</body>
</html>