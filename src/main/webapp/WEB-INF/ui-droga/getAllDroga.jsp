<%@page import="entities.Droga"%>
<%@page import="entities.Usuario"%>
<%@page import="logic.CtrlDroga"%>
<%@page import="java.util.LinkedList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300&display=swap" rel="stylesheet">
	<link href="../style/sharedStyle.css" rel="stylesheet" type="text/css">
	<meta charset="ISO-8859-1">
	<title>Listado de drogas</title>
		<% 
		LinkedList<Droga> arr = (LinkedList<Droga>)request.getAttribute("all");
	
		%>
</head>
<body>
<header>
	<h2 class="d-inline ml-3">Drogas</h2>
	<a href="../index.html" class="w-20 float-right mt-1 mb-3 mr-2 btn btn-success btn-lg">Volver al menu</a>
</header>
<table class="table-responsive ml-5" >
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
					<td><input value="<%=d.getNombre()%>" type="text" id=<%="name_"+d.getCod()%>></td>
					<td><input type="button" class="w-15 m-3 ml-5 btn btn-success btn-lg" value="Guardar" onclick=<%="sendUpdate("+d.getCod()+")" %>></td> 
					<td><input type="button" class="w-15 m-3 ml-5 btn btn-success btn-lg" value="Eliminar" onclick=<%="sendDelete("+d.getCod()+")" %>></td> 
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

	function sendDelete(cod) {
		if(confirm("Seguro que desea borrar el registro?")===false){
			return;	
		}
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
			.catch((err)=>{ console.log(err); alert("ups... algo salio mal") })
			
	}
</script>

</body>
</html>