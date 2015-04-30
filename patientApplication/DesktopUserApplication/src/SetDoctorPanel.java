import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;


public class SetDoctorPanel extends JPanel{

	JLabel setDoctor;
	JComboBox selectDoctor;
	JButton setAsDoctor;
	JButton back;
	
	//String [] settingDoctor = {""};
	String [] settingDoctor = getDoctorList();
	
	public SetDoctorPanel(Container contentPane) {
		//setting to box layout
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		//creating the components and setting them up
		//labels and combo box
		setDoctor = new JLabel("Set Doctor:");
		selectDoctor = new JComboBox(settingDoctor);
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
			BufferedReader br;
			@SuppressWarnings("unused")
			String head;
			String line;
			String[] doctorNames;
			try {
				doctorNames = new String[100];
				int i = 0;
				br = new BufferedReader(new FileReader(f));
				head = br.readLine();
				while ((line = br.readLine()) != null) {
				    String[] dNames = line.split(",");
					doctorNames[i] = dNames[1] + ", " + dNames[0];
					i++;
				}
				return doctorNames;
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else // patients.csv doesn't exist on the server or locally
		{
			JFrame frame = new JFrame();
			JOptionPane.showMessageDialog(frame, "No patients were found.");
			return new String[] {"---"};
		}
		return null;
	}
	
	//setDoctorPanel Listener
	/*public class setDoctorPanelListener implements ActionListener{
		Container contentPane;
		public LoginListener(Container contentPane){
			this.contentPane = contentPane;
		}
		
		public void ActionPerformed(ActionEvent e){
			PrintStream console = System.out;
			File f = Database.download("doctors.csv", console);
			
			//downloads file from database
			Database.download("doctors.csv", console);
			
			//TODO
			try{
				
			}
			
		}
	}*/
	
	
	
	public class SetAsDoctorListener implements ActionListener{
		Container contentPane;
		public SetAsDoctorListener(Container contenetPane){
			this.contentPane = contentPane;
		}
		public void actionPerformed(ActionEvent arg0){
			contentPane.removeAll();
			
			//TODO Add functionality of button
			
			
			contentPane.invalidate();
			contentPane.validate();
		}
	}
	
	
	public class BackListener implements ActionListener{
		Container contentPane;
		public BackListener(Container contentPane){
			this.contentPane = contentPane;
		}
	
		public void actionPerformed(ActionEvent arg0) {
			// TODO
			contentPane.removeAll();
			contentPane.add(new MainMenuPanel(contentPane));
			contentPane.invalidate();
			contentPane.validate();
			
		}
	}
	
	
}
