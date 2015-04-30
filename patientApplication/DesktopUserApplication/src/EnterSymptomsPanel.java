import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;

public class EnterSymptomsPanel extends JPanel{
	
	//components for panel
	//JLabels for symptoms
	JLabel painLabel;
	JLabel tirednessLabel;
	JLabel nauseaLabel;
	JLabel depressionLabel;
	JLabel anxietyLabel;
	JLabel drowsinessLabel;
	JLabel appetiteLabel;
	JLabel wellbeingLabel;
	JLabel sobLabel;
	JLabel otherLabel;
	
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
	JSlider oSlider;
	
	JButton submitButton;
	JButton backButton;
	
	public EnterSymptomsPanel(Container contentPane){
		
		//setting to grid layout
		setLayout(new GridLayout(6,5));
		
		//setting up the components
		//labels
		painLabel = new JLabel("Pain");
		tirednessLabel = new JLabel("Tiredness");
		nauseaLabel = new JLabel("Nausea");
		depressionLabel = new JLabel("Depression");
		anxietyLabel = new JLabel("Anxiety");
		drowsinessLabel = new JLabel("Drowsiness");
		appetiteLabel = new JLabel("Appetite");
		wellbeingLabel = new JLabel("Wellbeing");
		sobLabel = new JLabel("Shortness of Breath");
		otherLabel = new JLabel("Other");
		
		submitButton = new JButton("Submit");
		backButton = new JButton("Back");
		
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
		JSlider oSlider = new JSlider(JSlider.HORIZONTAL,
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
		
		oSlider.setMajorTickSpacing(1);
		oSlider.setMinorTickSpacing(1);
		oSlider.setPaintTicks(true);
		oSlider.setPaintLabels(true);
		oSlider.setSnapToTicks(true);
		
		//button listeners
		submitButton.addActionListener(new SubmitListener(contentPane));
		backButton.addActionListener(new BackListener(contentPane));
		
		//setting alignment
		
		painLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		painSlider.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		tirednessLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		tirednessSlider.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		nauseaLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		nauseaSlider.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		depressionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		depressionSlider.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		anxietyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		anxietySlider.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		drowsinessLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		drowsinessSlider.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		appetiteLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		appetiteSlider.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		wellbeingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		wellbeingSlider.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		sobLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		sobSlider.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		otherLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		oSlider.setAlignmentX(Component.CENTER_ALIGNMENT);
		
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
		oSlider.setMaximumSize(new Dimension(200, 30));
		
		//adding components
		add(painLabel);
		add(painSlider);
		
		add(tirednessLabel);
		add(tirednessSlider);

		add(nauseaLabel);
		add(nauseaSlider);

		add(depressionLabel);
		add(depressionSlider);

		add(anxietyLabel);
		add(anxietySlider);

		add(drowsinessLabel);
		add(drowsinessSlider);

		add(appetiteLabel);
		add(appetiteSlider);

		add(wellbeingLabel);
		add(wellbeingSlider);

		add(sobLabel);
		add(sobSlider);
		
		add(otherLabel);
		add(oSlider);
		
		add(submitButton);
		add(backButton);
		
	}
	
	public class SubmitListener implements ActionListener{
		Container contentPane;
		public SubmitListener(Container contentPane){
			this.contentPane = contentPane;
		}
		public void actionPerformed(ActionEvent arg0){
			System.out.println(painSlider.toString());
			Integer painValue = painSlider.getValue();
			Integer tirednessValue = tirednessSlider.getValue();
			Integer nauseaValue = nauseaSlider.getValue();
			Integer depressionValue = depressionSlider.getValue();
			Integer anxietyValue = anxietySlider.getValue();
			Integer drowsinessValue = drowsinessSlider.getValue();
			Integer appetiteValue = appetiteSlider.getValue();
			Integer wellbeingValue = wellbeingSlider.getValue();
			Integer sobValue = sobSlider.getValue();
			Integer oValue = oSlider.getValue();
			
			
			// TODO what if the patients.csv file does not exist? We need a check if it exists (if(.exists and !.isDirectory)
			
			// TODO So what this should do is:
			//		1. Grab their email address
			//		2. create/check a csv file of their email address
			//			a. if the file exists, append (including a date)
			//			b. if it does not, create the header line and append (including a date)
			//		3. upload that file
			String pEmail = LoginPanel.getEmail();
			
			PrintStream console = System.out;
			File f = Database.download(pEmail + ".csv", console);
			
			if(f.exists() && !f.isDirectory())
			{
				FileWriter fw;
				try {
					fw = new FileWriter(pEmail + ".csv");
					fw.append(painValue.toString());
					fw.append(",");
					fw.append(tirednessValue.toString());
					fw.append(",");
					fw.append(nauseaValue.toString());
					fw.append(",");
					fw.append(depressionValue.toString());
					fw.append(",");
					fw.append(anxietyValue.toString());
					fw.append(",");
					fw.append(drowsinessValue.toString());
					fw.append(",");
					fw.append(appetiteValue.toString());
					fw.append(",");
					fw.append(wellbeingValue.toString());
					fw.append(",");
					fw.append(sobValue.toString());
					fw.append(",");
					fw.append(oValue.toString());
				
					fw.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			else // pEmail.csv does not exist
			{
				FileWriter fw;
				try {
					fw = new FileWriter(pEmail + ".csv");
					fw.append("pain");
					fw.append(",");
					fw.append("tiredness");
					fw.append(",");
					fw.append("nausea");
					fw.append(",");
					fw.append("depression");
					fw.append(",");
					fw.append("anxiety");
					fw.append(",");
					fw.append("drowsiness");
					fw.append(",");
					fw.append("appetite");
					fw.append(",");
					fw.append("wellbeing");
					fw.append(",");
					fw.append("shortnessOfBreath");
					fw.append(",");
					fw.append("other");
					
					fw.append("\n");
					fw.append(painValue.toString());
					fw.append(",");
					fw.append(tirednessValue.toString());
					fw.append(",");
					fw.append(nauseaValue.toString());
					fw.append(",");
					fw.append(depressionValue.toString());
					fw.append(",");
					fw.append(anxietyValue.toString());
					fw.append(",");
					fw.append(drowsinessValue.toString());
					fw.append(",");
					fw.append(appetiteValue.toString());
					fw.append(",");
					fw.append(wellbeingValue.toString());
					fw.append(",");
					fw.append(sobValue.toString());
					fw.append(",");
					fw.append(oValue.toString());
					
					fw.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			
			Database.upload(pEmail + ".csv", new File(pEmail + ".csv"));
			
			contentPane.removeAll();
			contentPane.add(new LoginPanel(contentPane));
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
			contentPane.removeAll();
			contentPane.add(new MainMenuPanel(contentPane));
			contentPane.invalidate();
			contentPane.validate();
			
		}
	}

}