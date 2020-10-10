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
 *  Clase creada para identificar:
 * -Donde se ha atacado
 * -Determina el estado del juego
 */
public class ControlJuego {
	// Atributos.
	
	/** The barcos. 
	 * Nuestros barcos
	*/
	private ArrayList<Barcos> barcos;

	/** The barcos CPU. 
	 * Barcos de CPU
	*/
	private ArrayList<Barcos> barcosCPU;
	
	/** The posicion ataca CPU. 
	 * Se guardan las posiciones donde va atacar la CPU
	 * */
	private ArrayList<Point> posicionAtacaCPU = new ArrayList<Point>();


	/**   Matrices de posiciones atacadas, se crean para decir que casilla ha sido atacada(tocada). */
	private int[][] posicionesAtacadasCPU = new int[10][10];//Casillas que ha atacado CPU
	
	/** The posiciones atacadas. */
	private int[][] posicionesAtacadas = new int[10][10]; //Casillas que hemos atacado

	/** The pantalla usuario. 
	 * Pantalla donde estan barcos y demas casillas del usuario
	*/
	private PantallaUsuario pantallaUsuario;

	/** The pantalla CPU. 
	 * Pantalla donde estan barcos y demas casillas del CPU
	 */
	private PantallaCPU pantallaCPU;

	/** The aleatorio. 
	 *  Se crea para que el primer turno se haga de forma aleatoria
	 * */
	private Random aleatorio;
	
	/** The destruidos. Atributo que me indica cuantos barcos se han hundido*/
	private int destruidos = 0;
	
	/** The intentos.*/
	private int intentos = 0;
	
	/** The turno. Para saber de quien es el turno */
	private int turno;
	
	/** The index direccion preferida. */
	private int indexDireccionPreferida;
	
	/** The activar max inteligencia CPU.  */
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
		/*Construtor
		Se inicializa las condiciones al iniciar ControlJuego 
		*/
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
	 * Funcion de tipo void que crea barcos CPU y  usuario.
	 */
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
	 * Funcion tipo void
	 * 	iniciarJuegoCPU
	 * 	se crea para cuando se quiera volver a jugar, limpiamos las matrices y las volvemos a crear.
	 */

