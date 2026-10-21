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



	

}
