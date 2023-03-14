<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.LinkedList"%>
    <%@page import="entities.LineaVenta"%>
     <%@page import="entities.Venta"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300&display=swap" rel="stylesheet">
<link href="../style/sharedStyle.css" rel="stylesheet" type="text/css">
<link href="../style/asyncRequest.css " rel="stylesheet" type="text/css">
<title>Detalle venta</title>
<%		
  		LinkedList<LineaVenta> lventa= (LinkedList<LineaVenta>)request.getAttribute("lventa");
  		Venta v= (Venta)request.getAttribute("venta");
	%>
</head>
<body>
	<header class="head-list">
			<h1 class="title-list">Detalles</h1>
			<a href="../index" class="btn btn-lg btn-primary btn-c">Volver al menu</a>
	</header>
	<div class="container">
		<div class="row">
			<div class="nav-bar " >
				<div class="col" >
					<h4 style="margin-left:5%">Numero de Venta: <%=v.getNroVenta()%> </h4>
            	</div> 
		<div class="col-12 col-sm-12 col-lg-12">
	                	<div class="table-responsive">
	                    	<table class="table">
	                    		<thead class="t-head">
	                    			<tr>
	                        			<th>Medicamento</th>
	                    		 		<th>Unidad</th>
	                        			<th>Tamano</th>
	                        			<th>Cantidad</th>
	                        			<th>Precio Unitario</th>
	                      			</tr>
	                      		</thead>
	                    		<tbody>
	                    		<% 
	                    		for (LineaVenta lv : lventa) { %>
	                    			<tr>
	                    				<td><%=lv.getMedicamento().getNombre()%></td>
	                       				<td><%=lv.getMedicamento().getUnidad()%></td>
	                    				<td><%=lv.getMedicamento().getSize()%></td>
	                    				<td><%=lv.getCantidad()%></td>
	                    				<td>$<%=lv.getPrecioUnidad()%></td>               					                    				    				
	                    			</tr>
	                    			<%} %>
	                    		</tbody>
	                    	</table>
						</div>
					</div>
					<button style="margin-left:5%" class="btn btn-lg btn-primary btn-c" onclick="history.back()">Volver atras</button>
				 </div>
			</div>
			</div>
</body>
</html>