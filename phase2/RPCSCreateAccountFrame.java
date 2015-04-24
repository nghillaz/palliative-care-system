import java.awt.EventQueue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.*;
//import com.jgoodies.forms.layout.FormLayout;
//import com.jgoodies.forms.layout.ColumnSpec;
//import com.jgoodies.forms.layout.RowSpec;
//import com.jgoodies.forms.factories.FormFactory;


public class RPCSCreateAccountFrame {

	private JFrame frame;
	private JMenuBar menuBar;
	private JMenu mnFile;
	private JMenu mnEdit;
	private JMenuItem menuItem;
	private JMenuItem mntmEditPersonalDetails;
	private JButton btnCreateAccount;
	private JLabel lblUsername;
	private JLabel lblLastName;
	private JLabel lblEmail;
	private JLabel lblPassword;
	private JLabel lblConfirmPassword;
	private JTextField email;
	private JTextField firstName;
	private JTextField lastName;
	private JPasswordField password;
	private JPasswordField passwordConfirm;
	private JRadioButton rdbtnDoctor;
	private JRadioButton rdbtnNurse;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JLabel lblOccupation;
	private JLabel requiredField;
	
	private JLabel test;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RPCSCreateAccountFrame window = new RPCSCreateAccountFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public RPCSCreateAccountFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Account Creation");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		firstName = new JTextField();
		firstName.setBounds(131, 16, 126, 20);
		firstName.setColumns(15);
		frame.getContentPane().add(firstName);
		
		email = new JTextField();
		email.setBounds(131, 78, 126, 20);
		email.setColumns(15);
		frame.getContentPane().add(email);
		
		btnCreateAccount = new JButton("Create Account");
		btnCreateAccount.setBounds(317, 201, 127, 23);
		frame.getContentPane().add(btnCreateAccount);
		
		lblUsername = new JLabel("First Name:");
		lblUsername.setBounds(31, 1, 200, 50);
		frame.getContentPane().add(lblUsername);
		
		lastName = new JTextField();
		lastName.setColumns(15);
		lastName.setBounds(131, 47, 126, 20);
		frame.getContentPane().add(lastName);
		
		lblEmail = new JLabel("Email:");
		lblEmail.setBounds(31, 63, 200, 50);
		frame.getContentPane().add(lblEmail);
		
		lblPassword = new JLabel("Password:");
		lblPassword.setBounds(31, 94, 200, 50);
		frame.getContentPane().add(lblPassword);
		
		lblConfirmPassword = new JLabel("Confirm Password:");
		lblConfirmPassword.setBounds(31, 125, 200, 50);
		frame.getContentPane().add(lblConfirmPassword);
		
		password = new JPasswordField();
		password.setColumns(15);
		password.setBounds(131, 109, 126, 20);
		frame.getContentPane().add(password);
		
		passwordConfirm = new JPasswordField();
		passwordConfirm.setColumns(15);
		passwordConfirm.setBounds(131, 140, 126, 20);
		frame.getContentPane().add(passwordConfirm);
		
		lblLastName = new JLabel("Last Name:");
		lblLastName.setBounds(31, 32, 200, 50);
		frame.getContentPane().add(lblLastName);
		
		rdbtnDoctor = new JRadioButton("Doctor");
		buttonGroup.add(rdbtnDoctor);
		rdbtnDoctor.setBounds(131, 167, 109, 23);
		frame.getContentPane().add(rdbtnDoctor);
		
		rdbtnNurse = new JRadioButton("Nurse");
		buttonGroup.add(rdbtnNurse);
		rdbtnNurse.setBounds(131, 187, 109, 23);
		frame.getContentPane().add(rdbtnNurse);
		
		lblOccupation = new JLabel("Occupation:");
		lblOccupation.setBounds(31, 153, 200, 50);
		frame.getContentPane().add(lblOccupation);
		
		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
		
		mntmEditPersonalDetails = new JMenuItem("Edit Personal Details");
		mnEdit.add(mntmEditPersonalDetails);
		
		//move later
		//test = new JLabel(concatenateString);
		//test.setBounds(100, 100, 200, 50);
		//frame.getContentPane().add(test);
		
		
		requiredField = new JLabel("Please fill all fields!");
		frame.getContentPane().add(requiredField);
		
		btnCreateAccount.addActionListener(new ActionListener(){
		
			public void actionPerformed(ActionEvent e){
				String concatenateString = email.getText() + "/" + firstName.getText() + "/" + lastName.getText() 
						 + "/" + password.getText() + "/" + passwordConfirm.getText();
				if(rdbtnDoctor.isSelected()){
					concatenateString += "/Doctor";
				}else if(rdbtnNurse.isSelected()){
					concatenateString += "/Nurse";
				}else{
					requiredField.setBounds(300,13,400,20);
				}
				System.out.println(concatenateString); //test on the console if strings are concatenated
				
				
				//checks if any fields are empty
				if(firstName.getText().equals("") || lastName.getText().equals("") || 
						email.getText().equals("") || password.getText().equals("") ||
						passwordConfirm.getText().equals("")){
					requiredField.setBounds(300,13,400,20);
				}
				
				//******
				//THIS METHOD IS TO BE IMPLEMENTED IN THE FUTURE VERSIONS
				//AccountParser(concatenateString)
			}
		});
		
	}
}
