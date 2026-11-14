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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String regenerarse() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double aceptar(VisitorMinijuego visitor) {
		return visitor.visitar(this);
	}
	
	

}
