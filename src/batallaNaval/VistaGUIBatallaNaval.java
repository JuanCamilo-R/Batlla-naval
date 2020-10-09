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
import misComponentes.Titulos;
import javax.swing.UIManager;
// TODO: Auto-generated Javadoc

/**
 * The Class VistaGUIBatallaNaval.
 */
public class VistaGUIBatallaNaval extends JFrame {
	
	/** The posiciones escoger. */
	//attributes
	private ArrayList<Point> posicionesEscoger;
	
	/** The control. */
	private ControlJuego control;
	
	/** The type writter. */
	private TypeWritter typeWritter;
	
	/** The tipo barco escoger. */
	private String tipoBarcoEscoger = "";
	
	/** The tamano barco escoger. */
	private int tamanoBarcoEscoger = 0;
	
	/** The mensaje L. */
	private JLabel mensajeL;
	
	/** The zona atacar. */
	private JPanel zonaUnidades,zonaCasillas,zonaLogo,zonaBotones, zonaCasillasAtacar, zonaAtacar;
	
	/** The casillas. */
	private JButton[][] casillas = new JButton[10][10]; //Casillas donde se pone los barcos
	
	/** The casillas atacar. */
	private JButton[][] casillasAtacar = new JButton[10][10];
	
	/** The regla vertical ataque. */
	private JLabel[] reglaHorizontal, reglaVertical, reglaHorizontalAtaque, reglaVerticalAtaque;
	
	/** The historial juego. */
	private JTextArea historialJuego;
	
	/** The flota. */
	private JLabel[][] flota= new JLabel[10][10];
	
	/** The iniciar denuevo. */
	private JButton limpiar,confirmar,salir, instrucciones, verBarcosCPU, rendirse, confirmarAtaque,iniciarDenuevo;
	
	/** The imagen. */
	private ImageIcon imagen;
	
	/** The buffer image. */
	private BufferedImage bufferImage=null;
	
	/** The titulo. */
	private Titulos titulo;
	
	/** The escucha. */
	private Escuchas escucha;
	
	/** The plane. */
	private JToggleButton battleship, cruiser, destroyer, plane;
	
	/** The unidades. */
	private ButtonGroup unidades;
	
	/** The planes. */
	private int planes=4;
	
	/** The destroyers. */
	private int destroyers=3;
	
	/** The cruisers. */
	private int cruisers=2;
	
	/** The battleships. */
	private int battleships=1;
	
	/** The mi misma. */
	private JFrame miMisma = this;
	
	/** The constraints. */
	private GridBagConstraints constraints;
	//methods
	
	/**
	 * Instantiates a new vista GUI batalla naval.
	 */
	public VistaGUIBatallaNaval() {
		initGUI();
		crearJuego();
		this.setTitle("Batalla Naval");
		this.pack();
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JOptionPane.showMessageDialog(null, "Leer instrucciones");
	}
	
	/**
	 * Crear juego.
	 */
	private void crearJuego() {
		control.iniciarJuegoCPU();
	}
	
