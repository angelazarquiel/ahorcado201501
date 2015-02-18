package Ahocado_Graphics;

import graphics2.*;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

public class Ahorcado {

		public static void main(String[] args) throws InterruptedException {
			int texto = 40;
			int fallos=0;
			String palabra;
			
			base();
			cabeza();
			cuerpo();
			brazo1();
			brazo2();
			pierna1();
			pierna2();
			
			//Mensaje de bienvenida creado en la librería Canvas
			Canvas.bienvenida();
			//Para borrar el dibujo anterior
			Rectangle borrar;
			borrar = new Rectangle (0, 0, 500, 600);
			borrar.setColor(Color.WHITE);
			borrar.fill();
			
			
			//Para que no se vean los caracteres al introducir la palabra clave
			JPasswordField jpf = new JPasswordField();
			
			
			//Introducir la palabra clave en ventana
			JOptionPane.showConfirmDialog(null, jpf,"Dime la  Palabra Secreta: ",JOptionPane.OK_OPTION);
			palabra=String.valueOf(jpf.getPassword());
			StringBuffer adivinada=new StringBuffer();
			
			//Poner tantos asteriscos como letras tenga la palabra secreta
			for(int i=0;i<palabra.length();i++){
				adivinada.append("*");
			}
			
			Text textoinicio;
			textoinicio = new Text (30, 10, "La Palabra tiene: " + palabra.length()+ " letras.");
			textoinicio.grow(20, 20);
			textoinicio.setColor(Color.BLACK);
			textoinicio.draw();
			
			//Dibujamos tantas líneas como letras tenga la palabara secreta
			for(int i=0;i<palabra.length();i++){
				Text lineas;
				lineas = new Text (texto, 50, "-");
				lineas.grow(10, 10);
				lineas.setColor(Color.BLACK);
				lineas.draw();
				texto = texto + 40;
			}
			
			while(fallos<8 && !palabra.equals(adivinada.toString())){
				Text numfallos;
				//mostrar los guiones en consola
				System.out.println(adivinada);
				//pido una letra
				char letra = JOptionPane.showInputDialog(null,"Introduce una letra: ").charAt(0);
				//Busco la letra y hago los cambios
				boolean cambios=false;
				for(int i=0;i<palabra.length();i++)
				{
					if(palabra.charAt(i)==letra)
					{
						//Dibujar las letras adivinadas
						adivinada.setCharAt(i, letra);
						Text adivin;
						adivin = new Text (60, 55,  ""+ adivinada + "" );
						adivin.grow(30, 30);
						adivin.draw();
						//Mensaje de acierto creado en la libreria Canvas
						Canvas.acierto();
						adivin.undraw();
						cambios=true;
					}
				}
				
				if(cambios==false){
					//Cuenta el número de fallos y los imprime en la ventana
					fallos++;
					numfallos = new Text (350, 550, "Tienes " + fallos + " fallo/s");		
					numfallos.grow(30, 30);
					numfallos.setColor(Color.RED);
					numfallos.draw();
					
					Rectangle recuadro;
					recuadro = new Rectangle(290, 515, 190, 90);
					recuadro.setThickness(5);
					recuadro.setColor(Color.RED);
					recuadro.draw();
					
					//Mensaje de fallo creado en la librería Canvas
					Canvas.fallo();
					numfallos.undraw();
					recuadro.undraw();
					//Mostrar número de fallos por consola
					System.out.println("Tienes "+ fallos +" fallo/s");
					
					//Segun el  número de fallos, llama a un método para ir creando el dibujo
					switch (fallos){
					case 0:
						break;
					case 1:
						base();
						break;
					case 2:
						cabeza();
						break;
					case 3:
						cuerpo();
						break;
					case 4:
						brazo1();
						break;
					case 5:
						brazo2();
						break;
					case 6:
						pierna1();
						break;
					case 7:
						pierna2();
						ultimaOportunidad();
						break;
					}	
				}	
			}//fin while
			
			if(fallos==8){
				//Mensaje en ventana si ganas
				Text perder;
				perder = new Text (300, 300, "¡¡¡¡HAS PERDIDO, LO SIENTO!!!!");
				perder.grow(120, 140);
				perder.setColor(Color.RED);
				perder.draw();
				
				//Mensaje por consola en caso de perder la partida
				System.out.println("HAS FALLADO");
			}
			else{
				Rectangle borrar2;
				borrar2 = new Rectangle (0, 0, 500, 600);
				borrar2.setColor(Color.WHITE);
				borrar2.fill();
				
				//Llamamos al método que se encargará de hacer saltar al muñeco
				moverMuñeco();
				
				//Mensaje por consola si ganas la partida
				System.out.println("¡¡¡¡HAS ACERTADO!!!!");
			}
		}
		public static void base() {
			Rectangle rectangulo;
			rectangulo = new Rectangle(100, 75, 250, 25);
			rectangulo.fill();
			Rectangle rectangulo2;
			rectangulo2 = new Rectangle(100, 75, 25, 500);
			rectangulo2.fill();
			Rectangle rectangulo3;
			rectangulo3 = new Rectangle(50, 575, 150, 25);
			rectangulo3.fill();
		}
		public static void cabeza() {
			Ellipse cabeza;
			cabeza = new Ellipse(300, 100, 50, 70);
			cabeza.setThickness(10f); 
			cabeza.setColor(Color.BLACK);
			cabeza.draw();
			Ellipse ojo1;
			ojo1 = new Ellipse(310, 120, 5, 5);
			ojo1.setColor(Color.BLACK);
			ojo1.fill();
			Ellipse ojo2;
			ojo2 = new Ellipse(335, 120, 5, 5);
			ojo2.setColor(Color.BLACK);
			ojo2.fill();
			Rectangle boca;
			boca = new Rectangle (315, 140, 20, 5);
			boca.setColor(Color.RED);
			boca.fill();
		}
		public static void cuerpo() {
			Rectangle cuello;
			cuello = new Rectangle(315, 170, 15, 25);
			cuello.fill();
			Rectangle cuerpo;
			cuerpo = new Rectangle(290, 195, 70, 110);
			cuerpo.setColor(Color.ORANGE);
			cuerpo.fill();
		}
		public static void brazo1() {
			Rectangle uno;
			uno = new Rectangle(240, 195, 60, 15);
			uno.fill();
		}
		public static void brazo2() {
			Rectangle dos;
			dos = new Rectangle(360, 195, 60, 15);
			dos.fill();
		}
		public static void pierna1() {
			Rectangle pierna1;
			pierna1 = new Rectangle(290, 300, 25, 100);
			pierna1.setColor(Color.BLUE);
			pierna1.fill();
		}
		public static void pierna2() {
			Rectangle pierna2;
			pierna2 = new Rectangle(335, 300, 25, 100);
			pierna2.setColor(Color.BLUE);
			pierna2.fill();
			
		}
		public static void ultimaOportunidad() {
			//Ultima oportunidad
					Text faltauno;
					faltauno = new Text (400, 40, "ULTIMA OPORTUNIDAD...");
					faltauno.grow(90, 70);
					faltauno.setColor(Color.RED);
					faltauno.draw();
		}
		public static void moverMuñeco() throws InterruptedException {
			
			Ellipse cabeza;
			cabeza = new Ellipse(300, 100, 50, 70);
			cabeza.setThickness(10f); 
			cabeza.setColor(Color.BLACK);
			cabeza.draw();
			Ellipse ojo1;
			ojo1 = new Ellipse(310, 120, 5, 5);
			ojo1.setColor(Color.BLACK);
			ojo1.fill();
			Ellipse ojo2;
			ojo2 = new Ellipse(335, 120, 5, 5);
			ojo2.setColor(Color.BLACK);
			ojo2.fill();
			Rectangle boca;
			boca = new Rectangle (315, 140, 20, 5);
			boca.setColor(Color.RED);
			boca.fill();
			
			Rectangle cuello;
			cuello = new Rectangle(315, 170, 15, 25);
			cuello.fill();
			Rectangle cuerpo;
			cuerpo = new Rectangle(290, 195, 70, 110);
			cuerpo.setColor(Color.ORANGE);
			cuerpo.fill();
			
			Rectangle uno;
			uno = new Rectangle(240, 195, 60, 15);
			uno.fill();
			Rectangle dos;
			dos = new Rectangle(360, 195, 60, 15);
			dos.fill();
		
			Rectangle pierna1;
			pierna1 = new Rectangle(290, 300, 25, 100);
			pierna1.setColor(Color.BLUE);
			pierna1.fill();
			
			Rectangle pierna2;
			pierna2 = new Rectangle(335, 300, 25, 100);
			pierna2.setColor(Color.BLUE);
			pierna2.fill();
			
			//Saltar hacia arriba
			int saltararriba = 0;
			while (saltararriba < 40){
			Thread.sleep(20);
			cabeza.translate(0, -10);
			ojo1.translate(0, -10);
			ojo2.translate(0, -10);
			boca.translate(0, -10);
			cuello.translate(0, -10);
			cuerpo.translate(0, -10);
			uno.translate(0, -10);
			dos.translate(0, -10);
			pierna1.translate(0, -10);
			pierna2.translate(0, -10);
			saltararriba++;
			}
			//Saltar hacia abajo
			int saltarabajo = 0;
			while (saltarabajo < 40){
			Thread.sleep(20);
			cabeza.translate(0, 10);
			ojo1.translate(0, 10);
			ojo2.translate(0, 10);
			boca.translate(0, 10);
			cuello.translate(0, 10);
			cuerpo.translate(0, 10);
			uno.translate(0, 10);
			dos.translate(0, 10);
			pierna1.translate(0, 10);
			pierna2.translate(0, 10);
			saltarabajo++;
			}
			
			//Si ganas la partida dibuja el texto
			Text ganador;
			ganador = new Text (200, 300, "¡¡¡¡HAS GANADO ENHORABUENA!!!!");
			ganador.grow(200, 150);
			ganador.setColor(Color.RED);
			ganador.draw();
		}
}

