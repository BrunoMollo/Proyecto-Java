package entities;

public class ObraSocial {
	
	private Integer id;
	private String nombre;
	private String telefono;
	private String email;
	private Double descuento;
	
	
	public ObraSocial(String nombre, String telefono, String email, Double descuento) {
		super();
		this.nombre = nombre;
		this.telefono = telefono;
		this.email = email;
		this.descuento = descuento;
	}
	
	
	public Integer getId() {
		return id;
	}


	public void setId(Integer codigo) {
		id = codigo;
	}


	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Double getDescuento() {
		return descuento;
	}
	public void setDescuento(Double descuento) {
		this.descuento = descuento;
	}
	
	
	
	
	
}
