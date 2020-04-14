package login;
import java.io.Serializable;

import javax.swing.text.html.HTMLDocument.Iterator;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Image;

import javax.swing.*;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.awt.event.*;

import java.awt.Color;

import objects.*;


public class Create extends JPanel {

	private static final long serialVersionUID = 1L;

	private JTextField first; 		// First name of user
	private JTextField last; 		// Last name of user
	private JTextField email; 		// email of user
	private JTextField id; 			// student/professor id
	private JTextField password; 	// password of user
	private JTextField passwordConfirmation;
	private Integer schoolRole;
	private String userFaculty;		// faculty the user belongs in
	private List <String> faculties;
	private JComboBox facultyBox;
	private JLabel background_1;
	private JLabel background_2;
	private Font headerFont;
	private Font labelFont;

	public boolean student = true;
	public boolean dept = false;
	
	/**
	 * Menu for creating a new account.
	 * @param frame 
	 */
	public Create(JFrame frame) {
		// Save the user's screen resolution to variables, used to format GUI correctly
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;

		setLayout(null);

		// Create specific colors to be used in text and buttons
		Color gold = new Color(255, 207, 8);
		Color myRed = new Color(227, 37, 37);
		
		// Try catch to load in custom header font
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
		
		/**
		 * LABELS
		 */

		// Header of the system name
		JLabel header = new JLabel("UofC Scholarship Portal");
		header.setForeground(myRed);
		header.setBounds(screenWidth/4 - screenWidth/8 + screenWidth/32, screenHeight/25, screenWidth/4, screenHeight/25);
		header.setFont(headerFont);
		add(header);

		// Font size for remaining labels
		Font labelFontSize = new Font("Arial", Font.PLAIN, screenHeight/60);
		
		// Labels "areYouALabel", "orLabel", and "qMarkLabel" to make it clear to the user with regards to student or professor buttons
		JLabel areYouALabel = new JLabel("Are you a");
		areYouALabel.setBounds(screenWidth/4 - screenWidth/8 - screenWidth/140, screenHeight/10, screenWidth/15, screenHeight/40);
		areYouALabel.setFont(labelFont);
		areYouALabel.setForeground(gold);
		add(areYouALabel);

		// The 'or' in between student and professor
		JLabel orLabel = new JLabel("or");
		orLabel.setBounds(screenWidth/4 - screenWidth/200, screenHeight/10, screenWidth/15, screenHeight/40);
		orLabel.setFont(labelFont);
		orLabel.setForeground(gold);
		add(orLabel);

		// Question mark at the end of the statement
		JLabel qMarkLabel = new JLabel("?");
		qMarkLabel.setBounds(screenWidth/4 + screenWidth/12, screenHeight/10, screenWidth/15, screenHeight/40);
		qMarkLabel.setFont(labelFont);
		qMarkLabel.setForeground(gold);
		add(qMarkLabel);

		// Label for the first name text field
		JLabel firstName = new JLabel("First Name:");
		firstName.setBounds(screenWidth/4 - screenWidth/7 + screenWidth/100, screenHeight/7, screenWidth/7, screenHeight/35);
		firstName.setFont(labelFont);
		firstName.setForeground(gold);
		add(firstName);
		
		// Label for the last name text field
		JLabel lastName = new JLabel("Last Name:");
		lastName.setBounds(screenWidth/4 - screenWidth/7 + screenWidth/100, screenHeight/7 + screenHeight/30, screenWidth/7, screenHeight/35);
		lastName.setFont(labelFont);
		lastName.setForeground(gold);
		add(lastName);

		// Label for the email text field
		JLabel emailLabel = new JLabel("Email:");
		emailLabel.setBounds(screenWidth/4 - screenWidth/7 + screenWidth/29, screenHeight/7 + 2*screenHeight/30, screenWidth/7, screenHeight/35);
		emailLabel.setFont(labelFont);
		emailLabel.setForeground(gold);
		add(emailLabel);

		// Label for the student ID text field
		JLabel studID = new JLabel("Student ID:");
		studID.setBounds(screenWidth/4 - screenWidth/7 + screenWidth/100, screenHeight/7 + 3*screenHeight/30, screenWidth/7, screenHeight/35);
		studID.setFont(labelFont);
		studID.setForeground(gold);
		add(studID);

		// Label to indicate it is a professor's ID for the ID text field
		JLabel profID = new JLabel("Professor ID:");
		profID.setBounds(screenWidth/4 - screenWidth/7 + screenWidth/400, screenHeight/7 + 3*screenHeight/30, screenWidth/7, screenHeight/35);
		profID.setFont(labelFont);
		profID.setForeground(gold);
		add(profID);
		profID.setVisible(false);

		// Label for the password text field
		JLabel pwd = new JLabel("Password:");
		pwd.setBounds(screenWidth/4 - screenWidth/7 + screenWidth/70, screenHeight/7 + 4*screenHeight/30, screenWidth/7, screenHeight/35);
		pwd.setFont(labelFont);
		pwd.setForeground(gold);
		add(pwd);

		// Label for the confirm password text field
		JLabel confirmPwd = new JLabel("Confirm Password:");
		confirmPwd.setBounds(screenWidth/4 - screenWidth/6 - screenWidth/250, screenHeight/7 + 5*screenHeight/30, screenWidth/7, screenHeight/35);
		confirmPwd.setFont(labelFont);
		confirmPwd.setForeground(gold);
		add(confirmPwd);
    
		// Label for faculty in drop down menu
		JLabel faculty = new JLabel("Faculty:");
		faculty.setBounds(screenWidth/4 - screenWidth/8 + screenWidth/115, screenHeight/7 + 6*screenHeight/30, screenWidth/7, screenHeight/35);
		faculty.setFont(labelFont);
		faculty.setForeground(gold);
		add(faculty);
		
		/**
		 * TEXT FIELDS
		 */
		
		// Text field for the first name
		first = new JTextField();
		first.setBounds(screenWidth/4 - screenWidth/14, screenHeight/7, screenWidth/7, screenHeight/35);
		first.setFont(labelFontSize);
		add(first);
		
		// Text field for the last name
		last = new JTextField();
		last.setBounds(screenWidth/4 - screenWidth/14, screenHeight/7 + screenHeight/30, screenWidth/7, screenHeight/35);
		last.setFont(labelFontSize);
		add(last);
		
		// Text field for the email
		email = new JTextField();
		email.setBounds(screenWidth/4 - screenWidth/14, screenHeight/7 + 2*screenHeight/30, screenWidth/7, screenHeight/35);
		email.setFont(labelFontSize);
		add(email);
		
		// Text field for the ID
		id = new JTextField();
		id.setBounds(screenWidth/4 - screenWidth/14, screenHeight/7 + 3*screenHeight/30, screenWidth/7, screenHeight/35);
		id.setFont(labelFontSize);
		add(id);
		
		// Text field for the password
		password = new JPasswordField();
		password.setBounds(screenWidth/4 - screenWidth/14, screenHeight/7 + 4*screenHeight/30, screenWidth/7, screenHeight/35);
		password.setFont(labelFontSize);
		add(password);
		
		// Text field for the confirmed password
		passwordConfirmation = new JPasswordField();
		passwordConfirmation.setBounds(screenWidth/4 - screenWidth/14, screenHeight/7 + 5*screenHeight/30, screenWidth/7, screenHeight/35);
		passwordConfirmation.setFont(labelFontSize);
		add(passwordConfirmation);
		
		/**
		 * MESSAGES
		 */
		
		// Error message for when passwords entered do not match
		JLabel passMatch = new JLabel("Passwords don't match");
		passMatch.setForeground(Color.RED);
		passMatch.setBounds(screenWidth/4 + screenWidth/13, screenHeight/7 + 5*screenHeight/30, screenWidth/7, screenHeight/35);
		passMatch.setFont(labelFont);
		add(passMatch);

		// Error message for the password not meeting the required length
		JLabel passLength = new JLabel("Invalid Password length, needs 10 characters");
		passLength.setForeground(Color.RED);
		passLength.setBounds(screenWidth/4 + screenWidth/13, screenHeight/7 + 4*screenHeight/30, screenWidth/6, screenHeight/35);
		passLength.setFont(labelFont);
		add(passLength);
		
		// Error message for an invalid email
		JLabel invalidEmail = new JLabel("Invalid Email");
		invalidEmail.setForeground(Color.RED);
		invalidEmail.setBounds(screenWidth/4 + screenWidth/13, screenHeight/7 + 2*screenHeight/30, screenWidth/7, screenHeight/35);
		invalidEmail.setFont(labelFont);
		add(invalidEmail);

		// Error message for an invalid ID
		JLabel invalidID = new JLabel("Invalid ID");
		invalidID.setForeground(Color.RED);
		invalidID.setBounds(screenWidth/4 + screenWidth/13, screenHeight/7 + 3*screenHeight/30, screenWidth/7, screenHeight/35);
		invalidID.setFont(labelFont);
		add(invalidID);

		// Error message for an invalid integer
		JLabel invalidINT = new JLabel("Please enter an 8-digit integer");
		invalidINT.setForeground(Color.RED);
		invalidINT.setBounds(screenWidth/4 + screenWidth/13, screenHeight/7 + 3*screenHeight/30, screenWidth/6, screenHeight/35);
		invalidINT.setFont(labelFont);
		add(invalidINT);

		// Error message for an invalid first name
		JLabel invalidFirst = new JLabel("Invalid First Name");
		invalidFirst.setForeground(Color.RED);
		invalidFirst.setBounds(screenWidth/4 + screenWidth/13, screenHeight/7, screenWidth/7, screenHeight/35);
		invalidFirst.setFont(labelFont);
		add(invalidFirst);

		// Error message for an invalid last name
		JLabel invalidLast = new JLabel("Invalid Last Name");
		invalidLast.setForeground(Color.RED);
		invalidLast.setBounds(screenWidth/4 + screenWidth/13, screenHeight/7 + screenHeight/30, screenWidth/7, screenHeight/35);
		invalidLast.setFont(labelFont);
		add(invalidLast);

		// Error message for not filling in all the fields
		JLabel invalidField = new JLabel("Not all required fields have been filled in");
		invalidField.setForeground(Color.RED);
		invalidField.setBounds(screenWidth/4 - screenWidth/10, screenHeight/6 + 10 * screenHeight/39, screenWidth/5, screenHeight/25);
		invalidField.setFont(labelFont);
		add(invalidField);
    
		// Error message for not selecting a faculty
		JLabel invalidFaculty = new JLabel("Please select a faculty");
		invalidFaculty.setForeground(Color.RED);
		invalidFaculty.setBounds(screenWidth/4 + screenWidth/13, screenHeight/7 + 6*screenHeight/30, screenWidth/7, screenHeight/35);
		invalidFaculty.setFont(labelFont);
		add(invalidFaculty);
		
		// Error message if email is already in use
		JLabel inUse = new JLabel("Email already in use");
		inUse.setForeground(Color.RED);
		inUse.setBounds(screenWidth/4 + screenWidth/13, screenHeight/7 + screenHeight/30 + screenHeight/30, screenWidth/7, screenHeight/35);
		inUse.setFont(labelFont);
		add(inUse);
		inUse.setVisible(false);

		// Set the error messages to be invisible, until needed
		passMatch.setVisible(false);
		passLength.setVisible(false);
		invalidEmail.setVisible(false);
		invalidID.setVisible(false);
		invalidINT.setVisible(false);
		invalidFirst.setVisible(false);
		invalidLast.setVisible(false);
		invalidField.setVisible(false);
		invalidFaculty.setVisible(false);
    
		/**
		* DROP DOWN SELECT
		*/

		// Create an empty array list that calls faculties name from a file
		faculties = new ArrayList <String>();
		// Set the users faculty to a default, this will get updated in create account method
		userFaculty = "";

		// Add faculties to the drop down menu
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
		
		// Set up the drop down menu and its properties
		DefaultComboBoxModel modelTemp = new DefaultComboBoxModel(faculties.toArray());
		facultyBox = new JComboBox(modelTemp);
		facultyBox.setBounds(screenWidth/4 - screenWidth/14, screenHeight/7 + 6*screenHeight/30, screenWidth/7, screenHeight/35);
		facultyBox.setFont(labelFontSize);
		add(facultyBox);

		// On mouse click of the drop down menu, update what was selected
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

		// Initialize the schoolRole to equal 0, as student ID is the default

		// Button used to change the label from Student ID to Professor ID
		JButton prof = new JButton("Professor");
		prof.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				profID.setVisible(true);
				studID.setVisible(false);
				schoolRole = 1;
				student = false;
				dept = true;
			}
		});
		prof.setBounds(screenWidth/4 - screenWidth/30 + screenWidth/22, screenHeight/10, screenWidth/15, screenHeight/40);
		prof.setBackground(gold);
		prof.setFont(labelFont);
		add(prof);

		// Button used to change the label from Professor ID to Student ID
		JButton stud = new JButton("Student");
		stud.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				profID.setVisible(false);
				studID.setVisible(true);
				schoolRole = 0;
				student = true;
				dept = false;
			}
		});
		stud.setBounds(screenWidth/4 - screenWidth/30 - screenWidth/22, screenHeight/10, screenWidth/15, screenHeight/40);
		stud.setBackground(gold);
		stud.setFont(labelFont);
		add(stud);

		// Button for creating an account
		JButton createAccount = new JButton("Create Account");
		createAccount.addMouseListener(new MouseAdapter() {
			@Override
			// On a mouse click, method checks that all text fields have been filled out correctly and saves to a txt file
			public void mouseClicked(MouseEvent e) {

				// Keeps track of the number of errors to occur when trying to create an account
				Integer errorCount = 0;
		
				// Set strings to be the account details given in the text fields
				String f = password.getText();
				String s = passwordConfirmation.getText();
				String enteredEmail = email.getText();
				String enteredFirst = first.getText();
				String enteredLast = last.getText();
				String enteredID = id.getText();

				// Try catch statement to determine whether we can convert the ID string to an int and then return the correct message
				try {
					// Tries to convert the given string to an integer, catches exception if not able to
					Integer enteredIDint = Integer.parseInt(enteredID.trim());
					// Returns an invalid ID error if it doesn't follow criteria for an ID
					if (!((00000000 <= enteredIDint) && (enteredIDint <= 99999999)) || !(enteredID.length() == 8) || (enteredID.contains("."))) {
						invalidID.setVisible(true);
						invalidINT.setVisible(false);
						errorCount++;
					// Returns a valid ID if it follows the criteria for an ID
					} else if ((0 <= enteredIDint && enteredIDint <= 99999999) && (enteredID.length() == 8) && !(enteredID.contains("."))) {
						invalidID.setVisible(false);
						invalidINT.setVisible(false);
					} 
				} catch(NumberFormatException nfe) {
					// An integer was not given as an ID
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
				
				// If else statement to determine if all fields have been filled in
				if (facultyBox.getSelectedIndex() == 0 || enteredFirst.isEmpty() == true || enteredLast.isEmpty() == true || enteredEmail.isEmpty() == true || enteredID.isEmpty() == true || s.isEmpty() == true || f.isEmpty() == true) {
					invalidField.setVisible(true);	
					errorCount++;
				} else {
					invalidField.setVisible(false);
				}

				// If else statement to check if the entered passwords match
				if (f.isEmpty() == true || s.isEmpty() == true){
					invalidField.setVisible(true);
					passLength.setVisible(false);
					passMatch.setVisible(false);	
					errorCount++;
				// If the length is not 10 and the password doesn't equal the confirmed password
				} else if (f.length() < 10 && !f.equals(s)) {
					passLength.setVisible(true);
					passMatch.setVisible(true);
					errorCount++;
				// If just the password doesn't equal the confirmed passowrd
				} else if (!f.equals(s)) {
					passMatch.setVisible(true);
					passLength.setVisible(false);
					errorCount++;
				// If just the password length is not 10
				} else if (f.length() < 10) {
					passMatch.setVisible(false);
					passLength.setVisible(true);
					errorCount++;
				// If the password given meets criteria
				} else if (f.equals(s)) {
					passMatch.setVisible(false);
					passLength.setVisible(false);
				}
				
				// If nothing is given as email, due to checking for characters in later if statements
				if (enteredEmail.isEmpty() == true) {
					invalidField.setVisible(true);	
					errorCount++;
				// If the email doesn't contain "@", or ".", or isn't the required email character length of the form "x@y.z"
				} else if (!(enteredEmail.contains("@")) || !(enteredEmail.contains(".")) || !(enteredEmail.length() >= 5)){
					invalidEmail.setVisible(true);
					errorCount++;
				// If the given email meets the criteria
				} else if (enteredEmail.contains("@") && enteredEmail.contains(".") && enteredEmail.length() >= 5) {
					invalidEmail.setVisible(false);
				}

				// If statement to determine if the first name only contains letters
				if (enteredFirst.chars().allMatch(Character::isLetter)){
					invalidFirst.setVisible(false);
				} else {
					invalidFirst.setVisible(true);
					errorCount++;
				}

				// If statement to determine if the last name only contains letters
				if (enteredLast.chars().allMatch(Character::isLetter)){
					invalidLast.setVisible(false);
				} else {
					invalidLast.setVisible(true);
					errorCount++;
				}
				
				
				// Call hash-map stored in authenticator class and write email and password to it 
				Authenticator authen = new Authenticator();
				//authen.emailAddress = email.getText();
				//authen.pwd = password.getText();
				// authen.fac = (String)facultyBox.getSelectedItem();

				//execute loop if boolean student is true 
				if (student) {
				
					//check to see if entered email already exists in system
					//if it does don't do anything
					//else populate the hash-amps with the desired information and 
					//save and reload them 
					if (authen.getPeopleMap().containsKey(email.getText())) {
						inUse.setVisible(true);
						errorCount++;
					}
					else if (!authen.getPeopleMap().containsKey(email.getText()) && errorCount == 0) {
						authen.getPeopleMap().put(email.getText(), password.getText());
						authen.getRolesMap().put(email.getText(), (String)facultyBox.getSelectedItem());
						authen.getNamesMap().put(email.getText(), first.getText());
						Authenticator.loadStud();
						Authenticator.saveStud();
						Authenticator.loadRoles();
						Authenticator.saveRoles();
						Authenticator.loadNames();
						Authenticator.saveNames();
						System.out.println("all accounts " + Authenticator.accounts);
						System.out.println("roles " + Authenticator.roles);
					}
				}
				
				else if (dept) {
					
					//check to see if entered email already exists in system
					//if it does don't do anything
					//else populate the hash-amps with the desired information and 
					//save and reload them 
					if (authen.getDeptMap().containsKey(email.getText())) {
						inUse.setVisible(true);
						errorCount++;
					}
					else if (!authen.getDeptMap().containsKey(email.getText()) && errorCount == 0) {
						authen.getDeptMap().put(email.getText(), password.getText());
						Authenticator.loadDep();
						Authenticator.saveDep();
						System.out.println("all departments " + Authenticator.depts);
					}
				}
				
				// If no errors have occured, then we will save this information to a txt file
				if (errorCount == 0){
					/*
					 * Write inputs from the create account fields to two separate files
					 * first file users.txt stores the name, email and id of the person
					 * second file privateInfo.txt stores email and password (what users login with)
					 * if any of the fields is empty nothing is written 
					 * if confirm password doesn't match password field error message displayed 
					 */
					//Set the frame size on the closing of the create account GUI
					frame.setBounds((screenWidth/2 - screenWidth/4), (screenHeight/2 - screenHeight/4), screenWidth/2, screenHeight/2);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					Login panel = new Login(frame);
					frame.setContentPane(panel);
					frame.revalidate();
				}	    
			}
		});
		createAccount.setBounds(screenWidth/4 - screenWidth/10, screenHeight/6 + 8*screenHeight/34 - screenHeight/128, screenWidth/10 - screenWidth/256 + screenWidth/32, screenHeight/30);
		createAccount.setBackground(gold);
		createAccount.setFont(labelFont);
		add(createAccount);
		
		//add a button called back that takes user back to main page
		//if they did not want to go to create account page
		JButton backButton = new JButton("Back");
		backButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setBounds((screenWidth/2 - screenWidth/4), (screenHeight/2 - screenHeight/4), screenWidth/2, screenHeight/2);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				Login panel = new Login(frame);
				frame.setContentPane(panel);
				frame.revalidate();
			}
		});
		backButton.setBounds(screenWidth/4 + screenWidth/256 + screenWidth/32, screenHeight/6 + 8*screenHeight/34 - screenHeight/128, screenWidth/10 - screenWidth/32, screenHeight/30);
		backButton.setBackground(gold);
		backButton.setFont(labelFont);
		add(backButton);
		
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
		background_2.setBounds(screenWidth/4 - screenWidth/6 + screenWidth/32, screenHeight/35, 300, 300);
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
