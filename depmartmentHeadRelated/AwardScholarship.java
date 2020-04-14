package departmentHeadRelated;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

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
import javax.swing.ImageIcon;

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
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.FontFormatException;

import objects.*;

public class AwardScholarship extends JPanel {

	// Instance variables used in the class
	private List <String> scholarships;
	private List <String> names;
	private JComboBox scholarshipBox;
	private JComboBox nameBox;
	private String selectedScholarship;
	private String enteredRecipientName;
	private Font headerFont;
	private Font labelFont;
	private JLabel background_1;
	private JLabel background_2;

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

		// Create specific colors to be used in text and buttons
		Color gold = new Color(255, 207, 8);
		Color myRed = new Color(227, 37, 37);
		Color myGreen = new Color(61, 222, 29);

		// Try catch to load in custom font
		try {
			headerFont = Font.createFont(Font.TRUETYPE_FONT, new File("Bebas.ttf")).deriveFont(40f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("Bebas.ttf")));
		} catch (FontFormatException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

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

		// Create an empty array list for faculties
		scholarships = new ArrayList <String>();

		// Create an empty array list for names
		names = new ArrayList <String>();

		// Header of the system name
		JLabel header = new JLabel("Award Scholarships");
		header.setBounds(screenWidth/4 - screenWidth/18, screenHeight/25, screenWidth/3, screenHeight/25);
		header.setForeground(myRed);
		header.setFont(headerFont);
		add(header);

		// Label for the scholarship name drop down menu
		JLabel scholarshipLabel = new JLabel("Scholarship Name:");
		scholarshipLabel.setForeground(gold);
		scholarshipLabel.setBounds(screenWidth/4 - screenWidth/7 - screenWidth/45, screenHeight/7, screenWidth/7, screenHeight/35);
		scholarshipLabel.setFont(labelFont);
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
		scholarshipBox.setFont(labelFont);
		add(scholarshipBox);

		// Label for the recipient name text field
		JLabel recipientLabel = new JLabel("Recipient Name:");
		recipientLabel.setForeground(gold);
		recipientLabel.setBounds(screenWidth/4 - screenWidth/7 - screenWidth/100, screenHeight/7 + 2 * screenHeight/30, screenWidth/7, screenHeight/35);
		recipientLabel.setFont(labelFont);
		add(recipientLabel);

		// Drop down menu that will be later filled with the names of students associated with scholarships
        nameBox = new JComboBox();
		nameBox.setBounds(screenWidth/4 - screenWidth/14, screenHeight/7 + 2 * screenHeight/30, screenWidth/7, screenHeight/35);
		nameBox.setFont(labelFont);
		add(nameBox);

		// Message to be displayed if successfully awarding a scholarship
		JLabel successfulAdd = new JLabel("Successfully awarded the scholarship");
		successfulAdd.setForeground(myGreen);
		successfulAdd.setBounds(screenWidth/4 - screenWidth/12, screenHeight/7 + 4*screenHeight/33, screenWidth/5, screenHeight/35);
		successfulAdd.setFont(labelFont);
		add(successfulAdd);
		successfulAdd.setVisible(false);

		// Error message for not choosing a scholarship
		JLabel invalidTextField = new JLabel("Please Choose A Scholarship");
		invalidTextField.setForeground(myRed);
		invalidTextField.setBounds(screenWidth/4 + screenWidth/13, screenHeight/7, screenWidth/7, screenHeight/35);
		invalidTextField.setFont(labelFont);
		add(invalidTextField);
		invalidTextField.setVisible(false);

		// Button that loads the information associated with the scholarship selected
		JButton btnLoad = new JButton("Load");
		btnLoad.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Integer errorCount = 0;

				// If else statement to determine whether a scholarship has been chosen
				if (scholarshipBox.getSelectedIndex() != 0) {
					invalidTextField.setVisible(false);
					// Save the selected scholarship to string
					selectedScholarship = (String)scholarshipBox.getSelectedItem();
				} else {
					// error message displayed
					invalidTextField.setVisible(true);
					errorCount++;
				}	

				// If no errors have occured, try to read from JSON
				if (errorCount == 0) {
					// Initialize a JSONParser to get the data from the JSON file
					JSONParser parser3 = new JSONParser();

					// Try-catch statement to open the JSON file and add the scholarship information to its respective place in the menu
					try (Reader reader3 = new FileReader("scholarshipRequests.json")) {

						// Create a JSONObject out of the parsed JSON file
						JSONObject jsonObject3 = (JSONObject) parser3.parse(reader3);

						// Obtain the array that contains the label given from the selected scholarship
						JSONArray scholarshipRequestsArrayJSON3 = (JSONArray) jsonObject3.get(selectedScholarship);

						// Loop through the JSONArray, and each bit of information as a selected item in its respective place in the menu
						Iterator<String> iterator3 = scholarshipRequestsArrayJSON3.iterator();
						// Iterates through the array putting each element in a place on the menu
						while (iterator3.hasNext()) {
							names.add(iterator3.next());
						}
						
						DefaultComboBoxModel temp = new DefaultComboBoxModel(names.toArray());
						nameBox.setModel(temp);

						// Close the reader
						reader3.close();

					// Exceptions to be thrown if necessary
					} catch (IOException h) {
						h.printStackTrace();
					} catch (ParseException h) {
						h.printStackTrace();
					}

				// If there are errors that are run into, make the button useless and display those messages
				} else {

				}
				
			}
		});
		btnLoad.setFont(labelFont);
		btnLoad.setBackground(gold);
		btnLoad.setBounds(screenWidth/4 - screenWidth/14, screenHeight/7 + screenHeight/30, screenWidth/7, screenHeight/35);
		add(btnLoad);

		
		// Create a button that takes the information given by the user to write to the JSON file containing information of the scholarship
		JButton btnCreate = new JButton("Award");
		btnCreate.addMouseListener(new MouseAdapter() {
			@Override
			// On a mouse click, try to perform the following actions
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
				
				// If no errors have occured, try saving this information to the JSON files
				if (errorCount == 0){

					enteredRecipientName = (String)nameBox.getSelectedItem();

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
		btnCreate.setFont(labelFont);
		btnCreate.setBackground(gold);;
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
		btnCancel.setFont(labelFont);
		btnCancel.setBackground(gold);
		btnCancel.setBounds(screenWidth/4 - screenWidth/15 + 2*screenWidth/30 + screenWidth/200, screenHeight/7 + 5*screenHeight/30, screenWidth/20, screenHeight/33);
		add(btnCancel);

		/**
		 * PHOTOS
		 */

		// Loads in the image of the UofC logo and sets it to fit the specific location on the GUI
		ImageIcon img1 = new ImageIcon("logo.png");
		Image image = img1.getImage();
		Image newimg1 = image.getScaledInstance(75, 75, Image.SCALE_SMOOTH);
		img1 = new ImageIcon(newimg1);
		background_2 = new JLabel("",img1,SwingConstants.LEFT);
		background_2.setVerticalAlignment(SwingConstants.TOP);
		background_2.setBounds(screenWidth/4 - screenWidth/10, screenHeight/35, 300, 300);
		background_2.setVisible(true);
		add(background_2);

		// Loads in the background image
		ImageIcon img = new ImageIcon("red.jpg");
		background_1 = new JLabel("",img,SwingConstants.LEFT);
		background_1.setVerticalAlignment(SwingConstants.TOP);
		background_1.setBounds(0, 0, screenWidth, screenHeight);
		background_1.setVisible(true);
		add(background_1);

	}
}
