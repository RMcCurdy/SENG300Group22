/*
 * code burrowed from tutorial 
 * added a create account functionality 
 * which opens up a new window if user
 * does not have an account and 
 * wishes to create one
*/
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Create extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Create the panel.
	 * @param auth 
	 * @param frame 
	 */
	public Create(JFrame frame, Authenticator auth) {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("First Name:");
		lblNewLabel.setBounds(6, 64, 87, 16);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Last name:");
		lblNewLabel_1.setBounds(6, 95, 87, 16);
		add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(105, 59, 130, 26);
		add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(105, 90, 130, 26);
		add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Email:");
		lblNewLabel_2.setBounds(6, 134, 61, 16);
		add(lblNewLabel_2);
		
		textField_2 = new JTextField();
		textField_2.setBounds(105, 128, 130, 26);
		add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Student ID:");
		lblNewLabel_3.setBounds(6, 173, 87, 16);
		add(lblNewLabel_3);
		
		textField_3 = new JTextField();
		textField_3.setBounds(105, 166, 130, 26);
		add(textField_3);
		textField_3.setColumns(10);
		
		JButton btnNewButton = new JButton("Create Account");
		btnNewButton.setBounds(105, 232, 130, 29);
		add(btnNewButton);
		
	
	}
}
