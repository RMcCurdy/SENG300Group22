import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.json.simple.parser.JSONParser;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.Color;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.awt.Dimension;
import java.awt.Toolkit;

import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Font;
import java.util.Iterator;  
import java.io.*;
import java.awt.Color;
import javax.swing.DefaultComboBoxModel;

public class EditScholarship extends JPanel {

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

	private static final long serialVersionUID = 1L;

	/**
	 * Takes in the information given by the user and creates a new scholarship
	 * This information gets stored in a local JSON file that can be called for further editing
	 * 
	 * @param frame
	 * @param user
	 * @author Robert McCurdy
	 */
	public EditScholarship(JFrame frame, Account user) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		
		setLayout(null);

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

		//Font size for remaining labels
		Font labelFontSize = new Font("Arial", Font.PLAIN, screenHeight/60);

		//Error message for an invalid integer
		JLabel invalidINT = new JLabel("Please enter an integer");
		invalidINT.setForeground(Color.RED);
		invalidINT.setBounds(screenWidth/4 + screenWidth/13, screenHeight/7 + 3*screenHeight/30, screenWidth/6, screenHeight/35);
		invalidINT.setFont(labelFontSize);
		add(invalidINT);
		invalidINT.setVisible(false);

		//Error message for an invalid first name
		JLabel invalidScholarshipName = new JLabel("Invalid Scholarship Name");
		invalidScholarshipName.setForeground(Color.RED);
		invalidScholarshipName.setBounds(screenWidth/4 + screenWidth/13, screenHeight/7, screenWidth/7, screenHeight/35);
		invalidScholarshipName.setFont(labelFontSize);
		add(invalidScholarshipName);
		invalidScholarshipName.setVisible(false);

		//Error message for an invalid faculty
		JLabel invalidFaculty = new JLabel("Please select a faculty");
		invalidFaculty.setForeground(Color.RED);
		invalidFaculty.setBounds(screenWidth/4 + screenWidth/13, screenHeight/7 + screenHeight/30, screenWidth/7, screenHeight/35);
		invalidFaculty.setFont(labelFontSize);
		add(invalidFaculty);
		invalidFaculty.setVisible(false);

		//Error message for an invalid faculty
		JLabel invalidTerm = new JLabel("Please select a term");
		invalidTerm.setForeground(Color.RED);
		invalidTerm.setBounds(screenWidth/4 + screenWidth/13, screenHeight/7 + 2*screenHeight/30, screenWidth/7, screenHeight/35);
		invalidTerm.setFont(labelFontSize);
		add(invalidTerm);
		invalidTerm.setVisible(false);

		//Error message for an invalid faculty
		JLabel invalidDate = new JLabel("Invalid Date");
		invalidDate.setForeground(Color.RED);
		invalidDate.setBounds(screenWidth/4 + screenWidth/13, screenHeight/7 + 4*screenHeight/30, screenWidth/7, screenHeight/35);
		invalidDate.setFont(labelFontSize);
		add(invalidDate);
		invalidDate.setVisible(false);

		//Error message for an invalid faculty
		JLabel successfulAdd = new JLabel("Successfully added the scholarship");
		successfulAdd.setForeground(Color.green);
		successfulAdd.setBounds(screenWidth/4 - screenWidth/14, screenHeight/7 + 6*screenHeight/33, screenWidth/6, screenHeight/35);
		successfulAdd.setFont(labelFontSize);
		add(successfulAdd);
		successfulAdd.setVisible(false);

		//Header of the system name
		JLabel header = new JLabel("Add Scholarships");
		header.setBounds(screenWidth/4 - screenWidth/14, screenHeight/25, screenWidth/3, screenHeight/25);
		header.setForeground(Color.RED);
		header.setFont(new Font("Arial", Font.PLAIN, screenHeight/30));
		add(header);

		//Label for the first name text field
		JLabel scholarshipNameLabel = new JLabel("Scholarship Name:");
		scholarshipNameLabel.setBounds(screenWidth/4 - screenWidth/7 - screenWidth/100, screenHeight/7, screenWidth/7, screenHeight/35);
		scholarshipNameLabel.setFont(labelFontSize);
		add(scholarshipNameLabel);

		//Text field for the first name
		scholarshipName = new JTextField();
		scholarshipName.setBounds(screenWidth/4 - screenWidth/14, screenHeight/7, screenWidth/7, screenHeight/35);
		scholarshipName.setFont(labelFontSize);
		add(scholarshipName);

		//Label for the last name text field
		JLabel facultyName = new JLabel("Faculty:");
		facultyName.setBounds(screenWidth/4 - screenWidth/10 - screenWidth/130, screenHeight/7 + screenHeight/30, screenWidth/7, screenHeight/35);
		facultyName.setFont(labelFontSize);
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
		facultyBox.setFont(labelFontSize);
		add(facultyBox);

		//Label for the email text field
		JLabel termLabel = new JLabel("Term:");
		termLabel.setBounds(screenWidth/4 - screenWidth/7 + screenWidth/23, screenHeight/7 + 2*screenHeight/30, screenWidth/7, screenHeight/35);
		termLabel.setFont(labelFontSize);
		add(termLabel);

