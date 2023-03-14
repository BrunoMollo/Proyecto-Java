<%@page import="entities.Dosis"%>
<%@page import="entities.Medicamento"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>La farmacia</title>
	<link href="../style/sharedStyle.css" rel="stylesheet" type="text/css">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
	<script defer src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>
	
	<%
		Medicamento med= (Medicamento)request.getSession().getAttribute("medicamento");
	%>
</head>
<body>
<div class="p-3">

	<h1>Datos del medicamento</h1>
		<div>Codigo: <%=med.getCodigoBarra() %> </div>
		<div>Nombre: <%=med.getNombre() %> </div>
		<div>Laboratorio: <%=med.getLaboratorio().getNombre() %> </div>
		<div>Tamaño: <%=med.getSize()+" "+med.getUnidad()%> </div>
		<div>Precio: <%=med.getPrecio()%> </div>
		<h2>Dosis</h2>
		<table>
			<thead>
				<tr>
					<th>Droga</th> 
					<th>Cantidad</th>	
				</tr>
			</thead>
			<tbody>
				<%for (Dosis dose : med.getAllDosis().values()) {%>
				<tr>
					<td><%=dose.getDroga().getNombre()%></td>
					<td><%=dose.getCant()+dose.getUnidad()%></td>
				</tr>
				<%} %>
			</tbody>
		</table>
		<br>		
		<a href="../index.html" class="w-20 mt-1 mb-3 mr-2 btn btn-success btn-lg">Volver al menu</a>
		
</div>
</body>
</html>