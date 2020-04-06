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
import java.util.Arrays;
import java.util.List;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Font;
import java.util.Iterator;  
import java.io.*;
import java.awt.Color;
import javax.swing.DefaultComboBoxModel;

public class RemoveScholarship extends JPanel {

	private JTextField scholarshipName;

	private static final long serialVersionUID = 1L;
	
	/**
	 * Create the panel.
	 */
	public RemoveScholarship(JFrame frame, Account user) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		
		setLayout(null);

		//Font size for remaining labels
		Font labelFontSize = new Font("Arial", Font.PLAIN, screenHeight/60);

		//Header of the system name
		JLabel header = new JLabel("Remove Scholarships");
		header.setBounds(screenWidth/4 - screenWidth/6, screenHeight/25, screenWidth/3, screenHeight/25);
		header.setForeground(Color.RED);
		header.setFont(labelFontSize);
		add(header);

		//Label for the email text field
		JLabel termLabel = new JLabel("Scholarship Name:");
		termLabel.setBounds(screenWidth/4 - screenWidth/7 - screenWidth/100, screenHeight/7, screenWidth/7, screenHeight/35);
		termLabel.setFont(labelFontSize);
		add(termLabel);

		//Text field for the first name
		scholarshipName = new JTextField();
		scholarshipName.setBounds(screenWidth/4 - screenWidth/14, screenHeight/7, screenWidth/7, screenHeight/35);
		scholarshipName.setFont(labelFontSize);
		add(scholarshipName);

		//Error message for an invalid faculty
		JLabel successfulAdd = new JLabel("Successfully removed the scholarship");
		successfulAdd.setForeground(Color.green);
		successfulAdd.setBounds(screenWidth/4 - screenWidth/14, screenHeight/7 + 6*screenHeight/33, screenWidth/6, screenHeight/35);
		successfulAdd.setFont(labelFontSize);
		add(successfulAdd);
		successfulAdd.setVisible(false);

		//Error message for an invalid first name
		JLabel invalidScholarship = new JLabel("Scholarship Name Doesn't Exist");
		invalidScholarship.setForeground(Color.RED);
		invalidScholarship.setBounds(screenWidth/4 + screenWidth/13, screenHeight/7, screenWidth/7, screenHeight/35);
		invalidScholarship.setFont(labelFontSize);
		add(invalidScholarship);
		invalidScholarship.setVisible(false);

		//Error message for an invalid first name
		JLabel invalidScholarshipName = new JLabel("Invalid Scholarship Name");
		invalidScholarshipName.setForeground(Color.RED);
		invalidScholarshipName.setBounds(screenWidth/4 + screenWidth/13, screenHeight/7, screenWidth/7, screenHeight/35);
		invalidScholarshipName.setFont(labelFontSize);
		add(invalidScholarshipName);
		invalidScholarshipName.setVisible(false);

		//Error message for an invalid first name
		JLabel invalidTextField = new JLabel("Please Enter A Scholarship Name");
		invalidTextField.setForeground(Color.RED);
		invalidTextField.setBounds(screenWidth/4 + screenWidth/13, screenHeight/7, screenWidth/7, screenHeight/35);
		invalidTextField.setFont(labelFontSize);
		add(invalidTextField);
		invalidTextField.setVisible(false);
		
		JButton btnCreate = new JButton("Remove");
		btnCreate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Keeps track of the number of errors to occur when trying to add a new scholarship
				Integer initialerrorCount = 0;
				Integer errorCount = 0;

				String enteredScholarshipName = scholarshipName.getText();

				// If statement to determine if the scholarship name only contains letters
				if (!(enteredScholarshipName.chars().allMatch(Character::isLetter))){
					invalidScholarshipName.setVisible(true);
					invalidTextField.setVisible(false);
					invalidScholarship.setVisible(false);
					initialerrorCount++;
					errorCount++;
				} else {
					invalidScholarshipName.setVisible(false);
				}

				// If statement to determine if the scholarship name is blank
				if (enteredScholarshipName.isEmpty()) {
					invalidTextField.setVisible(true);
					invalidScholarship.setVisible(false);
					invalidScholarshipName.setVisible(false);
					initialerrorCount++;
					errorCount++;
				} else {
					invalidTextField.setVisible(false);
				}

				if (initialerrorCount == 0){
					// Initialize a JSONParser to get the data from the JSON file
					JSONParser parser = new JSONParser();

					// Try-catch statement to open the JSON file and add the school years to the drop down list
					try (Reader reader = new FileReader("currentScholarships.json")) {

						JSONObject jsonObject = (JSONObject) parser.parse(reader);
						String jsonObjectString = jsonObject.toString();

						if (jsonObjectString.contains(enteredScholarshipName)){
							invalidScholarship.setVisible(false);
						} else {
							invalidScholarship.setVisible(true);
							errorCount++;
						}
						
						// Close the reader
						reader.close();

					// Exceptions to be thrown if necessary
					} catch (IOException g) {
						g.printStackTrace();
					} catch (ParseException g) {
						g.printStackTrace();
					}
				}
				
					
				if (errorCount == 0){

					successfulAdd.setVisible(true);

					JSONParser parser5 = new JSONParser();

					//write the file to JSON
					JSONObject obj = new JSONObject();

					try (Reader reader5 = new FileReader("currentScholarships.json")){

						JSONObject jsonObject = (JSONObject) parser5.parse(reader5);
						String jsonObjectString = jsonObject.toString();

						Integer indexOfBracket = jsonObjectString.indexOf(enteredScholarshipName) + 2;
						while (jsonObjectString.charAt(indexOfBracket) != ']') {
							indexOfBracket++;
						}

						String substring = jsonObjectString.substring(jsonObjectString.indexOf(enteredScholarshipName) - 3, indexOfBracket);

						String newString = jsonObjectString.replace(substring, "");

						FileWriter fileWriter = new FileWriter("currentScholarships.json");
						fileWriter.write(newString);
						fileWriter.flush();

						successfulAdd.setVisible(true);

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
		btnCreate.setBounds(screenWidth/4 - screenWidth/15 + screenWidth/60 - screenWidth/200, screenHeight/7 + 5*screenHeight/30, screenWidth/20, screenHeight/33);
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
		btnCancel.setBounds(screenWidth/4 - screenWidth/15 + 2*screenWidth/30 + screenWidth/200, screenHeight/7 + 5*screenHeight/30, screenWidth/20, screenHeight/33);
		add(btnCancel);

	}
}