		// Initialize a JSONParser to get the data from the JSON file
		JSONParser parser1 = new JSONParser();

		// Try-catch statement to open the JSON file and add the school years to the drop down list
        try (Reader reader1 = new FileReader("data.json")) {

			// Create a JSONObject out of the parsed JSON file
            JSONObject jsonObject1 = (JSONObject) parser1.parse(reader1);

			// Obtain the array that contains the label "schoolYears"
            JSONArray semesterArrayJSON1 = (JSONArray) jsonObject1.get("semesters");

			// Loop through the JSONArray, and add those school years to the List
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
		// Create a JComboBox to display the school year information in a drop down menu
		DefaultComboBoxModel semesterBoxModel = new DefaultComboBoxModel(semesters.toArray());
        semesterBox = new JComboBox(semesterBoxModel);
		semesterBox.setBounds(screenWidth/4 - screenWidth/14, screenHeight/7 + 2*screenHeight/30, screenWidth/7, screenHeight/35);
		semesterBox.setFont(labelFontSize);
		add(semesterBox);

		//Label for the scholarship dollar amount
		JLabel dollarAmountLabel = new JLabel("$ Amount:");
		dollarAmountLabel.setBounds(screenWidth/4 - screenWidth/7 + screenWidth/39, screenHeight/7 + 3*screenHeight/30, screenWidth/7, screenHeight/35);
		dollarAmountLabel.setFont(labelFontSize);
		add(dollarAmountLabel);

		//Text field for the scholarship dollar amount
		dollarAmount = new JTextField();
		dollarAmount.setBounds(screenWidth/4 - screenWidth/14, screenHeight/7 + 3*screenHeight/30, screenWidth/7, screenHeight/35);
		dollarAmount.setFont(labelFontSize);
		add(dollarAmount);

		//Label for the password text field
		JLabel deadlineLabel = new JLabel("Deadline:");
		deadlineLabel.setBounds(screenWidth/4 - screenWidth/7 + screenWidth/36, screenHeight/7 + 4*screenHeight/30, screenWidth/7, screenHeight/35);
		deadlineLabel.setFont(labelFontSize);
		add(deadlineLabel);

		// Initialize a JSONParser to get the data from the JSON file
		JSONParser parser2 = new JSONParser();

		// Try-catch statement to open the JSON file and add the school years to the drop down list
        try (Reader reader2 = new FileReader("data.json")) {

			// Create a JSONObject out of the parsed JSON file
            JSONObject jsonObject2 = (JSONObject) parser2.parse(reader2);

			// Obtain the array that contains the label "schoolYears"
            JSONArray yearArrayJSON2 = (JSONArray) jsonObject2.get("month");

			// Loop through the JSONArray, and add those school years to the List
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
		// Create a JComboBox to display the school year information in a drop down menu
		DefaultComboBoxModel monthBoxModel = new DefaultComboBoxModel(months.toArray());
        monthBox = new JComboBox(monthBoxModel);
		monthBox.setBounds(screenWidth/4 - screenWidth/14 + screenWidth/85 - screenWidth/200, screenHeight/7 + 4*screenHeight/30, screenWidth/17, screenHeight/35);
		monthBox.setFont(labelFontSize);
		add(monthBox);

		// Initialize a JSONParser to get the data from the JSON file
		JSONParser parser3 = new JSONParser();

		// Try-catch statement to open the JSON file and add the school years to the drop down list
        try (Reader reader3 = new FileReader("data.json")) {

			// Create a JSONObject out of the parsed JSON file
            JSONObject jsonObject3 = (JSONObject) parser3.parse(reader3);

			// Obtain the array that contains the label "schoolYears"
            JSONArray yearArrayJSON3 = (JSONArray) jsonObject3.get("day");

			// Loop through the JSONArray, and add those school years to the List
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
		// Create a JComboBox to display the school year information in a drop down menu
		DefaultComboBoxModel dayBoxModel = new DefaultComboBoxModel(days.toArray());
        dayBox = new JComboBox(dayBoxModel);
		dayBox.setBounds(screenWidth/4 - screenWidth/14 + screenWidth/17 + screenWidth/85, screenHeight/7 + 4*screenHeight/30, screenWidth/40, screenHeight/35);
		dayBox.setFont(labelFontSize);
		add(dayBox);

		// Initialize a JSONParser to get the data from the JSON file
		JSONParser parser4 = new JSONParser();

		// Try-catch statement to open the JSON file and add the school years to the drop down list
        try (Reader reader4 = new FileReader("data.json")) {

			// Create a JSONObject out of the parsed JSON file
            JSONObject jsonObject4 = (JSONObject) parser4.parse(reader4);

			// Obtain the array that contains the label "schoolYears"
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
		yearBox.setBounds(screenWidth/4 - screenWidth/14 + screenWidth/17 + screenWidth/40 + screenWidth/85 + screenWidth/200, screenHeight/7 + 4*screenHeight/30, screenWidth/27, screenHeight/35);
		yearBox.setFont(labelFontSize);
		add(yearBox);
		
		JButton btnCreate = new JButton("Save");
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

				//Try catch statement to determine whether we can convert the dollar amount string to an int and then return the correct message
				try {
					//Tries to convert the given string to an integer, catches exception if not able to
					Integer enteredDollar = Integer.parseInt(enteredDollarAmount.trim());
					//Returns an invalid ID error if it doesn't follow criteria for an ID
					if (enteredDollarAmount.contains(".")) {
						invalidINT.setVisible(true);
						errorCount++;
					//Returns a valid ID if it follows the criteria for an ID
					} else {
						invalidINT.setVisible(false);
					} 
				} catch(NumberFormatException nfe) {
					//An integer was not given as an ID
					invalidINT.setVisible(true);
					errorCount++;
				}

				// If statement to determine if the scholarship name only contains letters
				if (!(enteredScholarshipName.chars().allMatch(Character::isLetter))){
					invalidScholarshipName.setVisible(true);
					errorCount++;
				} else {
					invalidScholarshipName.setVisible(false);
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
					//Save the selected faculty to string
					selectedFaculty = (String)facultyBox.getSelectedItem();
				} else {
					// error message displayed
					invalidFaculty.setVisible(true);
					errorCount++;
				}

				// If else statement to determine whether a faculty has been chosen
				if (semesterBox.getSelectedIndex() != 0) {
					invalidTerm.setVisible(false);
					//Save the selected faculty to string
					selectedTerm = (String)semesterBox.getSelectedItem();
				} else {
					// error message displayed
					invalidTerm.setVisible(true);
					errorCount++;
				}

				// If else statement to determine whether a faculty has been chosen
				if (dayBox.getSelectedIndex() != 0) {
					invalidDate.setVisible(false);
					//Save the selected faculty to string
					selectedDay = (String)dayBox.getSelectedItem();
				} else {
					// error message displayed
					invalidDate.setVisible(true);
					errorCount++;
				}

				// If else statement to determine whether a faculty has been chosen
				if (monthBox.getSelectedIndex() != 0) {
					invalidDate.setVisible(false);
					//Save the selected faculty to string
					selectedMonth = (String)monthBox.getSelectedItem();
				} else {
					// error message displayed
					invalidDate.setVisible(true);
					errorCount++;
				}

				// If else statement to determine whether a faculty has been chosen
				if (yearBox.getSelectedIndex() != 0) {
					invalidDate.setVisible(false);
					//Save the selected faculty to string
					selectedYear = (String)yearBox.getSelectedItem();
				} else {
					// error message displayed
					invalidDate.setVisible(true);
					errorCount++;
				}

				if (errorCount == 0){
					successfulAdd.setVisible(true);

					JSONParser parser5 = new JSONParser();

					//write the file to JSON
					JSONObject obj = new JSONObject();

					try (Reader reader5 = new FileReader("currentScholarships.json")){
						JSONArray scholarshipInfo = new JSONArray();
						scholarshipInfo.add(selectedFaculty);
						scholarshipInfo.add(selectedTerm);
						scholarshipInfo.add(enteredDollarAmount);
						scholarshipInfo.add(selectedMonth);
						scholarshipInfo.add(selectedDay);
						scholarshipInfo.add(selectedYear);
						scholarshipInfo.add("");

						obj.put(enteredScholarshipName, scholarshipInfo);

						String objString = obj.toString();
						String strippedVersion = objString.substring(1, objString.length() - 1);
						String readyForAppendVersion = ", " + strippedVersion;

						JSONObject jsonObject = (JSONObject) parser5.parse(reader5);
						String jsonObjectString = jsonObject.toString();

						String newString = jsonObjectString.substring(0, jsonObjectString.length() - 1) + readyForAppendVersion + "}";

						FileWriter fileWriter = new FileWriter("currentScholarships.json");
						fileWriter.write(newString);
						fileWriter.flush();

					} catch (IOException f) {
						f.printStackTrace();
					} catch (ParseException f) {
						f.printStackTrace();
					}
				} else {

				}	
					
			}
		});
		btnCreate.setFont(labelFontSize);
		btnCreate.setBounds(screenWidth/4 - screenWidth/15 + screenWidth/60 - screenWidth/200, screenHeight/7 + 7*screenHeight/30, screenWidth/20, screenHeight/33);
		add(btnCreate);
		
		JButton btnCancel = new JButton("Back");
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setBounds((screenWidth/2 - screenWidth/4), (screenHeight/2 - screenHeight/4), screenWidth/2, screenHeight/2);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				DepartmentHeadMenu dMenu = new DepartmentHeadMenu(frame, user);
				frame.setContentPane(dMenu);
				frame.revalidate();
			}
		});
		btnCancel.setFont(labelFontSize);
		btnCancel.setBounds(screenWidth/4 - screenWidth/15 + 2*screenWidth/30 + screenWidth/200, screenHeight/7 + 7*screenHeight/30, screenWidth/20, screenHeight/33);
		add(btnCancel);

	}
}
