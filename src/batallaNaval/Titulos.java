/*
 * Jennyfer Belalcazar 		- 1925639-3743
 * Samuel Riascos Prieto 	- 1922540-3743
 * Juan Camilo Randazzo		- 1923948-3743
 */
package batallaNaval;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

// TODO: Auto-generated Javadoc
/**
 * The Class Titulos.
 * Clase creada para poner los titulos en el JFrame
 */
public class Titulos extends JLabel {
	//atributos
	
	
		/**
	 * Instantiates a new titulos.
	 *
	 * @param texto the texto
	 * @param tamaño the tamaño
	 * @param colorFondo the color fondo
	 */
	//metodos
		public Titulos(String texto, int tamaño, Color colorFondo) {
			this.setText(texto);
			Font font = new Font(Font.SERIF,Font.BOLD+Font.ITALIC,tamaño);//fuente, tipo letra, tamaño
			this.setFont(font);
			this.setBackground(colorFondo); //fondo JLabel
			this.setForeground(Color.WHITE);//Color de la letra
			this.setHorizontalAlignment(JLabel.CENTER);
			this.setOpaque(true);//para ver el fondo
		}
		
}
