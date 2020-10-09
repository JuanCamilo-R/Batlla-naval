/*
 * Jennyfer Belalcazar 		- 1925639-3743
 * Samuel Riascos Prieto 	- 1922540-3743
 * Juan Camilo Randazzo		- 1923948-3743
 */
package batallaNaval;

import java.util.ArrayList;
import java.util.Random;
import java.awt.Point;

// TODO: Auto-generated Javadoc
/**
 * The Class PantallaCPU.
 */
//Heredo de PantallaUsuario
public class PantallaCPU extends PantallaUsuario {
	
	
	
	/** The posiciones tocadas. */
	//Atributos
	private ArrayList<Point> posicionesTocadas = new ArrayList<Point>();
	
	/** The barcos pantalla. */
	private Barcos [][] barcosPantalla = new Barcos[10][10];
	
	/** The tamano. */
	private int tamano;
	
	/** The ronda. */
	private int ronda;
	

	
	/**
	 * Instantiates a new pantalla CPU.
	 */
	//Metodos
	public PantallaCPU() {
		tamano =1;
		ronda=1;
	}
	
	/**
	 * Barco atacado.
	 *
	 * @param x the x
	 * @param y the y
	 */
	public void barcoAtacado(int x, int y) {
		barcosPantalla[x][y].atacado();
	}
	
	/**
	 * Poner barco 2.
	 *
	 * @param tamano the tamano
	 * @param barquito the barquito
	 */
	public void ponerBarco2(int tamano,Barcos barquito){
		Random aleatorio= new Random();
		Point dirección = new Point(0,0);
		int x=0,y=0;
		do {
			x=aleatorio.nextInt(10);
			y=aleatorio.nextInt(10);
			switch(aleatorio.nextInt(4)) {
			case 0:
				//Derecha
				barquito.setDireccion("derecha");
				dirección.setLocation(0, 1);
				break;
			case 1:
				//Abajo
				barquito.setDireccion("abajo");
				dirección.setLocation(1,0);
				break;
			case 2:
				//Izquierda
				barquito.setDireccion("izquierda");
				dirección.setLocation(0,-1);
				break;
			case 3:
				//Arriba
				barquito.setDireccion("arriba");
				dirección.setLocation(-1,0);
				break;
			}
		}while(!Disponible(x,y,dirección,tamano));
		
		for(int i=0 ;i < tamano; i++){
			barcosPantalla[y+dirección.x*i][x+dirección.y*i] = barquito;
		}
		
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
	 * Atacar barco.
	 *
	 * @param posicion the posicion
	 */
	public void atacarBarco(Point posicion) {
		barcosPantalla[posicion.x][posicion.y].atacar(posicion);
	}

	/**
	 * Disponible.
	 *
	 * @param x the x
	 * @param y the y
	 * @param dirección the dirección
	 * @param tamano the tamano
	 * @return true, if successful
	 */
	private boolean Disponible(int x, int y, Point dirección, int tamano) {
		// TODO Auto-generated method stub
		for (int i = 0; i < tamano; i++)
		{
			if(y+dirección.x*i<0||y+dirección.x*i>9)
			{
				return false;
			}
			if(x+dirección.y*i<0||x+dirección.y*i>9)
			{
				return false;
			}
			if(barcosPantalla[y+dirección.x*i][x+dirección.y*i]!=null)
			{
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Mostrar 2.
	 */
	public void mostrar2() {
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				if(barcosPantalla[i][j] != null) {
					System.out.print(barcosPantalla[i][j].getTamano()+" ");
				}else {
					System.out.print("0 ");
				}
			}
			System.out.print("\n");
		}
	}
	
}
