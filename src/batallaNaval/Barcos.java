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
	private boolean seleccionado;
	private boolean vivo;
	private String direccion = "";
	private Point  posicionAtacadas = new Point();
	private ArrayList<Point> posiciones = new ArrayList<Point>();
	//Metodos
	public Barcos(int tamano) {
		seleccionado = false;
		vecesTocado = 0;
		vivo = true;
		this.tamano = tamano;
	}
	//Ataque aliado.
	public  void atacar(Point posiciones) {
		System.out.println("Ay me pegaron T___T");
		posicionAtacadas.setLocation(posiciones.getX(), posiciones.getY());
		vecesTocado++;
	}
	//Ataque aliado
	public Point getPosicionesAtacadas() {
		return posicionAtacadas;
	}

	public boolean isVivo() {
		System.out.println("Noob me faltan "+(tamano-vecesTocado));
		if(vecesTocado==tamano) {
			vivo=false;
		}
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
		return posiciones.get(indice).x;
	}
	
	public int retornarY(int indice) {
		return posiciones.get(indice).y;
	}
	
	public String getDireccion() {
		return direccion;
	}
	
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public boolean isSeleccionado() {
		return seleccionado;
	}
	
	public void setSeleccionado(boolean seleccionado) {
		this.seleccionado = seleccionado;
	}
	public void limpiarPosiciones() {
		posiciones.clear();
	}
}
