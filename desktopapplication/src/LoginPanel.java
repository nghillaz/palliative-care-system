import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
//TODO comment code
public class LoginPanel extends JPanel{
	
	public LoginPanel(Container contentPane){
		//set to box layout
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		//create the components and set them up
		
		//icon component
		JLabel RPCSIconLabel = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/RPCSIcon.jpg")).getImage();
		RPCSIconLabel.setIcon(new ImageIcon(img));
		
		JLabel emailLabel = new JLabel("Email:");
		JTextField emailField = new JTextField(20);
		JLabel passwordLabel = new JLabel("Password:");
		JPasswordField passwordField = new JPasswordField(20);
		JButton loginButton = new JButton("Login");		
		JButton forgotPasswordButton = new JButton("Forgot Password");
		JButton createAccountButton = new JButton("Create Account");
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
			// TODO check if they're in the database then go to MainMenuPanel
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