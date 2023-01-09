<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="entities.Usuario" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>La farmacia</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
<link rel="stylesheet" href="style/home.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300&display=swap" rel="stylesheet">
<link href="./style/sharedStyle.css" rel="stylesheet" type="text/css">
<script defer src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>

</head>
<body>
	<header>
	<%Usuario u = (Usuario) session.getAttribute("user"); %>
		<div class="show-user">
			<span id="show-user-name">USER: <%=u.getUsuario() %></span> 
			<br>
			<a href="logout	">Log out</a>
		</div>
	</header>
   
      
    <section class="options">
   
		<div class="option-list">
			<h2>Clientes</h2>
			<ul>
					<li><a class="btn btn-sm" href="ABMC-cliente/new">Nuevo Cliente</a></li>
					<li><a  class="btn btn-sm" href="ABMC-cliente/all">Lista Cientes</a></li>
			</ul>
		</div>
			<div class="option-list">
			<h2>Venta</h2>
			<ul>
					<li><a class="btn btn-sm" href="venta/iniciarVentaLibre">Venta Particular</a></li>
					<li><a class="btn btn-sm" href="venta/iniciarVentaOS">Venta por Obra Social</a></li>
				
			</ul>
		</div>
	</section>
	
	

</body>
</html>