package taller03;

public class Jugador {
	private String nombre;
	private int puntajeMax;
	
	public Jugador(String nombre, int puntajeMax) {
		this.nombre = nombre;
		this.puntajeMax = puntajeMax;
	}

	public String getNombre() {
		return nombre;
	}

	public int getPuntajeMax() {
		return puntajeMax;
	}
	
}
