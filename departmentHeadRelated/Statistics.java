package departmentHeadRelated;
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
import javax.swing.ImageIcon;

import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.FontFormatException;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import objects.*;

/**
 * Class to read JSON files in order to display the statistical data for scholarships
 * @author Robert McCurdy
 */
public class Statistics extends JPanel {

	// Needed because Statistics is a serialized class
	private static final long serialVersionUID = 1L;

	// Declaration of variables to be used in the class
	private List <String> faculties;
	private List <String> schoolYears;
	private JComboBox facultyBox;
	private JComboBox schoolYearBox;
	private String selectedFaculty;
	private String selectedYear;
	private Font headerFont;
	private Font labelFont;
	private JLabel background_1;
	private JLabel background_2;

	/** Method to display all the label and boxes with information
	 * @param user
	 * @param frame 
	 */
	public Statistics(JFrame frame, Account user) {
		// Save the user's screen resolution to variables, used to format GUI correctly
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;

		// Used to initialized the layout
		setLayout(null);
		
		// Create specific colors to be used in text and buttons
		Color gold = new Color(255, 207, 8);
		Color myRed = new Color(227, 37, 37);

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

		// Create an empty array list for school years
		schoolYears = new ArrayList <String>();

		// Label of the menu name
		JLabel header = new JLabel("Statistics");
		header.setBounds(screenWidth/4 - screenWidth/22, screenHeight/25, screenWidth/10, screenHeight/25);
		header.setForeground(myRed);
		header.setFont(headerFont);
		add(header);

		// This label is to display the domestic/international breakdown for the fall scholarship
		// This label will later get updated with the information called from the JSON file
		JLabel locationFall = new JLabel("Temporary label for locationFall");
		locationFall.setForeground(gold);
		locationFall.setBounds(screenWidth/10, screenHeight/10, screenWidth/2, screenHeight/35);
		locationFall.setFont(labelFont);
		add(locationFall);

		// This label is to display the domestic/international breakdown for the winter scholarship
		// This label will later get updated with the information called from the JSON file
		JLabel locationWinter = new JLabel("Temporary label for locationWinter");
		locationWinter.setForeground(gold);
		locationWinter.setBounds(screenWidth/10, screenHeight/10 + 2*screenHeight/30, screenWidth/2, screenHeight/35);
		locationWinter.setFont(labelFont);
		add(locationWinter);

		// This label is to display the domestic/international breakdown for the full year scholarship
		// This label will later get updated with the information called from the JSON file
		JLabel locationFullYear = new JLabel("Temporary label for locationFullYear");
		locationFullYear.setForeground(gold);
		locationFullYear.setBounds(screenWidth/10, screenHeight/10 + 4*screenHeight/30, screenWidth/2, screenHeight/35);
		locationFullYear.setFont(labelFont);
		add(locationFullYear);

		// This label is to display the male/female breakdown for the fall scholarship
		// This label will later get updated with the information called from the JSON file
		JLabel genderFall = new JLabel("Temporary label for genderFall");
		genderFall.setForeground(gold);
		genderFall.setBounds(screenWidth/10, screenHeight/10 + screenHeight/30, screenWidth/2, screenHeight/35);
		genderFall.setFont(labelFont);
		add(genderFall);

		// This label is to display the male/female breakdown for the winter scholarship
		// This label will later get updated with the information called from the JSON file
		JLabel genderWinter = new JLabel("Temporary label for genderWinter");
		genderWinter.setForeground(gold);
		genderWinter.setBounds(screenWidth/10, screenHeight/10 + 3*screenHeight/30, screenWidth/2, screenHeight/35);
		genderWinter.setFont(labelFont);
		add(genderWinter);

		// This label is to display the male/female breakdown for the full year scholarship
		// This label will later get updated with the information called from the JSON file
		JLabel genderFullYear = new JLabel("Temporary label for genderFullYear");
		genderFullYear.setForeground(gold);
		genderFullYear.setBounds(screenWidth/10, screenHeight/10 + 5*screenHeight/30, screenWidth/2, screenHeight/35);
		genderFullYear.setFont(labelFont);
		add(genderFullYear);

		// This label is to display that the school year selected to display information does not contain any data yet
		JLabel futureSchoolYear = new JLabel("There is no data for this school year yet");
		futureSchoolYear.setForeground(gold);
		futureSchoolYear.setBounds(screenWidth/10 + screenWidth/20, screenHeight/10 + 2*screenHeight/30, screenWidth/2, screenHeight/35);
		futureSchoolYear.setFont(labelFont);
		add(futureSchoolYear);

		// Label to indicate to select a faculty
		JLabel selectFaculty = new JLabel("Select a faculty from below");
		selectFaculty.setForeground(gold);
		selectFaculty.setBounds(screenWidth/4 - screenWidth/9 - screenWidth/45, screenHeight/7 + 6*screenHeight/30, screenWidth/7, screenHeight/35);
		selectFaculty.setFont(labelFont);
		add(selectFaculty);

		// Label to indicate to select a school year
		JLabel selectYear = new JLabel("Select a year from below");
		selectYear.setForeground(gold);
		selectYear.setBounds(screenWidth/4 - screenWidth/7 + screenWidth/10 + screenWidth/7/2 - screenWidth/45, screenHeight/7 + 6*screenHeight/30, screenWidth/7, screenHeight/35);
		selectYear.setFont(labelFont);
		add(selectYear);

		// Label for error message on faculty selection
		JLabel invalidFaculty = new JLabel("Please select a faculty");
		invalidFaculty.setForeground(myRed);
		invalidFaculty.setBounds(screenWidth/4 - screenWidth/9 - screenWidth/75, screenHeight/7 + 6*screenHeight/30, screenWidth/7, screenHeight/35);
		invalidFaculty.setFont(labelFont);
		add(invalidFaculty);

		// Label for error message on school year selection
		JLabel invalidYear = new JLabel("Please select a year");
		invalidYear.setForeground(myRed);
		invalidYear.setBounds(screenWidth/4 - screenWidth/7 + screenWidth/10 + screenWidth/7/2 - screenWidth/105, screenHeight/7 + 6*screenHeight/30, screenWidth/7, screenHeight/35);
		invalidYear.setFont(labelFont);
		add(invalidYear);

		// Set certain labels to be invisible that will later get called to display, such as error messages
		locationFall.setVisible(false);
		locationWinter.setVisible(false);
		locationFullYear.setVisible(false);
		genderFall.setVisible(false);
		genderWinter.setVisible(false);
		genderFullYear.setVisible(false);
		futureSchoolYear.setVisible(false);
		invalidFaculty.setVisible(false);
		invalidYear.setVisible(false);

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
		facultyBox.setBounds(screenWidth/4 - screenWidth/9, screenHeight/7 + 7*screenHeight/30, screenWidth/7/2, screenHeight/35);
		facultyBox.setFont(labelFont);
		add(facultyBox);

		// Initialize a JSONParser to get the data from the JSON file
		JSONParser parser1 = new JSONParser();

		// Try-catch statement to open the JSON file and add the school years to the drop down list
        try (Reader reader1 = new FileReader("data.json")) {

			// Create a JSONObject out of the parsed JSON file
            JSONObject jsonObject1 = (JSONObject) parser1.parse(reader1);

			// Obtain the array that contains the label "schoolYears"
            JSONArray yearArrayJSON1 = (JSONArray) jsonObject1.get("schoolYears");

			// Loop through the JSONArray, and add those school years to the List
            Iterator<String> iterator1 = yearArrayJSON1.iterator();
            while (iterator1.hasNext()) {
                schoolYears.add(iterator1.next());
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
		DefaultComboBoxModel schoolYearBoxModel = new DefaultComboBoxModel(schoolYears.toArray());
        schoolYearBox = new JComboBox(schoolYearBoxModel);
		schoolYearBox.setBounds(screenWidth/4 - screenWidth/7 + screenWidth/10 + screenWidth/7/2, screenHeight/7 + 7*screenHeight/30, screenWidth/7/2, screenHeight/35);
		schoolYearBox.setFont(labelFont);
		add(schoolYearBox);

		//Add a button to go back to the Department Head Menu
		JButton btnCancel = new JButton("Back");
		btnCancel.setFont(labelFont);
		btnCancel.setBackground(gold);
		btnCancel.setBounds(screenWidth/4 - screenWidth/14 + 2*screenWidth/30 + screenWidth/200, screenHeight/7 + 8*screenHeight/30 + screenHeight/90, screenWidth/20, screenHeight/33);
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.setBounds((screenWidth/2 - screenWidth/4), (screenHeight/2 - screenHeight/4), screenWidth/2, screenHeight/2);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				DepartmentHeadMenu dhm = new DepartmentHeadMenu(frame, user);
				frame.setContentPane(dhm);
				frame.revalidate();
			}
		});
		add(btnCancel);

