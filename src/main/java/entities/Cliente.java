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
	private LocalDate fechaNacimiento;
	private String localidad;
	private String provincia;

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
		return maker.getJSONObject();
	}
	
}
