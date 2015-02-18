package juego;

import java.util.*;

import graphics.*;

public class Ahorcado {

	// ****************************************************************************************
	// Método para devolver una palabra aleatoria
	public static String palabraAleatoria() {

		int x = (int) (Math.random() * 9 + 1);

		String[] palabras = new String[] { "pelota", "patata", "arroz",
				"reloj", "pulsera", "camiseta", "abanico", "medalla",
				"programacion" };

		final String palabraOculta;

		palabraOculta = palabras[x];
		palabraOculta.toUpperCase();

		return palabraOculta;
	}

	// ****************************************************************************************
	// Método para introducir letra
	public static String introducirLetra() {
		Scanner teclado = new Scanner(System.in);
		String letra;
		
		System.out.println("Introduce una letra");
		letra = teclado.nextLine();
		
		return letra;
	}

	// ****************************************************************************************
	// Método para introducir letra y comprobar si está
	public static boolean comprobarLetra(String letra, String palabraOculta) {
		boolean esta = false;

		if (palabraOculta.contains(letra)) {
			esta = true;
		}

		return esta;
	}

	// ****************************************************************************************	

	public static void main(String[] args) {
		
		Scanner teclado = new Scanner(System.in);
		
		//Mensaje de bienvenida y dibujar horca
		Text mensaje = new Text(200, 50, "JUEGO DEL AHORCADO");
		mensaje.grow(200, 75);
		mensaje.draw();
		Rectangle palo = new Rectangle(50, 150, 25, 300);
		palo.fill();
		Rectangle palo1 = new Rectangle(50, 150, 150, 25);
		palo1.fill();
		
		final String palabraOculta = palabraAleatoria();
		System.out.println(palabraAleatoria());
		int contador = 0;
		
		while(contador < 7){
		final String letra = introducirLetra();
		
		comprobarLetra(letra, palabraOculta);
		boolean esta = comprobarLetra(letra, palabraOculta);
		if(esta == false){
			contador++;
			System.out.println("La letra " + letra + " no está contenida en la palabra.");
			System.out.println("Tienes " + contador + " fallos");
		}else if(esta == true){
			System.out.println("La letra " + letra + " está en la palabra!");
		}
		
		Line cuerda = new Line(150, 250, 150, 150);
		Ellipse cabeza = new Ellipse(150, 225, 50, 50);
		Line cuerpo = new Line(160, 270, 160, 350);
		Line brazo1 = new Line(160, 280, 140, 350);
		Line brazo2 = new Line(160, 280, 180, 350);
		Line pierna1 = new Line(140, 400, 160, 350);
		Line pierna2 = new Line(180, 400, 160, 350);

		if(contador == 1) {
			cuerda.draw();
		}
		if(contador == 2) {
			cuerda.draw();
			cabeza.draw();
		}
		if(contador == 3) {
			cuerda.draw();
			cabeza.draw();
			cuerpo.draw();
		}
		if(contador == 4) {
			cuerda.draw();
			cabeza.draw();
			cuerpo.draw();
			brazo1.draw();
		}
		if(contador == 5) {
			cuerda.draw();
			cabeza.draw();
			cuerpo.draw();
			brazo1.draw();
			brazo2.draw();
		}
		if(contador == 6) {
			cuerda.draw();
			cabeza.draw();
			cuerpo.draw();
			brazo1.draw();
			brazo2.draw();
			pierna1.draw();
		}
		if(contador == 7) {
			cuerda.draw();
			cabeza.draw();
			cuerpo.draw();
			brazo1.draw();
			brazo2.draw();
			pierna1.draw();
			pierna2.draw();
		}
	}}
	
}	