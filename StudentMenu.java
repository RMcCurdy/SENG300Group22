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
	//available scholarships and term
	private static List<String> scholarships = Arrays.asList("Arts, Fall", "Arts, Winter", "Arts, Full Year", "Medicine, Fall", "Medicine, Winter", "Medicine, Full Year", "Architecture, Fall", "Architecture, Winter", "Architecture, Full Year", "Business, Fall", "Business, Winter", "Business, Full Year", "Kinesiology, Fall", "Kinesiology, Winter", "Kinesiology, Full Year", "Law, Fall", "Law, Winter", "Law, Full Year", "Nursing, Fall", "Nursing, Winter", "Nursing, Full Year", "Engineering, Fall", "Engineering, Winter", "Engineering, Full Year", "Social Work, Fall", "Social Work, Winter", "Social Work, Full Year", "Education, Fall", "Education, Winter", "Education, Full Year");
	private static String[] term = {"Fall","Winter", "Full Year"};
	private static String[] faculty ={"Arts", "Medicine", "Architecture", "Business","Kinesiology", "Law", "Nursing", "Engineering", "Social Work", "Education"};
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
		//creating a default list model that will contain scholarships so lists can be changed dynamically
		DefaultListModel listModel = new DefaultListModel();
		listModel = new DefaultListModel();
		//list form of term & faculty
		List<String> termList = Arrays.asList(term);
		List<String> facList = Arrays.asList(faculty);	
		//adding the scholarships to list model
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 3; j++) {
				listModel.addElement(facList.get(i)+", "+termList.get(j));
		}}
		//creating list containing scholarships
		JList list = new JList(listModel);
		list.setFont(labelFontSize);
		list.setSize(218, 80);
		list.setLocation(145, 159);

		//only 1 item can be selected 
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		list.setBackground(Color.WHITE);

		//label for selected item
		JLabel selectedLabel = new JLabel("");
		selectedLabel.setFont(labelFontSize);
		selectedLabel.setForeground(Color.BLACK);
		selectedLabel.setBounds(screenWidth/4 - screenWidth/24, screenHeight/7 - screenHeight/40, screenWidth/7, screenHeight/35);
		selectedLabel.setFont(labelFontSize);
		add(selectedLabel);

		//label for error message
		JLabel selectedError = new JLabel("Please select a scholarship");
		selectedError.setFont(labelFontSize);
		selectedError.setForeground(Color.RED);
		selectedError.setBounds(screenWidth/4 - screenWidth/14, screenHeight/7 - screenHeight/40, screenWidth/7, screenHeight/35);
		selectedError.setFont(labelFontSize);
		add(selectedError);
		selectedError.setVisible(false);

		//ScrollPane & ScrollBar for the list
		JScrollPane sp = new JScrollPane(list);
		JScrollBar bar = sp.getVerticalScrollBar();
		bar.setPreferredSize(new Dimension(30, 0));
		//Button for selecting term
		JButton button = new JButton("Select");
		button.setBounds(screenWidth/4 - screenWidth/20, screenHeight/6 + 10 * screenHeight/37 - screenHeight/74, screenWidth/10, screenHeight/60);
		button.setFont(labelFontSize);
		add(button);
	    button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					//if selected term is in index range
					if (list.getSelectedIndex() <= 29 && list.getSelectedIndex() >= 0){
						//save to a variable and display it
						String selec = (String)list.getSelectedValue();
						selectedLabel.setText(selec);
						selectedError.setVisible(false);
					} else {
						//set error message visible
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
		
		//drop down menu for term
		JComboBox ter= new JComboBox(term);
		ter.setSelectedIndex(-1);
		ter.setBounds(screenWidth/4 - screenWidth/10, screenHeight/7, screenWidth/10, screenHeight/50);
		add(ter);
		ter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
				try {
					//if selected term is in index range
					if (ter.getSelectedIndex() <= 2 && ter.getSelectedIndex() >= 0) {
						//Save the selected term to the variable 
						String selectedTer = (String)ter.getSelectedItem();
						//creating the filtered list & setting it to list
						DefaultListModel fList = filteredList(scholarships, selectedTer);
						list.setModel(fList);		
					} else {
						
					}
            	} catch (Exception e) {

				}
			}
        });
		
		//drop down menu for facaulty
		JComboBox fac = new JComboBox(faculty);
		fac.setSelectedIndex(-1);
		fac.setBounds(screenWidth/4, screenHeight/7, screenWidth/10, screenHeight/50);
		add(fac);
		fac.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
				try {
					//if selected term is in index range
					if (fac.getSelectedIndex() <= 9 && fac.getSelectedIndex() >= 0) {
						//Save the selected faculty to the variable 
						String selectedFac = (String)fac.getSelectedItem();
						//creating the filtered list & setting it to list
						DefaultListModel fList2 = filteredList(scholarships, selectedFac);
						list.setModel(fList2);	
					} else {
						
					}
            	} catch (Exception e) {

				}
			}
        });
	}
	//method for filtering list 
	public DefaultListModel filteredList(List<String> list, String word){
	    	DefaultListModel model = new DefaultListModel<>();
	    	//going through every item in the list
	        for (String x : list)
	        {
	        	//if it contains term then add it to the model
	            if (x.contains(word))
	            {
	                model.addElement(x);
	            }
	        }
	        return model;
	 }    
}