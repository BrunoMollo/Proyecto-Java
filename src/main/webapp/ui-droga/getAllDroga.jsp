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
					<td><input value=<%=d.getNombre()%> type="text" id=<%="name_"+d.getCod()%>></td>
					<td><input type="button" value="Guardar" onclick=<%="sendUpdate("+d.getCod()+")" %> /></td>
					<td><input type="button" value="Eliminar" onclick=<%="MarcarParaBorrar("+d.getCod()+")" %> /></td>
				</tr>
		<%}%>
	</tbody>
</table>

<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script type="text/javascript">
	function sendUpdate(cod) {
		newName=document.getElementById("name_"+cod).value;
	
		axios(
			{
				url: '/lafarmacia/ABMC-droga/update',
				method:"post",
				params:{
					cod_droga: cod,
					name_droga: newName
				}
			}		
		)
		.then((res)=>location.reload())
		.catch((err)=>{ console.log(err.response.data); alert("ups... algo salio mal") })
		
	}
</script>

<script type="text/javascript">
	function MarcarParaBorrar(cod) {
		axios(
				{
					url: '/lafarmacia/ABMC-droga/delete',
					method:"post",
					params:{
						cod_droga: cod,
					}
				}		
			)
			.then((res)=>location.reload())
			.catch((err)=>{ console.log(err.response.data); alert("ups... algo salio mal") })
			
	}
</script>

</body>
</html>