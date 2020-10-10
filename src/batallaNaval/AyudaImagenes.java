/*
 * Jennyfer Belalcazar 		- 1925639-3743
 * Samuel Riascos Prieto 	- 1922540-3743
 * Juan Camilo Randazzo		- 1923948-3743
 */
package batallaNaval;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

// TODO: Auto-generated Javadoc
/**
 * The Class AyudaImagenes.
 * Clase creada para explicarle al usuario el significado de las imagenes que salen al
 * dar click en una casilla
 * 
 */
public class AyudaImagenes extends JFrame {
	
	/** The aceptar.
	 *  Boton para salirse de la ventana de ayuda
	 *  */
	private JButton aceptar;
	
	/** The escucha. 
	 * Escucha del boton
	 * */
	private Escuchas escucha;
	
	/** JLabels para poner las imagenes y su significado  * */
	private JLabel agua,tocado,hundido,textoAgua,textoTocado,textoHundido;
	
	/** The imagen.
	 * Para ponerle imagenes a los JLabels que lo requieran
	 *  */
	private ImageIcon imagen;
	
	/**
	 * Instantiates a new ayuda imagenes.
	 */
	public  AyudaImagenes() {
		//Constructor
		this.setTitle("Ayuda");
		this.setResizable(false);
		this.setVisible(false);
		initGUI();
		pack();
		this.setLocationRelativeTo(null);
	}
	
	/**
	 * Inits the GUI.
	 */
	private void initGUI () {
		//Contenedor con Layout GridBagLayout
		this.getContentPane().setLayout(new GridBagLayout());
		GridBagConstraints contraints = new GridBagConstraints();
		
		//Ponemos la imagen de agua
		imagen = new ImageIcon("src/imagenes/agua.png");//Darle la direccion de la imagen
		agua = new JLabel(imagen);//Puede recibir un objeto de tipo ImageIcon
		agua.setIcon(new ImageIcon(imagen.getImage().getScaledInstance(50, 50,0)));
		contraints.gridx=0;
		contraints.gridy=0;
		contraints.gridwidth=1;
		contraints.gridheight=1;
		add(agua,contraints);
		
		//Texto de significado imagen de agua
		textoAgua = new JLabel("Cuando tocas una casilla donde no habia nada");
		contraints.gridx=1;
		contraints.gridy=0;
		contraints.gridwidth=1;
		contraints.gridheight=1;
		add(textoAgua,contraints);
		
		//Ponemos la imagen de tocado
		imagen = new ImageIcon("src/imagenes/tocado2.png");//Darle la direccion de la imagen
		tocado = new JLabel(imagen);//Puede recibir un objeto de tipo ImageIcon
		tocado.setIcon(new ImageIcon(imagen.getImage().getScaledInstance(50, 50,0)));
		contraints.gridx=0;
		contraints.gridy=1;
		contraints.gridwidth=1;
		contraints.gridheight=1;
		add(tocado,contraints);
		
		//Texto de significado imagen de tocado
		textoTocado = new JLabel("Cuando tocas una casilla donde hay parte del barco");
		contraints.gridx=1;
		contraints.gridy=1;
		contraints.gridwidth=1;
		contraints.gridheight=1;
		add(textoTocado,contraints);
		
		//Ponemos la imagen de hundido
		imagen = new ImageIcon("src/imagenes/hundido2.png");//Darle la direccion de la imagen
		hundido = new JLabel(imagen);//Puede recibir un objeto de tipo ImageIcon
		hundido.setIcon(new ImageIcon(imagen.getImage().getScaledInstance(50, 50,0)));
		contraints.gridx=0;
		contraints.gridy=2;
		contraints.gridwidth=1;
		contraints.gridheight=1;
		add(hundido,contraints);
		
		//Texto de significado imagen de hundido
		textoHundido = new JLabel("Hundiste el barco");
		contraints.gridx=1;
		contraints.gridy=2;
		contraints.gridwidth=1;
		contraints.gridheight=1;
		contraints.anchor = GridBagConstraints.WEST;
		add(textoHundido,contraints);

		//Agregamos el boton aceptar y le ponemos la escucha
		escucha = new Escuchas();
		aceptar = new JButton("Aceptar");
		aceptar.addActionListener(escucha);
		contraints.gridx=0;
		contraints.gridy=3;
		contraints.gridwidth=2;
		contraints.gridheight=1;
		contraints.fill= GridBagConstraints.CENTER;
		contraints.anchor = GridBagConstraints.CENTER;
		add(aceptar,contraints);
		
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
			if(event.getSource() == aceptar) {
				//Si presiono aceptar me pongo no visible y sigo  viendo el juego
				setVisible(false);
			}
		}
		
	}
}
