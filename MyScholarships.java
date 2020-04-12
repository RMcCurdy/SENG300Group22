import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class MyScholarships extends JPanel {

	/**
	 * Allows the user to see the status of his/her scholarship
	 * This allows the user to choose if they want to accept or decline the scholarship if it has been awarded
	 * 
	 * 
	 * @param frame
	 * @param user
	 * @author Ayokolade Erinle
	 */
	private static final long serialVersionUID = 1L;
	private Font headerFont;
	private Font labelFont;
	private JLabel background_1;
	private JLabel background_2;
	private JLabel yellow;
	private JLabel green;
	private JLabel red;
	private String scholarship = "";
	
	public MyScholarships(JFrame frame, Account user) {
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
		
		//new instance of Authenticator 
		Authenticator authen = new Authenticator();
		//student name and faculty
		String studName = (String)authen.getNamesMap().get(Login.eAddress());
		String facs = (String)authen.getRolesMap().get(Login.eAddress());
		//creates strings for checking which scholarship student is associated with
		String Sch1 =(facs + "F");
		String Sch2 =(facs + "W");
		String Sch3 =(facs + "FY");
		
		// Header of the system name
		JLabel header = new JLabel("My Scholarships");
		header.setBounds(screenWidth/4 - screenWidth/12, screenHeight/25, screenWidth/3, screenHeight/25);
		header.setForeground(myRed);
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
		
		// Label for Status
		JLabel statusLabel = new JLabel("Status:");
		statusLabel.setBounds(screenWidth/4 - screenWidth/50, screenHeight/6+screenHeight/60, screenWidth/7, screenHeight/35);
		statusLabel.setForeground(gold);
		statusLabel.setFont(labelFont);
		add(statusLabel);
		
		//Loads in the image of the yellow circle and sets it to fit the specific location on the GUI
		ImageIcon img2 = new ImageIcon("yellow.png");
		Image image2 = img2.getImage();
		Image newimg2 = image2.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		img2 = new ImageIcon(newimg2);
		yellow = new JLabel("",img2,SwingConstants.LEFT);
		yellow.setVerticalAlignment(SwingConstants.TOP);
		yellow.setBounds(screenWidth/4 - screenWidth/180, screenHeight/5+screenHeight/50, 300, 300);
		yellow.setVisible(false);
		add(yellow);

		//Loads in the image of the green circle and sets it to fit the specific location on the GUI
		ImageIcon img3 = new ImageIcon("green.png");
		Image image3 = img3.getImage();
		Image newimg3 = image3.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		img3 = new ImageIcon(newimg3);
		green = new JLabel("",img3,SwingConstants.LEFT);
		green.setVerticalAlignment(SwingConstants.TOP);
		green.setBounds(screenWidth/4 - screenWidth/180+ screenWidth/35, screenHeight/6+screenHeight/100, 300, 300);
		green.setVisible(false);
		add(green);

		//Loads in the image of the red circle and sets it to fit the specific location on the GUI
		ImageIcon img4 = new ImageIcon("red.png");
		Image image4 = img4.getImage();
		Image newimg4 = image4.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		img4 = new ImageIcon(newimg4);
		red = new JLabel("",img4,SwingConstants.LEFT);
		red.setVerticalAlignment(SwingConstants.TOP);
		red.setBounds(screenWidth/4 - screenWidth/180, screenHeight/5+screenHeight/50, 300, 300);
		red.setVisible(false);
		add(red);
		
		//Jlabel for successfully clicking the accept button
		JLabel successfulAcc = new JLabel("Successfully accepted the scholarship");
		successfulAcc.setForeground(Color.green);
		successfulAcc.setBounds(screenWidth/4 - screenWidth/10, screenHeight/4+screenHeight/50, screenWidth/4, screenHeight/35);
		successfulAcc.setFont(labelFont);
		add(successfulAcc);
		successfulAcc.setVisible(false);
		
		//Jlabel for successfully clicking the decline button
		JLabel successfulDec = new JLabel("Successfully declined the scholarship");
		successfulDec.setForeground(Color.green);
		successfulDec.setBounds(screenWidth/4 - screenWidth/8, screenHeight/4+screenHeight/50, screenWidth/4, screenHeight/35);
		successfulDec.setFont(labelFont);
		add(successfulDec);
		successfulDec.setVisible(false);
		
		// Initialize a JSONParser to get the data from the JSON file
		JSONParser parser10 = new JSONParser();

		// Try-catch statement to open the JSON file and add the scholarship names to the drop down list
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
			     if(iterator10.next().equals(studName)) {
			    	 scholarship=Sch1;
			     }     
			}
			Iterator<String> iterator11 = scholarshipArrayJSON2.iterator();
			while (iterator11.hasNext()) {
			     if(iterator11.next().equals(studName)) {
			    	 scholarship=Sch2;
			     }     
			}
			Iterator<String> iterator12 = scholarshipArrayJSON3.iterator();
			while (iterator12.hasNext()) {
			     if(iterator12.next().equals(studName)) {
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
		
		//Button to accept the scholarship
		JButton acceptButton = new JButton("Accept");
		acceptButton.setFont(labelFont);
		acceptButton.setBackground(gold);
		acceptButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				//display confirmation message
				successfulAcc.setVisible(true);
				successfulDec.setVisible(false);
			}
		});
		acceptButton.setBounds(screenWidth/4 - screenWidth/12, screenHeight/3,screenWidth/15, screenHeight/30);
		add(acceptButton);
		acceptButton.setVisible(false);
		
		//Button to reject the scholarship
		JButton rejectButton = new JButton("Decline");
		rejectButton.setFont(labelFont);
		rejectButton.setBackground(gold);
		rejectButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				//display confirmation message
				successfulAcc.setVisible(false);
				successfulDec.setVisible(true);
			}
		});
		rejectButton.setBounds(screenWidth/4 - screenWidth/12 + screenWidth/9,screenHeight/3,screenWidth/15, screenHeight/30);
		add(rejectButton);
		rejectButton.setVisible(false);
		
		//Back button to go back to studentMenu class
		JButton btnBack = new JButton("Back");
		btnBack.setFont(labelFont);
		btnBack.setBackground(gold);
		btnBack.setBounds((screenWidth/2 - screenWidth/12), screenHeight/20, screenWidth/20, screenHeight/33);
		add(btnBack);
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			// On the click of this button, create a new instance of the frame with the StudentMenu class
			public void mouseClicked(MouseEvent e) {
				frame.setBounds((screenWidth/2 - screenWidth/4), (screenHeight/2 - screenHeight/4), screenWidth/2, screenHeight/2);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				StudentMenu sMenu = new StudentMenu(frame, user);
				frame.setContentPane(sMenu);
				frame.revalidate();
			}
			});
		
		//initialize variable for comparison
		int var1 = 5;
		//if scholarship is no longer empty
		if(!(scholarship=="")) {
		// Initialize a JSONParser to get the data from the JSON file currentScholarships
		JSONParser parser7 = new JSONParser();

		// Try-catch statement to open the JSON file
		try (Reader reader7 = new FileReader("currentScholarships.json")) {

		// Create a JSONObject out of the parsed JSON file
		JSONObject jsonObject = (JSONObject) parser7.parse(reader7);

		// Obtain the array that contains the label "scholarship"
		JSONArray scholarshipArrayJSON4 = (JSONArray) jsonObject.get(scholarship);

		// Loop through the JSONArray
		Iterator<String> iterator7 = scholarshipArrayJSON4.iterator();
		//initialize variable for going through list
		Integer elementNumber = 0;
		while (iterator7.hasNext()) {
		     System.out.println(iterator7.next());
		      if (elementNumber == 5){
		    	  //compare name and element to know if they are the same and store in var1
		    	  var1 = studName.compareTo(iterator7.next());
		    	  System.out.println(var1);
		      }
		      elementNumber++;
		      
		}
					
		// Close the reader
		reader7.close();

		// Exceptions to be thrown if necessary
		} catch (IOException e) {
		    e.printStackTrace();
		} catch (ParseException e) {
		    e.printStackTrace();
		}
		}
		
		//if var1 = 0 then student has been awarded scholarship so display green circle and show buttons
		if(var1==0) {
			green.setVisible(true);
			rejectButton.setVisible(true);
			acceptButton.setVisible(true);
		}else if(scholarship=="") {
			//if scholarship=="" then don't display anything as student has not applied for a scholarship
			green.setVisible(false);
			yellow.setVisible(false);
		}
		else {
			//display yellow circle as student has not recieved a response
			yellow.setVisible(true);
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
		background_2.setBounds(screenWidth/4 - screenWidth/7, screenHeight/35, 300, 300);
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
