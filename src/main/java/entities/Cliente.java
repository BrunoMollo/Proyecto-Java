package entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import ourLib.Parsers.JsonMaker;
import ourLib.Parsers.Jsonable;

public class Cliente implements Jsonable {
	
	 final static String dateFormat="dd/MM/yyyy";
	 public final static DateTimeFormatter dFormat= DateTimeFormatter.ofPattern(dateFormat);
	
	private int dni;
	private String nombre;
	private String apellido;
	private String email;
	private String telefono;
	private ObraSocial obraSocial;
	private int nroAfiliado;
	private LocalDate fechaNacimiento;
	private String localidad;
	private String provincia;
	private String direccion;
	private String nroAfiliado;
	
	public String getFullName() {
		return this.apellido+" "+this.nombre;
	}
	public int getDni() {
		return dni;
	}
	public void setDni(int dni) {
		this.dni = dni;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
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
	public ObraSocial getObraSocial() {
		return obraSocial;
	}
	public void setObraSocial(ObraSocial obraSocial) {
		this.obraSocial = obraSocial;
	}
	public int getNroAfiliado() {
		return nroAfiliado;
	}
	public void setNroAfiliado(int nroAfiliado) {
		this.nroAfiliado = nroAfiliado;
	}
	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getLocalidad() {
		return localidad;
	}
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getNroAfiliado() {
		return nroAfiliado;
	}
	public void setNroAfiliado(String nroAfiliado) {
		this.nroAfiliado = nroAfiliado;
	}
	public Cliente() {
		
	}
	public Cliente(int dni) {
		super();
		this.setDni(dni);
	}
	@Override
	public String toJson() {
		JsonMaker maker=new JsonMaker();
		maker.set("dni", dni);
		maker.set("nombre_cli", nombre);
		maker.set("apellido_cli", apellido);
		maker.set("email_cli", email);
		maker.set("telefono_cli", telefono);
		maker.set("cli_os_id", obraSocial.getId());
		maker.set("fecha_nac", fechaNacimiento.format(dFormat));
		maker.set("localidad_cli", localidad);
		maker.set("provincia_cli", provincia);
		maker.set("nroAfiliado_cli", nroAfiliado);
		return maker.getJSONObject();
	}
	
}
