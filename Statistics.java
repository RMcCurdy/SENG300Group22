import org.json.*;

import java.io.FileNotFoundException;
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

import java.io.FileReader; 
import java.util.Iterator; 
import java.util.Map; 

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;
import java.nio.channels.SelectableChannel;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.Color;
import javax.swing.DefaultComboBoxModel;

public class Statistics extends JPanel {

	private static final long serialVersionUID = 1L;

	private List <String> faculties;
	private List <String> schoolYears;
	private JComboBox facultyBox;
	private JComboBox schoolYearBox;
	private String selectedFaculty;
	private String selectedYear;

	/**
	 * Create the panel.
	 * @param user
	 * @param frame 
	 */
	public Statistics(JFrame frame, Account user) {
		//Save the user's screen resolution to variables, used to format GUI correctly
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;

		setLayout(null);
		
		//Font size for remaining labels
		Font labelFontSize = new Font("Arial", Font.PLAIN, screenHeight/60);

		//Create an empty array list that calls faculties name from a file
		faculties = new ArrayList <String>();

		//Create an empty array list that calls faculties name from a file
		schoolYears = new ArrayList <String>();

		//Header of the system name
		JLabel header = new JLabel("Statistics");
		header.setBounds(screenWidth/4 - screenWidth/22, screenHeight/25, screenWidth/10, screenHeight/25);
		header.setForeground(Color.RED);
		header.setFont(new Font("Arial", Font.PLAIN, screenHeight/30));
		add(header);

		JLabel locationFall = new JLabel("Temporary label for locationFall");
		locationFall.setForeground(Color.BLACK);
		locationFall.setBounds(screenWidth/10, screenHeight/10, screenWidth/2, screenHeight/35);
		locationFall.setFont(labelFontSize);
		add(locationFall);

		JLabel locationWinter = new JLabel("Temporary label for locationWinter");
		locationWinter.setForeground(Color.BLACK);
		locationWinter.setBounds(screenWidth/10, screenHeight/10 + 2*screenHeight/30, screenWidth/2, screenHeight/35);
		locationWinter.setFont(labelFontSize);
		add(locationWinter);

		JLabel locationFullYear = new JLabel("Temporary label for locationFullYear");
		locationFullYear.setForeground(Color.BLACK);
		locationFullYear.setBounds(screenWidth/10, screenHeight/10 + 4*screenHeight/30, screenWidth/2, screenHeight/35);
		locationFullYear.setFont(labelFontSize);
		add(locationFullYear);

		JLabel genderFall = new JLabel("Temporary label for genderFall");
		genderFall.setForeground(Color.BLACK);
		genderFall.setBounds(screenWidth/10, screenHeight/10 + screenHeight/30, screenWidth/2, screenHeight/35);
		genderFall.setFont(labelFontSize);
		add(genderFall);

		JLabel genderWinter = new JLabel("Temporary label for genderWinter");
		genderWinter.setForeground(Color.BLACK);
		genderWinter.setBounds(screenWidth/10, screenHeight/10 + 3*screenHeight/30, screenWidth/2, screenHeight/35);
		genderWinter.setFont(labelFontSize);
		add(genderWinter);

		JLabel genderFullYear = new JLabel("Temporary label for genderFullYear");
		genderFullYear.setForeground(Color.BLACK);
		genderFullYear.setBounds(screenWidth/10, screenHeight/10 + 5*screenHeight/30, screenWidth/2, screenHeight/35);
		genderFullYear.setFont(labelFontSize);
		add(genderFullYear);

		// Label to indicate to select a faculty
		JLabel futureSchoolYear = new JLabel("There is no data for this school year yet");
		futureSchoolYear.setForeground(Color.BLACK);
		futureSchoolYear.setBounds(screenWidth/10 + screenWidth/20, screenHeight/10 + 2*screenHeight/30, screenWidth/2, screenHeight/35);
		futureSchoolYear.setFont(labelFontSize);
		add(futureSchoolYear);

		// Label to indicate to select a faculty
		JLabel selectFaculty = new JLabel("Select a faculty from below");
		selectFaculty.setForeground(Color.BLACK);
		selectFaculty.setBounds(screenWidth/4 - screenWidth/9 - screenWidth/45, screenHeight/7 + 6*screenHeight/30, screenWidth/7, screenHeight/35);
		selectFaculty.setFont(labelFontSize);
		add(selectFaculty);

		// Label to indicate to select a faculty
		JLabel selectYear = new JLabel("Select a year from below");
		selectYear.setForeground(Color.BLACK);
		selectYear.setBounds(screenWidth/4 - screenWidth/7 + screenWidth/10 + screenWidth/7/2 - screenWidth/45, screenHeight/7 + 6*screenHeight/30, screenWidth/7, screenHeight/35);
		selectYear.setFont(labelFontSize);
		add(selectYear);

		// Label for error message
		JLabel invalidFaculty = new JLabel("Please select a faculty");
		invalidFaculty.setForeground(Color.RED);
		invalidFaculty.setBounds(screenWidth/4 - screenWidth/9 - screenWidth/75, screenHeight/7 + 6*screenHeight/30, screenWidth/7, screenHeight/35);
		invalidFaculty.setFont(labelFontSize);
		add(invalidFaculty);

		// Label for error message
		JLabel invalidYear = new JLabel("Please select a year");
		invalidYear.setForeground(Color.RED);
		invalidYear.setBounds(screenWidth/4 - screenWidth/7 + screenWidth/10 + screenWidth/7/2 - screenWidth/105, screenHeight/7 + 6*screenHeight/30, screenWidth/7, screenHeight/35);
		invalidYear.setFont(labelFontSize);
		add(invalidYear);

		locationFall.setVisible(false);
		locationWinter.setVisible(false);
		locationFullYear.setVisible(false);
		genderFall.setVisible(false);
		genderWinter.setVisible(false);
		genderFullYear.setVisible(false);
		futureSchoolYear.setVisible(false);
		invalidFaculty.setVisible(false);
		invalidYear.setVisible(false);

		//Add faculties to the drop down menu. Can be optimized to pull from a txt file.
		JSONParser parser = new JSONParser();

        try (Reader reader = new FileReader("data.json")) {

            JSONObject jsonObject = (JSONObject) parser.parse(reader);

            JSONArray facultyArrayJSON = (JSONArray) jsonObject.get("faculties");
            //System.out.println(msg.size());
            Iterator<String> iterator = facultyArrayJSON.iterator();
            while (iterator.hasNext()) {
                faculties.add(iterator.next());
			}
			
			reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
		//Set up the drop down menu and its properties
		DefaultComboBoxModel modelTemp = new DefaultComboBoxModel(faculties.toArray());
        facultyBox = new JComboBox(modelTemp);
		facultyBox.setBounds(screenWidth/4 - screenWidth/9, screenHeight/7 + 7*screenHeight/30, screenWidth/7/2, screenHeight/35);
		facultyBox.setFont(labelFontSize);
		add(facultyBox);

		//Add years to the drop down menu. Can be optimized to pull from a txt file.
		JSONParser parser1 = new JSONParser();

        try (Reader reader1 = new FileReader("data.json")) {

            JSONObject jsonObject1 = (JSONObject) parser1.parse(reader1);

            JSONArray yearArrayJSON1 = (JSONArray) jsonObject1.get("schoolYears");

            Iterator<String> iterator1 = yearArrayJSON1.iterator();
            while (iterator1.hasNext()) {
                schoolYears.add(iterator1.next());
			}
			
			reader1.close();

        } catch (IOException e) {
            System.out.println("IOException");
        } catch (ParseException e) {
            System.out.println("ParseException");
        }
		//Set up the drop down menu and its properties
		DefaultComboBoxModel schoolYearBoxModel = new DefaultComboBoxModel(schoolYears.toArray());
        schoolYearBox = new JComboBox(schoolYearBoxModel);
		schoolYearBox.setBounds(screenWidth/4 - screenWidth/7 + screenWidth/10 + screenWidth/7/2, screenHeight/7 + 7*screenHeight/30, screenWidth/7/2, screenHeight/35);
		schoolYearBox.setFont(labelFontSize);
		add(schoolYearBox);

		//Add a button to go back
		JButton btnCancel = new JButton("Back");
		btnCancel.setFont(labelFontSize);
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

		//Add a button to go back
		JButton btnConfirm = new JButton("Display");
		btnConfirm.setFont(labelFontSize);
		btnConfirm.setBounds(screenWidth/4 - screenWidth/14 + screenWidth/60 - screenWidth/200, screenHeight/7 + 8*screenHeight/30 + screenHeight/90, screenWidth/20, screenHeight/33);
		btnConfirm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Integer errorCount = 0;
					// if else statement to determine whether a faculty has been chosen
					if (schoolYearBox.getSelectedIndex() != 0) {
						invalidYear.setVisible(false);
						selectYear.setVisible(true);
						//Save the selected faculty to string
						selectedYear = (String)schoolYearBox.getSelectedItem();
						System.out.println(selectedYear);
					} else {
						// error message displayed
						invalidYear.setVisible(true);
						selectYear.setVisible(false);
						errorCount++;
					}

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

					//If statement to determine if statistics exist for this year, this school year has not come yet so return error for statistics
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

					if (errorCount == 0){

						//Add years to the drop down menu. Can be optimized to pull from a txt file.
						JSONParser parser3 = new JSONParser();

						try (Reader reader3 = new FileReader("scholarshipData.json")) {
							Integer elementNumber = 0;
							String dataName = "";

							//If statement to determine if statistics exist for this year, this school year has not come yet so return error for statistics
							if (schoolYearBox.getSelectedIndex() == 8){
								dataName = selectedFaculty;
							} else {
								dataName = selectedFaculty + " " + selectedYear;
							}

							JSONObject jsonObject3 = (JSONObject) parser3.parse(reader3);

							JSONArray yearArrayJSON3 = (JSONArray) jsonObject3.get(dataName);

							Iterator<String> iterator3 = yearArrayJSON3.iterator();
							while (iterator3.hasNext()) {
								if (elementNumber == 0){
									String elementValue = iterator3.next();
									//Calculate percentage of international based on domestic percentage
									String internationalPercentage = Integer.toString(100 - Integer.parseInt(elementValue));
									locationFall.setText("Scholarships in the Fall: Domestic - " + elementValue + "% / International - " + internationalPercentage + "%");
									locationFall.setVisible(true);
								}
								if (elementNumber == 1){
									String elementValue = iterator3.next();
									//Calculate percentage of international based on domestic percentage
									String internationalPercentage = Integer.toString(100 - Integer.parseInt(elementValue));
									genderFall.setText("Scholarships in the Fall: Male - " + elementValue + "% / Female - " + internationalPercentage + "%");
									genderFall.setVisible(true);
								}
								if (elementNumber == 2){
									String elementValue = iterator3.next();
									//Calculate percentage of international based on domestic percentage
									String internationalPercentage = Integer.toString(100 - Integer.parseInt(elementValue));
									locationWinter.setText("Scholarships in the Winter: Domestic - " + elementValue + "% / International - " + internationalPercentage + "%");
									locationWinter.setVisible(true);
								}
								if (elementNumber == 3){
									String elementValue = iterator3.next();
									//Calculate percentage of international based on domestic percentage
									String internationalPercentage = Integer.toString(100 - Integer.parseInt(elementValue));
									genderWinter.setText("Scholarships in the Winter: Male - " + elementValue + "% / Female - " + internationalPercentage + "%");
									genderWinter.setVisible(true);
								}
								if (elementNumber == 4){
									String elementValue = iterator3.next();
									//Calculate percentage of international based on domestic percentage
									String internationalPercentage = Integer.toString(100 - Integer.parseInt(elementValue));
									locationFullYear.setText("Scholarships for the Full Year: Domestic - " + elementValue + "% / International - " + internationalPercentage + "%");
									locationFullYear.setVisible(true);
								}
								if (elementNumber == 5){
									String elementValue = iterator3.next();
									//Calculate percentage of international based on domestic percentage
									String internationalPercentage = Integer.toString(100 - Integer.parseInt(elementValue));
									genderFullYear.setText("Scholarships for the Full Year: Male - " + elementValue + "% / Female - " + internationalPercentage + "%");
									genderFullYear.setVisible(true);
								}
								elementNumber++;
							}
							
							reader3.close();

						} catch (IOException e) {
							System.out.println("IOException");
						} catch (ParseException e) {
							System.out.println("ParseException");
						}

					} else {

					}

			}
		});
		add(btnConfirm);

		//If statements to determine which picture to display stats for
        
	}
}
