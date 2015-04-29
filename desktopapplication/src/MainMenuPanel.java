import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class MainMenuPanel extends JPanel{
	
	public MainMenuPanel(Container contentPane){
		// TODO prioritize patients based on severity
		// TODO create a list or graph of history of each patient somehow (low priority)
		// TODO when a patient is selected, show recent symptom entries
		
		//set to grid layout
		super(new GridLayout(1,2));
		
		contentPane.setPreferredSize(new Dimension(1000,480));
		
		//the list of patients, on a panel on the left
		String[] patientNames = getPatientList();
		final JList<String> patientList = new JList<String>(patientNames);
		patientList.addListSelectionListener(lSelectionListener);
		add(patientList);
		add(new RightPanel(contentPane));
	}
	
	//the panel that handles displaying the patient data
	public class RightPanel extends JPanel{
		Container contentPane;
		public RightPanel(Container contentPane){
			//set up with a grid layout
			super(new GridLayout(12,2));
			this.contentPane = contentPane;
			
			//set up the labels that won't be changing, they simply say what symptom it is
			JLabel[] symptomLabels = new JLabel[11];
			for(int i = 0; i < symptomLabels.length; i++){
				symptomLabels[i] = new JLabel();
			}
			
			//these labels will display whether the symptom is selected or not, and what the pain level is
			JLabel[] symptomRatingLabels = new JLabel[11];
			for(int i = 0; i < symptomRatingLabels.length; i++){
				symptomRatingLabels[i] = new JLabel("---");
			}
			
			
			//these labels will never change from these values
			symptomLabels[0].setText("Pain:");
			symptomLabels[1].setText("Tiredness:");
			symptomLabels[2].setText("Nausea:");
			symptomLabels[3].setText("Depression:");
			symptomLabels[4].setText("Anxiety:");
			symptomLabels[5].setText("Drowsiness:");
			symptomLabels[6].setText("Appetite:");
			symptomLabels[7].setText("Wellbeing:");
			symptomLabels[8].setText("Shortness of breath:");
			symptomLabels[9].setText("Other:");
			symptomLabels[10].setText("Submitted On:");
			
			//set up the panel so that the labels are in the correct spots
			//it's some weird math, but the logic makes them alternate left/right filling up all 22 spots
			for(int i = 0; i < symptomLabels.length * 2; i++){
				if(i%2 == 0){
					add(symptomLabels[(int) Math.floor(i/2)]);
				}
				if(i%2 == 1){
					add(symptomRatingLabels[(int) Math.floor(i/2)]);
				}
			}
			
			JButton editDetails = new JButton("Edit Personal Details");
			editDetails.addActionListener(new EditDetailsListener(contentPane));
			add(editDetails);
			
			JButton logoutButton = new JButton("Logout");
			logoutButton.addActionListener(new BackListener(contentPane));
			add(logoutButton);
			
				
		}
		
		public int updateSymptom(int symptoms)
		{
			
			
			return 0;
		}	
	
	}
	
	ListSelectionListener lSelectionListener = new ListSelectionListener()
	{
		public void valueChanged(ListSelectionEvent listSelectionEvent) {
	        if(!listSelectionEvent.getValueIsAdjusting())
	        {
	        	JList<String> patientList = (JList<String>) listSelectionEvent.getSource();
	        	String selectionValue = patientList.getSelectedValue();
	        	String[] Name = selectionValue.replaceAll("\\s", "").split(",");
	        	System.out.println(Name[0]);
	        	System.out.println(Name[1]);
	        	
	        	
	        	PrintStream console = System.out;
	    		File f = Database.download("patients.csv", console);
	    		String patientEmail = null;
	    		if(f.exists() && !f.isDirectory())
	    		{
	    			try
	    			{
	    				Scanner scanner = new Scanner(f);
						scanner.useDelimiter("\n");
						while(scanner.hasNext())
						{
							String temp = scanner.next().toLowerCase();
							if(temp.contains(Name[0].toLowerCase()) && temp.contains(Name[1].toLowerCase()))
							{
								String[] tempArray = temp.replaceAll("\\s", "").split(",");
								patientEmail = tempArray[5];
								System.out.println(tempArray[5]);
								scanner.close();
								return;
							}
						}
						scanner.close();
					
					} catch (FileNotFoundException e) {
						e.printStackTrace();
	    			} catch (IOException e) {
	    				e.printStackTrace();
	    			}
	    		}
	    		else // file doesn't exist on the server or locally
	    		{
	    			JFrame frame = new JFrame();
	    			JOptionPane.showMessageDialog(frame, "No patients were found.");
	    			return;
	    		}
	    		
	    		File patientf = Database.download(patientEmail + ".csv", console);
	    		
	    		if(f.exists() && !f.isDirectory())
	    		{
	    			for(int i = 0; i < symptomRatingLabels.length; i++)
	    			{
	    				Integer value = updateSymptom(i);
	    				symptomRatingLabels[i] = new JLabel(value.toString());
	    			}
	    		}
	    		else
	    		{
	    			JFrame frame = new JFrame();
	    			JOptionPane.showMessageDialog(frame, "The patient has yet to submit symptoms.");
	    			return;
	    		}

	        }
		}
	};
	
	//listener for the button that takes you to edit personal details panel
	public class EditDetailsListener implements ActionListener{
		Container contentPane;
		public EditDetailsListener(Container contentPane){
			this.contentPane = contentPane;
		}
		public void actionPerformed(ActionEvent e){
			contentPane.removeAll();
			contentPane.add(new EditPersonalDetailsPanel(contentPane));
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
	
	@SuppressWarnings("resource")
	public String[] getPatientList(){
		PrintStream console = System.out;
		File f = Database.download("patients.csv", console);
		
		if(f.exists() && !f.isDirectory())
		{
			BufferedReader br;
			@SuppressWarnings("unused")
			String head;
			String line;
			String[] patientNames;
			try {
				patientNames = new String[100];
				int i = 0;
				br = new BufferedReader(new FileReader(f));
				head = br.readLine();
				while ((line = br.readLine()) != null) {
				    String[] pNames = line.split(",");
					patientNames[i] = pNames[1] + ", " + pNames[0];
					i++;
				}
				return patientNames;
				
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
}