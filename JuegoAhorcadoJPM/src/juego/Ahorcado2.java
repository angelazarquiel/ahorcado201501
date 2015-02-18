package juego;

import java.util.Arrays;
import java.util.Scanner;

import javax.swing.*;

import graphics.*;

public class Ahorcado2 {

	public static Scanner teclado = new Scanner(System.in);
	public static int fallosPermitidos = 6;

	public static char[] letras = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
			'I', 'J', 'K', 'L', 'M', 'N', 'Ñ', 'O', 'P', 'Q', 'R', 'S', 'T',
			'U', 'V', 'W', 'X', 'Y', 'Z' };
	public static boolean[] utilizadas = new boolean[28];
	
	public static void main(String[] args) {

		jugar();

	}

	public static void jugar() {
		// dibujar lienzo
		Rectangle lienzo = new Rectangle(10, 10, 400, 520);
		lienzo.setColor(Color.WHITE);
		lienzo.fill();

		Line patib1 = new Line(290, 500, 395, 500);
		Line patib2 = new Line(390, 500, 390, 200);
		Line patib3 = new Line(390, 200, 340, 200);
		Line patib4 = new Line(340, 200, 340, 220);
		patib1.draw();
		patib2.draw();
		patib3.draw();
		patib4.draw();

		Arrays.fill(utilizadas, false);
		utilizadas[27] = true; // no corresponde a ninguna letra, sirve para que
								// después entre en un bucle
		fallosPermitidos = 6;

		// elegir palabra que se tiene que adivinar
		String palabra = elegirPalabra();

		// crear un array con los caracteres de la palabra
		char[] caracteres = new char[palabra.length()];
		for (int i = 0; i < palabra.length(); i++) {
			caracteres[i] = palabra.charAt(i);
		}

		// crear un array para saber si los caracteres se han adivinado
		boolean[] adivinado = new boolean[palabra.length()];
		Arrays.fill(adivinado, false);

		// dibujar título
		Text titulo = new Text(100, 50, "Juego del ahorcado");
		titulo.grow(60, 30);
		titulo.draw();

		rondas(fallosPermitidos, palabra, caracteres, adivinado);

	}

	// elige una palabra al azar
	public static String elegirPalabra() {
		String palabra = new PalabraAleatoria().palabra();
		palabra = palabra.toUpperCase();
		// quitar las tildes
		String nuevaPalabra = "";
		for (int i = 0; i < palabra.length(); i++) {
			if (palabra.substring(i, i + 1).equals("Á"))
				nuevaPalabra = nuevaPalabra.concat("A");
			else if (palabra.substring(i, i + 1).equals("É"))
				nuevaPalabra = nuevaPalabra.concat("E");
			else if (palabra.substring(i, i + 1).equals("Í"))
				nuevaPalabra = nuevaPalabra.concat("I");
			else if (palabra.substring(i, i + 1).equals("Ó"))
				nuevaPalabra = nuevaPalabra.concat("O");
			else if (palabra.substring(i, i + 1).equals("Ú"))
				nuevaPalabra = nuevaPalabra.concat("U");
			else if (palabra.substring(i, i + 1).equals("Ü"))
				nuevaPalabra = nuevaPalabra.concat("U");
			else
				nuevaPalabra = nuevaPalabra.concat(palabra.substring(i, i + 1));
		}
		return nuevaPalabra;
	}

	public static void rondas(int fallosP, String palabra, char[] caracteres,
			boolean[] adivinado) {

		while (fallosP >= 1) {
			boolean hayCoincidencias = false;
			mostrarPalabra(caracteres, adivinado);
			char nuevaLetra = elegirLetra(); // elige una letra
			comprobar(caracteres, adivinado, nuevaLetra);
			if (comprobar(caracteres, adivinado, nuevaLetra)) {
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

			// elegir otra letra:
			if (!hayCoincidencias) {
				dibujar(fallosP);
				fallosP--;
			}
		}
		if (fallosP == 0) {
			JFrame frame = new JFrame("");
			String adivina = JOptionPane.showInputDialog(frame,
					"Puedes intentar adivinar la palabra una vez:");
			adivina = adivina.toUpperCase();
			if (adivina.equals(palabra)) {
				gana();
			} else {
				JOptionPane.showMessageDialog(frame,
						"Perdiste. La palabra correcta era:\n" + palabra);
				menuFinal();
			}
		}
	}

	public static void mostrarPalabra(char[] caracteres, boolean[] adivinado) {
		Rectangle taparPalabra = new Rectangle(10,150,240,100);
		taparPalabra.setColor(Color.WHITE);
		taparPalabra.fill();
		String palabraAMostrar = "";
		for (int i = 0; i < caracteres.length; i++) {
			if (!adivinado[i]) {
				palabraAMostrar = palabraAMostrar.concat("_ ");
			} else {
				palabraAMostrar = palabraAMostrar.concat(String.valueOf(caracteres[i]) + " ");
			}
		}
		Text palabraPantalla = new Text(70,200,palabraAMostrar);
		palabraPantalla.grow(20,20);
		palabraPantalla.draw();
	}

	public static char elegirLetra() {
		for (int i = 0; i < 27; i++) {
			Text letra_ = new Text(i * 9 + 10, 300, String.valueOf(letras[i]));
			if (utilizadas[i]) {
				letra_.setColor(Color.LIGHT_GRAY);
				letra_.draw();
			} else {
				letra_.setColor(Color.BLACK);
				letra_.draw();
			}
		}
		// pedir letra
		int posicionCaracterElegido = 27; // fuerza a que se meta en el
											// siguiente bucle la primera vez
		while (utilizadas[posicionCaracterElegido]) {

			String letraElegida = "";
			JFrame frame = new JFrame("");
			while(letraElegida.equals("")) {
			letraElegida = JOptionPane.showInputDialog(frame, "Elige una letra");
			}
			if (letraElegida.equals("*"))
				System.exit(0);
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
		JFrame frame = new JFrame("");
		JOptionPane.showMessageDialog(frame, "Enhorabuena, lo has conseguido.");
		menuFinal();
	}

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

	public static void dibujar(int num) {
		if (num == 6) {
			Ellipse cabeza = new Ellipse(310, 220, 60, 60);
			cabeza.draw();
		} else if (num == 5) {
			Line tronco = new Line(340, 280, 340, 380);
			tronco.draw();
		} else if (num == 4) {
			Line brazoIzq = new Line(340, 300, 300, 320);
			brazoIzq.draw();
		} else if (num == 3) {
			Line brazoDer = new Line(340, 300, 380, 320);
			brazoDer.draw();
		} else if (num == 2) {
			Line piernaIzq = new Line(340, 380, 330, 440);
			piernaIzq.draw();
		} else if (num == 1) {
			Line piernaIzq = new Line(340, 380, 350, 440);
			piernaIzq.draw();
		}
	}

	public static void menuFinal() {

		int opcion = 0;
		while (opcion != 1 && opcion != 2) {
			JFrame frame = new JFrame("");
			String opcionCadena = JOptionPane.showInputDialog(frame,
					"Introduce 1 para nuevo juego, 2 para salir");
			if (!Character.isDigit(opcionCadena.charAt(0)))
				opcion = 0;
			else
				opcion = Integer.parseInt(opcionCadena);
		}
		if (opcion == 1)
			jugar();
		else
			System.exit(0);
	}
	
	
}
