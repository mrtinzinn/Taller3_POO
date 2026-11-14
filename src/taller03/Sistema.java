
package taller03;

import java.util.ArrayList;

public class Sistema {

	private ArrayList<Monstruos> monstruos;
	private ArrayList<Jugador> jugadores;
	private ArrayList<Minijuego> minijuegos;
	private boolean[] monstruosUsados;
	private boolean[] minijuegosJugados;

	private int puntajeSesion;

	public Sistema() {

		monstruos = new ArrayList<>();
		jugadores = new ArrayList<>();
		minijuegos = new ArrayList<>();

		minijuegos.add(new CarreraCementerio());
		minijuegos.add(new CazaCalabazas());
		minijuegos.add(new LanzamientoLapidas());
		minijuegos.add(new SobrevivirAmanecer());
	}

	public void agregarMonstruo(Monstruos m) {
		monstruos.add(m);
	}

	public void agregarJugador(Jugador j) {
		jugadores.add(j);
	}

	public ArrayList<Monstruos> getMonstruos() {
		return monstruos;
	}

	public ArrayList<Jugador> getJugadores() {
		return jugadores;
	}

	public ArrayList<Minijuego> getMinijuegos() {
		return minijuegos;
	}

	public int cantidadMonstruos() {
		return monstruos.size();
	}

	public int cantidadJugadores() {
		return jugadores.size();
	}

	public void iniciarPartida() {

		monstruosUsados = new boolean[monstruos.size()];
		minijuegosJugados = new boolean[minijuegos.size()];

		puntajeSesion = 0;
	}

	public boolean crearJugador(String[] partes) {

		if (partes.length != 2) {
			System.out.println("Debe tener nombre y puntaje.");
			return false;
		}

		String nombre = partes[0].trim();
		String puntajeTexto = partes[1].trim();

		if (nombre.equals("")) {
			System.out.println("El nombre esta vacio.");
			return false;
		}

		try {

			int puntajeMax = Integer.parseInt(puntajeTexto);

			if (puntajeMax < 0) {
				System.out.println("El puntaje no puede ser negativo.");
				return false;
			}

			Jugador j = new Jugador(nombre, puntajeMax);
			jugadores.add(j);

			return true;

		} catch (NumberFormatException e) {

			System.out.println("El puntaje no es un numero valido.");
			return false;
		}
	}

	public boolean crearMonstruo(String[] partes) {

		if (partes.length == 0) {
			return false;
		}

		String tipo = partes[0].trim();
		int cantidad = 0;

		switch (tipo) {

		case "Zombi":
		case "Momia":
			cantidad = 6;
			break;

		case "Vampiro":
		case "HombreLobo":
			cantidad = 7;
			break;

		default:
			System.out.println("Tipo de monstruo desconocido.");
			return false;
		}

		if (partes.length != cantidad) {
			System.out.println("Cantidad de datos incorrecta para " + tipo);
			return false;
		}

		try {

			int vida = Integer.parseInt(partes[1].trim());
			int velocidad = Integer.parseInt(partes[2].trim());
			int fuerza = Integer.parseInt(partes[3].trim());
			int resistencia = Integer.parseInt(partes[4].trim());

			if (vida < 1 || vida > 200 || velocidad < 1 || velocidad > 100 || fuerza < 1 || fuerza > 100
					|| resistencia < 1 || resistencia > 100) {

				System.out.println("Atributos generales fuera de rango.");
				return false;
			}

			switch (tipo) {

			case "Zombi": {

				int fuerzaMordida = Integer.parseInt(partes[5].trim());

				if (fuerzaMordida < 1 || fuerzaMordida > 100) {
					System.out.println("Fuerza de mordida fuera de rango.");
					return false;
				}

				Zombie z = new Zombie(tipo, vida, velocidad, fuerza, resistencia, fuerzaMordida);

				monstruos.add(z);
				break;
			}

			case "Vampiro": {

				int velocidadVuelo = Integer.parseInt(partes[5].trim());
				int resistenciaSol = Integer.parseInt(partes[6].trim());

				if (velocidadVuelo < 1 || velocidadVuelo > 100 || resistenciaSol < 1 || resistenciaSol > 100) {

					System.out.println("Atributos de vampiro fuera de rango.");
					return false;
				}

				Vampiro v = new Vampiro(tipo, vida, velocidad, fuerza, resistencia, velocidadVuelo, resistenciaSol);

				monstruos.add(v);
				break;
			}

			case "Momia": {

				int longitudVendajes = Integer.parseInt(partes[5].trim());

				if (longitudVendajes < 1 || longitudVendajes > 300) {
					System.out.println("Longitud de vendajes fuera de rango.");
					return false;
				}

				Momia m = new Momia(tipo, vida, velocidad, fuerza, resistencia, longitudVendajes);

				monstruos.add(m);
				break;
			}

			case "HombreLobo": {

				int ferocidad = Integer.parseInt(partes[5].trim());
				int faseLunar = Integer.parseInt(partes[6].trim());

				if (ferocidad < 1 || ferocidad > 100 || faseLunar < 1 || faseLunar > 4) {

					System.out.println("Atributos de hombre lobo fuera de rango.");
					return false;
				}

				HombreLobo h = new HombreLobo(tipo, vida, velocidad, fuerza, resistencia, ferocidad, faseLunar);

				monstruos.add(h);
				break;
			}
			}

			return true;

		} catch (NumberFormatException e) {

			System.out.println("Hay un dato que no es un numero.");
			return false;
		}
	}

	public String mostrarCatalogo() {

		String texto = "";

		for (int i = 0; i < monstruos.size(); i++) {

			Monstruos m = monstruos.get(i);

			texto += "\nMonstruo " + (i + 1) + "\n";
			texto += "Tipo: " + m.getTipo() + "\n";
			texto += "Vida: " + m.getVida() + "\n";
			texto += "Velocidad: " + m.getVelocidad() + "\n";
			texto += "Fuerza: " + m.getFuerza() + "\n";
			texto += "Resistencia: " + m.getResistencia() + "\n";
			texto += "------------------------\n";
		}

		return texto;
	}

	public boolean monstruoDisponible(int posicion) {

		if (posicion < 0 || posicion >= monstruos.size()) {
			return false;
		}

		return !monstruosUsados[posicion];
	}

	public boolean minijuegoDisponible(int posicion) {

		if (posicion < 0 || posicion >= minijuegos.size()) {
			return false;
		}

		return !minijuegosJugados[posicion];
	}

	public int getPuntajeSesion() {
		return puntajeSesion;
	}

}
