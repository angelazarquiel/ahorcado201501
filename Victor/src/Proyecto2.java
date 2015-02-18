

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Proyecto2 {

	public static String PalabraAleatoria() {

		String pal = PalabraAleatoria.palabra();
		String palabra = pal.toLowerCase();
		palabra=palabra.replace('á', 'a');
		palabra=palabra.replace('é', 'e');
		palabra=palabra.replace('ó', 'o');
		palabra=palabra.replace('í', 'i');
		palabra=palabra.replace('ú', 'u');
		
		return palabra;

	}

	public static ArrayList<Character> ahorcado() {
		ArrayList<Character> arraypal = new ArrayList<Character>();
		String pal = PalabraAleatoria();
		for (int k = 0; k < pal.length(); k++) {
			arraypal.add(pal.charAt(k));

		}
		return arraypal;

	}

	public static ArrayList<Character> cifrado(int tam) {
		ArrayList<Character> arraycif = new ArrayList<Character>();

		for (int k = 0; k < tam; k++) {
			arraycif.add('_');

		}
		return arraycif;
	}
	public static ArrayList<Character> meterLetras(char caracterIntroducido,ArrayList palabraCifrada,ArrayList palabraNormal){
		ArrayList<Character> palabraNormal2 = new ArrayList<Character>();
		palabraNormal2= palabraNormal;

			for (int j = 0; j < palabraCifrada.size(); j++) {
				if (palabraNormal2.get(j) == caracterIntroducido)
					palabraCifrada.set(j, caracterIntroducido);

			}
		return palabraCifrada;
		
	}
	

	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);
		ArrayList<Character> palabraNormal = new ArrayList<Character>();
		ArrayList<Character> palabraCifrada = new ArrayList<Character>();
		ArrayList<Character> caracteresMetidos = new ArrayList<Character>();

		palabraNormal = ahorcado();
		int tamaño = palabraNormal.size();
		
		palabraCifrada = cifrado(tamaño);

		System.out.println("---Juego del ahorcado---");
		System.out.println("Debes de insertar letra a letra para adivinar la palabra");
		System.out.println(palabraCifrada);
		
		int contador = 6,contador2=0;

		while (palabraCifrada.contains('_') && contador > 0) {
			System.out.print("Introduce letras: ");
			String f = teclado.nextLine();
			char caracterIntroducido = (char) f.charAt(0);
			
			for (int j = 0; j < palabraCifrada.size(); j++) {
				if (palabraNormal.get(j) == caracterIntroducido){
					contador2++;
				}
					
				}
			if (contador2==0){
				contador--;
			}
			contador2=0;
			palabraCifrada=meterLetras(caracterIntroducido, palabraCifrada, palabraNormal);
		
			System.out.println(palabraCifrada);
			if (palabraCifrada.contains('_'))
				System.out.println("Te kedan " + contador + " intentos");
			if (contador == 0)
				System.out.println("---Fin---  la palabra era "
						+ palabraNormal);
			if (!(palabraCifrada.contains('_')))
				System.out.println("----Bien has acertado la palabra----");
		
		}
	}

}
