import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;

public class StudentMenu extends JPanel {
	private JList list;
	//available scholarships and term
	private static String[] scholarships = {"School Of Arts:Fall", "School Of Arts:Winter", "School Of Arts:Academic Year","School Of Medicine:Fall", "School Of Medicine:Winter", "School Of Medicine:Academic Year","School Of Architecture:Fall", "School Of Architecture:Winter", "School Of Architecture:Academic Year","School Of Business:Fall", "School Of Business:Winter", "School Of Business:Academic Year","School Of Kinesiology:Fall", "School Of Kinesiology:Winter", "School Of Kinesiology:Academic Year","School Of Law:Fall", "School Of Law:Winter", "School Of Law:Academic Year","School Of Nursing:Fall", "School Of Nursing:Winter", "School Of Nursing:Academic Year","School Of Engineering:Fall", "School Of Engineering:Winter", "School Of Engineering:Academic Year","School Of Social Work:Fall", "School Of Social Work:Winter", "School Of Social Work:Academic Year","School Of Education:Fall", "School Of Education:Winter", "School Of Education:Academic Year"};
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	
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

    JTextPane txtpnAsd = new JTextPane();
		txtpnAsd.setText("The following info should be correct: "+
				"\nEmail: " + user.getEmail() +
				"\nFirst Name: " + user.getFirstName() +
				"\nLast Name: " + user.getLastName() + 
				"\nID: " + user.getID() + 
				"\nType: " + user.getType() );
		txtpnAsd.setBounds(240, 140, 480, 200);
		add(txtpnAsd);
    
		/**
		 * 
		 */
		//creating list containing scholarships
		list = new JList(scholarships);
		list.setFont(new Font("Arial", Font.PLAIN, 12));
		list.setSize(218, 80);
		list.setLocation(145, 159);
		//only 1 item can be selected and list will only display 3 items
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		list.setVisibleRowCount(3);
		list.setBackground(Color.WHITE);
		//label for testing
		JLabel label1 = new JLabel("");
		label1.setFont(new Font("Arial", Font.PLAIN, 13));
		label1.setBackground(Color.WHITE);
		label1.setBounds(112, 292, 298, 26);
		add(label1);
		//scrollpane for list
		JScrollPane sp = new JScrollPane(list);
		JButton button = new JButton("Select");
		button.setBounds(203, 248, 81, 26);
	    add(button);
	    button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (list.getSelectedIndex() != 0) {
						//Save the selected faculty & term 
						String selec = (String)list.getSelectedValue();
						label1.setText(selec);
					} else {
						label1.setText("...");
					}
            	} catch (Exception e1) {

				}
			
			}
	    });
	    //for scroll pane
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
		
		//search bar for list of scholarships
		textField = new JTextField();
		textField.setBounds(183, 159, 190, 26);
		add(textField);
		textField.setColumns(10);
		JLabel lblNewLabel = new JLabel("Search:");
		lblNewLabel.setBounds(136, 164, 61, 16);
		add(lblNewLabel);

	}
}