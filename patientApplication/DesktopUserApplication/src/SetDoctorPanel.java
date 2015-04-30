import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;


public class SetDoctorPanel extends JPanel{

	JLabel setDoctor;
	JComboBox selectDoctor;
	JButton setAsDoctor;
	JButton back;
	
	String[] doctorEmails;
	
	public SetDoctorPanel(Container contentPane) {
		//setting to box layout
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		//creating the components and setting them up
		//labels and combo box
		setDoctor = new JLabel("Set Doctor:");
		selectDoctor = new JComboBox<String>(getDoctorList());
		setAsDoctor = new JButton("Set as Doctor");
		back = new JButton("Back");
		
		//button listeners
		setAsDoctor.addActionListener(new SetAsDoctorListener(contentPane));
		back.addActionListener(new BackListener(contentPane));
		
		//setting alignment
		setDoctor.setAlignmentX(Component.CENTER_ALIGNMENT);
		selectDoctor.setAlignmentX(Component.CENTER_ALIGNMENT);
		setAsDoctor.setAlignmentX(Component.CENTER_ALIGNMENT);
		back.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		//setting maximum width for uneditable combo box
		selectDoctor.setMaximumSize(new Dimension(200, 30));
		
		//adding components
		add(Box.createRigidArea(new Dimension(0,150)));
		add(setDoctor);
		contentPane.add(selectDoctor);
		add(Box.createRigidArea(new Dimension(0,10)));
		add(selectDoctor);
		add(Box.createRigidArea(new Dimension(0,0)));
		add(setAsDoctor);
		add(Box.createRigidArea(new Dimension(0,150)));
		add(back);
	}
	
	
	@SuppressWarnings("resource")
	public String[] getDoctorList(){
		PrintStream console = System.out;
		File f = Database.download("doctors.csv", console);
		
		if(f.exists() && !f.isDirectory())
		{
			String[] doctorNames;

			doctorNames = new String[100];
			doctorEmails = new String[100];
			
			try {
				//scanner for reading the doctor details
				Scanner scanner = new Scanner(f);
				
				//ignore the header line
				scanner.useDelimiter("\n");
				scanner.next();
				
				//set the current line number to 0
				int lineNumber = 0;
				
				//now we parse through commas
				scanner.useDelimiter(",");
				
				while(scanner.hasNext()){
					doctorNames[lineNumber] = scanner.next() + ", " + scanner.next();
					doctorEmails[lineNumber] = scanner.next();
					
					//now we ignore the rest of the line
					scanner.useDelimiter("\n");
					scanner.nextLine();
					scanner.useDelimiter(",");
					
					lineNumber++;
					
				}
				return doctorNames;				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else // doctors.csv doesn't exist on the server or locally
		{
			JFrame frame = new JFrame();
			JOptionPane.showMessageDialog(frame, "No doctors were found.");
			return new String[] {"---"};
		}
		return null;
	}
	
	public class SetAsDoctorListener implements ActionListener{
		Container contentPane;
		public SetAsDoctorListener(Container contenetPane){
			this.contentPane = contentPane;
		}
		public void actionPerformed(ActionEvent arg0){
			// TODO 5. add MainMenuPanel contentPane
			
			PrintStream console = System.out;
			
			String selectedDoctor = (String)selectDoctor.getSelectedItem();
			
			File f = Database.download("doctors.csv", console);
			
			if(f.exists() && !f.isDirectory())
			{
				FileWriter fw;
				
				@SuppressWarnings("unused")
				String buffer = "";
				
				try {
					f = Database.download("patients.csv", console);
					
					String patientEmail = LoginPanel.getEmail();
					System.out.println(patientEmail + selectedDoctor);
					
					boolean found = false;
					Scanner scanner = new Scanner(f);
					scanner.useDelimiter("\n");
					int lineNumber = 0;					
					
					//scan for the account
					while(scanner.hasNext())
					{
						if((scanner.next().toLowerCase()).contains(patientEmail.toLowerCase())){
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
							JOptionPane.showMessageDialog(frame, "Could not find account in database");
							scanner.close();
							return;
						}
						//is in the database
						else{
							buffer = "";
							scanner.close();
							scanner = new Scanner(f);
							scanner.useDelimiter("\n");
							System.out.println("lineNumber is: " + lineNumber);
							//scan in all the unchanged accounts
							for(int i = 0; i < lineNumber; i++){
								buffer += scanner.next();
							}
							scanner.useDelimiter(",");
							//name
							buffer += scanner.next() + ",";
							buffer += scanner.next() + ",";
							//email
							buffer += scanner.next() + ",";
							//password
							buffer += scanner.next() + ",";
							//phonenumber
							buffer += scanner.next() + ",";
							//add in the new doctor on the line
							System.out.println("this is what i'm putting in the email slot: " + doctorEmails[selectDoctor.getSelectedIndex()]);
							buffer += doctorEmails[selectDoctor.getSelectedIndex()] + "\n";
							
							//scan in the rest of the patients
							scanner.nextLine();
							scanner.useDelimiter("\n");
							while(scanner.hasNext()){
								buffer += scanner.next();
							}
							System.out.println("buffer is: " + buffer);
							
							try {
								fw = new FileWriter(f, false);
								fw.append(buffer);
								fw.close();
							} catch (IOException e1) {
								e1.printStackTrace();
							}
							Database.upload("patients.csv", f);
						}
					}catch (IOException e1) {
						e1.printStackTrace();
				}
			}
			else // doctors.csv doesn't exist on the server or locally
			{
				JFrame frame = new JFrame();
				JOptionPane.showMessageDialog(frame, "No doctors were found.");

			}

		}
	}
	
	public class BackListener implements ActionListener{
		Container contentPane;
		public BackListener(Container contentPane){
			this.contentPane = contentPane;
		}
	
		public void actionPerformed(ActionEvent arg0) {
			contentPane.removeAll();
			contentPane.add(new MainMenuPanel(contentPane));
			contentPane.invalidate();
			contentPane.validate();
			
		}
	}
	
	
}