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

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;
import java.nio.channels.SelectableChannel;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.Color;
import javax.swing.DefaultComboBoxModel;

public class NominateStudent extends JPanel {

	private static final long serialVersionUID = 1L;

    private JTextField first;
	private JTextField last;
    private JTextField id;
    private JTextField notes;
	private String userFaculty;
	private List <String> faculties;
	private JComboBox facultyBox;
	/**
	 * Create the panel.
	 * @param user
	 * @param frame 
	 */
	public NominateStudent(JFrame frame, Account user) {
		//Save the user's screen resolution to variables, used to format GUI correctly
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;

		setLayout(null);
		
		//Font size for remaining labels
        Font labelFontSize = new Font("Arial", Font.PLAIN, screenHeight/60);

		//Label for the first name text field
		JLabel firstName = new JLabel("First Name:");
		firstName.setBounds(screenWidth/4 - screenWidth/7 + screenWidth/100, screenHeight/7, screenWidth/7, screenHeight/35);
		firstName.setFont(labelFontSize);
		add(firstName);
		
		//Label for the last name text field
		JLabel lastName = new JLabel("Last Name:");
		lastName.setBounds(screenWidth/4 - screenWidth/7 + screenWidth/100, screenHeight/7 + screenHeight/30, screenWidth/7, screenHeight/35);
		lastName.setFont(labelFontSize);
		add(lastName);

		//Label for the student ID text field
		JLabel studID = new JLabel("Student ID:");
		studID.setBounds(screenWidth/4 - screenWidth/7 + screenWidth/100, screenHeight/7 + 3*screenHeight/30, screenWidth/7, screenHeight/35);
		studID.setFont(labelFontSize);
		add(studID);
    
    	//Label for faculty in drop down menu
		JLabel faculty = new JLabel("Faculty:");
		faculty.setBounds(screenWidth/4 - screenWidth/8 + screenWidth/115, screenHeight/7 + 6*screenHeight/30, screenWidth/7, screenHeight/35);
		faculty.setFont(labelFontSize);
		add(faculty);

        //Text field for the first name
		first = new JTextField();
		first.setBounds(screenWidth/4 - screenWidth/14, screenHeight/7, screenWidth/7, screenHeight/35);
		first.setFont(labelFontSize);
		add(first);
		
		//Text field for the last name
		last = new JTextField();
		last.setBounds(screenWidth/4 - screenWidth/14, screenHeight/7 + screenHeight/30, screenWidth/7, screenHeight/35);
		last.setFont(labelFontSize);
		add(last);
		
		//Text field for the ID
		id = new JTextField();
		id.setBounds(screenWidth/4 - screenWidth/14, screenHeight/7 + 3*screenHeight/30, screenWidth/7, screenHeight/35);
		id.setFont(labelFontSize);
        add(id);
        
        //Text field for the ID
		notes = new JTextField();
		notes.setBounds(screenWidth/4 - screenWidth/14, screenHeight/7 + 5*screenHeight/30, screenWidth/7, screenHeight/35);
		notes.setFont(labelFontSize);
		add(notes);
		
		/**
		 * MESSAGES
		 */

		//Error message for an invalid ID
		JLabel invalidID = new JLabel("Invalid ID");
		invalidID.setForeground(Color.RED);
		invalidID.setBounds(screenWidth/4 + screenWidth/13, screenHeight/7 + 3*screenHeight/30, screenWidth/7, screenHeight/35);
		invalidID.setFont(labelFontSize);
		add(invalidID);

		//Error message for an invalid integer
		JLabel invalidINT = new JLabel("Please enter an 8-digit integer");
		invalidINT.setForeground(Color.RED);
		invalidINT.setBounds(screenWidth/4 + screenWidth/13, screenHeight/7 + 3*screenHeight/30, screenWidth/6, screenHeight/35);
		invalidINT.setFont(labelFontSize);
		add(invalidINT);

		//Error message for an invalid first name
		JLabel invalidFirst = new JLabel("Invalid First Name");
		invalidFirst.setForeground(Color.RED);
		invalidFirst.setBounds(screenWidth/4 + screenWidth/13, screenHeight/7, screenWidth/7, screenHeight/35);
		invalidFirst.setFont(labelFontSize);
		add(invalidFirst);

		//Error message for an invalid last name
		JLabel invalidLast = new JLabel("Invalid Last Name");
		invalidLast.setForeground(Color.RED);
		invalidLast.setBounds(screenWidth/4 + screenWidth/13, screenHeight/7 + screenHeight/30, screenWidth/7, screenHeight/35);
		invalidLast.setFont(labelFontSize);
		add(invalidLast);

		//Error message for not filling in all the fields
		JLabel invalidField = new JLabel("Not all required fields have been filled in");
		invalidField.setForeground(Color.RED);
		invalidField.setBounds(screenWidth/4 - screenWidth/10, screenHeight/6 + 10 * screenHeight/40, screenWidth/5, screenHeight/25);
		invalidField.setFont(labelFontSize);
		add(invalidField);
    
    	JLabel invalidFaculty = new JLabel("Please select a faculty");
		invalidFaculty.setForeground(Color.RED);
		invalidFaculty.setBounds(screenWidth/4 + screenWidth/13, screenHeight/7 + 6*screenHeight/30, screenWidth/7, screenHeight/35);
		invalidFaculty.setFont(labelFontSize);
		add(invalidFaculty);

		//Set the error messages to be invisible, until needed
		invalidID.setVisible(false);
		invalidINT.setVisible(false);
		invalidFirst.setVisible(false);
		invalidLast.setVisible(false);
		invalidField.setVisible(false);
    	invalidFaculty.setVisible(false);
    
        /**
		* DROP DOWN SELECT
		*/

		//Create an empty array list that calls faculties name from a file
		faculties = new ArrayList <String>();
		//Set the users faculty to a default, this will get updated in create account method
		userFaculty = "";

		//Add faculties to the drop down menu
		faculties.add("");
		faculties.add("Arts");
		faculties.add("Medicine");
		faculties.add("Architecture");
		faculties.add("Business");
		faculties.add("Kinesiology");
		faculties.add("Law");
		faculties.add("Nursing");
		faculties.add("Engineering");
		faculties.add("Social Work");
		faculties.add("Education");
		faculties.add("Science");
		//Set up the drop down menu and its properties
		DefaultComboBoxModel modelTemp = new DefaultComboBoxModel(faculties.toArray());
        facultyBox = new JComboBox(modelTemp);
		facultyBox.setBounds(screenWidth/4 - screenWidth/14, screenHeight/7 + 6*screenHeight/30, screenWidth/7, screenHeight/35);
		facultyBox.setFont(labelFontSize);
		add(facultyBox);

		//On mouse click of the drop down menu, update what was selected
		facultyBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
				try {
					if (facultyBox.getSelectedIndex() != 0) {
						//Save the selected faculty to the variable for later use
						String selectedFaculty = (String)facultyBox.getSelectedItem();
						userFaculty = selectedFaculty;
					} else {
						invalidFaculty.setVisible(true);
					}
            	} catch (Exception e) {

				}
			}
        });
		
		/**
		 * BUTTONS
		 */

		//Button for creating an account
		JButton submitButton = new JButton("Submit");
		submitButton.addMouseListener(new MouseAdapter() {
			@Override
			//On a mouse click, method checks that all text fields have been filled out correctly and saves to a txt file
			public void mouseClicked(MouseEvent e) {

				//Keeps track of the number of errors to occur when trying to create an accoun
				Integer errorCount = 0;
		
				//Set strings to be the account details given in the text fields
				String enteredFirst = first.getText();
				String enteredLast = last.getText();
                String enteredID = id.getText();
                String enteredNotes = notes.getText();

				//Try catch statement to determine whether we can convert the ID string to an int and then return the correct message
				try {
					//Tries to convert the given string to an integer, catches exception if not able to
					Integer enteredIDint = Integer.parseInt(enteredID.trim());
					//Returns an invalid ID error if it doesn't follow criteria for an ID
					if (!((00000000 <= enteredIDint) && (enteredIDint <= 99999999)) || !(enteredID.length() == 8) || (enteredID.contains("."))) {
						invalidID.setVisible(true);
						invalidINT.setVisible(false);
						errorCount++;
					//Returns a valid ID if it follows the criteria for an ID
					} else if ((0 <= enteredIDint && enteredIDint <= 99999999) && (enteredID.length() == 8) && !(enteredID.contains("."))) {
						invalidID.setVisible(false);
						invalidINT.setVisible(false);
					} 
				} catch(NumberFormatException nfe) {
					//An integer was not given as an ID
					invalidINT.setVisible(true);
					invalidID.setVisible(false);
					errorCount++;
				}

				if (userFaculty == "") {
					invalidFaculty.setVisible(true);
					errorCount++;
				} else {
					invalidFaculty.setVisible(false);
				}
				
				//If else statement to determine if all fields have been filled in
				if (facultyBox.getSelectedIndex() == 0 || enteredFirst.isEmpty() == true || enteredLast.isEmpty() == true || enteredID.isEmpty() == true) {
					invalidField.setVisible(true);	
					errorCount++;
				} else {
					invalidField.setVisible(false);
				}

				//If statement to determine if the first name only contains letters
				if (enteredFirst.chars().allMatch(Character::isLetter)){
					invalidFirst.setVisible(false);
				} else {
					invalidFirst.setVisible(true);
					errorCount++;
				}

				//If statement to determine if the last name only contains letters
				if (enteredLast.chars().allMatch(Character::isLetter)){
					invalidLast.setVisible(false);
				} else {
					invalidLast.setVisible(true);
					errorCount++;
				}

				//If no errors have occured, then we will save this information to a txt file
				if (errorCount == 0){
					/*
					 * Write inputs from the create account fields to two separate files
					 * first file users.txt stores the name, email and id of the person
					 * second file privateInfo.txt stores email and password (what users login with)
					 * if any of the fields is empty nothing is written 
					 * if confirm password doesn't match password field error message displayed 
					 */
					try {
						Recommend information = new Recommend(enteredFirst, enteredLast, Integer.parseInt(enteredID), userFaculty, enteredNotes);
						
						// "accountInformation.txt" stores accounts, i.e. email, name, etc. Passwords aren't stored here
						BufferedWriter bw = new BufferedWriter(new FileWriter("recommendedStudents.txt", true));
						bw.write(information.toString());
						bw.newLine();
						bw.close();
                    }
					catch(Exception ex) 
						{
						//Exception thrown if the above code can't proceed
							ex.printStackTrace();
						}
					//Set the frame size on the closing of the create account GUI
					frame.setBounds((screenWidth/2 - screenWidth/4), (screenHeight/2 - screenHeight/4), screenWidth/2, screenHeight/2);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					ProfessorMenu panel = new ProfessorMenu(frame, user);
					frame.setContentPane(panel);
					frame.revalidate();
				}	    
			}
		});
		submitButton.setBounds(screenWidth/4 - screenWidth/20, screenHeight/6 + 8 * screenHeight/37, screenWidth/10, screenHeight/30);
		submitButton.setFont(labelFontSize);
		add(submitButton);
	

		//Add a button to go back
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setFont(labelFontSize);
		btnCancel.setBounds(screenWidth/4 - screenWidth/14 + screenWidth/20, screenHeight/7 + 7*screenHeight/30, screenWidth/20, screenHeight/35);
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.setBounds((screenWidth/2 - screenWidth/4), (screenHeight/2 - screenHeight/4), screenWidth/2, screenHeight/2);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				ProfessorMenu pm = new ProfessorMenu(frame, user);
				frame.setContentPane(pm);
				frame.revalidate();
			}
		});
		add(btnCancel);
	}
		
}
