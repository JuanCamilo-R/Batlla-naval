/*
 * Jennyfer Belalcazar 		- 1925639-3743
 * Samuel Riascos Prieto 	- 1922540-3743
 * Juan Camilo Randazzo		- 1923948-3743
 */
package batallaNaval;

import java.util.ArrayList;
import java.awt.Point;

// TODO: Auto-generated Javadoc
/**
 * The Class PantallaUsuario.
 */
public class PantallaUsuario {
	
/** The barcos pantalla. */
//Atributos
private Barcos [][] barcosPantalla = new Barcos[10][10];

	
	/**
	 * Instantiates a new pantalla usuario.
	 */
	//Metodos
	public PantallaUsuario() {
		
	}
	
	/**
	 * Esta disponible.
	 *
	 * @param x1 the x 1
	 * @param y1 the y 1
	 * @param x2 the x 2
	 * @param y2 the y 2
	 * @param tamano the tamano
	 * @return true, if successful
	 */
	protected boolean estaDisponible(int x1,int y1,int x2, int y2,int tamano) {
		//Calcula la distancia entre los puntos (x1,y1) y (y1,y2)
		int distancia = ((int) Math.sqrt(Math.pow(x2-x1,2)+Math.pow(y2-y1,2))) + 1;
		//Pregunta si los puntos dados estan disponibles, dado su tamano y que los puntos no esten en diagonal
		if(barcosPantalla[x1][y1]==null && barcosPantalla[x2][y2]==null && tamano ==distancia && (x1==x2 || y1==y2 )) {
			//Pregunta si tamano es mayor a 2
			if(tamano > 2) {
				//Pregunta si las posiciones entre los puntos (x1,y1) y (x2,y2) estan disponibles
				if(verificarMitad(x1, y1, x2, y2, tamano)) {
					return true;
				}
				return false;
			}
			return true;
		}
		
		return false;
		
	}
	
	/**
	 * Hay barco.
	 *
	 * @param x the x
	 * @param y the y
	 * @return true, if successful
	 */
	public boolean hayBarco(int x, int y) {
		if(barcosPantalla[x][y] != null) {
			return true;
		}
		return false;
	}
	
	/**
	 * Barco vivo.
	 *
	 * @param x the x
	 * @param y the y
	 * @return true, if successful
	 */
	public boolean barcoVivo(int x, int y) {
		return barcosPantalla[x][y].isVivo();
	}
	
	/**
	 * Verificar mitad. Verifica que las posiciones entre los puntos
	 *(x1,y1) y (x2,y2) esten disponibles
	 *
	 * @param x1 the x 1
	 * @param y1 the y 1
	 * @param x2 the x 2
	 * @param y2 the y 2
	 * @param tamano the tamano
	 * @return true, if successful
	 */
	protected boolean verificarMitad(int x1,int y1,int x2, int y2,int tamano) {
		//Define la direccion en la que se van a verificar las posiciones
		//1 si es de izquierda a derecha o de arriba hacia abajo
		//-1 si es de derecha a izquierda o de abajo hacia arriba
		int direccion = 1;
		//Pregunta si los puntos dados como parametros estan en la misma fila
		if(x1==x2) {
			//Pregunta si el segundo punto esta a la izquierda del primero
			if(y2-y1<0) {
				direccion=-1;
			}
			//Verifica que los puntos entre el primero y el segundo esten disponibles
			for(int i = 0; i <= Math.abs(y2-y1)-1; i++) {
				//Pregunta si el punto que se esta evaluando esta ocupado
				if(barcosPantalla[x1][y1+i*direccion] != null) {
					return false;
			 	}
			}
			return true;
		}
		//Pregunta si los puntos dados como parametros estan en la misma columna
		else if(y1==y2) {
			//Pregunta si el segundo punto esta arriba del primero
			if(x2-x1<0) {
				direccion=-1;
			}
			//Verifica que los puntos entre el primero y el segundo esten disponibles
			for(int i = 1; i <= Math.abs(x2-x1)-1; i++) {
				//Pregunta si el punto que se esta evaluando esta ocupado
				if(barcosPantalla[x1+i*direccion][y1] != null) {
					return false;
				}
			}
			return true;
		}
		
		return false;
	}
	
	/**
	 * Poner barco.
	 *
	 * @param x1 the x 1
	 * @param y1 the y 1
	 * @param x2 the x 2
	 * @param y2 the y 2
	 * @param tamano the tamano
	 * @param barco the barco
	 */
	//[1,5] [1,3] 1 == 1, 5 > 3 //Izquierda
	public void ponerBarco(int x1,int y1,int x2, int y2, int tamano, Barcos barco) {
		if(estaDisponible(x1,y1,x2,y2,tamano)) {
			int contador = 0;
			int direccion = 1;
			if(x1==x2) {
				if(y2-y1<0) {
					barco.setDireccion("izquierda");
					direccion=-1;
				}
				else {
					barco.setDireccion("derecha");
				}
			}
			else if(y1==y2) {
				if(x2-x1<0) {
					barco.setDireccion("abajo");
					direccion=-1;
				}
				else {
					barco.setDireccion("arriba");
				}
			}
			do {
				if(x1 == x2) {
					barco.anadir(new Point(x1,y1+contador*direccion));
					barcosPantalla[x1][y1+contador*direccion] = barco;
				}
				if(y1 == y2) {
					barco.anadir(new Point(x1+contador*direccion,y1));
					barcosPantalla[x1+contador*direccion][y1] = barco;
				}
				contador++;
			}while(contador != tamano);
			
			barco.setSeleccionado(true);
		}
	}
	
	/**
	 * Limpiar pantalla. Elimina todos los barcos de la pantalla
	 */
	public void limpiarPantalla() {
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				barcosPantalla[i][j] = null;
			}
		}
	}
	
	/**
	 * Gets the tamano barco.
	 *
	 * @param i the i
	 * @param j the j
	 * @return the tamano barco
	 */
	public int getTamanoBarco(int i, int j) {
		return barcosPantalla[i][j].getTamano();
	}
	
	/**
	 * Gets the barco.
	 *
	 * @param i the i
	 * @param j the j
	 * @return the barco
	 */
	public Barcos getBarco(int i, int j) {
		return barcosPantalla[i][j];
	}
	
	/**
	 * Atacar barco. 
	 * Ataca el barco presente en la posicion que recibe como parametro
	 *
	 * @param posicion the posicion
	 */
	public void atacarBarco(Point posicion) {
		barcosPantalla[posicion.x][posicion.y].atacado();
	}
	
}
