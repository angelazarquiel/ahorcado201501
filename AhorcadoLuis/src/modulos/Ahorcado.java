package modulos;

import java.util.ArrayList;
import java.util.Scanner;

import graphics.*;

// 6 intentos
public class Ahorcado {

	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);
		boolean acierto, gana = false;
		int oportunidades = 6, contAciertos = 0, posx = 150, posy = 100;
		Line pie, palo, barra, pequeño;
		ArrayList<String> letraAñadida = new ArrayList<String>();
		Picture foto;
		Text dibujar;
		foto = new Picture("imagen.jpg");
		foto.draw();
		pie = new Line(20, 150, 130, 150);
		pie.draw();
		palo = new Line(35, 150, 35, 20);
		palo.draw();
		barra = new Line(35, 20, 90, 20);
		barra.draw();
		pequeño = new Line(90, 20, 90, 40);
		pequeño.draw();

		String palabra;
		do{
		 palabra = eliminarTildes(Aleatoria.palabra().toUpperCase());//generamos nueva palabra
		}while(palabra.indexOf(' ') != -1);//comprobamos que no haya espacios. Una vez salió "pepitas de oro"
		guion(palabra);
		System.out.println("Introduce una letra: ");
		while (oportunidades > 0 && gana == false) {
			acierto = false;//volvemos a poner el acierto a false por defecto

			String letra = teclado.nextLine().toUpperCase();
			
			char letr;
			letr = (char) letra.charAt(0);//convertimos a char

			if (Character.isLetter(letr)) {//comprovamos que solo introduzca una letra

				for (int i = 0; i < palabra.length(); i++) {  //comprobamos si la letra está

					if (letr == palabra.charAt(i)
							&& (!(letraAñadida.contains(letra)))) {
						acierto = true;
						contAciertos++;

						switch (i) {
						case 0:
							dibujar = new Text(23, 180, letra);
							dibujar.draw();
							continue;
						case 1:
							dibujar = new Text(43, 180, letra);
							dibujar.draw();
							continue;
						case 2:
							dibujar = new Text(63, 180, letra);
							dibujar.draw();
							continue;
						case 3:
							dibujar = new Text(83, 180, letra);
							dibujar.draw();
							continue;
						case 4:
							dibujar = new Text(103, 180, letra);
							dibujar.draw();
							continue;
						case 5:
							dibujar = new Text(123, 180, letra);
							dibujar.draw();
							continue;
						case 6:
							dibujar = new Text(143, 180, letra);
							dibujar.draw();
							continue;
						case 7:
							dibujar = new Text(163, 180, letra);
							dibujar.draw();
							continue;
						case 8:
							dibujar = new Text(183, 180, letra);
							dibujar.draw();
							continue;
						case 9:
							dibujar = new Text(203, 180, letra);
							dibujar.draw();
							continue;
						case 10:
							dibujar = new Text(223, 180, letra);
							dibujar.draw();
							continue;
						case 11:
							dibujar = new Text(243, 180, letra);
							dibujar.draw();
							continue;
						}
					}

				}
			} else {
				System.out.println("Introduce sólo letras");
			}
			if (!letraAñadida.contains(letra)) {//añadimos las letras introducidas a un array y las dibujamos
				letraAñadida.add(letra);
				Text dibujarLetra;
				dibujarLetra = new Text(posx, posy, letra);
				dibujarLetra.setColor(Color.RED);
				dibujarLetra.draw();
				posx += 12;

			} else {
				System.out
						.println("Ya habias introducido esa letra... Presta atencion");
			}
			if (acierto == false) {
				oportunidades--;
				System.out.println("Te quedan " + oportunidades
						+ " oportunidades.");

				switch (oportunidades) {
				case 5:
					Ellipse cabeza;
					cabeza = new Ellipse(85, 40, 10, 10);
					cabeza.draw();
					continue;
				case 4:
					Line cuerpo;
					cuerpo = new Line(90, 50, 90, 90);
					cuerpo.draw();
					continue;
				case 3:
					Line brazoiq;
					brazoiq = new Line(90, 50, 80, 70);
					brazoiq.draw();
					continue;
				case 2:
					Line brazodr;
					brazodr = new Line(90, 50, 100, 70);
					brazodr.draw();
					continue;
				case 1:
					Line pieiq;
					pieiq = new Line(90, 90, 100, 110);
					pieiq.draw();
					continue;
				case 0:
					Line piedr;
					piedr = new Line(90, 90, 80, 110);
					piedr.draw();
					Text msgPierde;
					msgPierde = new Text(150, 130,
							"¡Que lastima..! Has perdido");
					msgPierde.draw();
					msgPierde = new Text(150, 150,
							"La palabra era "+palabra);
					msgPierde.draw();
					System.out.println("El juego ha acabado");
					continue;

				}
			}

			if (contAciertos == palabra.length() && !(oportunidades == 0)) {
				gana = true;
				Text msgGana;
				msgGana = new Text(150, 130, "¡Enhorabuena! Has ganado");
				msgGana.draw();

			}

		} // cierre while
	}
	public static String eliminarTildes(String cadena) {
		   
	    String original = "áàäéèëíìïóòöúùuñÁÀÄÉÈËÍÌÏÓÒÖÚÙÜçÇ";
	    // Cadena de caracteres que reemplazarán los originales.
	    String sustituir = "aaaeeeiiiooouuunAAAEEEIIIOOOUUUcC";
	    String cadenaNueva = cadena;
	    for (int i=0; i<original.length(); i++) {
	        // Reemplazamos los caracteres especiales.
	    	cadenaNueva = cadenaNueva.replace(original.charAt(i), sustituir.charAt(i));
	    }
	    return cadenaNueva;
	}
	
	public static void guion(String palabra){
		Line guion;
		guion = new Line(20, 200, 30, 200);
		guion.draw();
		int x1 = 20, x2 = 30;
		for (int i = 1; i < palabra.length(); i++) {// generar los guiones bajos
			x1 += 20;
			x2 += 20;
			guion = new Line(x1, 200, x2, 200);
			guion.draw();
		}
	}
}
