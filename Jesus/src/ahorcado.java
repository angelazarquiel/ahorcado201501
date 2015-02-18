

import javax.swing.JOptionPane;

public class ahorcado {

	public static void main(String[] args) {
		
		String palabra[]={"gato","perro", "iguana", "leon", "tigre", "oso"};
		
		int aleatorio;
		aleatorio=(int)(Math.random()*10);
		
		String respuesta;
		String introducidas="";
		
		char prueba[]=new char[palabra[aleatorio].length()];
		
		for (int x=0;x<palabra[aleatorio].length();x++){
			prueba[x]='_';
		}
		int cont=6;
		int cont2=0;
		int salir=0;
		
		System.out.println("Bienvenido a el juego del ahorcado.");
		System.out.println("En este caso el tema es de animales.");
		System.out.println("Usted tiene 6 intentos para adivinar la palabra.");
		System.out.println("Usa solo minusculas.");
		
		for(int i=0;i<palabra[aleatorio].length();i++){
			System.out.print(prueba[i]+" ");
		}
		
		System.out.println(" ");
		
		while (cont>0){
			respuesta=JOptionPane.showInputDialog("Ingrese una letra.");
			introducidas=introducidas+respuesta.charAt(0);
			
			System.out.println("Has introducido ya estas letras: " + introducidas);
			
			System.out.println(" ");
			
			for (int j=0;j<palabra[aleatorio].length();j++){
				if(respuesta.charAt(0)==palabra[aleatorio].charAt(j)){
					prueba[j]=palabra[aleatorio].charAt(j);
					cont2++;
				}	
			}
			System.out.println("");
			if(cont2<1){
				cont--;
				System.out.println("Esa letra no esta en la palabra, intentalo de nuevo.");
				System.out.println("Le quedan " + cont + "intentos");
				cont2=0;
			}
			else{
				System.out.println("La letra existe.");
				System.out.println("Ingresa una nueva letra");
				cont2=0;
			}
			for (int k=0;k<palabra[aleatorio].length();k++){
				System.out.print(prueba[k]+ " ");
			}
			for (int l=0;l<palabra[aleatorio].length();l++){
				if(palabra[aleatorio].charAt(l)==prueba[l]){
					salir++;
				}
				else{
					salir=0;
				}
				if(salir==palabra[aleatorio].length()){
					System.out.println("Ha ganado!!! Felicidades! :D");
					System.out.println("FIN DEL JUEGO.");
					cont=0;
				}
				cont2=0;
			}
		}
		if(salir<palabra[aleatorio].length()){
			System.out.println("Ha perdido :(");
			System.out.println("FIN DEL JUEGO.");
		}
		
	}

}
