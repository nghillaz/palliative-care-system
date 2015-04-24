import javax.swing.*;
import java.awt.*;


public class Main extends JApplet{
	public void init(){
		Dimension initSize = new Dimension(640,480);
		
		LoginPanel loginPanel = new LoginPanel(getContentPane());
		loginPanel.setPreferredSize(initSize);
		
		getContentPane().add(loginPanel);
		setSize(initSize);
	}
}
