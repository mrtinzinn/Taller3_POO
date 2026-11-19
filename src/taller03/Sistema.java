
package taller03;

import java.util.ArrayList;
import java.util.LinkedList;

public class Sistema {

	private ArrayList<Monstruos> monstruos;
	private ArrayList<Jugador> jugadores;
	private ArrayList<Minijuego> minijuegos;
	private boolean[] monstruosUsados;
	private boolean[] minijuegosJugados;
	private LinkedList<String> historial = new LinkedList<>();
	private int puntajeSesion;
	private String ultimoError = "";

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
		historial.clear();
	}

	public Jugador buscarJugador(String nombre) {

		for (int i = 0; i < jugadores.size(); i++) {

			Jugador j = jugadores.get(i);

			if (j.getNombre().equalsIgnoreCase(nombre)) {
				return j;
			}
		}

		return null;
	}

	public boolean crearJugador(String[] partes) {
		ultimoError = "";
		if (partes.length != 2) {
			ultimoError = "Debe tener nombre y puntaje.";
			return false;
		}

		String nombre = partes[0].trim();
		String puntajeTexto = partes[1].trim();

		if (nombre.equals("")) {
			ultimoError = "El nombre esta vacio.";
			return false;
		}

		try {

			int puntajeMax = Integer.parseInt(puntajeTexto);

			if (puntajeMax < 0) {
				ultimoError = "El puntaje no puede ser negativo.";
				return false;
			}

			Jugador existente = buscarJugador(nombre);

			if (existente != null) {

				if (puntajeMax > existente.getPuntajeMax()) {
					existente.setPuntajeMax(puntajeMax);
				}

				return true;
			}

			Jugador j = new Jugador(nombre, puntajeMax);
			jugadores.add(j);

			return true;

		} catch (NumberFormatException e) {

			ultimoError = "El puntaje no es un numero valido.";
			return false;
		}
	}

	public void registrarPartida(int posicionJuego, int posicionMonstruo, int puntaje) {

		minijuegosJugados[posicionJuego] = true;
		monstruosUsados[posicionMonstruo] = true;

		puntajeSesion += puntaje;

		Minijuego juego = minijuegos.get(posicionJuego);
		Monstruos monstruo = monstruos.get(posicionMonstruo);

		String registro = juego.getNombre() + " | " + monstruo.getTipo() + " | " + puntaje + " puntos";

		historial.add(registro);
	}

	public String mostrarHistorial() {

		if (historial.isEmpty()) {
			return "Todavia no has jugado ninguna partida.";
		}

		String texto = "";

		for (int i = 0; i < historial.size(); i++) {

			texto += (i + 1) + ". " + historial.get(i) + "\n";
		}

		texto += "\nPuntaje total: " + puntajeSesion;

		return texto;
	}

	public int calcularPuntaje(int posicionJuego, int posicionMonstruo) {

		Minijuego juego = minijuegos.get(posicionJuego);
		Monstruos monstruo = monstruos.get(posicionMonstruo);

		double potencial = juego.calcularPotencial(monstruo);
		double multiplicador = 1.0;

		if (juego instanceof CarreraCementerio && monstruo instanceof Volador) {
			multiplicador = 1.5;
		}

		if (juego instanceof CazaCalabazas && monstruo instanceof Mordedor) {
			multiplicador = 1.5;
		}

		if (juego instanceof LanzamientoLapidas && monstruo instanceof Furioso) {
			multiplicador = 1.5;
		}

		if (juego instanceof SobrevivirAmanecer && monstruo instanceof Regenerable) {
			multiplicador = 1.5;
		}

		int puntaje = (int) Math.round(potencial * 10 * multiplicador);

		return puntaje;
	}

	public String obtenerHabilidad(int posicionJuego, int posicionMonstruo) {

		Minijuego juego = minijuegos.get(posicionJuego);
		Monstruos monstruo = monstruos.get(posicionMonstruo);

		if (juego instanceof CarreraCementerio) {

			if (monstruo instanceof Volador) {
				Volador v = (Volador) monstruo;
				return v.volar();
			}
		}

		if (juego instanceof CazaCalabazas) {

			if (monstruo instanceof Mordedor) {
				Mordedor m = (Mordedor) monstruo;
				return m.morder();
			}
		}

		if (juego instanceof LanzamientoLapidas) {

			if (monstruo instanceof Furioso) {
				Furioso f = (Furioso) monstruo;
				return f.enfurecerse();
			}
		}

		if (juego instanceof SobrevivirAmanecer) {

			if (monstruo instanceof Regenerable) {
				Regenerable r = (Regenerable) monstruo;
				return r.regenerarse();
			}
		}

		return "El monstruo no posee la habilidad premiada.";
	}

	public boolean crearMonstruo(String[] partes) {
		ultimoError = "";
		if (partes.length == 0) {
			ultimoError = "La linea esta vacia";
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
			ultimoError = "Tipo de monstruo desconocido.";
			return false;
		}

		if (partes.length != cantidad) {
			ultimoError = "Cantidad de datos incorrecta para " + tipo;
			return false;
		}

		try {

			int vida = Integer.parseInt(partes[1].trim());
			int velocidad = Integer.parseInt(partes[2].trim());
			int fuerza = Integer.parseInt(partes[3].trim());
			int resistencia = Integer.parseInt(partes[4].trim());

			if (vida < 1 || vida > 200 || velocidad < 1 || velocidad > 100 || fuerza < 1 || fuerza > 100
					|| resistencia < 1 || resistencia > 100) {

				ultimoError = "Atributos generales fuera de rango.";
				return false;
			}

			switch (tipo) {

			case "Zombi": {

				int fuerzaMordida = Integer.parseInt(partes[5].trim());

				if (fuerzaMordida < 1 || fuerzaMordida > 100) {
					ultimoError = "Fuerza de mordida fuera de rango.";
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

					ultimoError = "Atributos de vampiro fuera de rango.";
					return false;
				}

				Vampiro v = new Vampiro(tipo, vida, velocidad, fuerza, resistencia, velocidadVuelo, resistenciaSol);

				monstruos.add(v);
				break;
			}

			case "Momia": {

				int longitudVendajes = Integer.parseInt(partes[5].trim());

				if (longitudVendajes < 1 || longitudVendajes > 300) {
					ultimoError = "Longitud de vendajes fuera de rango.";
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

					ultimoError = "Atributos de hombre lobo fuera de rango.";
					return false;
				}

				HombreLobo h = new HombreLobo(tipo, vida, velocidad, fuerza, resistencia, ferocidad, faseLunar);

				monstruos.add(h);
				break;
			}
			}

			return true;

		} catch (NumberFormatException e) {

		    ultimoError = "Hay un dato que no es un numero.";
		    return false;
		}
	}

	public String mostrarCatalogo() {

		String texto = "";

		for (int i = 0; i < monstruos.size(); i++) {

			Monstruos m = monstruos.get(i);

			texto += "\n===== MONSTRUO " + (i + 1) + " =====\n";

			texto += "Tipo: " + m.getTipo() + "\n";
			texto += "Vida: " + m.getVida() + "\n";
			texto += "Velocidad: " + m.getVelocidad() + "\n";
			texto += "Fuerza: " + m.getFuerza() + "\n";
			texto += "Resistencia: " + m.getResistencia() + "\n";

			if (m instanceof Zombie) {

				Zombie z = (Zombie) m;

				texto += "Fuerza de mordida: " + z.getFuerzaMordida() + "\n";
			}

			if (m instanceof Vampiro) {

				Vampiro v = (Vampiro) m;

				texto += "Velocidad de vuelo: " + v.getVelocidadVuelo() + "\n";

				texto += "Resistencia al sol: " + v.getResistenciaSol() + "\n";
			}

			if (m instanceof Momia) {

				Momia momia = (Momia) m;

				texto += "Longitud de vendajes: " + momia.getLongitudVendajes() + "\n";
			}

			if (m instanceof HombreLobo) {

				HombreLobo h = (HombreLobo) m;

				texto += "Ferocidad: " + h.getFerocidad() + "\n";

				texto += "Fase lunar: " + h.getFaseLunar() + "\n";
			}

			texto += "------------------------\n";
		}

		return texto;
	}

	public boolean monstruoDisponible(int posicion) {

		if (monstruosUsados == null) {
			return false;
		}

		if (posicion < 0 || posicion >= monstruos.size()) {
			return false;
		}

		return !monstruosUsados[posicion];
	}

	public boolean minijuegoDisponible(int posicion) {

		if (minijuegosJugados == null) {
			return false;
		}

		if (posicion < 0 || posicion >= minijuegos.size()) {
			return false;
		}

		return !minijuegosJugados[posicion];
	}

	public int getPuntajeSesion() {
		return puntajeSesion;
	}

	public String finalizarPartida(String nombre) {

		Jugador j = buscarJugador(nombre);

		if (j != null) {

			if (puntajeSesion > j.getPuntajeMax()) {

				j.setPuntajeMax(puntajeSesion);

				return "Nuevo record: " + puntajeSesion + " puntos.";
			}

			return "Tu record sigue siendo: " + j.getPuntajeMax() + " puntos.";
		}

		Jugador nuevo = new Jugador(nombre, puntajeSesion);
		jugadores.add(nuevo);

		return "Jugador registrado con " + puntajeSesion + " puntos.";
	}

	public String mostrarRanking() {

		if (jugadores.isEmpty()) {
			return "No hay jugadores registrados.";
		}

		ArrayList<Jugador> ranking = new ArrayList<>();

		for (int i = 0; i < jugadores.size(); i++) {
			ranking.add(jugadores.get(i));
		}

		for (int i = 0; i < ranking.size() - 1; i++) {

			for (int j = 0; j < ranking.size() - 1 - i; j++) {

				Jugador actual = ranking.get(j);
				Jugador siguiente = ranking.get(j + 1);

				boolean cambiar = false;

				if (actual.getPuntajeMax() < siguiente.getPuntajeMax()) {
					cambiar = true;
				}

				if (actual.getPuntajeMax() == siguiente.getPuntajeMax()) {

					if (actual.getNombre().compareToIgnoreCase(siguiente.getNombre()) > 0) {
						cambiar = true;
					}
				}

				if (cambiar) {
					ranking.set(j, siguiente);
					ranking.set(j + 1, actual);
				}
			}
		}

		String texto = "";

		for (int i = 0; i < ranking.size(); i++) {

			Jugador j = ranking.get(i);

			texto += (i + 1) + ". " + j.getNombre() + " - " + j.getPuntajeMax() + " puntos\n";
		}

		return texto;
	}

	public String getUltimoError() {
		return ultimoError;
	}

	public void setUltimoError(String error) {
		ultimoError = error;
	}

}
