/*
 * Jennyfer Belalcazar 		- 1925639-3743
 * Samuel Riascos Prieto 	- 1922540-3743
 * Juan Camilo Randazzo		- 1923948-3743
 */
package batallaNaval;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

// TODO: Auto-generated Javadoc
/**
 * The Class VerBarcosCPU.
 */
public class VerBarcosCPU extends JFrame {
	
	/** The pantalla. */
	private JButton[][] pantalla; 
	
	/** The buffer image. */
	private BufferedImage bufferImage; 
	
	/** The volver. */
	private JButton volver;
	
	/** The escucha. */
	private Escuchas escucha;
	
	/** The zona pantalla. */
	private JPanel zonaPantalla;
	
	/** The zona boton. */
	private JPanel zonaBoton;
	
	/** The regla horizontal. */
	private JLabel[] reglaHorizontal;
	
	/** The regla vertical. */
	private JLabel[] reglaVertical;
	
	/** The pantalla principal. */
	private JFrame pantallaPrincipal;
	
	/** The control. */
	private ControlJuego control;
	
	/**
	 * Instantiates a new ver barcos CPU.
	 *
	 * @param pantallaPrincipal the pantalla principal
	 * @param control the control
	 */
	public  VerBarcosCPU(JFrame pantallaPrincipal, ControlJuego control) {
		this.pantallaPrincipal = pantallaPrincipal;
		this.control = control;
		this.setTitle("Barcos CPU");
		this.setResizable(false);
		this.setVisible(false);
		initGUI();
		this.pack();
		this.setLocationRelativeTo(null);
	}
	
	/**
	 * Inits the GUI.
	 */
	private void initGUI () {
		
		this.getContentPane().setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		pantalla = new JButton[10][10];
		volver = new JButton("Volver");
		escucha = new Escuchas();
		volver.addActionListener(escucha);
		zonaPantalla = new JPanel();
		zonaBoton = new JPanel();
		
		zonaPantalla = new JPanel(new GridLayout(11,11));
		reglaHorizontal = new JLabel[11];
		reglaVertical = new JLabel[10];
		String texto = "[Y,X]";
		//For que asigna el texto de la regla horizontal
		for (int i=0;i<11;i++) {
			reglaHorizontal[i] = new JLabel(texto);
			reglaHorizontal[i].setHorizontalAlignment(SwingConstants.CENTER);
			texto=""+i;
			zonaPantalla.add(reglaHorizontal[i]);
		}
		//For que asigna el texto de la regla vertical
		for (int i=0;i<10;i++) {
			for(int j=0;j<11;j++) {
				if(j==0) {
					reglaVertical[j] = new JLabel(""+i);
					reglaVertical[j].setHorizontalAlignment(SwingConstants.CENTER);
					zonaPantalla.add(reglaVertical[j]);
				}
				else {
				pantalla[i][j-1] = new JButton();
				pantalla[i][j-1].setPreferredSize(new Dimension (50,50));
				pantalla[i][j-1].setBackground(Color.blue);
				zonaPantalla.add(pantalla[i][j-1]);
				}
			}
		}
		constraints.gridx=0;
		constraints.gridy=0;
		constraints.gridwidth=1;
		constraints.gridheight=1;
		constraints.fill=GridBagConstraints.NONE;
		add(zonaPantalla,constraints);
		
		constraints.gridx=0;
		constraints.gridy=1;
		constraints.gridwidth=1;
		constraints.gridheight=1;
		constraints.fill=GridBagConstraints.NONE;
		zonaBoton.add(volver);
		add(zonaBoton,constraints);

		//For que pinta los barcos enemigos.
		for(int i = 0; i < 10; i++ ) {
			pintarBarco(control.darBarcoPorIndice(i), 
					control.darBarcoPorIndice(i).retornarTipoBarco(control.darBarcoPorIndice(i).getTamano()));
		}
		
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
			if(event.getSource() == volver) {
				pantallaPrincipal.setVisible(true);
				dispose();
			}
			
		}
		
	}
	public void cerrar() {
		System.exit(0);
	}
	/**
	 * Pintar barco.
	 *
	 * @param barcoAPintar the barco A pintar
	 * @param tipoBarcoEscoger the tipo barco escoger
	 */
	private void pintarBarco(Barcos barcoAPintar, String tipoBarcoEscoger) {
		// TODO Auto-generated method stub
		String filePath="src/imagenes/"+tipoBarcoEscoger+".png";
		if(barcoAPintar.getTamano()==1) {
			pantalla[barcoAPintar.retornarX(0)][barcoAPintar.retornarY(0)].setIcon(new ImageIcon(filePath));
		}else {
			//Angulo de rotacion
			
			int angulo=0;
			
			//Inicializa las variables para el getSubimage();
			
			int x0=0;
			int y0=0;
			int xIncremento=0;
			int yIncremento=1;
			int posicionInicial=0;
			
			//Generaliza las variables anteriores dependiendo de la direccion del barco
			
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
				Image subimagen = bufferImage.getSubimage(bufferImage.getWidth()*x0+(i+posicionInicial)*xIncremento*50, 
						bufferImage.getHeight()*y0+(i+posicionInicial)*yIncremento*50,
						bufferImage.getWidth()+(50-bufferImage.getWidth())*Math.abs(xIncremento),
						bufferImage.getHeight()+(50-bufferImage.getHeight())*Math.abs(yIncremento));
				pantalla[barcoAPintar.retornarX(i)][barcoAPintar.retornarY(i)].setIcon(new ImageIcon(subimagen));
			}
		}
		
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
	
}

