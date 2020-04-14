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


public class AddScholarship extends JPanel {

	// List of instance variables used within the class
	private JTextField scholarshipName;

	private List <String> faculties;
	private JComboBox facultyBox;

	private List <String> semesters;
	private JComboBox semesterBox;

	private JTextField dollarAmount;

	private List <String> months;
	private JComboBox monthBox;

	private List <String> days;
	private JComboBox dayBox;

	private List <String> years;
	private JComboBox yearBox;

	private String selectedFaculty;
	private String selectedTerm;
	private String selectedYear;
	private String selectedMonth;
	private String selectedDay;

	private Font headerFont;
	private Font labelFont;
	private JLabel background_1;
	private JLabel background_2;

	private static final long serialVersionUID = 1L;

	/**
	 * Takes in the information given by the user and creates a new scholarship
	 * This information gets stored in a local JSON file that can be called for further editing
	 * 
	 * @param frame
	 * @param user
	 * @author Robert McCurdy
	 */
	public AddScholarship(JFrame frame, Account user) {
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
		faculties = new ArrayList <String>();

		// Create an empty array list for semesters
		semesters = new ArrayList <String>();

		// Create an empty array list for months
		months = new ArrayList <String>();

		// Create an empty array list for days
		days = new ArrayList <String>();

		// Create an empty array list for years
		years = new ArrayList <String>();

		// Error message for an invalid integer
		JLabel invalidINT = new JLabel("Please enter an integer");
		invalidINT.setForeground(myRed);
		invalidINT.setBounds(screenWidth/4 + screenWidth/13, screenHeight/7 + 3*screenHeight/30, screenWidth/6, screenHeight/35);
		invalidINT.setFont(labelFont);
		add(invalidINT);
		invalidINT.setVisible(false);

		// Error message for an invalid scholarship name
		JLabel invalidScholarshipName = new JLabel("Invalid Scholarship Name");
		invalidScholarshipName.setForeground(myRed);
		invalidScholarshipName.setBounds(screenWidth/4 + screenWidth/13, screenHeight/7, screenWidth/7, screenHeight/35);
		invalidScholarshipName.setFont(labelFont);
		add(invalidScholarshipName);
		invalidScholarshipName.setVisible(false);

		// Error message for an invalid faculty
		JLabel invalidFaculty = new JLabel("Please select a faculty");
		invalidFaculty.setForeground(myRed);
		invalidFaculty.setBounds(screenWidth/4 + screenWidth/13, screenHeight/7 + screenHeight/30, screenWidth/7, screenHeight/35);
		invalidFaculty.setFont(labelFont);
		add(invalidFaculty);
		invalidFaculty.setVisible(false);

		// Error message for an invalid term
		JLabel invalidTerm = new JLabel("Please select a term");
		invalidTerm.setForeground(myRed);
		invalidTerm.setBounds(screenWidth/4 + screenWidth/13, screenHeight/7 + 2*screenHeight/30, screenWidth/7, screenHeight/35);
		invalidTerm.setFont(labelFont);
		add(invalidTerm);
		invalidTerm.setVisible(false);

		// Error message for an invalid date
		JLabel invalidDate = new JLabel("Invalid Date");
		invalidDate.setForeground(myRed);
		invalidDate.setBounds(screenWidth/4 + screenWidth/13, screenHeight/7 + 4*screenHeight/30, screenWidth/7, screenHeight/35);
		invalidDate.setFont(labelFont);
		add(invalidDate);
		invalidDate.setVisible(false);

		// Message to display when successfully adding a scholarship
		JLabel successfulAdd = new JLabel("Successfully added the scholarship");
		successfulAdd.setForeground(myGreen);
		successfulAdd.setBounds(screenWidth/4 - screenWidth/13, screenHeight/7 + 6*screenHeight/33, screenWidth/6, screenHeight/35);
		successfulAdd.setFont(labelFont);
		add(successfulAdd);
		successfulAdd.setVisible(false);

		// Label for the menu name
		JLabel header = new JLabel("Add Scholarships");
		header.setBounds(screenWidth/4 - screenWidth/18, screenHeight/25, screenWidth/3, screenHeight/25);
		header.setForeground(myRed);
		header.setFont(headerFont);
		add(header);

		// Label for the scholarship name text field
		JLabel scholarshipNameLabel = new JLabel("Scholarship Name:");
		scholarshipNameLabel.setBounds(screenWidth/4 - screenWidth/6, screenHeight/7, screenWidth/7, screenHeight/35);
		scholarshipNameLabel.setForeground(gold);
		scholarshipNameLabel.setFont(labelFont);
		add(scholarshipNameLabel);

		// Text field for the scholarship name
		scholarshipName = new JTextField();
		scholarshipName.setFont(labelFont);
		scholarshipName.setBounds(screenWidth/4 - screenWidth/14, screenHeight/7, screenWidth/7, screenHeight/35);
		scholarshipName.setFont(labelFont);
		add(scholarshipName);

		// Label for the faculty drop down
		JLabel facultyName = new JLabel("Faculty:");
		facultyName.setForeground(gold);
		facultyName.setBounds(screenWidth/4 - screenWidth/9 - screenWidth/200, screenHeight/7 + screenHeight/30, screenWidth/7, screenHeight/35);
		facultyName.setFont(labelFont);
		add(facultyName);

		// Initialize a JSONParser to get the data from the JSON file
		JSONParser parser = new JSONParser();

		// Try-catch statement to open the JSON file and add the faculty names to the drop down list
        try (Reader reader = new FileReader("data.json")) {

			// Create a JSONObject out of the parsed JSON file
            JSONObject jsonObject = (JSONObject) parser.parse(reader);

			// Obtain the array that contains the label "faculties"
            JSONArray facultyArrayJSON = (JSONArray) jsonObject.get("faculties");

			// Loop through the JSONArray, and add those faculty names to the List
            Iterator<String> iterator = facultyArrayJSON.iterator();
            while (iterator.hasNext()) {
                faculties.add(iterator.next());
			}
			
			// Close the reader
			reader.close();

		// Exceptions to be thrown if necessary
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
		// Create a JComboBox to display the faculty information in a drop down menu
		DefaultComboBoxModel modelTemp = new DefaultComboBoxModel(faculties.toArray());
        facultyBox = new JComboBox(modelTemp);
		facultyBox.setBounds(screenWidth/4 - screenWidth/14, screenHeight/7 + screenHeight/30, screenWidth/7, screenHeight/35);
		facultyBox.setFont(labelFont);
		add(facultyBox);

		//Label for the term drop down
		JLabel termLabel = new JLabel("Term:");
		termLabel.setBounds(screenWidth/4 - screenWidth/9 + screenWidth/300, screenHeight/7 + 2*screenHeight/30, screenWidth/7, screenHeight/35);
		termLabel.setFont(labelFont);
		termLabel.setForeground(gold);
		add(termLabel);

		// Initialize a JSONParser to get the data from the JSON file
		JSONParser parser1 = new JSONParser();

		// Try-catch statement to open the JSON file and add the semesters to the drop down list
        try (Reader reader1 = new FileReader("data.json")) {

			// Create a JSONObject out of the parsed JSON file
            JSONObject jsonObject1 = (JSONObject) parser1.parse(reader1);

			// Obtain the array that contains the label "semesters"
            JSONArray semesterArrayJSON1 = (JSONArray) jsonObject1.get("semesters");

			// Loop through the JSONArray, and add those semesters to the List
            Iterator<String> iterator1 = semesterArrayJSON1.iterator();
            while (iterator1.hasNext()) {
                semesters.add(iterator1.next());
			}
			
			// Close the reader
			reader1.close();

		// Exceptions to be thrown if necessary
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
		// Create a JComboBox to display the semester information in a drop down menu
		DefaultComboBoxModel semesterBoxModel = new DefaultComboBoxModel(semesters.toArray());
        semesterBox = new JComboBox(semesterBoxModel);
		semesterBox.setBounds(screenWidth/4 - screenWidth/14, screenHeight/7 + 2*screenHeight/30, screenWidth/7, screenHeight/35);
		semesterBox.setFont(labelFont);
		add(semesterBox);

		// Label for the scholarship dollar amount
		JLabel dollarAmountLabel = new JLabel("$ Amount:");
		dollarAmountLabel.setBounds(screenWidth/4 - screenWidth/8 - screenWidth/400, screenHeight/7 + 3*screenHeight/30, screenWidth/7, screenHeight/35);
		dollarAmountLabel.setFont(labelFont);
		dollarAmountLabel.setForeground(gold);
		add(dollarAmountLabel);

		// Text field for the scholarship dollar amount
		dollarAmount = new JTextField();
		dollarAmount.setBounds(screenWidth/4 - screenWidth/14, screenHeight/7 + 3*screenHeight/30, screenWidth/7, screenHeight/35);
		dollarAmount.setFont(labelFont);
		add(dollarAmount);

		// Label for the deadline drop down
		JLabel deadlineLabel = new JLabel("Deadline:");
		deadlineLabel.setBounds(screenWidth/4 - screenWidth/9 - screenWidth/90, screenHeight/7 + 4*screenHeight/30, screenWidth/7, screenHeight/35);
		deadlineLabel.setFont(labelFont);
		deadlineLabel.setForeground(gold);
		add(deadlineLabel);

		// Initialize a JSONParser to get the data from the JSON file
		JSONParser parser2 = new JSONParser();

		// Try-catch statement to open the JSON file and add the months to the drop down list
        try (Reader reader2 = new FileReader("data.json")) {

			// Create a JSONObject out of the parsed JSON file
            JSONObject jsonObject2 = (JSONObject) parser2.parse(reader2);

			// Obtain the array that contains the label "month"
            JSONArray yearArrayJSON2 = (JSONArray) jsonObject2.get("month");

			// Loop through the JSONArray, and add those months to the List
            Iterator<String> iterator2 = yearArrayJSON2.iterator();
            while (iterator2.hasNext()) {
                months.add(iterator2.next());
			}
			
			// Close the reader
			reader2.close();

		// Exceptions to be thrown if necessary
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
		// Create a JComboBox to display the month information in a drop down menu
		DefaultComboBoxModel monthBoxModel = new DefaultComboBoxModel(months.toArray());
        monthBox = new JComboBox(monthBoxModel);
		monthBox.setBounds(screenWidth/4 - screenWidth/14, screenHeight/7 + 4*screenHeight/30, screenWidth/15, screenHeight/35);
		monthBox.setFont(labelFont);
		add(monthBox);

		// Initialize a JSONParser to get the data from the JSON file
		JSONParser parser3 = new JSONParser();

		// Try-catch statement to open the JSON file and add the days to the drop down list
        try (Reader reader3 = new FileReader("data.json")) {

			// Create a JSONObject out of the parsed JSON file
            JSONObject jsonObject3 = (JSONObject) parser3.parse(reader3);

			// Obtain the array that contains the label "day"
            JSONArray yearArrayJSON3 = (JSONArray) jsonObject3.get("day");

			// Loop through the JSONArray, and add those days to the List
            Iterator<String> iterator3 = yearArrayJSON3.iterator();
            while (iterator3.hasNext()) {
                days.add(iterator3.next());
			}
			
			// Close the reader
			reader3.close();

		// Exceptions to be thrown if necessary
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
		// Create a JComboBox to display the day information in a drop down menu
		DefaultComboBoxModel dayBoxModel = new DefaultComboBoxModel(days.toArray());
        dayBox = new JComboBox(dayBoxModel);
		dayBox.setBounds(screenWidth/4 - screenWidth/14 + screenWidth/17 + screenWidth/85 + screenWidth/260, screenHeight/7 + 4*screenHeight/30, screenWidth/40, screenHeight/35);
		dayBox.setFont(labelFont);
		add(dayBox);

		// Initialize a JSONParser to get the data from the JSON file
		JSONParser parser4 = new JSONParser();

		// Try-catch statement to open the JSON file and add the school years to the drop down list
        try (Reader reader4 = new FileReader("data.json")) {

			// Create a JSONObject out of the parsed JSON file
            JSONObject jsonObject4 = (JSONObject) parser4.parse(reader4);

			// Obtain the array that contains the label "year"
            JSONArray yearArrayJSON4 = (JSONArray) jsonObject4.get("year");

			// Loop through the JSONArray, and add those school years to the List
            Iterator<String> iterator4 = yearArrayJSON4.iterator();
            while (iterator4.hasNext()) {
                years.add(iterator4.next());
			}
			
			// Close the reader
			reader4.close();

		// Exceptions to be thrown if necessary
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
		// Create a JComboBox to display the school year information in a drop down menu
		DefaultComboBoxModel yearBoxModel = new DefaultComboBoxModel(years.toArray());
        yearBox = new JComboBox(yearBoxModel);
		yearBox.setBounds(screenWidth/4 - screenWidth/14 + screenWidth/17 + screenWidth/21, screenHeight/7 + 4*screenHeight/30, screenWidth/27, screenHeight/35);
		yearBox.setFont(labelFont);
		add(yearBox);
		
		// Button that initiates the adding of the new scholarship information to the JSON file with the information
		JButton btnCreate = new JButton("Add");
		btnCreate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Keeps track of the number of errors to occur when trying to add a new scholarship
				Integer errorCount = 0;

				// Reset the success message to not display, in case errors are run into
				successfulAdd.setVisible(false);

				// Set strings to be the entered information given in the text fields
				String enteredScholarshipName = scholarshipName.getText();
				String enteredDollarAmount = dollarAmount.getText();

				// Try catch statement to determine whether we can convert the dollar amount string to an int and then return the correct message
				try {
					// Tries to convert the given string to an integer, catches exception if not able to
					Integer enteredDollar = Integer.parseInt(enteredDollarAmount.trim());
					// Returns an invalid ID error if it doesn't follow criteria for an ID
					if (enteredDollarAmount.contains(".")) {
						invalidINT.setVisible(true);
						errorCount++;
					// Returns a valid ID if it follows the criteria for an ID
					} else {
						invalidINT.setVisible(false);
					} 
				} catch(NumberFormatException nfe) {
					// An integer was not given as an ID
					invalidINT.setVisible(true);
					errorCount++;
				}

				// If statement to determine if the scholarship name only contains letters
				if (enteredScholarshipName.chars().allMatch(Character::isLetter) || enteredScholarshipName.contains(" ")){
					invalidScholarshipName.setVisible(false);
				} else {
					invalidScholarshipName.setVisible(true);
					errorCount++;
				}

				// If statement to determine if the scholarship name is blank
				if (enteredScholarshipName.isEmpty()) {
					invalidScholarshipName.setVisible(true);
					errorCount++;
				} else {
					invalidScholarshipName.setVisible(false);
				}

				// If else statement to determine whether a faculty has been chosen
				if (facultyBox.getSelectedIndex() != 0) {
					invalidFaculty.setVisible(false);
					// Save the selected faculty to string
					selectedFaculty = (String)facultyBox.getSelectedItem();
				} else {
					// error message displayed
					invalidFaculty.setVisible(true);
					errorCount++;
				}

				// If else statement to determine whether a semester has been chosen
				if (semesterBox.getSelectedIndex() != 0) {
					invalidTerm.setVisible(false);
					// Save the selected semester to string
					selectedTerm = (String)semesterBox.getSelectedItem();
				} else {
					// error message displayed
					invalidTerm.setVisible(true);
					errorCount++;
				}

				// If else statement to determine whether a day has been chosen
				if (dayBox.getSelectedIndex() != 0) {
					invalidDate.setVisible(false);
					// Save the selected day to string
					selectedDay = (String)dayBox.getSelectedItem();
				} else {
					// error message displayed
					invalidDate.setVisible(true);
					errorCount++;
				}

				// If else statement to determine whether a month has been chosen
				if (monthBox.getSelectedIndex() != 0) {
					invalidDate.setVisible(false);
					// Save the selected month to string
					selectedMonth = (String)monthBox.getSelectedItem();
				} else {
					// error message displayed
					invalidDate.setVisible(true);
					errorCount++;
				}

				// If else statement to determine whether a year has been chosen
				if (yearBox.getSelectedIndex() != 0) {
					invalidDate.setVisible(false);
					// Save the selected year to string
					selectedYear = (String)yearBox.getSelectedItem();
				} else {
					// error message displayed
					invalidDate.setVisible(true);
					errorCount++;
				}

				// If no errors have been run into, try to add the scholarship to the JSON list
				if (errorCount == 0){

					// Initialize a JSONParser to get the data from the JSON file
					JSONParser parser5 = new JSONParser();

					// Initialize a JSONObject to create an object with the given information
					JSONObject obj = new JSONObject();

					try (Reader reader5 = new FileReader("currentScholarships.json")){
						// Initialize a JSONArray to add the given information to
						JSONArray scholarshipInfo = new JSONArray();
						// Add all of the information the user has provided to the JSONArray
						scholarshipInfo.add(selectedFaculty);
						scholarshipInfo.add(selectedTerm);
						scholarshipInfo.add(enteredDollarAmount);
						scholarshipInfo.add(selectedMonth);
						scholarshipInfo.add(selectedDay);
						scholarshipInfo.add(selectedYear);
						// Set the recipient of the scholarship to null, until later updated
						scholarshipInfo.add("null");

						// Put this newly created JSONArray into the object with a given name
						obj.put(enteredScholarshipName, scholarshipInfo);

						// Convert this object to a string in order to write to the JSON file
						String objString = obj.toString();
						String strippedVersion = objString.substring(1, objString.length() - 1);
						String readyForAppendVersion = ", " + strippedVersion;

						// Take in the read JSON file and convert this to a string
						JSONObject jsonObject = (JSONObject) parser5.parse(reader5);
						String jsonObjectString = jsonObject.toString();

						// Create a new string that adds the new scholarship to the JSON file
						String newString = jsonObjectString.substring(0, jsonObjectString.length() - 1) + readyForAppendVersion + "}";

						// Write this new string to the JSON file
						FileWriter fileWriter = new FileWriter("currentScholarships.json");
						fileWriter.write(newString);
						fileWriter.flush();

						// Display the success message
						successfulAdd.setVisible(true);

					// catch any exceptions if necessary
					} catch (IOException f) {
						f.printStackTrace();
					} catch (ParseException f) {
						f.printStackTrace();
					}

					// Initialize a JSONParser to get the data from the JSON file
					JSONParser parser6 = new JSONParser();

					// Try-catch in order to update the JSON with the list of scholarship names
					try (Reader reader6 = new FileReader("scholarshipNames.json")){

						// Create a string from the given JSONObject
						JSONObject jsonObject = (JSONObject) parser6.parse(reader6);
						String jsonObjectString = jsonObject.toString();

						// Create a string that will be used to add this scholarship name to the JSON
						String substring = (",\"" + enteredScholarshipName + "\"");

						// Create a new string that has the new scholasrship name included in the array
						String newString = jsonObjectString.substring(0, jsonObjectString.length() - 2) + substring + "]}";

						// Write this string to the JSON file containing scholarship names
						FileWriter fileWriter = new FileWriter("scholarshipNames.json");
						fileWriter.write(newString);
						fileWriter.flush();

						// Display the success message
						successfulAdd.setVisible(true);

					// catch any errors if necessary
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
		btnCreate.setBackground(gold);
		btnCreate.setBounds(screenWidth/4 - screenWidth/15 + screenWidth/60 - screenWidth/200, screenHeight/7 + 7*screenHeight/30, screenWidth/20, screenHeight/33);
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
		btnCancel.setBounds(screenWidth/4 - screenWidth/15 + 2*screenWidth/30 + screenWidth/200, screenHeight/7 + 7*screenHeight/30, screenWidth/20, screenHeight/33);
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
