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
import java.lang.Math;

public class Login extends JPanel {
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Create the panel.
	 */
	public Login(JFrame frame, Authenticator auth) {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("UofC Scholarship Portal");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setBounds(122, 38, 181, 16);
		add(lblNewLabel);
		
		JLabel Username = new JLabel("Email");
		Username.setBounds(33, 99, 83, 16);
		add(Username);
		
		JLabel Password = new JLabel("Password");
		Password.setBounds(33, 139, 83, 16);
		add(Password);
		
		textField = new JTextField();
		textField.setBounds(146, 94, 130, 26);
		add(textField);
		textField.setColumns(10);
		
		textField_1 = new JPasswordField();
		textField_1.setBounds(146, 134, 130, 26);
		add(textField_1);
		textField_1.setColumns(10);
		
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
		btnNewButton_1.setBounds(132, 241, 150, 29);
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
					    	  }
					    	  else {
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
		btnNewButton.setBounds(144, 200, 117, 29);
		add(btnNewButton);

	}
}
