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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String morder() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double aceptar(VisitorMinijuego visitor) {
		return visitor.visitar(this);
	}
	
	

}
