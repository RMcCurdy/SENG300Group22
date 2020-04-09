import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.Color;
import java.awt.Component;

import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ApplyScholarships extends JPanel {
	
	private JList list;
	
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	
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

		setLayout(null);

		//Font size for remaining labels
		Font labelFontSize = new Font("Arial", Font.PLAIN, screenHeight1/60);

		Authenticator authen = new Authenticator();
		
		String facs = (String)authen.getRolesMap().get(Login.eAddress());
		
		/**
		 * LIST
		 */
		//creating list containing scholarships
		
		DefaultListModel scholarships = new DefaultListModel();
		scholarships.addElement(facs + " Fall");
		scholarships.addElement(facs + " Winter");
		scholarships.addElement(facs + " Full Year");
		list = new JList(scholarships);
		list.setFont(labelFontSize);
		list.setSize(218, 80);
		list.setLocation(145, 159);

		//only 1 item can be selected and list will only display 3 items
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		list.setVisibleRowCount(3);
		list.setBackground(Color.WHITE);

		//label for testing
		JLabel selectedLabel = new JLabel("");
		selectedLabel.setFont(labelFontSize);
		selectedLabel.setForeground(Color.BLACK);
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
		button.setBounds(screenWidth1/4 - screenWidth1/20, screenHeight1/6 + 10 * screenHeight1/37 - screenHeight1/74, screenWidth1/10, screenHeight1/60);
		button.setFont(labelFontSize);
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
		sp.setLocation(screenWidth1/4 - screenWidth1/10, screenHeight1/6);
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
		textField.setBounds(screenWidth1/4 - screenWidth1/14, screenHeight1/9, screenWidth1/7, screenHeight1/35);
		add(textField);
		JLabel lblNewLabel = new JLabel("Search:");
		lblNewLabel.setBounds(screenWidth1/4 - screenWidth1/8 + screenWidth1/100, screenHeight1/9, screenWidth1/7, screenHeight1/35);
		lblNewLabel.setFont(labelFontSize);
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
		logoutButton.setBounds(615, 8, 117, 29);
		add(logoutButton);
		
		JLabel userLabel = new JLabel("User: " + Login.eAddress());
		userLabel.setBounds(6, 8, 457, 16);
		add(userLabel);
		
		
		JLabel facultyLabel = new JLabel("Faculty: " + facs);
		facultyLabel.setBounds(6, 25, 294, 16);
		add(facultyLabel);
		
	}
	

}
