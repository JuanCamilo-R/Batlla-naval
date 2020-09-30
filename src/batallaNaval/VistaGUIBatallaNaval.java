/*
 * Jennyfer Belalcazar 		- 1925639-3743
 * Samuel Riascos Prieto 	- 1922540-3743
 * Juan Camilo Randazzo		- 1923948-3743
 */
package batallaNaval;


import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.geom.AffineTransform;
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
import misComponentes.Título;

public class VistaGUIBatallaNaval extends JFrame {
	//attributes
	
	private JPanel zonaUnidades,zonaCasillas,zonaLogo,zonaBotones;
	private JButton[][] casillas= new JButton[10][10];
	private JLabel[] reglaHorizontal, reglaVertical;
	private JLabel[][] flota= new JLabel[10][10];
	private JButton limpiar,confirmar,salir;
	private ImageIcon imagen;
	private BufferedImage bufferImage=null;
	private Título título;
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
		título = new Título("¡Organiza tu flota!", 30, Color.black);
		constraints.gridx=0;
		constraints.gridy=0;
		constraints.gridwidth=3;
		constraints.fill=GridBagConstraints.HORIZONTAL;
		
		add(título,constraints);
		
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
		zonaBotones.setPreferredSize(new Dimension(100,100));
		confirmar = new JButton("Confirmar");
		confirmar.setPreferredSize(new Dimension(100,30));
		zonaBotones.add(confirmar);
		limpiar = new JButton("Limpiar");
		limpiar.setPreferredSize(new Dimension(100,30));
		zonaBotones.add(limpiar);
		salir = new JButton("Salir");
		salir.setPreferredSize(new Dimension(100,30));
		zonaBotones.add(salir);
		constraints.gridx=2;
		constraints.gridy=2;
		constraints.gridwidth=1;
		constraints.gridheight=1;
		constraints.fill=GridBagConstraints.VERTICAL;
		add(zonaBotones,constraints);
	}
	
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
}
