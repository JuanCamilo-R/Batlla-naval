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
		int distancia = ((int) Math.sqrt(Math.pow(x2-x1,2)+Math.pow(y2-y1,2))) + 1;
		if(barcosPantalla[x1][y1]==null && barcosPantalla[x2][y2]==null && tamano ==distancia && (x1==x2 || y1==y2 )) {
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
	 * Verificar mitad.
	 *
	 * @param x1 the x 1
	 * @param y1 the y 1
	 * @param x2 the x 2
	 * @param y2 the y 2
	 * @param tamano the tamano
	 * @return true, if successful
	 */
	protected boolean verificarMitad(int x1,int y1,int x2, int y2,int tamano) {
		int direccion = 1;
		if(x1==x2) {
			if(y2-y1<0) {
				direccion=-1;
			}
		}
		if(y1==y2) {
			if(x2-x1<0) {
				direccion=-1;
			}
		}
		if( x1 == x2) {
			System.out.println("Horizontal");
			for(int i = 0; i <= Math.abs(y2-y1)-1; i++) {
				System.out.println("Verificando posición x: "+(x1)+",y: "+(y1+i*direccion));
				if(barcosPantalla[x1][y1+i*direccion] != null) {
					System.out.println("Ocupado");
					return false;
			 	}
			}
			return true;
		}
		else if( y1 == y2 ) {
			System.out.println("Vertical");
			for(int i = 1; i <= Math.abs(x2-x1)-1; i++) {
				System.out.println("Verificando posición x: "+(x1+i*direccion)+",y: "+(y1));
				if(barcosPantalla[x1+i*direccion][y1] != null) {
					System.out.println("Ocupado");
					return false;
				}
			}
			return true;
		}
		
		return false;
	}
	
	/**
	 * Mostrar.
	 */
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
					System.out.println("Dirección izquierda");
					barco.setDireccion("izquierda");
					direccion=-1;
				}
				else {
					System.out.println("Dirección derecha");
					barco.setDireccion("derecha");
				}
				System.out.print("Despues del else de derecha");
			}
			else if(y1==y2) {
				if(x2-x1<0) {
					System.out.println("Direccion abajo");
					barco.setDireccion("abajo");
					direccion=-1;
				}
				else {
					System.out.println("Direccion arriba");
					barco.setDireccion("arriba");
				}
			}
			System.out.println("Antes del do while");
			do {
				if(x1 == x2) {
					barco.anadir(new Point(x1,y1+contador*direccion));
					barcosPantalla[x1][y1+contador*direccion] = barco;
					System.out.println("Puso el barco en x1: "+x1+",y1: "+(y1+contador*direccion));
				}
				if(y1 == y2) {
					barco.anadir(new Point(x1+contador*direccion,y1));
					barcosPantalla[x1+contador*direccion][y1] = barco;
					System.out.println("Puso el barco en x1: "+(x1+contador*direccion)+",y1: "+y1);
				}
				System.out.print("Puso el barco \n");
				contador++;
			}while(contador != tamano);
			System.out.println("Puso el barco entre x1: "+x1+",y1: "+y1+" y x2:"+x2+",y2: "+y2);
			barco.setSeleccionado(true);
		}
		else {
		System.out.println("No he colocado nada");
		}
	}
	
	/**
	 * Limpiar pantalla.
	 */
	public void limpiarPantalla() {
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				barcosPantalla[i][j] = null;
			}
		}
	}
	
}
