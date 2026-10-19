package taller03;

public class Zombie extends Monstruos{
	
	private int fuerzaMordida;

	public Zombie(String tipo, int vida, int velocidad, int fuerza, int resistencia, int fuerzaMordida) {
		super(tipo, vida, velocidad, fuerza, resistencia);
		this.fuerzaMordida = fuerzaMordida;
	}

	public int getFuerzaMordida() {
		return fuerzaMordida;
	}
	
	

}
