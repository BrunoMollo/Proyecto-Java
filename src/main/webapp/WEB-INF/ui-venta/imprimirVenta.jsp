<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="entities.Usuario"%>
<%@page import="entities.LineaVenta"%>
<%@page import="java.util.LinkedList"%>
<%@page import="entities.Venta"%>
<%@page import="entities.ObraSocial"%>
<%@page import="entities.Cliente"%>
<%@page import="logic.CtrlVenta"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Imprimir Venta</title>
		<%	
		Usuario u = (Usuario) session.getAttribute("user");
  		Cliente c = (Cliente) session.getAttribute("cliente");
  		ObraSocial os = c!=null?c.getObraSocial():null;
  		Venta v = ((CtrlVenta) session.getAttribute("CtrlVenta")).getVenta();
  		LinkedList<LineaVenta> lineasVenta = v.getLineas();
		%>
	</head>
	<body onload="window.print()">
		<label>Fecha y Hora: </label><%=v.getFechaVenta()%>
		<hr>
		<label>Cliente: </label><%=c!=null?c.getFullName():"PARTICULAR"%><br>
		<label>DNI: </label><%=c!=null?c.getDni():"-"%><br>
		<label>Obra Social: </label><%=v.getNroReceta()!=null?os.getNombre():"-"%><br>
		<label>Descuento: <%=os!=null?os.getDescuento():"-"%>%</label>
		<hr>
		<table>
			<thead>
				<tr>
					<th>Medicamento |	</th>
					<th>Precio Unidad |	</th>		
					<th>Cantidad |	</th>
					<th>Obra Social |	</th>
					<th>Subtotal Cliente	</th>
					
				</tr>
			</thead>
			<tbody>
				<%for(LineaVenta l : lineasVenta){%>
					<tr>
						<td><%=l.getMedicamento().getNombre()%></td>
						<td><%=l.getPrecioUnidad()%></td>
						<td><%=l.getCantidad()%></td>
						<td><%=String.format("%.2f", l.getSubTotal() * (os!=null?os.getDescuento()/100:0))%></td>
						<td><%=String.format("%.2f", l.getSubTotal() * (os!=null?1-os.getDescuento()/100:1))%></td>
						
					</tr>
				<%}%>
			</tbody>
		</table>
		<hr>
		<label><strong>Total: </strong></label> <%=String.format("%.2f", v.getTotal())%>
		<label>Cajero/a: </label><%=u.getNombre()%>
		<p><strong>Gracias por su compra!</strong></p>
		<script type="text/javascript">setTimeout(()=>window.location.replace("../Redirect"),3000)</script>
	</body>
</html>