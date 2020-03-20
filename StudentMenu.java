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
import javax.swing.JTextPane;

public class StudentMenu extends JPanel {

	private static final long serialVersionUID = 1L;
	
	//NEED VARIABLES FOR NEW MENU

	/**
	 * Create the panel.
	 * @param auth 
	 * @param frame 
	 */
	public StudentMenu(JFrame frame, Account user) {
		//Save the user's screen resolution to variables, used to format GUI correctly
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;

		setLayout(null);

		/**
		 * LABELS
		 */

		//Header of the system name
		JLabel header = new JLabel("UofC Student Scholarship Portal");
		header.setForeground(Color.RED);
		header.setBounds(screenWidth/4 - screenWidth/8, screenHeight/25, screenWidth/4, screenHeight/25);
		header.setFont(new Font("Arial", Font.PLAIN, screenHeight/30));
		add(header);
		
		JTextPane txtpnAsd = new JTextPane();
		txtpnAsd.setText("The following info should be correct: "+
				"\nEmail: " + user.getEmail() +
				"\nFirst Name: " + user.getFirstName() +
				"\nLast Name: " + user.getLastName() + 
				"\nID: " + user.getID() + 
				"\nType: " + user.getType() );
		txtpnAsd.setBounds(240, 140, 480, 200);
		add(txtpnAsd);

		//Font size for remaining labels
		Font labelFontSize = new Font("Arial", Font.PLAIN, screenHeight/60);
			
		/**
		 * TEXT FIELDS
		 */

		//Text field for searching for a scholarship name
		//Text field for inputing student's GPA (They will need to upload actual "transcript" for confirmation of this)

		/**
		 * MESSAGES
		 */

		//Create any error or confirmation messages here
		//Error message if student doesn't meet requirements for a specific scholarship
		
		/**
		 * BUTTONS
		 */

		//Create a button to upload a "transcript" to the system
		//Create a button to confirm selection of transcript selected from the list to apply for
		//Create a button to allow user to navigate to page of scholarships they have applied to and can check status

		/**
		 * LIST
		 */

		//Create a scrollable list showing all available scholarships, including details like for what term, faculty, and name of scholarship
	}
}
