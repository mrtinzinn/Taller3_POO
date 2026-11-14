package taller03;

public abstract class Monstruos {
	
	protected String tipo;
	protected int vida;
	protected int velocidad;
	protected int fuerza;
	protected int resistencia;
	
	
	
	public Monstruos(String tipo, int vida, int velocidad, int fuerza, int resistencia ) {
		this.tipo = tipo;
		this.vida = vida;
		this.velocidad = velocidad;
		this.fuerza = fuerza;
		this.resistencia = resistencia;
	}

	public abstract double aceptar(VisitorMinijuego visitor);

	public String getTipo() {
		return tipo;
	}



	public int getVida() {
		return vida;
	}



	public int getVelocidad() {
		return velocidad;
	}



	public int getFuerza() {
		return fuerza;
	}



	public int getResistencia() {
		return resistencia;
	}
	


	

}
