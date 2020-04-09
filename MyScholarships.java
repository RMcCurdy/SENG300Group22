import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.JButton;

public class MyScholarships extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected List<String> list = new ArrayList<String>();
	private Boolean valid= false;

	public MyScholarships(JFrame frame, Account user) {
		//Save the user's screen resolution to variables, used to format GUI correctly
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;

		setLayout(null);
		
		// Font size for remaining labels
		Font labelFontSize = new Font("Arial", Font.PLAIN, screenHeight/60);
		
		Authenticator authen = new Authenticator();
		String facs = (String)authen.getRolesMap().get(Login.eAddress());
		
		// Header of the system name
		JLabel header = new JLabel("My Scholarships");
		header.setBounds(screenWidth/4 - screenWidth/12, screenHeight/25, screenWidth/3, screenHeight/25);
		header.setForeground(Color.RED);
		header.setFont(new Font("Arial", Font.PLAIN, screenHeight/27));
		add(header);
		
		// Label for Status
		JLabel statusLabel = new JLabel("Status:");
		statusLabel.setBounds(screenWidth/4 - screenWidth/25, screenHeight/5, screenWidth/7, screenHeight/35);
		statusLabel.setFont(labelFontSize);
		add(statusLabel);
		
		JLabel acceptLabel = new JLabel("Accepted");
		acceptLabel.setForeground(Color.green);
		acceptLabel.setBounds(screenWidth/4 - screenWidth/180, screenHeight/5, screenWidth/7, screenHeight/35);
		acceptLabel.setFont(labelFontSize);
		add(acceptLabel);
		acceptLabel.setVisible(false);
		
		JLabel waitLabel = new JLabel("Waitlist");
		waitLabel.setForeground(Color.yellow);
		waitLabel.setBounds(screenWidth/4 - screenWidth/180, screenHeight/5, screenWidth/7, screenHeight/35);
		waitLabel.setFont(labelFontSize);
		add(waitLabel);
		waitLabel.setVisible(false);
		
		JLabel rejLabel = new JLabel("Rejected");
		rejLabel.setForeground(Color.red);
		rejLabel.setBounds(screenWidth/4 - screenWidth/180, screenHeight/5, screenWidth/7, screenHeight/35);
		rejLabel.setFont(labelFontSize);
		add(rejLabel);
		rejLabel.setVisible(false);
		
		JLabel successfulAcc = new JLabel("Successfully accepted the scholarship");
		successfulAcc.setForeground(Color.green);
		successfulAcc.setBounds(screenWidth/4 - screenWidth/12, screenHeight/4, screenWidth/6, screenHeight/35);
		successfulAcc.setFont(labelFontSize);
		add(successfulAcc);
		successfulAcc.setVisible(false);
		
		JLabel successfulDec = new JLabel("Successfully declined the scholarship");
		successfulDec.setForeground(Color.black);
		successfulDec.setBounds(screenWidth/4 - screenWidth/12, screenHeight/4, screenWidth/6, screenHeight/35);
		successfulDec.setFont(labelFontSize);
		add(successfulDec);
		successfulDec.setVisible(false);
		
		JButton acceptButton = new JButton("Accept");
		acceptButton.addMouseListener(new MouseAdapter() {
			@Override
			// On the click of this button, create a new instance of the frame with the DepartmentHeadMenu class
			public void mouseClicked(MouseEvent e) {
				successfulAcc.setVisible(true);
				successfulDec.setVisible(false);
			}
		});
		acceptButton.setBounds(screenWidth/4 - screenWidth/12, screenHeight/3,screenWidth/15, screenHeight/30);
		add(acceptButton);
		acceptButton.setVisible(false);
		
		JButton rejectButton = new JButton("Decline");
		rejectButton.addMouseListener(new MouseAdapter() {
			@Override
			// On the click of this button, create a new instance of the frame with the DepartmentHeadMenu class
			public void mouseClicked(MouseEvent e) {
				successfulAcc.setVisible(false);
				successfulDec.setVisible(true);
			}
		});
		rejectButton.setBounds(screenWidth/4 - screenWidth/12 + 2*screenWidth/12,screenHeight/3,screenWidth/15, screenHeight/30);
		add(rejectButton);
		rejectButton.setVisible(false);
		
		JButton btnBack = new JButton("Back");
		btnBack.setFont(labelFontSize);
		btnBack.setBounds((screenWidth/2 - screenWidth/12), screenHeight/40, screenWidth/20, screenHeight/33);
		add(btnBack);
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			// On the click of this button, create a new instance of the frame with the DepartmentHeadMenu class
			public void mouseClicked(MouseEvent e) {
				frame.setBounds((screenWidth/2 - screenWidth/4), (screenHeight/2 - screenHeight/4), screenWidth/2, screenHeight/2);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				StudentMenu sMenu = new StudentMenu(frame, user);
				frame.setContentPane(sMenu);
				frame.revalidate();
			}
			});
		
		// Initialize a JSONParser to get the data from the JSON file
		JSONParser parser7 = new JSONParser();

		// Try-catch statement to open the JSON file and add the scholarship names to the drop down list
		try (Reader reader7 = new FileReader("currentScholarships.json")) {

		// Create a JSONObject out of the parsed JSON file
		JSONObject jsonObject = (JSONObject) parser7.parse(reader7);

		// Obtain the array that contains the label "scholarships"
		JSONArray scholarshipArrayJSON = (JSONArray) jsonObject.get("ArtsFY");

		// Loop through the JSONArray, and add those scholarship names to the List
		Iterator<String> iterator7 = scholarshipArrayJSON.iterator();
		while (iterator7.hasNext()) {
		      System.out.println(iterator7.next());
		      
		}
					
		// Close the reader
		reader7.close();

		// Exceptions to be thrown if necessary
		} catch (IOException e) {
		    e.printStackTrace();
		} catch (ParseException e) {
		    e.printStackTrace();
		}
		
		String name = ("null");
		if(list.contains(null)) {
			acceptButton.setVisible(true);
			valid=true;
		}
		else {
			waitLabel.setVisible(true);
		}
		
		if(valid==true) {
			acceptButton.setVisible(true);
			rejectButton.setVisible(true);
		}
		
	}
}
