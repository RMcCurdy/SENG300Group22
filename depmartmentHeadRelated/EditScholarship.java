package departmentHeadRelated;
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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import objects.*
;
public class EditScholarship extends JPanel {

	// List of instance variables used within the class
	private List <String> scholarships;
	private JComboBox scholarshipBox;
	private String selectedScholarship;

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
	 * Takes in the information given by the user and edits a pre-existing scholarship
	 * This information gets stored in a local JSON file that can be called for further editing
	 * 
	 * @param frame
	 * @param user
	 * @author Robert McCurdy
	 */
	public EditScholarship(JFrame frame, Account user) {
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

		// Error message for not choosing a scholarship
		JLabel invalidScholarshipName = new JLabel("Please Choose A Scholarship");
		invalidScholarshipName.setForeground(myRed);
		invalidScholarshipName.setBounds(screenWidth/4 + screenWidth/13, screenHeight/7 - screenHeight/33, screenWidth/7, screenHeight/35);
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

		// Message to be displayed when successfully editing a scholarship
		JLabel successfulAdd = new JLabel("Successfully edited the scholarship");
		successfulAdd.setForeground(myGreen);
		successfulAdd.setBounds(screenWidth/4 - screenWidth/12, screenHeight/7 + 6*screenHeight/33, screenWidth/6, screenHeight/35);
		successfulAdd.setFont(labelFont);
		add(successfulAdd);
		successfulAdd.setVisible(false);

		// Header of the system name
		JLabel header = new JLabel("Edit Scholarships");
		header.setBounds(screenWidth/4 - screenWidth/18, screenHeight/25, screenWidth/3, screenHeight/25);
		header.setForeground(myRed);
		header.setFont(headerFont);
		add(header);

		// Label for the scholarship name
		JLabel scholarshipNameLabel = new JLabel("Scholarship Name:");
		scholarshipNameLabel.setBounds(screenWidth/4 - screenWidth/7 - screenWidth/45, screenHeight/7 - screenHeight/33, screenWidth/7, screenHeight/35);
		scholarshipNameLabel.setFont(labelFont);
		scholarshipNameLabel.setForeground(gold);
		add(scholarshipNameLabel);

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
		scholarshipBox.setBounds(screenWidth/4 - screenWidth/14, screenHeight/7 - screenHeight/33, screenWidth/7, screenHeight/35);
		scholarshipBox.setFont(labelFont);
		add(scholarshipBox);

		// Button that loads the information associated with the scholarship selected
		JButton btnLoad = new JButton("Load");
		btnLoad.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Integer errorCount = 0;

				// If else statement to determine whether a scholarship has been chosen
				if (scholarshipBox.getSelectedIndex() != 0) {
					invalidScholarshipName.setVisible(false);
					// Save the selected scholarship to string
					selectedScholarship = (String)scholarshipBox.getSelectedItem();
				} else {
					// error message displayed
					invalidScholarshipName.setVisible(true);
					errorCount++;
				}	

				// If no errors have occured, try to read from JSON
				if (errorCount == 0) {
					// Initialize a JSONParser to get the data from the JSON file
					JSONParser parser3 = new JSONParser();

					// Try-catch statement to open the JSON file and add the scholarship information to its respective place in the menu
					try (Reader reader3 = new FileReader("currentScholarships.json")) {

						// Create a JSONObject out of the parsed JSON file
						JSONObject jsonObject3 = (JSONObject) parser3.parse(reader3);

						// Obtain the array that contains the label given from the selected scholarship
						JSONArray yearArrayJSON3 = (JSONArray) jsonObject3.get(scholarshipBox.getSelectedItem());

						// Loop through the JSONArray, and each bit of information as a selected item in its respective place in the menu
						Iterator<String> iterator3 = yearArrayJSON3.iterator();
						Integer elementNumber = 0;
						// Iterates through the array putting each element in a place on the menu
						while (iterator3.hasNext()) {
							if (elementNumber == 0){
								facultyBox.setSelectedItem(iterator3.next());
							}
							if (elementNumber == 1){
								semesterBox.setSelectedItem(iterator3.next());
							}
							if (elementNumber == 2){
								dollarAmount.setText(iterator3.next());
							}
							if (elementNumber == 3){
								monthBox.setSelectedItem(iterator3.next());
							}
							if (elementNumber == 4){
								dayBox.setSelectedItem(iterator3.next());
							}
							if (elementNumber == 5){
								yearBox.setSelectedItem(iterator3.next());
							}
							// To help iterate through each element
							elementNumber++;
						}
						
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
		btnLoad.setBounds(screenWidth/4 - screenWidth/14, screenHeight/7, screenWidth/7, screenHeight/35);
		add(btnLoad);

		// Label for the faculty drop down
		JLabel facultyName = new JLabel("Faculty:");
		facultyName.setBounds(screenWidth/4 - screenWidth/10 - screenWidth/80, screenHeight/7 + screenHeight/30, screenWidth/7, screenHeight/35);
		facultyName.setFont(labelFont);
		facultyName.setForeground(gold);
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
		DefaultComboBoxModel facultyBoxModel = new DefaultComboBoxModel(faculties.toArray());
        facultyBox = new JComboBox(facultyBoxModel);
		facultyBox.setBounds(screenWidth/4 - screenWidth/14, screenHeight/7 + screenHeight/30, screenWidth/7, screenHeight/35);
		facultyBox.setFont(labelFont);
		add(facultyBox);

		// Label for the term drop down menu
		JLabel termLabel = new JLabel("Term:");
		termLabel.setBounds(screenWidth/4 - screenWidth/7 + screenWidth/26, screenHeight/7 + 2*screenHeight/30, screenWidth/7, screenHeight/35);
		termLabel.setFont(labelFont);
		termLabel.setForeground(gold);
		add(termLabel);

		// Initialize a JSONParser to get the data from the JSON file
		JSONParser parser1 = new JSONParser();

		// Try-catch statement to open the JSON file and add the semesters/terms to the drop down list
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
		dollarAmountLabel.setBounds(screenWidth/4 - screenWidth/7 + screenWidth/40 - screenWidth/150, screenHeight/7 + 3*screenHeight/30, screenWidth/7, screenHeight/35);
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
		deadlineLabel.setBounds(screenWidth/4 - screenWidth/7 + screenWidth/40 - screenWidth/250, screenHeight/7 + 4*screenHeight/30, screenWidth/7, screenHeight/35);
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

		// Try-catch statement to open the JSON file and add the years to the drop down list
        try (Reader reader4 = new FileReader("data.json")) {

			// Create a JSONObject out of the parsed JSON file
            JSONObject jsonObject4 = (JSONObject) parser4.parse(reader4);

			// Obtain the array that contains the label "year"
            JSONArray yearArrayJSON4 = (JSONArray) jsonObject4.get("year");

			// Loop through the JSONArray, and add those years to the List
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
		// Create a JComboBox to display the year information in a drop down menu
		DefaultComboBoxModel yearBoxModel = new DefaultComboBoxModel(years.toArray());
        yearBox = new JComboBox(yearBoxModel);
		yearBox.setBounds(screenWidth/4 - screenWidth/14 + screenWidth/17 + screenWidth/21, screenHeight/7 + 4*screenHeight/30, screenWidth/27, screenHeight/35);
		yearBox.setFont(labelFont);
		add(yearBox);
		
		// Save button that tries to initiate writing this newly given information to the JSON file
		JButton btnCreate = new JButton("Save");
		btnCreate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Keeps track of the number of errors to occur when trying to add a new scholarship
				Integer errorCount = 0;

				// Reset the success message to not display, in case errors are run into
				successfulAdd.setVisible(false);

				// Convert the text from the dollar amount text field to a string
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
					//Save the selected semester to string
					selectedTerm = (String)semesterBox.getSelectedItem();
				} else {
					// error message displayed
					invalidTerm.setVisible(true);
					errorCount++;
				}

				// If else statement to determine whether a day has been chosen
				if (dayBox.getSelectedIndex() != 0) {
					invalidDate.setVisible(false);
					//Save the selected day to string
					selectedDay = (String)dayBox.getSelectedItem();
				} else {
					// error message displayed
					invalidDate.setVisible(true);
					errorCount++;
				}

				// If else statement to determine whether a month has been chosen
				if (monthBox.getSelectedIndex() != 0) {
					invalidDate.setVisible(false);
					//Save the selected month to string
					selectedMonth = (String)monthBox.getSelectedItem();
				} else {
					// error message displayed
					invalidDate.setVisible(true);
					errorCount++;
				}

				// If else statement to determine whether a year has been chosen
				if (yearBox.getSelectedIndex() != 0) {
					invalidDate.setVisible(false);
					//Save the selected year to string
					selectedYear = (String)yearBox.getSelectedItem();
				} else {
					// error message displayed
					invalidDate.setVisible(true);
					errorCount++;
				}

				// If no errors have occured, then try writing this information to the JSON
				if (errorCount == 0){

					// Initialize a JSONParser to get the data from the JSON file
					JSONParser parser5 = new JSONParser();

					//write the file to JSON
					JSONObject obj = new JSONObject();

					// try catch to write the new information to the JSON
					try (Reader reader5 = new FileReader("currentScholarships.json")){
						// Initialized a new JSONArray
						JSONArray scholarshipInfo = new JSONArray();
						// Add the information given in order to this array
						scholarshipInfo.add(selectedFaculty);
						scholarshipInfo.add(selectedTerm);
						scholarshipInfo.add(enteredDollarAmount);
						scholarshipInfo.add(selectedMonth);
						scholarshipInfo.add(selectedDay);
						scholarshipInfo.add(selectedYear);
						// Set to null until awarded to a student
						scholarshipInfo.add("null");

						// Create an object from this array with a given name
						obj.put(selectedScholarship, scholarshipInfo);

						// Take this object and create a string version that is ready to get appended to the JSON
						String objString = obj.toString();
						String strippedVersion = objString.substring(1, objString.length() - 1);
						String readyForAppendVersion = ", " + strippedVersion;

						// Create a string out of the read and parsed JSON file
						JSONObject jsonObject = (JSONObject) parser5.parse(reader5);
						String jsonObjectString = jsonObject.toString();

						// Locate where the end of the array is of the given scholarship
						Integer indexOfBracket = jsonObjectString.indexOf(selectedScholarship) + 2;
						while (jsonObjectString.charAt(indexOfBracket) != ']') {
							indexOfBracket++;
						}

						// Create a string with the full array to be edited
						String substring = jsonObjectString.substring(jsonObjectString.indexOf(selectedScholarship) - 2, indexOfBracket + 1);

						// Replace this old array with the new array
						String newString = jsonObjectString.replace(substring, readyForAppendVersion);

						// Write this string to the file
						FileWriter fileWriter = new FileWriter("currentScholarships.json");
						fileWriter.write(newString);
						fileWriter.flush();

						// Display the success message
						successfulAdd.setVisible(true);

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
