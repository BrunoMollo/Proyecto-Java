package entities;

public class Dosis {

	private Droga droga;
	private Double cant;
	private String unidad;
	
	public Dosis(Droga drug, Double cant, String unidad) {
		this.droga=drug;
		this.cant=cant;
		this.unidad=unidad;
	}
	
	public Integer getCodigoDroga() {
		return this.droga.getCod();
	}
	public Droga getDroga() {
		return droga;
	}
	public void setDroga(Droga droga) {
		this.droga = droga;
	}
	public Double getCant() {
		return cant;
	}
	public void setCant(Double cant) {
		this.cant = cant;
	}
	public String getUnidad() {
		return unidad;
	}
	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}
	
}
