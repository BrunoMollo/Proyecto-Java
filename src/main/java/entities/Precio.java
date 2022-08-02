package entities;

import java.time.LocalDate;


public class Precio {
	private LocalDate fecha;
	private Double monto;
	
	public Precio() {
		super();
	}
	
	public Precio(LocalDate fecha, Double monto) {
		this.fecha=fecha;
		this.monto=monto;
	}

	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	public Double getMonto() {
		return monto;
	}
	public void setMonto(Double monto) {
		this.monto = monto;
	}

}
