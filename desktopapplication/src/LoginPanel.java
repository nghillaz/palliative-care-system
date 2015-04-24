import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class LoginPanel extends JPanel{
	
	public LoginPanel(Container contentPane){
		//set to grid layout
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		//create the components and set them up
		JLabel usernameLabel = new JLabel("Username:");
		JTextField usernameField = new JTextField(20);
		JLabel passwordLabel = new JLabel("Password:");
		JTextField passwordField = new JTextField(20);
		JButton loginButton = new JButton("Login");		
		JButton forgotPasswordButton = new JButton("Forgot Password");
		JButton createAccountButton = new JButton("Create Account");
		forgotPasswordButton.addActionListener(new ForgotPasswordListener(contentPane));
		loginButton.addActionListener(new LoginListener(contentPane));
		createAccountButton.addActionListener(new CreateAccountListener(contentPane));
		usernameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		usernameField.setAlignmentX(Component.CENTER_ALIGNMENT);
		passwordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);
		loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		forgotPasswordButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		createAccountButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		usernameField.setMaximumSize(new Dimension(200, 30));
		passwordField.setMaximumSize(new Dimension(200, 30));
		
		//add the components to the panel
		add(usernameLabel);
		add(usernameField);
		add(passwordLabel);
		add(passwordField);
		add(loginButton);
		add(forgotPasswordButton);
		add(createAccountButton);
	}
	
	public class LoginListener implements ActionListener{
		Container contentPane;
		public LoginListener(Container contentPane){
			this.contentPane = contentPane;
		}
		public void actionPerformed(ActionEvent e){
			//needs finishing!!
			//check if they're in the database then log them in
			contentPane.removeAll();
			contentPane.add(new MainMenuPanel(contentPane));
			contentPane.invalidate();
			contentPane.validate();
		}
	}
	
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