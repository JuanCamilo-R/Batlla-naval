package batallaNaval;

import java.util.ArrayList;
import java.util.Random;

public class ControlJuego {
	//Atributos.
	private ArrayList <Barcos> barcos;
	private PantallaUsuario pantallaUsuario;
	private PantallaCPU pantallaCPU;
	private Random aleatorio;
	private int turno;
	
	//metodos.
	public ControlJuego() {
		aleatorio = new Random();
		barcos = new ArrayList<Barcos>();
		
	}
	
	public void agregarBarcos() {
		int aux = 4;
		int capacidad = 1;
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < aux; j++) {
				barcos.add(new Barcos(capacidad));
			}
			aux--;
			capacidad++;
		}
	}
	public void determinarJuego() {
		
	}
	
	public void recibirPosiciones(int x, int y) {
		pantallaUsuario.ponerBarco(x, y);
	}
	
	public void decidirTurno() {
		turno = aleatorio.nextInt(2);
	}
	
	public int retornarTurno() {
		return turno;
	}
	
	public void eliminarBarco() {
		
	}
	
}
