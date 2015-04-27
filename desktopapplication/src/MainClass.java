import javax.swing.*;
import java.awt.*;


public class MainClass {
	public static void main(String[] args){
		//this is the first panel of the program, the entry point
		JFrame frame = new JFrame("Rice Palliative Care Software - Doctor Portal");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Dimension initSize = new Dimension(640,480);
		frame.setPreferredSize(initSize);
		
		//every panel has the content pane as an argument in the constructor
		//this is to ensure than each panel can load the next panel
		LoginPanel loginPanel = new LoginPanel(frame.getContentPane());
		loginPanel.setPreferredSize(initSize);
	
		frame.getContentPane().add(loginPanel);
		frame.setSize(initSize);
	    
		//Display the window.
        frame.pack();
        frame.setVisible(true);
	}
}