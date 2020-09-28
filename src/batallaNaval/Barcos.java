/*
 * Jennyfer Belalcazar 		- 1925639-3743
 * Samuel Riascos Prieto 	- 1922540-3743
 * Juan Camilo Randazzo		- 1923948-3743
 */
package batallaNaval;

import java.awt.Point;
import java.util.ArrayList;

public class Barcos {
	//atributos
	private int tamano;
	private int vecesTocado;
	private boolean vivo;
	private ArrayList<Point> posiciones = new ArrayList<Point>();
	//Metodos
	public Barcos(int tamano) {
		vecesTocado = 0;
		vivo = true;
		this.tamano = tamano;
	}
	
	public boolean isVivo() {
		return vivo;
	}
	public int getTamano() {
		return tamano;
	}
	
	public boolean estoyVivo() {
		if(tamano == vecesTocado) {
			vivo = false;
		}
		return vivo;
	}
	
	public void atacado() {
		if(vivo == true) {
			vecesTocado++;
		}
	}
	public void anadir(Point punto) {
		posiciones.add(punto);
	}
	
	public int retornarX(int indice) {
		return (int)posiciones.get(indice).getX();
	}
	
	public int retornarY(int indice) {
		return (int)posiciones.get(indice).getY();
	}
}
