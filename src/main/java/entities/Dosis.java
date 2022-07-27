package entities;

public class Dosis {

	private Droga droga;
	private Double cant;
	
	public Dosis(Droga drug, Double cant) {
		this.droga=drug;
		this.cant=cant;
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
	
}
