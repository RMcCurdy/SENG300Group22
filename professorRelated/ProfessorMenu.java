package professorRelated;
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

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Image;

import javax.swing.JList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.Set;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import login.*;

public class ProfessorMenu extends JPanel {
	private String[] scholarshiplist; // List of all displayed scholarships
	private JList list;	// JList of all displayed scholarships
	private JTextField search; 
	private static final long serialVersionUID = 1L;
	private JLabel background_1;
	private JLabel background_2;
	private Font headerFont;
	private Font labelFont;

	/**
	 * The screen a professor gets when they sign in
	 * @param frame The main JFrame
	 * @param email The email of the professor who is logged in.
	 */
	public ProfessorMenu(JFrame frame, String email) {
		
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
		JLabel header = new JLabel("UofC Professor Scholarship Portal");
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
		JLabel lblInstructions = new JLabel("Please select the scholarship you wish to nominate a student in.");
		lblInstructions.setBounds(screenWidth/10 + screenWidth/150, screenHeight/10, screenWidth/2, screenHeight/35);
		lblInstructions.setFont(labelFont);
		lblInstructions.setForeground(gold);
		add(lblInstructions);
        
		// Creating list containing scholarships
		list = new JList(scholarshiplist);
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
		
		// Label for the search bar
		JLabel lblNewLabel = new JLabel("Search:");
		lblNewLabel.setBounds(screenWidth/4 - screenWidth/10, screenHeight/10 + screenHeight/24, screenWidth/7, screenHeight/35);
		lblNewLabel.setFont(labelFont);
		lblNewLabel.setForeground(gold);
		add(lblNewLabel);
		
		// Search bar for list of scholarships
		search = new JTextField();
		search.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				String[] newList = null; // List that displays according to the text in the search bar
				JSONParser parser = new JSONParser(); // Parser to read the JSON file
				
				// Try-catch statement to open the JSON file and add the school years to the drop down list
		        try (Reader reader1 = new FileReader("currentScholarships.json")) {

					// Create a JSONObject out of the parsed JSON file
		            JSONObject jsonObject = (JSONObject) parser.parse(reader1);            
		            newList = new String[jsonObject.size()];

		            int i = 0;	// Counter
		            Set a = jsonObject.keySet();	// Countains all scholarships that start with text in search bar
		            for (Object key : a) {
		            	String input = (String) key;
		            		if (input.toLowerCase().startsWith(search.getText().toLowerCase())) {	// If scholarshipname starts with text in search box
		            			newList[i] = input;
		            			i++;
		            		}
		            }
		            reader1.close();

				// Exceptions to be thrown if necessary
		        } catch (IOException ex) {
		            ex.printStackTrace();
		        } catch (ParseException ex) {
		            ex.printStackTrace();
		        }

		        // New JList containing only filtered scholarships
				JList newJList = new JList(newList);
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
		search.setBounds(screenWidth/4 - screenWidth/16, screenHeight/10 + screenHeight/24, screenWidth/8 + screenWidth/48, screenHeight/35);
		search.setFont(new Font("Arial", Font.PLAIN, screenHeight/60));
		search.setForeground(Color.BLACK);
		add(search);
		
		// Select Button
		JButton button = new JButton("Select");
		button.setBounds(screenWidth/4 - screenWidth/10, screenHeight/6 + 8*screenHeight/34 - screenHeight/128, screenWidth/10 - screenWidth/256 + screenWidth/32, screenHeight/30);
		button.setBackground(gold);
		button.setFont(labelFont);
		add(button);
	    button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (list.getSelectedIndex() >= 0){ // If a scholarship is selected
						String selec = (String)list.getSelectedValue();	// Gets selected scholarship
						selectedError.setVisible(false);
						
						frame.setBounds((screenWidth/2 - screenWidth/4), (screenHeight/2 - screenHeight/4), screenWidth/2, screenHeight/2);
						frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						AddReference panel = new AddReference(frame, email, (String)list.getSelectedValue()); // Opens AddReference page
						frame.setContentPane(panel);
						frame.revalidate();
						
					} else {
						selectedError.setVisible(true);
					}
            	} catch (Exception e1) {

				}
			
			}
	    });		
		
	    // Log out button
		JButton logoutButton = new JButton("Logout");
		logoutButton.setBackground(gold);
		logoutButton.setFont(labelFont);
		logoutButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.setBounds((screenWidth/2 - screenWidth/4), (screenHeight/2 - screenHeight/4), screenWidth/2, screenHeight/2);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				Login panel = new Login(frame); // Opens login window
				frame.setContentPane(panel);
				frame.revalidate();
			}
		});
		logoutButton.setBounds(screenWidth/4 + screenWidth/256 + screenWidth/32, screenHeight/6 + 8*screenHeight/34 - screenHeight/128, screenWidth/10 - screenWidth/32, screenHeight/30);
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