	/**
	 * Inits the GUI.
	 */
	private void initGUI() {
		// TODO Auto-generated method stub}
		//ControlJuego
		typeWritter = new TypeWritter();
		/*String mensaje = " <html>Para iniciar el juego, debes colocar todos los barcos.<br>"+    
				"Para colocar un barco, escoge la cabeza y la cola del mismo:<br>" + 
				"El primer barco tiene un tamano de 4 casillas.<br>" + 
				"El segundo barco tiene un tamano de 3 casillas.<br>" + 
				"El tercer barco tiene un tamano de 2 casillas. <br>" + 
				"El cuarto barco tiene un tamano de 1 casilla.</html>";
		mensajeL = new JLabel(mensaje);*/
		control = new ControlJuego();
		//Posiciones escoger
		posicionesEscoger = new ArrayList<Point>();
		//Inicializar escucha.
		escucha = new Escuchas();
		this.getContentPane().setLayout(new GridBagLayout());
		constraints = new GridBagConstraints();
		titulo = new Titulos("¡Organiza tu flota!", 30, Color.black);
		constraints.gridx=0;
		constraints.gridy=0;
		constraints.gridwidth=3;
		constraints.fill=GridBagConstraints.HORIZONTAL;
		
		add(titulo,constraints);
		
		
		zonaUnidades = new JPanel();
		zonaUnidades.setLayout(new BoxLayout(zonaUnidades,BoxLayout.Y_AXIS));
		imagen = new ImageIcon("src/imagenes/Battleship.png");
		battleship = new JToggleButton(""+battleships);
		battleship.addActionListener(escucha);
		battleship.setIcon(imagen);
		battleship.setVerticalTextPosition(SwingConstants.TOP);
		imagen = new ImageIcon("src/imagenes/Cruiser.png");
		cruiser = new JToggleButton(""+cruisers);
		cruiser.addActionListener(escucha);
		cruiser.setIcon(imagen);
		cruiser.setVerticalTextPosition(SwingConstants.TOP);
		
		imagen = new ImageIcon("src/imagenes/Destroyer.png");
		destroyer = new JToggleButton(""+destroyers);
		destroyer.addActionListener(escucha);
		destroyer.setIcon(imagen);
		destroyer.setVerticalTextPosition(SwingConstants.TOP);
		
		imagen = new ImageIcon("src/imagenes/Plane.png");
		plane = new JToggleButton(""+planes);
		plane.addActionListener(escucha);
		plane.setIcon(imagen);
		plane.setVerticalTextPosition(SwingConstants.TOP);
		plane.setMaximumSize(new Dimension(plane.getMaximumSize().width,plane.getMaximumSize().height));
		destroyer.setMaximumSize(new Dimension(plane.getMaximumSize().width,destroyer.getMaximumSize().height));
		cruiser.setMaximumSize(new Dimension(plane.getMaximumSize().width,battleship.getMaximumSize().height));
		battleship.setMaximumSize(new Dimension(plane.getMaximumSize().width,battleship.getMaximumSize().height));
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
		constraints.gridx=0;
		constraints.gridy=1;
		constraints.gridwidth=1;
		constraints.gridheight=2;
		constraints.fill=GridBagConstraints.BOTH;
		
		add(zonaUnidades,constraints);
		zonaAtacar = new JPanel(new GridLayout(11,11));
		zonaCasillas = new JPanel();
		zonaCasillas.setLayout(new GridLayout(11,11));
		reglaHorizontal = new JLabel[11];
		reglaVertical = new JLabel[10];
		String texto = "[Y,X]";
		for (int i=0;i<11;i++) {
			reglaHorizontal[i] = new JLabel(texto);
			reglaHorizontal[i].setHorizontalAlignment(SwingConstants.CENTER);
			texto=""+i;
			zonaCasillas.add(reglaHorizontal[i]);
		}
		for (int i=0;i<10;i++) {
			for(int j=0;j<11;j++) {
				if(j==0) {
					reglaVertical[j] = new JLabel(""+i);
					reglaVertical[j].setHorizontalAlignment(SwingConstants.CENTER);
					zonaCasillas.add(reglaVertical[j]);
				}
				else {
				casillas[i][j-1] = new JButton();
				casillas[i][j-1].addActionListener(escucha);
				casillas[i][j-1].setPreferredSize(new Dimension (50,50));
				casillas[i][j-1].setBackground(Color.blue);
				zonaCasillas.add(casillas[i][j-1]);
				}
			}
		}
		constraints.gridx=1;
		constraints.gridy=1;
		constraints.gridwidth=2;
		constraints.gridheight=1;
		constraints.fill=GridBagConstraints.NONE;
		
		add(zonaCasillas,constraints);
		
		zonaLogo = new JPanel();
		JLabel Logo = new JLabel();
		imagen = new ImageIcon("src/imagenes/Logo.png");
		Logo.setIcon(imagen);
		zonaLogo.add(Logo);
		constraints.gridx=1;
		constraints.gridy=2;
		constraints.gridwidth=1;
		constraints.gridheight=1;
		constraints.fill=GridBagConstraints.NONE;
		add(zonaLogo,constraints);
		
		zonaBotones = new JPanel();
		zonaBotones.setLayout(new GridLayout(4,1));
		zonaBotones.setPreferredSize(new Dimension(150,100));
		instrucciones = new JButton("Instrucciones");
		instrucciones.addActionListener(escucha);
		instrucciones.setPreferredSize(new Dimension(120,30));
		confirmar = new JButton("Confirmar");
		confirmar.addActionListener(escucha);
		confirmar.setPreferredSize(new Dimension(120,30));
		zonaBotones.add(confirmar);
		limpiar = new JButton("Limpiar");
		limpiar.addActionListener(escucha);
		limpiar.setPreferredSize(new Dimension(120,30));
		zonaBotones.add(limpiar);
		salir = new JButton("Salir");
		salir.addActionListener(escucha);
		salir.setPreferredSize(new Dimension(120,30));
		zonaBotones.add(instrucciones);
		zonaBotones.add(salir);
		constraints.gridx=2;
		constraints.gridy=2;
		constraints.gridwidth=1;
		constraints.gridheight=4;
		constraints.anchor = GridBagConstraints.EAST;
		constraints.fill=GridBagConstraints.VERTICAL;
		add(zonaBotones,constraints);
	}
	
