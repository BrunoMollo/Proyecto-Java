<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="entities.ObraSocial"%>
<%@page import="entities.Usuario"%>
<%@page import="entities.Cliente"%>
<%@page import="logic.CtrlObraSocial"%>
<%@page import="logic.CtrlCliente"%>
<%@page import="java.util.LinkedList"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300&display=swap" rel="stylesheet">
	<link href="../style/addCliente.css" rel="stylesheet" type="text/css">
	<link href="../style/sharedStyle.css" rel="stylesheet" type="text/css">
	<script defer src="../js/apiLocalidades.js"></script>
	<title>Actualiza Cliente</title>
	<%	
		LinkedList<ObraSocial> obraSociales = (LinkedList<ObraSocial>) request.getAttribute("listOS");
		Cliente c = (Cliente) request.getAttribute("cliente");
	%>

</head>

<body>
	
	<main>
		<form class="signup-form" action="update" method="post">
		  	<h2>Actualizar Cliente</h2><br>
			<div class="form-group fieldset">
					<div class="row">
 						<div class="col">
  							<label class="col-md-0 control-label">Nombre</label>      
 							<input  name="nombre"class="form-control" value= "<%=c.getNombre()%> "type="text">
    					</div>
    					<div class="col">
			  				<label class="col-md-0 control-label">Apellido</label>      
			 				<input  name="apellido"class="form-control" value="<%=c.getApellido()%>" type="text">
    					</div>
  					</div>
			</div>
			<div class="form-group fieldset">
				<div class="row">
		 			<div class="col">
		  				<label class="col-md-0 control-label" >DNI</label>      
		 				<input  name="dni"class="form-control" value="<%=c.getDni()%>" type="text" required>
		    		</div>
		    		<div class="col">
		  				<label class="col-md-0 control-label">Fecha Nacimiento</label>      
		 				<input  name="fechaNac"class="form-control" value="<%=c.getFechaNacimiento().format(Cliente.dFormat)%>" type="text" required required pattern="\d{2}/\d{2}/\d{4}" >
		    		</div>
		  		</div>
			</div>
			<div class="form-group fieldset">
		  		<div class="row">
		    		<div class="col">
		  				<label class="col-md-0 control-label">Provincia</label>       		 			   
 						<select class="form-control" name="provincia"  id="selProvincia" required >
 							<option selected value="<%=c.getProvincia()%>"><%=c.getProvincia()%> </option>
 						</select>
		    		</div>
		  		</div>
				<div class="row">
		    		<div class="col">
		  				<label class="col-md-0 control-label">Localidad</label>      
		 				<select class="form-control" name="localidad"  id="selLocalidad" required >
 							<option selected value="<%=c.getLocalidad()%>"><%=c.getLocalidad()%></option>
 						</select>
		    		</div>
		  		</div>
		  		<div class="row">
		    		<div class="col">
		  				<label class="col-md-0 control-label">Direccion</label>      
		 				<input  name="direccion" class="form-control" value="<%=c.getDireccion()%>" type="text" required>   
		    		</div>
		  		</div>
		  		
			</div>
			<div class="form-group fieldset">
				<div class="row">
					<div class="col">
		 				<label class="col-md-0 control-label">Email</label>      
		 				<input  name="email" value="<%=c.getEmail()%>" class="form-control"  type="email" required> 
		 				
		 			</div>
		 		</div>
		 		<div class="row">
		 			<div class="col">
		 				<label class="col-md-0 control-label">Telefono</label>      
		 				<input  name="telefono" value="<%=c.getTelefono()%>" class="form-control"  type="text" required> 
		 			</div>
		 		</div>
			</div>
			<div class="form-group fieldset">
				<div class="row">
					<div class="col">
		 				<label class="col-md-0 control-label">Obra Social</label>      		 			   
 						<select class="form-control" name="id_os"  id="cli_os" required >
      						<option value="<%=c.getObraSocial().getId()%>"><%=c.getObraSocial().getNombre()%></option>
 				<% for(ObraSocial os : obraSociales){ %>
		  			<option value="<%=os.getId()%>"><%=os.getNombre()%></option>
		  
					<%} %>  
						</select>
					</div>
		 		</div>
			</div>
			

 	  		<div class="form-group">
 	  			<div class="row">
 	  				<div class="col">
    					<a class="btn btn-success btn-lg " href="../Redirect">Volver al menu</a>
    				</div>
 	  				<div class="col">
    					<button type="submit" class="btn btn-warning" >Actualizar </button>
    				</div>
    				
    			</div>
 			</div>
  	</form>
</main>

</body>
</html>