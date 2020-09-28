package batallaNaval;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class ControlJuego {
	//Atributos.
	private ArrayList <Barcos> barcos;
	private ArrayList <Barcos> barcosCPU;
	private int[][] posicionesAtacadasCPU = new int[10][10]; //CPU
	private int[][] posicionesAtacadas = new int[10][10]; //aliado
	private PantallaUsuario pantallaUsuario;
	private PantallaCPU pantallaCPU;
	private Random aleatorio;
	private int destruidos=0;
	private int turnos=0;
	private int intentos=0;
	private int turno;
	private int indexDireccionPreferida;
	private boolean activarMaxInteligenciaCPU;
	//Posicion recordada por la CPU donde ataco exitosamente, usada en la funcion maxInteligencia
	private Point posicionGolpeada;
	private ArrayList <Point> direccionesPosibles;
	
	//metodos.
	public ControlJuego() {
		pantallaCPU = new PantallaCPU();
		aleatorio = new Random();
		barcos = new ArrayList<Barcos>();
		barcosCPU = new ArrayList<Barcos>();
		turno = aleatorio.nextInt(2);
		activarMaxInteligenciaCPU = false;
		direccionesPosibles = new ArrayList<Point>();
		indexDireccionPreferida = -1;
	}
	//Crea barcos CPU y jugador.
	public void crearBarcos() {
		int aux = 4;
		int capacidad = 1;
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < aux; j++) {
				barcosCPU.add(new Barcos(capacidad));
				barcos.add(new Barcos(capacidad));
			}
			System.out.print(i+"\n");
			
			aux--;
			capacidad++;
		}
		for (int i = 0; i < barcosCPU.size(); i++) {
			pantallaCPU.ponerBarco2(barcosCPU.get(i).getTamano(), barcosCPU.get(i));
		}
	}
	
	
	public void determinarJuego() {
		
	}
	//recibe un point que se refiere a la posicion (x,y) que ha sido atacada
	public boolean ataqueValido(Point posicionesAtacadas) {
		//turno = 0 CPU
		//turno = 1 Aliado
		if(turno == 0) {
			if(posicionesAtacadasCPU[(int)posicionesAtacadas.getX()][(int)posicionesAtacadas.getY()] == 0) {
				posicionesAtacadasCPU[(int)posicionesAtacadas.getX()][(int)posicionesAtacadas.getY()] = 1;
			}else {
				return false;
			}
		}else {
			if(this.posicionesAtacadas[(int)posicionesAtacadas.getX()][(int)posicionesAtacadas.getY()] == 0) {
				this.posicionesAtacadas[(int)posicionesAtacadas.getX()][(int)posicionesAtacadas.getY()] = 1;
			}else {
				return false;
			}
		}
		return true;
	}
	
	public void ataque(Point posicionesAtacadas) { //ataque aliado
		if(ataqueValido(posicionesAtacadas)) {
			switch(turno) {
				case 0: 
					break;
			}
		}
	}
	
	public void inteligenciaCPU() { //decide que posiciones va a elegir.
		if(activarMaxInteligenciaCPU&&intentos<5) {
			maxInteligenciaCPU(posicionGolpeada);
		}
		else {
			intentos=0;
			activarMaxInteligenciaCPU=false;
			Point posicion = new Point();
			posicion.setLocation(aleatorio.nextInt(10), aleatorio.nextInt(10));
			if(pantallaCPU.hayBarco((int)posicion.getX(), (int)posicion.getY())&&posicionesAtacadasCPU[posicion.x][posicion.y]==0) {
				posicionesAtacadasCPU[posicion.x][posicion.y]=2;
				System.out.println("Posición atacada exitosamente: ("+posicion.x+","+posicion.y+")");
				activarMaxInteligenciaCPU = true;
				posicionGolpeada=posicion;
				pantallaCPU.atacarBarco(posicion);
				if (!pantallaCPU.barcoVivo(posicionGolpeada.x, posicionGolpeada.y)) {
					System.out.println("Barco destruido, conservando inteligencia normal");
					destruidos++;
					activarMaxInteligenciaCPU=false;
				}
			}
			else if(posicionesAtacadasCPU[posicion.x][posicion.y]!=0) {
				inteligenciaCPU();
			}
			else {
				posicionesAtacadasCPU[posicion.x][posicion.y]=1;
				System.out.println("Posición atacada: ("+posicion.x+","+posicion.y+")");
			}
		}
	}
	public void maxInteligenciaCPU(Point posicionGolpeada) {
		if(direccionesPosibles.isEmpty()) {
			if(posicionGolpeada.x!=0) {
				direccionesPosibles.add(new Point(-1,0));
			}
			if(posicionGolpeada.x!=9) {
				direccionesPosibles.add(new Point(1,0));
			}
			if(posicionGolpeada.y!=0) {
				direccionesPosibles.add(new Point(0,-1));
			}
			if(posicionGolpeada.y!=9) {
				direccionesPosibles.add(new Point(0,1));
			}
		}
		boolean cercano = false;
		int escalar = 1;
		int indexDireccionElegida; 
		if(indexDireccionPreferida == -1) {
			indexDireccionElegida = aleatorio.nextInt(direccionesPosibles.size());
		}
		else {
			indexDireccionElegida = indexDireccionPreferida;
		}
		Point direccionElegida = direccionesPosibles.get(indexDireccionElegida);
		for(escalar = 1; escalar <= 4; escalar++) {
			if(posicionGolpeada.x+direccionElegida.x*escalar<10
					&&posicionGolpeada.x+direccionElegida.x*escalar>=0
					&&posicionGolpeada.y+direccionElegida.y*escalar<10
					&&posicionGolpeada.y+direccionElegida.y*escalar>=0) {
				if(posicionesAtacadasCPU[posicionGolpeada.x+direccionElegida.x*escalar][posicionGolpeada.y+direccionElegida.y*escalar]==0) {
					cercano=true;
					break;
				}
			}
			else {
				break;
			}
		}
		System.out.println("escalar:"+escalar);
		if(!cercano) {
			direccionesPosibles.remove(indexDireccionElegida);
			maxInteligenciaCPU(posicionGolpeada);
		}
		else if (!pantallaCPU.hayBarco(posicionGolpeada.x+direccionElegida.x*escalar, posicionGolpeada.y+direccionElegida.y*escalar)){
			posicionesAtacadasCPU[posicionGolpeada.x+direccionElegida.x*escalar][posicionGolpeada.y+direccionElegida.y*escalar]=1;
			intentos++;
			System.out.println("Posición atacada MAX: ("+(posicionGolpeada.x+direccionElegida.x*escalar)+","+(posicionGolpeada.y+direccionElegida.y*escalar)+")");
			direccionesPosibles.remove(indexDireccionElegida);
		}
		else {
			System.out.println("dirección: ("+direccionElegida.x+","+direccionElegida.y+"), escalar: "+escalar);
			intentos=0;
			direccionesPosibles.clear();
			direccionesPosibles.add(direccionElegida);
			direccionesPosibles.add(new Point(direccionElegida.x*-1,direccionElegida.y*-1));
			indexDireccionPreferida = 0;
			posicionesAtacadasCPU[posicionGolpeada.x+direccionElegida.x][posicionGolpeada.y+direccionElegida.y]=2;
			pantallaCPU.atacarBarco(new Point(posicionGolpeada.x+direccionElegida.x*escalar,posicionGolpeada.y+direccionElegida.y*escalar));
			System.out.println("Posición atacada exitosamente MAX: ("+(posicionGolpeada.x+direccionElegida.x*escalar)+","+(posicionGolpeada.y+direccionElegida.y*escalar)+")");
			this.posicionGolpeada.setLocation(posicionGolpeada.x+direccionElegida.x*escalar,posicionGolpeada.y+direccionElegida.y*escalar);
			if (!pantallaCPU.barcoVivo(posicionGolpeada.x, posicionGolpeada.y)) {
				System.out.println("Barco destruido, volviendo a inteligencia normal");
				destruidos++;
				posicionesAtacadasCPU[posicionGolpeada.x][posicionGolpeada.y]=3;
				direccionesPosibles.clear();
				activarMaxInteligenciaCPU=false;
				indexDireccionPreferida = -1;
			}
		}
	}
	public void decidirTurno() {
		//turno = 0 CPU
		//turno = 1 Aliado
		if(turno == 0) {
			turno = 1;
		}else {
			turno = 0;
		}
	}
	
	public int retornarTurno() {
		return turno;
	}
	
	public void mostrar() {
		pantallaCPU.mostrar2();
	}
	
	public void mostrarPosicionesAtacadasCPU() {
		String repetir="";
		do {
			do {
				inteligenciaCPU();
				turnos++;
			}while(destruidos<10);
			pantallaCPU.mostrar2();
			System.out.println();
			for(int i=0;i<10;i++)
			{
				for (int j=0;j<10;j++) {
					System.out.print(posicionesAtacadasCPU[i][j]+" ");
				}
				System.out.print("\n");
			}
			Scanner in = new Scanner(System.in);
			System.out.println(destruidos+"destruidos en "+turnos+"turnos");
			System.out.print("Continuar? :");
			repetir = in.nextLine();
		}while (repetir.equals("s"));
	}
	
	public Barcos retornarBarco(int indice) {
		return barcos.get(indice);
	}
	public void añadirBarco(Barcos barco) {
		barcosCPU.add(barco);
	}
}
