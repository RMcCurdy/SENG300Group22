import javax.swing.JPanel;
import javax.swing.JPasswordField;

import java.io.File;
import java.io.FileNotFoundException; 
import java.util.Scanner; 
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Font;

public class Login extends JPanel {

	private JTextField textFieldEmail;
	private JTextField textFieldPass;

	//Creates the login page for the system
	public Login(JFrame frame) {
		//Save the screen resolution of the user to a height and width integer
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;

		setLayout(null);
		
		/**
		 * LABELS
		 */

		//Header of the system name
		JLabel lblNewLabel = new JLabel("UofC Scholarship Portal");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setBounds(screenWidth/4 - screenWidth/8, screenHeight/25, screenWidth/4, screenHeight/25);
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, screenHeight/30));
		add(lblNewLabel);
		
		//Label for the email text field
		JLabel Username = new JLabel("Email:");
		Username.setBounds(screenWidth/4 - screenWidth/9 + screenWidth/200, screenHeight/7, screenWidth/10, screenHeight/40);
		Username.setFont(new Font("Arial", Font.PLAIN, screenHeight/60));
		add(Username);
		
		//Label for the password text field
		JLabel Password = new JLabel("Password:");
		Password.setBounds(screenWidth/4 - screenWidth/8, screenHeight/7 + screenHeight/20, screenWidth/10, screenHeight/40);
		Password.setFont(new Font("Arial", Font.PLAIN, screenHeight/60));
		add(Password);
		
		/**
		 * TEXT FIELDS
		 */

		//Font size and style used for the text fields
		Font textFieldFontSize = new Font("Arial", Font.PLAIN, screenHeight/60);

		//Text field for the Email
		textFieldEmail = new JTextField();
		textFieldEmail.setBounds(screenWidth/4 - screenWidth/14, screenHeight/7, screenWidth/7, screenHeight/35);
		add(textFieldEmail);
		textFieldEmail.setFont(textFieldFontSize);
		
		//Text field for the Password
		textFieldPass = new JPasswordField();
		textFieldPass.setBounds(screenWidth/4 - screenWidth/14, screenHeight/7 + screenHeight/20, screenWidth/7, screenHeight/35);
		add(textFieldPass);
		textFieldPass.setFont(textFieldFontSize);
		
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

				try {
				    File myObj = new File("accountLogins.txt");
				    Scanner myReader = new Scanner(myObj);

				    while (myReader.hasNextLine()) {
						String data = myReader.nextLine();
											
						if (email.equals(data)) {
					    	String data1 = myReader.nextLine();
					    					    	
					    	// VALID LOGIN
					    	if (pass.equals(data1)) {
													    							    		
					    		Account user = null;
					    		
					    		File newObj = new File("accountInformation.txt");
							    Scanner newReader = new Scanner(newObj);
					    		
							    while (newReader.hasNextLine()) {		// Searches through database
									String info = newReader.nextLine();
									String[] accountInfo = info.split(",");
									if (accountInfo[0].equals(email)) {	// Once found
										// String email, String firstName, String lastName, int ID, int type
								    	user = new Account(accountInfo[0], accountInfo[1], accountInfo[2], Integer.parseInt(accountInfo[3]), Integer.parseInt(accountInfo[4]), accountInfo[5]);
									}
							    }
							    newReader.close();
							    							    
							    if (user == null) {
							    	System.out.println("ERROR: User not found in database");
							    	// Should only is if email is stored in accountLogins, but not accounts. AKA problem with saving new accounts
							    }
					    									    
							    // Depending on type, different window will open
								if (user.getType() == 0) {
									frame.setBounds((screenWidth/2 - screenWidth/4), (screenHeight/2 - screenHeight/4), screenWidth/2, screenHeight/2);
									frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
									StudentMenu stuMenu = new StudentMenu(frame, user);
									frame.setContentPane(stuMenu);
									frame.revalidate();
								} else if (user.getType() == 1) {
									frame.setBounds((screenWidth/2 - screenWidth/4), (screenHeight/2 - screenHeight/4), screenWidth/2, screenHeight/2);
									frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
									ProfessorMenu profMenu = new ProfessorMenu(frame, user);
									frame.setContentPane(profMenu);
									frame.revalidate();
								} else if (user.getType() == 2) {
									frame.setBounds((screenWidth/2 - screenWidth/4), (screenHeight/2 - screenHeight/4), screenWidth/2, screenHeight/2);
									frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
									DepartmentHeadMenu dhMenu = new DepartmentHeadMenu(frame, user);
									frame.setContentPane(dhMenu);
									frame.revalidate();
								} 
							} else {
								wrngEmail.setVisible(false);
								validLogin.setVisible(false);
								wrngPassword.setVisible(true);
								break;
					    	}
						} else {
							String data1 = myReader.nextLine();
							if (data1 != null){
								wrngEmail.setVisible(true);
								validLogin.setVisible(false);
								wrngPassword.setVisible(false);
							}
						}
					} myReader.close();
					
				} catch (FileNotFoundException e1) {
					System.out.println("An error occurred.");
					e1.printStackTrace();
				Authenticator authen = new Authenticator();
				
				Account user = null;
				
				//gets value stored in email text-field and searches for corresponding key in hash-map 
				//if match is found gets the associated value and compares to password text-field 
				//successful login if both match 
				if (authen.getPeopleMap().containsKey(email)) {
					String pass1 = (String)authen.getPeopleMap().get(email);
					if (pass1.equals(pass)) {
						System.out.println("7");
						wrngPassword.setVisible(false);
						wrngEmail.setVisible(false);
						validLogin.setVisible(true);
						
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
				
				if (authen.getDeptMap().containsKey(email)) {
					String pass1 = (String)authen.getDeptMap().get(email);
					if (pass1.equals(pass)) {
						System.out.println("7");
						wrngPassword.setVisible(false);
						wrngEmail.setVisible(false);
						validLogin.setVisible(true);
					
						frame.setBounds((screenWidth/2 - screenWidth/4), (screenHeight/2 - screenHeight/4), screenWidth/2, screenHeight/2);
						frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						DepartmentHeadMenu profMenu = new DepartmentHeadMenu(frame, user);
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
			}
		});

		loginButton.setBounds(screenWidth/4 - 2*screenWidth/30 - screenWidth/60, screenHeight/6 + 3 * screenHeight/25, screenWidth/15, screenHeight/30);
		loginButton.setFont(new Font("Arial", Font.PLAIN, screenHeight/60));
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
		createButton.setBounds(screenWidth/4 + screenWidth/60, screenHeight/6 + 3 * screenHeight/25, screenWidth/15, screenHeight/30);
		createButton.setFont(new Font("Arial", Font.PLAIN, screenHeight/60));
		add(createButton);

	}
}