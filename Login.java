import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;

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
		
		JLabel Username = new JLabel("Username");
		Username.setBounds(33, 99, 83, 16);
		add(Username);
		
		JLabel Password = new JLabel("Password");
		Password.setBounds(33, 139, 83, 16);
		add(Password);
		
		textField = new JTextField();
		textField.setBounds(146, 94, 130, 26);
		add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(146, 134, 130, 26);
		add(textField_1);
		textField_1.setColumns(10);
		
		JLabel invalid_login = new JLabel("Invalid Login");
		invalid_login.setBounds(156, 172, 120, 16);
		add(invalid_login);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String user = textField.getText();
				String pass = textField_1.getText();
				Account acc = auth.login(user, pass);
				if (acc == null) {
					invalid_login.setVisible(true);
				}
				
			}
		});
		btnNewButton.setBounds(144, 200, 117, 29);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Create Account");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//frame = new JFrame();
				frame.setBounds(100, 100, 450, 300);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				Create panel = new Create(frame, auth);
				frame.setContentPane(panel);
				frame.revalidate();
			}
		});
		btnNewButton_1.setBounds(132, 241, 150, 29);
		add(btnNewButton_1);
		invalid_login.setVisible(false);

	}
}
