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
import java.io.BufferedWriter;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Font;

import java.awt.Color;

public class Create extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField fname;
	private JTextField lname;
	private JTextField email;
	private JTextField id;
	private JTextField fpwd;
	private JTextField spwd;
	
	/**
	 * Create the panel.
	 * @param auth 
	 * @param frame 
	 */
	public Create(JFrame frame, Authenticator auth) {
		//Save the user's screen resolution to the integers
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
		
		/**
		 * TEXT FIELDS
		 */

		//Text field for the first name
		fname = new JTextField();
		fname.setBounds(screenWidth/4 - screenWidth/14, screenHeight/7, screenWidth/7, screenHeight/35);
		fname.setFont(labelFontSize);
		add(fname);
		
		//Text field for the last name
		lname = new JTextField();
		lname.setBounds(screenWidth/4 - screenWidth/14, screenHeight/7 + screenHeight/30, screenWidth/7, screenHeight/35);
		lname.setFont(labelFontSize);
		add(lname);
		
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
		fpwd = new JPasswordField();
		fpwd.setBounds(screenWidth/4 - screenWidth/14, screenHeight/7 + 4*screenHeight/30, screenWidth/7, screenHeight/35);
		fpwd.setFont(labelFontSize);
		add(fpwd);
		
		//Text field for the confirmed password
		spwd = new JPasswordField();
		spwd.setBounds(screenWidth/4 - screenWidth/14, screenHeight/7 + 5*screenHeight/30, screenWidth/7, screenHeight/35);
		spwd.setFont(labelFontSize);
		add(spwd);
		
		/**
		 * MESSAGES
		 */

		//Error message for when passwords entered do not match
		JLabel passMatch = new JLabel("Passwords don't match");
		passMatch.setForeground(Color.RED);
		passMatch.setBounds(screenWidth/4 + screenWidth/13, screenHeight/7 + 5*screenHeight/30, screenWidth/7, screenHeight/35);
		passMatch.setFont(labelFontSize);
		add(passMatch);
		
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

		//Error message for an invalid last name
		JLabel invalidField = new JLabel("Not all required fields have been filled in");
		invalidField.setForeground(Color.RED);
		invalidField.setBounds(screenWidth/4 - screenWidth/10, screenHeight/6 + 9 * screenHeight/40, screenWidth/5, screenHeight/25);
		invalidField.setFont(labelFontSize);
		add(invalidField);

		//Set the error messages to be invisible, until needed
		passMatch.setVisible(false);
		invalidEmail.setVisible(false);
		invalidID.setVisible(false);
		invalidFirst.setVisible(false);
		invalidLast.setVisible(false);
		invalidField.setVisible(false);
		
		/**
		 * BUTTONS
		 */

		//Button for creating an account
		JButton createAccount = new JButton("Create Account");
		createAccount.addMouseListener(new MouseAdapter() {
			@Override
			//On a mouse click, method checks that all text fields have been filled out correctly and saves to a txt file
			public void mouseClicked(MouseEvent e) {

				//Keeps track of the number of errors to occur when trying to create an accoun
				Integer errorCount = 0;
		
				//Get the account details to test if correctly inputed
				String f = fpwd.getText();
				String s = spwd.getText();
				String enteredEmail = email.getText();
				String enteredFirst = fname.getText();
				String enteredLast = lname.getText();
				String enteredID = id.getText();
				Integer enteredIDint = Integer.parseInt(enteredID);
				
				//If statements to determine which error messages to display
				if (enteredFirst.equals("") || enteredLast.equals("") || enteredEmail.equals("") || enteredEmail.equals("") || s.equals("") || f.equals("")) {
					invalidField.setVisible(true);	
					errorCount++;
				} else {
					invalidField.setVisible(false);
				}

				if (!f.equals(s)) {
					passMatch.setVisible(true);
					errorCount++;
				} else if (f.equals(s)) {
					passMatch.setVisible(false);
				}

				if (!(0 <= enteredIDint && enteredIDint <= 99999999) || !(Integer.toString(enteredIDint).length() == 8) || (enteredID.contains("."))) {
					invalidID.setVisible(true);
					errorCount++;
				} else if ((0 <= enteredIDint && enteredIDint <= 99999999) && (Integer.toString(enteredIDint).length() == 8) && !(enteredID.contains("."))) {
					invalidID.setVisible(false);
				} 
				
				if (!(enteredEmail.contains("@")) || !(enteredEmail.contains("."))){
					invalidEmail.setVisible(true);
					errorCount++;
				} else if (enteredEmail.contains("@") && enteredEmail.contains(".") && enteredEmail.length() > 5) {
					invalidEmail.setVisible(false);
				}

				if (enteredFirst.chars().allMatch(Character::isLetter)){
					invalidFirst.setVisible(false);
				} else {
					invalidFirst.setVisible(true);
					errorCount++;
				}

				if (enteredLast.chars().allMatch(Character::isLetter)){
					invalidLast.setVisible(false);
				} else {
					invalidLast.setVisible(true);
					errorCount++;
				}

				if (errorCount == 0){
					/*
					 * Write inputs from the create account fields to two separate files
					 * first file users.txt stores the name, email and id of the person
					 * second file privateInfo.txt stores email and password (what users login with)
					 * if any of the fields is empty nothing is written 
					 * if confirm password doesn't match password field error message displayed 
					 */
					try { 
						invalidField.setVisible(false);
						BufferedWriter bw = new BufferedWriter(new FileWriter("users.txt", true));
						bw.write(fname.getText() + " " + lname.getText());
						bw.newLine();
						bw.write(email.getText());
						bw.newLine();
						bw.write(id.getText());
						bw.newLine();
						bw.close();
						BufferedWriter bw1 = new BufferedWriter(new FileWriter("privateInfo.txt", true));
						bw1.write(email.getText());
						bw1.newLine();
						bw1.write(f);
						bw1.newLine();
						bw1.write("");
						bw1.newLine();
						bw1.close();
					} catch(Exception ex) {
						ex.printStackTrace();
					}
					//Set the frame size on close of the create account GUI
					frame.setBounds((screenWidth/2 - screenWidth/4), (screenHeight/2 - screenHeight/4), screenWidth/2, screenHeight/2);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					Login panel = new Login(frame, auth);
					frame.setContentPane(panel);
					frame.revalidate();
				}	    
			}
		});
		createAccount.setBounds(screenWidth/4 - screenWidth/20, screenHeight/6 + 7 * screenHeight/38, screenWidth/10, screenHeight/30);
		createAccount.setFont(labelFontSize);
		add(createAccount);
		
		JButton prof = new JButton("Professor");
		prof.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				profID.setVisible(true);
				studID.setVisible(false);
			}
		});
		prof.setBounds(screenWidth/4 - screenWidth/30 + screenWidth/22, screenHeight/10, screenWidth/15, screenHeight/40);
		prof.setFont(labelFontSize);
		add(prof);
		
		JButton stud = new JButton("Student");
		stud.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				profID.setVisible(false);
				studID.setVisible(true);
			}
		});
		stud.setBounds(screenWidth/4 - screenWidth/30 - screenWidth/22, screenHeight/10, screenWidth/15, screenHeight/40);
		stud.setFont(labelFontSize);
		add(stud);
	}
}
