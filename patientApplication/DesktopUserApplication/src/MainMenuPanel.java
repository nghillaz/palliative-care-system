import javax.swing.*;

		import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

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
			enterSymptomsButton = new JButton("Enter Symptoms/Pain Level");						editDetailsButton = new JButton("Edit Details");
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
			// TODO
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
			// TODO
			contentPane.removeAll();
			contentPane.add(new LoginPanel(contentPane));
			contentPane.invalidate();
			contentPane.validate();
			
		}
		
	}
	
	
	
	
			/*
			public class LoginListener implements ActionListener{
				Container contentPane;
				public LoginListener(Container contentPane){
					this.contentPane = contentPane;
				}
				public void actionPerformed(ActionEvent e){
					
					//right here, we use the database class to get the list of patients
					PrintStream console = System.out;
					File f = Database.download("patients.csv", console);
					
					Database.download("patients.csv", console);
							
					try {
						boolean found = false;
						Scanner scanner = new Scanner(f);
						scanner.useDelimiter("\n");
						int lineNumber = 0;
						System.out.println("text: " + emailField.getText());
						//check to see if the email and password are valid
						if(emailField.getText().length() < 4 || passwordField.getText().length() < 4){
							JFrame frame = new JFrame();
							JOptionPane.showMessageDialog(frame, "Account not found in database");
							scanner.close();
							return;
						}
						//search for the email and password in the account
						while(scanner.hasNext())
						{
							String temp = scanner.next().toLowerCase();
							if(temp.contains(emailField.getText().toLowerCase())
									&& temp.contains(passwordField.getText().toLowerCase())){
								found = true;
								break;
							}
						}
						//the account could not be found
						if(!found){
							JFrame frame = new JFrame();
							JOptionPane.showMessageDialog(frame, "Account not found in the database.");
							scanner.close();
							return;
						}
						//the account could be found
						scanner.close();
						contentPane.removeAll();
						contentPane.add(new MainMenuPanel(contentPane));
						contentPane.invalidate();
						contentPane.validate();
					}
					catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
			
			//listener on the button to recover password
			public class ForgotPasswordListener implements ActionListener{
				Container contentPane;
				public ForgotPasswordListener(Container contentPane){
					this.contentPane = contentPane;
				}
				public void actionPerformed(ActionEvent e){
					contentPane.removeAll();
					contentPane.add(new ForgotPasswordPanel(contentPane));
					contentPane.invalidate();
					contentPane.validate();
				}
			}
			
			//listener for the button to create an account
			public class CreateAccountListener implements ActionListener{
				Container contentPane;
				public CreateAccountListener(Container contentPane){
					this.contentPane = contentPane;
				}
				public void actionPerformed(ActionEvent e){
					contentPane.removeAll();
					contentPane.add(new CreateAccountPanel(contentPane));
					contentPane.invalidate();
					contentPane.validate();
				}
			}*/
		
		
		
	}
