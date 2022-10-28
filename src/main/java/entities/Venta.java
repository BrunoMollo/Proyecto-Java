package entities;

import java.time.LocalDateTime;

public class Venta {
	private Integer nroVenta;
	private LocalDateTime fechaVenta;
	private Double total;
	private Integer dniCliente;
	private Integer nroReceta;
	
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
	public Integer getDniCliente() {
		return dniCliente;
	}
	public void setDniCliente(Integer dniCliente) {
		this.dniCliente = dniCliente;
	}
	public Integer getNroReceta() {
		return nroReceta;
	}
	public void setNroReceta(Integer nroReceta) {
		this.nroReceta = nroReceta;
	}
		
}
