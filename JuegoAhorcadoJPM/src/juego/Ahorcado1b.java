package juego;

import java.util.Arrays;
import java.util.Scanner;

public class Ahorcado1b {

	// variables "globales": lista de palabras, scanner, numero de intentos,
	// lista de letras, array de booleanos de letras utuilizadas

	public static Scanner teclado = new Scanner(System.in);
	public static int fallosPermitidos = 6;

	public static char[] letras = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
			'I', 'J', 'K', 'L', 'M', 'N', 'Ñ', 'O', 'P', 'Q', 'R', 'S', 'T',
			'U', 'V', 'W', 'X', 'Y', 'Z' };
	public static boolean[] utilizadas = new boolean[28];

	public static void main(String[] args) {

		jugar();

	}

	// inicia el juego y las variables
	public static void jugar() {
		Arrays.fill(utilizadas, false);
		utilizadas[27] = true; // no corresponde a ninguna letra, sirve para que
								// después entre en un bucle
		fallosPermitidos = 6;

		String palabra = elegirPalabra();

		// crear un array con los caracteres de la palabra
		char[] caracteres = new char[palabra.length()];
		for (int i = 0; i < palabra.length(); i++) {
			caracteres[i] = palabra.charAt(i);
		}

		// crear un array para saber si los caracteres se han adivinado
		boolean[] adivinado = new boolean[palabra.length()];
		Arrays.fill(adivinado, false);

		int opcion = menuInicial(); // muestra el menú inicial
		if (opcion == 2) {
			salir();
		}
		System.out.println("**** Juego del ahorcado ****");
		rondas(fallosPermitidos, palabra, caracteres, adivinado);
	}

	// elige una palabra al azar de la lista
	public static String elegirPalabra() {
		String palabra = new PalabraAleatoria().palabra();
		palabra = palabra.toUpperCase();
		//quitar las tildes
		String nuevaPalabra = "";
		for (int i=0; i<palabra.length();i++) {
			if (palabra.substring(i, i+1).equals("Á"))
				nuevaPalabra = nuevaPalabra.concat("A");
			else if (palabra.substring(i, i+1).equals("É"))
				nuevaPalabra = nuevaPalabra.concat("E");
			else if (palabra.substring(i, i+1).equals("Í"))
				nuevaPalabra = nuevaPalabra.concat("I");
			else if (palabra.substring(i, i+1).equals("Ó"))
				nuevaPalabra = nuevaPalabra.concat("O");
			else if (palabra.substring(i, i+1).equals("Ú"))
				nuevaPalabra = nuevaPalabra.concat("U");
			else if (palabra.substring(i, i+1).equals("Ü"))
				nuevaPalabra = nuevaPalabra.concat("U");
			else
				nuevaPalabra = nuevaPalabra.concat(palabra.substring(i,i+1));
		}
		return nuevaPalabra;
		}

	// Muestra un menú para la primera vez que se inicia el juego
	public static int menuInicial() {
		int respuesta = 0;
		while (respuesta != 1 && respuesta != 2) {
			System.out
					.print("Introduce 1 para iniciar el juego, 2 para salir: ");
			respuesta = teclado.nextInt();
		}
		return respuesta;
	}

	// Rondas de juego, por cada ronda dice cuantos intentos quedan, muestra
	// palabra,
	// el jugador elige letra, muestra palabra otra vez y da 3 opciones al
	// jugador
	public static void rondas(int intentos, String palabra, char[] caracteres,
			boolean[] adivinado) {
		while (intentos >= 0) {
			boolean hayCoincidencias = false;
			System.out.println("Número de fallos permitidos: " + intentos);
			mostrarPalabra(caracteres, adivinado);
			char nuevaLetra = elegirLetra(); // elige una letra
			comprobar(caracteres, adivinado, nuevaLetra);
			if (comprobar(caracteres, adivinado,nuevaLetra)) {
				hayCoincidencias = true;
			}
			mostrarPalabra(caracteres, adivinado);
			// comprobar si todos los caracteres están descubiertos (en ese
			// caso, ha ganado)
			boolean todosDescubiertos = true;
			for (int j = 0; j < adivinado.length; j++) {
				if (!adivinado[j]) {
					todosDescubiertos = false;
				}
			}
			if (todosDescubiertos) {
				gana();
			}
			int opcion = menuRonda(intentos); // decidir si elige otra letra,
												// adivina palabra o sale de la
												// partida
			// el jugador escoge intentar adivinar la palabra
			if (opcion == 2) {
				System.out.print("¿Qué palabra es?: ");
				String respuesta = teclado.next();
				if (respuesta.toUpperCase().equals(palabra)) {
					gana();
				} else {
					System.out.println("No es correcto.");
				}
			}
			// salir de la partida
			else if (opcion == 3) {
				jugar();
			}
			// opcion 1 - elegir otra letra: resta un intento y repite el bucle
			System.out.println("\n***********************************\n");
			if (!hayCoincidencias) 
				intentos--;
		}
		// cuando se han acabado los intentos, ha perdido; inicia una nueva
		// partida
		System.out.println("No has adivinado la palabra correcta: " + palabra);
		System.out.println("*****************\n*****************");
		jugar();
	}

	// muestra la palabra en pantalla con los caracteres adivinados
	// descubiertos;
	public static void mostrarPalabra(char[] caracteres, boolean[] adivinado) {
		for (int i = 0; i < caracteres.length; i++) {
			if (!adivinado[i]) {
				System.out.print("_ ");
			} else {
				System.out.print(caracteres[i] + " ");
			}
		}
		System.out.print("\n\n");
	}

	// Menú al final de cada ronda (las tres opciones que se dan a elegir al
	// jugador)
	public static int menuRonda(int intentos) {
		int opcion = 0;
		if (intentos > 0) {
			System.out
					.print("\n1. Elegir otra letra\n2. Adivinar palabra\n3. Salir del juego\n");
			while (opcion != 1 && opcion != 2 && opcion != 3) {
				System.out.print("__? ");
				String opcionCadena = teclado.next();
				if (!Character.isDigit(opcionCadena.charAt(0))) {
					opcion = 0;
				} else {
					opcion = Integer.parseInt(opcionCadena);
				}

			}
		}
		// si es el último intento, desactiva la opción elegir otra letra
		else {
			System.out
					.print("\n1. Elegir otra letra [DESACTIVADO] \n2. Adivinar palabra\n3. Salir del juego\n");
			while (opcion != 2 && opcion != 3) {
				System.out.print("__? ");
				String opcionCadena = teclado.next();
				if (!Character.isDigit(opcionCadena.charAt(0))) {
					opcion = 0;
				} else {
					opcion = Integer.parseInt(opcionCadena);
				}
			}
		}
		return opcion;

	}

	// método para que el jugador elija una letra, antes puede ver la lista de
	// todas las letras y las ya elegidas
	public static char elegirLetra() {
		// imprimir letras, debajo de las utilizadas se imprime un asterisco
		System.out.println("ABCDEFGHIJKLMNÑOPQRSTUVWXYZ");
		for (int i = 0; i < 27; i++) {
			if (utilizadas[i]) {
				System.out.print("*");
			} else {
				System.out.print(" ");
			}
		}
		// pedir letra
		int posicionCaracterElegido = 27; // fuerza a que se meta en el
											// siguiente bucle la primera vez
		while (utilizadas[posicionCaracterElegido]) {
			System.out.print("\nElige una letra: ");
			String letraElegida = teclado.next();
			letraElegida = letraElegida.toUpperCase();
			char caracterElegido = letraElegida.charAt(0);

			// buscar el caracter en la lista y (cuando termina el bucle)
			// ponerlo como utilizado
			for (int i = 0; i < 27; i++) {
				if (caracterElegido == letras[i]) {
					posicionCaracterElegido = i;
				}
			}
		}
		utilizadas[posicionCaracterElegido] = true;
		return letras[posicionCaracterElegido];
	}

	public static void gana() {
		System.out.println("Enhorabuena. ¡Lo has conseguido!");
		jugar();
	}

	public static void salir() {
		System.out.println("Abandonando el juego");
		System.exit(0);
	}

	// comprueba si la letra elegida por el jugador está en la palabra, para
	// descubrirla
	public static boolean comprobar(char[] caracteres, boolean[] adivinado,
			char letra) {
		boolean hayCoincidencias = false;
		for (int i = 0; i < caracteres.length; i++) {
			if (letra == caracteres[i]) {
				hayCoincidencias = true;
				adivinado[i] = true;
			}
		}
		return hayCoincidencias;
	}
}
