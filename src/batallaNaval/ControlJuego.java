/*
 * Jennyfer Belalcazar 		- 1925639-3743
 * Samuel Riascos Prieto 	- 1922540-3743
 * Juan Camilo Randazzo		- 1923948-3743
 */
package batallaNaval;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

// TODO: Auto-generated Javadoc
/**
 * The Class ControlJuego.
 */
public class ControlJuego {
	
	/** The barcos. */
	//Atributos.
	private ArrayList <Barcos> barcos;
	
	/** The barcos CPU. */
	private ArrayList <Barcos> barcosCPU;
	
	/** The posiciones atacadas CPU. */
	//Matrices de posiciones atacadas, solo fue creado para decir que posicion ha sido atacada
	private int[][] posicionesAtacadasCPU = new int[10][10]; //CPU
	
	/** The posiciones atacadas. */
	private int[][] posicionesAtacadas = new int[10][10]; //aliado
	
	/** The pantalla usuario. */
	private PantallaUsuario pantallaUsuario;
	
	/** The pantalla CPU. */
	private PantallaCPU pantallaCPU;
	
	/** The aleatorio. */
	private Random aleatorio;
	
	/** The turno. */
	private int turno;
	
	/** The activar max inteligencia CPU. */
	private boolean activarMaxInteligenciaCPU;
	
	/** The posicion golpeada. */
	//Posicion recordada por la CPU donde ataco exitosamente, usada en la funcion maxInteligencia
	private Point posicionGolpeada;
	
	/** The direcciones posibles. */
	private ArrayList <Point> direccionesPosibles;
	
	/**
	 * Instantiates a new control juego.
	 */
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
	
	/**
	 * Crear barcos.
	 */
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
	
	
	/**
	 * Determinar juego.
	 */
	public void determinarJuego() {
		
	}
	
	/**
	 * Ataque valido.
	 *
	 * @param posicionesAtacadas the posiciones atacadas
	 * @return true, if successful
	 */
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
	
	/**
	 * Ataque.
	 *
	 * @param posicionAtacada the posicion atacada
	 */
	public void ataque(Point posicionAtacada) { //ataque aliado
		if(ataqueValido(posicionAtacada)) {
			switch(turno) {
				case 0: //CPU
					decidirTurno();
					break;
				case 1: // Aliado
					for(int i = 0; i < barcos.size(); i++) {
						if(barcos.get(i).isVivo() == true) {
							boolean hayBarco = pantallaCPU.hayBarco((int)posicionAtacada.getX(), (int)posicionAtacada.getY());
							if(hayBarco) {
								pantallaCPU.barcoAtacado((int)posicionAtacada.getX(), (int)posicionAtacada.getY());
							}
							break;
						}
					}
					decidirTurno();
					break;
			}
		}
	}
	
	/**
	 * Perdio usuario.
	 *
	 * @return the int
	 */
	public int perdioUsuario() {
		int contadorUsuario = 0; 
		int contadorCPU = 0;
		for(int i = 0; i < barcos.size(); i++) {
			if(barcos.get(i).isVivo() == false) {
				contadorUsuario++;
			}
		}
		if(contadorUsuario == 10) {
			return 1; // perdio Usuario
		}
		for(int i = 0; i < barcosCPU.size(); i++) {
			if(barcosCPU.get(i).isVivo() == false) {
				contadorCPU++;
			}
		}
		if(contadorCPU == 10) {
			return 0; //perdioCPU
		}
		
		return 2; // No ha perdido ninguno.
		
	}
	
	/**
	 * Inteligencia CPU.
	 */
	public void inteligenciaCPU() { //decide que posiciones va a elegir.
		if(activarMaxInteligenciaCPU) {
			maxInteligenciaCPU(posicionGolpeada);
		}
		else {
			Point posicion = new Point();
			posicion.setLocation(aleatorio.nextInt(10), aleatorio.nextInt(10));
			if(pantallaUsuario.hayBarco((int)posicion.getX(), (int)posicion.getY())) {
				activarMaxInteligenciaCPU = true;
				//pantallaCPU.estoyVivo();
			}
		}
	}
	
	/**
	 * Max inteligencia CPU.
	 *
	 * @param posicionGolpeada the posicion golpeada
	 */
	public void maxInteligenciaCPU(Point posicionGolpeada) {
		if(direccionesPosibles.isEmpty()) {
			
		}
	}
	
	/**
	 * Decidir turno.
	 */
	public void decidirTurno() {
		//turno = 0 CPU
		//turno = 1 Aliado
		if(turno == 0) {
			turno = 1;
		}else {
			turno = 0;
		}
	}
	
	/**
	 * Retornar turno.
	 *
	 * @return the int
	 */
	public int retornarTurno() {
		return turno;
	}
	
	/**
	 * Mostrar.
	 */
	public void mostrar() {
		pantallaCPU.mostrar2();
	}
	
}
