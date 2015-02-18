

import java.util.Random;
import java.util.Scanner;

public class ahorcado  {
	


	public static void jugar() {
		Scanner sc = new Scanner(System.in);
		Random ran = new Random();
		int r = ran.nextInt(7) + 1;
		String palabra = "";
		int error = 0;
		String letra = "";
		char l;
        //Lista de palabras que se seleccionan de forma aleatoria
		switch (r) {

		case 1:
			palabra = "malviviendo";
			break;
		case 2:
			palabra = "simpsons";
			break;
		case 3:
			palabra = "volvo";
			break;
		case 4:
			palabra = "aire";
			break;
		case 5:
			palabra = "television";
			break;
		case 6:
			palabra = "italia";
			break;
		case 7:
			palabra = "florencia";
			break;

		}

		char[] psecreta = palabra.toCharArray();
		int aciertos = 0;
		System.out.println("Instrucciones: Este juego consiste en adivinar la palabra escondida con"
				         + " el menor numero de intentos");
		  
		System.out.println("Tamaño de la palabra es " + palabra.length());
		char[] aux = new char[palabra.length()];
		for (int i = 0; i < palabra.length(); i++) {
			aux[i] = '*';
		}
		while (error != 4) {
			for (int i = 0; i < palabra.length(); i++)
				System.out.print(aux[i]);
			System.out.println(" ingrese una letra " + ", Errores: " + error);
			letra = sc.next();
			l = letra.toCharArray()[0];

			if (comprobar(letra, palabra)) {

				for (int i = 0; i < palabra.length(); i++) {
					if (psecreta[i] == l) {
						aciertos++;
						aux[i] = l;
					}
				}
			} else {
				error++;
				if (error == 3) {
					System.out.println("Has perdido! la palabra es: " + palabra);
					break;
				}
			}

			if (aciertos == palabra.length()) {
				System.out.println("Has ganado!!! La palabra es: " + palabra);
				break;
			}
		}

	}
	public static boolean comprobar(CharSequence cs, String palabra) { 
        
		boolean comprobar;
		comprobar = palabra.contains(cs);
		return comprobar;
	}

	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		ahorcado a = new ahorcado();
		a.jugar();

	}
}

