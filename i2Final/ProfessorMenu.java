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

public class ProfessorMenu extends JPanel {

	private static final long serialVersionUID = 1L;
	
	//NEED VARIABLES FOR NEW MENU

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

		/**
		 * LABELS
		 */

		//Header of the system name
		JLabel header = new JLabel("UofC Student Scholarship Portal");
		header.setForeground(Color.RED);
		header.setBounds(180, 36, 561, 36);
		header.setFont(new Font("Arial", Font.PLAIN, screenHeight/30));
		add(header);

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
