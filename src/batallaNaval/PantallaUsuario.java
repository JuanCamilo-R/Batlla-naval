package batallaNaval;

import java.util.ArrayList;
import java.awt.Point;

public class PantallaUsuario {
	//Atributos
	private Barcos [][] barcosPantalla = new Barcos[10][10];
	private
	//Metodos
	public PantallaUsuario() {
		
	}
	public void estaDisponible() {
		
	}
	//1er numero > 2ndo numero //Izquierda
	//1er numero < 2nd numero //Derecha
	//[1,3] [1,5]  1 == 1, 3 < 5 //Derecha
	//[1,3] [2,4] 1 == 2 ? , 3 == 4 ?
	//[1,5] [1,3] 1 == 1, 5 > 3 //Izquierda
	public void ponerBarco(ArrayList<Point> primerPar, ArrayList<Point> segundoPar) {
		if()
	}
}
