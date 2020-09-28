/*
 * Jennyfer Belalcazar 		- 1925639-3743
 * Samuel Riascos Prieto 	- 1922540-3743
 * Juan Camilo Randazzo		- 1923948-3743
 */
package batallaNaval;
public class PrincipalBatallaNaval {

	public static void main(String[] args) {
		Barcos [][] barcosPantalla = new Barcos[10][10];
		PantallaUsuario pantalla = new PantallaUsuario();
		//PantallaCPU cpu = new PantallaCPU();
		ControlJuego control = new ControlJuego();
		control.crearBarcos();
		//pantalla.ponerBarco(0, 0, 0, 4, 4, control.retornarBarco(0));
		//pantalla.ponerBarco(0, 1, 0, 4, 4, control.retornarBarco(0));
		//System.out.print(control.retornarBarco(0).getTamano());
		//pantalla.mostrar();
		control.mostrar();

	}

}
