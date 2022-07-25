package entities;

import jakarta.servlet.http.HttpServletRequest;

public class Usuario {

	public static final int ADMIN=0;
	public static final int VENDEDOR=1;
	
	
	//Este metodo lo hice para usarlo en los servlets, pq es mas seguro tener un Usuario vacio que un 'null', no le puedo enivar metodos al null
	public static Usuario factory(HttpServletRequest request) {
		Usuario u=(Usuario) request.getSession().getAttribute("user");
		if(u==null) {
			return new Usuario();
		}
		else {
			return u;
		}
		
	}
	
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
	
	public Boolean hasAccess(Integer NeededAccess) {
		if(this.rol==null) { return false; }
		
		switch (NeededAccess) {
		case Usuario.ADMIN: 
			if(this.rol==Usuario.ADMIN) { return true; }
			else {return false; }
		case Usuario.VENDEDOR:
			if(this.rol==Usuario.ADMIN || this.rol==Usuario.VENDEDOR) { return true; }
			else { return false; }
		default:
			throw new IllegalArgumentException("Unexpected value: " + NeededAccess);
		}
	}
	
	@Deprecated
	public Boolean equals(Usuario comparar) {
//		if(comparar!=null) {
		return (this.getUsuario()==(comparar.getUsuario()) && this.getContrasena()==(comparar.getContrasena()));
//		return false;
	}
}
