import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import org.json.simple.parser.JSONParser;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;  
import java.io.*;

public class AwardScholarship extends JPanel {

	// Instance variables used in the class
	private List <String> scholarships;
	private JComboBox scholarshipBox;
	private String selectedScholarship;
	private JTextField recipientName;

	private static final long serialVersionUID = 1L;
	
	/**
	 * Creates a menu to allow the user to award a scholarship to a student
	 * This allows the user to choose a scholarship and enter a students name
	 * 
	 * @param user
	 * @param frame 
	 * @author Robert McCurdy
	 */
	public AwardScholarship(JFrame frame, Account user) {
		// Save the user's screen resolution to variables, used to format GUI correctly
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		
		// Initialized the layout to have no perameters
		setLayout(null);

		// Create an empty array list for faculties
		scholarships = new ArrayList <String>();

		// Font size for remaining labels
		Font labelFontSize = new Font("Arial", Font.PLAIN, screenHeight/60);

		// Header of the system name
		JLabel header = new JLabel("Award Scholarships");
		header.setBounds(screenWidth/4 - screenWidth/11, screenHeight/25, screenWidth/3, screenHeight/25);
		header.setForeground(Color.RED);
		header.setFont(new Font("Arial", Font.PLAIN, screenHeight/30));
		add(header);

		// Label for the scholarship name drop down menu
		JLabel scholarshipLabel = new JLabel("Scholarship Name:");
		scholarshipLabel.setBounds(screenWidth/4 - screenWidth/7 - screenWidth/100, screenHeight/7, screenWidth/7, screenHeight/35);
		scholarshipLabel.setFont(labelFontSize);
		add(scholarshipLabel);

		// Initialize a JSONParser to get the data from the JSON file
		JSONParser parser7 = new JSONParser();

		// Try-catch statement to open the JSON file and add the scholarship names to the drop down list
        try (Reader reader7 = new FileReader("scholarshipNames.json")) {

			// Create a JSONObject out of the parsed JSON file
            JSONObject jsonObject = (JSONObject) parser7.parse(reader7);

			// Obtain the array that contains the label "scholarships"
            JSONArray scholarshipArrayJSON = (JSONArray) jsonObject.get("scholarships");

			// Loop through the JSONArray, and add those scholarship names to the List
            Iterator<String> iterator7 = scholarshipArrayJSON.iterator();
            while (iterator7.hasNext()) {
                scholarships.add(iterator7.next());
			}
			
			// Close the reader
			reader7.close();

		// Exceptions to be thrown if necessary
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
		// Create a JComboBox to display the scholarship information in a drop down menu
		DefaultComboBoxModel modelTemp = new DefaultComboBoxModel(scholarships.toArray());
        scholarshipBox = new JComboBox(modelTemp);
		scholarshipBox.setBounds(screenWidth/4 - screenWidth/14, screenHeight/7, screenWidth/7, screenHeight/35);
		scholarshipBox.setFont(labelFontSize);
		add(scholarshipBox);

		// Label for the recipient name text field
		JLabel recipientLabel = new JLabel("Recipient Name:");
		recipientLabel.setBounds(screenWidth/4 - screenWidth/7 - screenWidth/100, screenHeight/7 + 2 * screenHeight/30, screenWidth/7, screenHeight/35);
		recipientLabel.setFont(labelFontSize);
		add(recipientLabel);

		// Text field for the recipient name
		recipientName = new JTextField();
		recipientName.setBounds(screenWidth/4 - screenWidth/14, screenHeight/7 + 2 * screenHeight/30, screenWidth/7, screenHeight/35);
		recipientName.setFont(labelFontSize);
		add(recipientName);

		// Error message for an invalid name
		JLabel invalidRecipientName = new JLabel("Invalid Name");
		invalidRecipientName.setForeground(Color.RED);
		invalidRecipientName.setBounds(screenWidth/4 + screenWidth/13, screenHeight/7 + 2 * screenHeight/30, screenWidth/7, screenHeight/35);
		invalidRecipientName.setFont(labelFontSize);
		add(invalidRecipientName);
		invalidRecipientName.setVisible(false);

		// Error message for no name given
		JLabel invalidRecipientField = new JLabel("Please Enter A Name");
		invalidRecipientField.setForeground(Color.RED);
		invalidRecipientField.setBounds(screenWidth/4 + screenWidth/13, screenHeight/7 + 2 * screenHeight/30, screenWidth/7, screenHeight/35);
		invalidRecipientField.setFont(labelFontSize);
		add(invalidRecipientField);
		invalidRecipientField.setVisible(false);

		// Message to be displayed if successfully awarding a scholarship
		JLabel successfulAdd = new JLabel("Successfully awarded the scholarship");
		successfulAdd.setForeground(Color.green);
		successfulAdd.setBounds(screenWidth/4 - screenWidth/13, screenHeight/7 + 4*screenHeight/33, screenWidth/6, screenHeight/35);
		successfulAdd.setFont(labelFontSize);
		add(successfulAdd);
		successfulAdd.setVisible(false);

		// Error message for not choosing a scholarship
		JLabel invalidTextField = new JLabel("Please Choose A Scholarship");
		invalidTextField.setForeground(Color.RED);
		invalidTextField.setBounds(screenWidth/4 + screenWidth/13, screenHeight/7, screenWidth/7, screenHeight/35);
		invalidTextField.setFont(labelFontSize);
		add(invalidTextField);
		invalidTextField.setVisible(false);
		
		// Create a button that takes the information given by the user to write to the JSON file containing information of the scholarship
		JButton btnCreate = new JButton("Award");
		btnCreate.addMouseListener(new MouseAdapter() {
			@Override
			// On a mouse click, try to perform the following actions
			public void mouseClicked(MouseEvent e) {
				// Keeps track of the number of errors to occur when trying to add a new scholarship
				Integer errorCount = 0;

				// Take in the text from the recipient name text field and save as a string
				String enteredRecipientName = recipientName.getText();

				// If else statement to determine whether a scholarship has been chosen
				if (scholarshipBox.getSelectedIndex() != 0) {
					invalidTextField.setVisible(false);
					//Save the selected scholarship to string
					selectedScholarship = (String)scholarshipBox.getSelectedItem();
				} else {
					// error message displayed
					invalidTextField.setVisible(true);
					errorCount++;
				}

				// If statement to determine if the name only contains letters
				if (enteredRecipientName.chars().allMatch(Character::isLetter) || enteredRecipientName.contains(" ")){
					invalidRecipientName.setVisible(false);
				} else {
					invalidRecipientName.setVisible(true);
					errorCount++;
				}

				// If statement to determine if the name is blank
				if (enteredRecipientName.isEmpty()) {
					invalidRecipientField.setVisible(true);
					errorCount++;
				} else {
					invalidRecipientField.setVisible(false);
				}
				
				// If no errors have occured, try saving this information to the JSON files
				if (errorCount == 0){

					// Initialize a JSONParser to get the data from the JSON file
					JSONParser parser5 = new JSONParser();

					// Try-catch to statement to try and write new information to the JSON file
					try (Reader reader5 = new FileReader("currentScholarships.json")){

						// Create a string out of the parsed JSON file
						JSONObject jsonObject = (JSONObject) parser5.parse(reader5);
						String jsonObjectString = jsonObject.toString();

						// Locate where we need to write this information for the given scholarship
						Integer indexOfBracket = jsonObjectString.indexOf(selectedScholarship) + 2;
						while (jsonObjectString.charAt(indexOfBracket) != ']') {
							indexOfBracket++;
						}

						// Create a new string that writes this information to JSON in the form of a string
						String newString = jsonObjectString.substring(0, indexOfBracket - 5) + enteredRecipientName + jsonObjectString.substring(indexOfBracket-1);

						// Write this string to the JSON file
						FileWriter fileWriter = new FileWriter("currentScholarships.json");
						fileWriter.write(newString);
						fileWriter.flush();

						successfulAdd.setVisible(true);

					// catch exceptions if necessary
					} catch (IOException f) {
						f.printStackTrace();
					} catch (ParseException f) {
						f.printStackTrace();
					}

				// If there are errors that are run into, make the button useless and display those messages
				} else {

				}

			}
		});
		btnCreate.setFont(labelFontSize);
		btnCreate.setBounds(screenWidth/4 - screenWidth/15 + screenWidth/60 - screenWidth/200, screenHeight/7 + 5*screenHeight/30, screenWidth/20, screenHeight/33);
		add(btnCreate);
		
		// Create a back button to take the user back to the main menu
		JButton btnCancel = new JButton("Back");
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			// On the click of this button, create a new instance of the frame with the DepartmentHeadMenu class
			public void mouseClicked(MouseEvent e) {
				frame.setBounds((screenWidth/2 - screenWidth/4), (screenHeight/2 - screenHeight/4), screenWidth/2, screenHeight/2);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				DepartmentHeadMenu dMenu = new DepartmentHeadMenu(frame, user);
				frame.setContentPane(dMenu);
				frame.revalidate();
			}
		});
		btnCancel.setFont(labelFontSize);
		btnCancel.setBounds(screenWidth/4 - screenWidth/15 + 2*screenWidth/30 + screenWidth/200, screenHeight/7 + 5*screenHeight/30, screenWidth/20, screenHeight/33);
		add(btnCancel);

	}
}
