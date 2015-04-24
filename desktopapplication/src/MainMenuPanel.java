import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class MainMenuPanel extends JPanel{
	
	public MainMenuPanel(Container contentPane){
		//set to grid layout
		super(new GridLayout(1,2));
		
		contentPane.setPreferredSize(new Dimension(1000,480));
		
		String[] patientNames = getPatientList();
		JList<String> customerList = new JList<String>(patientNames);
		add(customerList);
		add(new RightPanel(contentPane));
	}
	
	public class RightPanel extends JPanel{
		Container contentPane;
		public RightPanel(Container contentPane){
			//set up with a grid layout
			super(new GridLayout(11,2));
			this.contentPane = contentPane;
			
			//set up the labels that won't be changing, they simply say what symptom it is
			JLabel[] symptomLabels = new JLabel[11];
			for(int i = 0; i < symptomLabels.length; i++){
				symptomLabels[i] = new JLabel();
			}
			
			//these labels will display whether the symptom is selected or not, and what the pain level is
			JLabel[] symptomRatingLabels = new JLabel[11];
			for(int i = 0; i < symptomRatingLabels.length; i++){
				symptomRatingLabels[i] = new JLabel();
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
			for(int i = 1; i < symptomLabels.length * 2 - 4; i++){
				if(i%2 == 1){
					add(symptomLabels[(int) Math.floor(i/2)]);
				}
				if(i%2 == 0){
					add(symptomRatingLabels[(int) Math.floor(i/2)]);
				}
			}
					
		}
		
		
		
//		public void updateSymptoms(int[] symptoms){
//			pain.setText("Pain: " + symptoms[0]);
//			for(int i = 1; i < symptoms.length; i++){
//				if(symptoms[i]  == 1)
					
//			}
//		}
		
	}
	
	public String[] getPatientList(){
		//needs finishing!!
		//return the list of customers from the database
		return new String[] {"harry", "sally", "tom"};
	}
}