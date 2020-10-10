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

public class VerBarcosCPU extends JFrame {
	private JButton[][] pantalla; 
	private BufferedImage bufferImage; 
	private JButton volver;
	private Escuchas escucha;
	private JPanel zonaPantalla;
	private JPanel zonaBoton;
	private JLabel[] reglaHorizontal;
	private JLabel[] reglaVertical;
	private JFrame pantallaPrincipal;
	private ControlJuego control;
	
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
		for (int i=0;i<11;i++) {
			reglaHorizontal[i] = new JLabel(texto);
			reglaHorizontal[i].setHorizontalAlignment(SwingConstants.CENTER);
			texto=""+i;
			zonaPantalla.add(reglaHorizontal[i]);
		}
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
		//System.out.print("Tamano barco : "+control.darBarcoPorIndice(0).getTamano()+"\n");
		//pintarBarco(control.darBarcoPorIndice(0),"Plane");
		//System.out.print(control.darBarcoPorIndice(0).retornarX(0));
		
		for(int i = 0; i < 10; i++ ) {
			pintarBarco(control.darBarcoPorIndice(i), 
					control.darBarcoPorIndice(i).retornarTipoBarco(control.darBarcoPorIndice(i).getTamano()));
		}
		
	}
	
	
	
	
	private class Escuchas implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			// TODO Auto-generated method stub
			if(event.getSource() == volver) {
				pantallaPrincipal.setVisible(true);
				dispose();
			}
			
		}
		
	}
	
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
				pantalla[barcoAPintar.retornarX(i)][barcoAPintar.retornarY(i)].setIcon(new ImageIcon(subimagen));
			}
		}
		
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