	/**
	 * Rotate image by degrees.
	 *
	 * @param img the img
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
	//Redisena la interfaz después de colocar los 10 barcos.
	private void redisenar() {
		getContentPane().removeAll();
		
		this.getContentPane().setLayout(new GridBagLayout());
		
		zonaUnidades.removeAll();
		zonaBotones.removeAll();
		remove(zonaUnidades);
		this.repaint();
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				casillasAtacar[i][j] = new JButton();
			}
		}
		
		reglaHorizontalAtaque = new JLabel[11];
		reglaVerticalAtaque = new JLabel[10];
		 String texto = "[Y,X]";
		for (int i=0;i<11;i++) {
			reglaHorizontalAtaque[i] = new JLabel(texto);
			reglaHorizontalAtaque[i].setHorizontalAlignment(SwingConstants.CENTER);
			texto=""+i;
			zonaAtacar.add(reglaHorizontalAtaque[i]);
		}
		for (int i=0;i<10;i++) {
			for(int j=0;j<11;j++) {
				if(j==0) {
					reglaVerticalAtaque[j] = new JLabel(""+i);
					reglaVerticalAtaque[j].setHorizontalAlignment(SwingConstants.CENTER);
					zonaAtacar.add(reglaVerticalAtaque[j]);
				}
				else {
				casillasAtacar[i][j-1] = new JButton();
				casillasAtacar[i][j-1].addActionListener(escucha);
				//Cambie esto
				casillasAtacar[i][j-1].setPreferredSize(new Dimension (45,45));
				
				casillasAtacar[i][j-1].setBackground(Color.blue);
				casillas[i][j-1].setPreferredSize(new Dimension (45,45));
				zonaAtacar.add(casillasAtacar[i][j-1]);
				}
			}
		}
		titulo.setText("ATACA!");
		titulo.setFont(new Font(titulo.getText(),1,30));
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 3;
		constraints.gridheight = 1;
		constraints.fill=GridBagConstraints.BOTH;
		add(titulo,constraints);
		
		zonaCasillas.setBorder(new TitledBorder("Mis barcos"));
		
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill=GridBagConstraints.BOTH;
		add(zonaCasillas,constraints); 
		
		
		zonaAtacar.setBorder(new TitledBorder("Zona Atacar"));
		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill=GridBagConstraints.BOTH;
		add(zonaAtacar,constraints); //zona de ataque
		
		
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill=GridBagConstraints.NONE;
		add(zonaLogo,constraints);
		
		zonaBotones.removeAll(); //Contiene el historial juego y el boton ver barcos CPU
		historialJuego = new JTextArea(27,5);
		historialJuego.setEditable(false);
		JScrollPane scroll = new JScrollPane(historialJuego);
		constraints.gridx = 2;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill=GridBagConstraints.HORIZONTAL;
		//constraints.anchor = GridBagConstraints.NORTHWEST;
		add(scroll,constraints);
		
		zonaBotones.setLayout(new GridLayout(4,1));
		verBarcosCPU = new JButton("Ver barcos CPU");
		verBarcosCPU.setPreferredSize(new Dimension(120,30));
		verBarcosCPU.addActionListener(escucha);
		rendirse = new JButton("Rendirse");
		rendirse.setPreferredSize(new Dimension(120,30));
		rendirse.addActionListener(escucha);
		confirmarAtaque = new JButton("Confirmar ataque");
		confirmarAtaque.setPreferredSize(new Dimension(120,30));
		confirmarAtaque.addActionListener(escucha);
		iniciarDenuevo = new JButton("Iniciar de nuevo");
		iniciarDenuevo.setPreferredSize(new Dimension(120,30));
		iniciarDenuevo.addActionListener(escucha);
		zonaBotones.add(verBarcosCPU);
		zonaBotones.add(rendirse);
		zonaBotones.add(confirmarAtaque);
		zonaBotones.add(iniciarDenuevo);
		constraints.gridx = 2;
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		constraints.gridheight = 4;
		constraints.anchor = GridBagConstraints.EAST;
		constraints.fill=GridBagConstraints.VERTICAL;
		add(zonaBotones,constraints);
		
		repaint();
		pack();
		miMisma.setLocationRelativeTo(null);
	}
	
	
	/**
	 * The Class Escuchas.
	 */
	private class Escuchas implements ActionListener {

