import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class MainMenuPanel extends JPanel{
	
	protected String[] tempArray;
	protected JLabel[] symptomRatingLabels;
	int threshold = 5;
	JList<String> patientList;
	String[] patientNames;
	Timer timer;
	
	public MainMenuPanel(Container contentPane){
		// TODO prioritize patients based on severity/list history
		// TODO threshold for every symptom?
		// TODO create a local textfield so the doctor can submit their painThreshold

		//set to grid layout
		super(new GridLayout(1,2));
		
		contentPane.setPreferredSize(new Dimension(1000,480));
		
		//the list of patients, on a panel on the left
		patientNames = getPatientList();
		
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
			super(new GridLayout(14,2));
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
			
			JButton editDetails = new JButton("Edit Personal Details");
			editDetails.addActionListener(new EditDetailsListener(contentPane));
			add(editDetails);
			
			JButton logoutButton = new JButton("Logout");
			logoutButton.addActionListener(new BackListener(contentPane));
			add(logoutButton);
			
			JButton updateThresholdButton = new JButton("Update Threshold:");
			updateThresholdButton.addActionListener(new EditThresholdListener(contentPane));
			add(updateThresholdButton);
			
			JTextField thresholdField = new JTextField();
			add(thresholdField);
			
			JButton viewPatientHistoryButton = new JButton("View Patient History");
			updateThresholdButton.addActionListener(new ViewPatientHistoryListener(contentPane));
			add(viewPatientHistoryButton);
			
			
			timer = new Timer();
			long delay = 0;
			long intervalPeriod = 60 * 1000; // 60 second interval
			TimerTask task = new TimerTask()
			{
				@Override
			    public void run()
				{
					updateSymptomsValues();
			    }
			};
			timer.scheduleAtFixedRate(task, delay, intervalPeriod);
		}	
	}
	
	
	ListSelectionListener lSelectionListener = new ListSelectionListener()
	{
		public void valueChanged(ListSelectionEvent listSelectionEvent) {
	        if(!listSelectionEvent.getValueIsAdjusting())
	        {
	        	@SuppressWarnings("unchecked")
				JList<String> pList = (JList<String>) listSelectionEvent.getSource();
	        	String selectionValue = pList.getSelectedValue();
	        	String[] Name = selectionValue.replaceAll("\\s", "").split(",");
	        	System.out.println(Name[0] + " " + Name[1]);
	        	
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
	    			} //catch (IOException e) {
	    				//e.printStackTrace();
	    			//}
	    		}
	    		else // file doesn't exist on the server or locally
	    		{
	    			JFrame frame = new JFrame();
	    			JOptionPane.showMessageDialog(frame, "No patients were found.");
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
						scanner.nextLine();
						if(scanner.hasNext()){
							//get the second line
							String temp = scanner.nextLine();
							System.out.println(temp);
							String[] tempArray = temp.split(",", 11);
							System.out.println(tempArray[0]);
							Integer painLevel;
							try {
								painLevel = Integer.valueOf(tempArray[0]);
								//if the pain level is at the threshold, make the text red
								if(painLevel >= (threshold)){
									for(int i = 0; i < symptomRatingLabels.length; i++)
									{
										System.out.println(tempArray[i]);
										symptomRatingLabels[i].setText(" " + tempArray[i]);
									}
									symptomRatingLabels[0].setForeground(Color.RED);
								}
								//if the pain level is only mildly urgent
								else if(painLevel >= (threshold) && painLevel < (threshold + 3))
								{
									for(int i = 0; i < symptomRatingLabels.length; i++)
									{
										symptomRatingLabels[i].setText(" " + tempArray[i]);
									}
									symptomRatingLabels[0].setForeground(Color.ORANGE);
								}
								else
								{
									for(int i = 0; i < symptomRatingLabels.length; i++)
									{
										symptomRatingLabels[i].setText(" " + tempArray[i]);
									}
									symptomRatingLabels[0].setForeground(Color.BLACK);
								}
								
							} catch (NumberFormatException e) {
								//e.printStackTrace();
							}	
						}						
						scanner.close();	
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
	    		}
	    		else
	    		{
	    			symptomRatingLabels[0].setForeground(Color.BLACK);
	    			for(int i = 0; i < symptomRatingLabels.length; i++)
					{
						symptomRatingLabels[i].setText("---");
					}
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
			timer.cancel();
			contentPane.removeAll();
			contentPane.add(new LoginPanel(contentPane));
			contentPane.invalidate();
			contentPane.validate();
		}
	}
	
	public class EditThresholdListener implements ActionListener{
		Container contentPane;
		public EditThresholdListener(Container contentPane){
			this.contentPane = contentPane;
		}
		public void actionPerformed(ActionEvent e){
			//TODO, finish the edit threshold click
		}
	}
	
	public class ViewPatientHistoryListener implements ActionListener{
		Container contentPane;
		public ViewPatientHistoryListener(Container contentPane){
			this.contentPane = contentPane;
		}
		public void actionPerformed(ActionEvent e){
			//TODO, finish the view patient history click
		}
	}
	
	
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
					if(line.contains(LoginPanel.getEmail()))
					{
						System.out.println("line = " + line);
						String[] pNames = line.split(",");
						patientNames[i] = pNames[1] + ", " + pNames[0];
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
	
	public void updateSymptomsValues()
	{
		for(int i = 0; i < patientNames.length; i++)
		{
			patientList.setSelectedValue(patientNames[i], true);
		}
	}
}