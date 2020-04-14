package professorRelated;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.awt.Color;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class AddReference extends JPanel {

	private List <String> names;	// Names of students who applied for said scholarship
	private Font labelFont;
	private JLabel background_1;
	private JLabel background_2;

	/**
	 * Creates the panel.
	 * @param frame is the main JFrame
	 * @param email is the signed in users email
	 * @param scholarshipName is the selected scholarships name
	 */
	public AddReference(JFrame frame, String email, String scholarshipName) {
		//Save the user's screen resolution to variables, used to format GUI correctly
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;

		Color gold = new Color(255, 207, 8);

		setLayout(null);
		
		// Try catch to load in custom font
		try {
			labelFont = Font.createFont(Font.TRUETYPE_FONT, new File("Roboto.ttf")).deriveFont(20f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("Roboto.ttf")));
		} catch (FontFormatException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		// Label Displaying the scholarship which was selected
		JLabel lblScholarship = new JLabel("Scholarship: "+scholarshipName);
		lblScholarship.setBounds(screenWidth/8, screenHeight/32, screenWidth/4, screenHeight/35);
		lblScholarship.setFont(labelFont);
		lblScholarship.setForeground(gold);
		add(lblScholarship);
		
		// Label for instructions
		JLabel lblID = new JLabel("Select a student that you wish to recommend:");
		lblID.setBounds(screenWidth/8, screenHeight/14, screenWidth/4, screenHeight/35);
		lblID.setFont(labelFont);
		lblID.setForeground(gold);
		add(lblID);
		
		names = new ArrayList <String>(); 	// ArrayList of student names
		JSONArray JSONArrayNames = null;	// JSONArray of student names
		
		// Initialize a JSONParser to get the data from the JSON file
		JSONParser parser3 = new JSONParser();
		// Try-catch statement to open the JSON file and add the scholarship information to its respective place in the menu
		try (Reader reader3 = new FileReader("scholarshipRequests.json")) {

			// Create a JSONObject out of the parsed JSON file
			JSONObject jsonObject3 = (JSONObject) parser3.parse(reader3);

			// Obtain the array that contains the label given from the selected scholarship
			JSONArrayNames = (JSONArray) jsonObject3.get(scholarshipName);

			// Loop through the JSONArray, and each bit of information as a selected item in its respective place in the menu
			Iterator<String> iterator3 = JSONArrayNames.iterator();
			
			names.add(null);
			
			// Iterates through the array putting each element in a place on the menu
			while (iterator3.hasNext()) {
				names.add(iterator3.next());
			}
			
			// Close the reader
			reader3.close();

		// Exceptions to be thrown if necessary
		} catch (IOException h) {
			h.printStackTrace();
		} catch (ParseException h) {
			h.printStackTrace();
		}
		JComboBox comboStudent = new JComboBox(names.toArray()); // ComboBox lets users choose a student who applied.
		comboStudent.setBackground(Color.WHITE);
		comboStudent.setBounds(screenWidth/8, screenHeight/10, screenWidth/8 + screenWidth/16, screenHeight/35);
		comboStudent.setFont(labelFont);
		add(comboStudent);
		
		// Label for error message
		JLabel noStudentSelected = new JLabel("No student selected");
		noStudentSelected.setFont(labelFont);
		noStudentSelected.setForeground(Color.RED);
		noStudentSelected.setBounds(screenWidth/4+screenWidth/14, screenHeight/10, screenWidth/8 + screenWidth/16, screenHeight/35);
		add(noStudentSelected);
		noStudentSelected.setVisible(false);
		
		// Label for text
		JLabel lblNewLabel_1 = new JLabel("Reference Letter:");
		lblNewLabel_1.setBounds(screenWidth/8, screenHeight/7, screenWidth/2, screenHeight/35);
		lblNewLabel_1.setFont(labelFont);
		lblNewLabel_1.setForeground(gold);
		add(lblNewLabel_1);
		
		// TextArea where user writes reference letters
		JTextArea referenceletter = new JTextArea(16, 58);
		referenceletter.setLineWrap(true);
		referenceletter.setFont(labelFont);
		JScrollPane bar = new JScrollPane(referenceletter);
		bar.setBounds(screenWidth/8, screenHeight/7 + screenHeight/26, screenWidth/4, screenHeight/4 - screenHeight/22);
		add(bar);
		
		// Label for error message
		JLabel noReferenceletter = new JLabel("No Reference Letter");
		noReferenceletter.setFont(labelFont);
		noReferenceletter.setForeground(Color.RED);
		noReferenceletter.setBounds(screenWidth/5 + screenWidth/128, screenHeight/7, screenWidth/8 + screenWidth/16, screenHeight/35);
		add(noReferenceletter);
		noReferenceletter.setVisible(false);
		
		// Button which saves reference letter
		JButton btnRecommendStudent = new JButton("Recommend Student");
		btnRecommendStudent.setBackground(gold);
		btnRecommendStudent.setFont(labelFont);
		btnRecommendStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (comboStudent.getSelectedItem() == null) // If no student selected
					noStudentSelected.setVisible(true);
				else 
					noStudentSelected.setVisible(false);

				if (referenceletter.getText().length() == 0) // If no reference letter
					noReferenceletter.setVisible(true);
				else
					noReferenceletter.setVisible(false);

				if (noStudentSelected.isVisible() == false && noReferenceletter.isVisible() == false) { // If no errors
					String filename = "./ReferenceLetters/"+scholarshipName+"-"+comboStudent.getSelectedItem() + ".txt";
					
					System.out.println("Want to save at: "+filename);
					
					try {
						BufferedWriter bw = new BufferedWriter(new FileWriter(filename, true));
						// Message printed to text
						String output = "--------------\nScholarship: "+scholarshipName+"\nStudent being recommended: "+comboStudent.getSelectedItem()+"\nProfessor: "+email+"\n\nReference Letter:\n\n"+referenceletter.getText()+"\n\n";
						bw.write(output);
						bw.newLine();
						bw.close();
					} 
					catch (Exception ex) 
					{
						ex.printStackTrace();
					}
					
					// Returns user to ProfessorMenu
					frame.setBounds((screenWidth/2 - screenWidth/4), (screenHeight/2 - screenHeight/4), screenWidth/2, screenHeight/2);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					ProfessorMenu panel = new ProfessorMenu(frame, email);
					frame.setContentPane(panel);
					frame.revalidate();
				}
			}
		});
		btnRecommendStudent.setBounds(screenWidth/4 - screenWidth/10, screenHeight/6 + 8*screenHeight/34 - screenHeight/128, screenWidth/10 - screenWidth/256 + screenWidth/32, screenHeight/30);
		add(btnRecommendStudent);

		// Cancel button
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {				
				
				// Returns user to ProfessorMenu
				frame.setBounds((screenWidth/2 - screenWidth/4), (screenHeight/2 - screenHeight/4), screenWidth/2, screenHeight/2);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				ProfessorMenu panel = new ProfessorMenu(frame, email);
				frame.setContentPane(panel);
				frame.revalidate();
			}
		});
		btnNewButton_1.setBackground(gold);
		btnNewButton_1.setFont(labelFont);
		btnNewButton_1.setBounds(screenWidth/4 + screenWidth/256 + screenWidth/32, screenHeight/6 + 8*screenHeight/34 - screenHeight/128, screenWidth/10 - screenWidth/32, screenHeight/30);
		add(btnNewButton_1);
		
		// Loads in the background image
		ImageIcon img = new ImageIcon("red.jpg");
		background_1 = new JLabel("",img,SwingConstants.LEFT);
		background_1.setVerticalAlignment(SwingConstants.TOP);
		background_1.setBounds(0, 0, screenWidth, screenHeight);
		background_1.setVisible(true);
		add(background_1);
	}
}
