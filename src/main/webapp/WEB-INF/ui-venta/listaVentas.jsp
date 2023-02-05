<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.LinkedList"%>
    <%@page import="entities.Venta"%>
    <%@page import="entities.ObraSocial"%>
    <%@page import="entities.Cliente"%>
     <%@  page import="java.time.LocalDate"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300&display=swap" rel="stylesheet">
<link href="../style/sharedStyle.css" rel="stylesheet" type="text/css">
<link href="../style/listaVentas.css" rel="stylesheet" type="text/css">
<title>Ventas</title>
	<%	
  		
  		LinkedList<Venta> ventas= (LinkedList<Venta>)request.getAttribute("ventas");
  		ObraSocial os = (ObraSocial)request.getAttribute("os");
  		LocalDate fechaDesde = (LocalDate)request.getAttribute("fechaDesde");
  		LocalDate fechaHasta = (LocalDate)request.getAttribute("fechaHasta");
  		
	%>
</head>
<body>
	<main>
	<div class="container">
		<div class="row">
			<div class="nav-bar " >
				<div class="col" >
					<h4 class="prueba">Obra Social: <%=os.getNombre()%> </h4>
        			<h4 class="prueba">Ventas desde <%=fechaDesde.format(Cliente.dFormat)%> hasta <%=fechaHasta.format(Cliente.dFormat)%></h4>
        		</div> 
		<div class="col-12 col-sm-12 col-lg-12">
	                	<div class="table-responsive">
	                    	<table class="table">
	                    		<thead class="t-head">
	                    			<tr>
	                        			<th>Numero Venta</th>
	                    		    	<th>Fecha Venta</th>
	                        			<th>Total</th>
	                        			<th>Cliente</th>
	                      			</tr>
	                      		</thead>
	                    		<tbody>
	                    		<% 
	                    		for (Venta v : ventas) { %>
	                    			<tr>
	                    				<td>
	                    					<a  class="linkVenta btn btn-sm" href="http://localhost:8080/lafarmacia/venta/detalle?nro=<%=v.getNroVenta()%>">
	                    						<%=v.getNroVenta()%>
	                    					</a>
	                    					</td>
	                       				<td><%=v.getFechaVenta().format(Cliente.dFormat)%></td>
	                    				<td>$<%=v.getTotalDB()%></td>
	                    				<% if(v.getCliente()!=null){%>
	                    					<td><%=v.getCliente().getFullName()%></td>
	                    				<% }
	                    				else { %>
	                    					<td>Particular</td>
	                    				<%}%>
	                    				
	                    				
	                    				
	                    				    				
	                    			</tr>
	                    		<% } %>
	                    		</tbody>
	                    	</table>
						</div>
					</div>
					<a href="../Redirect" class="btn btn-success btn-lg btn-block w-25"> Volver al menu</a>
				 </div>
			</div>
			</div>
	</main>

</body>
</html>