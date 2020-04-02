import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
	
	//NEED VARIABLES FOR NEW MENU

	/**
	 * Create the panel.
	 * @param auth 
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
		JLabel header = new JLabel("UofC Professor Scholarship Portal");
		header.setBounds(screenWidth/4 - screenWidth/6, screenHeight/25, screenWidth/3, screenHeight/25);
		header.setForeground(Color.RED);
		header.setFont(new Font("Arial", Font.PLAIN, screenHeight/30));
		add(header);
		
		JButton btnAddScholarship = new JButton("Add Scholarship");
		btnAddScholarship.setBounds(174, 418, 170, 25);
		btnAddScholarship.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				System.out.println("Hello World!");
				
				frame.setBounds((screenWidth/2 - screenWidth/4), (screenHeight/2 - screenHeight/4), screenWidth/2, screenHeight/2);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				AddScholarship sch = new AddScholarship(frame, user);
				frame.setContentPane(sch);
				frame.revalidate();
			}
		});
		add(btnAddScholarship);
		
		JButton btnEditScholarship = new JButton("Edit Scholarship");
		btnEditScholarship.setBounds(356, 418, 170, 25);
		btnEditScholarship.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Edit pressed");
			}
		});
		add(btnEditScholarship);
		
		JButton btnRemoveScholarship = new JButton("Remove Scholarship");
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
		JList lstScholarships = new JList();		
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
	}
}
