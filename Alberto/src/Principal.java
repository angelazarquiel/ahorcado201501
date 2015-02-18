

import java.util.*;


public class Principal {

	public static void main(String[] args)  throws InterruptedException {
		Scanner teclado = new Scanner(System.in);
		
		
		System.out.println("Bienvenidos al juego del ahorcado");
		System.out.println("Tiene que adiviniar esta palabra");
		char letra = '_';
		
		Constructor juego=new Constructor();
		juego.pasarLetrasPalabra();
		juego.pasarLetrasPalabraResuelta();
		
		while(true){
			
			juego.convertirAciertos(letra);
			
			juego.contarErrores(letra);
			
			System.out.println("Introduce una letra");
			
			letra=teclado.next().charAt(0); 
		}		
	}
}