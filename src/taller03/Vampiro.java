package taller03;

public class Vampiro extends Monstruos{

	private int velocidadVuelo;

	public Vampiro(String tipo, int vida, int velocidad, int fuerza, int resistencia, int velocidadVuelo) {
		super(tipo, vida, velocidad, fuerza, resistencia);
		this.velocidadVuelo = velocidadVuelo;
	}

	public int getVelocidadVuelo() {
		return velocidadVuelo;
	}
	
	

}
