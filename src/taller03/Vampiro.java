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
		return "El vampiro despliega sus alas y comienza a volar.";
	}

	@Override
	public String morder() {
		return "El vampiro utiliza su poderosa mordida.";
	}

	@Override
	public double aceptar(VisitorMinijuego visitor) {
		return visitor.visitar(this);
	}
	
	

}
