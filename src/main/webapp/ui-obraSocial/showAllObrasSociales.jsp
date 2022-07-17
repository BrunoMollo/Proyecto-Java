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
	LinkedList<ObraSocial> arr = (LinkedList<ObraSocial>)request.getAttribute("all");
	%>
</head>
<body>
<h2>Obras Sociales</h2>

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
				<tr>
					<td><%=os.getId() %> </td>
					<td><input value=<%=os.getNombre() %> type="text" id=<%="name-"+os.getId()%>></td>
					<td><input value=<%=os.getTelefono() %> type="number" id=<%="tel-"+os.getId()%>></td>
					<td><input value=<%=os.getEmail() %> type="email" id=<%="email-"+os.getId()%>></td>
					<td><input value=<%=os.getDescuento() %> type="number" step="0.001" min="0" max="100" id=<%="discount-"+os.getId()%>></td>
					<td><input type="button" value="Guardar" onclick=<%="updateObraSocial("+os.getId()+")" %> id="btn-update"/></td>
					<td><input type="button" value="Eliminar" onclick=<%="deleteObraSocial("+os.getId()+")" %> id="btn-delete"/></td>
				</tr>
		<%}%>
	</tbody>
</table>


<form hidden="true" id="form" action="/lafarmacia/ABMC-obrasocial/update" method="post" >
	<input name="id_os">
	<input name="name_os">
	<input name="phone_os">
	<input name="email_os">
	<input name="discount_os">
</form>

<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script type="text/javascript">
	function updateObraSocial(cod){
		document.querySelector("[name=id_os]").value=cod;
		document.querySelector("[name=name_os]").value=document.getElementById(`name-`+cod).value;
		document.querySelector("[name=phone_os]").value=document.getElementById(`tel-`+cod).value ;
		document.querySelector("[name=email_os]").value=document.getElementById(`email-`+cod).value ;
		document.querySelector("[name=discount_os]").value=document.getElementById(`discount-`+cod).value ;
		
		let form=document.getElementById("form");
		form.action="/lafarmacia/ABMC-obrasocial/update";
		form.submit();
	}
</script>


<script type="text/javascript">
	function deleteObraSocial(cod){
		document.querySelector("[name=id_os]").value=cod;
		let form=document.getElementById("form");
		form.action="/lafarmacia/ABMC-obrasocial/delete";
		
		if(confirm("Seguro que desea borrar el registro?")){
			form.submit();	
		}
		
	}

</script>



</body>
</html>