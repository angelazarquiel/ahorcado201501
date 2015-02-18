package Juego;

import graphics.*;

import java.util.Scanner;
import java.util.Arrays;

import javax.swing.JOptionPane;

public class Ahorcado {

	public static void main(String[] args) throws InterruptedException {
		Scanner teclado = new Scanner(System.in);
		String palabraOculta = generarPalabra();
		String[] letras = new String[32];
		int contadorVictoria=palabraOculta.length();
		int contadorDerrota=6;
		boolean esta=false;
		String letra; 
		
		Arrays.fill(letras, "-");
		
		dibujarLineas(palabraOculta.length());
		ahorcadoDibujo();
		
		//System.out.println("Intenta adivinar la palabra !!");
		
	
		for(int i=0;contadorDerrota!=0;i++){
			//letra = teclado.nextLine();
			letra = JOptionPane.showInputDialog("Introduce una letra !!");
			letra = letra.toUpperCase();
			for(int j=0;j<letras.length;j++){
				if(i!=j){
				if(letra.equals(letras[j])){
				esta=true;
				break;
				}else esta=false;
			}}
			
			
			if(!esta){
				letras[i]=letra;
				boolean acierto = comprobarLetra(letra,palabraOculta);
				if(acierto){
					contadorVictoria= contadorVictoria-dibujarLetra(letra.charAt(0),palabraOculta,letra);
					if(contadorVictoria==0){
						victoria();
						break;
						 }
				}else{
					dibujarLetraFallada(contadorDerrota,letra);
					contadorDerrota-=dibujarAhorcado(contadorDerrota);
					//System.out.println("Te quedan " + contadorDerrota + " intentos");
					if(contadorDerrota==0){
						derrota(palabraOculta);
						break;
						 }
				}
			}else{
				//System.out.println("Ya has introducido ese valor !!");
			}
		
				}
		}
//----------------------METODOS--------------------------------------------METODOS--------------------------------------------METODOS----------------------\\
	
	public static void ahorcadoDibujo(){
		Rectangle cuadrado;
		cuadrado = new Rectangle(300,400,300,30);
		cuadrado.fill();
		Rectangle cuadrado2;
		cuadrado2 = new Rectangle(300,400,30,550);
		cuadrado2.fill();
		Line linea;
		linea = new Line(590,430,590,520);
		linea.draw();
		Text texto;
		texto = new Text(900,70, "LETRAS FALLADAS");
		texto.setColor(new Color(255,0,0));
		texto.draw();
		texto.grow(70,70);
		
	}
	public static String generarPalabra(){
		String[] palabra={"PATATA","COCHE","FUTBOL","ARROZ","PELOTA","FELIZ","MANZANA","MOTO","RADIO","PASTOR","ATASCO","NARANJA","FIGURA","CARTERA","MOCHILA","PESCADO"};
		int aleatorio =(int) Math.floor((Math.random() * 15) + 0);
		return palabra[aleatorio];
	}
	public static void dibujarLineas(int longitud){
		for(int i=0;longitud>0;i++){
		Line linea;
		linea = new Line(80+(110*i),100,150+(110*i),100);
		linea.draw();
		longitud--;
		}
	}
	public static boolean comprobarLetra(String letra, String palabraOculta){
		boolean acierto=false;
		if(palabraOculta.contains(letra))
		acierto=true;
		return acierto;
}
	public static int dibujarLetra(char letra,String palabra,String letraDibujo){
		int contadorVictoria = 0;
		for(int i=0;i<palabra.length();i++){
			if(palabra.charAt(i)==letra){
				Text texto;
				texto = new Text(110+(i*110), 70, letraDibujo);
				texto.draw();
				texto.grow(30,30);
				contadorVictoria++;
			}

			}
		return contadorVictoria;
	}
	public static void dibujarLetraFallada(int contador,String letraDibujo){
	
				Text texto;
				texto = new Text(330+(contador*110), 170, letraDibujo);
				texto.setColor(new Color(255,0,0));
				texto.draw();
				texto.grow(30,30);
				
			}
	public static int dibujarAhorcado(int contadorDerrota){
		if(contadorDerrota==6){
			Ellipse cabeza;
			cabeza = new Ellipse(546,520,90,90);
			cabeza.draw();
			}
		if(contadorDerrota==5){
			Line cuerpo;
			cuerpo = new Line(590,610,590,800);
			cuerpo.draw();
			}
		if(contadorDerrota==4){
			Line brazoIzquierdo;
			brazoIzquierdo = new Line(500,680,590,610);
			brazoIzquierdo.draw();
			}
		if(contadorDerrota==3){
			Line brazoDerecho;
			brazoDerecho = new Line(590,610,680,680);
			brazoDerecho.draw();
			}
		if(contadorDerrota==2){
			Line piernaIzquierda;
			piernaIzquierda = new Line(500,870,590,800);
			piernaIzquierda.draw();
			}
		if(contadorDerrota==1){
			Line piernaDerecha;
			piernaDerecha = new Line(590,800,680,870);
			piernaDerecha.draw();
			}
		contadorDerrota =1;
		return contadorDerrota;	
	}
	public static void derrota(String palabra) throws InterruptedException{
		int z=0;
		
		Rectangle cuadrado;
		cuadrado = new Rectangle(700,500,300,200);
		cuadrado.fill();
		
		Rectangle cuadrado2;
		cuadrado2 = new Rectangle(722,522,260,160);
		cuadrado2.setColor(new Color(255,0,0));
		cuadrado2.fill();
		
		
		Text texto;
		texto = new Text(810,580, "Has perdido");
		texto.setColor(new Color(255,255,255));
		texto.draw();
		texto.grow(70,70);
		
		Text texto2;
		texto2 = new Text(810,380, "La palabra era " + palabra);
		texto2.setColor(new Color(0,255,0));
		texto2.draw();
		texto2.grow(80,50);
		
		while(z==0){
		for(int x=0;x<5;x++){
			cuadrado.grow(x, x);
			Thread.sleep(15);
			cuadrado2.grow(x,x);
			Thread.sleep(15);
		}
		for(int y=0;y<5;y++){
			cuadrado.grow(-y, -y);
			Thread.sleep(15);
			cuadrado2.grow(-y,-y);
			Thread.sleep(15);
		}}
	}
	public static void victoria() throws InterruptedException{
		int z=0;
		
		Rectangle cuadrado;
		cuadrado = new Rectangle(700,500,300,200);
		cuadrado.fill();
		
		Rectangle cuadrado2;
		cuadrado2 = new Rectangle(722,522,260,160);
		cuadrado2.setColor(new Color(0,255,0));
		cuadrado2.fill();
		
		
		Text texto;
		texto = new Text(810,580, "Has ganado");
		texto.setColor(new Color(255,255,255));
		texto.draw();
		texto.grow(70,70);
		
		while(z==0){
		for(int x=0;x<5;x++){
			cuadrado.grow(x, x);
			Thread.sleep(15);
			cuadrado2.grow(x,x);
			Thread.sleep(15);
		}
		for(int y=0;y<5;y++){
			cuadrado.grow(-y, -y);
			Thread.sleep(15);
			cuadrado2.grow(-y,-y);
			Thread.sleep(15);
		}}
	}
///////	
}
