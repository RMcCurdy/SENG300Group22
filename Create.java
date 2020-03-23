/*
 * code burrowed from tutorial 
 * added a create account functionality 
 * which opens up a new window if user
 * does not have an account and 
 * wishes to create one
*/
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JTextField;

import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileWriter;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Font;
import javax.swing.*;
import java.util.List;
import java.awt.event.*;

import java.awt.Color;

public class Create extends JPanel {

	private static final long serialVersionUID = 1L;

	
	private JTextField first;
	private JTextField last;
	private JTextField email;
	private JTextField id;
	private JTextField password;
	private JTextField passwordConfirmation;
	private Integer schoolRole;
	private String userFaculty;
	private List <String> faculties;
	private JComboBox facultyBox;


	/**
	 * Create the panel.
	 * @param auth 
	 * @param frame 
	 */

	public Create(JFrame frame) {
		//Save the user's screen resolution to variables, used to format GUI correctly
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;

		setLayout(null);

		/**
		 * LABELS
		 */

		//Header of the system name
		JLabel header = new JLabel("UofC Scholarship Portal");
		header.setForeground(Color.RED);
		header.setBounds(screenWidth/4 - screenWidth/8, screenHeight/25, screenWidth/4, screenHeight/25);
		header.setFont(new Font("Arial", Font.PLAIN, screenHeight/30));
		add(header);

		//Font size for remaining labels
		Font labelFontSize = new Font("Arial", Font.PLAIN, screenHeight/60);
		
		//Labels "areYouALabel", "orLabel", and "qMarkLabel" to make it clear to the user with regards to student or professor buttons
		JLabel areYouALabel = new JLabel("Are you a");
		areYouALabel.setBounds(screenWidth/4 - screenWidth/8 - screenWidth/140, screenHeight/10, screenWidth/15, screenHeight/40);
		areYouALabel.setFont(labelFontSize);
		add(areYouALabel);

		JLabel orLabel = new JLabel("or");
		orLabel.setBounds(screenWidth/4 - screenWidth/200, screenHeight/10, screenWidth/15, screenHeight/40);
		orLabel.setFont(labelFontSize);
		add(orLabel);

		JLabel qMarkLabel = new JLabel("?");
		qMarkLabel.setBounds(screenWidth/4 + screenWidth/12, screenHeight/10, screenWidth/15, screenHeight/40);
		qMarkLabel.setFont(labelFontSize);
		add(qMarkLabel);

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

		//Label for the email text field
		JLabel emailLabel = new JLabel("Email:");
		emailLabel.setBounds(screenWidth/4 - screenWidth/7 + screenWidth/29, screenHeight/7 + 2*screenHeight/30, screenWidth/7, screenHeight/35);
		emailLabel.setFont(labelFontSize);
		add(emailLabel);

		//Label for the student ID text field
		JLabel studID = new JLabel("Student ID:");
		studID.setBounds(screenWidth/4 - screenWidth/7 + screenWidth/100, screenHeight/7 + 3*screenHeight/30, screenWidth/7, screenHeight/35);
		studID.setFont(labelFontSize);
		add(studID);

		//Label to indicate it is a professor's ID for the ID text field
		JLabel profID = new JLabel("Professor ID:");
		profID.setBounds(screenWidth/4 - screenWidth/7 + screenWidth/400, screenHeight/7 + 3*screenHeight/30, screenWidth/7, screenHeight/35);
		profID.setFont(labelFontSize);
		add(profID);
		profID.setVisible(false);

		//Label for the password text field
		JLabel pwd = new JLabel("Password:");
		pwd.setBounds(screenWidth/4 - screenWidth/7 + screenWidth/70, screenHeight/7 + 4*screenHeight/30, screenWidth/7, screenHeight/35);
		pwd.setFont(labelFontSize);
		add(pwd);

		//Label for the confirm password text field
		JLabel confirmPwd = new JLabel("Confirm Password:");
		confirmPwd.setBounds(screenWidth/4 - screenWidth/6 - screenWidth/250, screenHeight/7 + 5*screenHeight/30, screenWidth/7, screenHeight/35);
		confirmPwd.setFont(labelFontSize);
		add(confirmPwd);
    
    //Label for faculty in drop down menu
		JLabel faculty = new JLabel("Faculty:");
		faculty.setBounds(screenWidth/4 - screenWidth/8 + screenWidth/115, screenHeight/7 + 6*screenHeight/30, screenWidth/7, screenHeight/35);
		faculty.setFont(labelFontSize);
		add(faculty);
		
		/**
		 * TEXT FIELDS
		 */

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
		
		//Text field for the email
		email = new JTextField();
		email.setBounds(screenWidth/4 - screenWidth/14, screenHeight/7 + 2*screenHeight/30, screenWidth/7, screenHeight/35);
		email.setFont(labelFontSize);
		add(email);
		
		//Text field for the ID
		id = new JTextField();
		id.setBounds(screenWidth/4 - screenWidth/14, screenHeight/7 + 3*screenHeight/30, screenWidth/7, screenHeight/35);
		id.setFont(labelFontSize);
		add(id);
		
		//Text field for the password
		password = new JPasswordField();
		password.setBounds(screenWidth/4 - screenWidth/14, screenHeight/7 + 4*screenHeight/30, screenWidth/7, screenHeight/35);
		password.setFont(labelFontSize);
		add(password);
		
		//Text field for the confirmed password
		passwordConfirmation = new JPasswordField();
		passwordConfirmation.setBounds(screenWidth/4 - screenWidth/14, screenHeight/7 + 5*screenHeight/30, screenWidth/7, screenHeight/35);
		passwordConfirmation.setFont(labelFontSize);
		add(passwordConfirmation);
		
		/**
		 * MESSAGES
		 */

		//Error message for when passwords entered do not match
		JLabel passMatch = new JLabel("Passwords don't match");
		passMatch.setForeground(Color.RED);
		passMatch.setBounds(screenWidth/4 + screenWidth/13, screenHeight/7 + 5*screenHeight/30, screenWidth/7, screenHeight/35);
		passMatch.setFont(labelFontSize);
		add(passMatch);

		//Error message for the password not meeting the required length
		JLabel passLength = new JLabel("Invalid Password length, needs 10 characters");
		passLength.setForeground(Color.RED);
		passLength.setBounds(screenWidth/4 + screenWidth/13, screenHeight/7 + 4*screenHeight/30, screenWidth/6, screenHeight/35);
		passLength.setFont(new Font("Arial", Font.PLAIN, screenHeight/90));
		add(passLength);
		
		//Error message for an invalid email
		JLabel invalidEmail = new JLabel("Invalid Email");
		invalidEmail.setForeground(Color.RED);
		invalidEmail.setBounds(screenWidth/4 + screenWidth/13, screenHeight/7 + 2*screenHeight/30, screenWidth/7, screenHeight/35);
		invalidEmail.setFont(labelFontSize);
		add(invalidEmail);

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
		invalidField.setBounds(screenWidth/4 - screenWidth/10, screenHeight/6 + 9 * screenHeight/40, screenWidth/5, screenHeight/25);
		invalidField.setFont(labelFontSize);
		add(invalidField);
    
    JLabel invalidFaculty = new JLabel("Please select a faculty");
		invalidFaculty.setForeground(Color.RED);
		invalidFaculty.setBounds(screenWidth/4 + screenWidth/13, screenHeight/7 + 6*screenHeight/30, screenWidth/7, screenHeight/35);
		invalidFaculty.setFont(labelFontSize);
		add(invalidFaculty);

		//Set the error messages to be invisible, until needed
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

		//Create an empty array list that calls faculties name from a file
		faculties = new ArrayList <String>();
		//Set the users faculty to a default, this will get updated in create account method
		userFaculty = "";

		//Add faculties to the drop down menu
		faculties.add("");
		for (int i = 0; i < 5; i++) {
			faculties.add("Faculty " + (i + 1));
		}
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

		//Initialize the schoolRole to equal 0, as student ID is the default
		schoolRole = 0;

		//Button used to change the label from Student ID to Professor ID
		JButton prof = new JButton("Professor");
		prof.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				profID.setVisible(true);
				studID.setVisible(false);
				schoolRole = 1;
			}
		});
		prof.setBounds(screenWidth/4 - screenWidth/30 + screenWidth/22, screenHeight/10, screenWidth/15, screenHeight/40);
		prof.setFont(labelFontSize);
		add(prof);

		//Button used to change the label from Professor ID to Student ID
		JButton stud = new JButton("Student");
		stud.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				profID.setVisible(false);
				studID.setVisible(true);
				schoolRole = 0;
			}
		});
		stud.setBounds(screenWidth/4 - screenWidth/30 - screenWidth/22, screenHeight/10, screenWidth/15, screenHeight/40);
		stud.setFont(labelFontSize);
		add(stud);

		//Button for creating an account
		JButton createAccount = new JButton("Create Account");
		createAccount.addMouseListener(new MouseAdapter() {
			@Override
			//On a mouse click, method checks that all text fields have been filled out correctly and saves to a txt file
			public void mouseClicked(MouseEvent e) {

				//Keeps track of the number of errors to occur when trying to create an accoun
				Integer errorCount = 0;
		
				//Set strings to be the account details given in the text fields
				String f = password.getText();
				String s = passwordConfirmation.getText();
				String enteredEmail = email.getText();
				String enteredFirst = first.getText();
				String enteredLast = last.getText();
				String enteredID = id.getText();

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
				if (facultyBox.getSelectedIndex() == 0 || enteredFirst.isEmpty() == true || enteredLast.isEmpty() == true || enteredEmail.isEmpty() == true || enteredID.isEmpty() == true || s.isEmpty() == true || f.isEmpty() == true) {
					invalidField.setVisible(true);	
					errorCount++;
				} else {
					invalidField.setVisible(false);
				}

				//If else statement to check if the entered passwords match
				if (f.isEmpty() == true || s.isEmpty() == true){
					invalidField.setVisible(true);
					passLength.setVisible(false);
					passMatch.setVisible(false);	
					errorCount++;
				//If the length is not 10 and the password doesn't equal the confirmed password
				} else if (f.length() < 10 && !f.equals(s)) {
					passLength.setVisible(true);
					passMatch.setVisible(true);
					errorCount++;
				//If just the password doesn't equal the confirmed passowrd
				} else if (!f.equals(s)) {
					passMatch.setVisible(true);
					passLength.setVisible(false);
					errorCount++;
				//If just the password length is not 10
				} else if (f.length() < 10) {
					passMatch.setVisible(false);
					passLength.setVisible(true);
					errorCount++;
				//If the password given meets criteria
				} else if (f.equals(s)) {
					passMatch.setVisible(false);
					passLength.setVisible(false);
				}
				
				//If nothing is given as email, due to checking for characters in later if statements
				if (enteredEmail.isEmpty() == true) {
					invalidField.setVisible(true);	
					errorCount++;
				//If the email doesn't contain "@", or ".", or isn't the required email character length of the form "x@y.z"
				} else if (!(enteredEmail.contains("@")) || !(enteredEmail.contains(".")) || !(enteredEmail.length() >= 5)){
					invalidEmail.setVisible(true);
					errorCount++;
				//If the given email meets the criteria
				} else if (enteredEmail.contains("@") && enteredEmail.contains(".") && enteredEmail.length() >= 5) {
					invalidEmail.setVisible(false);
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
							Account account = new Account(email.getText(), first.getText(), last.getText(), Integer.parseInt(id.getText()), schoolRole, userFaculty);
							
							// "accountInformation.txt" stores accounts, i.e. email, name, etc. Passwords aren't stored here
							BufferedWriter bw = new BufferedWriter(new FileWriter("accountInformation.txt", true));
							bw.write(account.toString());
							bw.newLine();
							bw.close();
							
							// "accountLogins.txt" stores account emails + passwords. aka the login info
							BufferedWriter bw1 = new BufferedWriter(new FileWriter("accountLogins.txt", true));
							bw1.write(email.getText());
							bw1.newLine();
							bw1.close();
						} 
					catch(Exception ex) 
						{
						//Exception thrown if the above code can't proceed
							ex.printStackTrace();
						}
					//Set the frame size on the closing of the create account GUI
					frame.setBounds((screenWidth/2 - screenWidth/4), (screenHeight/2 - screenHeight/4), screenWidth/2, screenHeight/2);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					Login panel = new Login(frame);
					frame.setContentPane(panel);
					frame.revalidate();
				}	    
			}
		});
		createAccount.setBounds(screenWidth/4 - screenWidth/20, screenHeight/6 + 8 * screenHeight/37, screenWidth/10, screenHeight/30);
		createAccount.setFont(labelFontSize);
		add(createAccount);
	}
}