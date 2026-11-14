
package taller03;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class App {

	public static Scanner scanner = new Scanner(System.in);
	public static Sistema sistema = new Sistema();

	public static void main(String[] args) {

		System.out.println("Cargando archivos...");

		CargarArchivo();
		CargarJugadores();

		System.out.println();
		System.out.println("> " + sistema.cantidadMonstruos() + " monstruos cargados.");

		System.out.println("> " + sistema.cantidadJugadores() + " jugadores cargados.");

		if (sistema.cantidadMonstruos() == 0) {

			System.out.println("No hay monstruos disponibles.");
			return;
		}

		boolean programaActivo = true;

		while (programaActivo) {

			String nombre = iniciarSesion();

			boolean sesionActiva = true;

			while (sesionActiva) {

				int opcion = menuPrincipal();

				switch (opcion) {

				case 1:
					System.out.println("Minijuegos proximamente.");
					break;

				case 2:
					System.out.println("===== CATALOGO DE MONSTRUOS =====");
					System.out.println(sistema.mostrarCatalogo());
					break;

				case 3:
					System.out.println("Historial proximamente.");
					break;

				case 4:
					System.out.println("Ranking proximamente.");
					break;

				case 5:
					System.out.println("Sesion finalizada: " + nombre);
					sesionActiva = false;
					break;

				case 6:
					System.out.println("Gracias por jugar Spooky Season.");
					sesionActiva = false;
					programaActivo = false;
					break;

				default:
					System.out.println("Opcion no valida.");
					break;
				}
			}
		}
	}

	public static void CargarArchivo() {

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

				System.out.print("Linea " + numeroLinea + ": ");

				boolean resultado = sistema.crearMonstruo(partes);

				if (resultado) {
					System.out.println("Monstruo cargado correctamente.");
				}
			}

		} catch (FileNotFoundException e) {

			System.out.println("No se pudo cargar el archivo de Monstruos.");
		}
	}

	public static String iniciarSesion() {

		String nombre = "";

		while (nombre.equals("")) {

			System.out.println();
			System.out.println("=== SPOOKY SEASON ===");
			System.out.print("Ingrese su nombre: ");

			nombre = scanner.nextLine().trim();

			if (nombre.equals("") || nombre.contains(";")) {

				System.out.println("Nombre no valido.");
				nombre = "";
			}
		}

		return nombre;
	}

	public static int menuPrincipal() {

		System.out.println();
		System.out.println("===== MENU PRINCIPAL =====");
		System.out.println("1. Jugar un minijuego");
		System.out.println("2. Ver catalogo de monstruos");
		System.out.println("3. Ver historial");
		System.out.println("4. Ver ranking");
		System.out.println("5. Terminar sesion");
		System.out.println("6. Salir del programa");
		System.out.print("Seleccione una opcion: ");

		try {

			int opcion = Integer.parseInt(scanner.nextLine());
			return opcion;

		} catch (NumberFormatException e) {

			return 0;
		}
	}

	public static void jugarMinijuego() {

		System.out.println();
		System.out.println("===== MINIJUEGOS =====");

		for (int i = 0; i < sistema.getMinijuegos().size(); i++) {

			Minijuego juego = sistema.getMinijuegos().get(i);

			System.out.println((i + 1) + ". " + juego.getNombre());
		}

		System.out.print("Seleccione un minijuego (0 para volver): ");

		int opcion;

		try {
			opcion = Integer.parseInt(scanner.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("Debe ingresar un numero.");
			return;
		}

		if (opcion == 0) {
			return;
		}

		int posicionJuego = opcion - 1;

		if (!sistema.minijuegoDisponible(posicionJuego)) {
			System.out.println("Minijuego no disponible o ya jugado.");
			return;
		}

		System.out.println();
		System.out.println("===== MONSTRUOS =====");

		for (int i = 0; i < sistema.getMonstruos().size(); i++) {

			Monstruos m = sistema.getMonstruos().get(i);

			System.out.println((i + 1) + ". " + m.getTipo());
		}

		System.out.print("Seleccione un monstruo (0 para volver): ");

		try {
			opcion = Integer.parseInt(scanner.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("Debe ingresar un numero.");
			return;
		}

		if (opcion == 0) {
			return;
		}

		int posicionMonstruo = opcion - 1;

		if (!sistema.monstruoDisponible(posicionMonstruo)) {
			System.out.println("Monstruo no disponible o ya utilizado.");
			return;
		}

		Minijuego juego = sistema.getMinijuegos().get(posicionJuego);
		Monstruos monstruo = sistema.getMonstruos().get(posicionMonstruo);

		System.out.println();
		System.out.println("Minijuego seleccionado: " + juego.getNombre());
		System.out.println("Monstruo seleccionado: " + monstruo.getTipo());

		double potencial = juego.calcularPotencial(monstruo);

		System.out.println("Potencial calculado: " + potencial);
	}

	public static void CargarJugadores() {

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

				System.out.print("Linea " + numeroLinea + ": ");

				boolean resultado = sistema.crearJugador(partes);

				if (resultado) {
					System.out.println("Jugador cargado correctamente.");
				}
			}

		} catch (FileNotFoundException e) {

			System.out.println("No se encontro Registros.txt.");
			System.out.println("Se iniciara sin jugadores registrados.");
		}
	}

}
