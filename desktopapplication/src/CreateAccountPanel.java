import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

public class CreateAccountPanel extends JPanel{
	JTextField firstNameField;
	JTextField lastNameField;
	JTextField emailField;
	JPasswordField passwordField;
	JPasswordField cPasswordField;
	JTextField phoneNumberField;
	JRadioButton doctorRButton;
	JRadioButton nurseRButton;
	
	AWSCredentials credentials = new BasicAWSCredentials(
			"AKIAJ6ESZAJPDCWD4MOA", 
			"SK1p8jgrSA4t6TlpOgXrX4IW9cVJRjCWSOIu901t");
	String bucketName			= "rpcareapp";
	String keyName				= "doctors.csv";
	AmazonS3 s3Client = new AmazonS3Client(credentials);
	
	public CreateAccountPanel(Container contentPane){
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
		passwordField = new JPasswordField(20);
		cPasswordField = new JPasswordField(20);
		phoneNumberField = new JTextField(20);
		JButton createAccountButton = new JButton("Create Account");
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
			char[] password = passwordField.getPassword();
			String phoneNumber = phoneNumberField.getText();
			Boolean docRButton = doctorRButton.isSelected();
			Boolean nurRButton = nurseRButton.isSelected();
			PrintStream console = System.out;
			
			// TODO We should verify if both passwords are equal
			// TODO We should verify if all fields are filled in
			
			try {
	            System.out.println("Downloading an object");
	            S3Object s3object = s3Client.getObject(new GetObjectRequest(
	            		bucketName, keyName));
	            System.out.println("Content-Type: "  + s3object.getObjectMetadata().getContentType());
	            BufferedReader reader = new BufferedReader(new InputStreamReader(s3object.getObjectContent()));
	            
	            PrintStream outcsv = new PrintStream(new FileOutputStream("doctors.csv"));
	            System.setOut(outcsv);
	            
	            // TODO line == null to inside the while condition
	            while (true) {
	                String line = reader.readLine();
	                if (line == null) break;

	                System.out.println(line);
	            }
	            System.setOut(console);
	            
	        } catch (AmazonServiceException ase) {
	            System.out.println("Caught an AmazonServiceException, which" +
	            		" means your request made it " +
	                    "to Amazon S3, but was rejected with an error response" +
	                    " for some reason.");
	            System.out.println("Error Message:    " + ase.getMessage());
	            System.out.println("HTTP Status Code: " + ase.getStatusCode());
	            System.out.println("AWS Error Code:   " + ase.getErrorCode());
	            System.out.println("Error Type:       " + ase.getErrorType());
	            System.out.println("Request ID:       " + ase.getRequestId());
	        } catch (AmazonClientException ace) {
	            System.out.println("Caught an AmazonClientException, which means"+
	            		" the client encountered " +
	                    "an internal error while trying to " +
	                    "communicate with S3, " +
	                    "such as not being able to access the network.");
	            System.out.println("Error Message: " + ace.getMessage());
	        } catch (IOException e1) {
				e1.printStackTrace();
			}
			
			File f = new File("doctors.csv");
			if(f.exists() && !f.isDirectory())
			{
				try {
					FileWriter fw = new FileWriter("doctors.csv", true);
					fw.append(firstName);
					fw.append(",");
					fw.append(lastName);
					fw.append(",");
					fw.append(email);
					fw.append(",");
					fw.append(password.toString());
					fw.append(",");
					fw.append(phoneNumber);
					fw.append(",");
					fw.append(docRButton.toString());
					fw.append(",");
					fw.append(nurRButton.toString());
					
					fw.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			else
			{
				FileWriter fw;
				try {
					fw = new FileWriter("doctors.csv");
					fw.append("firstName");
					fw.append(",");
					fw.append("lastName");
					fw.append(",");
					fw.append("email");
					fw.append(",");
					fw.append("password");
					fw.append(",");
					fw.append("phoneNumber");
					fw.append(",");
					fw.append("doctor");
					fw.append(",");
					fw.append("nurse");
					
					fw.append("\n");
					fw.append(firstName);
					fw.append(",");
					fw.append(lastName);
					fw.append(",");
					fw.append(email);
					fw.append(",");
					fw.append(password.toString());
					fw.append(",");
					fw.append(phoneNumber);
					fw.append(",");
					fw.append(docRButton.toString());
					fw.append(",");
					fw.append(nurRButton.toString());
					
					fw.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			
			try {
	            System.out.println("Uploading a new object to S3 from a file\n");
	            File file = new File("doctors.csv");
	            s3Client.putObject(new PutObjectRequest(bucketName, keyName, file));
	            
	            // TODO Popup box saying "Account Created"
	         } catch (AmazonServiceException ase) {
	            System.out.println("Caught an AmazonServiceException, which " +
	            		"means your request made it " +
	                    "to Amazon S3, but was rejected with an error response" +
	                    " for some reason.");
	            System.out.println("Error Message:    " + ase.getMessage());
	            System.out.println("HTTP Status Code: " + ase.getStatusCode());
	            System.out.println("AWS Error Code:   " + ase.getErrorCode());
	            System.out.println("Error Type:       " + ase.getErrorType());
	            System.out.println("Request ID:       " + ase.getRequestId());
	        } catch (AmazonClientException ace) {
	            System.out.println("Caught an AmazonClientException, which " +
	            		"means the client encountered " +
	                    "an internal error while trying to " +
	                    "communicate with S3, " +
	                    "such as not being able to access the network.");
	            System.out.println("Error Message: " + ace.getMessage());
	       	}
			
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