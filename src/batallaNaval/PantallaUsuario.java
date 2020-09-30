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
			System.out.println("Entro a esta Disponible");
			if(tamano > 2) {
				if(verificarMitad(x1, y1, x2, y2, tamano)) {
					return true;
				}
				return false;
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
		System.out.println("Verificando mitad");
		int distancia = ((int) Math.sqrt(Math.pow(x2-x1,2)+Math.pow(y2-y1,2))) + 1;
		System.out.println("Distancia: "+distancia);
		int dirección = 1;
		if(x1==x2) {
			if(y2-y1<0) {
				dirección=-1;
			}
		}
		if(y1==y2) {
			if(x2-x1<0) {
				dirección=-1;
			}
		}
		if( x1 == x2 && distancia == tamano) {
			System.out.println("Horizontal");
			for(int i = 0; i <= Math.abs(y2-y1)-1; i++) {
				System.out.println("Verificando posición x: "+(x1)+",y: "+(y1+i*dirección));
				if(barcosPantalla[x1][y1+i*dirección] != null) {
					System.out.println("Ocupado");
					return false;
			 	}
			}
			return true;
		}
		else if( y1 == y2 && distancia == tamano) {
			System.out.println("Vertical");
			for(int i = 1; i <= Math.abs(x2-x1)-1; i++) {
				System.out.println("Verificando posición x: "+(x1+i*dirección)+",y: "+(y1));
				if(barcosPantalla[x1+i*dirección][y1] != null) {
					System.out.println("Ocupado");
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
					System.out.print("1 ");
				}else {
					System.out.print("0 ");
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
		if(estaDisponible(x1,y1,x2,y2,tamano)) {
			int contador = 0;
			int dirección = 1;
			if(x1==x2) {
				if(y2-y1<0) {
					System.out.println("Dirección izquierda");
					barco.setDireccion("izquierda");
					dirección=-1;
				}
				else {
					System.out.println("Dirección derecha");
					barco.setDireccion("derecha");
				}
			}
			else if(y1==y2) {
				if(x2-x1<0) {
					System.out.println("Dirección abajo");
					barco.setDireccion("abajo");
					dirección=-1;
				}
				else {
					System.out.println("Dirección arriba");
					barco.setDireccion("arriba");
				}
			}
			do {
				if(x1 == x2) {
					barco.anadir(new Point(x1,y1+contador*dirección));
					barcosPantalla[x1][y1+contador*dirección] = barco;
					System.out.println("Puso el barco en x1: "+x1+",y1: "+(y1+contador*dirección));
				}
				if(y1 == y2) {
					barco.anadir(new Point(x1+contador*dirección,y1));
					barcosPantalla[x1+contador*dirección][y1] = barco;
					System.out.println("Puso el barco en x1: "+(x1+contador*dirección)+",y1: "+y1);
				}
				System.out.print("Puso el barco \n");
				contador++;
			}while(contador != tamano);
			System.out.println("Puso el barco entre x1: "+x1+",y1: "+y1+" y x2:"+x2+",y2: "+y2);
		}
		else {
		System.out.println("No he colocado nada");
		}
	}
	
}
