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
<%Usuario u = (Usuario) session.getAttribute("user"); %>
</head>
<body>
	<header>
  		<div class="user-container">
  			<ul>
  				<li>											
	  				<a class="nav-link dropdown-toggle btn" href="#" role="button" data-bs-toggle="dropdown" >
	  				  <span class="user-icon">
  						<img style="width:20px" src="./assets/user-icon.png">
  					  </span>			
				      <span id="show-user-name"><%=u.getUsuario()%></span>   
				    </a>
		      		  <ul class="dropdown-menu">
				            <li class="dropdown-item"><a href="logout">Log out</a></li>
			      	  </ul>
			 	</li>
			</ul>
   		</div>  
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
				<li><a class="btn btn-sm" href="ABMC-medicamento/redirectUpdMed">Modificar datos Medicamento</a></li> 
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
					<li><a class="btn btn-sm" href="DescargarListadoVentas">Descargar listado de medicamentos mas vendidos del mes</a></li>
					<li><a class="btn btn-sm" href="venta/listaVentas">Lista de ventas por Obra Social</a></li>	
					<li>
					
					<style>
    					.sending:after {content: "  [procesando...]";}
    					.done:after {content: "  [Enviado]";}	
    					.fail:after {content: "  [ups... algo salio mal]";}	
					</style>
						<button id='enviarMail' class="btn btn-sm" 
						onclick="
						const btn=document.getElementById('enviarMail');
						btn.classList.add('sending');	
						fetch('/lafarmacia/EmailObrasSociales')
						.then((res)=>{
							if(res.status>=500){
								btn.classList.add('fail');
								return
							}
				
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
				