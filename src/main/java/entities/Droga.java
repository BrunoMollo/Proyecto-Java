package entities;

import ourLib.Parsers.JsonMaker;
import ourLib.Parsers.Jsonable;

public class Droga implements Jsonable{
	private Integer cod;
	private String nombre;
	
	
	public Integer getCod() {
		return cod;
	}
	public void setCod(Integer cod) {
		this.cod = cod;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	public String toJson() {
		JsonMaker maker=new JsonMaker();
		maker.set("cod_droga", cod);
		maker.set("name_droga", nombre);
		return maker.getJSONObject();
	}	
	
}
