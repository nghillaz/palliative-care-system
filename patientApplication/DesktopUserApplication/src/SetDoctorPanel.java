import java.awt.*;

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
	JButton setAsDoctor;
	JButton back;
	
	String [] settingDoctor = {""};
	
	public SetDoctorPanel(Container contentPane) {
		//setting to box layout
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		//creating the components and setting them up
		//labels and combo box
		setDoctor = new JLabel("Set Doctor:");
		selectDoctor = new JComboBox(settingDoctor);
		setAsDoctor = new JButton("Set as Doctor");
		back = new JButton("Back");
		
		//button listeners
		setAsDoctor.addActionListener(new SetAsDoctorListener(contentPane));
		back.addActionListener(new BackListener(contentPane));
		
		//setting alignment
		setDoctor.setAlignmentX(Component.CENTER_ALIGNMENT);
		selectDoctor.setAlignmentX(Component.CENTER_ALIGNMENT);
		setAsDoctor.setAlignmentX(Component.CENTER_ALIGNMENT);
		back.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		add(Box.createRigidArea(new Dimension(0,150)));
		add(setDoctor);
		contentPane.add(selectDoctor);
		add(Box.createRigidArea(new Dimension(0,10)));
		add(selectDoctor);
		add(Box.createRigidArea(new Dimension(0,0)));
		add(setAsDoctor);
		add(Box.createRigidArea(new Dimension(0,10)));
		add(back);
	}
	
	//setDoctorPanel Listener
	/*public class setDoctorPanelListener implements ActionListener{
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
	}*/
	
	
	
	public class SetAsDoctorListener implements ActionListener{
		Container contentPane;
		public SetAsDoctorListener(Container contenetPane){
			this.contentPane = contentPane;
		}
		public void actionPerformed(ActionEvent arg0){
			contentPane.removeAll();
			
			//TODO Add functionality of button
			
			
			
			contentPane.invalidate();
			contentPane.validate();
		}
	}
	
	
	public class BackListener implements ActionListener{
		Container contentPane;
		public BackListener(Container contentPane){
			this.contentPane = contentPane;
		}
	
		public void actionPerformed(ActionEvent arg0) {
			// TODO
			contentPane.removeAll();
			contentPane.add(new MainMenuPanel(contentPane));
			contentPane.invalidate();
			contentPane.validate();
			
		}
	}
	
	
}
