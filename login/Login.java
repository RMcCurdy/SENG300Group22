package login;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Image;

import objects.*;

import professorRelated.ProfessorMenu;
import studentRelated.StudentMenu;

import departmentHeadRelated.DepartmentHeadMenu;

public class Login extends JPanel {

	private static JTextField textFieldEmail;
	private JTextField textFieldPass;
	private static String eAddress;
	private boolean valid = false;
	private JLabel background_1;
	private JLabel background_2;
	private Font headerFont;
	private Font labelFont;

	// Creates the login page for the system
	public Login(JFrame frame){
		// Save the screen resolution of the user to a height and width integer
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;

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

		/**
		 * LABELS
		 */

		//Header of the system name
		JLabel lblNewLabel = new JLabel("UofC Scholarship Portal");
		lblNewLabel.setForeground(myRed);
		lblNewLabel.setBounds(screenWidth/4 - screenWidth/12, screenHeight/25, screenWidth/4, screenHeight/25);
		lblNewLabel.setFont(headerFont);
		add(lblNewLabel);
		
		//Label for the email text field
		JLabel Username = new JLabel("Email:");
		Username.setForeground(gold);
		Username.setBounds(screenWidth/4 - screenWidth/9 + screenWidth/200, screenHeight/7, screenWidth/10, screenHeight/40);
		Username.setFont(labelFont);
		add(Username);
		
		//Label for the password text field
		JLabel Password = new JLabel("Password:");
		Password.setForeground(gold);
		Password.setBounds(screenWidth/4 - screenWidth/8, screenHeight/7 + screenHeight/20, screenWidth/10, screenHeight/40);
		Password.setFont(labelFont);
		add(Password);
		
		/**
		 * TEXT FIELDS
		 */

		//Text field for the Email
		textFieldEmail = new JTextField();
		textFieldEmail.setBounds(screenWidth/4 - screenWidth/14, screenHeight/7, screenWidth/7, screenHeight/35);
		add(textFieldEmail);
		textFieldEmail.setFont(labelFont);
		
		//Text field for the Password
		textFieldPass = new JPasswordField();
		textFieldPass.setBounds(screenWidth/4 - screenWidth/14, screenHeight/7 + screenHeight/20, screenWidth/7, screenHeight/35);
		add(textFieldPass);
		textFieldPass.setFont(labelFont);
		
		/**
		 * MESSAGES
		 */

		//Error message displated when email does not match
		JLabel wrngEmail = new JLabel("Incorrect Email");
		wrngEmail.setForeground(Color.RED);
		wrngEmail.setBounds(screenWidth/4 - screenWidth/20, screenHeight/10, screenWidth/7, screenHeight/30);
		wrngEmail.setFont(new Font("Arial", Font.PLAIN, screenHeight/60));
		add(wrngEmail);

		//Error message displayed when password does not match email
		JLabel wrngPassword = new JLabel("Incorrect Password");
		wrngPassword.setForeground(Color.RED);
		wrngPassword.setBounds(screenWidth/4 - screenWidth/20, screenHeight/10, screenWidth/7, screenHeight/30);
		wrngPassword.setFont(new Font("Arial", Font.PLAIN, screenHeight/60));
		add(wrngPassword);

		//Displayed when username and password has been entered successfully
		JLabel validLogin = new JLabel("Login Successful");
		validLogin.setBounds(screenWidth/4 - screenWidth/20, screenHeight/10, screenWidth/7, screenHeight/30);
		validLogin.setFont(new Font("Arial", Font.PLAIN, screenHeight/60));
		add(validLogin);

		//Set the messages of logging in or failing to log in to be invisible
		validLogin.setVisible(false);
		wrngEmail.setVisible(false);
		wrngPassword.setVisible(false);
	
		/**
		 * BUTTONS
		 */

		//Button for logging into the system
		JButton loginButton = new JButton("Login");
		loginButton.addMouseListener(new MouseAdapter() {
			@Override
			//On a mouse click, checks to see if the email and password is a match
			//NEED TO FIX BECAUSE OF NEW WAY OF STORING USERNAME AND PASSWORD
			public void mouseClicked(MouseEvent e) {
				String email = textFieldEmail.getText();
				String pass = textFieldPass.getText();

				Authenticator authen = new Authenticator();
				
				Account user = null;
				
				//gets value stored in email text-field and searches for corresponding key in hash-map 
				//if match is found gets the associated value and compares to password text-field 
				//successful login if both match 
				//display corresponding error message otherwise
				//for student login
				if (authen.getPeopleMap().containsKey(email)) {
					String pass1 = (String)authen.getPeopleMap().get(email);
					if (pass1.equals(pass)) {
						System.out.println("7");
						wrngPassword.setVisible(false);
						wrngEmail.setVisible(false);
						validLogin.setVisible(true);
						valid = true;
						
						frame.setBounds((screenWidth/2 - screenWidth/4), (screenHeight/2 - screenHeight/4), screenWidth/2, screenHeight/2);
						frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						StudentMenu stuMenu = new StudentMenu(frame, user);
						frame.setContentPane(stuMenu);
						frame.revalidate();
						
					}
							
					else {
						wrngEmail.setVisible(false);
						validLogin.setVisible(false);
						wrngPassword.setVisible(true);
					}
				}
				
				else {
					wrngEmail.setVisible(true);
					validLogin.setVisible(false);
					wrngPassword.setVisible(false);
				}

				//gets value stored in email text-field and searches for corresponding key in hash-map 
				//if match is found gets the associated value and compares to password text-field 
				//successful login if both match 
				//display corresponding error message otherwise
				//for professor login
				if (authen.getDeptMap().containsKey(email)) {
					String pass1 = (String)authen.getDeptMap().get(email);
					if (pass1.equals(pass)) {
						System.out.println("7");
						wrngPassword.setVisible(false);
						wrngEmail.setVisible(false);
						validLogin.setVisible(true);
					
						frame.setBounds((screenWidth/2 - screenWidth/4), (screenHeight/2 - screenHeight/4), screenWidth/2, screenHeight/2);
						frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						ProfessorMenu profMenu = new ProfessorMenu(frame, email);
						frame.setContentPane(profMenu);
						frame.revalidate();
					}
					else {
						wrngEmail.setVisible(false);
						validLogin.setVisible(false);
						wrngPassword.setVisible(true);
					}
				}
				else {
					wrngEmail.setVisible(false);
					validLogin.setVisible(false);
					wrngPassword.setVisible(true);
				}

				//gets value stored in email text-field and searches for corresponding key in hash-map 
				//if match is found gets the associated value and compares to password text-field 
				//successful login if both match 
				//display corresponding error message otherwise
				//for administrator login
				if (authen.getAdminMap().containsKey(email)) {
					String pass1 = (String)authen.getAdminMap().get(email);
					if (pass1.equals(pass)) {
						System.out.println("7");
						wrngPassword.setVisible(false);
						wrngEmail.setVisible(false);
						validLogin.setVisible(true);
					
						frame.setBounds((screenWidth/2 - screenWidth/4), (screenHeight/2 - screenHeight/4), screenWidth/2, screenHeight/2);
						frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						DepartmentHeadMenu adminMenu = new DepartmentHeadMenu(frame, user);
						frame.setContentPane(adminMenu);
						frame.revalidate();
					}
					else {
						wrngEmail.setVisible(false);
						validLogin.setVisible(false);
						wrngPassword.setVisible(true);
					}
				}
				else {
					wrngEmail.setVisible(false);
					validLogin.setVisible(false);
					wrngPassword.setVisible(true);
				}
			}
		});
		loginButton.setBackground(gold);
		loginButton.setBounds(screenWidth/4 - 2*screenWidth/30 - screenWidth/60, screenHeight/6 + 3 * screenHeight/25, screenWidth/15, screenHeight/30);
		loginButton.setFont(labelFont);
		add(loginButton);

		//Button for the create account option
		JButton createButton = new JButton("Sign Up");
		createButton.addMouseListener(new MouseAdapter() {
			@Override
			//On a mouse click, will take the user to a new GUI to create a new account
			public void mouseClicked(MouseEvent e) {
				frame.setBounds((screenWidth/2 - screenWidth/4), (screenHeight/2 - screenHeight/4), screenWidth/2, screenHeight/2);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				Create panel = new Create(frame);
				frame.setContentPane(panel);
				frame.revalidate();
			}
		});
		createButton.setBackground(gold);
		createButton.setBounds(screenWidth/4 + screenWidth/60, screenHeight/6 + 3 * screenHeight/25, screenWidth/15, screenHeight/30);
		createButton.setFont(labelFont);
		add(createButton);

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
		background_2.setBounds(screenWidth/4 - screenWidth/8, screenHeight/35, 300, 300);
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

        //method to return the text stored in email address text-box
	public static String eAddress() {
		eAddress = textFieldEmail.getText(); 
		return eAddress;
	}

}