		/**
		 * Action performed.
		 *
		 * @param event the event
		 */
		@Override
		public void actionPerformed(ActionEvent event) {
			// TODO Auto-generated method stub
			
			if(event.getSource() == instrucciones) {
				String[] options= {"Aceptar"};
				//mensajeL.setFont(new Font("Arial", Font.BOLD, 18));
				JOptionPane.showOptionDialog(null,"Para iniciar el juego debes colocar todos los barcos y dar en confirmar \n" + 
						"				Para colocar un barco:  \n"+
						"				Primero dale click al barco que quieres poner  \n"+
						"				Escoge la cabeza y la cola del mismo en las casillas  azules: \n" + 
						"				El primer barco tiene un tamano de 4 casillas.\n" + 
						"				El segundo barco tiene un tamano de 3 casillas\n" + 
						"				El tercer barco tiene un tamano de 2 casillas.\n" + 
						"				El cuarto barco tiene un tamano de 1 casilla \n" +
						"               Nota: Solo se aceptan barcos de forma vertica y horizontal", "INSTRUCCIONES", 
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
			}
			if(event.getSource() == salir) {
				System.exit(0);
			} else if(event.getSource() == limpiar) {
				
				//Limpiamos el string
				tipoBarcoEscoger = "";
				for(int i = 0; i < 10; i++) {
					for(int j = 0; j < 10; j++ ) {
						casillas[i][j].setIcon(null);
					}
				}
				control.limpiarBarcos();
				//Actualizamos las cantidades de los barcos
				planes = 4;
				battleships = 1;
				cruisers = 2;
				destroyers = 3;
				actualizar();
				//Activamos los botones
				plane.setEnabled(true);
				battleship.setEnabled(true);
				cruiser.setEnabled(true);
				destroyer.setEnabled(true);
				unidades.clearSelection();
				//Limpiar toda la matriz
			} else if(event.getSource() == confirmar) {
				if(cruisers == 0 && battleships == 0 && destroyers == 0 && planes == 0) {
					//Remover los escuchas de las casillas de los barcos aliados.
					for(int i = 0; i < 10; i++) {
						for(int j = 0; j < 10; j++) {
							casillas[i][j].removeActionListener(escucha);
						}
					}
					redisenar();
					
					//Empieza el juego
					if(control.retornarTurno()==1) {
					
						historialJuego.append("Mi turno \n selecciona una casilla \n");
					}else {
						historialJuego.append(control.getRonda()+"\n");
						historialJuego.append("Empezo CPU \n");
						jugar();
					}
				}else {
					JOptionPane.showMessageDialog(null,"Coloque todos los barcos");
					redisenar();
					if(control.retornarTurno()==1) {
						
						historialJuego.append("Mi turno \n selecciona una casilla \n");
					}else {
						historialJuego.append("Empezo CPU \n");
						jugar();
					}
				}
				
				
				
			} else if(event.getSource() == plane && Integer.parseInt(plane.getText()) != 0) { //Tamano 1, cantidad: 4
				tipoBarcoEscoger = "plane";
				tamanoBarcoEscoger = 1;
				posicionesEscoger.clear();
				
			} else if(event.getSource() == battleship && Integer.parseInt(battleship.getText()) != 0) { //Tamano 4, cantidad 1
				tipoBarcoEscoger = "battleship";
				System.out.print("Entro aquí");
				tamanoBarcoEscoger = 4;
				posicionesEscoger.clear();
			} else if(event.getSource() == destroyer) { //tamano 2 , cantidad: 3
				tipoBarcoEscoger = "destroyer";
				tamanoBarcoEscoger = 2;
				posicionesEscoger.clear();
			} else if(event.getSource() == cruiser) { // tamano 3 , cantidad: 2
				tamanoBarcoEscoger = 3;
				tipoBarcoEscoger = "cruiser";
				posicionesEscoger.clear();
			}
			//Volver a iniciar Juego
			if(event.getSource()==iniciarDenuevo) {
				getContentPane().removeAll();
				tipoBarcoEscoger = "";
				for(int i = 0; i < 10; i++) {
					for(int j = 0; j < 10; j++ ) {
						casillas[i][j].setIcon(null);
					}
				}
				control.limpiarBarcos();
				//Actualizamos las cantidades de los barcos
				planes = 4;
				battleships = 1;
				cruisers = 2;
				destroyers = 3;
				actualizar();
				//Activamos los botones
				plane.setEnabled(true);
				battleship.setEnabled(true);
				cruiser.setEnabled(true);
				destroyer.setEnabled(true);
				unidades.clearSelection();
				initGUI();
				control.iniciarJuegoCPU();
				setTitle("Batalla Naval");
				pack();
				setLocationRelativeTo(null);
				setResizable(false);
				setVisible(true);
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			}
			
			//Turno aliado
			for(int i = 0; i < 10; i++) {
				for(int j = 0; j < 10; j++) {
					if(event.getSource() == casillasAtacar[i][j]) {
						ataqueAliado(i,j);
					}
				}
			}
			
			
			
			//Colocar barcos
			for(int i = 0; i < 10; i++) {
				for(int j = 0; j < 10; j++) {
					if(casillas[i][j] == event.getSource()) {

						
						posicionesEscoger.add(new Point(i,j));
						if(tipoBarcoEscoger.equals("plane")) {
							posicionesEscoger.add(new Point(i,j));
						}
						if(posicionesEscoger.size() == 2) {
							
							//Guarda el mismo barco para ponerlo en la PantallaUsuario y pintarlo en la GUI
							
							Barcos barcoSeleccionado = control.retornarBarco(tamanoBarcoEscoger);
								System.out.print("battleship \n");
								System.out.print("Size vector"+posicionesEscoger.size());
								control.ponerBarco((int)posicionesEscoger.get(0).getX()
										,(int)posicionesEscoger.get(0).getY() , (int)posicionesEscoger.get(1).getX(),
										(int)posicionesEscoger.get(1).getY(),tamanoBarcoEscoger
										, barcoSeleccionado);
								control.mostrar();
								if(barcoSeleccionado.isSeleccionado() == true) {
									pintarBarco(barcoSeleccionado);

									//Resta el número de unidades navales disponibles

									switch(tipoBarcoEscoger) {
									case "battleship":
										battleships--;
										break;
									case "cruiser":
										System.out.print("Entré a cruisers");
										cruisers--;
										break;
									case "destroyer":
										destroyers--;
										break;
									case "plane":
										planes--;
										break;
									}

									actualizar();
								}else {
									
									JOptionPane.showMessageDialog(null,"No coloco barco, intentalo de nuevo");
								}
								posicionesEscoger.clear();
								
								
								
								
							
							
						
							
						}
					}
				}
			}
			if(event.getSource()==rendirse) {
				String[] options= {"Si","No"};
				int input = JOptionPane.showOptionDialog(null, "¿Seguro?", "Rendirse", 
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
				
				if(input == 0) {
					JOptionPane.showMessageDialog(null, "Has abandonado el juego");
					System.exit(0);
				}else {
				//Sigue el juego
					
				}
			}
				
		
		}
		
		

	}

	/**
	 * Jugar.
	 */
	private void jugar() {
		
		control.ataque(null);
		historialJuego.append("El cpu tiro en ["+(int)control.retornarPosicion().getX()+","+(int)
				control.retornarPosicion().getY()+"] \n");
		if(control.retornarTurno()==1) {
			historialJuego.append("Tira Usuario \n");
		}
		if(control.perdio()==1) {	
			JOptionPane.showMessageDialog(null,"Se acabo \n Gano CPU \n De click en Iniciar de nuevo"
					+ "\n si quiere volver a jugar" );
			for(int i=0;i<10;i++) {
				for(int j=0;j<10;j++) {
					casillasAtacar[i][j].removeActionListener(escucha);
				}
			}
				
		}else if(control.perdio()==0){
			JOptionPane.showMessageDialog(null,"Se acabo \n Gano Usuario \n De click en Iniciar de nuevo"
					+ "\n si quiere volver a jugar" );
			for(int i=0;i<10;i++) {
				for(int j=0;j<10;j++) {
					casillasAtacar[i][j].removeActionListener(escucha);
				}
			}
		}
	}
		
		/**
		 * Ataque aliado.
		 *
		 * @param i the i
		 * @param j the j
		 */
		private void ataqueAliado(int i,int j) {
			if(control.ataqueValido(new Point(i,j))) {
				historialJuego.append("El usuario tiro en["+i +","+j+"] \n");
				control.ataque(new Point(i,j));
				jugar();
				}else {
					System.out.print("no entro");
					historialJuego.append("Escoge de nuevo \n");
			}
		}
	
	
		
	
	
	//Escribir mensajes en el JTextArea historialJuego
	/* public void slowPrint(String message, int millisPerChar) {

	        Timer timer = new Timer(millisPerChar, null);
	        timer.addActionListener(new ActionListener() {
	        	int counter = 0;
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	historialJuego.append(String.valueOf(message.charAt(counter++)));
	            	historialJuego.setCaretPosition(historialJuego.getDocument().getLength());
	            	if(counter>= message.length()) {
	            		timer.stop();
	            	}
	            }
	        });
	        timer.start();
	    }*/
	 
	 
	//Actualiza el estado de los JToggleButtons
	
	/**
	 * Actualizar.
	 */
	private void actualizar() {
		// TODO Auto-generated method stub
		battleship.setText(""+battleships);
		if(battleships==0 && battleship.isSelected()) {
			battleship.setSelected(false);
			unidades.clearSelection();
			battleship.setEnabled(false);
		}
		cruiser.setText(""+cruisers);
		if(cruisers==0 && cruiser.isSelected()) {
			cruiser.setSelected(false);
			unidades.clearSelection();
			cruiser.setEnabled(false);
		}
		destroyer.setText(""+destroyers);
		if(destroyers==0 && destroyer.isSelected()) {
			destroyer.setSelected(false);
			unidades.clearSelection();
			destroyer.setEnabled(false);
		}
		plane.setText(""+planes);
		if(planes==0 && plane.isSelected()) {
			plane.setSelected(false);
			unidades.clearSelection();
			plane.setEnabled(false);
		}
	}

	//Pinta un barco en la pantalla
	
	/**
	 * Pintar barco.
	 *
	 * @param barcoAPintar the barco A pintar
	 */
	private void pintarBarco(Barcos barcoAPintar) {
		// TODO Auto-generated method stub
		String filePath="src/imagenes/"+tipoBarcoEscoger+".png";
		if(barcoAPintar.getTamano()==1) {
			casillas[barcoAPintar.retornarX(0)][barcoAPintar.retornarY(0)].setIcon(new ImageIcon(filePath));
		}else {
			//ángulo de rotación
			
			int angulo=0;
			
			//Inicializa las variables para el getSubimage();
			
			int x0=0;
			int y0=0;
			int xIncremento=0;
			int yIncremento=1;
			int posicionInicial=0;
			
			//Generaliza las variables anteriores dependiendo de la dirección del barco
			
			switch(barcoAPintar.getDireccion()) {
			case "derecha":
				x0=0;
				y0=0;
				xIncremento=1;
				yIncremento=0;
				posicionInicial=0;
				angulo=270;
				break;
			case "izquierda":
				x0=1;
				y0=0;
				xIncremento=-1;
				yIncremento=0;
				angulo=90;
				posicionInicial=1;
				break;
			case "abajo":
				x0=0;
				y0=1;
				xIncremento=0;
				yIncremento=-1;
				angulo=180;
				posicionInicial=1;
				break;
			}
			
			try {
				bufferImage = ImageIO.read(new File(filePath));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			bufferImage = rotateImageByDegrees(bufferImage, angulo);
			for(int i=0;i<barcoAPintar.getTamano();i++) {
				System.out.println("Barco en x: "+barcoAPintar.retornarX(i)+",y: "+barcoAPintar.retornarY(i));
				Image subimagen = bufferImage.getSubimage(bufferImage.getWidth()*x0+(i+posicionInicial)*xIncremento*50, 
						bufferImage.getHeight()*y0+(i+posicionInicial)*yIncremento*50,
						bufferImage.getWidth()+(50-bufferImage.getWidth())*Math.abs(xIncremento),
						bufferImage.getHeight()+(50-bufferImage.getHeight())*Math.abs(yIncremento));
				casillas[barcoAPintar.retornarX(i)][barcoAPintar.retornarY(i)].setIcon(new ImageIcon(subimagen));
			}
		}
	}
	
		
}
