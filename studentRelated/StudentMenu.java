package studentRelated;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Color;
import java.awt.Component;

import javax.swing.SwingConstants;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.awt.Desktop;

import objects.*;
import login.Login;

public class StudentMenu extends JPanel {
	private static final long serialVersionUID = 1L;
	private Font headerFont;
	private Font labelFont;
	private JLabel background_1;
	private JLabel background_2;
	private String scholarship = "";
	

	/**
	 * Creates a menu with multiple buttons
	 * This allows the user to choose what they want to do in this menu
	 * With each button click, the method will call for a new interface with the proper functions associated with it
	 * 
	 * 
	 * @param frame
	 * @param user
	 * @author Ayokolade Erinle
	 */
	public StudentMenu(JFrame frame, Account user) {
		//Save the user's screen resolution to variables, used to format GUI correctly
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;

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

		//new instance of Authenticator to get student name and faculty
		Authenticator authen = new Authenticator();
		
		String facs = (String)authen.getRolesMap().get(Login.eAddress());
		String studName = (String)authen.getNamesMap().get(Login.eAddress());

		//creates strings for checking which scholarship student is associated with
		String Sch1 =(facs + "F");
		String Sch2 =(facs + "W");
		String Sch3 =(facs + "FY");

		//Header of the system name
		JLabel header = new JLabel("UofC Student Scholarship Portal");
		header.setHorizontalAlignment(SwingConstants.LEFT);
		header.setForeground(myRed);
		header.setBounds(screenWidth/4 - screenWidth/7, screenHeight/25, screenWidth/2, screenHeight/25);
		header.setFont(headerFont);
		add(header);
		
		//User label
		JLabel userLabel = new JLabel("User: " + Login.eAddress());
		userLabel.setForeground(gold);
		userLabel.setBounds(screenWidth/80, screenHeight/6+screenHeight/6+screenHeight/20+screenHeight/30, screenWidth/8, screenHeight/30);
		userLabel.setFont(labelFont);
		add(userLabel);
		
		//faculty label
		JLabel facultyLabel = new JLabel("Faculty: " + facs);
		facultyLabel.setForeground(gold);
		facultyLabel.setBounds(screenWidth/80, screenHeight/6+screenHeight/6+screenHeight/20+screenHeight/20, screenWidth/8, screenHeight/30);
		facultyLabel.setFont(labelFont);
		add(facultyLabel);
		
		//Apply Scholarship button
		JButton btnApplyScholarship = new JButton("Apply Scholarship");
		btnApplyScholarship.setFont(labelFont);
		btnApplyScholarship.setBounds(screenWidth/6 + screenWidth/50 - screenWidth/8/2 - screenWidth/175-screenWidth/30, screenHeight/8 + 2 * screenHeight/30+screenHeight/100, screenWidth/8+screenWidth/30, screenHeight/30);
		btnApplyScholarship.addMouseListener(new MouseAdapter() {
			@Override
			// On the click of this button, create a new instance of the frame with the ApplyScholarships class
			public void mouseClicked(MouseEvent arg0) {
				frame.setBounds((screenWidth/2 - screenWidth/4), (screenHeight/2 - screenHeight/4), screenWidth/2, screenHeight/2);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				ApplyScholarships panel = new ApplyScholarships(frame, user);
				frame.setContentPane(panel);
				frame.revalidate();
			}
		});
		btnApplyScholarship.setBackground(gold);
		add(btnApplyScholarship);
		
		// Button that takes the user to Upload Transcript
		JButton UploadTranscriptButt= new JButton("Upload Transcript");
		UploadTranscriptButt.setFont(labelFont);
		UploadTranscriptButt.setBounds(screenWidth/6 + screenWidth/50 - screenWidth/8/2 - screenWidth/175-screenWidth/30, screenHeight/8 + 2 * screenHeight/30+2 * screenHeight/30 + screenHeight/100, screenWidth/8+screenWidth/30, screenHeight/30);
		UploadTranscriptButt.addMouseListener(new MouseAdapter() {
			@Override
			// On click of this button, create a new instance of the frame with Upload
			public void mouseClicked(MouseEvent e) {
				String currDir = System.getProperty("user.dir");
				try {
					Desktop.getDesktop().open(new File(currDir + "\\Upload.jar"));
				} catch (IOException ioe) {
					System.out.println("Could not run JAR from " + currDir);
				}
			}
		});
		UploadTranscriptButt.setBackground(gold);
		add(UploadTranscriptButt);
		
		// Button that takes the user to My Scholarships
		JButton MyScholarshipsButt = new JButton("My Scholarships");
		MyScholarshipsButt.setFont(labelFont);
		MyScholarshipsButt.setBounds(screenWidth/6 + screenWidth/50 + screenWidth/8/2 + screenWidth/175, screenHeight/8 + 2 * screenHeight/30+screenHeight/100, screenWidth/8+screenWidth/30, screenHeight/30);
		MyScholarshipsButt.addMouseListener(new MouseAdapter() {
		@Override
		// On click of this button, create a new instance of the frame with the MyScholarships class
		public void mouseClicked(MouseEvent e) {
			frame.setBounds((screenWidth/2 - screenWidth/4), (screenHeight/2 - screenHeight/4), screenWidth/2, screenHeight/2);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			MyScholarships panel = new MyScholarships(frame, user);
			frame.setContentPane(panel);
			frame.revalidate();
		}
		});
		MyScholarshipsButt.setBackground(gold);
		add(MyScholarshipsButt);
			
		// Button that allows the user to logout
		JButton logOutButton = new JButton("Logout");
		logOutButton.addMouseListener(new MouseAdapter() {
		@Override
		// On click of this button, create a new instance of the Login class
		public void mouseClicked(MouseEvent e) {
			frame.setBounds((screenWidth/2 - screenWidth/4), (screenHeight/2 - screenHeight/4), screenWidth/2, screenHeight/2);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			Login panel = new Login(frame);
			frame.setContentPane(panel);
			frame.revalidate();
		}
		});
		logOutButton.setBounds(screenWidth/6 + screenWidth/50 + screenWidth/8/2 + screenWidth/175,screenHeight/8 + 2 * screenHeight/30+2 * screenHeight/30 + screenHeight/100, screenWidth/8+screenWidth/30, screenHeight/30);
		logOutButton.setFont(labelFont);
		logOutButton.setBackground(gold);
		add(logOutButton);
		
		// Initialize a JSONParser to get the data from the JSON file
		JSONParser parser10 = new JSONParser();

		// Try-catch statement to open the JSON file scholarshipRequests
		try (Reader reader10 = new FileReader("scholarshipRequests.json")) {

		// Create a JSONObject out of the parsed JSON file
		JSONObject jsonObject1 = (JSONObject) parser10.parse(reader10);

		// Obtain the array that contains the label "Sch1",Sch2" & Sch3"
		JSONArray scholarshipArrayJSON1 = (JSONArray) jsonObject1.get(Sch1);
		JSONArray scholarshipArrayJSON2 = (JSONArray) jsonObject1.get(Sch2);
		JSONArray scholarshipArrayJSON3 = (JSONArray) jsonObject1.get(Sch3);
		
		// Loop through the JSONArray, and check which scholarship the user applied to and assign it to scholarship
		Iterator<String> iterator10 = scholarshipArrayJSON1.iterator();
		while (iterator10.hasNext()) {
		     if(iterator10.next().contains(studName)) {
		    	 scholarship=Sch1;
		     }     
		}
		Iterator<String> iterator11 = scholarshipArrayJSON2.iterator();
		while (iterator11.hasNext()) {
		     if(iterator11.next().contains(studName)) {
		    	 scholarship=Sch2;
		     }     
		}
		Iterator<String> iterator12 = scholarshipArrayJSON3.iterator();
		while (iterator12.hasNext()) {
		     if(iterator12.next().contains(studName)) {
		    	 scholarship=Sch3;
		     } 
		}
		// Close the reader
		reader10.close();

		// Exceptions to be thrown if necessary
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//if scholarship is not empty then disable button as user has already applied to a scholarship
		if(!(scholarship=="")) {
			btnApplyScholarship.setEnabled(false);
		}
		
		/**
		 * PHOTOS
		 */

		 //Loads in the image of the UofC logo and sets it to fit the specific location on the GUI
		ImageIcon img1 = new ImageIcon("logo.png");
		Image image = img1.getImage();
		Image newimg1 = image.getScaledInstance(75, 75, Image.SCALE_SMOOTH);
		img1 = new ImageIcon(newimg1);
		background_2 = new JLabel("",img1,SwingConstants.LEFT);
		background_2.setVerticalAlignment(SwingConstants.TOP);
		background_2.setBounds(screenWidth/4 - screenWidth/5, screenHeight/35, 300, 300);
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