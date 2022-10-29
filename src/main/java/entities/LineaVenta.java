package entities;

public class LineaVenta {
	
	private Medicamento medicamento;
	private Integer cantidad;
	private Double precioUnidad;
	
	
	public LineaVenta(){}
	
	
	public LineaVenta(Medicamento med, Integer cant) {
		this.medicamento=med;
		precioUnidad=med.getPrecio(); //seria le ultimo
		
		this.cantidad=cant;
	}
	
	public Double getSubTotal() {
		return cantidad*precioUnidad;
	}
	
	public void increaseQuantity(Integer delta) {
		this.cantidad+=delta;
	}
	
	public void decreaseQuantity(Integer delta) {
		this.cantidad-=delta;
	}
	
	
	public Medicamento getMediacamento() {
		return medicamento;
	}
	public void setMediacamento(Medicamento mediacamento) {
		this.medicamento = mediacamento;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	public Double getPrecioUnidad() {
		return precioUnidad;
	}
	public void setPrecioUnidad(Double precioUnidad) {
		this.precioUnidad = precioUnidad;
	}
}
