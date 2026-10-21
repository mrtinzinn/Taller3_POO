package taller03;

public class Vampiro extends Monstruos{

	private int velocidadVuelo;
	private int ResistenciaSol;
	public Vampiro(String tipo, int vida, int velocidad, int fuerza, int resistencia, int velocidadVuelo, int resistenciaSol) {
		super(tipo, vida, velocidad, fuerza, resistencia);
		this.velocidadVuelo = velocidadVuelo;
		this.ResistenciaSol = resistenciaSol;
	}

	public int getVelocidadVuelo() {
		return velocidadVuelo;
	}

	public int getResistenciaSol() {
		return ResistenciaSol;
	}
	
	

}
