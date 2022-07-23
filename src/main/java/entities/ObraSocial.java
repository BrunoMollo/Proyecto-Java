package entities;

import ourLib.Parsers.JsonMaker;
import ourLib.Parsers.Jsonable;

public class ObraSocial implements Jsonable{
	
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
	
	
	public ObraSocial() { }


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


	@Override
	public String toJson() {
		JsonMaker maker=new JsonMaker();
		maker.set("id_os", id);
		maker.set("name_os", nombre);
		maker.set("phone_os", telefono);
		maker.set("email_os", email);
		maker.set("discount_os", descuento);
		return maker.getJSONObject();
	}
	
	
	
	
	
}
