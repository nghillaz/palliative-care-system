import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class LoginPanel extends JPanel{

	JLabel emailLabel;
	JTextField emailField;
	JLabel passwordLabel;
	JTextField passwordField;
	JButton loginButton;		
	JButton forgotPasswordButton;
	JButton createAccountButton;
	
	public LoginPanel(Container contentPane){
		//set to box layout
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		//create the components and set them up
		
		//icon component
		JLabel RPCSIconLabel = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/RPCSIcon.jpg")).getImage();
		RPCSIconLabel.setIcon(new ImageIcon(img));
		
		//all of the labels and fields
		emailLabel = new JLabel("Email:");
		emailField = new JTextField(20);
		passwordLabel = new JLabel("Password:");
		passwordField = new JPasswordField(20);
		loginButton = new JButton("Login");		
		forgotPasswordButton = new JButton("Forgot Password");
		createAccountButton = new JButton("Create Account");
		forgotPasswordButton.addActionListener(new ForgotPasswordListener(contentPane));
		loginButton.addActionListener(new LoginListener(contentPane));
		createAccountButton.addActionListener(new CreateAccountListener(contentPane));
		RPCSIconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		emailLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		emailField.setAlignmentX(Component.CENTER_ALIGNMENT);
		passwordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);
		loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		forgotPasswordButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		createAccountButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		emailField.setMaximumSize(new Dimension(200, 30));
		passwordField.setMaximumSize(new Dimension(200, 30));
		
		//add the components to the panel
		add(Box.createRigidArea(new Dimension(0,10)));
		add(RPCSIconLabel);
		add(Box.createRigidArea(new Dimension(0,10)));
		add(emailLabel);
		add(Box.createRigidArea(new Dimension(0,5)));
		add(emailField);
		add(Box.createRigidArea(new Dimension(0,2)));
		add(passwordLabel);
		add(Box.createRigidArea(new Dimension(0,5)));
		add(passwordField);
		add(Box.createRigidArea(new Dimension(0,10)));
		add(loginButton);
		add(Box.createRigidArea(new Dimension(0,5)));
		add(forgotPasswordButton);
		add(Box.createRigidArea(new Dimension(0,5)));
		add(createAccountButton);
	}
	
	public class LoginListener implements ActionListener{
		Container contentPane;
		public LoginListener(Container contentPane){
			this.contentPane = contentPane;
		}
		public void actionPerformed(ActionEvent e){
			
			//right here, we use the database class to get the list of doctors
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
	}
}