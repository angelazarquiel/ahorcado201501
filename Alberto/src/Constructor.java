

import java.util.ArrayList;

public class Constructor {
	
	private String palabraOculta = "wikipedia";
	private ArrayList<Character> LetrasPalabra = new ArrayList <Character>();
	private ArrayList<Character> LetrasPalabraResuelta = new ArrayList <Character>();
	private ArrayList<Character> errores = new ArrayList <Character>();	
	
	public void pasarLetrasPalabraResuelta (){
		
        for(int i=0;i<palabraOculta.length();i++)
        {
            LetrasPalabraResuelta.add(palabraOculta.charAt(i));
        }
	}
	
	public ArrayList<Character> pasarLetrasPalabra (){
		
        for(int i=0;i<palabraOculta.length();i++)
        {
            LetrasPalabra.add('_');
        }
		return LetrasPalabra;
	}

	public void contarErrores (char letra){
		
			if(!(LetrasPalabraResuelta.contains(letra))){
				errores.add(letra);
			}
		 System.out.println("Tienes " + (errores.size()-1) + " errores");
	}
	
	public  void  convertirAciertos (char letra){
		
		 for(int i=0;i<palabraOculta.length();i++)
	        {	
			 	if (palabraOculta.charAt(i) == letra){
			 		LetrasPalabra.set( i, letra );
			 	}
	        }
		System.out.println(LetrasPalabra);
	}
}