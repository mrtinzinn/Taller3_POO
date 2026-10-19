package taller03;

public class HombreLobo extends Monstruos{

	private int ferocidad;
	private int faseLunar;
	public HombreLobo(String tipo, int vida, int velocidad, int fuerza, int resistencia, int ferocidad, int faseLunar) {
		super(tipo, vida, velocidad, fuerza, resistencia);
		this.ferocidad = ferocidad;
		this.faseLunar = faseLunar;
	}
	public int getFerocidad() {
		return ferocidad;
	}
	public int getFaseLunar() {
		return faseLunar;
	}
	

}
