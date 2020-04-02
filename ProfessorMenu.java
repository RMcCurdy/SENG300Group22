import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Font;
import javax.swing.JList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;


public class ProfessorMenu extends JPanel {
	private JList list;
	//available scholarships and term
	private static String[] scholarships = {"Science, Fall", "Science, Winter", "Science, Full Year", "Arts, Fall", "Arts, Winter", "Arts, Full Year", "Medicine, Fall", "Medicine, Winter", "Medicine, Full Year", "Architecture, Fall", "Architecture, Winter", "Architecture, Full Year", "Business, Fall", "Business, Winter", "Business, Full Year", "Kinesiology, Fall", "Kinesiology, Winter", "Kinesiology, Full Year", "Law, Fall", "Law, Winter", "Law, Full Year", "Nursing, Fall", "Nursing, Winter", "Nursing, Full Year", "Engineering, Fall", "Engineering, Winter", "Engineering, Full Year", "Social Work, Fall", "Social Work, Winter", "Social Work, Full Year", "Education, Fall", "Education, Winter", "Education, Full Year"};
	private static final long serialVersionUID = 1L;
	private JTextField textField;

	/**
	 * Create the panel.
	 * @param auth 
	 * @param frame 
	 */
	public ProfessorMenu(JFrame frame, Account user) {
		//Save the user's screen resolution to variables, used to format GUI correctly
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;

		setLayout(null);

		//Font size for remaining labels
		Font labelFontSize = new Font("Arial", Font.PLAIN, screenHeight/60);

		/**
		 * LIST
		 */
		//creating list containing scholarships
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
		selectedLabel.setBounds(screenWidth/4 - screenWidth/14, screenHeight/7 - screenHeight/200, screenWidth/7, screenHeight/35);
		selectedLabel.setFont(labelFontSize);
		add(selectedLabel);

		//label for testing
		JLabel selectedError = new JLabel("Please select a scholarship");
		selectedError.setFont(labelFontSize);
		selectedError.setForeground(Color.RED);
		selectedError.setBounds(screenWidth/4 - screenWidth/14, screenHeight/7 - screenHeight/200, screenWidth/7, screenHeight/35);
		selectedError.setFont(labelFontSize);
		add(selectedError);
		selectedError.setVisible(false);

		//ScrollPane to display the list
		JScrollPane sp = new JScrollPane(list);
		JScrollBar bar = sp.getVerticalScrollBar();
		bar.setPreferredSize(new Dimension(30, 0));
		JButton button = new JButton("Select");
		button.setBounds(screenWidth/4 - screenWidth/20, screenHeight/6 + 10 * screenHeight/37 - screenHeight/74, screenWidth/10, screenHeight/60);
		button.setFont(labelFontSize);
		add(button);
	    button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (list.getSelectedIndex() <= 32 && list.getSelectedIndex() >= 0){
						String selec = (String)list.getSelectedValue();
						frame.setBounds((screenWidth/2 - screenWidth/4), (screenHeight/2 - screenHeight/4), screenWidth/2, screenHeight/2);
						frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						NominateStudent panel = new NominateStudent(frame, user);
						frame.setContentPane(panel);
						frame.revalidate();
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
		sp.setLocation(screenWidth/4 - screenWidth/10, screenHeight/6);
		sp.setSize(screenWidth/5, screenHeight/4);
		add(sp);
		setVisible(true);

		//Header of the system name
		JLabel header = new JLabel("UofC Professor Scholarship Portal");
		header.setHorizontalAlignment(SwingConstants.CENTER);
		header.setForeground(Color.RED);
		header.setBounds(screenWidth/4 - screenWidth/6, screenHeight/25, screenWidth/3, screenHeight/25);
		header.setFont(new Font("Arial", Font.PLAIN, screenHeight/30));
		add(header);
		
		//search bar for list of scholarships
		textField = new JTextField();
		textField.setBounds(screenWidth/4 - screenWidth/14, screenHeight/9, screenWidth/7, screenHeight/35);
		add(textField);
		JLabel lblNewLabel = new JLabel("Search:");
		lblNewLabel.setBounds(screenWidth/4 - screenWidth/8 + screenWidth/100, screenHeight/9, screenWidth/7, screenHeight/35);
		lblNewLabel.setFont(labelFontSize);
		add(lblNewLabel);
	}
}