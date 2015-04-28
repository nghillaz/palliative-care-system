import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.*;

import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

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
		
		AWSCredentials credentials = new BasicAWSCredentials(
				"AKIAJ6ESZAJPDCWD4MOA", 
				"SK1p8jgrSA4t6TlpOgXrX4IW9cVJRjCWSOIu901t");
		String bucketName			= "rpcareapp";
		String keyName				= "doctors.csv";
		AmazonS3 s3Client = new AmazonS3Client(credentials);
		
		//add the components to the panel
		add(emailLabel);
		add(emailField);
		add(submitButton);
		add(backButton);
	}
	
	//the listener for the submit button
	public class SubmitButtonListener implements ActionListener{
		Container contentPane;
		public SubmitButtonListener(Container contentPane){
			this.contentPane = contentPane;
		}
		String passwordb;
		public void actionPerformed(ActionEvent e){
			
			//get the list of doctors
			PrintStream console = System.out;
			Database.download("patients.csv", console);
			
			try {
				Scanner scanner = new Scanner(new File("patients.csv"));
				scanner.useDelimiter(",");
				
				while(scanner.hasNext())
				{
					if((scanner.next().equals(emailField.getText())))
						passwordb = scanner.next().toString();
				}
				
				scanner.close();
				System.out.println("Scanner closed.");
			
			//if the file can't be found
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			
			//if the password doesn't exist
			if(passwordb == null)
			{
				JFrame frame = new JFrame();
				JOptionPane.showMessageDialog(frame, "Email not found in the database.");
			}
			else
			{
				//send the email to the user
				final String username = "Team22CSE360@gmail.com";
				final String password = "passwordTeam22CSE360";
		 
				Properties props = new Properties();
			    props.put("mail.smtp.auth", "true");
			    props.put("mail.smtp.starttls.enable", "true");
			    props.put("mail.smtp.host", "smtp.gmail.com");
			    props.put("mail.smtp.port", "587");
		 
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
					message.setSubject("RPCS Email Recovery");
					message.setText("Your password is " + passwordb + ".");
		 
					Transport.send(message);
					
					JFrame frame = new JFrame();
					JOptionPane.showMessageDialog(frame, "Email Sent.");
		 
				} catch (MessagingException me) {
					throw new RuntimeException(me);
				}
				
				//load the login panel
		        contentPane.removeAll();
				contentPane.add(new LoginPanel(contentPane));
				contentPane.invalidate();
				contentPane.validate();
			}
		}
	}
	
	//listener for the back button
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