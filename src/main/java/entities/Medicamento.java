package entities;

import java.util.HashMap;

public class Medicamento {

	private Integer codigoBarra;
	private Laboratorio laboratorio;
	private String nombre;
	private Double size;
	private Double precio;
	private HashMap<Integer, Dosis> dosis;
	
	public Medicamento() {
		dosis=new HashMap<>();
	}
	
	public Integer getCodigoBarra() {
		return codigoBarra;
	}
	public void setCodigoBarra(Integer codigoBarra) {
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

	public void addDosis(Dosis dosisToBeAdded) {
		dosis.put(dosisToBeAdded.getCodigoDroga(), dosisToBeAdded);
	}
	
	public void removeDosis(Dosis dosisToBeRemoved) {
		this.dosis.remove(dosisToBeRemoved.getCodigoDroga());
	}
	
	public boolean hasDosis(Dosis dosisToCheck) {
		return this.dosis.containsKey(dosisToCheck.getCodigoDroga());
	}
	
	public HashMap<Integer, Dosis> getAllDosis(){
		return this.dosis;
	}

	
	
	
	
}
