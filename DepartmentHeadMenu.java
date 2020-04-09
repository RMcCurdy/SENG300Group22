import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.Color;

public class DepartmentHeadMenu extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Creates a menu with multiple buttons
	 * This allows the user to choose what they want to do in this menu
	 * With each button click, the method will call for a new interface with the proper functions associated with it
	 * @param user
	 * @param frame 
	 * @author Robert McCurdy
	 */
	public DepartmentHeadMenu(JFrame frame, Account user) {
		// Save the user's screen resolution to variables, used to format GUI correctly
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;

		// Initialized the layout to have no perameters
		setLayout(null);

		// Font size for remaining labels
		Font labelFontSize = new Font("Arial", Font.PLAIN, screenHeight/60);

		// Header of the system name
		JLabel header = new JLabel("UofC Scholarship Coordinator Portal");
		header.setBounds(screenWidth/4 - screenWidth/6 + screenWidth/175, screenHeight/25, screenWidth/3, screenHeight/25);
		header.setForeground(Color.RED);
		header.setFont(new Font("Arial", Font.PLAIN, screenHeight/30));
		add(header);
		
		// Each button below contains "set" methods in order to size and shape them to their specific needs

		// Button that takes the user to the Add Scholarship menu
		JButton btnAddScholarship = new JButton("Add Scholarship");
		btnAddScholarship.setFont(labelFontSize);
		btnAddScholarship.setBounds(screenWidth/6 + screenWidth/50 - screenWidth/8/2 - screenWidth/175, screenHeight/8 + screenHeight/100, screenWidth/8, screenHeight/30);
		btnAddScholarship.addMouseListener(new MouseAdapter() {
			@Override
			// On the click of this button, create a new instance of the frame with the AddScholarship class
			public void mouseClicked(MouseEvent arg0) {
				frame.setBounds((screenWidth/2 - screenWidth/4), (screenHeight/2 - screenHeight/4), screenWidth/2, screenHeight/2);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				AddScholarship sch = new AddScholarship(frame, user);
				frame.setContentPane(sch);
				frame.revalidate();
			}
		});
		add(btnAddScholarship);
		
		// Button that takes the user to the Edit Scholarship menu
		JButton btnEditScholarship = new JButton("Edit Scholarship");
		btnEditScholarship.setFont(labelFontSize);
		btnEditScholarship.setBounds(screenWidth/6 + screenWidth/50 - screenWidth/8/2 - screenWidth/175, screenHeight/8 + 2 * screenHeight/30 + screenHeight/100, screenWidth/8, screenHeight/30);
		btnEditScholarship.addMouseListener(new MouseAdapter() {
			@Override
			// On click of this button, create a new instance of the frame with the EditScholarship class
			public void mouseClicked(MouseEvent e) {
				frame.setBounds((screenWidth/2 - screenWidth/4), (screenHeight/2 - screenHeight/4), screenWidth/2, screenHeight/2);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				EditScholarship panel = new EditScholarship(frame, user);
				frame.setContentPane(panel);
				frame.revalidate();
			}
		});
		add(btnEditScholarship);
		
		// Button that takes the user to the Remove Scholarship menu
		JButton btnRemoveScholarship = new JButton("Remove Scholarship");
		btnRemoveScholarship.setFont(labelFontSize);
		btnRemoveScholarship.setBounds(screenWidth/6 + screenWidth/50 - screenWidth/8/2 - screenWidth/175, screenHeight/8 + 4 * screenHeight/30 + screenHeight/100, screenWidth/8, screenHeight/30);
		btnRemoveScholarship.addMouseListener(new MouseAdapter() {
			@Override
			// On click of this button, create a new instance of the frame with the RemoveScholarship class
			public void mouseClicked(MouseEvent e) {
				frame.setBounds((screenWidth/2 - screenWidth/4), (screenHeight/2 - screenHeight/4), screenWidth/2, screenHeight/2);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				RemoveScholarship panel = new RemoveScholarship(frame, user);
				frame.setContentPane(panel);
				frame.revalidate();
			}
		});
		add(btnRemoveScholarship);

		// Button that takes the user to the Award Scholarship menu
		JButton btnAwardScholarship = new JButton("Award Scholarship");
		btnAwardScholarship.setFont(labelFontSize);
		btnAwardScholarship.setBounds(screenWidth/6 + screenWidth/50 + screenWidth/8/2 + screenWidth/175, screenHeight/8 + screenHeight/100, screenWidth/8, screenHeight/30);
		btnAwardScholarship.addMouseListener(new MouseAdapter() {
			@Override
			// On click of this button, create a new instance of the frame with the AwardScholarship class
			public void mouseClicked(MouseEvent e) {
				frame.setBounds((screenWidth/2 - screenWidth/4), (screenHeight/2 - screenHeight/4), screenWidth/2, screenHeight/2);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				AwardScholarship panel = new AwardScholarship(frame, user);
				frame.setContentPane(panel);
				frame.revalidate();
			}
		});
		add(btnAwardScholarship);
	
		// Button that takes the user to the Statistics menu
		JButton statisticsButton = new JButton("Statistics");
		statisticsButton.addMouseListener(new MouseAdapter() {
			@Override
			// On click of this button, create a new instance of the frame with the Statistics class
			public void mouseClicked(MouseEvent e) {
				frame.setBounds((screenWidth/2 - screenWidth/4), (screenHeight/2 - screenHeight/4), screenWidth/2, screenHeight/2);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				Statistics panel = new Statistics(frame, user);
				frame.setContentPane(panel);
				frame.revalidate();
			}
		});
		statisticsButton.setBounds(screenWidth/6 + screenWidth/50 + screenWidth/8/2 + screenWidth/175, screenHeight/8 + 2 * screenHeight/30 + screenHeight/100, screenWidth/8, screenHeight/30);
		statisticsButton.setFont(labelFontSize);
		add(statisticsButton);

		// Button that takes the user back to the Login menu
		JButton logOutButton = new JButton("Log Out");
		logOutButton.addMouseListener(new MouseAdapter() {
			@Override
			// On click of this button, create a new instance of the frame with the Login class
			public void mouseClicked(MouseEvent e) {
				frame.setBounds((screenWidth/2 - screenWidth/4), (screenHeight/2 - screenHeight/4), screenWidth/2, screenHeight/2);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				Login panel = new Login(frame);
				frame.setContentPane(panel);
				frame.revalidate();
			}
		});
		logOutButton.setBounds(screenWidth/6 + screenWidth/50 + screenWidth/8/2 + screenWidth/175, screenHeight/8 + 4 * screenHeight/30 + screenHeight/100, screenWidth/8, screenHeight/30);
		logOutButton.setFont(labelFontSize);
		add(logOutButton);

	}
}
