import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.PrintStream;

public class MainMenuPanel extends JPanel{
	
	public MainMenuPanel(Container contentPane){
		// TODO prioritize patients based on severity
		// TODO create a list or graph of history of each patient somehow (low priority)
		// TODO grab patients list
		// TODO when a patient is selected, show recent symptom entries
		
		//set to grid layout
		super(new GridLayout(1,2));
		
		contentPane.setPreferredSize(new Dimension(1000,480));
		
		//the list of patients, on a panel on the left
		String[] patientNames = getPatientList();
		JList<String> customerList = new JList<String>(patientNames);
		add(customerList);
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
				symptomRatingLabels[i] = new JLabel("placeholder " + i);
			}
			
			//these labels will never change from these values
			symptomLabels[0].setText("Pain:");
			symptomLabels[1].setText("Nausea:");
			symptomLabels[2].setText("Fatigue:");
			symptomLabels[3].setText("Anxiety:");
			symptomLabels[4].setText("Depression:");
			symptomLabels[5].setText("Constipation:");
			symptomLabels[6].setText("Diarrhea:");
			symptomLabels[7].setText("Cough:");
			symptomLabels[8].setText("Sore Throat:");
			symptomLabels[9].setText("Vomiting:");
			symptomLabels[10].setText("Fever:");
			
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
		
		
		//TODO use the patient csv files to update patient data everytime a new patient is selected
		//this code is probably not right, just the right idea maybe
//		public void updateSymptoms(int[] symptoms){
//			pain.setText("Pain: " + symptoms[0]);
//			for(int i = 1; i < symptoms.length; i++){
//				if(symptoms[i]  == 1)
					
//			}
//		}
		
	}
	
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
	
	public String[] getPatientList(){
		PrintStream console = System.out;
		Database.download("patients.csv", console);
		
		
		
		return new String[] {"harry", "sally", "tom"};
	}
}