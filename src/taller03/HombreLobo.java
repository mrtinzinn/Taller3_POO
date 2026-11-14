package taller03;

public class HombreLobo extends Monstruos implements Mordedor,Furioso{

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
	@Override
	public String enfurecerse() {
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
