import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.AbstractListModel;

public class StudentMenu extends JPanel {
	private JList list;
	private static String[] scholarships = {"School Of Arts:Fall", "School Of Arts:Winter", "School Of Arts:Academic Year","School Of Medicine:Fall", "School Of Medicine:Winter", "School Of Medicine:Academic Year","School Of Architecture:Fall", "School Of Architecture:Winter", "School Of Architecture:Academic Year","School Of Business:Fall", "School Of Business:Winter", "School Of Business:Academic Year","School Of Kinesiology:Fall", "School Of Kinesiology:Winter", "School Of Kinesiology:Academic Year","School Of Law:Fall", "School Of Law:Winter", "School Of Law:Academic Year","School Of Nursing:Fall", "School Of Nursing:Winter", "School Of Nursing:Academic Year","School Of Engineering:Fall", "School Of Engineering:Winter", "School Of Engineering:Academic Year","School Of Social Work:Fall", "School Of Social Work:Winter", "School Of Social Work:Academic Year","School Of Education:Fall", "School Of Education:Winter", "School Of Education:Academic Year"};
	

	private static final long serialVersionUID = 1L;
	
	//NEED VARIABLES FOR NEW MENU

	/**
	 * Create the panel.
	 * @param auth 
	 * @param frame 
	 */
	public StudentMenu(JFrame frame, Authenticator auth) {
		//Save the user's screen resolution to variables, used to format GUI correctly
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;

		setLayout(null);

		/**
		 * LABELS
		 */

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
		list = new JList(scholarships);
		list.setSize(218, 80);
		list.setLocation(145, 159);
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		list.setVisibleRowCount(3);
		list.setBackground(Color.WHITE);
		JScrollPane sp = new JScrollPane(list);
		JButton button = new JButton("Facaulty & Scholarship Selected");
	    add(button);
	    button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Selected values:");
				String selectedItem = (String) list.getSelectedValue();
				System.out.println(selectedItem);
			}
	     });
	    	
	    
		sp.setLocation(136, 189);
		sp.setSize(237, 55);
		add(sp);
		setVisible(true);
			
		//Header of the system name
		JLabel header = new JLabel("UofC Student Scholarship Portal");
		header.setHorizontalAlignment(SwingConstants.CENTER);
		header.setForeground(Color.RED);
		header.setBounds(21, 6, 438, 36);
		header.setFont(new Font("Arial", Font.PLAIN, screenHeight/30));
		add(header);
		
		

		//Create a scrollable list showing all available scholarships, including details like for what term, faculty, and name of scholarship
	}
}
