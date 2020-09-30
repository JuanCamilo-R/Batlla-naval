/*
 * Jennyfer Belalcazar 		- 1925639-3743
 * Samuel Riascos Prieto 	- 1922540-3743
 * Juan Camilo Randazzo		- 1923948-3743
 */
package batallaNaval;


import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import javafx.scene.shape.Box;
import misComponentes.T�tulo;

public class VistaGUIBatallaNaval extends JFrame {
	//attributes
	
	private JPanel zonaUnidades,zonaCasillas,zonaBotones;
	private JButton[][] casillas= new JButton[10][10];
	private JLabel[][] flota= new JLabel[10][10];
	private JButton limpiar,confirmar;
	private ImageIcon imagen;
	private BufferedImage bufferImage=null;
	private T�tulo t�tulo;
	private JToggleButton battleship, cruiser, destroyer, plane;
	private ButtonGroup unidades;
	
	//methods
	
	public VistaGUIBatallaNaval() {
		initGUI();
		
		this.setTitle("Batalla Naval");
		this.pack();
		this.setLocationRelativeTo(null);
		this.setResizable(true);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void initGUI() {
		// TODO Auto-generated method stub
		this.getContentPane().setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		t�tulo = new T�tulo("�Organiza tu flota!", 30, Color.black);
		constraints.gridx=0;
		constraints.gridy=0;
		constraints.gridwidth=3;
		constraints.fill=GridBagConstraints.HORIZONTAL;
		
		add(t�tulo,constraints);
		
		zonaUnidades = new JPanel();
		zonaUnidades.setLayout(new BoxLayout(zonaUnidades,BoxLayout.Y_AXIS));
		imagen = new ImageIcon("src/imagenes/Battleship.png");
		battleship = new JToggleButton("1");
		battleship.setIcon(imagen);
		battleship.setVerticalTextPosition(SwingConstants.TOP);
		imagen = new ImageIcon("src/imagenes/Cruiser.png");
		cruiser = new JToggleButton("2");
		cruiser.setIcon(imagen);
		cruiser.setVerticalTextPosition(SwingConstants.TOP);
		
		imagen = new ImageIcon("src/imagenes/ShipDestroyerHull.png");
		destroyer = new JToggleButton("3");
		destroyer.setIcon(imagen);
		destroyer.setVerticalTextPosition(SwingConstants.TOP);
		
		imagen = new ImageIcon("src/imagenes/PlaneF-35Lightning2.png");
		plane = new JToggleButton("4");
		plane.setIcon(imagen);
		plane.setVerticalTextPosition(SwingConstants.TOP);
		plane.setMaximumSize(new Dimension(plane.getMaximumSize().width,plane.getMaximumSize().height));
		destroyer.setMaximumSize(new Dimension(plane.getMaximumSize().width,destroyer.getMaximumSize().height));
		cruiser.setMaximumSize(new Dimension(plane.getMaximumSize().width,cruiser.getMaximumSize().height));
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
		
		zonaCasillas = new JPanel();
		zonaCasillas.setLayout(new GridLayout(10,10));
		for (int i=0;i<10;i++) {
			for(int j=0;j<10;j++) {
			casillas[i][j] = new JButton(""+(i*10+j));
			casillas[i][j].setPreferredSize(new Dimension (50,50));
			casillas[i][j].setBackground(Color.blue);
			zonaCasillas.add(casillas[i][j]);
			}
		}
		zonaCasillas.setBorder(new TitledBorder("Casillas"));
		constraints.gridx=1;
		constraints.gridy=1;
		constraints.gridwidth=1;
		constraints.gridheight=1;
		constraints.fill=GridBagConstraints.NONE;
		
		add(zonaCasillas,constraints);
		
		zonaBotones = new JPanel();
		JLabel Logo = new JLabel();
		imagen = new ImageIcon("src/imagenes/Logo.png");
		Logo.setIcon(imagen);
		zonaBotones.add(Logo);
		limpiar = new JButton("Limpiar");
		zonaBotones.add(limpiar);
		confirmar = new JButton("Confirmar");
		zonaBotones.add(confirmar);
		constraints.gridx=1;
		constraints.gridy=2;
		constraints.gridwidth=1;
		constraints.gridheight=1;
		
		add(zonaBotones,constraints);
		
	}
}
