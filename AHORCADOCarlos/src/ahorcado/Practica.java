package ahorcado;

import java.util.*;

public class Practica {
	static int coinciden = 0;
	static String Palabras = PalabraAleatoria.palabra().toLowerCase();
	
	static char[] LetrasAdivinadas;
	static String charIntroducido = "";
	static int intentos=0;
	static String palabraActual;
	static int posicionAcierto;
	static char[] Letras;
	static int terminar;
	
	// Metodo para pedir letra
	public char introducirLetra() {
		Scanner entrada = new Scanner(System.in);
		System.out.print("Introduzca la letra: ");
		String cadena = entrada.next();
		// char [] charIntr = cadena.toCharArray();
		charIntroducido = charIntroducido + cadena.charAt(0) + " - ";
		
		return cadena.charAt(0);
	}

	// Metodo para comparar letra introducida con una palabra del array de
	// palabras
	public char coincide(char charIntroducido)  {
		palabraActual = Palabras;
		Letras = palabraActual.toCharArray();
		for (int i = 0; i < Letras.length; i++) {
			if (charIntroducido == Letras[i]) {
				posicionAcierto = i;
				coinciden = 1;
				// Letras[i] = charIntroducido;
			}

		}
		if (coinciden != 1) {
			intentos++;
			System.out.println("No coincide. \nNumero de Intentos: " + intentos);
		}
		return charIntroducido;
	}

	// Metodo para mostrar las rayas segun el tamaño de la palabra del array
	public void inicializarPalabra() {
		int indiceArray = Palabras.length();
		LetrasAdivinadas = new char[indiceArray];
		for (int i = 0; i < LetrasAdivinadas.length; i++) {
			LetrasAdivinadas[i] = '_';
			System.out.print(LetrasAdivinadas[i] + " ");
		}
		System.out.println("\n ");
	}

	// Metodo para cambiar la raya por la letra correspondiente
	public void intercambiarLetra(char charIntroducido) {
		int pos = 0;
		do {
			pos = palabraActual.indexOf(charIntroducido, pos);
			if (pos != -1) {
				LetrasAdivinadas[pos] = charIntroducido;
				pos++;
			}
		} while (pos != -1);
	}

	public void mostrarJuego() {
		for (int i = 0; i < Letras.length; i++) {
			System.out.print(LetrasAdivinadas[i] + " ");
		}
		System.out.println();
		System.out.println("Letras que ya introduciste: " + charIntroducido);
	}

	public int fin() {
		int res = 0;
		for (int i = 0; i < Letras.length; i++) {
			if (LetrasAdivinadas[i] == '_') {
				res = 1;
				break;
			} else {
				res = 0;
			}
		}
		System.out.println();
		// System.out.println(res);
		return res;
	}

	public static void main(String[] args)  {

		while (intentos < 6) {
			char c;
			Practica practica = new Practica();
			practica.inicializarPalabra();
			for(int i=0; i >6; i++){
			if (intentos > 6){
				System.out.println("Has perdido¡¡¡¡¡¡¡");
				break;
			}
			}
			do {
				c = practica.introducirLetra();
				practica.coincide(c);
				practica.intercambiarLetra(c);
				practica.mostrarJuego();
			} while (practica.fin() == 1);
			System.out.println(" Felicidades!!!!!!!!");
			break;

		}
	}
}