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
 * 
 */
public class AyudaImagenes extends JFrame {
	
	/** The aceptar. */
	private JButton aceptar;
	
	/** The escucha. */
	private Escuchas escucha;
	
	/** The texto hundido. */
	private JLabel agua,tocado,hundido,textoAgua,textoTocado,textoHundido;
	
	/** The imagen. */
	private ImageIcon imagen;
	
	/**
	 * Instantiates a new ayuda imagenes.
	 */
	public  AyudaImagenes() {

		this.setTitle("Ayuda");
		this.setResizable(false);
		this.setVisible(false);
		initGUI();
		//this.setSize(380,250);//ancho,alto
		pack();
		this.setLocationRelativeTo(null);
	}
	
	/**
	 * Inits the GUI.
	 */
	private void initGUI () {
		
		this.getContentPane().setLayout(new GridBagLayout());
		GridBagConstraints contraints = new GridBagConstraints();
		
		
		imagen = new ImageIcon("src/imagenes/agua.png");//Darle la direccion de la imagen
		agua = new JLabel(imagen);//Puede recibir un objeto de tipo ImageIcon
		agua.setIcon(new ImageIcon(imagen.getImage().getScaledInstance(50, 50,0)));
		contraints.gridx=0;
		contraints.gridy=0;
		contraints.gridwidth=1;
		contraints.gridheight=1;
		add(agua,contraints);
		
		textoAgua = new JLabel("Cuando tocas una casilla donde no habia nada");
		contraints.gridx=1;
		contraints.gridy=0;
		contraints.gridwidth=1;
		contraints.gridheight=1;
		add(textoAgua,contraints);
		
		imagen = new ImageIcon("src/imagenes/tocado2.png");//Darle la direccion de la imagen
		tocado = new JLabel(imagen);//Puede recibir un objeto de tipo ImageIcon
		tocado.setIcon(new ImageIcon(imagen.getImage().getScaledInstance(50, 50,0)));
		contraints.gridx=0;
		contraints.gridy=1;
		contraints.gridwidth=1;
		contraints.gridheight=1;
		add(tocado,contraints);
		
		textoTocado = new JLabel("Cuando tocas una casilla donde hay parte del barco");
		contraints.gridx=1;
		contraints.gridy=1;
		contraints.gridwidth=1;
		contraints.gridheight=1;
		add(textoTocado,contraints);
		
		imagen = new ImageIcon("src/imagenes/hundido2.png");//Darle la direccion de la imagen
		hundido = new JLabel(imagen);//Puede recibir un objeto de tipo ImageIcon
		hundido.setIcon(new ImageIcon(imagen.getImage().getScaledInstance(50, 50,0)));
		contraints.gridx=0;
		contraints.gridy=2;
		contraints.gridwidth=1;
		contraints.gridheight=1;
		add(hundido,contraints);
		
		textoHundido = new JLabel("Hundiste el barco");
		contraints.gridx=1;
		contraints.gridy=2;
		contraints.gridwidth=1;
		contraints.gridheight=1;
		contraints.anchor = GridBagConstraints.WEST;
		add(textoHundido,contraints);

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
				setVisible(false);
			}
		}
		
	}
}
