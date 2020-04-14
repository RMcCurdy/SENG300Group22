package departmentHeadRelated;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.Color;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.FontFormatException;

import objects.*;
import login.Login;

public class DepartmentHeadMenu extends JPanel {

	private static final long serialVersionUID = 1L;
	private Font headerFont;
	private Font labelFont;
	private JLabel background_1;
	private JLabel background_2;

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

		// Create specific colors to be used in text and buttons
		Color gold = new Color(255, 207, 8);
		Color myRed = new Color(227, 37, 37);

		// Try catch to load in custom font
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

		// Header of the system name
		JLabel header = new JLabel("Coordinator Portal");
		header.setBounds(screenWidth/4 - screenWidth/19, screenHeight/25, screenWidth/3, screenHeight/25);
		header.setForeground(myRed);
		header.setFont(headerFont);
		add(header);
		
		// Each button below contains "set" methods in order to size and shape them to their specific needs

		// Button that takes the user to the Add Scholarship menu
		JButton btnAddScholarship = new JButton("Add Scholarship");
		btnAddScholarship.setFont(labelFont);
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
		btnAddScholarship.setBackground(gold);
		add(btnAddScholarship);
		
		// Button that takes the user to the Edit Scholarship menu
		JButton btnEditScholarship = new JButton("Edit Scholarship");
		btnEditScholarship.setFont(labelFont);
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
		btnEditScholarship.setBackground(gold);
		add(btnEditScholarship);
		
		// Button that takes the user to the Remove Scholarship menu
		JButton btnRemoveScholarship = new JButton("Remove Scholarship");
		btnRemoveScholarship.setFont(labelFont);
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
		btnRemoveScholarship.setBackground(gold);
		add(btnRemoveScholarship);

		// Button that takes the user to the Award Scholarship menu
		JButton btnAwardScholarship = new JButton("Award Scholarship");
		btnAwardScholarship.setFont(labelFont);
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
		btnAwardScholarship.setBackground(gold);
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
		statisticsButton.setBackground(gold);
		statisticsButton.setBounds(screenWidth/6 + screenWidth/50 + screenWidth/8/2 + screenWidth/175, screenHeight/8 + 2 * screenHeight/30 + screenHeight/100, screenWidth/8, screenHeight/30);
		statisticsButton.setFont(labelFont);
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
		logOutButton.setFont(labelFont);
		logOutButton.setBackground(gold);
		add(logOutButton);

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
		background_2.setBounds(screenWidth/4 - screenWidth/10, screenHeight/35, 300, 300);
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
