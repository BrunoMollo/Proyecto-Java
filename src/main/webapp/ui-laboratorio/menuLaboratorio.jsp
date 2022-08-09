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
					<td><input value=<%=l.getNombre()%> type="text" id=<%="name_"+l.getCodigo()%> ></td>
					<td><input value=<%=l.getEmail()%> type="email" id=<%="email_"+l.getCodigo()%> ></td>
					<td><input value=<%=l.getTelefono()%> type="number" id=<%="telefono_"+l.getCodigo()%> ></td>
					<td><input type="button" value="Guardar" onclick=<%="sendUpdate("+l.getCodigo()+")" %> /></td>
					<td><input type="button" value="Eliminar" onclick=<%="sendDelete("+l.getCodigo()+")" %> /></td>
				</tr>
		<%}%>
	</tbody>
</table>


<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script type="text/javascript">
	function sendUpdate(cod) {
		newName=document.getElementById("name_"+cod).value;
		newEmail=document.getElementById("email_"+cod).value;
		newTel=document.getElementById("telefono_"+cod).value;
	
		axios(
			{
				url: '/lafarmacia/LaboratorioABMC/update',
				method:"post",
				params:{
					cod_lab: cod,
					name_lab: newName,
					email_lab: newEmail,
					telefono_lab: newTel
					
				}
			}		
		)
		.then((res)=>location.reload())
		.catch((err)=>{ console.log(err.response.data); alert("ups... algo salio mal") })
		
	}

	function sendDelete(cod) {
		if(confirm("Seguro que desea borrar el registro?")===false){
			return;	
		}
		axios(
				{
					url: '/lafarmacia/LaboratorioABMC/delete',
					method:"post",
					params:{
						cod_lab: cod,
					}
				}		
			)
			.then((res)=>location.reload())
			.catch((err)=>{ console.log(err.response.data); alert("ups... algo salio mal") })
			
	}
</script>


</body>
</html>