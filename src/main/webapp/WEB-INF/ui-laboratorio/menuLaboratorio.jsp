<%@page import="entities.Laboratorio"%>
<%@page import="java.util.LinkedList"%>
<%@ page import="entities.Usuario" %>
<%@ page import="logic.CtrlLaboratorio" %>
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
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300&display=swap" rel="stylesheet">
	<link href="../style/sharedStyle.css" rel="stylesheet" type="text/css">
	<meta charset="ISO-8859-1">
	<title>Listado de laboratorios</title>
	<% 
	LinkedList<Laboratorio> arr = (LinkedList<Laboratorio>)request.getAttribute("all");
	%>
</head>
<body>
<header class="head-list">
	<h2 class="title-list">Laboratorios</h2>
	<a href="../index.html" class="btn btn-lg btn-primary btn-c">Volver al menu</a>
</header>
<table class="table-responsive m-4" >
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
					<td><input value="<%=l.getNombre()%>" type="text" id=<%="name_"+l.getCodigo()%> ></td>
					<td><input value="<%=l.getEmail()%>" type="email" id=<%="email_"+l.getCodigo()%> ></td>
					<td><input value="<%=l.getTelefono()%>" type="number" id=<%="telefono_"+l.getCodigo()%> ></td>
					<td><input type="button" class="btn btn-primary btn-lg btn-s" onclick=<%="sendUpdate("+l.getCodigo()+")" %> id="btn-update" value="Guardar"></td> 
					<td><input type="button" class="btn btn-primary btn-lg btn-c" onclick=<%="sendDelete("+l.getCodigo()+")" %> id="btn-delete" value="Eliminar"></td>
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
				url: '/lafarmacia/ABMC-laboratorio/update',
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
					url: '/lafarmacia/ABMC-laboratorio/delete',
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