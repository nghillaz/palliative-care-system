import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ForgotPasswordPanel extends JPanel{
	public ForgotPasswordPanel(Container contentPane){
		//set up the components
		JLabel emailLabel = new JLabel("Email Address:");
		JTextField emailField = new JTextField(20);
		JButton submitButton = new JButton("Submit");
		JButton backButton = new JButton("Back");
		submitButton.addActionListener(new SubmitButtonListener());
		backButton.addActionListener(new BackButtonListener(contentPane));
		
		//add the components to the panel
		add(emailLabel);
		add(emailField);
		add(submitButton);
		add(backButton);
	}
	
	public class SubmitButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			//needs finishing!!
			//send them an email with their password and username
		}
	}
	
	public class BackButtonListener implements ActionListener{
		Container contentPane;
		public BackButtonListener(Container contentPane){
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