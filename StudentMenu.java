import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;

public class StudentMenu extends JPanel {
	private JList list;
	//available scholarships and term
	private static List<String> scholarships;
	private static String[] term = {"Fall","Winter", "Full Year"};
	private static String[] facaulty ={"Arts", "Medicine", "Architecture", "Business","Kinesiology", "Law", "Nursing", "Engineering", "Social Work", "Education"};
	private static final long serialVersionUID = 1L;
	
	//NEED VARIABLES FOR NEW MENU

	/**
	 * Create the panel.
	 * @param auth 
	 * @param frame 
	 */
	public StudentMenu(JFrame frame, Account user) {
		//Save the user's screen resolution to variables, used to format GUI correctly
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;

		setLayout(null);

		/**
		 * LABELS
		 */

		//Font size for remaining labels
		Font labelFontSize = new Font("Arial", Font.PLAIN, screenHeight/60);
			
		/**
		 * TEXT FIELDS
		 */

		//Text field for searching for a scholarship name
		//Text field for inputing student's GPA (They will need to upload actual "transcript" for confirmation of this)

		/**
		 * MESSAGES
		 */

		//Create any error or confirmation messages here
		//Error message if student doesn't meet requirements for a specific scholarship
		
		/**
		 * BUTTONS
		 */

		//Create a button to upload a "transcript" to the system
		//Create a button to confirm selection of transcript selected from the list to apply for
		//Create a button to allow user to navigate to page of scholarships they have applied to and can check status

		/**
		 * LIST
		 */
		//creating list containing scholarships
		scholarships = Arrays.asList("Arts, Fall", "Arts, Winter", "Arts, Full Year", "Medicine, Fall", "Medicine, Winter", "Medicine, Full Year", "Architecture, Fall", "Architecture, Winter", "Architecture, Full Year", "Business, Fall", "Business, Winter", "Business, Full Year", "Kinesiology, Fall", "Kinesiology, Winter", "Kinesiology, Full Year", "Law, Fall", "Law, Winter", "Law, Full Year", "Nursing, Fall", "Nursing, Winter", "Nursing, Full Year", "Engineering, Fall", "Engineering, Winter", "Engineering, Full Year", "Social Work, Fall", "Social Work, Winter", "Social Work, Full Year", "Education, Fall", "Education, Winter", "Education, Full Year");
		list = new JList(scholarships.toArray());
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
		selectedLabel.setBounds(258, 95, screenWidth/7, screenHeight/35);
		selectedLabel.setFont(labelFontSize);
		add(selectedLabel);

		//label for testing
		JLabel selectedError = new JLabel("Please select a scholarship");
		selectedError.setFont(labelFontSize);
		selectedError.setForeground(Color.RED);
		selectedError.setBounds(258, 95, screenWidth/7, screenHeight/35);
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
					if (list.getSelectedIndex() <= 29 && list.getSelectedIndex() >= 0){
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
		sp.setLocation(screenWidth/4 - screenWidth/10, screenHeight/6);
		sp.setSize(screenWidth/5, screenHeight/4);
		add(sp);
		setVisible(true);

		//Header of the system name
		JLabel header = new JLabel("UofC Student Scholarship Portal");
		header.setHorizontalAlignment(SwingConstants.CENTER);
		header.setForeground(Color.RED);
		header.setBounds(screenWidth/4 - screenWidth/6, screenHeight/25, screenWidth/3, screenHeight/25);
		header.setFont(new Font("Arial", Font.PLAIN, screenHeight/30));
		add(header);
		
		JComboBox ter= new JComboBox(term);
		ter.setSelectedIndex(-1);
		ter.setBounds(216, 120, 144, 27);
		add(ter);
		ter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
				try {
					if (ter.getSelectedIndex() <= 29 && ter.getSelectedIndex() >= 0) {
						//Save the selected term to the variable 
						String selectedTer = (String)ter.getSelectedItem();
						List<String> fList = filteredList(scholarships, selectedTer);		
					} else {
						
					}
            	} catch (Exception e) {

				}
			}
        });
		
		JComboBox fac = new JComboBox(facaulty);
		fac.setSelectedIndex(-1);
		fac.setBounds(360, 120, 144, 27);
		add(fac);
		fac.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
				try {
					if (fac.getSelectedIndex() <= 29 && fac.getSelectedIndex() >= 0) {
						//Save the selected faculty to the variable 
						String selectedFac = (String)fac.getSelectedItem();
						List<String> fList2 = filteredList(scholarships, selectedFac);
					} else {
						
					}
            	} catch (Exception e) {

				}
			}
        });
		
		

	}
	public List<String> filteredList(List<String> list, String item)
    {
        List<String> fList = new ArrayList<>();
        for (String x : list)
        {
            if (x.contains(item))
            {
                fList.add(x);
            }
        }
        return fList;
    }
}