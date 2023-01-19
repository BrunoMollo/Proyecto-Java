package entities;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedList;

import ourLib.Parsers.Jsonable;
import ourLib.dbUtils.AutoGenerated;
import ourLib.dbUtils.HasAutogeneratedKey;

public class Venta implements HasAutogeneratedKey{
	
	@AutoGenerated
	private Integer nroVenta;
	
	private LocalDateTime fechaVenta;
	private Double total;
	private Cliente cliente;
	private Integer nroReceta;
	private HashMap<Integer, LineaVenta> lineas=new HashMap<Integer, LineaVenta>(); //la key es el codigo de barras
	private Usuario vendidoPor;
	
	
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
	
	public void calcularTotal() {
		this.total=0.;
		for(LineaVenta lv : lineas.values()) {
			total+=lv.getSubTotal();
		}
		if(nroReceta!=null) {
			Double desc = this.cliente.getObraSocial().getDescuento();
			this.setTotal(this.total*(1-desc/100));
		}
	}
	
	public LinkedList<LineaVenta> getLineas(){
		return new LinkedList<LineaVenta>(lineas.values());
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
		calcularTotal();
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

	public Usuario getVendidoPor() {
		return vendidoPor;
	}
	public void setVendidoPor(Usuario vendidoPor) {
		this.vendidoPor = vendidoPor;
	}
		
}
