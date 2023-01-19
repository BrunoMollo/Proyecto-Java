<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="entities.Usuario" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>La farmacia</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
<link rel="stylesheet" href="style/index.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300&display=swap" rel="stylesheet">
<link href="./style/sharedStyle.css" rel="stylesheet" type="text/css">
<script defer src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>

</head>


<body>
	<header>
	 <nav class="navbar navbar-expand-lg ">
  		<div class="container-fluid ">
    		<div class="collapse navbar-collapse " id="navbarNav">
	      		<ul class="navbar-nav">
			        <li class="nav-item dropdown">
			          <a class="nav-link dropdown-toggle btn btn-sm" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
			           Medicamentos
			          </a>
			          <ul class="dropdown-menu">
			            <li><a class="dropdown-item" href="./altaMedicamento.html">Alta Medicamento</a></li>
			            <li><a class="dropdown-item" href="#">Actualizar Medicamento</a></li>
			            <li><a class="dropdown-item" href="#">Eliminar Medicamento</a></li>
			          </ul>
			        </li>
			         <li class="nav-item dropdown">
			          <a class="nav-link dropdown-toggle btn btn-sm" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
			           	Drogas
			          </a>
			          <ul class="dropdown-menu">
			            <li><a class="dropdown-item" href="./altaDroga.html">Alta Droga</a></li>
			            <li><a class="dropdown-item" href="ABMC-droga/all">Modificar datos droga</a></li>
			            <li><a class="dropdown-item" href="getDrogaByNameAsync.html">Busca por nombre droga</a></li>
			          </ul>
			        </li>
			         <li class="nav-item dropdown">
			          <a class="nav-link dropdown-toggle btn btn-sm" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
			            Clientes
			          </a>
			          <ul class="dropdown-menu">
			            <li><a class="dropdown-item"href="ABMC-cliente/">Nuevo Cliente</a></li>
			           	<li><a class="dropdown-item" href="">Actualizar cliente</a></li>
			            <li><a class="dropdown-item" href="ABMC-cliente/">Consulta cliente</a></li>
			            <li><a class="dropdown-item" href="ABMC-cliente/all">Lista clientes</a></li>
			          </ul>
			        </li>
			        <li class="nav-item dropdown">
			          <a class="nav-link dropdown-toggle btn btn-sm" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
			            Obra Sociales
			          </a>
			          <ul class="dropdown-menu">
			            <li><a class="dropdown-item" href="./altaObraSocial.html">Nuevo Obra Social</a></li>
			            <li><a class="dropdown-item" href="">Actualizar Obra Social</a></li>
			          </ul>
			        </li>
			        <li class="nav-item dropdown">
			          <a class="nav-link dropdown-toggle btn btn-sm" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
			            Laboratorios
			          </a>
			          <ul class="dropdown-menu">
			            <li><a class="dropdown-item" href="./altaLaboratorio.html">Nuevo Laboratorio</a></li>
			            <li><a class="dropdown-item" href="#">Actualizar Laboratorio</a></li>
			          </ul>
			        </li>
			        	<%Usuario u = (Usuario) session.getAttribute("user"); %>
						<li class="show-user nav-item">
							<span id="show-user-name">USER: <%=u.getUsuario() %></span> 
							<br>
							<a href="logout	">Log out</a>
						</li>
	      		</ul>
   			</div>
  		</div>
  		
  
	</nav>
	

</header>
   
    <section class="options">
		<div class="option-list">
			<h2>Drogas</h2>	
			<ul>
				<li><a class="btn btn-sm"href="ABMC-droga/redirectAddDroga">Alta droga</a></li>
				<li><a class="btn btn-sm" href="ABMC-droga/all">Modificar datos droga</a></li>
							
			</ul>
		</div>
		<div class="option-list">
			<h2>Obras Sociales</h2>
			<ul>
				<li><a class="btn btn-sm" href="ABMC-obrasocial/redirectAddOS">Alta Obra Social</a></li>
				<li><a class="btn btn-sm" href="ABMC-obrasocial/all">Modificar datos Obra Social</a></li>
			</ul>
		</div>
		<div class="option-list">
			<h2>Laboratorios</h2>
			<ul>
				<li><a class="btn btn-sm" href="ABMC-laboratorio/redirectAddLab">Alta Laboratorio</a></li>
				<li><a class="btn btn-sm" href="ABMC-laboratorio/all">Modificar datos Laboratorio</a></li>
			</ul>
		</div>
		<div class="option-list">
			<h2>Medicamentos</h2>
			<ul>
				<li><a class="btn btn-sm" href="ABMC-medicamento/redirectAddMed">Alta Medicamento</a></li>
				<li><a hidden class="btn btn-sm" href="#">Modificar datos Medicamento</a></li> 
				<!-- OJO QUE ACA LO PUSE COMO HIDDEN PQ NO USAMOS ESTE BOTON TODAVIA -->
				<li><a class="btn btn-sm" href="ABMC-medicamento/redirectNewPrecio">Modificar precio Medicamento</a></li>
			</ul>
		</div>
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
					<li><a class="btn btn-sm" href="DescargarListadoVentas">Descargar listado de ventas</a></li>
					
					<li>
					
					<style>
    					.sending:after {content: "  [procesando...]";}
    					.done:after {content: "  [DONE]";}	
    					.fail:after {content: "  [ups... algo salio mal]";}	
					</style>
						<button id='enviarMail' class="btn btn-sm" 
						onclick="
						const btn=document.getElementById('enviarMail');
						btn.classList.add('sending');	
						fetch('/lafarmacia/EmailObrasSociales')
						.then(()=>{
							btn.classList.remove('sending')
							btn.classList.add('done')
							setTimeout(()=>btn.classList.remove('done'), 1500)
						})
						.catch((err)=>{
							btn.classList.add('fail');
							console.log(err)
						})
						" 
						>Enviar mails a las Obras Sociales	
						</button>
					</li>
					
					
			</ul>
		</div>
	</section>
	
</body>
</html>
				