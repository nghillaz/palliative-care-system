import java.awt.Container;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;


public class SetDoctorPanel extends JPanel{

	JLabel setDoctor;
	JComboBox selectDoctor;
	
	public SetDoctorPanel(Container contentPane) {
		//setting to box layout
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		//creating the components and setting them up
		//labels and combo box
		setDoctor = new JLabel("Set Doctor:");
		selectDoctor = new JComboBox(settingDoctor);
		setDoctor.setAlignmentX(Component.CENTER_ALIGNMENT);
		selectDoctor.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		//adding componenets to panel
		add(Box.createRigidArea(new Dimension(0,150)));
		add(JLabel);
		add(Box.createRigidArea(new Dimension(0,10)));
		add(JComboBox);
		
	}
	
	//setDoctorPanel Listener
	public class setDoctorPanelListener implements ActionListener{
		Container contentPane;
		public LoginListener(Container contentPane){
			this.contentPane = contentPane;
		}
		
		public void ActionPerformed(ActionEvent e){
			PrintStream console = System.out;
			File f = Database.download("doctors.csv", console);
			
			//downloads file from database
			Database.download("doctors.csv", console);
			
			//TODO
			try{
				
			}
			
		}
	}
}
