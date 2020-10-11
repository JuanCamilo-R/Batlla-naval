/*
 * Jennyfer Belalcazar 		- 1925639-3743
 * Samuel Riascos Prieto 	- 1922540-3743
 * Juan Camilo Randazzo		- 1923948-3743
 */
package batallaNaval;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.TitledBorder;
import javafx.scene.shape.Box;
import javax.swing.UIManager;
// TODO: Auto-generated Javadoc

/**
 * The Class VistaGUIBatallaNaval.
 */
public class VistaGUIBatallaNaval extends JFrame {
	// attributes
	/** The posiciones escoger.
	 * 	Guarda la posicion de la casilla que escogio el usuario
	 *  */
	private ArrayList<Point> posicionesEscoger;

	/** The control. 
	 *  Controla el juego
	 * */
	private ControlJuego control;
	

	/** The tipo barco escoger. 
	 *  Cada tipo de barco tiene un nombre, cuando escoja uno se cambia ese String
	 * */
	private String tipoBarcoEscoger = "";

	/** The tamano barco escoger. */
	private int tamanoBarcoEscoger = 0, ronda = 1;

	/** The tamano barco escoger.
	 * Cada tipo de barco tiene un tamano, cuando escoja uno se cambia ese Int
	 * */

	/** The zona atacar. 
	 * Jpanel para el diseño de la ventana
	 * ZonaUnidades para poner un boton que contiene cada tipo de barco
	 * ZonaCasillas donde pongo los barcos del usuario
	 * ZonaLogo para poner el logo del juego
	 * ZonaBotones donde pongo los botones que necesite segun la ventana
	 * ZonaAtacar casillas donde voy atacar al CPU
	 * */
	private JPanel zonaUnidades, zonaCasillas, zonaLogo, zonaBotones, zonaAtacar;

	/** The casillas. 
	 * Casillas donde se pone los barcos del usuario
	 * */
	private JButton[][] casillas = new JButton[10][10]; 

	/** The casillas atacar.
	 *  Casillas donde voy atacar
	 *  */
	private JButton[][] casillasAtacar = new JButton[10][10];

	/** The regla vertical ataque. */
	private JLabel[] reglaHorizontal, reglaVertical, reglaHorizontalAtaque, reglaVerticalAtaque;

	/** The historial juego.
	 * Ponemos cuales han sido mis ataques y los del CPU, y quien inicia
	 *  */
	private JTextArea historialJuego;

	/** The iniciar denuevo.
	 * Uso de los botones:
	 * limpiar: Para que el usuario vuelva a poner todos sus barcos en caso de que quiera
	 * confirmar: cuando ya el usuario pone todos sus barcos al dar confimar se da inicio al juego
	 * salir: para abandonar el juego
	 * instrucciones: Explicarle al usuario como poner los barcos
	 * verBarcosCPU: para mirar donde estan ubicados los barcos del CPU
	 * rendirse: Para salirse del juego
	 * ayuda2: Se explica las convenciones de las imagenes
	 * iniciarDeNuevo: Para iniciar un nuevo juego
	 *  */
	private JButton limpiar, confirmar, salir, instrucciones, verBarcosCPU, rendirse, ayuda2, iniciarDenuevo;

	/** The imagen. 
	 * Para poner las imaganes necesarias
	 * */
	private ImageIcon imagen;

	/** The buffer image. 
	 * Para dividir las imagenes
	 * */
	private BufferedImage bufferImage = null;

	/** The titulo. 
	 * Titulos a los JFrame
	 * */
	private Titulos titulo;

	/** The escucha.
	 * Escuchas de la clase
	 *  */
	private Escuchas escucha;

	/** The plane. */
	private JToggleButton battleship, cruiser, destroyer, plane;

	/** The unidades. */
	private ButtonGroup unidades;

	/** The planes. 
	 * Indica que puedo poner 4 barcos de tipo planes
	 * */
	private int planes = 4;

	/** The destroyers. 
	 * Indica que puedo poner 3 barcos de tipo destroyers
	 * */
	private int destroyers = 3;

	/** The cruisers. 
	 * Indica que puedo poner 2 barcos de tipo cruisers
	 * */
	private int cruisers = 2;

	/** The battleships. 
	 * Indica que puedo poner 1 barco de tipo battleships
	 * */
	private int battleships = 1;

	/** The mi misma. 
	 * Referencia a la ventana
	 * */
	private JFrame miMisma = this;

