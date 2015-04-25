import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CreateAccountPanel extends JPanel{
	
	public CreateAccountPanel(Container contentPane){
		//set to box layout
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		//create the components and set them up
		JLabel firstNameLabel = new JLabel("First Name:");
		JLabel lastNameLabel = new JLabel("Last Name:");
		JLabel emailLabel = new JLabel("Email:");
		JLabel passwordLabel = new JLabel("Password:");
		JLabel cPasswordLabel = new JLabel("Confirm Password:");
		JLabel phoneNumberLabel = new JLabel("Phone number:");
		JTextField firstNameField = new JTextField(20);
		JTextField lastNameField = new JTextField(20);
		JTextField emailField = new JTextField(20);
		JPasswordField passwordField = new JPasswordField(20);
		JPasswordField cPasswordField = new JPasswordField(20);
		JTextField phoneNumberField = new JTextField(20);
		JButton createAccountButton = new JButton("Create Account");
		JButton backButton = new JButton("Back");
		firstNameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		lastNameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		emailLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		passwordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		cPasswordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		phoneNumberLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		firstNameField.setAlignmentX(Component.CENTER_ALIGNMENT);
		lastNameField.setAlignmentX(Component.CENTER_ALIGNMENT);
		emailField.setAlignmentX(Component.CENTER_ALIGNMENT);
		passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);
		cPasswordField.setAlignmentX(Component.CENTER_ALIGNMENT);
		phoneNumberField.setAlignmentX(Component.CENTER_ALIGNMENT);
		createAccountButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		firstNameField.setMaximumSize(new Dimension(200, 30));
		lastNameField.setMaximumSize(new Dimension(200, 30));
		emailField.setMaximumSize(new Dimension(200, 30));
		passwordField.setMaximumSize(new Dimension(200, 30));
		cPasswordField.setMaximumSize(new Dimension(200, 30));
		phoneNumberField.setMaximumSize(new Dimension(200, 30));
		createAccountButton.addActionListener(new SubmitListener(contentPane));
		backButton.addActionListener(new BackListener(contentPane));
		
		//add the components to the panel
		add(Box.createRigidArea(new Dimension(0,10)));
		add(firstNameLabel);
		add(Box.createRigidArea(new Dimension(0,2)));
		add(firstNameField);
		add(Box.createRigidArea(new Dimension(0,2)));
		add(lastNameLabel);
		add(Box.createRigidArea(new Dimension(0,2)));
		add(lastNameField);
		add(Box.createRigidArea(new Dimension(0,2)));
		add(emailLabel);
		add(Box.createRigidArea(new Dimension(0,2)));
		add(emailField);
		add(Box.createRigidArea(new Dimension(0,2)));
		add(passwordLabel);
		add(Box.createRigidArea(new Dimension(0,2)));
		add(passwordField);
		add(Box.createRigidArea(new Dimension(0,2)));
		add(cPasswordLabel);
		add(Box.createRigidArea(new Dimension(0,2)));
		add(cPasswordField);
		add(Box.createRigidArea(new Dimension(0,2)));
		add(phoneNumberLabel);
		add(Box.createRigidArea(new Dimension(0,2)));
		add(phoneNumberField);
		add(Box.createRigidArea(new Dimension(0,2)));
		add(createAccountButton);
		add(Box.createRigidArea(new Dimension(0,2)));
		add(backButton);
		
	}
	
	public class SubmitListener implements ActionListener{
		Container contentPane;
		public SubmitListener(Container contentPane){
			this.contentPane = contentPane;
		}
		public void actionPerformed(ActionEvent e){
			//needs finishing!!
			//create the account and put it in the database
			contentPane.removeAll();
			contentPane.add(new LoginPanel(contentPane));
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