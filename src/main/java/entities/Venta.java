package entities;

import java.time.LocalDateTime;
import java.util.HashMap;

public class Venta {
	private Integer nroVenta;
	private LocalDateTime fechaVenta;
	private Double total;
	private Cliente cliente;
	private Integer nroReceta;
	private HashMap<Integer, LineaVenta> lineas; //la key es el codigo de barras
	
	
	public void addMedicamento(Medicamento med, Integer cantidad) {
		Integer cod=med.getCodigoBarra();
		
		if(lineas.containsKey(cod)) {
			lineas.get(cod).increaseQuantity(cantidad);
		}
		else {
			lineas.put(cod, new LineaVenta(med, cantidad) );
		}	
	}
	
	
	public void removeMedicamento(Medicamento med, Integer cantidad) {
		Integer cod=med.getCodigoBarra();
		if(lineas.containsKey(cod)) {
			lineas.get(cod).decreaseQuantity(cantidad);
			
			if(lineas.get(cod).getCantidad()<=0) {
				lineas.remove(cod);
			}
		}
		
	}
	
	
	public Integer getNroVenta() {
		return nroVenta;
	}
	public void setNroVenta(Integer nroVenta) {
		this.nroVenta = nroVenta;
	}
	public LocalDateTime getFechaVenta() {
		return fechaVenta;
	}
	public void setFechaVenta(LocalDateTime fechaVenta) {
		this.fechaVenta = fechaVenta;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public Cliente getCliente() {
		return this.cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Integer getNroReceta() {
		return nroReceta;
	}
	public void setNroReceta(Integer nroReceta) {
		this.nroReceta = nroReceta;
	}
		
}
