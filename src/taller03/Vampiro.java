package taller03;

public class Vampiro extends Monstruos implements Mordedor,Volador{

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

	@Override
	public String volar() {
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
