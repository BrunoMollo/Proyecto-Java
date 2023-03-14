package entities;

import java.util.HashMap;

import ourLib.Parsers.JsonMaker;
import ourLib.Parsers.Jsonable;

public class Medicamento  implements Jsonable{


	private String codigoBarra;
	private Laboratorio laboratorio;
	private String nombre;
	private Double size;
	private String unidad;
	private Double precio;
	private HashMap<Integer, Dosis> dosis;
	
	public Medicamento() {
		dosis=new HashMap<>();
	}
	public String getCodigoBarra() {
		return codigoBarra;
	}
	public void setCodigoBarra(String codigoBarra) {
		this.codigoBarra = codigoBarra;
	}
	public Laboratorio getLaboratorio() {
		return laboratorio;
	}
	public void setLaboratorio(Laboratorio laboratorio) {
		this.laboratorio = laboratorio;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Double getSize() {
		return size;
	}
	public void setSize(Double size) {
		this.size = size;
	}
	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	public String getUnidad() {
		return unidad;
	}
	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}
	
	public void addDosis(Dosis dosisToBeAdded) {
		dosis.put(dosisToBeAdded.getCodigoDroga(), dosisToBeAdded);
	}
	public void addAllDosis(HashMap<Integer, Dosis> dosisToBeAdded) {
		this.dosis=dosisToBeAdded;
	}	
	public void removeDosis(Dosis dosisToBeRemoved) {
		this.dosis.remove(dosisToBeRemoved.getCodigoDroga());
	}	
	public boolean hasDosis(Dosis dosisToCheck) {
		return this.dosis.containsKey(dosisToCheck.getCodigoDroga());
	}	
	public boolean handleDosis(Dosis dosisToBeHandled) {
		if(dosisToBeHandled.getDroga()==null) return false;
		if(dosisToBeHandled.getCant()<=0) {
			this.dosis.remove(dosisToBeHandled.getCodigoDroga());
		} else {
			this.dosis.put(dosisToBeHandled.getCodigoDroga(), dosisToBeHandled);
		}
		return false;
		
	}
	public HashMap<Integer, Dosis> getAllDosis(){
		return this.dosis;
	}
	
	@Override
	public String toJson() {
		JsonMaker maker=new JsonMaker();
		maker.set("cod_med", codigoBarra);
		maker.set("name_med", nombre);
		maker.set("size_med", size);
		maker.set("unidad_med", unidad);
		maker.set("price_med", precio);
		maker.set("name_med", nombre);
		if(laboratorio!=null) {
			maker.set("lab_name_med",laboratorio.getNombre());
			maker.set("lab_id_med",laboratorio.getCodigo());			
		}
		return maker.getJSONObject();
	}	


	
	
	
	
}
