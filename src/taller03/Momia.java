package taller03;

public class Momia extends Monstruos{

	private int longitudVendajes;

	public Momia(String tipo, int vida, int velocidad, int fuerza, int resistencia, int longitudVendajes) {
		super(tipo, vida, velocidad, fuerza, resistencia);
		this.longitudVendajes = longitudVendajes;
	}

	public int getLongitudVendajes() {
		return longitudVendajes;
	}
	
	

}
