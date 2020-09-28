package batallaNaval;

import java.util.ArrayList;
import java.util.Random;
import java.awt.Point;

//Heredo de PantallaUsuario
public class PantallaCPU extends PantallaUsuario {
	
	
	
	//Atributos
	private ArrayList<Point> posicionesTocadas = new ArrayList<Point>();
	private Barcos [][] barcosPantalla = new Barcos[10][10];
	private int tamano;
	private int ronda;
	

	
	//Metodos
	public PantallaCPU() {
		tamano =1;
		ronda=1;
	}
	
	public void ponerBarco2(int tamano,Barcos barquito){
		Random aleatorio= new Random();
		Point direcci�n = new Point(0,0);
		int x=0,y=0;
		do {
			System.out.println("me trab�");
			x=aleatorio.nextInt(10);
			y=aleatorio.nextInt(10);
			switch(aleatorio.nextInt(4)) {
			case 0:
				//Derecha
				direcci�n.setLocation(0, 1);
				break;
			case 1:
				//Arriba
				direcci�n.setLocation(1,0);
				break;
			case 2:
				//Izquierda
				direcci�n.setLocation(0,-1);
				break;
			case 3:
				//Abajo
				direcci�n.setLocation(-1,0);
				break;
			}
		}while(!Disponible(x,y,direcci�n,tamano));
		for(int i=0 ;i < tamano; i++){
			barcosPantalla[y+direcci�n.x*i][x+direcci�n.y*i] = barquito;
			System.out.println("trozo "+i+" metido en i: "+(y+direcci�n.x*i)+" j:"+(x+direcci�n.y*i)+ "JEJE");
		}
		System.out.println("Meti barquito JEJE");
		
	}
	public boolean barcoVivo(int x, int y) {
		return barcosPantalla[x][y].isVivo();
	}
	public boolean hayBarco(int x, int y) {
		if(barcosPantalla[x][y] != null) {
			return true;
		}
		return false;
	}
	public void atacarBarco(Point posicion) {
		barcosPantalla[posicion.x][posicion.y].atacar(posicion);
	}
	private boolean Disponible(int x, int y, Point direcci�n, int tamano) {
		// TODO Auto-generated method stub
		for (int i = 0; i < tamano; i++)
		{
			if(y+direcci�n.x*i<0||y+direcci�n.x*i>9)
			{
				return false;
			}
			if(x+direcci�n.y*i<0||x+direcci�n.y*i>9)
			{
				return false;
			}
			if(barcosPantalla[y+direcci�n.x*i][x+direcci�n.y*i]!=null)
			{
				return false;
			}
		}
		return true;
	}
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