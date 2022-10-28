package entities;

public class LineaVenta {
	private Integer nroVenta;
	private Integer codBarra;
	private Integer cantidad;
	private Double precioUnidad;
	
	public Integer getNroVenta() {
		return nroVenta;
	}
	public void setNroVenta(Integer nroVenta) {
		this.nroVenta = nroVenta;
	}
	public Integer getCodBarra() {
		return codBarra;
	}
	public void setCodBarra(Integer codBarra) {
		this.codBarra = codBarra;
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
