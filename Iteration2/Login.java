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
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import java.net.URL;

public class Login extends JPanel {
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Create the panel.
	 */
	public Login(JFrame frame, Authenticator auth) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;

		setLayout(null);

		System.out.println(screenWidth);
		System.out.println(screenHeight);

		Font textFieldFontSize = new Font("Arial", Font.PLAIN, 20);
		
		JLabel lblNewLabel = new JLabel("UofC Scholarship Portal");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setBounds(screenWidth/4 - screenWidth/8, screenHeight/12, screenWidth/4, screenHeight/25);
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, screenHeight/30));
		add(lblNewLabel);
		
		JLabel Username = new JLabel("Email");
		Username.setBounds(screenWidth/4 - screenWidth/12, screenHeight/6, screenWidth/10, screenHeight/40);
		Username.setFont(new Font("Arial", Font.PLAIN, screenHeight/80));
		add(Username);
		
		JLabel Password = new JLabel("Password");
		Password.setBounds(screenWidth/4 - screenWidth/10, screenHeight/6 + screenHeight/20, screenWidth/10, screenHeight/40);
		Password.setFont(new Font("Arial", Font.PLAIN, screenHeight/80));
		add(Password);
		
		textField = new JTextField();
		textField.setBounds(screenWidth/4 - screenWidth/18, screenHeight/6, screenWidth/10, screenHeight/40);
		add(textField);
		textField.setFont(textFieldFontSize);
		
		textField_1 = new JPasswordField();
		textField_1.setBounds(screenWidth/4 - screenWidth/18, screenHeight/6 + screenHeight/20, screenWidth/10, screenHeight/40);
		add(textField_1);
		textField_1.setFont(textFieldFontSize);
		
		JLabel invalid_login = new JLabel("Login Successful");
		invalid_login.setBounds(156, 172, 120, 16);
		add(invalid_login);
		
		JButton btnNewButton_1 = new JButton("Create Account");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
				int screenHeight = screenSize.height;
				int screenWidth = screenSize.width;
				//frame = new JFrame();
				frame.setBounds((screenWidth/2 - screenWidth/4), (screenHeight/2 - screenHeight/4), screenWidth/2, screenHeight/2);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				Create panel = new Create(frame, auth);
				frame.setContentPane(panel);
				frame.revalidate();
			}
		});
		btnNewButton_1.setBounds(screenWidth/4 - screenWidth/24, screenHeight/6 + 4 * screenHeight/25, screenWidth/13, screenHeight/35);
		btnNewButton_1.setFont(new Font("Arial", Font.PLAIN, screenHeight/80));
		add(btnNewButton_1);
		
		JLabel wrng = new JLabel("INCORRECT EMAIL/PASSWORD");
		wrng.setForeground(Color.RED);
		wrng.setBounds(85, 172, 243, 16);
		add(wrng);
		invalid_login.setVisible(false);
		wrng.setVisible(false);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String user = textField.getText();
				String pass = textField_1.getText();
				Account acc = auth.login(user, pass);
				try {
				    File myObj = new File("privateInfo.txt");
				    Scanner myReader = new Scanner(myObj);
				    while (myReader.hasNextLine()) {
				        String data = myReader.nextLine();
				        System.out.println(data);
				        if (user.equals(data)) {
					    	System.out.println("&");
					    	String data1 = myReader.nextLine();
					    	if (pass.equals(data1)) {
					    		System.out.println("0");
					    		wrng.setVisible(false);
					    		invalid_login.setVisible(true);
					    	} else {
					    		wrng.setVisible(true);
					    		invalid_login.setVisible(false);
					    	}
					    	  
				        }
				      }
				      myReader.close();
				    } catch (FileNotFoundException e1) {
				      System.out.println("An error occurred.");
				      e1.printStackTrace();
				    }
			}
		});
		btnNewButton.setBounds(screenWidth/4 - screenWidth/27, screenHeight/6 + 3 * screenHeight/25, screenWidth/15, screenHeight/35);
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, screenHeight/80));
		add(btnNewButton);

	}
}
