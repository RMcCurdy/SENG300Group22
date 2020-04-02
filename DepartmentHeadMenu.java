import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Font;

import java.awt.Color;
import javax.swing.JList;
import javax.swing.AbstractListModel;

public class DepartmentHeadMenu extends JPanel {

	private static final long serialVersionUID = 1L;
	
	public ScholarshipsList scholarshipslist = new ScholarshipsList();
	public List<Scholarship> list = scholarshipslist.getScholarships();
	
	/**
	 * Create the panel.
	 * @param user
	 * @param frame 
	 */
	public DepartmentHeadMenu(JFrame frame, Account user) {
		//Save the user's screen resolution to variables, used to format GUI correctly
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;

		setLayout(null);

		//Font size for remaining labels
		Font labelFontSize = new Font("Arial", Font.PLAIN, screenHeight/60);

		//Header of the system name
		JLabel header = new JLabel("UofC Department Scholarship Portal");
		JLabel header = new JLabel("UofC Department Head Scholarship Portal");
		header.setBounds(screenWidth/4 - screenWidth/6, screenHeight/25, screenWidth/3, screenHeight/25);
		header.setForeground(Color.RED);
		header.setFont(new Font("Arial", Font.PLAIN, screenHeight/30));
		add(header);
		
		JButton btnAddScholarship = new JButton("Add Scholarship");
		btnAddScholarship.setBounds(174, 418, 170, 25);
		btnAddScholarship.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.setBounds((screenWidth/2 - screenWidth/4), (screenHeight/2 - screenHeight/4), screenWidth/2, screenHeight/2);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				AddScholarship sch = new AddScholarship(frame, user);
				frame.setContentPane(sch);
				frame.revalidate();
			}
		});
		add(btnAddScholarship);
		
		
		int i = 0;
		Object[] data = new Scholarship[list.size()];	// Array of scholarships
		for (Scholarship x : list) {					// Creates an array so it can be displayed in the JList
			data[i] = x;
			i = i + 1;
		}
		JList JList_scholarships = new JList(data);
		JList_scholarships.setBounds(174, 180, 534, 225);
		add(JList_scholarships);
		
		JButton btnRemoveScholarship = new JButton("Remove Scholarship");
		btnRemoveScholarship.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int index = JList_scholarships.getSelectedIndex();	// Finds which scholarship was selected
				scholarshipslist.removeScholarship(index);			// Removes said scholarship
				data[data.length - 1] = null;						// Removes last element from array
				int j = 0;											// Counter variable
				for (Scholarship x : list) {						// Updates array with all remaining scholarships
					data[j] = x;
					j++;
				}
			}
		});
		btnRemoveScholarship.setBounds(538, 418, 170, 25);
		add(btnRemoveScholarship);
	
		
		
		Scholarship[] scholarships = new Scholarship[99];	// CLEAN UP
		try {
			File newObj = new File("scholarships.txt");
		    Scanner newReader = new Scanner(newObj);
		    int i=0;
		    while (newReader.hasNextLine()) {		// Searches through database
				String info = newReader.nextLine();
				String[] scholarshipInfo = info.split(",");
				Scholarship scholarship = new Scholarship(scholarshipInfo[0], scholarshipInfo[1], Integer.parseInt(scholarshipInfo[2]), scholarshipInfo[3], scholarshipInfo[4], scholarshipInfo[5], scholarshipInfo[6]);
				scholarships[i] = scholarship;
				i++;
		    }
		    newReader.close();
		} 
	catch(Exception ex) 
		{
		//Exception thrown if the above code can't proceed
			ex.printStackTrace();
		}
		
		// FORMAT TO LOOK GOOD
		/**JList lstScholarships = new JList();		
		lstScholarships.setModel(new AbstractListModel() {
			public int getSize() {
				return scholarships.length;
			}
			public Object getElementAt(int index) {
				return scholarships[index];
			}
		});
		lstScholarships.setBounds(174, 180, 534, 225);
		add(lstScholarships);
		*/

		//Button for the create account option
		JButton statisticsButton = new JButton("Statistics");
		statisticsButton.addMouseListener(new MouseAdapter() {
			@Override
			//On a mouse click, will take the user to a new GUI to create a new account
			public void mouseClicked(MouseEvent e) {
				frame.setBounds((screenWidth/2 - screenWidth/4), (screenHeight/2 - screenHeight/4), screenWidth/2, screenHeight/2);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				Statistics panel = new Statistics(frame, user);
				frame.setContentPane(panel);
				frame.revalidate();
			}
		});
		statisticsButton.setBounds(screenWidth/4 + screenWidth/60, screenHeight/6 + 3 * screenHeight/25, screenWidth/15, screenHeight/30);
		statisticsButton.setFont(new Font("Arial", Font.PLAIN, screenHeight/60));
		add(statisticsButton);

		JButton btnEditScholarship = new JButton("Edit Scholarship");
		btnEditScholarship.setBounds(356, 418, 170, 25);
		btnEditScholarship.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setBounds((screenWidth/2 - screenWidth/4), (screenHeight/2 - screenHeight/4), screenWidth/2, screenHeight/2);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				EditScholarship sch = new EditScholarship(frame, user, JList_scholarships.getSelectedIndex());
				frame.setContentPane(sch);
				frame.revalidate();			
			}
		});
		add(btnEditScholarship);
		
	
	}
}