	/** The constraints. 
	 * Contenedor usado
	 * */
	private GridBagConstraints constraints;

	/** The ayuda. 
	 * Ventana para ver los barcos los CPU
	 * */
	private VerBarcosCPU ayuda;
	
	/** The ventana ayuda. 
	 * Ventana para explicarle al usuario las imagenes que salen al tocar una casilla
	 * */
	private AyudaImagenes ventanaAyuda;
	
	private boolean empiezaUsuario = false, redisenar = false;
	
	// methods

	/**
	 * Instantiates a new vista GUI batalla naval.
	 */
	public VistaGUIBatallaNaval() {
		//Se iniciar al GUI, se crean los barcos y se da inicio al juego
		initGUI();
		crearJuego();
		ayuda = new VerBarcosCPU(miMisma, control);
		this.setTitle("Batalla Naval");
		this.setUndecorated(true);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JOptionPane.showMessageDialog(null, "Leer instrucciones");
	}

	/**
	 * metodo void de la clase Crear juego.
	 * Condiciones iniciales del juego
	 */
	private void crearJuego() {
		control.iniciarJuegoCPU();
	}

	/**
	 * Se ponen las componentes de la ventana(botones,label,Jpanel)
	 */
	private void initGUI() {
		
		control = new ControlJuego();
		// Posiciones escoger
		posicionesEscoger = new ArrayList<Point>();
		
		// Inicializar escucha.
		escucha = new Escuchas();
		this.getContentPane().setLayout(new GridBagLayout());
		constraints = new GridBagConstraints();
		
		//Ponemos un titulo en el JFrame
		titulo = new Titulos("¡Organiza tu flota!", 30, Color.black);
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 3;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		add(titulo, constraints);

		//Ponemos los botones, cada uno tiene un tipo de barco
		zonaUnidades = new JPanel();
		zonaUnidades.setLayout(new BoxLayout(zonaUnidades, BoxLayout.Y_AXIS));
		imagen = new ImageIcon("src/imagenes/Battleship.png");
		battleship = new JToggleButton("" + battleships);
		battleship.addActionListener(escucha);
		battleship.setIcon(imagen);
		battleship.setVerticalTextPosition(SwingConstants.TOP);
		imagen = new ImageIcon("src/imagenes/Cruiser.png");
		cruiser = new JToggleButton("" + cruisers);
		cruiser.addActionListener(escucha);
		cruiser.setIcon(imagen);
		cruiser.setVerticalTextPosition(SwingConstants.TOP);
		imagen = new ImageIcon("src/imagenes/Destroyer.png");
		destroyer = new JToggleButton("" + destroyers);
		destroyer.addActionListener(escucha);
		destroyer.setIcon(imagen);
		destroyer.setVerticalTextPosition(SwingConstants.TOP);
		imagen = new ImageIcon("src/imagenes/Plane.png");
		plane = new JToggleButton("" + planes);
		plane.addActionListener(escucha);
		plane.setIcon(imagen);
		plane.setVerticalTextPosition(SwingConstants.TOP);
		plane.setMaximumSize(new Dimension(plane.getMaximumSize().width, plane.getMaximumSize().height));
		destroyer.setMaximumSize(new Dimension(plane.getMaximumSize().width, destroyer.getMaximumSize().height));
		cruiser.setMaximumSize(new Dimension(plane.getMaximumSize().width, battleship.getMaximumSize().height));
		battleship.setMaximumSize(new Dimension(plane.getMaximumSize().width, battleship.getMaximumSize().height));
		unidades = new ButtonGroup();
		unidades.add(battleship);
		unidades.add(cruiser);
		unidades.add(destroyer);
		unidades.add(plane);
		zonaUnidades.add(battleship);
		zonaUnidades.add(cruiser);
		zonaUnidades.add(destroyer);
		zonaUnidades.add(plane);
		zonaUnidades.setBorder(new TitledBorder("Unidades"));
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 2;
		constraints.fill = GridBagConstraints.BOTH;
		add(zonaUnidades, constraints);
		
		//JPanel de las casillas de los barcos del usuario y donde voy atacar
		zonaAtacar = new JPanel(new GridLayout(11, 11));
		zonaCasillas = new JPanel();
		zonaCasillas.setLayout(new GridLayout(11, 11));
		reglaHorizontal = new JLabel[11];
		reglaVertical = new JLabel[10];
		String texto = "[Y,X]";
		for (int i = 0; i < 11; i++) {
			reglaHorizontal[i] = new JLabel(texto);
			reglaHorizontal[i].setHorizontalAlignment(SwingConstants.CENTER);
			texto = "" + i;
			zonaCasillas.add(reglaHorizontal[i]);
		}
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 11; j++) {
				if (j == 0) {
					reglaVertical[j] = new JLabel("" + i);
					reglaVertical[j].setHorizontalAlignment(SwingConstants.CENTER);
					zonaCasillas.add(reglaVertical[j]);
				} else {
					casillas[i][j - 1] = new JButton();
					casillas[i][j - 1].addActionListener(escucha);
					casillas[i][j - 1].setPreferredSize(new Dimension(50, 50));
					casillas[i][j - 1].setBackground(Color.blue);
					//Agregamos la casillas donde el usuario pone los barcos
					zonaCasillas.add(casillas[i][j - 1]);
				}
			}
		}
		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.NONE;
		add(zonaCasillas, constraints);

		//Logo del juego
		zonaLogo = new JPanel();
		JLabel Logo = new JLabel();
		imagen = new ImageIcon("src/imagenes/Logo.png");
		Logo.setIcon(imagen);
		zonaLogo.add(Logo);
		constraints.gridx = 1;
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.NONE;
		add(zonaLogo, constraints);

		//Botones que se van a usar para el usuario
		zonaBotones = new JPanel();
		zonaBotones.setLayout(new GridLayout(4, 1));
		zonaBotones.setPreferredSize(new Dimension(150, 100));
		instrucciones = new JButton("Instrucciones");
		instrucciones.addActionListener(escucha);
		instrucciones.setPreferredSize(new Dimension(120, 30));
		confirmar = new JButton("Confirmar");
		confirmar.addActionListener(escucha);
		confirmar.setPreferredSize(new Dimension(120, 30));
		zonaBotones.add(confirmar);
		limpiar = new JButton("Limpiar");
		limpiar.addActionListener(escucha);
		limpiar.setPreferredSize(new Dimension(120, 30));
		zonaBotones.add(limpiar);
		salir = new JButton("Salir");
		salir.addActionListener(escucha);
		salir.setPreferredSize(new Dimension(120, 30));
		zonaBotones.add(instrucciones);
		zonaBotones.add(salir);
		constraints.gridx = 2;
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		constraints.gridheight = 4;
		constraints.anchor = GridBagConstraints.EAST;
		constraints.fill = GridBagConstraints.VERTICAL;
		add(zonaBotones, constraints);
	}

	/**
	 * Rotate image by degrees.
	 *
	 * @param img   the img
	 * @param angle the angle
	 * @return the buffered image
	 */
	private BufferedImage rotateImageByDegrees(BufferedImage img, double angle) {

		double rads = Math.toRadians(angle);
		double sin = Math.abs(Math.sin(rads)), cos = Math.abs(Math.cos(rads));
		int w = img.getWidth();
		int h = img.getHeight();
		int newWidth = (int) Math.floor(w * cos + h * sin);
		int newHeight = (int) Math.floor(h * cos + w * sin);

		BufferedImage rotated = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = rotated.createGraphics();
		AffineTransform at = new AffineTransform();
		at.translate((newWidth - w) / 2, (newHeight - h) / 2);

		int x = w / 2;
		int y = h / 2;

		at.rotate(rads, x, y);
		g2d.setTransform(at);
		g2d.drawImage(img, 0, 0, this);
		g2d.setColor(Color.RED);
		g2d.drawRect(0, 0, newWidth - 1, newHeight - 1);
		g2d.dispose();

		return rotated;
	}

	/**
	 * Redisenar.
	 */
	// Redisena la interfaz despues de colocar los 10 barcos
	//se añaden las casillas donde vamos atacar, nuevos botones y el historial del juego
	private void redisenar() {
		redisenar = true;
		//Borramos lo que tenia el contenedor
		getContentPane().removeAll();
		
		this.getContentPane().setLayout(new GridBagLayout());
		zonaUnidades.removeAll();
		zonaBotones.removeAll();
		remove(zonaUnidades);
		this.repaint();
		//Creamos las casillas donde vamos atacar
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				casillasAtacar[i][j] = new JButton();
			}
		}

		reglaHorizontalAtaque = new JLabel[11];
		reglaVerticalAtaque = new JLabel[10];
		String texto = "[Y,X]";
		for (int i = 0; i < 11; i++) {
			reglaHorizontalAtaque[i] = new JLabel(texto);
			reglaHorizontalAtaque[i].setHorizontalAlignment(SwingConstants.CENTER);
			texto = "" + i;
			zonaAtacar.add(reglaHorizontalAtaque[i]);
		}
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 11; j++) {
				if (j == 0) {
					reglaVerticalAtaque[j] = new JLabel("" + i);
					reglaVerticalAtaque[j].setHorizontalAlignment(SwingConstants.CENTER);
					zonaAtacar.add(reglaVerticalAtaque[j]);
				} else {
					casillasAtacar[i][j - 1] = new JButton();
					casillasAtacar[i][j - 1].addActionListener(escucha);
					casillasAtacar[i][j - 1].setPreferredSize(new Dimension(45, 45));
					casillasAtacar[i][j - 1].setBackground(Color.blue);
					casillas[i][j - 1].setPreferredSize(new Dimension(45, 45));
					//agregamos las casillas donde ataca el usuario
					zonaAtacar.add(casillasAtacar[i][j - 1]);
				}
			}
		}
		
		//Ponemos de nuevo el titulo del juego
		titulo.setText("ATACA!");
		titulo.setFont(new Font(titulo.getText(), 1, 30));
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 3;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.BOTH;
		add(titulo, constraints);

		//Agregamos la zonaCasillas(los barcos del usuario)
		zonaCasillas.setBorder(new TitledBorder("Mis barcos"));
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.BOTH;
		add(zonaCasillas, constraints);
		
		//Ponemos las casillas donde vamos atacar
		zonaAtacar.setBorder(new TitledBorder("Zona Atacar"));
		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.BOTH;
		add(zonaAtacar, constraints); 
		
		//Ponemos el logo
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.NONE;
		add(zonaLogo, constraints);

		// Contiene el historial juego y el boton ver barcos CPU
		zonaBotones.removeAll(); 
		historialJuego = new JTextArea(27, 10);
		historialJuego.setEditable(false);
		JScrollPane scroll = new JScrollPane(historialJuego);
		constraints.gridx = 2;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		add(scroll, constraints);

		//Ponemos los nuevos botones
		zonaBotones.setLayout(new GridLayout(4, 1));
		verBarcosCPU = new JButton("Ver barcos CPU");
		verBarcosCPU.setPreferredSize(new Dimension(150, 50));
		verBarcosCPU.addActionListener(escucha);
		rendirse = new JButton("Rendirse/Salir");
		rendirse.setPreferredSize(new Dimension(150, 50));
		rendirse.addActionListener(escucha);
		ayuda2 = new JButton("Convenciones");
		ayuda2.setPreferredSize(new Dimension(150, 50));
		ayuda2.addActionListener(escucha);
		iniciarDenuevo = new JButton("Iniciar de nuevo");
		iniciarDenuevo.setPreferredSize(new Dimension(150, 50));
		iniciarDenuevo.addActionListener(escucha);
		zonaBotones.add(verBarcosCPU);
		zonaBotones.add(rendirse);
		zonaBotones.add(ayuda2);
		zonaBotones.add(iniciarDenuevo);
		constraints.gridx = 2;
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		constraints.gridheight = 4;
		constraints.anchor = GridBagConstraints.EAST;
		constraints.fill = GridBagConstraints.VERTICAL;
		add(zonaBotones, constraints);
		titulo.addMouseListener(escucha);
		titulo.addMouseMotionListener(escucha);
		repaint();
		pack();
		miMisma.setLocationRelativeTo(null);
	}

	/**
	 * The Class Escuchas.
	 */
	private class Escuchas implements ActionListener, MouseMotionListener, MouseListener  {
		private int x, y;
		/**
		 * Action performed.
		 *
		 * @param event the event
		 */
		@Override
		public void actionPerformed(ActionEvent event) {
			// TODO Auto-generated method stub
			
			//Boton que muestra un JFrame que contiene los barcos del CPU
			if (event.getSource() == verBarcosCPU) {
				ayuda = new VerBarcosCPU(miMisma,control);
				ayuda.setVisible(true);

			}
			
			//Boton que muestra un JFrame que contiene ayuda para el usuario entender
			//las imagenes que le sale al atacar
			if (event.getSource() == ayuda2) {
				ventanaAyuda = new AyudaImagenes();
				ventanaAyuda.setVisible(true);

			}
			
			//Mensaje de instruccion de como se deben poner los barcos
			if (event.getSource() == instrucciones) {
				String[] options = { "Aceptar" };
				JOptionPane.showOptionDialog(null,
						"Para iniciar el juego debes colocar todos los barcos y dar en confirmar \n"
								+ "				Para colocar un barco:  \n"
								+ "				Primero dale click al barco que quieres poner  \n"
								+ "				Escoge la cabeza y la cola del mismo en las casillas  azules: \n"
								+ "				El primer barco tiene un tamano de 4 casillas.\n"
								+ "				El segundo barco tiene un tamano de 3 casillas\n"
								+ "				El tercer barco tiene un tamano de 2 casillas.\n"
								+ "				El cuarto barco tiene un tamano de 1 casilla \n"
								+ "             Nota: Solo se aceptan barcos de forma vertical y horizontal \n"
								+"				El boton limpiar te permite poner los barcos de nuevo",
						"INSTRUCCIONES", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options,
						options[0]);
			}
			
			//Boton para salirse del juego
			if (event.getSource() == salir) {
				System.exit(0);
			} else if (event.getSource() == limpiar) {
				//El boton limpiar sirve por si el usuario quiere poner de nuevo sus barcos
				// Limpiamos el string
				tipoBarcoEscoger = "";
				for (int i = 0; i < 10; i++) {
					for (int j = 0; j < 10; j++) {
						casillas[i][j].setIcon(null);
					}
				}
				control.limpiarBarcos();
				// Actualizamos las cantidades de los barcos
				planes = 4;
				battleships = 1;
				cruisers = 2;
				destroyers = 3;
				actualizar();
				// Activamos los botones
				plane.setEnabled(true);
				battleship.setEnabled(true);
				cruiser.setEnabled(true);
				destroyer.setEnabled(true);
				unidades.clearSelection();
				// Limpiar toda la matriz
			} else if (event.getSource() == confirmar) {
				/*Cuando se ponen todos los barcos y se da en confirmar se redisena la
				 * ventana y empieza el usuario ya puede atacar los barcos del CPU */
				
				//Se verifica que el usuario ya puso todos sus barcos
				if (cruisers == 0 && battleships == 0 && destroyers == 0 && planes == 0) {
					
					// Remover los escuchas de las casillas de los barcos aliados.
					for (int i = 0; i < 10; i++) {
						for (int j = 0; j < 10; j++) {
							casillas[i][j].removeActionListener(escucha);
						}
					}
					//Rediseñamos la ventana para poner la zonaAtaque, otros botones y el historial del juego
					redisenar();


					historialJuego.append("Ronda: "+ronda+ "\n");
					if (control.retornarTurno() == 1) { //Empieza usuario
						empiezaUsuario = true;
						historialJuego.append("Empieza el usuario \n");
					} else {
						//Empieza CPU
						empiezaUsuario = false;
						historialJuego.append("Empezo CPU \n");
						jugar();
					}
				} else {
					//Si no ha puesto todos lols barcos le informamos que lo debe hacer
					JOptionPane.showMessageDialog(null, "Coloque todos los barcos");
				}

			} else if (event.getSource() == plane && Integer.parseInt(plane.getText()) != 0) { 
				/*Si se presiona ese boton quiere decir que el usuario quiere poner ese barco
				que tiene estas caracteristicas:
				 Tamano 1, cantidad: 4*/
				tipoBarcoEscoger = "plane";
				tamanoBarcoEscoger = 1;
				posicionesEscoger.clear();

			} else if (event.getSource() == battleship && Integer.parseInt(battleship.getText()) != 0) { 
				/*Si se presiona ese boton quiere decir que el usuario quiere poner ese barco
				que tiene estas caracteristicas:
				 Tamano 4, cantidad: 1*/
				tipoBarcoEscoger = "battleship";
				tamanoBarcoEscoger = 4;
				posicionesEscoger.clear();
				
			} else if (event.getSource() == destroyer) { 
				/*Si se presiona ese boton quiere decir que el usuario quiere poner ese barco
				que tiene estas caracteristicas:
				 tamano 2 , cantidad: 3*/
				tipoBarcoEscoger = "destroyer";
				tamanoBarcoEscoger = 2;
				posicionesEscoger.clear();
				
			} else if (event.getSource() == cruiser) { 
				/*Si se presiona ese boton quiere decir que el usuario quiere poner ese barco
				que tiene estas caracteristicas:
				tamano 3 , cantidad: 2*/
				tamanoBarcoEscoger = 3;
				tipoBarcoEscoger = "cruiser";
				posicionesEscoger.clear();
			}
			
			// Si quiere volver a jugar, volvemos  las condiciones iniciales
			if (event.getSource() == iniciarDenuevo) {
				getContentPane().removeAll();
				tipoBarcoEscoger = "";
				for (int i = 0; i < 10; i++) {
					for (int j = 0; j < 10; j++) {
						casillas[i][j].setIcon(null);
					}
				}
				control.limpiarBarcos();
				
				// Actualizamos las cantidades de los barcos
				planes = 4;
				battleships = 1;
				cruisers = 2;
				destroyers = 3;
				ronda=1;
				actualizar();
				
				// Activamos los botones
				plane.setEnabled(true);
				battleship.setEnabled(true);
				cruiser.setEnabled(true);
				destroyer.setEnabled(true);
				unidades.clearSelection();
				//Iniciamos la GUI
				initGUI();
				control.iniciarJuegoCPU();
				setTitle("Batalla Naval");
				pack();
				setLocationRelativeTo(null);
				setResizable(false);
				setVisible(true);
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			}
			
			try {
				//Para que el usuario ponga los barcos
				for (int i = 0; i < 10; i++) {
					for (int j = 0; j < 10; j++) {
						if (casillas[i][j] == event.getSource()) {
							if(cruisers != 0 || battleships != 0 || destroyers != 0 || planes != 0) {
								posicionesEscoger.add(new Point(i, j));
								if (tipoBarcoEscoger.equals("plane")) {
									posicionesEscoger.add(new Point(i, j));
								}
								if (posicionesEscoger.size() == 2) {

									// Guarda el mismo barco para ponerlo en la PantallaUsuario y pintarlo en la GUI

									Barcos barcoSeleccionado = control.retornarBarco(tamanoBarcoEscoger);
									control.ponerBarco((int) posicionesEscoger.get(0).getX(),
											(int) posicionesEscoger.get(0).getY(), (int) posicionesEscoger.get(1).getX(),
											(int) posicionesEscoger.get(1).getY(), tamanoBarcoEscoger, barcoSeleccionado);
									if (barcoSeleccionado.isSeleccionado() == true) {
										pintarBarco(barcoSeleccionado);
										
										// Resta el numero de unidades navales disponibles

										switch (tipoBarcoEscoger) {
										case "battleship":
											battleships--;
											break;
										case "cruiser":
											cruisers--;
											break;
										case "destroyer":
											destroyers--;
											break;
										case "plane":
											planes--;
											break;
										default:
											break;
										}

										actualizar();
									} else {
										//Sale en caso de que se pone un barco con tamano equivocado o de forma diagonal
										JOptionPane.showMessageDialog(null, "No coloco barco, intentalo de nuevo");
									}
									posicionesEscoger.clear();
								}
							}
						}
					}
				}
			} catch(Exception e){
				JOptionPane.showMessageDialog(null, "No puedes colocar mas barcos de ese tipo");
			}
			
			// Cuando el usuario va atacar le damos esa informacion a la funcion ataqueAliado
						//pasandole la posicion que escogio
						for (int i = 0; i < 10; i++) {
							for (int j = 0; j < 10; j++) {
								if (event.getSource() == casillasAtacar[i][j]) {
									ataqueAliado(i, j);
								}
							}
						}
						
			//Si el usuario se rinde tiene la opcion de salirse o seguir si le dio por equivacion
			if (event.getSource() == rendirse) {
				String[] options = { "Si", "No" };
				int input = JOptionPane.showOptionDialog(null, "¿Seguro?", "Rendirse/Salir", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

				if (input == 0) {
					JOptionPane.showMessageDialog(null, "Has abandonado el juego");
					System.exit(0);
				} else {
					// Sigue el juego

				}
			}

		}

		@Override
		public void mouseDragged(MouseEvent e) {
			
			if(redisenar) {
				setLocation(miMisma.getLocation().x+ (e.getX()-x),
						miMisma.getLocation().y+(e.getY()-y));
			}
			
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent event) {
			// TODO Auto-generated method stub
			x = event.getX();
			y = event.getY();
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

	}

	/**
	 * Jugar.
	 * Funcion que usa la CPU para jugar
	 */
	//Tira el CPU y ponemos las imagenes indiacado si es agua, tocado o hundido
	private void jugar() {
		control.ataque(null);
		//Ponemos donde tiro el CPU
		historialJuego.append("El cpu tiro en [" + (int) control.retornarPosicion().getX() + ","
				+ (int) control.retornarPosicion().getY() + "] \n");
		if(control.hayBarco((int)control.retornarPosicion().getX(),(int) control.retornarPosicion().getY())) {
			casillas[(int)control.retornarPosicion().getX()][(int) control.retornarPosicion().getY()].setBackground(new Color(67,179,174));
			imagen = new ImageIcon("src/imagenes/tocado2.png");
			casillas[(int)control.retornarPosicion().getX()][(int)control.retornarPosicion().getY()].setIcon(new ImageIcon(imagen.getImage().getScaledInstance(45,45, Image.SCALE_DEFAULT)));
			if(!control.estaVivo((int)control.retornarPosicion().getX(), (int) control.retornarPosicion().getY(),"A")) {
				Barcos barquito = control.getBarco((int)control.retornarPosicion().getX(), (int) control.retornarPosicion().getY(), "A");
				imagen = new ImageIcon("src/imagenes/hundido2.png");
				for(int b = 0; b < barquito.getTamano(); b++) {
					casillas[barquito.retornarX(b)][barquito.retornarY(b)].setIcon(
							new ImageIcon(imagen.getImage().getScaledInstance(45,45, Image.SCALE_DEFAULT)));
				}
			}
			
		}else {
			casillas[(int)control.retornarPosicion().getX()][(int) control.retornarPosicion().getY()].setBackground(new Color(67,179,174));
			imagen = new ImageIcon("src/imagenes/agua.png");
			casillas[(int)control.retornarPosicion().getX()][(int)control.retornarPosicion().getY()].setIcon(new ImageIcon(imagen.getImage().getScaledInstance(45,45, Image.SCALE_DEFAULT)));
		}
		//Si retorna 1 es porque el usuario perdio
		if (control.perdio() == 1) {
			JOptionPane.showMessageDialog(null,
					"Se acabo \n Gano CPU \n De click en Iniciar de nuevo" + "\n si quiere volver a jugar");
			historialJuego.append("El cpu tiro en [" + (int) control.retornarPosicion().getX() + ","
					+ (int) control.retornarPosicion().getY() + "] \n");
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					casillasAtacar[i][j].removeActionListener(escucha);
				}
			}
			//Si retorna 0 es porque el CPU perdio
		} else if (control.perdio() == 0) {
			JOptionPane.showMessageDialog(null,
					"Se acabo \n Gano Usuario \n De click en Iniciar de nuevo" + "\n si quiere volver a jugar");
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					casillasAtacar[i][j].removeActionListener(escucha);
				}
			}
		}else {
			if(empiezaUsuario == true) {
				ronda++;
				historialJuego.append("Ronda: "+ronda+"\n");
			}
		}
	}

	/**
	 * 
	 * Ataque aliado.
	 *Recibe como parametro la posicion donde ataco el usuario
	 * Tira el usuario y ponemos las imagenes indiacado si es agua, tocado o hundido
	 * @param i the i
	 * @param j the j
	 */
	private void ataqueAliado(int i, int j) {
		if (control.ataqueValido(new Point(i, j))) {
			casillasAtacar[i][j].removeActionListener(escucha);
			casillasAtacar[i][j].setBackground(new Color(67,179,174));
			control.ataque(new Point(i, j));
			if(control.hayBarcoCPU(i, j)) {
				imagen = new ImageIcon("src/imagenes/tocado2.png");
				casillasAtacar[i][j].setIcon(new ImageIcon(imagen.getImage().getScaledInstance(45,45, Image.SCALE_DEFAULT)));
				if(!control.estaVivo(i, j,"C")) {
					Barcos barquito = control.getBarco(i, j, "C"); //C de CPU
					imagen = new ImageIcon("src/imagenes/hundido2.png");
					for(int b = 0; b < barquito.getTamano(); b++) {
						casillasAtacar[barquito.retornarX(b)][barquito.retornarY(b)].setIcon(
								new ImageIcon(imagen.getImage().getScaledInstance(45,45, Image.SCALE_DEFAULT)));
					}
				}
			}else {
				imagen = new ImageIcon("src/imagenes/agua.png");
				casillasAtacar[i][j].setIcon(new ImageIcon(imagen.getImage().getScaledInstance(45,45, Image.SCALE_DEFAULT)));
			}
			
			if (control.perdio() == 1) { // GANA CPU
				JOptionPane.showMessageDialog(null,
						"Se acabo \n Gano CPU \n De click en Iniciar de nuevo" + "\n si quiere volver a jugar");
				for (int k = 0; k < 10; k++) {
					for (int m = 0; m < 10; m++) {
						casillasAtacar[k][m].removeActionListener(escucha);
					}
				}

			} else if (control.perdio() == 0) { //GANA USUARIO
				historialJuego.append("El usuario tiro en[" + i + "," + j + "] \n");
				JOptionPane.showMessageDialog(null,
						"Se acabo \n Gano Usuario \n De click en Iniciar de nuevo" + "\n si quiere volver a jugar");
				for (int k = 0; k < 10; k++) {
					for (int m = 0; m < 10; m++) {
						casillasAtacar[k][m].removeActionListener(escucha);
					}
				}
			} else {
				historialJuego.append("El usuario tiro en[" + i + "," + j + "] \n");
				if(!empiezaUsuario) {
					ronda++;
					historialJuego.append("Ronda: "+ronda+"\n");
				}
				//Si no ha ganado tira CPU
				jugar();
			}
		} else {
			//Si escoge una casilla donde ya habia escogido antes
			historialJuego.append("Escoge de nuevo \n");
		}
	}


	// Actualiza el estado de los JToggleButtons

	/**
	 * Actualizar.
	 */
	private void actualizar() {
		// TODO Auto-generated method stub
		battleship.setText("" + battleships);
		if (battleships == 0 && battleship.isSelected()) {
			battleship.setSelected(false);
			unidades.clearSelection();
			battleship.setEnabled(false);
		}
		cruiser.setText("" + cruisers);
		if (cruisers == 0 && cruiser.isSelected()) {
			cruiser.setSelected(false);
			unidades.clearSelection();
			cruiser.setEnabled(false);
		}
		destroyer.setText("" + destroyers);
		if (destroyers == 0 && destroyer.isSelected()) {
			destroyer.setSelected(false);
			unidades.clearSelection();
			destroyer.setEnabled(false);
		}
		plane.setText("" + planes);
		if (planes == 0 && plane.isSelected()) {
			plane.setSelected(false);
			unidades.clearSelection();
			plane.setEnabled(false);
		}
	}

	// Pinta un barco en la pantalla

	/**
	 * Pintar barco.
	 *
	 * @param barcoAPintar the barco A pintar
	 */
	private void pintarBarco(Barcos barcoAPintar) {
		// TODO Auto-generated method stub
		String filePath = "src/imagenes/" + tipoBarcoEscoger + ".png";
		if (barcoAPintar.getTamano() == 1) {
			casillas[barcoAPintar.retornarX(0)][barcoAPintar.retornarY(0)].setIcon(new ImageIcon(filePath));
		} else {
			// angulo de rotacion

			int angulo = 0;

			// Inicializa las variables para el getSubimage();

			int x0 = 0;
			int y0 = 0;
			int xIncremento = 0;
			int yIncremento = 1;
			int posicionInicial = 0;

			// Generaliza las variables anteriores dependiendo de la direccion del barco

			switch (barcoAPintar.getDireccion()) {
			case "derecha":
				x0 = 0;
				y0 = 0;
				xIncremento = 1;
				yIncremento = 0;
				posicionInicial = 0;
				angulo = 270;
				break;
			case "izquierda":
				x0 = 1;
				y0 = 0;
				xIncremento = -1;
				yIncremento = 0;
				angulo = 90;
				posicionInicial = 1;
				break;
			case "abajo":
				x0 = 0;
				y0 = 1;
				xIncremento = 0;
				yIncremento = -1;
				angulo = 180;
				posicionInicial = 1;
				break;
			}

			try {
				bufferImage = ImageIO.read(new File(filePath));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			bufferImage = rotateImageByDegrees(bufferImage, angulo);
			for (int i = 0; i < barcoAPintar.getTamano(); i++) {
				Image subimagen = bufferImage.getSubimage(
						bufferImage.getWidth() * x0 + (i + posicionInicial) * xIncremento * 50,
						bufferImage.getHeight() * y0 + (i + posicionInicial) * yIncremento * 50,
						bufferImage.getWidth() + (50 - bufferImage.getWidth()) * Math.abs(xIncremento),
						bufferImage.getHeight() + (50 - bufferImage.getHeight()) * Math.abs(yIncremento));
				casillas[barcoAPintar.retornarX(i)][barcoAPintar.retornarY(i)].setIcon(new ImageIcon(subimagen));
			}
		}
	}

}
