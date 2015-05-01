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
	
	String[] tempArray;
	JLabel[] symptomRatingLabels;
	int[] thresholdValues = new int[10];
	int selectedPatient;
	JList<String> patientList;
	String[] patientNames;
	String patientEmail = "";
	PrintStream console = System.out;
	
	public MainMenuPanel(Container contentPane){
		//set to grid layout
		super(new GridLayout(1,2));
		
		//initialize threshold values array
		for(int i = 0; i < thresholdValues.length; i++){
			thresholdValues[i] = 5;
		}
		
		contentPane.setPreferredSize(new Dimension(1000,1000));
		
		//the list of patients, on a panel on the left
		patientNames = getPatientNames();
		
		patientList = new JList<String>(patientNames);
		patientList.addListSelectionListener(lSelectionListener);
		add(patientList);
		add(new RightPanel(contentPane));		
	}
	
	//the panel that handles displaying the patient data
	public class RightPanel extends JPanel{
		Container contentPane;
		public RightPanel(Container contentPane){
			//set up with a grid layout
			super(new GridLayout(23,2));
			this.contentPane = contentPane;
			
			//set up the labels that won't be changing, they simply say what symptom it is
			JLabel[] symptomLabels = new JLabel[11];
			for(int i = 0; i < symptomLabels.length; i++){
				symptomLabels[i] = new JLabel();
			}
			
			//these labels will display whether the symptom is selected or not, and what the pain level is
			symptomRatingLabels = new JLabel[11];
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
			
			JButton viewPatientHistoryButton = new JButton("View Patient History");
			viewPatientHistoryButton.addActionListener(new ViewPatientHistoryListener(contentPane));
			add(viewPatientHistoryButton);
			
			JButton updateDataButton = new JButton("Update Data");
			updateDataButton.addActionListener(new UpdateDataListener(contentPane));
			add(updateDataButton);
			
			JButton[] thresholdButtons = new JButton[10];
			
			for(int i = 0; i < thresholdButtons.length; i++){
				thresholdButtons[i] = new JButton();
			}
			
			JTextField[] thresholdTextFields = new JTextField[10];
			for(int i = 0; i < thresholdTextFields.length; i++){
				thresholdTextFields[i] = new JTextField();
			}
			
			//these Buttons will never change from these values
			thresholdButtons[0].setText("Edit Pain Threshold:");
			thresholdButtons[1].setText("Edit Tiredness Threshold:");
			thresholdButtons[2].setText("Edit Nausea Threshold:");
			thresholdButtons[3].setText("Edit Depression Threshold:");
			thresholdButtons[4].setText("Edit Anxiety Threshold:");
			thresholdButtons[5].setText("Edit Drowsiness Threshold:");
			thresholdButtons[6].setText("Edit Appetite Threshold:");
			thresholdButtons[7].setText("Edit Wellbeing Threshold:");
			thresholdButtons[8].setText("Edit Shortness of Breath Threshold:");
			thresholdButtons[9].setText("Edit Other Threshold:");
			
			for(int i = 0; i < thresholdButtons.length; i++){
				thresholdButtons[i].addActionListener(new EditThresholdListener(contentPane, thresholdTextFields[i], i));
				System.out.println("i = " + i);
			}
			
			//set up the panel so that the Buttons are in the correct spots
			//it's some weird math, but the logic makes them alternate left/right filling up all 22 spots
			for(int i = 0; i < thresholdButtons.length * 2; i++){
				if(i%2 == 0){
					//left side
					add(thresholdButtons[(int) Math.floor(i/2)]);
				}
				if(i%2 == 1){
					//right side
					add(thresholdTextFields[(int) Math.floor(i/2)]);
				}
			}
			
			JButton editDetails = new JButton("Edit Personal Details");
			editDetails.addActionListener(new EditDetailsListener(contentPane));
			add(editDetails);
			
			JButton logoutButton = new JButton("Logout");
			logoutButton.addActionListener(new BackListener(contentPane));
			add(logoutButton);
			
			updateSymptomsValues();
		}	
	}
	
	
	ListSelectionListener lSelectionListener = new ListSelectionListener()
	{
		public void valueChanged(ListSelectionEvent listSelectionEvent) {
	        if(!listSelectionEvent.getValueIsAdjusting())
	        {
	        	System.out.println(thresholdValues[0]);
	        	@SuppressWarnings("unchecked")
				JList<String> pList = (JList<String>) listSelectionEvent.getSource();
	        	//if list has lost focus to buttons
	        	String[] Name;
	        	if(pList.getSelectedValue() == null){
	        		String selectionValue = pList.getModel().getElementAt(selectedPatient);
		        	Name = selectionValue.replaceAll("\\s", "").split(",");
	        	}else{
	        		String selectionValue = pList.getSelectedValue();
		        	Name = selectionValue.replaceAll("\\s", "").split(",");
	        	}
	        	
	        	
	    		File f = Database.download("patients.csv", console);
	    		if(f.exists() && !f.isDirectory())
	    		{
	    			try
	    			{
	    				Scanner scanner = new Scanner(f);
						scanner.useDelimiter("\n");
						while(scanner.hasNext())
						{
							String temp = scanner.next();
							if(temp.contains(Name[0]) && temp.contains(Name[1]))
							{
								String[] tempArray = temp.replaceAll("\\s", "").split(",");
								patientEmail = tempArray[2];
								scanner.close();
								break;
							}
						}
						scanner.close();	
					} catch (FileNotFoundException e) {
						e.printStackTrace();
	    			} 
	    		}
	    		else // file doesn't exist on the server or locally
	    		{
	    			return;
	    		}
	    		
	    		File patientf = Database.download(patientEmail + ".csv", console);
	    		
	    		if(patientf.exists() && !patientf.isDirectory())
	    		{
	    			try {
						//scan the patient reports, show the most recent one (on line 2)
						Scanner scanner = new Scanner(patientf);
						scanner.useDelimiter("\n");
						//skip the header
						scanner.next();
						if(scanner.hasNext()){
							//get the most recent report if it exists
							String temp = scanner.next();
							String[] tempArray = temp.split(",", 13);
							try {
								//danger level is none, some, or lots depending on pain level
								String dangerLevel = "none";
								for(int i = 0; i < thresholdValues.length; i++){
									if(Integer.valueOf(tempArray[i]) >= (thresholdValues[i] + 3)){
										//the symptom is significantly problematic
										symptomRatingLabels[i].setText(tempArray[i]);
										symptomRatingLabels[i].setForeground(Color.RED);
										dangerLevel = "lots";
									}
									else if(Integer.valueOf(tempArray[i]) >= (thresholdValues[i] + 2) && Integer.valueOf(tempArray[i]) < (thresholdValues[i] + 3)){
										//the symptom is problematic
										symptomRatingLabels[i].setText(tempArray[i]);
										symptomRatingLabels[i].setForeground(Color.BLUE);
										if(dangerLevel != "lots")
											dangerLevel = "some";
									}
									else{
										symptomRatingLabels[i].setText(tempArray[i]);
										symptomRatingLabels[i].setForeground(Color.BLACK);
									}
								}
								//set the date and time properly
								symptomRatingLabels[10].setText(tempArray[10]);
								
								if(dangerLevel.equals("lots") && tempArray[11].trim().equals("FALSE")){
									System.out.println("lots of danger!!!");
									JFrame warningFrame = new JFrame();
									JOptionPane.showMessageDialog(warningFrame, tempArray[10] + ": " + Name[1] + " " + Name[0] + "'s symptoms are significantly problematic!");
								}else if(dangerLevel.equals("some") && tempArray[11].trim().equals("FALSE")){
									JFrame warningFrame = new JFrame();
									JOptionPane.showMessageDialog(warningFrame, tempArray[10] + ": " + Name[1] + " " + Name[0] + "'s symptoms are problematic!");
								}
								String buffer = "";
								Scanner symptomScanner = new Scanner(patientf);
								symptomScanner.useDelimiter("\n");
								//scan in the header
								buffer += symptomScanner.next() + "\n";
								//add in the modified symptom report
								buffer += tempArray[0] + "," + tempArray[1] + "," + tempArray[2] + "," + tempArray[3] + "," + tempArray[4] + "," + tempArray[5] + ","
										+ tempArray[6] + "," + tempArray[7] + "," + tempArray[8] + "," + tempArray[9] + "," + tempArray[10] + "," + "TRUE" + "\n";
								//skip the line we just constructed manually
								symptomScanner.nextLine();
								symptomScanner.nextLine();
								while(symptomScanner.hasNext()){
									buffer += symptomScanner.nextLine() + "\n";
								}
								//now we write the new buffer into the old symptom report file
								FileWriter symptomWriter = new FileWriter(patientf, false);
								System.out.println(buffer);
								symptomWriter.append(buffer);
								symptomScanner.close();
								symptomWriter.close();
								Database.upload(patientEmail + ".csv", patientf);
							}catch (NumberFormatException e1) {
								e1.printStackTrace();
							}
						}
						scanner.close();
		    		}catch (IOException e1) {
		    			e1.printStackTrace();
		    		}
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
	
	public class UpdateDataListener implements ActionListener{
		Container contentPane;
		public UpdateDataListener(Container contentPane){
			this.contentPane = contentPane;
		}
		public void actionPerformed(ActionEvent e){
			updateSymptomsValues();
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
	
	public class ViewPatientHistoryListener implements ActionListener{
		Container contentPane;
		public ViewPatientHistoryListener(Container contentPane){
			this.contentPane = contentPane;
		}
		public void actionPerformed(ActionEvent e){
			//TODO, finish the view patient history click
			File patientf = Database.download(patientEmail + ".csv", console);
			
			Scanner scanner;
			String message = "";
			try {
				scanner = new Scanner(patientf);
				scanner.useDelimiter("\n");
				message += scanner.nextLine().replace(",", " | ") + "\n";
				while(scanner.hasNext()){
					message += scanner.nextLine().replace(",", " | ") + "\n";
				}
				scanner.close();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}			
			JFrame frame = new JFrame();
			JOptionPane.showMessageDialog(frame, message);
		}
	}
	
	public String[] getPatientNames(){
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
				patientNames = new String[50];
				int i = 0;
				br = new BufferedReader(new FileReader(f));
				head = br.readLine();
				while ((line = br.readLine()) != null) {
					if(line.contains(LoginPanel.getEmail()))
					{
						System.out.println("line = " + line);
						String[] pNames = line.split(",");
						patientNames[i] = pNames[0] + ", " + pNames[1];
						i++;
					}
				}
				br.close();
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
	
	public DefaultListModel<String> getPatientList(DefaultListModel<String> model){
		PrintStream console = System.out;
		File f = Database.download("patients.csv", console);
		
		if(f.exists() && !f.isDirectory())
		{
			BufferedReader br;
			@SuppressWarnings("unused")
			String head;
			String line;
			try {
				patientNames = new String[100];
				br = new BufferedReader(new FileReader(f));
				head = br.readLine();
				while ((line = br.readLine()) != null) {
					if(line.contains(LoginPanel.getEmail()))
					{
						System.out.println("line = " + line);
						String[] pNames = line.split(",");
						model.addElement(pNames[1] + ", " + pNames[0]);
					}
				}
				br.close();
				return model;
				
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else // patients.csv doesn't exist on the server or locally
		{
			model.addElement("---");
			return model;
		}
		return null;
	}
	
	public void updateSymptomsValues()
	{
		for(int i = 0; i < patientNames.length; i++)
		{
			patientList.setSelectedValue(patientNames[i], true);
		}
	}
	
	public class EditThresholdListener implements ActionListener{
		Container contentPane;
		JTextField textField;
		int index;
		public EditThresholdListener(Container contentPane, JTextField textField, int index){
			this.contentPane = contentPane;
			this.textField = textField;
			this.index = index;
		}
		public void actionPerformed(ActionEvent e){
			//check if the textfield can be cast as an int
			try {
		        thresholdValues[index] = Integer.valueOf(textField.getText());
		        System.out.println("new threshold value at " + index + " is " + thresholdValues[index]);
		        textField.setText("");
		        //update the numbers' colors
		        updateSymptomsValues();
		    } catch (NumberFormatException f) {
		        return;
		    }
		}
	}
	
	
}