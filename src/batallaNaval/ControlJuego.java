/*
 * Jennyfer Belalcazar 		- 1925639-3743
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
	private int destruidos=0;
	private int turnos=0;
	private int ronda = 1;
	private int intentos=0;
	private int turno;
	private int indexDireccionPreferida;
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
	//Crea barcos CPU y jugador.
	public void crearBarcos() {
		int aux = 4;
		int capacidad = 1;
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < aux; j++) {
				barcosCPU.add(new Barcos(capacidad));
				barcos.add(new Barcos(capacidad));
				//System.out.print(capacidad+"\n");
			}
			//System.out.print(i+"\n");
			aux--;
			capacidad++;
		}
		//Ponemos los barcos enemigos.
		for (int i = 0; i < barcosCPU.size(); i++) {
			pantallaCPU.ponerBarco2(barcosCPU.get(i).getTamano(), barcosCPU.get(i));
		}
	}
	
	
	/**
	 * Determinar juego.
	 */
	
	public void iniciarJuegoCPU() {
		crearBarcos(); //Creamos barcos enemigos y aliados.
	}
	
	//Ataca y define si alguien perdió.
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
					inteligenciaCPU();
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
			ronda++;
		}
	}
	
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
					posicionesAtacadasCPU[posicionGolpeada.x][posicionGolpeada.y]=3;
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
	
	/**
	 * Max inteligencia CPU.
	 *
	 * @param posicionGolpeada the posicion golpeada
	 */
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
	
	public void ponerBarco(int x1, int y1, int x2, int y2, int tamano, Barcos barco) {
		pantallaUsuario.ponerBarco(x1, y1, x2, y2, tamano, barco);
		barco.setSeleccionado(true);
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
		System.out.println("Pantalla Usuario");
		pantallaUsuario.mostrar();
		System.out.println("Pantalla CPU");
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
	
	public Barcos retornarBarco(int tamano) {
		for(int i = 0; i < barcos.size(); i++) {
			if(barcos.get(i).getTamano() == tamano && barcos.get(i).isSeleccionado() == false) {
				
				return barcos.get(i);
			}
		}
		return null;
	}

}
