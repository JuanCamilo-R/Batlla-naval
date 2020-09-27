package batallaNaval;

import java.awt.Point;
import java.util.ArrayList;

public class Barcos {
	//atributos
	private int tamano;
	private int vecesTocado;
	private boolean vivo;
	private Point posicionAtacadas = new Point();
	private ArrayList<Point> posiciones = new ArrayList<Point>();
	//Metodos
	public Barcos(int tamano) {
		vecesTocado = 0;
		vivo = true;
		this.tamano = tamano;
	}
	//Ataque aliado.
	public  void atacar(Point posiciones) {
		posicionAtacadas.setLocation(posiciones.getX(), posiciones.getY());
	}
	//Ataque aliado
	public Point getPosicionesAtacadas() {
		return posicionAtacadas;
	}

	public boolean isVivo() {
		return vivo;
	}
	public int getTamano() {
		return tamano;
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
