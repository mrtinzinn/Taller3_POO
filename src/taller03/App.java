//Martin Hofer Ochoa - 22.368.055-0 - ICCI	

package taller03;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class App {

	public static Scanner scanner = new Scanner(System.in);
	public static ArrayList<Monstruos> mounstros = new ArrayList<>();
	public static ArrayList<Jugador> jugadores = new ArrayList<>();

	public static void main(String[] args) {
		System.out.println("Cargando archivos...");
		CargarArchivo();
		CargarJugadores();
		System.out.println("> " + mounstros.size() + " cargados desde Monstruos.txt");
		System.out.println("> " + jugadores.size() + " cargados desde Registros.txt");
	}

	private static void CargarJugadores() {
		File arch = new File("txt/Registros.txt");

		try (Scanner sFile = new Scanner(arch)) {
			int numeroLinea = 0;

			while (sFile.hasNextLine()) {
				String linea = sFile.nextLine();
				numeroLinea++;

				if (linea.trim().equals("")) {
					continue;
				}

				String[] partes = linea.split(";", -1);

				if (partes.length != 2) {
					System.out.println("Linea " + numeroLinea + ": debe tener nombre y puntaje.");
					continue;
				}

				String nombre = partes[0].trim();
				String puntajeTexto = partes[1].trim();

				if (nombre.equals("")) {
					System.out.println("Linea " + numeroLinea + ": el nombre esta vacio.");
					continue;
				}

				try {
					int puntajeMax = Integer.parseInt(puntajeTexto);

					if (puntajeMax > 0) {
						Jugador j = new Jugador(nombre, puntajeMax);
						jugadores.add(j);
					} else {
						System.out.println("Linea " + numeroLinea + ": el puntaje debe ser mayor que cero.");
					}

				} catch (NumberFormatException e) {
					System.out.println("Linea " + numeroLinea + ": el puntaje no es un numero valido.");
				}
			}

		} catch (FileNotFoundException e) {
			System.out.println("No se pudo cargar el archivo de Registros.");
		}
	}

	private static void CargarArchivo() {
		File arch = new File("txt/Monstruos.txt");

		try (Scanner sFile = new Scanner(arch)) {
			int numeroLinea = 0;

			while (sFile.hasNextLine()) {
				String linea = sFile.nextLine();
				numeroLinea++;

				if (linea.trim().equals("")) {
					continue;
				}

				String[] partes = linea.split(";", -1);

				for (int i = 0; i < partes.length; i++) {
					partes[i] = partes[i].trim();
				}

				String tipo = partes[0];
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
					System.out.println("Linea " + numeroLinea + ": tipo de monstruo desconocido.");
					continue;
				}

				if (partes.length != cantidad) {
					System.out.println("Linea " + numeroLinea + ": cantidad de datos incorrecta para " + tipo);
					continue;
				}

				try {
					int vida = Integer.parseInt(partes[1]);
					int velocidad = Integer.parseInt(partes[2]);
					int fuerza = Integer.parseInt(partes[3]);
					int resistencia = Integer.parseInt(partes[4]);

					switch (tipo) {
					case "Zombi": {
						int fuerzaMordida = Integer.parseInt(partes[5]);

						Zombie z = new Zombie(tipo, vida, velocidad, fuerza, resistencia, fuerzaMordida);
						mounstros.add(z);
						break;
					}

					case "Vampiro": {
						int velocidadVuelo = Integer.parseInt(partes[5]);
						int resistenciaSol = Integer.parseInt(partes[6]);

						Vampiro v = new Vampiro(tipo, vida, velocidad, fuerza, resistencia, velocidadVuelo,
								resistenciaSol);
						mounstros.add(v);
						break;
					}

					case "Momia": {
						int longitudVendajes = Integer.parseInt(partes[5]);

						Momia m = new Momia(tipo, vida, velocidad, fuerza, resistencia, longitudVendajes);
						mounstros.add(m);
						break;
					}

					case "HombreLobo": {
						int ferocidad = Integer.parseInt(partes[5]);
						int faseLunar = Integer.parseInt(partes[6]);

						HombreLobo h = new HombreLobo(tipo, vida, velocidad, fuerza, resistencia, ferocidad, faseLunar);
						mounstros.add(h);
						break;
					}
					}

				} catch (NumberFormatException e) {
					System.out.println("Linea " + numeroLinea + ": hay un dato que no es un numero.");
				}
			}

		} catch (FileNotFoundException e) {
			System.out.println("No se pudo cargar el archivo de Monstruos.");
		}
	}

}
