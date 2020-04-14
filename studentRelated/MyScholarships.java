package studentRelated;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import objects.*;
import login.Login;

public class MyScholarships extends JPanel {

	/**
	 * Allows the user to see the status of his/her scholarship
	 * This allows the user to choose if they want to accept or decline the scholarship if it has been awarded
	 * 
	 * 
	 * @param frame
	 * @param user
	 */
	private String[] scholarshiplist; // List of all displayed scholarships
	private JList list;	// JList of all displayed scholarships
	private JTextField search; 
	private static final long serialVersionUID = 1L;
	private Font headerFont;
	private Font labelFont;
	private JLabel background_1;
	private JLabel background_2;
	private JLabel yellow;
	private JLabel green;
	private JLabel red;
	private String scholarship = "";
	private ArrayList<String> acceptedScholarships = new ArrayList<String>();
	private ArrayList<String> pendingScholarships = new ArrayList<String>();
	private ArrayList<String> appliedScholarships = new ArrayList<String>();
	
	public MyScholarships(JFrame frame, Account user) {
				
		acceptedScholarships.add("accepted1");
		acceptedScholarships.add("accepted2");
		acceptedScholarships.add("accepted2");
		
		pendingScholarships.add("pending1");
		pendingScholarships.add("pending2");
		pendingScholarships.add("pending3");
		
		appliedScholarships.add("applied1");
		appliedScholarships.add("applied2");
		appliedScholarships.add("applied3");
		
		// Save the user's screen resolution to variables, used to format GUI correctly
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;

		setLayout(null);

		// Create specific colors to be used in text and buttons
		Color gold = new Color(255, 207, 8);
		Color myRed = new Color(227, 37, 37);
		
		// Try catch to load in custom header font
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
		JLabel header = new JLabel("UofC Student Scholarship Portal");
		header.setHorizontalAlignment(SwingConstants.CENTER);
		header.setForeground(myRed);
		header.setBounds(screenWidth/4 - screenWidth/6 + screenWidth/100, screenHeight/25, screenWidth/3, screenHeight/25);
		header.setFont(headerFont);
		add(header);

		String[] scholarshiplist = null; // List of all scholarships
		JSONParser parser = new JSONParser(); // Parser to read the JSON file
		
		// Try-catch statement to open the JSON file
        try (Reader reader1 = new FileReader("currentScholarships.json")) {

			// Create a JSONObject out of the parsed JSON file
            JSONObject jsonObject = (JSONObject) parser.parse(reader1);            
            scholarshiplist = new String[jsonObject.size()];

            int i = 0;	// Counter variable
            Set a = jsonObject.keySet(); // Set of all scholarship names
            for (Object key : a) {
            	scholarshiplist[i] = (String) key;	// Fills list with all scholarship names
            	i++;
            }
			reader1.close();

		// Exceptions to be thrown if necessary
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
		
        // Label with instructions
		JLabel lblInstructions = new JLabel("Your scholarships status:");
		lblInstructions.setBounds(screenWidth/5 - screenWidth/150, screenHeight/10, screenWidth/2, screenHeight/35);
		lblInstructions.setFont(labelFont);
		lblInstructions.setForeground(gold);
		add(lblInstructions);
        
		String[] temp = {};
		
		// Creating list containing scholarships
		list = new JList(temp);
		list.setFont(new Font("Arial", Font.PLAIN, screenHeight/60));
		list.setForeground(Color.BLACK);
		list.setSize(218, 80);
		list.setLocation(145, 159);
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		list.setVisibleRowCount(3);
		list.setBackground(Color.WHITE);

		// Label for testing
		JLabel selectedError = new JLabel("Please select a scholarship");
		selectedError.setFont(labelFont);
		selectedError.setForeground(Color.RED);
		selectedError.setBounds(screenWidth/6 - screenWidth/7, screenHeight/4, screenWidth/7 , screenHeight/35);
		add(selectedError);
		selectedError.setVisible(false);

		// ScrollPane to display the list
		JScrollPane sp = new JScrollPane();
		sp.setLocation(screenWidth/4 - screenWidth/10, screenHeight/7 + screenHeight/26);
		sp.setSize(screenWidth/5, screenHeight/4 - screenHeight/22);
		sp.setViewportView(list);
		add(sp);

		// Scroll bar added to ScrollPane
		JScrollBar bar = sp.getVerticalScrollBar();
		bar.setPreferredSize(new Dimension(15, 0));

		// Label if accepted selected
		JLabel acceptedSelected = new JLabel("The scholarships that you have been awarded.");
		acceptedSelected.setBounds(screenWidth/8+screenWidth/48, screenHeight/6 + 8*screenHeight/34 - screenHeight/128, screenWidth/4, screenHeight/30);
		acceptedSelected.setFont(labelFont);
		acceptedSelected.setForeground(gold);
		add(acceptedSelected);
		acceptedSelected.setVisible(false);
		
		// Label if accepted selected
		JLabel appliedSelected = new JLabel("The scholarships that you are waiting to hear from.");
		appliedSelected.setBounds(screenWidth/8+screenWidth/128, screenHeight/6 + 8*screenHeight/34 - screenHeight/128, screenWidth/4, screenHeight/30);
		appliedSelected.setFont(labelFont);
		appliedSelected.setForeground(gold);
		add(appliedSelected);
		appliedSelected.setVisible(false);
		
		
		// Select Button
		JButton accept = new JButton("Accept");
		accept.setBounds(screenWidth/4-screenWidth/10+screenWidth/38, screenHeight/6 + 8*screenHeight/34 - screenHeight/128, screenWidth/10 - screenWidth/32, screenHeight/30);
		accept.setBackground(gold);
		accept.setFont(labelFont);
		add(accept);
		accept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (list.getSelectedIndex() >= 0) {

					String move = pendingScholarships.get(list.getSelectedIndex());
					acceptedScholarships.add(move);
					pendingScholarships.remove(list.getSelectedIndex());
					
					// Update pending list
					String[] a = new String[pendingScholarships.size()];
					for (int i = 0; i < pendingScholarships.size();i++) {
						a[i] = pendingScholarships.get(i);
					}
					
					JList newJList = new JList(a);
					newJList.setFont(new Font("Arial", Font.PLAIN, screenHeight/60));
					newJList.setForeground(Color.BLACK);
					newJList.setSize(218, 80);
					newJList.setLocation(0, 0);
					newJList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
					newJList.setVisibleRowCount(3);
					newJList.setBackground(Color.WHITE);
					sp.setViewportView(newJList);
					
					list = newJList; // Setting displayed list to new filtered list
					//pendingScholarships[list.getSelectedIndex()] = null;
				}
				else {
					selectedError.setVisible(true);
				}
				
			}
	    });		
	    accept.setVisible(false);
		
	    // Log out button
		JButton decline = new JButton("Decline");
		decline.setBackground(gold);
		decline.setFont(labelFont);
		decline.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				if (list.getSelectedIndex() >= 0) {

					pendingScholarships.remove(list.getSelectedIndex());
					
					// Update pending list
					String[] a = new String[pendingScholarships.size()];
					for (int i = 0; i < pendingScholarships.size();i++) {
						a[i] = pendingScholarships.get(i);
					}
					
					JList newJList = new JList(a);
					newJList.setFont(new Font("Arial", Font.PLAIN, screenHeight/60));
					newJList.setForeground(Color.BLACK);
					newJList.setSize(218, 80);
					newJList.setLocation(0, 0);
					newJList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
					newJList.setVisibleRowCount(3);
					newJList.setBackground(Color.WHITE);
					sp.setViewportView(newJList);
					
					list = newJList; // Setting displayed list to new filtered list
					//pendingScholarships[list.getSelectedIndex()] = null;
					
				}
				else {
					selectedError.setVisible(true);
				}
			}
		});
		decline.setBounds(screenWidth/4 + screenWidth/256 + screenWidth/32-screenWidth/38, screenHeight/6 + 8*screenHeight/34 - screenHeight/128, screenWidth/10 - screenWidth/32, screenHeight/30);
		add(decline);
		decline.setVisible(false);
		
		
		// Select Button
		JButton accepted = new JButton("Accepted");
		accepted.setBounds(screenWidth/8+screenWidth/64, screenHeight/10 + screenHeight/24, screenWidth/10 - screenWidth/32, screenHeight/30);
		accepted.setBackground(gold);
		accepted.setFont(labelFont);
		add(accepted);
		accepted.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				//System.out.println(list.getSelectedIndex());
				selectedError.setVisible(false);
					
				acceptedSelected.setVisible(true);
				accept.setVisible(false);
				decline.setVisible(false);
				appliedSelected.setVisible(false);

				String[] a = new String[acceptedScholarships.size()];
				for (int i = 0; i < acceptedScholarships.size();i++) {
					a[i] = acceptedScholarships.get(i);
				}
					
				JList newJList = new JList(a);
				newJList.setFont(new Font("Arial", Font.PLAIN, screenHeight/60));
				newJList.setForeground(Color.BLACK);
				newJList.setSize(218, 80);
				newJList.setLocation(0, 0);
				newJList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
				newJList.setVisibleRowCount(3);
				newJList.setBackground(Color.WHITE);
				sp.setViewportView(newJList);
					
				list = newJList; // Setting displayed list to new filtered list
			}
	    });	
		
		// Select Button
		JButton pending = new JButton("Pending");
		pending.setBounds(screenWidth/8+screenWidth/64+screenWidth/10-screenWidth/42, screenHeight/10 + screenHeight/24, screenWidth/10 - screenWidth/32, screenHeight/30);
		pending.setBackground(gold);
		pending.setFont(labelFont);
		add(pending);
		pending.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				acceptedSelected.setVisible(false);
				accept.setVisible(true);
				decline.setVisible(true);
				appliedSelected.setVisible(false);
				
				String[] a = new String[pendingScholarships.size()];
				for (int i = 0; i < pendingScholarships.size();i++) {
					a[i] = pendingScholarships.get(i);
				}
				
				JList newJList = new JList(a);
				newJList.setFont(new Font("Arial", Font.PLAIN, screenHeight/60));
				newJList.setForeground(Color.BLACK);
				newJList.setSize(218, 80);
				newJList.setLocation(0, 0);
				newJList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
				newJList.setVisibleRowCount(3);
				newJList.setBackground(Color.WHITE);
				sp.setViewportView(newJList);
				
				list = newJList; // Setting displayed list to new filtered list
			}
	    });	

		// Select Button
		JButton applied = new JButton("Applied");
		applied.setBounds(screenWidth/8+screenWidth/64+screenWidth/10-screenWidth/32+screenWidth/64+screenWidth/10-screenWidth/32, screenHeight/10 + screenHeight/24, screenWidth/10 - screenWidth/32, screenHeight/30);
		applied.setBackground(gold);
		applied.setFont(labelFont);
		add(applied);
		applied.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				acceptedSelected.setVisible(false);
				accept.setVisible(false);
				decline.setVisible(false);
				appliedSelected.setVisible(true);
				
				String[] a = new String[appliedScholarships.size()];
				for (int i = 0; i < appliedScholarships.size();i++) {
					a[i] = appliedScholarships.get(i);
				}
				
				JList newJList = new JList(a);
				newJList.setFont(new Font("Arial", Font.PLAIN, screenHeight/60));
				newJList.setForeground(Color.BLACK);
				newJList.setSize(218, 80);
				newJList.setLocation(0, 0);
				newJList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
				newJList.setVisibleRowCount(3);
				newJList.setBackground(Color.WHITE);
				sp.setViewportView(newJList);
				
				list = newJList; // Setting displayed list to new filtered list
			}
	    });	

	    // Button to go back to the Student portal
		JButton logoutButton = new JButton("Back");
		logoutButton.setBackground(gold);
		logoutButton.setFont(labelFont);
		logoutButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.setBounds((screenWidth/2 - screenWidth/4), (screenHeight/2 - screenHeight/4), screenWidth/2, screenHeight/2);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				StudentMenu panel = new StudentMenu(frame, user); //
				frame.setContentPane(panel);
				frame.revalidate();
			}
		});
		logoutButton.setBounds(screenWidth/4 + screenWidth/8, screenHeight/6 + 8*screenHeight/34 - screenHeight/128, screenWidth/10 - screenWidth/32, screenHeight/30);
		add(logoutButton);
		
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
		background_2.setBounds(screenWidth/4 - screenWidth/6, screenHeight/35, 300, 300);
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
