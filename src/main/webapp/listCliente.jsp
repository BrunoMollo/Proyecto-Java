<%@ page import="java.util.LinkedList" %>
<%@ page import="entities.Cliente" %>
<%@ page import="entities.Usuario" %>
<%@ page import="logic.CtrlCliente" %>
<%@ page import="logic.CtrlLogin" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@  page import="java.time.LocalDate"%>
 <%@  page import= "java.time.format.DateTimeFormatter"%>
<!DOCTYPE>
<html >
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Listado Clientes</title>
	
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300&display=swap" rel="stylesheet">
	<link href="./style/sharedStyle.css" rel="stylesheet" type="text/css">
	<script defer src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
	
	<script defer src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
	<script defer src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>
	    
    <%	
 // Usuario u = (Usuario)  session.getAttribute("user");
    	CtrlCliente ctrl = new CtrlCliente();
    	CtrlLogin login = new CtrlLogin();
    	Usuario user = new Usuario();
    	LinkedList<Cliente> clientes= ctrl.getAll();
    	
		
    	
	%>
	
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="nav-bar " >
				<div class="col" >
        			<h4 id="prueba">Alumnos</h4>
        		</div>
        		<div class="col" >
        			<a href="./index.html" class="btn btn-success "> <h4>Volver al Menu</h4></a>
        		</div>
        		
        	</div>
            	<div class="col-12 col-sm-12 col-lg-12">
                	<div class="table-responsive">
                    	<table class="table">
                    		<thead>
                    			<tr>
                    				<th>DNI</th>
                    		    	<th>Nombre</th>
                        			<th>Apellido</th>
                        			<th>Fecha Nacimiento</th>
                        			<th>Telefono</th>
                        			<th>Provincia</th>
                        			<th>Localidad</th>
                                 	<th>Obra Social</th>
                        			
                      			</tr>
                      		</thead>
                    		<tbody>
                    		<% for (Cliente cli : clientes) { %>
                    			<tr>
                    				<td><%=cli.getDni()%></td>
                       				<td><%=cli.getNombre()%></td>
                    				<td><%=cli.getApellido()%></td>
                    				<td><%=cli.getFechaNacimiento().format(Cliente.dFormat)%></td>
                    				<td><%=cli.getTelefono()%></td>
                    				<td><%=cli.getProvincia()%></td>
                    				<td><%=cli.getLocalidad()%></td>
                    				<td><%=cli.getObraSocial().getNombre()%></td>
                    			
                    				
                    				<td>
                    					<div class= "btn-edit">
                    						<form action="" method="post">
                    							<input type="hidden" class="custom-control-input" id="edit-control" name="dni" value="<%=cli.getDni()%>">
												<input class="btn btn-success btn-lg btn-block" type="submit" value="Modificar"> 
											</form>
										</div>	
                    				</td>
                    				<td>
                    					<div class= "btn-edit">
                    				 		<form action="" method="post">
                    							<input type="hidden" class="custom-control-input" id="delete-control" name="dni" value="<%=cli.getDni()%>">
												<input class="btn btn-success btn-lg btn-block" type="submit" value="Eliminar"> 
											</form>
                    					</div>	
                    				</td><!-- borrar -->
                    			</tr>
                    		<% } %>
                    		</tbody>
                    		</table>
		</div>
			</div>
			  </div>
				</div>
	 <!-- /container -->
	 <script defer src="js/getUserList.js"></script>
</body>
</html>
