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
	private int posicionx;
	private Random aleatoriox;
	private int posiciony;
	private Random aleatorioy;
	private int opcion;
	private int escogerDireccion;
	private Random aleatorioD;
	private int posicionx2, posiciony2;

	
	//Metodos
	public PantallaCPU() {
		tamano =1;
		ronda=1;
		//posicionx = aleatoriox.nextInt(10);//Aletario entre 0 y 10
		//posiciony = aleatorioy.nextInt(10);//Aletario entre 0 y 10
		//escogerDireccion  = aleatorioD.nextInt(5);
	}
	/*
	private void cambiar() {
		posicionx = aleatoriox.nextInt(10);
		posiciony = aleatorioy.nextInt(10);
	}
	*/
	/*
	private void cambiarDirecion() {

		escogerDireccion  = aleatorioD.nextInt(4)+1;
	}
	*/
	/*
	public boolean estaDisponibleCPU() {
		if(ronda<=4) {
			if(barcosPantalla[posicionx][posiciony]==null && tamano==1) {
				ronda++;
				return true;
			}
		}
		if(ronda>= 5 && ronda<= 7) {
			tamano = 2;
			switch(escogerDireccion){
				case 0://arriba
					if(posicionx-(tamano-1)<0) {
						return false;
					}else {
						if(barcosPantalla[posicionx][posiciony]==null && barcosPantalla[posicionx-1][posiciony]==null) {
							ronda++;
							posicionx2 = posicionx-1;
							posiciony2 = posiciony;
							return true;
						}
					}
				case 1://derecha
					if(posiciony+(tamano-1)>10) {
						return false;
					}else {
						if(barcosPantalla[posicionx][posiciony]==null && barcosPantalla[posicionx][posiciony+1]==null) {
							ronda++;
							posicionx2 = posicionx;
							posiciony2 = posiciony+1;
							return true;
						}
					}
				case 2: //abajo
					if(posicionx+(tamano-1)>10) {
						return false;
					} else {
						if(barcosPantalla[posicionx][posiciony]==null && barcosPantalla[posicionx+1][posiciony]==null) {
							ronda++;
							posicionx2 = posicionx+1;
							posiciony2 = posiciony;
							return true;
						}
					}
				case 3: //izquierda
					if(posiciony-(tamano-1) < 0) {
						return false;
					} else {
						if(barcosPantalla[posicionx][posiciony]==null && barcosPantalla[posicionx][posiciony-1]==null) {
							ronda++;
							posicionx2 = posicionx;
							posiciony2 = posiciony-1;
							
							return true;
						}
					}
			}
				
			} else if(ronda >= 8 && ronda <= 9) {
				tamano = 3;
				switch(escogerDireccion){
				case 0://arriba
					if(posicionx-(tamano-1)<0) {
						return false;
					}else {
						if(estaDisponible(posicionx,posiciony,posicionx-(tamano-1),posiciony,tamano)) {
							ronda++;
							posicionx2 = posicionx-(tamano-1);
							posiciony2 = posiciony;
							
							return true;
						}
					}
				case 1://derecha
					if(posiciony+(tamano-1)>10) {
						return false;
					}else {
						if(estaDisponible(posicionx,posiciony,posicionx,posiciony+(tamano-1),tamano)) {
							ronda++;
							posicionx2 = posicionx;
							posiciony2 = posiciony+(tamano-1);
							return true;
						}
					}
				case 2: //abajo
					if(posicionx+(tamano-1)>10) {
						return false;
					} else {
						if(estaDisponible(posicionx,posiciony,posicionx,posiciony+(tamano-1),tamano)) {
							ronda++;
							posicionx2 = posicionx;
							posiciony2 = posiciony+(tamano-1);

							return true;
						}
					}
				case 3: //izquierda
					if(posiciony-(tamano-1) < 0) {
						return false;
					} else {
						if(estaDisponible(posicionx,posiciony,posicionx,posiciony-(tamano-1),tamano)) {
							ronda++;
							posicionx2 = posicionx;
							posiciony2 = posiciony-(tamano-1);

							return true;
						}
					}
				}
			} else if(ronda == 10) {
				tamano = 4;
				switch(escogerDireccion){
				case 0://arriba
					if(posicionx-(tamano-1)<0) {
						return false;
					}else {
						if(estaDisponible(posicionx,posiciony,posicionx-(tamano-1),posiciony,tamano)) {
							ronda++;
							posicionx2 = posicionx-(tamano-1);
							posiciony2 = posiciony;
							return true;
						}
					}
				case 1://derecha
					if(posiciony+(tamano-1)>10) {
						return false;
					}else {
						if(estaDisponible(posicionx,posiciony,posicionx,posiciony+(tamano-1),tamano)) {
							ronda++;
							posicionx2 = posicionx;
							posiciony2 = posiciony+(tamano-1);

							return true;
						}
					}
				case 2: //abajo
					if(posicionx+(tamano-1)>10) {
						return false;
					} else {
						if(estaDisponible(posicionx,posiciony,posicionx+(tamano-1),posiciony,tamano)) {
							ronda++;
							posicionx2 = posicionx+(tamano-1);
							posiciony2 = posiciony;
							return true;
						}
					}
				case 3: //izquierda
					if(posiciony-(tamano-1) < 0) {
						return false;
					} else {
						if(estaDisponible(posicionx,posiciony,posicionx,posiciony-(tamano-1),tamano)) {
							ronda++;
							posicionx2 = posicionx;
							posiciony2 = posiciony-(tamano-1);
							return true;
						}
					}
				}
			}
		
		return false;
	
	}
*/
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
	/*
	public void ponerBarcoCPU() {
		for(int i = 0; i < 10; i++) {
			ponerBarco(posicionx, posiciony, posicionx2,posiciony2,tamano,);
		}
	}
}
*/