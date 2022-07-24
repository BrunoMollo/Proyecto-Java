package entities;

public class Usuario {

	public static final int ADMIN=10;
	public static final int VENDEDOR=5;
	
	
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
	
	//TODO DECIDIR UN ESTANDAR PARA ESTO
	public void setRol(Integer rol_id) {
		if(rol_id==0) {
			this.rol=ADMIN;
		}
		else if(rol_id==1) {
			this.rol=VENDEDOR;
		}
	}	
	
	@Deprecated
	public Boolean equals(Usuario comparar) {
//		if(comparar!=null) {
		return (this.getUsuario()==(comparar.getUsuario()) && this.getContrasena()==(comparar.getContrasena()));
//		return false;
	}
}
