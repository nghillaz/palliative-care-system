import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class CreateAccountPanel extends JPanel{
	
	public CreateAccountPanel(Container contentPane){
		//set to grid layout
		super(new GridLayout(10,1));
		
		//create the components and set them up
		JTextField usernameField = new JTextField(20);
		usernameField.setText("Username");
		JTextField passwordField = new JTextField(20);
		passwordField.setText("Password");
		JTextField firstNameField = new JTextField(20);
		firstNameField.setText("First Name");
		JTextField lastNameField = new JTextField(20);
		lastNameField.setText("Last Name");
		JTextField emailAddressField = new JTextField(20);
		emailAddressField.setText("Email Address");
		JTextField phoneNumberField = new JTextField(20);
		phoneNumberField.setText("Phone Number");
		JTextField addressField = new JTextField(20);
		addressField.setText("Address");
		
		JButton forgotPasswordButton = new JButton("Forgot Password");
		JButton submitButton = new JButton("Submit");
		JButton backButton = new JButton("Back");
		forgotPasswordButton.addActionListener(new ForgotPasswordListener(contentPane));
		submitButton.addActionListener(new SubmitListener());
		backButton.addActionListener(new BackListener(contentPane));

		//add the components to the panel
		add(usernameField);
		add(passwordField);
		add(firstNameField);
		add(lastNameField);
		add(emailAddressField);
		add(phoneNumberField);
		add(addressField);
		add(submitButton);
		add(forgotPasswordButton);
		add(backButton);
	}
	
	public class SubmitListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			//needs finishing!!
			//create the account and put it in the database
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
	
	public class BackListener implements ActionListener{
		Container contentPane;
		public BackListener(Container contentPane){
			this.contentPane = contentPane;
		}
		public void actionPerformed(ActionEvent e){
			contentPane.removeAll();
			contentPane.add(new LoginPanel(contentPane));
			contentPane.invalidate();
			contentPane.validate();
		}
	}
}
