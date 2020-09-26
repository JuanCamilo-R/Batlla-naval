package batallaNaval;

import java.util.ArrayList;
import java.util.Random;

public class ControlJuego {
	//Atributos.
	private ArrayList <Barcos> barcos;
	private ArrayList <Barcos> barcosCPU;
	private PantallaUsuario pantallaUsuario;
	private PantallaCPU pantallaCPU;
	private Random aleatorio;
	private int turno;
	
	//metodos.
	public ControlJuego() {
		pantallaCPU = new PantallaCPU();
		aleatorio = new Random();
		barcos = new ArrayList<Barcos>();
		barcosCPU = new ArrayList<Barcos>();
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
	
	
	public void decidirTurno() {
		turno = aleatorio.nextInt(2);
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
