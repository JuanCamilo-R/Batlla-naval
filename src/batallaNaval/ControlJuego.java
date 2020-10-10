/* Jennyfer Belalcazar 		- 1925639-3743
 * Samuel Riascos Prieto 	- 1922540-3743
 * Juan Camilo Randazzo		- 1923948-3743
 */
package batallaNaval;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

// TODO: Auto-generated Javadoc
/**
 * The Class ControlJuego.
 */
public class ControlJuego {

	/** The barcos. */
	// Atributos.
	private ArrayList<Barcos> barcos;
	
	/** The posicion ataca CPU. */
	private ArrayList<Point> posicionAtacaCPU = new ArrayList<Point>();

	/** The barcos CPU. */
	private ArrayList<Barcos> barcosCPU;

	/** The posiciones atacadas CPU. */
	// Matrices de posiciones atacadas, solo fue creado para decir que posicion ha
	// sido atacada
	private int[][] posicionesAtacadasCPU = new int[10][10]; // CPU

	/** The posiciones atacadas. */
	private int[][] posicionesAtacadas = new int[10][10]; // aliado

	/** The pantalla usuario. */
	private PantallaUsuario pantallaUsuario;

	/** The pantalla CPU. */
	private PantallaCPU pantallaCPU;

	/** The aleatorio. */
	private Random aleatorio;
	
	/** The destruidos. */
	private int destruidos = 0;
	
	/** The turnos. */
	private int turnos = 0;
	
	/** The ronda. */
	private int ronda = 1;
	
	/** The intentos. */
	private int intentos = 0;
	
	/** The turno. */
	private int turno;
	
	/** The index direccion preferida. */
	private int indexDireccionPreferida;
	
	/** The activar max inteligencia CPU. */
	private boolean activarMaxInteligenciaCPU;

	/** The posicion golpeada. */
	// Posicion recordada por la CPU donde ataco exitosamente, usada en la funcion
	// maxInteligencia
	private Point posicionReferencia;
	
	/** The posicion atacada. */
	// Posicion donde ataco la CPU
	private Point posicionAtacada;

	/** The direcciones posibles. */
	private ArrayList<Point> direccionesPosibles;

	/**
	 * Instantiates a new control juego.
	 */
	// metodos.
	public ControlJuego() {
		pantallaUsuario = new PantallaUsuario();
		pantallaCPU = new PantallaCPU();
		pantallaUsuario = new PantallaUsuario();
		aleatorio = new Random();
		barcos = new ArrayList<Barcos>();
		barcosCPU = new ArrayList<Barcos>();
		turno = aleatorio.nextInt(2);
		activarMaxInteligenciaCPU = false;
		direccionesPosibles = new ArrayList<Point>();
		indexDireccionPreferida = -1;
	}