		//Add a button to display the statistical information the user has requested
		JButton btnConfirm = new JButton("Display");
		btnConfirm.setFont(labelFont);
		btnConfirm.setBackground(gold);
		btnConfirm.setBounds(screenWidth/4 - screenWidth/14 + screenWidth/60 - screenWidth/200, screenHeight/7 + 8*screenHeight/30 + screenHeight/90, screenWidth/18, screenHeight/33);
		btnConfirm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Integer errorCount = 0;
					// If else statement to determine whether a school year has been chosen
					if (schoolYearBox.getSelectedIndex() != 0) {
						invalidYear.setVisible(false);
						selectYear.setVisible(true);
						//Save the selected school year to string
						selectedYear = (String)schoolYearBox.getSelectedItem();
						System.out.println(selectedYear);
					} else {
						// error message displayed
						invalidYear.setVisible(true);
						selectYear.setVisible(false);
						errorCount++;
					}

					// If else statement to determine whether a faculty has been chosen
					if (facultyBox.getSelectedIndex() != 0) {
						invalidFaculty.setVisible(false);
						selectFaculty.setVisible(true);
						//Save the selected faculty to string
						selectedFaculty = (String)facultyBox.getSelectedItem();
					} else {
						// error message displayed
						invalidFaculty.setVisible(true);
						selectFaculty.setVisible(false);
						errorCount++;
					}

