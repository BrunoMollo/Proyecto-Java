<%@page import="entities.ObraSocial"%>
<%@page import="java.util.LinkedList"%>
<%@page import="entities.Usuario"%>
<%@page import="logic.CtrlObraSocial"%>
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
	<link href="../style/sharedStyle.css" rel="stylesheet" type="text/css">
	<title>Listado de drogas</title>
		
	<% 
	LinkedList<ObraSocial> arr = (LinkedList<ObraSocial>)request.getAttribute("all");
	%>
</head>
<body>
<header class="head-list">
	<h2 class="title-list">Obras Sociales</h2>
	<a href="../index.html" class="btn btn-lg btn-primary btn-c">Volver al menu</a>
</header>
<table class="table-responsive m-4" >
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
				<tr>
					<td><%=os.getId() %> </td>
					<td><input value="<%=os.getNombre().toString()%>" type="text" id=<%="name-"+os.getId()%>></td>
					<td><input value="<%=os.getTelefono() %>" type="number" id=<%="tel-"+os.getId()%>></td>
					<td><input value="<%=os.getEmail() %>" type="email" id=<%="email-"+os.getId()%>></td>
					<td><input value=<%=os.getDescuento() %> type="number" step="0.001" min="0" max="100" id=<%="discount-"+os.getId()%>></td>
					<td><input type="button" class="btn btn-primary btn-lg btn-s" onclick=<%="sendUpdate("+os.getId()+")" %> id="btn-update" value="Guardar"></td> 
					<td><input type="button" class="btn btn-primary btn-lg btn-c" onclick=<%="sendDelete("+os.getId()+")" %> id="btn-delete" value="Eliminar"></td>
				</tr>
		<%}%>
	</tbody>
</table>


<form hidden="true" id="form" action="../ABMC-obrasocial/update" method="post" >
	<input name="id_os">
	<input name="name_os">
	<input name="phone_os">
	<input name="email_os">
	<input name="discount_os">
</form>

<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script type="text/javascript">
function sendUpdate(cod) {
	let newName=document.getElementById(`name-`+cod).value;
	let newTel=document.getElementById(`tel-`+cod).value ;
	let newEmail=document.getElementById(`email-`+cod).value ;
	let newDisc=document.getElementById(`discount-`+cod).value ;

	axios(
		{
			url: '../ABMC-obrasocial/update',
			method:"post",
			params:{
				id_os: cod,
				name_os: newName,
				phone_os: newTel,
				email_os: newEmail,
				discount_os: newDisc
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
			url: '../ABMC-obrasocial/delete',
			method:"post",
			params:{
				id_os: cod
			}
		}		
	)
	.then((res)=> location.reload())
	.catch((err)=>{ console.log(err); alert("ups... algo salio mal") })
	
}


</script>



</body>
</html>