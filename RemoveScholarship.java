import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import org.json.simple.parser.JSONParser;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.Color;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException; 
import java.io.*;

public class RemoveScholarship extends JPanel {

	// Variable instance for the text field that takes in the scholarship name to be removed
	private List <String> scholarships;
	private JComboBox scholarshipBox;
	private String selectedScholarship;

	private static final long serialVersionUID = 1L;
	
	/**
	 * Takes in the scholarship name given by the user and removes that scholarship
	 * This information gets stored in a local JSON file that can be called for further editing
	 * 
	 * @param frame
	 * @param user
	 * @author Robert McCurdy
	 */
	public RemoveScholarship(JFrame frame, Account user) {
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
		JLabel header = new JLabel("Remove Scholarships");
		header.setBounds(screenWidth/4 - screenWidth/11, screenHeight/25, screenWidth/3, screenHeight/25);
		header.setForeground(Color.RED);
		header.setFont(new Font("Arial", Font.PLAIN, screenHeight/30));
		add(header);

		// Label for the scholarship drop down
		JLabel termLabel = new JLabel("Scholarship Name:");
		termLabel.setBounds(screenWidth/4 - screenWidth/7 - screenWidth/100, screenHeight/7, screenWidth/7, screenHeight/35);
		termLabel.setFont(labelFontSize);
		add(termLabel);

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

		// Message to be displayed when successfully removing a scholarship
		JLabel successfulAdd = new JLabel("Successfully removed the scholarship");
		successfulAdd.setForeground(Color.green);
		successfulAdd.setBounds(screenWidth/4 - screenWidth/13, screenHeight/7 + 2*screenHeight/37, screenWidth/6, screenHeight/35);
		successfulAdd.setFont(labelFontSize);
		add(successfulAdd);
		successfulAdd.setVisible(false);

		// Error message for not choosing a scholarship from the drop down
		JLabel invalidTextField = new JLabel("Please Choose A Scholarship");
		invalidTextField.setForeground(Color.RED);
		invalidTextField.setBounds(screenWidth/4 + screenWidth/13, screenHeight/7, screenWidth/7, screenHeight/35);
		invalidTextField.setFont(labelFontSize);
		add(invalidTextField);
		invalidTextField.setVisible(false);
		
		// Button that initiates the removing of the scholarship from the JSON file
		JButton btnCreate = new JButton("Remove");
		btnCreate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Keeps track of the number of errors to occur when trying to add a new scholarship
				Integer errorCount = 0;

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
					
				// If no errors have occured, try to remove this scholarship from the JSON
				if (errorCount == 0){

					// Initialize a JSONParser to get the data from the JSON file
					JSONParser parser5 = new JSONParser();

					// Try-catch to read and write to the JSON
					try (Reader reader5 = new FileReader("currentScholarships.json")){

						// Create a JSONObject out of the parsed JSON file and convert to string
						JSONObject jsonObject = (JSONObject) parser5.parse(reader5);
						String jsonObjectString = jsonObject.toString();

						// Locate where the end of the array of information is
						Integer indexOfBracket = jsonObjectString.indexOf(selectedScholarship) + 2;
						while (jsonObjectString.charAt(indexOfBracket) != ']') {
							indexOfBracket++;
						}

						// Create a string of the array to be removed
						String substring = jsonObjectString.substring(jsonObjectString.indexOf(selectedScholarship) - 3, indexOfBracket);

						// Remove this array by replacing it with "" in the string
						String newString = jsonObjectString.replace(substring, "");

						// Write this string to the JSON
						FileWriter fileWriter = new FileWriter("currentScholarships.json");
						fileWriter.write(newString);
						fileWriter.flush();

						// Display the success message for removing the scholarship
						successfulAdd.setVisible(true);

					// catch any exceptions if necessary
					} catch (IOException f) {
						f.printStackTrace();
					} catch (ParseException f) {
						f.printStackTrace();
					}

					// Initialize a JSONParser to get the data from the JSON file
					JSONParser parser6 = new JSONParser();

					// Try-catch to read and write to the JSOn
					try (Reader reader6 = new FileReader("scholarshipNames.json")){

						// Create a JSONObject out of the parsed JSON file
						JSONObject jsonObject = (JSONObject) parser6.parse(reader6);
						String jsonObjectString = jsonObject.toString();

						// Create a string of the scholarship to be removed
						String substring = ("\"" + selectedScholarship + "\",");

						// Find this string in the file and remvove it by replacing it with ""
						String newString = jsonObjectString.replace(substring, "");

						// Write this string to the JSON file
						FileWriter fileWriter = new FileWriter("scholarshipNames.json");
						fileWriter.write(newString);
						fileWriter.flush();

						// Display the success message for removing
						successfulAdd.setVisible(true);

					// catch any exceptions if necessary
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
		btnCreate.setBounds(screenWidth/4 - screenWidth/15 + screenWidth/60 - screenWidth/200, screenHeight/7 + 3*screenHeight/30, screenWidth/18, screenHeight/33);
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
		btnCancel.setBounds(screenWidth/4 - screenWidth/15 + 2*screenWidth/30 + screenWidth/200, screenHeight/7 + 3*screenHeight/30, screenWidth/20, screenHeight/33);
		add(btnCancel);

	}
}
