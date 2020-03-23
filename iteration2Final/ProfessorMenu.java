import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Font;

import java.awt.Color;
import javax.swing.JList;

public class ProfessorMenu extends JPanel {
	

	/**
	 * Create the panel.
	 * @param auth 
	 * @param frame 
	 */
	public ProfessorMenu(JFrame frame, Authenticator auth) {
		//Save the user's screen resolution to variables, used to format GUI correctly
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		setLayout(null);

		//Header of the system name
		JLabel header = new JLabel("UofC Professor Scholarship Portal");
		header.setBounds(154, 95, 648, 43);
		header.setForeground(Color.RED);
		header.setFont(new Font("Arial", Font.PLAIN, screenHeight/30));
		add(header);
		
		JList lstScholarships = new JList();
		lstScholarships.setBounds(174, 180, 534, 225);
		add(lstScholarships);

		//Font size for remaining labels
		Font labelFontSize = new Font("Arial", Font.PLAIN, screenHeight/60);
		
		/**
		 * TEXT FIELDS
		 */

		//Text field for searching for a scholarship name
		//Text field for inputing student they want to nominate

		/**
		 * MESSAGES
		 */

		//Create any error or confirmation messages here
		
		/**
		 * BUTTONS
		 */

		//Create a button to confirm selection of transcript selected from the list to nominate a student for (this will lead to a new class where prof can enter a student's info)

		/**
		 * LIST
		 */

		//Create a scrollable list showing all available scholarships, including details like for what term, faculty, and name of scholarship
		
	}
}