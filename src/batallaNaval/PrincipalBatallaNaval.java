/*
 * Jennyfer Belalcazar 		- 1925639-3743
 * Samuel Riascos Prieto 	- 1922540-3743
 * Juan Camilo Randazzo		- 1923948-3743
 */
package batallaNaval;

import java.awt.EventQueue;

//Clase para ejecutar el codigo.
public class PrincipalBatallaNaval {

	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		//La clase Evenqueue es capaz de encolar eventos de forma asincrona
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
		    	// TODO Auto-generated method stub
			    //Se crea un objeto de tipo vista para visualizar el juego
				 VistaGUIBatallaNaval vistaGUIBatallaNaval = new VistaGUIBatallaNaval();
			}
						
		});
	}
		
}
		
			
		
	

