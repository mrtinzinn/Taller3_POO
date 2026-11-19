package taller03;

public class Momia extends Monstruos implements Regenerable,Furioso{

	private int longitudVendajes;

	public Momia(String tipo, int vida, int velocidad, int fuerza, int resistencia, int longitudVendajes) {
		super(tipo, vida, velocidad, fuerza, resistencia);
		this.longitudVendajes = longitudVendajes;
	}

	public int getLongitudVendajes() {
		return longitudVendajes;
	}

	@Override
	public String enfurecerse() {
		return "La momia se ha enfurecido!";
	}

	@Override
	public String regenerarse() {
		return "La momia empieza a regenerarse.";
	}

	@Override
	public double aceptar(VisitorMinijuego visitor) {
		return visitor.visitar(this);
	}
	
	

}
