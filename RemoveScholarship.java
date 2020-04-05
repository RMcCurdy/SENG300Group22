import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.Color;

public class RemoveScholarship extends JPanel {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Create the panel.
	 */
	public RemoveScholarship(JFrame frame, Account user) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		
		setLayout(null);

		//Font size for remaining labels
		Font labelFontSize = new Font("Arial", Font.PLAIN, screenHeight/60);

		//Header of the system name
		JLabel header = new JLabel("Remove Scholarships");
		header.setBounds(screenWidth/4 - screenWidth/6, screenHeight/25, screenWidth/3, screenHeight/25);
		header.setForeground(Color.RED);
		header.setFont(labelFontSize);
		add(header);
		
		
		JButton btnSaveChanges = new JButton("Remove");
		btnSaveChanges.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				// On click run if statements to determine if scholarship can be deleted.
				// Use some way of taking in a txt document and deleting a line from it.
				
				//Set the frame size on the closing of the create account GUI
				frame.setBounds((screenWidth/2 - screenWidth/4), (screenHeight/2 - screenHeight/4), screenWidth/2, screenHeight/2);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				DepartmentHeadMenu dMenu = new DepartmentHeadMenu(frame, user);
				frame.setContentPane(dMenu);
				frame.revalidate();
			}
		});
		btnSaveChanges.setBounds(screenWidth/4 - screenWidth/14 + screenWidth/60 - screenWidth/200, screenHeight/7 + 8*screenHeight/30 + screenHeight/90, screenWidth/20, screenHeight/33);
		add(btnSaveChanges);
		
		JButton btnCancel = new JButton("Back");
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setBounds((screenWidth/2 - screenWidth/4), (screenHeight/2 - screenHeight/4), screenWidth/2, screenHeight/2);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				DepartmentHeadMenu dMenu = new DepartmentHeadMenu(frame, user);
				frame.setContentPane(dMenu);
				frame.revalidate();
			}
		});
		btnCancel.setBounds(screenWidth/4 - screenWidth/14 + 2*screenWidth/30 + screenWidth/200, screenHeight/7 + 8*screenHeight/30 + screenHeight/90, screenWidth/20, screenHeight/33);
		add(btnCancel);

	}
}
