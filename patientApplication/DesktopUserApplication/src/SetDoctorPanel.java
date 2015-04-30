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
					
					//and set it back to parsing through as commas for the next doctor
					scanner.useDelimiter(",");
					System.out.println(doctorNames[lineNumber] + ", " + doctorEmails[lineNumber]);
					scanner.next();
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
			// TODO 1. Grab the email of the patient
			// TODO 2. Locate the patient in the patients.csv file
			// TODO 3. Append the doctor's email address
			// TODO 4. Upload patient.csv
			// TODO 5. add MainMenuPanel contentPane
			
			PrintStream console = System.out;
			File f1 = Database.download("doctors.csv", console);
			File f2 = Database.download("patients.csv", console);
			
			String selectedDoctor = (String)selectDoctor.getSelectedItem();
			
			if(f1.exists() && !f1.isDirectory() && f2.exists() && !f2.isDirectory())
			{
				
				BufferedReader br1;
				BufferedReader br2;
				FileWriter fw1;
				FileWriter fw2;
				
				@SuppressWarnings("unused")
				String head;
				String line;
				String patientEmail;
				String doctorEmail = "";
				String buffer = "";
				
				try {
					   patientEmail = LoginPanel.getEmail();
					   System.out.println(patientEmail + selectedDoctor);
					   
					   	br1 = new BufferedReader(new FileReader(f1));
						br2 = new BufferedReader(new FileReader(f2));
						fw1 = new FileWriter("patients.csv", true);
						head = br1.readLine();
						while ((line = br1.readLine()) != null ) {
						    String[] dNames = line.split(",");
							if(selectedDoctor.equals(dNames[1  ] + ", " + dNames[0])){
								doctorEmail = dNames[2];
								System.out.println(line);
								
								
							}
						}
						while((line = br2.readLine()) != null){
								String[] pNames = line.split(",");
								if(pNames[2].equals(patientEmail)){
								buffer += pNames[0]+","+pNames[1]+","+pNames[2]+","+pNames[3]+","+pNames[4]+","+doctorEmail+"\n";
								
								}
								
						}
					br1.close();
					System.out.println(buffer);
					fw1.append(buffer);
				
					
				} catch (IOException e) {
					e.printStackTrace();
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