	public void iniciarJuegoCPU() {
		barcos.clear();
		barcosCPU.clear();
		pantallaCPU.limpiarPantalla();
		crearBarcos(); // Creamos barcos enemigos y aliados.
	}

	
	/**
	 * Ataque valido.
	 * Funcion de tipo boolean
	 * Esta funcion es creada para evitar que el usuario ataque dos o mas veces en una misma
	 * casilla, en caso de retornar false me vuelve a pedir otra posicion
	 * Recibe un point que se refiere a la posicion (x,y) que ha sido atacada
	 * @param posicionesAtacadas the posiciones atacadas
	 * @return true, if successful
	 */
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
	 * Funcion de tipo void
	 * En caso de que el ataque sea valido y le pasamos el punto llamamos a la funcion atacar
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
					//para saber a quien le toca despues de atacar
					decidirTurno();	
					break;
			}	
	}

	/**
	 * Perdio .
	 * Funcion tipo int que retorna un numero de acuerdo a si ya gano alguno o no
	 * @return the int
	 */
	public int perdio() {
		//cuenta cuantos barcos han muerto
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
				activarMaxInteligenciaCPU = true;
				posicionReferencia = posicion;
				pantallaUsuario.atacarBarco(posicion);
				if (!pantallaUsuario.barcoVivo(posicionReferencia.x, posicionReferencia.y)) {
					destruidos++;
					activarMaxInteligenciaCPU = false;
				}
			} else if (posicionesAtacadasCPU[posicion.x][posicion.y] != 0) {
				inteligenciaCPU();
			} else {
				posicionesAtacadasCPU[posicion.x][posicion.y] = 1;
				posicionAtacada = posicion;
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
			direccionesPosibles.remove(indexDireccionElegida);
		} else {
			intentos = 0;
			direccionesPosibles.clear();
			direccionesPosibles.add(direccionElegida);
			direccionesPosibles.add(new Point(direccionElegida.x * -1, direccionElegida.y * -1));
			indexDireccionPreferida = 0;
			posicionesAtacadasCPU[posicionReferencia.x + direccionElegida.x][posicionReferencia.y + direccionElegida.y] = 2;
			posicionAtacada = new Point(posicionReferencia.x + direccionElegida.x * escalar, posicionReferencia.y + direccionElegida.y * escalar);
			pantallaUsuario.atacarBarco(new Point(posicionReferencia.x + direccionElegida.x * escalar,
					posicionReferencia.y + direccionElegida.y * escalar));
			posicionReferencia.setLocation(posicionReferencia.x + direccionElegida.x * escalar,
					posicionReferencia.y + direccionElegida.y * escalar);
			if (!pantallaUsuario.barcoVivo(posicionReferencia.x, posicionReferencia.y)) {
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
	 * Me indica de quien es el siguiente turno
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
	 * Metodo de tipo void usado para indicar donde se van a poner los barcos del usuario
	 * recibiendo como parametro la posicion de la cabeza y cola del barco
	 * tamaño del barco que va a poner y un Barco
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
	 * Retornar un int que me indica de quien es el turno.
	 * @return the int
	 */
	public int retornarTurno() {
		return turno;
	}

	/**
	 * Retornar barco.
	 * Me retorna un barco de usuario dependiendo del tamaño
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
	 * Me retorna un barco de CPU dependiendo del tamaño
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
	
	/**
	 * Dar barco por indice.
	 *	se usa en la ventana de ver Barcos CPU para pintar los barcos en pantalla
	 * @param indice the indice
	 * @return the barcos
	 */
	public Barcos darBarcoPorIndice(int indice) {
		 return barcosCPU.get(indice);
	}

	/**
	 * Limpiar barcos.
	 * Usada cuando se reinicia el juego
	 */
	public void limpiarBarcos() {
		for (int i = 0; i < barcos.size(); i++) {
			barcos.get(i).setSeleccionado(false);
			barcos.get(i).limpiarPosiciones();
		}
		pantallaUsuario.limpiarPantalla();
	}
	
	/**
	 * Retornar posicion.
	 *Usada en la funcion jugar en vistaBatallaNaval
	 * Se usa para retornar la posicion en la que ataco CPU
	 * @return the point
	 */
	//
	public Point retornarPosicion() {
		return posicionAtacada;
	}
	
	/**
	 * Hay barco CPU.
	 * Me recibe como parametro dos enteros que representan una posicion
	 * casilla en el tablero
	 * Me retorna un true si hay un barco de CPU en esa posicion
	 * @param x the x
	 * @param y the y
	 * @return true, if successful
	 */
	public boolean hayBarcoCPU(int x, int y) {
		if(pantallaCPU.hayBarco(x, y)) {
			return true;
		}
		return false;
	}
	
	/**
	 * Hay barco usuario
	 *Me recibe como parametro dos enteros que representan una posicion
	 * casilla en el tablero
	 * Me retorna un true si hay un barco de usuario en esa posicion
	 * @param x the x
	 * @param y the y
	 * @return true, if successful
	 */
	public boolean hayBarco(int x, int y) {
		if(pantallaUsuario.hayBarco(x, y)) {
			return true;
		}
		return false;
	}
	
	/**
	 * Esta vivo.
	 * Metodo de tipo boolean que me verifica si el barco esta vivo, me reibe una posicion
	 *  A se refiere a que esta en pantallaUsuario
	 * @param i the i
	 * @param j the j
	 * @param pantalla the pantalla
	 * @return true, if successful
	 */
	public boolean estaVivo(int i, int j, String pantalla) {
		if(pantalla == "A") {
			return pantallaUsuario.barcoVivo(i, j);
		}
		return pantallaCPU.barcoVivo(i, j);
	}
	
	/**
	 * Gets the tamano barco.
	 * Me recibe una posicion ( i,j) y un String para saber en que pantalla esta
	 * y me da el tamaño
	 * @param i the i
	 * @param j the j
	 * @param pantalla the pantalla
	 * @return the tamano barco
	 */
	public int getTamanoBarco(int i, int j, String pantalla){
		if(pantalla == "A" ) { //A de aliado
			return pantallaUsuario.getTamanoBarco(i, j);
		}
		return pantallaCPU.getTamanoBarco(i, j);
	}
	
	/**
	 * Gets the barco.
	 * Me retorna el barco que esta en la posicion (i,j) y me recibe un string 
	 * dependiendo de que pantalla esta
	 * @param i the i
	 * @param j the j
	 * @param pantalla the pantalla
	 * @return the barco
	 */
	public Barcos getBarco(int i, int j, String pantalla) {
		if(pantalla == "A") { //Aliados
			return pantallaUsuario.getBarco(i, j);
		}
		return pantallaCPU.getBarco(i, j);
	}
	
}

