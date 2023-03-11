<%@page import="entities.Medicamento"%>
<%@page import="entities.Usuario"%>
<%@page import="logic.CtrlMedicamento"%>
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
		LinkedList<Medicamento> arr =  (LinkedList<Medicamento>)request.getAttribute("all");
	
		%>
</head>
<body>
<header class="head-list">
		<h1 class="head-list">Medicamentos</h1>
		<a href="../index.html" class="btn btn-lg btn-primary btn-c">Volver al menu</a>
	</header>

<table class="table" >
	<thead>
		<tr>
			<th>Codigo</th>
			<th>Nombre</th>
			<th>Precio</th>
			<th></th>
		</tr>
	</thead>
	<tbody>
		<% for(Medicamento m: arr){ %>
				<tr>
					<td><%=m.getCodigoBarra()%> </td>
					<td><input value="<%=m.getNombre()%>" type="text" ></td>
					<td><input value="<%=m.getPrecio()%>" type="text" ></td>
					
				</tr>
		<%}%>
	</tbody>
</table> -

<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script type="text/javascript">
	function sendUpdate(cod) {
		newName=document.getElementById("name_"+cod).value;
	
		axios(
			{
				url: '../ABMC-droga/update',
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
					url: '../ABMC-droga/delete',
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