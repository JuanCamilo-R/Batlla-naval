/*
 * Jennyfer Belalcazar 		- 1925639-3743
 * Samuel Riascos Prieto 	- 1922540-3743
 * Juan Camilo Randazzo		- 1923948-3743
 */
package batallaNaval;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VistaGUIBatallaNaval extends JFrame {
	
	public static final String rutaFile = "src/imagenes/1.png";
	
	private JButton[][] ataquesUsuario = new JButton[10][10];
	private JButton[][] posicionesUsuario = new JButton[10][10];
	private ArrayList<JLabel> numeros = new ArrayList<JLabel>();
	private ArrayList<JLabel> letras = new ArrayList<JLabel>();
	private JLabel[][] pantallaCPU = new JLabel[10][10];
	private JLabel lol = new JLabel("Hola");
	private Escuchas escucha;
	private JPanel pantallaAtaquesUsuario;
	private JPanel pantallaPosicionesUsuario;
	private JButton salir, verPosicionesCPU;
	private BufferedImage bufferImage = null;
	
	public VistaGUIBatallaNaval() {
		
		try {
			bufferImage = ImageIO.read(new File(rutaFile));
			initGUI();
			
			//default window
			this.setResizable(false);
			pack();
			this.setSize(1000,1000);
			this.setLocationRelativeTo(null);
			this.setVisible(true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	
		

	}
	
	private void initGUI() {
		
		//container
		/*
		Container contenedor = this.getContentPane();
		contenedor.setLayout(new FlowLayout());
		contenedor.setPreferredSize(new Dimension(500,500));
		*/
		//Crear el escucha
		escucha = new Escuchas();
		
		//Zona de juego
		pantallaPosicionesUsuario = new JPanel();
		pantallaAtaquesUsuario = new JPanel();
		
		//Matrices de botones.
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++ ) {
				posicionesUsuario[i][j] = new JButton("posicion");
				//ataquesUsuario[i][j] = new JButton("ataque");
				posicionesUsuario[i][j].setPreferredSize(new Dimension(100,100));
				//ataquesUsuario[i][j].setPreferredSize(new Dimension(100,100));
				posicionesUsuario[i][j].setBackground(Color.BLUE);
				posicionesUsuario[i][j].addActionListener(escucha);
				//ataquesUsuario[i][j].setBackground(Color.BLUE);
				//ataquesUsuario[i][j].addActionListener(escucha);
				pantallaPosicionesUsuario.add(posicionesUsuario[i][j]);
				//pantallaAtaquesUsuario.add(ataquesUsuario[i][j]);
			}
		}
		
		add(pantallaPosicionesUsuario, BorderLayout.WEST);
		//add(pantallaAtaquesUsuario);
		lol();
		
	}
	
	public void lol() {
		add(pantallaAtaquesUsuario,BorderLayout.EAST);
	}
	private class Escuchas implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}

}
