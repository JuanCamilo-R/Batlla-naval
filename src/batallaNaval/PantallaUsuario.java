/*
 * Jennyfer Belalcazar 		- 1925639-3743
 * Samuel Riascos Prieto 	- 1922540-3743
 * Juan Camilo Randazzo		- 1923948-3743
 */
package batallaNaval;

import java.util.ArrayList;
import java.awt.Point;

public class PantallaUsuario {
//Atributos
private Barcos [][] barcosPantalla = new Barcos[10][10];

	
	//Metodos
	public PantallaUsuario() {
		
	}
	protected boolean estaDisponible(int x1,int y1,int x2, int y2,int tamano) {
		if(barcosPantalla[x1][y1]==null && barcosPantalla[x2][y2]==null) {
			if(tamano > 2 && verificarMitad( x1,y1,x2,y2, tamano)) {
				return true;
			}
			return true;
		}
		
		return false;
		
	}
	
	public boolean hayBarco(int x, int y) {
		if(barcosPantalla[x][y] != null) {
			return true;
		}
		return false;
	}
	public boolean barcoVivo(int x, int y) {
		return barcosPantalla[x][y].isVivo();
	}
	protected boolean verificarMitad(int x1,int y1,int x2, int y2,int tamano) {
		
		int distancia = (int) Math.sqrt(Math.pow(x2-x1,2)+Math.pow(y2-y1,2)) + 1;
		
		if( x1 == x2 && distancia == tamano) {
			for(int i = 0; i <= (y2-y1-1); i++) {
				if(barcosPantalla[x1][y1+i] != null) {
					return false;
				}
			}
			return true;
		}
		else if( y1 == y2 && distancia == tamano) {
			for(int i = 1; i <= (x2-x1-1); i++) {
				if(barcosPantalla[x1+i][y1] != null) {
					return false;
				}
			}
			return true;
		}
		
		return false;
	}
	
	public void mostrar() {
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				if(barcosPantalla[i][j] != null) {
					System.out.print("1");
				}else {
					System.out.print("0");
				}
			}
			System.out.print("\n");
		}
	}
	//1er numero > 2ndo numero //Izquierda
	//1er numero < 2nd numero //Derecha
	//[1,3] [1,5]  1 == 1, 3 < 5 //Derecha
	//[1,3] [2,4] 1 == 2 ? , 3 == 4 ?
	//[1,5] [1,3] 1 == 1, 5 > 3 //Izquierda
	public void ponerBarco(int x1,int y1,int x2, int y2, int tamano, Barcos barco) {
		if(estaDisponible(x1,x2,y1,y2,tamano)) {
			int contador = 0;
			do {
				if(x1 == x2) {
					barco.anadir(new Point(x1,y1+contador));
					barcosPantalla[x1][y1+contador] = barco;
				}
				if(y1 == y2) {
					barco.anadir(new Point(x1,y1+contador));
					barcosPantalla[x1+contador][y1] = barco;
				}
				contador++;
			}while(contador != tamano);
		}
	}
	
}
