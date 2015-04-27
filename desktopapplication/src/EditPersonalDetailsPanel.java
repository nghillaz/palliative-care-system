import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;
import java.util.Scanner;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

public class EditPersonalDetailsPanel extends JPanel{
	// TODO Make sure they enter all fields
	// TODO jeffdmoore@live.com was not found in the database but nghillaz@gmail.com was found
	// TODO Since the doctor is logged in already, they shouldn't have to enter their email address again
	
	JTextField firstNameField;
	JTextField lastNameField;
	JTextField emailField;
	JTextField passwordField;
	JTextField cPasswordField;
	JTextField phoneNumberField;
	JRadioButton doctorRButton;
	JRadioButton nurseRButton;
		
	AWSCredentials credentials = new BasicAWSCredentials(
			"AKIAJ6ESZAJPDCWD4MOA", 
			"SK1p8jgrSA4t6TlpOgXrX4IW9cVJRjCWSOIu901t");
	String bucketName			= "rpcareapp";
	String keyName				= "doctors.csv";
	AmazonS3 s3Client = new AmazonS3Client(credentials);
		
	final ArrayList<JTextField> textFields = new ArrayList<>();
		
	public boolean anyFieldsEmpty() {
		textFields.add(firstNameField);
		textFields.add(lastNameField);
		textFields.add(emailField);
		textFields.add(passwordField);
		textFields.add(cPasswordField);
		textFields.add(phoneNumberField);
			
	    for (JTextField textbox : textFields) {
	        if (textbox.getText().trim().isEmpty() ) {
	            return true;
	        }
	    }
	    return false;
	}
		
	public EditPersonalDetailsPanel(Container contentPane){
			//set to box layout
			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			
			//create the components
			JLabel firstNameLabel = new JLabel("First Name:");
			JLabel lastNameLabel = new JLabel("Last Name:");
			JLabel emailLabel = new JLabel("Email:");
			JLabel passwordLabel = new JLabel("Password:");
			JLabel cPasswordLabel = new JLabel("Confirm Password:");
			JLabel phoneNumberLabel = new JLabel("Phone number:");
			firstNameField = new JTextField(20);
			lastNameField = new JTextField(20);
			emailField = new JTextField(20);
			emailField.setText("Account lookup is through Email");
			passwordField = new JPasswordField(20);
			cPasswordField = new JPasswordField(20);
			phoneNumberField = new JTextField(20);
			JButton createAccountButton = new JButton("Update Details");
			JButton backButton = new JButton("Back");
			doctorRButton = new JRadioButton("Doctor");
			nurseRButton = new JRadioButton("Nurse");
			
			//Group radio buttons
			ButtonGroup group = new ButtonGroup();
			group.add(doctorRButton);
			group.add(nurseRButton);
			
			//Align
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
			doctorRButton.setAlignmentX(Component.CENTER_ALIGNMENT);
			nurseRButton.setAlignmentX(Component.CENTER_ALIGNMENT);
			
			//set Max Size
			firstNameField.setMaximumSize(new Dimension(200, 30));
			lastNameField.setMaximumSize(new Dimension(200, 30));
			emailField.setMaximumSize(new Dimension(200, 30));
			passwordField.setMaximumSize(new Dimension(200, 30));
			cPasswordField.setMaximumSize(new Dimension(200, 30));
			phoneNumberField.setMaximumSize(new Dimension(200, 30));
			createAccountButton.addActionListener(new SubmitListener(contentPane));
			backButton.addActionListener(new BackListener(contentPane));
			
			//add the components to the panel
			add(Box.createRigidArea(new Dimension(0,2)));
			add(emailLabel);
			add(Box.createRigidArea(new Dimension(0,2)));
			add(emailField);
			add(Box.createRigidArea(new Dimension(0,10)));
			add(firstNameLabel);
			add(Box.createRigidArea(new Dimension(0,2)));
			add(firstNameField);
			add(Box.createRigidArea(new Dimension(0,2)));
			add(lastNameLabel);
			add(Box.createRigidArea(new Dimension(0,2)));
			add(lastNameField);
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
			add(doctorRButton);
			add(Box.createRigidArea(new Dimension(0,2)));
			add(nurseRButton);
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
				String firstName = firstNameField.getText();
				String lastName = lastNameField.getText();
				String email = emailField.getText();
				String password = passwordField.getText();
				String cPassword = cPasswordField.getText();
				String phoneNumber = phoneNumberField.getText();
				Boolean docRButton = doctorRButton.isSelected();
				Boolean nurRButton = nurseRButton.isSelected();
				PrintStream console = System.out;
				
				if(!(password.equals(cPassword)))
				{
					JFrame frame = new JFrame();
					JOptionPane.showMessageDialog(frame, "Passwords do not match.");
				}
				else if(anyFieldsEmpty() && (doctorRButton.isSelected() || nurseRButton.isSelected()))
				{
					JFrame frame = new JFrame();
					JOptionPane.showMessageDialog(frame, "Please fill in all fields.");
				}
				
				//right here, we use the database class to get the list of doctors
				File f = Database.download("doctors.csv", console);
				String buffer = "";
				
				Database.download("doctors.csv", console);
						
				try {
					boolean found = false;
					Scanner scanner = new Scanner(f);
					scanner.useDelimiter("\n");
					int lineNumber = 0;
					while(scanner.hasNext())
					{
						if(scanner.next().contains(email)){
							found = true;
							System.out.println("line number is: " + lineNumber);
							break;
						}
						buffer += scanner.next();
						lineNumber++;
					}
					
					if(!found){
						buffer = "";
						JFrame frame = new JFrame();
						JOptionPane.showMessageDialog(frame, "Email not found in the database.");
						scanner.close();
						return;
					}
					else{
						buffer = "";
						scanner.close();
						scanner = new Scanner(f);
						scanner.useDelimiter("\n");
						
						for(int i = 0; i <= lineNumber; i++){
							buffer += scanner.next();
						}
						scanner.next();
						buffer += firstName+","+lastName+","+email+","+password+","+phoneNumber+","+docRButton.toString()+","+nurRButton.toString()+"\n";
						while(scanner.hasNext()){
							buffer += scanner.next();
						}
						System.out.println("buffer is: " + buffer);
						
						try {
							FileWriter fw = new FileWriter(f, false);
							fw.append(buffer);
							fw.close();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						Database.upload("doctors.csv", f);
					}
					
					scanner.close();
					System.out.println("Scanner closed.");
							
				} catch (FileNotFoundException e1) {
							e1.printStackTrace();
				}
			}
		}
		public class BackListener implements ActionListener{
			Container contentPane;
			public BackListener(Container contentPane){
				this.contentPane = contentPane;
			}
			public void actionPerformed(ActionEvent e){
				contentPane.removeAll();
				contentPane.add(new MainMenuPanel(contentPane));
				contentPane.invalidate();
				contentPane.validate();
			}
		}
	}