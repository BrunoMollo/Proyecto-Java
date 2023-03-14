<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="entities.Usuario" %>
<%@ page import="entities.LineaVenta" %>
<%@ page import="logic.CtrlVenta" %>
<%@ page import="entities.Venta" %>
<%@ page import="java.util.LinkedList" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300&display=swap" rel="stylesheet">
<link href="../style/sharedStyle.css" rel="stylesheet" type="text/css">
<link href="../style/asyncRequest.css " rel="stylesheet" type="text/css">
<script defer src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script defer src="../js/asyncRequest.js"></script>

<title>Venta</title>

  	<%	
  		Usuario u = (Usuario) session.getAttribute("user");
  		Venta v = ((CtrlVenta) session.getAttribute("CtrlVenta")).getVenta();
  		LinkedList<LineaVenta> lineasVenta;
  		Boolean medEncontrado= (Boolean)request.getAttribute("medEncontrado");
	%>
</head>
<body>
	<header class="head-list">
		<h1 class="title-list">Nueva Venta</h1>
		<a href="../index" class="btn btn-lg btn-primary btn-c">Volver al menu</a>
	</header>
	<main>
		<form class="form-med" action="addMedicamento" method="post">
			<div class="form-group ">
					<div class="row">
 						<div class="col async-search" url="../ABMC-medicamento/getbyname" searchParameter="name_med">
  							<label class="col-md-0 control-label">Nombre Medicamento</label>      
 							<input id="asyncInput" style="width:49.1%" name="name_med"class="form-control" placeholder="Nombre Medicamento" type="text" autocomplete="off" required>
 							<ul id="sugerencias"></ul>	
    					</div>
    				</div>
    				<div class="row">	
    					<div class="col async-search">
			  				<label class="col-md-0 control-label">Cantidad</label>      
			 				<input  name="cantidad"class="form-control" placeholder="Cantidad" type="number" value="1">
    					</div>
    					<div class="col">
			  				<button class="btn btn-lg mt-4 btn-primary btn-s type="submit">Agregar</button>
    					</div>
    				</div>
			</div>
		</form>
		<div class="table-responsive">
                    	<table class="table">
                    		<thead class="t-head">
                    			<tr>
                    				<th>Medicamento</th>
                    		    	<th>Cantidad</th>
                        			<th>Precio Unitario</th>      
                        			<th>Subtotal</th>                   			
                      			</tr>
                      		</thead>
                    		<tbody>
                    		<% 
                
 
                    		for (LineaVenta lv : v.getLineas()) { %>
                    			<tr>
                    				<td><%=lv.getMedicamento().getNombre()%></td>
                       				<td><%=lv.getCantidad()%></td>
                    				<td>$<%=lv.getPrecioUnidad()%></td>
                    				<td>$<%=lv.getSubTotal() %></td>
                    			</tr>
                    		<% } %>								
	                    		<tr style="background:transparent;color:red;">
	                    			<td colspan="1">Total: $<%=v.getTotal() %> </td>
	                    		</tr>
                    		</tbody>
                    		</table>
		</div>
		
		<div>
                 <form action="cerrarVenta" method="post">
						<input type="submit" class="btn btn-lg btn-primary btn-s" style="position:relative;left:5%;" value="Realizar Venta"> 
				</form>
        </div>	
 	</main>
 	<%if(medEncontrado!=null && medEncontrado==false){ %>
 	<script type="text/javascript">
 		setTimeout(()=>alert("No existe el medicamento"), 1)
 	</script>
 	<% } %>
</body>
</html>