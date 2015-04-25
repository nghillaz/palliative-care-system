import javax.swing.*;
import java.awt.*;


public class MainClass {
	public static void main(String[] args){
		createAndShowGUI();
	}
	private static void createAndShowGUI(){
	 	
		JFrame frame = new JFrame("Rice Palliative Care Software - Doctor Portal");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Dimension initSize = new Dimension(640,480);
		frame.setPreferredSize(initSize);
		
		LoginPanel loginPanel = new LoginPanel(frame.getContentPane());
		loginPanel.setPreferredSize(initSize);
	
		frame.getContentPane().add(loginPanel);
		frame.setSize(initSize);
	    
		//Display the window.
        frame.pack();
        frame.setVisible(true);
	}
}