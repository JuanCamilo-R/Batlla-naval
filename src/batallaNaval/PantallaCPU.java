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
	 * Coloca los barcos de CPU
	 * @param tamano the tamano
	 * @param barquito the barquito
	 */
	public void ponerBarco2(int tamano,Barcos barquito){
		Random aleatorio= new Random();
		Point direccion = new Point(0,0);
		int x=0,y=0;
		//Bucle que asigna las posiciones (x,y) de los barcos CPU
		do {
			x=aleatorio.nextInt(10);
			y=aleatorio.nextInt(10);
			switch(aleatorio.nextInt(4)) {
			case 0:
				//Derecha
				barquito.setDireccion("derecha");
				direccion.setLocation(0, 1);
				break;
			case 1:
				//Arriba
				barquito.setDireccion("arriba");
				direccion.setLocation(1,0);
				break;
			case 2:
				//Izquierda
				barquito.setDireccion("izquierda");
				direccion.setLocation(0,-1);
				break;
			case 3:
				//Abajo
				barquito.setDireccion("abajo");
				direccion.setLocation(-1,0);
				break;
			}
		}while(!Disponible(x,y,direccion,tamano));
		
		//Se anaden los barcos CPU en la pantallaCPU (matriz enemiga)
		for(int i=0 ;i < tamano; i++){
			barquito.anadir(new Point( y+direccion.x*i, x+direccion.y*i));
			barcosPantalla[y+direccion.x*i][x+direccion.y*i] = barquito;
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
	 * @param direccion the direccion
	 * @param tamano the tamano
	 * @return true, if successful
	 * Verifica que la posicion x, y generada en la funcion poner barco es valida
	 */
	private boolean Disponible(int x, int y, Point direccion, int tamano) {
		// TODO Auto-generated method stub
		for (int i = 0; i < tamano; i++)
		{
			if(y+direccion.x*i<0||y+direccion.x*i>9)
			{
				return false;
			}
			if(x+direccion.y*i<0||x+direccion.y*i>9)
			{
				return false;
			}
			if(barcosPantalla[y+direccion.x*i][x+direccion.y*i]!=null)
			{
				return false;
			}
		}
		return true;
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
	
}