	/**
	 * Crear barcos.
	 */
	// Crea barcos CPU y jugador.
	public void crearBarcos() {
		int aux = 4;
		int capacidad = 1;
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < aux; j++) {
				barcosCPU.add(new Barcos(capacidad));
				barcos.add(new Barcos(capacidad));
			}
			aux--;
			capacidad++;
		}
		// Ponemos los barcos enemigos.
		for (int i = 0; i < barcosCPU.size(); i++) {
			pantallaCPU.ponerBarco2(barcosCPU.get(i).getTamano(), barcosCPU.get(i));
		}
	}

	/**
	 * Determinar juego.
	 */

	public void iniciarJuegoCPU() {
		barcos.clear();
		barcosCPU.clear();
		pantallaCPU.limpiarPantalla();
		crearBarcos(); // Creamos barcos enemigos y aliados.
	}

	/**
	 * Determinar juego.
	 *
	 * @param posicionAtacada the posicion atacada
	 * @return the int
	 */
	// Ataca y define si alguien perdio.
	public int determinarJuego(Point posicionAtacada) {
		ataque(posicionAtacada);
		return perdio();
	}

	/**
	 * Ataque valido.
	 *
	 * @param posicionesAtacadas the posiciones atacadas
	 * @return true, if successful
	 */
	// recibe un point que se refiere a la posicion (x,y) que ha sido atacada
	public boolean ataqueValido(Point posicionesAtacadas) {
		// turno = 0 CPU
		// turno = 1 Aliado
		if (turno == 0) {
			if (posicionesAtacadasCPU[(int) posicionesAtacadas.getX()][(int) posicionesAtacadas.getY()] == 0) {
				posicionesAtacadasCPU[(int) posicionesAtacadas.getX()][(int) posicionesAtacadas.getY()] = 1;
				return true;
			} else {
				return false;
			}
		}
		if(turno==1) {
	
			if (this.posicionesAtacadas[(int) posicionesAtacadas.getX()][(int) posicionesAtacadas.getY()] == 0) {
				this.posicionesAtacadas[(int) posicionesAtacadas.getX()][(int) posicionesAtacadas.getY()] = 1;
				
			} else {
				return false;
			}
			return true;
		}
		return false;
	}
	

	/**
	 * Ataque.
	 *
	 * @param posicionAtacada the posicion atacada
	 */
	public void ataque(Point posicionAtacada) { // ataque aliado
			switch (turno) {
			case 0: // CPU
				inteligenciaCPU();
				decidirTurno();
				break;
			case 1: // Aliado
					for (int i = 0; i < barcos.size(); i++) {
						if (barcos.get(i).isVivo() == true) {
							boolean hayBarco = pantallaCPU.hayBarco((int) posicionAtacada.getX(),
									(int) posicionAtacada.getY());
							if (hayBarco) {
								pantallaCPU.barcoAtacado((int) posicionAtacada.getX(), (int) posicionAtacada.getY());
							}
							break;
						}
					}
					decidirTurno();
					
					break;

			}
			ronda++;
		
	}

	/**
	 * Gets the ronda.
	 *
	 * @return the ronda
	 */
	public int getRonda() {
		return ronda;
	}

	/**
	 * Perdio usuario.
	 *
	 * @return the int
	 */
	public int perdio() {
		int contadorUsuario = 0;
		int contadorCPU = 0;
		for (int i = 0; i < barcos.size(); i++) {
			if (barcos.get(i).isVivo() == false) {
				contadorUsuario++;
			}
		}
		if (contadorUsuario == 10) {
			return 1; // perdio Usuario
		}
		for (int i = 0; i < barcosCPU.size(); i++) {
			if (barcosCPU.get(i).isVivo() == false) {
				contadorCPU++;
			}
		}
		if (contadorCPU == 10) {
			return 0; // perdioCPU
		}

		return 2; // No ha perdido ninguno.

	}

	/**
	 * Inteligencia CPU.
	 */
	public void inteligenciaCPU() { // decide que posiciones va a elegir.
		posicionAtacaCPU.clear();
		if (activarMaxInteligenciaCPU && intentos < 10) {
			maxInteligenciaCPU();
		} else {
			intentos = 0;
			activarMaxInteligenciaCPU = false;
			Point posicion = new Point();
			posicion.setLocation(aleatorio.nextInt(10), aleatorio.nextInt(10));
			if (pantallaUsuario.hayBarco((int) posicion.getX(), (int) posicion.getY())
					&& posicionesAtacadasCPU[posicion.x][posicion.y] == 0) {
				posicionesAtacadasCPU[posicion.x][posicion.y] = 2;
				posicionAtacada = posicion;
				System.out.println("Posición atacada exitosamente: (" + posicion.x + "," + posicion.y + ")");
				activarMaxInteligenciaCPU = true;
				posicionReferencia = posicion;
				pantallaUsuario.atacarBarco(posicion);
				if (!pantallaUsuario.barcoVivo(posicionReferencia.x, posicionReferencia.y)) {
					System.out.println("Barco destruido, conservando inteligencia normal");
					destruidos++;
					activarMaxInteligenciaCPU = false;
				}
			} else if (posicionesAtacadasCPU[posicion.x][posicion.y] != 0) {
				inteligenciaCPU();
			} else {
				posicionesAtacadasCPU[posicion.x][posicion.y] = 1;
				posicionAtacada = posicion;
				System.out.println("Posición atacada: (" + posicion.x + "," + posicion.y + ")");
			}
		}
	}

	/**
	 * Max inteligencia CPU.
	 */
	public void maxInteligenciaCPU() {
		if (direccionesPosibles.isEmpty()) {
			if (posicionReferencia.x != 0) {
				direccionesPosibles.add(new Point(-1, 0));
			}
			if (posicionReferencia.x != 9) {
				direccionesPosibles.add(new Point(1, 0));
			}
			if (posicionReferencia.y != 0) {
				direccionesPosibles.add(new Point(0, -1));
			}
			if (posicionReferencia.y != 9) {
				direccionesPosibles.add(new Point(0, 1));
			}
		}
		boolean cercano = false;
		int escalar = 1;
		int indexDireccionElegida;
		if (indexDireccionPreferida == -1) {
			indexDireccionElegida = aleatorio.nextInt(direccionesPosibles.size());
		} else {
			indexDireccionElegida = indexDireccionPreferida;
		}
		Point direccionElegida = direccionesPosibles.get(indexDireccionElegida);
		for (escalar = 1; escalar <= 4; escalar++) {
			if (posicionReferencia.x + direccionElegida.x * escalar < 10
					&& posicionReferencia.x + direccionElegida.x * escalar >= 0
					&& posicionReferencia.y + direccionElegida.y * escalar < 10
					&& posicionReferencia.y + direccionElegida.y * escalar >= 0) {
				if (posicionesAtacadasCPU[posicionReferencia.x + direccionElegida.x * escalar][posicionReferencia.y
						+ direccionElegida.y * escalar] == 0) {
					cercano = true;
					break;
				}
			} else {
				break;
			}
		}
		System.out.println("escalar:" + escalar);
		if (!cercano) {
			direccionesPosibles.remove(indexDireccionElegida);
			intentos++;
			inteligenciaCPU();
		} else if (!pantallaUsuario.hayBarco(posicionReferencia.x + direccionElegida.x * escalar,
				posicionReferencia.y + direccionElegida.y * escalar)) {
			posicionesAtacadasCPU[posicionReferencia.x + direccionElegida.x * escalar][posicionReferencia.y
					+ direccionElegida.y * escalar] = 1;
			intentos++;
			posicionAtacada = new Point(posicionReferencia.x + direccionElegida.x * escalar,posicionReferencia.y
					+ direccionElegida.y * escalar);
			System.out.println("Posición atacada MAX: (" + (posicionReferencia.x + direccionElegida.x * escalar) + ","
					+ (posicionReferencia.y + direccionElegida.y * escalar) + ")");
			direccionesPosibles.remove(indexDireccionElegida);
		} else {
			System.out
					.println("dirección: (" + direccionElegida.x + "," + direccionElegida.y + "), escalar: " + escalar);
			intentos = 0;
			direccionesPosibles.clear();
			direccionesPosibles.add(direccionElegida);
			direccionesPosibles.add(new Point(direccionElegida.x * -1, direccionElegida.y * -1));
			indexDireccionPreferida = 0;
			posicionesAtacadasCPU[posicionReferencia.x + direccionElegida.x][posicionReferencia.y + direccionElegida.y] = 2;
			posicionAtacada = new Point(posicionReferencia.x + direccionElegida.x * escalar, posicionReferencia.y + direccionElegida.y * escalar);
			pantallaUsuario.atacarBarco(new Point(posicionReferencia.x + direccionElegida.x * escalar,
					posicionReferencia.y + direccionElegida.y * escalar));
			System.out.println(
					"Posición atacada exitosamente MAX: (" + (posicionReferencia.x + direccionElegida.x * escalar) + ","
							+ (posicionReferencia.y + direccionElegida.y * escalar) + ")");
			posicionReferencia.setLocation(posicionReferencia.x + direccionElegida.x * escalar,
					posicionReferencia.y + direccionElegida.y * escalar);
			if (!pantallaUsuario.barcoVivo(posicionReferencia.x, posicionReferencia.y)) {
				System.out.println("Barco destruido, volviendo a inteligencia normal");
				destruidos++;
				posicionesAtacadasCPU[posicionReferencia.x][posicionReferencia.y] = 3;
				direccionesPosibles.clear();
				activarMaxInteligenciaCPU = false;
				indexDireccionPreferida = -1;
			}
		}
	}

	/**
	 * Decidir turno.
	 */
	public void decidirTurno() {
		// turno = 0 CPU
		// turno = 1 Aliado
		if (turno == 0) {
			turno = 1;
		} else {
			turno = 0;
		}
	}

	/**
	 * Poner barco.
	 *
	 * @param x1 the x 1
	 * @param y1 the y 1
	 * @param x2 the x 2
	 * @param y2 the y 2
	 * @param tamano the tamano
	 * @param barco the barco
	 */
	public void ponerBarco(int x1, int y1, int x2, int y2, int tamano, Barcos barco) {
		pantallaUsuario.ponerBarco(x1, y1, x2, y2, tamano, barco);

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
	 * Retornar barco.
	 *
	 * @param tamano the tamano
	 * @return the barcos
	 */
	public Barcos retornarBarco(int tamano) {
		for (int i = 0; i < barcos.size(); i++) {
			if (barcos.get(i).getTamano() == tamano && barcos.get(i).isSeleccionado() == false) {

				return barcos.get(i);
			}
		}
		return null;
	}
	/**
	 * Retornar barco CPU.
	 *
	 * @param tamano the tamano
	 * @return the barcos CPU
	 */
	public Barcos retornarBarcoCPU(int tamano) {
		for (int i = 0; i < barcosCPU.size(); i++) {
			if (barcosCPU.get(i).getTamano() == tamano) {

				return barcosCPU.get(i);
			}
		}
		return null;
	}
	//barcos CPU se usa en la ventana de ver Barcos CPU
	public Barcos darBarcoPorIndice(int indice) {
		 return barcosCPU.get(indice);
	}

	/**
	 * Limpiar barcos.
	 */
	//Usada cuando se reinicia el juego
	public void limpiarBarcos() {
		for (int i = 0; i < barcos.size(); i++) {
			barcos.get(i).setSeleccionado(false);
			barcos.get(i).limpiarPosiciones();
		}
		pantallaUsuario.limpiarPantalla();
	}
	
	/**
	 * Retornar posicion.
	 *
	 * @return the point
	 */
	//Usada en la funcion jugar en vistaBatallaNaval
	//Se usa para retornar la 
	public Point retornarPosicion() {
		return posicionAtacada;
	}
	
	public boolean hayBarcoCPU(int x, int y) {
		if(pantallaCPU.hayBarco(x, y)) {
			return true;
		}
		return false;
	}
	public boolean hayBarco(int x, int y) {
		if(pantallaUsuario.hayBarco(x, y)) {
			return true;
		}
		return false;
	}
	
	public boolean estaVivo(int i, int j, String pantalla) {
		if(pantalla == "A") {
			return pantallaUsuario.barcoVivo(i, j);
		}
		return pantallaCPU.barcoVivo(i, j);
	}
	
	public int getTamanoBarco(int i, int j, String pantalla){
		if(pantalla == "A" ) { //A de aliado
			return pantallaUsuario.getTamanoBarco(i, j);
		}
		return pantallaCPU.getTamanoBarco(i, j);
	}
	
	public Barcos getBarco(int i, int j, String pantalla) {
		if(pantalla == "A") { //Aliados
			return pantallaUsuario.getBarco(i, j);
		}
		return pantallaCPU.getBarco(i, j);
	}
}

