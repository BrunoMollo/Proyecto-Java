<%@page import="entities.Droga"%>
<%@page import="java.util.LinkedList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Mostrar todas las drogas</title>
<%
LinkedList<Droga> arr = (LinkedList<Droga>)request.getAttribute("listadroga");
%>
</head>
<body>
<h2>Drogas</h2>


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
					<td><input type="button" value="Eliminar" onclick=<%="" %> /></td>
				</tr>
			
		<%}%>
	</tbody>
</table>

<form hidden="true" id="miForm" action="modifydrug" method="post">
	<input name="codModifiedDrug" id="codModifiedDrug">
	<input name="newName" id="newName">
</form>

<script type="text/javascript">
	function MarcarParaModificar(oldCodDroga) {
		newName=document.getElementById("name-"+oldCodDroga).value;
		
		document.getElementById("codModifiedDrug").value=oldCodDroga;
		document.getElementById("newName").value = newName;
		
		document.getElementById("miForm").submit();
	}
</script>

</body>
</html>