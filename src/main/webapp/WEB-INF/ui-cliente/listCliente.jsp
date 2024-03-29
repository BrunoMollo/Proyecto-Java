<%@ page import="java.util.LinkedList" %>
<%@ page import="entities.Cliente" %>
<%@ page import="entities.Usuario" %>
<%@ page import="logic.CtrlCliente" %>
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
	<link href="../style/sharedStyle.css" rel="stylesheet" type="text/css">
	<link href="../style/asyncRequest.css " rel="stylesheet" type="text/css">
	<script defer src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
	<script defer src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
	<script defer src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>
	    
    <%	
    	LinkedList<Cliente> clientes=(LinkedList<Cliente>)request.getAttribute("all");
	%>
	
</head>
<body>
	<header class="head-list">
		<h2 class="title-list">Clientes</h2>	
		<a href="../index" class="btn btn-lg btn-primary btn-c">Volver al menu</a>
	</header >
            	<div class="m-5 col-12 col-sm-12 col-lg-12">
                	<div class="table-responsive">
                    	<table class="table">
                    		<thead class="t-head">
                    			<tr>
                    				<th>DNI</th>
                    		    	<th>Nombre</th>
                        			<th>Apellido</th>
                        			<th>Fecha Nacimiento</th>
                        			<th>Telefono</th>
                        			<th>Provincia</th>
                        			<th>Localidad</th>
                                 	<th>Direccion</th>
                                 	<th>Obra Social</th>
                        			<th>Numero Afiliado</th>
                      			</tr>
                      		</thead>
                    		<tbody>
                    		<% 
                    		for (Cliente cli : clientes) { %>
                    			<tr>
                    				<td><%=cli.getDni()%></td>
                       				<td><%=cli.getNombre()%></td>
                    				<td><%=cli.getApellido()%></td>
                    				<td><%=cli.getFechaNacimiento().format(Cliente.dFormat)%></td>
                    				<td><%=cli.getTelefono()%></td>
                    				<td><%=cli.getProvincia()%></td>
                    				<td><%=cli.getLocalidad()%></td>
                    				<td><%=cli.getDireccion()%></td>
                    				<td><%=cli.getObraSocial().getNombre()%></td>
                    			    <td><%=cli.getNroAfiliado()%></td>              				
                    				<td>
                    					<div class= "btn-edit">
                    						<form action="redirectUpdate" method="post">
                    							<input type="hidden" class="custom-control-input" id="edit-control" name="dniCliente" value="<%=cli.getDni()%>">
												<input class="btn btn-lg btn-primary btn-s" type="submit" value="Modificar"> 
											</form>
										</div>	
                    				</td>
                    				<td>
                    					<div class= "btn-edit">
												<input class="btn btn-lg btn-primary btn-c" onclick=<%="sendDelete("+cli.getDni()+")"%> id="delete-control" type="submit" value="Eliminar"> 											
                    					</div>	
                    				</td>
                    			</tr>
                    		<% } %>
                    		</tbody>
                    	</table>
					</div>
				</div>
	 <!-- /container -->
	 
<script  type="text/javascript"	 >

	 function sendDelete(dni) {
			if(confirm("Seguro que desea borrar el registro?")===false){
				return;	
			}
			axios(
					{
						url: '../ABMC-cliente/delete',
						method:"post",
						params:{
							dniCliente: dni,
						}
					}		
				)
				.then((res)=>location.reload())
				.catch((err)=>{ console.log(err.response.data); alert("ups... algo salio mal") })
				
		}
	 </script>
	 
</body>
</html>