					//If statement to determine if the year 2020-21 has been selected, as this school year has no statistical data yet
					if (schoolYearBox.getSelectedIndex() == 7) {
						futureSchoolYear.setVisible(true);
						locationFall.setVisible(false);
						genderFall.setVisible(false);
						locationWinter.setVisible(false);
						genderWinter.setVisible(false);
						locationFullYear.setVisible(false);
						genderFullYear.setVisible(false);
						errorCount++;
					} else {
						futureSchoolYear.setVisible(false);
					}

					// If statement to determine if there were any errors encountered when trying to display data
					if (errorCount == 0){

						// Initialize a JSONParser to get the data from the JSON file
						JSONParser parser3 = new JSONParser();

						// Try-catch statement to open the scholarshipData JSON file in order to get the information requested
						try (Reader reader3 = new FileReader("scholarshipData.json")) {
							Integer elementNumber = 0;
							String dataName = "";

							// If statement to determine if statistical data requested is for the past 5 years instead of one particular school year
							if (schoolYearBox.getSelectedIndex() == 8){
								dataName = selectedFaculty;
							} else {
								dataName = selectedFaculty + " " + selectedYear;
							}

							// Create a JSONObject by parsing through the JSON file
							JSONObject jsonObject3 = (JSONObject) parser3.parse(reader3);

							// Create a JSONArray by getting the data from the JSONObject
							JSONArray yearArrayJSON3 = (JSONArray) jsonObject3.get(dataName);

							// Loop to extract all the data for the requested data
							Iterator<String> iterator3 = yearArrayJSON3.iterator();
							while (iterator3.hasNext()) {
								// Multiple if-statements to check which element we are looking for in the array
								if (elementNumber == 0){
									// Obtain the next value in the array
									String elementValue = iterator3.next();
									// Calculate percentage of international based on domestic percentage
									String internationalPercentage = Integer.toString(100 - Integer.parseInt(elementValue));
									// Update the label to display correct information
									locationFall.setText("Scholarships in the Fall: Domestic - " + elementValue + "% / International - " + internationalPercentage + "%");
									locationFall.setVisible(true);
								}
								if (elementNumber == 1){
									// Obtain the next value in the array
									String elementValue = iterator3.next();
									// Calculate percentage of female based on male percentage
									String internationalPercentage = Integer.toString(100 - Integer.parseInt(elementValue));
									// Update the label to display correct information
									genderFall.setText("Scholarships in the Fall: Male - " + elementValue + "% / Female - " + internationalPercentage + "%");
									genderFall.setVisible(true);
								}
								if (elementNumber == 2){
									// Obtain the next value in the array
									String elementValue = iterator3.next();
									// Calculate percentage of international based on domestic percentage
									String internationalPercentage = Integer.toString(100 - Integer.parseInt(elementValue));
									// Update the label to display correct information
									locationWinter.setText("Scholarships in the Winter: Domestic - " + elementValue + "% / International - " + internationalPercentage + "%");
									locationWinter.setVisible(true);
								}
								if (elementNumber == 3){
									// Obtain the next value in the array
									String elementValue = iterator3.next();
									// Calculate percentage of female based on male percentage
									String internationalPercentage = Integer.toString(100 - Integer.parseInt(elementValue));
									// Update the label to display correct information
									genderWinter.setText("Scholarships in the Winter: Male - " + elementValue + "% / Female - " + internationalPercentage + "%");
									genderWinter.setVisible(true);
								}
								if (elementNumber == 4){
									// Obtain the next value in the array
									String elementValue = iterator3.next();
									// Calculate percentage of international based on domestic percentage
									String internationalPercentage = Integer.toString(100 - Integer.parseInt(elementValue));
									// Update the label to display correct information
									locationFullYear.setText("Scholarships for the Full Year: Domestic - " + elementValue + "% / International - " + internationalPercentage + "%");
									locationFullYear.setVisible(true);
								}
								if (elementNumber == 5){
									// Obtain the next value in the array
									String elementValue = iterator3.next();
									// Calculate percentage of female based on male percentage
									String internationalPercentage = Integer.toString(100 - Integer.parseInt(elementValue));
									// Update the label to display correct information
									genderFullYear.setText("Scholarships for the Full Year: Male - " + elementValue + "% / Female - " + internationalPercentage + "%");
									genderFullYear.setVisible(true);
								}
								// Increase elementNumber by 1 to continuously loop through the array of values
								elementNumber++;
							}
							
							// Close the reader
							reader3.close();

						// Exceptions to be thrown
						} catch (IOException e) {
							e.printStackTrace();
						} catch (ParseException e) {
							e.printStackTrace();
						}

					} else {
						// If the errorCount is greater then 1, render the display button useless
						// This button won't do anything and there will instead be error messages displayed to prompt the user to correct their mistakes
					}

			}
		});
		add(btnConfirm);    
		
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
		background_2.setBounds(screenWidth/4 - screenWidth/11, screenHeight/35, 300, 300);
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
