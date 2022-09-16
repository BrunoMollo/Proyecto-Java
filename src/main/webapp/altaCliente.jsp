<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="entities.ObraSocial"%>
<%@page import="entities.Usuario"%>
<%@page import="logic.CtrlObraSocial"%>
<%@page import="java.util.LinkedList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta lang="es">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300&display=swap" rel="stylesheet">
<link href="./style/addCliente.css" rel="stylesheet" type="text/css">
<link href="./style/sharedStyle.css" rel="stylesheet" type="text/css">
<%	
	LinkedList<ObraSocial> obraSociales = new CtrlObraSocial().getAll();
	Usuario u= (Usuario)request.getSession().getAttribute("user");
%>
<title>Nuevo Cliente</title>
</head>

<body>
	
	<main>
		<form class="signup-form" action="./ABMC-cliente/add" method="post">
		  	<h2 >Nuevo Cliente <%=u.getNombre()%></h2>
			<div class="form-group fieldset">
					<div class="row">
 						<div class="col">
  							<label class="col-md-0 control-label">Nombre</label>      
 							<input  name="nombre"class="form-control" placeholder="Nombre" type="text">
    					</div>
    					<div class="col">
			  				<label class="col-md-0 control-label">Apellido</label>      
			 				<input  name="apellido"class="form-control" placeholder="Apellido" type="text">
    					</div>
  					</div>
			</div>
			<div class="form-group fieldset">
				<div class="row">
		 			<div class="col">
		  				<label class="col-md-0 control-label" >DNI</label>      
		 				<input  name="dni"class="form-control" placeholder="DNI"  type="text" required>
		    		</div>
		    		<div class="col">
		  				<label class="col-md-0 control-label">Fecha Nacimiento</label>      
		 				<input  name="fechaNac"class="form-control" placeholder="dd/mm/aaaa" type="text" required required pattern="\d{2}/\d{2}/\d{4}" >
		    		</div>
		  		</div>
			</div>
			<div class="form-group fieldset">
		  		<div class="row">
		    		<div class="col">
		  				<label class="col-md-0 control-label">Provincia</label>       		 			   
 						<select class="form-control" name="provincia"  id="selProvincia" required >
 							<option>Eliga una provincia</option>
 						</select>
		    		</div>
		  		</div>
				<div class="row">
		    		<div class="col">
		  				<label class="col-md-0 control-label">Localidad</label>      
		 				<select class="form-control" name="localidad"  id="selLocalidad" required >
 							<option>Eliga una localidad</option>
 						</select>
		    		</div>
		  		</div>
		  		<div class="row">
		    		<div class="col">
		  				<label class="col-md-0 control-label">Direccion</label>      
		 				<input  name="direccion"class="form-control" placeholder="Direccion" class="form-control"  type="text" required>   
		    		</div>
		  		</div>
		  		
			</div>
			<div class="form-group fieldset">
				<div class="row">
					<div class="col">
		 				<label class="col-md-0 control-label">Email</label>      
		 				<input  name="email"class="form-control" placeholder="Correo Electronico" class="form-control"  type="email" required> 
		 			</div>
		 		</div>
		 		<div class="row">
		 			<div class="col">
		 				<label class="col-md-0 control-label">Telefono</label>      
		 				<input  name="telefono"class="form-control" placeholder="Telefono" class="form-control"  type="text" required> 
		 			</div>
		 		</div>
			</div>
			<div class="form-group fieldset">
				<div class="row">
					<div class="col">
		 				<label class="col-md-0 control-label">Obra Social</label>      		 			   
 						<select class="form-control" name="id_os"  id="cli_os" required >
      						<option disabled selected>Eliga una Obra Social</option>
 				<% for(ObraSocial os : obraSociales){ %>
		  			<option value="<%=os.getId()%>"><%=os.getNombre()%></option>
		  
					<%} %>  
						</select>
					</div>
		 		</div>
			</div>
			

 	  		<div class="form-group">  
    			<button type="submit" class="btn btn-warning" >Agregar </button>
 			</div>
  	</form>
</main>
<script src="./js/apiLocalidades.js"></script>
</body>
</html>