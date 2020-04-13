import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
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
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.awt.Image;


public class ApplyScholarships extends JPanel {
	
	private JList list;
	
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private Font headerFont;
	private Font labelFont;
	private JLabel background_1;
	private JLabel background_2;
	
	//NEED VARIABLES FOR NEW MENU

	/**
	 * Create the panel.
	 * @param auth 
	 * @param frame 
	 */
	
	public ApplyScholarships(JFrame frame, Account user) {
		
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		
		Dimension screenSize1 = Toolkit.getDefaultToolkit().getScreenSize();
		int screenHeight1 = screenSize1.height;
		int screenWidth1 = screenSize1.width;
		
		// Create specific colors to be used in text and buttons
		Color gold = new Color(255, 207, 8);
		Color myRed = new Color(227, 37, 37);
		
		setLayout(null);

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

		setLayout(null);

		//Font size for remaining labels
		Font labelFontSize = new Font("Arial", Font.PLAIN, screenHeight1/60);

		Authenticator authen = new Authenticator();

		//get the users faculty based on the email address
		//searches in the hash-map that stores <email, faculty>
		String facs = (String)authen.getRolesMap().get(Login.eAddress());
		
		/**
		 * LIST
		 */
		//creating list containing scholarships
		
		//
		DefaultListModel scholarships = new DefaultListModel();
		scholarships.addElement(facs + "F");
		scholarships.addElement(facs + "W");
		scholarships.addElement(facs + "FY");
		list = new JList(scholarships);
		list.setFont(labelFontSize);
		list.setSize(218, 80);
		list.setLocation(145, 159);

		//label for testing
		JLabel selectedLabel = new JLabel("");
		selectedLabel.setFont(labelFontSize);
		selectedLabel.setForeground(myRed);
		selectedLabel.setBounds(screenWidth1/4 - screenWidth1/14, screenHeight1/7 - screenHeight1/200, screenWidth1/7, screenHeight1/35);
		selectedLabel.setFont(labelFontSize);
		add(selectedLabel);

		//label for testing
		JLabel selectedError = new JLabel("Please select a scholarship");
		selectedError.setFont(labelFontSize);
		selectedError.setForeground(Color.RED);
		selectedError.setBounds(screenWidth1/4 - screenWidth1/14, screenHeight1/7 - screenHeight1/200, screenWidth1/7, screenHeight1/35);
		selectedError.setFont(labelFontSize);
		add(selectedError);
		selectedError.setVisible(false);

		//ScrollPane to display the list
		JScrollPane sp = new JScrollPane(list);
		JScrollBar bar = sp.getVerticalScrollBar();
		bar.setPreferredSize(new Dimension(30, 0));
		JButton button = new JButton("Select");
		button.setBackground(gold);
		button.setBounds(216, 408, screenWidth/15, screenHeight/30);
		button.setFont(labelFont);
		add(button);
	    button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (list.getSelectedIndex() <= 32 && list.getSelectedIndex() >= 0){
						String selec = (String)list.getSelectedValue();
						selectedLabel.setText(selec);
						selectedError.setVisible(false);
					} else {
						selectedError.setVisible(true);
					}
            	} catch (Exception e1) {

				}
			
			}
	    });
	    //for scroll pane
		sp.setLocation(201, 161);
		sp.setSize(screenWidth1/5, screenHeight1/4);
		add(sp);
		setVisible(true);

		//Header of the system name
		JLabel header = new JLabel("UofC Student Scholarship Portal");
		header.setHorizontalAlignment(SwingConstants.CENTER);
		header.setForeground(Color.RED);
		header.setBounds(screenWidth1/4 - screenWidth1/6, screenHeight1/25, screenWidth1/3, screenHeight1/25);
		header.setFont(new Font("Arial", Font.PLAIN, screenHeight1/30));
		add(header);
		
		//search bar for list of scholarships
		textField = new JTextField();
		textField.setBounds(241, 84, screenWidth1/7, screenHeight1/35);
		add(textField);
		JLabel lblNewLabel = new JLabel("Search:");
		lblNewLabel.setBounds(175, 87, screenWidth1/7, screenHeight1/35);
		lblNewLabel.setFont(labelFontSize);
		lblNewLabel.setForeground(gold);
		add(lblNewLabel);
		
		JButton logoutButton = new JButton("Logout");
		logoutButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setBounds((screenWidth1/2 - screenWidth1/4), (screenHeight1/2 - screenHeight1/4), screenWidth1/2, screenHeight1/2);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				Login panel = new Login(frame);
				frame.setContentPane(panel);
				frame.revalidate();
			}
		});
		logoutButton.setBackground(gold);
		logoutButton.setBounds(612, 19, screenWidth/15, screenHeight/30);
		add(logoutButton);
		
		JLabel userLabel = new JLabel("User: " + Login.eAddress());
		userLabel.setForeground(gold);
		userLabel.setBounds(screenWidth/80, screenHeight/6+screenHeight/6+screenHeight/20+screenHeight/30, screenWidth/8, screenHeight/30);
		userLabel.setFont(labelFont);
		add(userLabel);
		
		
		JLabel facultyLabel = new JLabel("Faculty: " + facs);
		facultyLabel.setForeground(gold);
		facultyLabel.setBounds(screenWidth/80, screenHeight/6+screenHeight/6+screenHeight/20+screenHeight/20, screenWidth/8, screenHeight/30);
		facultyLabel.setFont(labelFont);
		add(facultyLabel);
		
		
		JButton applyNewButton = new JButton("APPLY");
		
		applyNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Authenticator.loadNames();
				String studName = (String)authen.getNamesMap().get(Login.eAddress());
				
				try {
					File myObj = new File("gpa.txt");
					Scanner scanner = new Scanner(myObj);
					
					int lineNum = 0;
					while (scanner.hasNextLine()) {
						String line = scanner.nextLine();
						lineNum++;
						if (line == studName) {
							System.out.println("P");
							JSONParser parser9 = new JSONParser();
							
							try(Reader reader9 = new FileReader("scholarshipNames.json")) {
								JSONObject jsonObject = (JSONObject) parser9.parse(reader9);
								String jsonObjectString = jsonObject.toString();
								
								String substring = (","+" enteredScholarshipName "+"");
								String newString = jsonObjectString.substring(0,jsonObjectString.length() - 2) + substring + "]}";
								
								FileWriter fileWriter = new FileWriter("scholarshipNames.json");
								fileWriter.write(newString);
								fileWriter.flush();
								
								//successfulAdd.setVisible(true);
								
							} catch(IOException r) {
								r.printStackTrace();
							} catch (ParseException p) {
								p.printStackTrace();
							}
							
						}
						else {
							System.out.println("upload transcript first please");
						}
					}
								
			} catch(FileNotFoundException e3) {
				System.out.println("i");
			}
			}
				
				
		});
		
		applyNewButton.setBackground(gold);
		applyNewButton.setBounds(367, 408, screenWidth/15, screenHeight/30);
		add(applyNewButton);
		
		 //Loads in the image of the UofC logo and sets it to fit the specific location on the GUI
		ImageIcon img1 = new ImageIcon("logo.png");
		Image image = img1.getImage();
		Image newimg1 = image.getScaledInstance(75, 75, Image.SCALE_SMOOTH);
		img1 = new ImageIcon(newimg1);
		background_2 = new JLabel("",img1,SwingConstants.LEFT);
		background_2.setVerticalAlignment(SwingConstants.TOP);
		background_2.setBounds(72, 25, 866, 510);
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
	


