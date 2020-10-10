package batallaNaval;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextArea;
import javax.swing.Timer;

public class TypeWritter {
	private Timer timer;
    private int characterIndex = 0;
    private String input;
    private JTextArea textArea;

    public void type(JTextArea textArea, String input) {
        this.textArea = textArea;
        this.input = input;
        timer = new Timer(60, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (characterIndex < input.length()) {
                    textArea.append(String.valueOf((input.charAt(characterIndex))));
                    characterIndex++;
                } else {
                    stop();
                }
				start();
				
			}
        	
        });
    }
    
   

    public void start() {
        textArea.setText(null);
        characterIndex = 0;
        timer.start();
    }

    public void stop() {
        timer.stop();
    }

}
