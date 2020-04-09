import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.Color;
import java.awt.Component;

import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StudentMenu extends JPanel {
	private JList list;
	private JList list2;
	//available scholarships and term
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

		//Font size for remaining labels
		Font labelFontSize = new Font("Arial", Font.PLAIN, screenHeight/60);

		Authenticator authen = new Authenticator();
		
		String facs = (String)authen.getRolesMap().get(Login.eAddress());

		//Header of the system name
		JLabel header = new JLabel("UofC Student Scholarship Portal");
		header.setHorizontalAlignment(SwingConstants.CENTER);
		header.setForeground(Color.RED);
		header.setBounds(screenWidth/4 - screenWidth/6, screenHeight/25, screenWidth/3, screenHeight/25);
		header.setFont(new Font("Arial", Font.PLAIN, screenHeight/30));
		add(header);
		
		
		JLabel userLabel = new JLabel("User: " + Login.eAddress());
		userLabel.setBounds(screenWidth/80, screenHeight/85, screenWidth/8, screenHeight/30);
		add(userLabel);
		
		
		JLabel facultyLabel = new JLabel("Faculty: " + facs);
		facultyLabel.setBounds(screenWidth/80, screenHeight/35, screenWidth/8, screenHeight/30);
		add(facultyLabel);
		
		JButton btnApplyScholarship = new JButton("Apply Scholarship");
		btnApplyScholarship.setFont(labelFontSize);
		btnApplyScholarship.setBounds(screenWidth/6 + screenWidth/50 - screenWidth/8/2 - screenWidth/175, screenHeight/8 + screenHeight/100, screenWidth/8, screenHeight/30);
		btnApplyScholarship.addMouseListener(new MouseAdapter() {
			@Override
			// On the click of this button, create a new instance of the frame with the AddScholarship class
			public void mouseClicked(MouseEvent arg0) {
				frame.setBounds((screenWidth/2 - screenWidth/4), (screenHeight/2 - screenHeight/4), screenWidth/2, screenHeight/2);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				MyScholarships panel = new MyScholarships(frame, user);
				frame.setContentPane(panel);
				frame.revalidate();
			}
		});
		add(btnApplyScholarship);
		
		// Button that takes the user to the Edit Scholarship menu
		JButton UploadTranscriptButt= new JButton("Upload Transcript");
		UploadTranscriptButt.setFont(labelFontSize);
		UploadTranscriptButt.setBounds(screenWidth/6 + screenWidth/50 - screenWidth/8/2 - screenWidth/175, screenHeight/8 + 2 * screenHeight/30 + screenHeight/100, screenWidth/8, screenHeight/30);
		UploadTranscriptButt.addMouseListener(new MouseAdapter() {
			@Override
			// On click of this button, create a new instance of the frame with the EditScholarship class
			public void mouseClicked(MouseEvent e) {
				frame.setBounds((screenWidth/2 - screenWidth/4), (screenHeight/2 - screenHeight/4), screenWidth/2, screenHeight/2);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				MyScholarships panel = new MyScholarships(frame, user);
				frame.setContentPane(panel);
				frame.revalidate();
			}
		});
		add(UploadTranscriptButt);
		
		// Button that takes the user to the Award Scholarship menu
		JButton MyScholarshipsButt = new JButton("My Scholarships");
		MyScholarshipsButt.setFont(labelFontSize);
		MyScholarshipsButt.setBounds(screenWidth/6 + screenWidth/50 + screenWidth/8/2 + screenWidth/175, screenHeight/8 + screenHeight/100, screenWidth/8, screenHeight/30);
		MyScholarshipsButt.addMouseListener(new MouseAdapter() {
		@Override
		// On click of this button, create a new instance of the frame with the AwardScholarship class
		public void mouseClicked(MouseEvent e) {
			frame.setBounds((screenWidth/2 - screenWidth/4), (screenHeight/2 - screenHeight/4), screenWidth/2, screenHeight/2);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			MyScholarships panel = new MyScholarships(frame, user);
			frame.setContentPane(panel);
			frame.revalidate();
		}
		});
		add(MyScholarshipsButt);
			
		// Button that takes the user to the Statistics menu
		JButton logOutButton = new JButton("Logout");
		logOutButton.addMouseListener(new MouseAdapter() {
		@Override
		// On click of this button, create a new instance of the frame with the Statistics class
		public void mouseClicked(MouseEvent e) {
			frame.setBounds((screenWidth/2 - screenWidth/4), (screenHeight/2 - screenHeight/4), screenWidth/2, screenHeight/2);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			Login panel = new Login(frame);
			frame.setContentPane(panel);
			frame.revalidate();
		}
		});
		logOutButton.setBounds(screenWidth/6 + screenWidth/50 + screenWidth/8/2 + screenWidth/175, screenHeight/8 + 2 * screenHeight/30 + screenHeight/100, screenWidth/8, screenHeight/30);
		logOutButton.setFont(labelFontSize);
		add(logOutButton);

	}
}