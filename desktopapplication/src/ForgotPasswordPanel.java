import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.UnsupportedEncodingException;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class ForgotPasswordPanel extends JPanel{
	JTextField emailField;
	
	public ForgotPasswordPanel(Container contentPane){
		//set up the components
		JLabel emailLabel = new JLabel("Email Address:");
		emailField = new JTextField(20);
		JButton submitButton = new JButton("Submit");
		JButton backButton = new JButton("Back");
		submitButton.addActionListener(new SubmitButtonListener(contentPane));
		backButton.addActionListener(new BackButtonListener(contentPane));
		
		//add the components to the panel
		add(emailLabel);
		add(emailField);
		add(submitButton);
		add(backButton);
	}
	
	public class SubmitButtonListener implements ActionListener{
		Container contentPane;
		public SubmitButtonListener(Container contentPane){
			this.contentPane = contentPane;
		}
		public void actionPerformed(ActionEvent e){
			
	         // TODO finish this (try Yahoo instead of google)
			final String username = "Team22CSE360@gmail.com";
			final String password = "passwordTeam22CSE360";
	 
			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class",
					"javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "465");
			props.put("mail.transport.protocol", "smtp");
	 
			Session session = Session.getDefaultInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});
	 
			try {
	 
				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress(username));
				message.setRecipients(Message.RecipientType.TO,
						InternetAddress.parse(emailField.getText()));
				message.setSubject("Testing Subject");
				message.setText("Dear Mail Crawler," +
						"\n\n No spam to my email, please!");
	 
				Transport.send(message);
	 
				System.out.println("Done");
	 
			} catch (MessagingException me) {
				throw new RuntimeException(me);
			}
			
	        contentPane.removeAll();
			contentPane.add(new LoginPanel(contentPane));
			contentPane.invalidate();
			contentPane.validate();
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