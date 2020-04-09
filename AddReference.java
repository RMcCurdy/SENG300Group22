import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.awt.Color;

public class AddReference extends JPanel {
	private JTextField studentid;

	/**
	 * Create the panel.
	 */
	public AddReference(JFrame frame, Account user, String scholarshipName) {
		//Save the user's screen resolution to variables, used to format GUI correctly
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;

		setLayout(null);
		
		studentid = new JTextField();
		studentid.setBounds(192, 102, 167, 22);
		add(studentid);
		studentid.setColumns(10);
		
		JLabel lblID = new JLabel("Students ID:");
		lblID.setBounds(192, 76, 100, 16);
		add(lblID);
		
		JTextArea referenceletter = new JTextArea();
		referenceletter.setBounds(192, 176, 486, 263);
		add(referenceletter);
		
		JLabel lblNewLabel_1 = new JLabel("Reference Letter: (optional)");
		lblNewLabel_1.setBounds(192, 149, 177, 16);
		add(lblNewLabel_1);
		
		JLabel lblScholarship = new JLabel("Scholarship: "+scholarshipName);
		lblScholarship.setBounds(192, 31, 282, 22);
		add(lblScholarship);
		
		JLabel invalidID = new JLabel("Invalid ID");
		invalidID.setForeground(Color.RED);
		invalidID.setBounds(371, 105, 86, 16);
		add(invalidID);
		invalidID.setVisible(false);

		JButton btnNewButton = new JButton("Recommend Student");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (studentid.getText().length() != 8)
					invalidID.setVisible(true);
				else {
					invalidID.setVisible(false);
				
					String filename = "./ReferenceLetters/"+scholarshipName + studentid.getText() + ".txt";
					
					System.out.println("Want to save at: "+filename);
					
					try {
						BufferedWriter bw = new BufferedWriter(new FileWriter(filename, true));
						String output = "Scholarship: "+scholarshipName+"\nStudent being recommended: "+studentid.getText()+"\nProfessor: +user.getLastName()+, +user.getFirstName()+ +user.getEmail()\n"+referenceletter.getText();
						System.out.println(output);
						bw.write(output);
						bw.newLine();
						bw.close();
						
						System.out.println("Saved to "+filename);
					} 
					catch (Exception ex) 
					{
						ex.printStackTrace();
					}
					
					frame.setBounds((screenWidth/2 - screenWidth/4), (screenHeight/2 - screenHeight/4), screenWidth/2, screenHeight/2);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					ProfessorMenu panel = new ProfessorMenu(frame, user);
					frame.setContentPane(panel);
					frame.revalidate();
				}
			}
		});
		btnNewButton.setBounds(335, 452, 187, 25);
		add(btnNewButton);
	}
}
