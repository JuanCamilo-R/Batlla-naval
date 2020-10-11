/*
 * Jennyfer Belalcazar 		- 1925639-3743
 * Samuel Riascos Prieto 	- 1922540-3743
 * Juan Camilo Randazzo		- 1923948-3743
 */
package batallaNaval;

import java.awt.Point;
import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class Barcos. Sirve para simular los barcos en pantalla
 */
public class Barcos {
	
	//atributos
	
	/** Tamano del barco. */
	private int tamano; 
	
	/** Las veces que ha sido atacado. */
	private int vecesTocado; 
	
	/** Indica si fue selecionado para poner en pantalla. */
	private boolean seleccionado; 
	
	/** Indica si no se ha undido. */
	private boolean vivo;
	
	/** Indica hacia que direccion esta( derecha,izquiera,abajo, arrriba). */
	private String direccion = "";
	
	/** Posicion donde me han atacado. */
	private Point  posicionAtacadas = new Point();
	
	/** Posiciones que usa el barco. */
	private ArrayList<Point> posiciones = new ArrayList<Point>();
	
	/**
	 * Constructor de Barcos
	 * Instantiates a new barcos.
	 *
	 * @param tamano the tamano
	 */
	//Metodos
	public Barcos(int tamano) {
		seleccionado = false;
		vecesTocado = 0;
		vivo = true;
		this.tamano = tamano;
	}
	
	/**
	 * Guarda las posiciones donde me han atacado
	 * Atacar.
	 * Recibe la posicion donde me atacaron de tipo Point
	 * @param posiciones the posiciones
	 */
	//Ataque aliado.
	public  void atacar(Point posiciones) {
		posicionAtacadas.setLocation(posiciones.getX(), posiciones.getY());
	}
	
	/**
	 * Gets the posiciones atacadas.
	 *
	 * @return un punto donde he sido atacado
	 */
	public Point getPosicionesAtacadas() {
		return posicionAtacadas;
	}

	/**
	 * Inicialmente los barcos estan vivos si han sido atacados
	 * a cantidad de veces de su tamaño se unde( muere)
	 * Checks if is vivo.
	 *
	 * @return true, if is vivo
	 */
	public boolean isVivo() {
		if(vecesTocado==tamano) {
			vivo = false;
		}
		return vivo;
	}
	
	/**
	 * Gets the tamano.
	 *
	 * @return the tamano
	 */
	public int getTamano() {
		return tamano;
	}
	
	
	/**
	 * Si me han atacado entonces modifico la cantidad de veces que me tocaron
	 * Atacado.
	 */
	public void atacado() {
		if(vivo == true) {
			vecesTocado++;
		}
	}
	
	/**
	 * Agrego un punto al arreglo de la posiciones donde estan los barcos
	 * Anadir.
	 *
	 * @param punto the punto
	 */
	public void anadir(Point punto) {
		posiciones.add(punto);
	}
	
	/**
	 * Retornar x.
	 *
	 * @param indice the indice
	 * @return the int
	 */
	public int retornarX(int indice) {
		return posiciones.get(indice).x;
	}
	
	/**
	 * Retornar y.
	 *
	 * @param indice the indice
	 * @return the int
	 */
	public int retornarY(int indice) {
		return posiciones.get(indice).y;
	}
	
	/**
	 * Retorna en que direccion esta el barco(arriba,abajo,derecha,izquierda)
	 * Gets the direccion.
	 *
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
	}
	
	/**
	 * Le da una direccion al barco
	 * Sets the direccion.
	 *
	 * @param direccion the new direccion
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	/**
	 * Si estoy seleccionado retorna un true sino un false
	 * Checks if is seleccionado.
	 *
	 * @return true, if is seleccionado
	 */
	public boolean isSeleccionado() {
		return seleccionado;
	}
	
	/**
	 * Inidica que fui seleccionado
	 * Sets the seleccionado.
	 *
	 * @param seleccionado the new seleccionado
	 */
	public void setSeleccionado(boolean seleccionado) {
		this.seleccionado = seleccionado;
	}
	
	/**
	 * Limpiar posiciones donde estan los barcos.
	 */
	public void limpiarPosiciones() {
		posiciones.clear();
	}
	
	/**
	 * Retornar tipo barco.
	 * Me dice que tipo de barco es dependiendo del tamano, funcion usada para pintar barcos CPU
	 * @param tamano the tamano
	 * @return the string
	 */
	public String retornarTipoBarco(int tamano) {
		switch(tamano) {
			
		case 1 :
			return "Plane";
		case 2 : 
			return "Destroyer";
		case 3 : 
			return "Cruiser";
		case 4:
			return "Battleship";
		default:
			return "";
		}
	}
	
	/**
	 * Retornar posiciones que usa el Barco
	 *
	 * @return the array list
	 */
	public ArrayList<Point> retornarPosiciones(){
		return posiciones;
	}
	
}
