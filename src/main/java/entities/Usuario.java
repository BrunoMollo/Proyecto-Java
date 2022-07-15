package entities;

public class Usuario {

	private String usuario;
	private String contrasena;
	private String nombre;
	private String apellido;
	private Integer rol;
	
	
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getContrasena() {
		return contrasena;
	}
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
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
	public Integer getRol() {
		return rol;
	}
	public void setRol(Integer rol) {
		this.rol = rol;
	}	
	
	@Deprecated
	public Boolean equals(Usuario comparar) {
//		if(comparar!=null) {
		return (this.getUsuario()==(comparar.getUsuario()) && this.getContrasena()==(comparar.getContrasena()));
//		return false;
	}
}
