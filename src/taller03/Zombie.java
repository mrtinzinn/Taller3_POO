package taller03;

public class Zombie extends Monstruos implements Mordedor,Regenerable{
	
	private int fuerzaMordida;

	public Zombie(String tipo, int vida, int velocidad, int fuerza, int resistencia, int fuerzaMordida) {
		super(tipo, vida, velocidad, fuerza, resistencia);
		this.fuerzaMordida = fuerzaMordida;
	}

	public int getFuerzaMordida() {
		return fuerzaMordida;
	}

	@Override
	public String regenerarse() {
		return "El zombi comienza a regenerarse.";
	}

	@Override
	public String morder() {
		return "El zombi utiliza su poderosa mordida.";
	}

	@Override
	public double aceptar(VisitorMinijuego visitor) {
		return visitor.visitar(this);
	}
	
	

}
