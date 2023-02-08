<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@page import="entities.ObraSocial"%>
<%@page import="logic.CtrlObraSocial"%>
<%@page import="java.util.LinkedList"%>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300&display=swap" rel="stylesheet">
<link href="../style/sharedStyle.css" rel="stylesheet" type="text/css">
<link href="../style/busquedaVentas.css" rel="stylesheet" type="text/css">
<title>Ventas</title>

<%	
	LinkedList<ObraSocial> obraSociales = (LinkedList<ObraSocial>) request.getAttribute("listOS");
%>
</head>
<body>
	<form class="form-group search" action="OSSales" method="post">
		<div class="form-group ">
			<div class="row">
				<div class="col">
	 				<label class="col-md-0 control-label">Obra Social</label>      		 			   
						<select class="form-control" name="id_os"  id="cli_os" required >
	    						<option class="col-md-0 control-label" disabled selected>Eliga una Obra Social</option>
				<% for(ObraSocial os : obraSociales){ %>
	  			<option class="col-md-0 control-label" value="<%=os.getId()%>"><%=os.getNombre()%></option>
	  
				<%} %>  
					</select>
				</div>
	 		</div>
	 		<div class="row" style="margin-top:10px">
	 			<div class="col">
					<label class="col-md-0 control-label">Fecha Desde</label>
					<input name="fechaDesde"  placeholder="dd/mm/aaaa" type="text"> 			
	 			</div>
	 			<div class="col" style="margin-left:22px">
					<label class="col-md-0 control-label">Fecha Hasta</label>
					<input name="fechaHasta"  placeholder="dd/mm/aaaa" type="text"> 			
	 			</div>
	 		</div>
		</div>
		<div class="form-group">
 	  			<div class="row">
 	  				<div class="col">
    					<a class="btn btn-warning btn-lg " style="height:38px; width:140px" href="../index">Volver al menu</a>
    				</div>
 	  				<div class="col">
    					<button type="submit" class="btn btn-success" >Buscar </button>
    				</div>  				
    			</div>
 			</div>
	</form>

</body>
</html>