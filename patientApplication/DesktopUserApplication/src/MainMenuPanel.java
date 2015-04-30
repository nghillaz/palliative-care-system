import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class MainMenuPanel extends JPanel{
	
	public MainMenuPanel(Container contentPane){
			JButton enterSymptomsButton;		
			JButton editDetailsButton;
			JButton setDoctorButton;
			JButton logOutButton;
			
			//set to box layout
			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			
			//create the components and set them up
			//all of the buttons
			enterSymptomsButton = new JButton("Enter Symptoms/Pain Level");
			editDetailsButton = new JButton("Edit Details");
			setDoctorButton = new JButton("Set Doctor");
			logOutButton = new JButton("Log Out");
				
			//button listeners
			enterSymptomsButton.addActionListener(new EnterSymptomsListener(contentPane));		
			editDetailsButton.addActionListener(new EditDetailsListener(contentPane));
			setDoctorButton.addActionListener(new setDoctorListener(contentPane));
			logOutButton.addActionListener(new logOutListener(contentPane));
				
			//setting the position of the buttons
			enterSymptomsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
			editDetailsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
			setDoctorButton.setAlignmentX(Component.CENTER_ALIGNMENT);
			logOutButton.setAlignmentX(Component.CENTER_ALIGNMENT);
				
				
			//add the components to the panel
			add(Box.createRigidArea(new Dimension(0,150)));
			add(enterSymptomsButton);
			add(Box.createRigidArea(new Dimension(0,10)));
			add(editDetailsButton);
			add(Box.createRigidArea(new Dimension(0,10)));
			add(setDoctorButton);
			add(Box.createRigidArea(new Dimension(0,100)));
			add(logOutButton);
		}
	
	

	
	//implementing action listeners for each button
	public class EnterSymptomsListener implements ActionListener{
		Container contentPane;
		public EnterSymptomsListener(Container contentPane){
			this.contentPane = contentPane;
		}
	
		public void actionPerformed(ActionEvent arg0) {
			// TODO
			contentPane.removeAll();
			contentPane.add(new EnterSymptomsPanel(contentPane));
			contentPane.invalidate();
			contentPane.validate();
			
		}
		
	}
	
	public class EditDetailsListener implements ActionListener{
		Container contentPane;
		public EditDetailsListener(Container contentPane){
			this.contentPane = contentPane;
		}
	
		public void actionPerformed(ActionEvent arg0) {
			// TODO
			contentPane.removeAll();
			contentPane.add(new EditPersonalDetailsPanel(contentPane));
			contentPane.invalidate();
			contentPane.validate();
			
		}
		
	}
	
	public class setDoctorListener implements ActionListener{
		Container contentPane;
		public setDoctorListener(Container contentPane){
			this.contentPane = contentPane;
		}
	
		public void actionPerformed(ActionEvent arg0) {
			contentPane.removeAll();
			contentPane.add(new SetDoctorPanel(contentPane));
			contentPane.invalidate();
			contentPane.validate();
			
		}
		
	}
	
	public class logOutListener implements ActionListener{
		Container contentPane;
		public logOutListener(Container contentPane){
			this.contentPane = contentPane;
		}
		public void actionPerformed(ActionEvent arg0) {
			contentPane.removeAll();
			contentPane.add(new LoginPanel(contentPane));
			contentPane.invalidate();
			contentPane.validate();
			
		}
		
	}		
}
