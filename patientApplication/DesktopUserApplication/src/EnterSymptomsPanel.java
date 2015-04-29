import java.awt.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class EnterSymptomsPanel extends JPanel{
	
	//components for panel
	//JLabels for symptoms
	JLabel pain;
	JLabel tiredness;
	JLabel nausea;
	JLabel depression;
	JLabel anxiety;
	JLabel drowsiness;
	JLabel appetite;
	JLabel wellbeing;
	JLabel sob;
	
	//JSliders for symptoms
	JSlider painSlider;
	JSlider tirednessSlider;
	JSlider nauseaSlider;
	JSlider depressionSlider;
	JSlider anxietySlider;
	JSlider drowsinessSlider;
	JSlider appetiteSlider;
	JSlider wellbeingSlider;
	JSlider sobSlider;
	
	JButton submit;
	JButton back;
	
	public EnterSymptomsPanel(Container contentPane){
		//setting to box layout
		//setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		//setting to grid layout
		setLayout(new GridLayout(5,5));
		
		//setting up the components
		//labels
		pain = new JLabel("Pain");
		tiredness = new JLabel("Tiredness");
		nausea = new JLabel("Nausea");
		depression = new JLabel("Depression");
		anxiety = new JLabel("Anxiety");
		drowsiness = new JLabel("Drowsiness");
		appetite = new JLabel("Appetite");
		wellbeing = new JLabel("Wellbeing");
		sob = new JLabel("Shortness of Breath");
		
		submit = new JButton("Submit");
		back = new JButton("Back");
		
		//sliders
		JSlider painSlider = new JSlider(JSlider.HORIZONTAL,
                0, 10, 1);
		JSlider tirednessSlider = new JSlider(JSlider.HORIZONTAL,
                0, 10, 1);
		JSlider nauseaSlider = new JSlider(JSlider.HORIZONTAL,
                0, 10, 1);
		JSlider depressionSlider = new JSlider(JSlider.HORIZONTAL,
                        0, 10, 1);
		JSlider anxietySlider = new JSlider(JSlider.HORIZONTAL,
                0, 10, 1);
		JSlider drowsinessSlider = new JSlider(JSlider.HORIZONTAL,
                0, 10, 1);
		JSlider appetiteSlider = new JSlider(JSlider.HORIZONTAL,
                0, 10, 1);
		JSlider wellbeingSlider = new JSlider(JSlider.HORIZONTAL,
                0, 10, 1);
		JSlider sobSlider = new JSlider(JSlider.HORIZONTAL,
                0, 10, 1);
		
		
		//setting paint ticks and labels
		painSlider.setMajorTickSpacing(1);
		painSlider.setMinorTickSpacing(1);
		painSlider.setPaintTicks(true);
		painSlider.setPaintLabels(true);
		
		tirednessSlider.setMajorTickSpacing(1);
		tirednessSlider.setMinorTickSpacing(1);
		tirednessSlider.setPaintTicks(true);
		tirednessSlider.setPaintLabels(true);
		
		nauseaSlider.setMajorTickSpacing(1);
		nauseaSlider.setMinorTickSpacing(1);
		nauseaSlider.setPaintTicks(true);
		nauseaSlider.setPaintLabels(true);
		
		depressionSlider.setMajorTickSpacing(1);
		depressionSlider.setMinorTickSpacing(1);
		depressionSlider.setPaintTicks(true);
		depressionSlider.setPaintLabels(true);
		
		anxietySlider.setMajorTickSpacing(1);
		anxietySlider.setMinorTickSpacing(1);
		anxietySlider.setPaintTicks(true);
		anxietySlider.setPaintLabels(true);
		
		drowsinessSlider.setMajorTickSpacing(1);
		drowsinessSlider.setMinorTickSpacing(1);
		drowsinessSlider.setPaintTicks(true);
		drowsinessSlider.setPaintLabels(true);
		
		appetiteSlider.setMajorTickSpacing(1);
		appetiteSlider.setMinorTickSpacing(1);
		appetiteSlider.setPaintTicks(true);
		appetiteSlider.setPaintLabels(true);
		
		wellbeingSlider.setMajorTickSpacing(1);
		wellbeingSlider.setMinorTickSpacing(1);
		wellbeingSlider.setPaintTicks(true);
		wellbeingSlider.setPaintLabels(true);
		
		sobSlider.setMajorTickSpacing(1);
		sobSlider.setMinorTickSpacing(1);
		sobSlider.setPaintTicks(true);
		sobSlider.setPaintLabels(true);
		
		
		//button listeners
		//submit.addActionListener(new SubmitListener(contentPane));
		back.addActionListener(new BackListener(contentPane));
		
		
		//setting alignment
		
		pain.setAlignmentX(Component.CENTER_ALIGNMENT);
		painSlider.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		tiredness.setAlignmentX(Component.CENTER_ALIGNMENT);
		tirednessSlider.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		nausea.setAlignmentX(Component.CENTER_ALIGNMENT);
		nauseaSlider.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		depression.setAlignmentX(Component.CENTER_ALIGNMENT);
		depressionSlider.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		anxiety.setAlignmentX(Component.CENTER_ALIGNMENT);
		anxietySlider.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		drowsiness.setAlignmentX(Component.CENTER_ALIGNMENT);
		drowsinessSlider.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		appetite.setAlignmentX(Component.CENTER_ALIGNMENT);
		appetiteSlider.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		wellbeing.setAlignmentX(Component.CENTER_ALIGNMENT);
		wellbeingSlider.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		sob.setAlignmentX(Component.CENTER_ALIGNMENT);
		sobSlider.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		//setting slider size
		painSlider.setMaximumSize(new Dimension(200, 30));
		tirednessSlider.setMaximumSize(new Dimension(200, 30));
		nauseaSlider.setMaximumSize(new Dimension(200, 30));
		depressionSlider.setMaximumSize(new Dimension(200, 30));
		anxietySlider.setMaximumSize(new Dimension(200, 30));
		drowsinessSlider.setMaximumSize(new Dimension(200, 30));
		appetiteSlider.setMaximumSize(new Dimension(200, 30));
		wellbeingSlider.setMaximumSize(new Dimension(200,30));
		sobSlider.setMaximumSize(new Dimension(200, 30));
		
		
		//adding components
		add(pain);
		add(painSlider);
		
		add(tiredness);
		add(tirednessSlider);

		add(nausea);
		add(nauseaSlider);

		add(depression);
		add(depressionSlider);

		add(anxiety);
		add(anxietySlider);

		add(drowsiness);
		add(drowsinessSlider);

		add(appetite);
		add(appetiteSlider);

		add(wellbeing);
		add(wellbeingSlider);

		add(sob);
		add(sobSlider);
		
		add(submit);
		add(back);
		
		
		//adding components in box layout
		/*
		add(Box.createRigidArea(new Dimension(0,2)));
		add(pain);
		add(Box.createRigidArea(new Dimension(0,10)));
		add(painSlider);
		
		add(Box.createRigidArea(new Dimension(0,2)));
		add(tiredness);
		add(Box.createRigidArea(new Dimension(0,10)));
		add(tirednessSlider);
		
		add(Box.createRigidArea(new Dimension(0,2)));
		add(nausea);
		add(Box.createRigidArea(new Dimension(0,10)));
		add(nauseaSlider);
		
		add(Box.createRigidArea(new Dimension(0,2)));
		add(depression);
		add(Box.createRigidArea(new Dimension(0,10)));
		add(depressionSlider);
		
		add(Box.createRigidArea(new Dimension(0,2)));
		add(anxiety);
		add(Box.createRigidArea(new Dimension(0,10)));
		add(anxietySlider);
		
		add(Box.createRigidArea(new Dimension(0,2)));
		add(drowsiness);
		add(Box.createRigidArea(new Dimension(0,10)));
		add(drowsinessSlider);
		
		add(Box.createRigidArea(new Dimension(0,2)));
		add(appetite);
		add(Box.createRigidArea(new Dimension(0,10)));
		add(appetiteSlider);
		
		add(Box.createRigidArea(new Dimension(0,2)));
		add(wellbeing);
		add(Box.createRigidArea(new Dimension(0,10)));
		add(wellbeingSlider);
		
		add(Box.createRigidArea(new Dimension(0,2)));
		add(sob);
		add(Box.createRigidArea(new Dimension(0,10)));
		add(sobSlider);*/
		
		
	}
	
	//TODO rest of this method implementation
	public class SubmitListener implements ActionListener{
		Container contentPane;
		public SubmitListener(Container contenetPane){
			this.contentPane = contentPane;
		}
		public void actionPerformed(ActionEvent arg0){
			int painValue = 0;
			int tirednessValue = 0;
			int nauseaValue = 0;
			int depressionValue = 0;
			int anxietyValue = 0;
			int drowsinessValue = 0;
			int appetiteValue = 0;
			int wellbeingValue = 0;
			int sobValue = 0;
			String email = "";
			
			PrintStream console = System.out;
			
			//will have to create or search for patient file here
			
			File f = Database.download("patients.csv", console);
			String buffer = "";
			
			try{
				boolean found = false;
				Scanner scanner = new Scanner(f);
				scanner.useDelimiter("\n");
				int lineNumber = 0;
				
				
				//TODO specific changes to method functionality
				//scan for the account
				while(scanner.hasNextLine()){
					if((scanner.next().toLowerCase()).contains(email.toLowerCase())){
						found = true;
						System.out.println("line number is: " + lineNumber);
						break;
					}
					lineNumber++;
				}if(!found){
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
					buffer += painValue+","+tirednessValue+","+nauseaValue+","
							+ ""+depressionValue+","+anxietyValue+","+drowsinessValue+","
							+appetiteValue+","+wellbeingValue+","+sobValue+"\n";
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
			}catch (FileNotFoundException e1) {
				e1.printStackTrace();
				}
			
			
		}
	}
	
	public class BackListener implements ActionListener{
		Container contentPane;
		public BackListener(Container contenetPane){
			this.contentPane = contentPane;
		}
		public void actionPerformed(ActionEvent arg0){
			contentPane.removeAll();
			contentPane.add(new MainMenuPanel(contentPane));
			contentPane.invalidate();
			contentPane.validate();
		}
	}

}