import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.io.File;
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
	// TODO There should be "other" too
	
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
                0, 10, 0);
		JSlider tirednessSlider = new JSlider(JSlider.HORIZONTAL,
                0, 10, 0);
		JSlider nauseaSlider = new JSlider(JSlider.HORIZONTAL,
                0, 10, 0);
		JSlider depressionSlider = new JSlider(JSlider.HORIZONTAL,
                        0, 10, 0);
		JSlider anxietySlider = new JSlider(JSlider.HORIZONTAL,
                0, 10, 0);
		JSlider drowsinessSlider = new JSlider(JSlider.HORIZONTAL,
                0, 10, 0);
		JSlider appetiteSlider = new JSlider(JSlider.HORIZONTAL,
                0, 10, 0);
		JSlider wellbeingSlider = new JSlider(JSlider.HORIZONTAL,
                0, 10, 0);
		JSlider sobSlider = new JSlider(JSlider.HORIZONTAL,
                0, 10, 0);
		
		
		//setting paint ticks and labels
		painSlider.setMajorTickSpacing(1);
		painSlider.setMinorTickSpacing(1);
		painSlider.setPaintTicks(true);
		painSlider.setPaintLabels(true);
		painSlider.setSnapToTicks(true);
		
		tirednessSlider.setMajorTickSpacing(1);
		tirednessSlider.setMinorTickSpacing(1);
		tirednessSlider.setPaintTicks(true);
		tirednessSlider.setPaintLabels(true);
		painSlider.setSnapToTicks(true);
		
		nauseaSlider.setMajorTickSpacing(1);
		nauseaSlider.setMinorTickSpacing(1);
		nauseaSlider.setPaintTicks(true);
		nauseaSlider.setPaintLabels(true);
		nauseaSlider.setSnapToTicks(true);
		
		depressionSlider.setMajorTickSpacing(1);
		depressionSlider.setMinorTickSpacing(1);
		depressionSlider.setPaintTicks(true);
		depressionSlider.setPaintLabels(true);
		depressionSlider.setSnapToTicks(true);
		
		anxietySlider.setMajorTickSpacing(1);
		anxietySlider.setMinorTickSpacing(1);
		anxietySlider.setPaintTicks(true);
		anxietySlider.setPaintLabels(true);
		anxietySlider.setSnapToTicks(true);
		
		drowsinessSlider.setMajorTickSpacing(1);
		drowsinessSlider.setMinorTickSpacing(1);
		drowsinessSlider.setPaintTicks(true);
		drowsinessSlider.setPaintLabels(true);
		drowsinessSlider.setSnapToTicks(true);
		
		appetiteSlider.setMajorTickSpacing(1);
		appetiteSlider.setMinorTickSpacing(1);
		appetiteSlider.setPaintTicks(true);
		appetiteSlider.setPaintLabels(true);
		appetiteSlider.setSnapToTicks(true);
		
		wellbeingSlider.setMajorTickSpacing(1);
		wellbeingSlider.setMinorTickSpacing(1);
		wellbeingSlider.setPaintTicks(true);
		wellbeingSlider.setPaintLabels(true);
		appetiteSlider.setSnapToTicks(true);
		
		sobSlider.setMajorTickSpacing(1);
		sobSlider.setMinorTickSpacing(1);
		sobSlider.setPaintTicks(true);
		sobSlider.setPaintLabels(true);
		sobSlider.setSnapToTicks(true);
		
		
		//button listeners
		submit.addActionListener(new SubmitListener(contentPane));
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
		
	}
	
	// TODO test method
	public int getValue(){
		return painSlider.getValue();
	}
	
	
	//TODO rest of this method implementation
	public class SubmitListener implements ActionListener{
		Container contentPane;
		public SubmitListener(Container contenetPane){
			this.contentPane = contentPane;
		}
		public void actionPerformed(ActionEvent arg0){
			int painValue = painSlider.getValue();
			int tirednessValue = tirednessSlider.getValue();
			int nauseaValue = nauseaSlider.getValue();
			int depressionValue = depressionSlider.getValue();
			int anxietyValue = anxietySlider.getValue();
			int drowsinessValue = drowsinessSlider.getValue();
			int appetiteValue = appetiteSlider.getValue();
			int wellbeingValue = wellbeingSlider.getValue();
			int sobValue = sobSlider.getValue();
			String email = "";
			
			PrintStream console = System.out;			
			File f = Database.download("patients.csv", console);
			// TODO what if the patients.csv file does not exist? We need a check if it exists (if(.exists and !.isDirectory)
			
			// TODO So what this should do is:
			//		1. Grab their email address
			//		2. create/check a csv file of their email address
			//			a. if the file exists, append (including a date)
			//			b. if it does not, create the header line and append (including a date)
			//		3. upload that file
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