package batallaNaval;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class ControlJuego {
	//Atributos.
	private ArrayList <Barcos> barcos;
	private ArrayList <Barcos> barcosCPU;
	private int[][] posicionesAtacadasCPU = new int[10][10]; //CPU
	private int[][] posicionesAtacadas = new int[10][10]; //aliado
	private PantallaUsuario pantallaUsuario;
	private PantallaCPU pantallaCPU;
	private Random aleatorio;
	private int turno;
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
		if(activarMaxInteligenciaCPU) {
			maxInteligenciaCPU(posicionGolpeada);
		}
		else {
			Point posicion = new Point();
			posicion.setLocation(aleatorio.nextInt(10), aleatorio.nextInt(10));
			if(pantallaUsuario.hayBarco((int)posicion.getX(), (int)posicion.getY())) {
				activarMaxInteligenciaCPU = true;
			}
		}
	}
	public void maxInteligenciaCPU(Point posicionGolpeada) {
		if(direccionesPosibles.isEmpty()) {
			
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
	
	public Barcos retornarBarco(int indice) {
		return barcos.get(indice);
	}
	public void añadirBarco(Barcos barco) {
		barcosCPU.add(barco);
	}
}
