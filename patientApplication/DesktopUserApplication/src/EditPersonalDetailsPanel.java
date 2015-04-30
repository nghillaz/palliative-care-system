import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class EditPersonalDetailsPanel extends JPanel{
	JTextField firstNameField;
	JTextField lastNameField;
	JTextField emailField;
	JTextField passwordField;
	JTextField cPasswordField;
	JTextField phoneNumberField;
	
	//this holds all the text fields
	final ArrayList<JTextField> textFields = new ArrayList<>();
	
	//check if any fields are left empty
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
	
	//constructor
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

		add(Box.createRigidArea(new Dimension(0,2)));

		add(Box.createRigidArea(new Dimension(0,2)));
		add(createAccountButton);
		add(Box.createRigidArea(new Dimension(0,2)));
		add(backButton);
	}
	
		
	//listener for the submit button
	public class SubmitListener implements ActionListener{
		Container contentPane;
		public SubmitListener(Container contentPane){
			this.contentPane = contentPane;
		}
		public void actionPerformed(ActionEvent e){
			//grab the text from the fields
			String firstName = firstNameField.getText();
			String lastName = lastNameField.getText();
			String email = emailField.getText();
			String password = passwordField.getText();
			String cPassword = cPasswordField.getText();
			String phoneNumber = phoneNumberField.getText();

			//check if the password matches the confirm password and if any errors in filling out the form occur
			if(!(password.equals(cPassword)))
			{
				JFrame frame = new JFrame();
				JOptionPane.showMessageDialog(frame, "Passwords do not match.");
				return;
			}
			else if(anyFieldsEmpty())
			{
				JFrame frame = new JFrame();
				JOptionPane.showMessageDialog(frame, "Please fill in all fields.");
				return;
			}
			
			//right here, we use the database class to get the list of patients
			PrintStream console = System.out;
			File f = Database.download("patients.csv", console);
			String buffer = "";
			// TODO what if the patient.csv doesn't exist? This needs to have a if (Exists && !isDirectory).
					
			try {
				boolean found = false;
				Scanner scanner = new Scanner(f);
				scanner.useDelimiter("\n");
				int lineNumber = 0;
				
				//scan for the account
				while(scanner.hasNext())
				{
					if((scanner.next().toLowerCase()).contains(email.toLowerCase())){
						found = true;
						System.out.println("line number is: " + lineNumber);
						break;
					}
					lineNumber++;
				}
				
				//not in the database
				if(!found){
					buffer = "";
					JFrame frame = new JFrame();
					JOptionPane.showMessageDialog(frame, "Email not found in the database.");
					scanner.close();
					return;
				}
				//is in the database
				else{
					buffer = "";
					scanner.close();
					scanner = new Scanner(f);
					scanner.useDelimiter("\n");
					
					for(int i = 0; i < lineNumber; i++){
						buffer += scanner.next();
					}
					scanner.next();
					buffer += firstName+","+lastName+","+email+","+password+","+phoneNumber+"\n";
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
					Database.upload("patients.csv", f);
				}
					
				scanner.close();
				System.out.println("Scanner closed.");
				contentPane.removeAll();
				contentPane.add(new MainMenuPanel(contentPane));
				contentPane.invalidate();
				contentPane.validate();
						
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	//listener for the back button
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