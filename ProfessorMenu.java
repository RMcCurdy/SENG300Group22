import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.Set;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class ProfessorMenu extends JPanel {
	private JList list;
	//available scholarships and term
	private static String[] scholarships = {"Science, Fall", "Science, Winter", "Science, Full Year", "Arts, Fall", "Arts, Winter", "Arts, Full Year", "Medicine, Fall", "Medicine, Winter", "Medicine, Full Year", "Architecture, Fall", "Architecture, Winter", "Architecture, Full Year", "Business, Fall", "Business, Winter", "Business, Full Year", "Kinesiology, Fall", "Kinesiology, Winter", "Kinesiology, Full Year", "Law, Fall", "Law, Winter", "Law, Full Year", "Nursing, Fall", "Nursing, Winter", "Nursing, Full Year", "Engineering, Fall", "Engineering, Winter", "Engineering, Full Year", "Social Work, Fall", "Social Work, Winter", "Social Work, Full Year", "Education, Fall", "Education, Winter", "Education, Full Year"};
	private static final long serialVersionUID = 1L;
	private JTextField search;
	private String[] scholarshiplist;

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

		String[] scholarshiplist = null;
		
		JSONParser parser = new JSONParser();
		// Try-catch statement to open the JSON file and add the school years to the drop down list
        try (Reader reader1 = new FileReader("currentScholarships.json")) {

			// Create a JSONObject out of the parsed JSON file
            JSONObject jsonObject = (JSONObject) parser.parse(reader1);            
            scholarshiplist = new String[jsonObject.size()];

            int i = 0;
            Set a = jsonObject.keySet();
            for (Object key : a) {
            	scholarshiplist[i] = (String) key;
            	i++;
            }

			reader1.close();

		// Exceptions to be thrown if necessary
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
		
		/**
		 * LIST
		 */
		//creating list containing scholarships
		list = new JList(scholarshiplist);
		list.setFont(labelFontSize);
		list.setSize(218, 80);
		list.setLocation(145, 159);
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
		JScrollPane sp = new JScrollPane();
		sp.setLocation(screenWidth/4 - screenWidth/10, screenHeight/6);
		sp.setSize(screenWidth/5, screenHeight/4);
		sp.setViewportView(list);
		add(sp);

		//search bar for list of scholarships
		
		search = new JTextField();
		search.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				System.out.println("getText: "+search.getText());
				
				String[] newList = null;
				
				JSONParser parser = new JSONParser();
				// Try-catch statement to open the JSON file and add the school years to the drop down list
		        try (Reader reader1 = new FileReader("currentScholarships.json")) {

					// Create a JSONObject out of the parsed JSON file
		            JSONObject jsonObject = (JSONObject) parser.parse(reader1);            
		            newList = new String[jsonObject.size()];

		            int i = 0;
		            Set a = jsonObject.keySet();
		            for (Object key : a) {
		            	
	            	String input = (String) key;
		            	if (input.toLowerCase().startsWith(search.getText().toLowerCase())) {
		            		newList[i] = input;
		            		i++;
		            	}
		            }
		            reader1.close();

				// Exceptions to be thrown if necessary
		        } catch (IOException ex) {
		            ex.printStackTrace();
		        } catch (ParseException ex) {
		            ex.printStackTrace();
		        }
				
				JList newJList = new JList(newList);
				newJList.setFont(labelFontSize);
				newJList.setSize(218, 80);
				newJList.setLocation(0, 0);
				newJList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
				newJList.setVisibleRowCount(3);
				newJList.setBackground(Color.WHITE);
				sp.setViewportView(newJList);

				list = newJList;
			}
		});
		search.setBounds(screenWidth/4 - screenWidth/14, screenHeight/9, screenWidth/7, screenHeight/35);
		add(search);
		
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
						selectedLabel.setText(selec);
						selectedError.setVisible(false);
						
						frame.setBounds((screenWidth/2 - screenWidth/4), (screenHeight/2 - screenHeight/4), screenWidth/2, screenHeight/2);
						frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						AddReference panel = new AddReference(frame, user, (String)list.getSelectedValue());
						frame.setContentPane(panel);
						frame.revalidate();
						
					} else {
						selectedError.setVisible(true);
					}
            	} catch (Exception e1) {

				}
			
			}
	    });		
		
		//Header of the system name
		JLabel header = new JLabel("UofC Professor Scholarship Portal");
		header.setHorizontalAlignment(SwingConstants.CENTER);
		header.setForeground(Color.RED);
		header.setBounds(160, 43, 595, 43);
		header.setFont(new Font("Arial", Font.PLAIN, screenHeight/30));
		add(header);
		
		JLabel lblNewLabel = new JLabel("Search:");
		lblNewLabel.setBounds(screenWidth/4 - screenWidth/8 + screenWidth/100, screenHeight/9, screenWidth/7, screenHeight/35);
		lblNewLabel.setFont(labelFontSize);
		add(lblNewLabel);
		
		JButton logoutButton = new JButton("Logout");
		logoutButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
		});
		logoutButton.setBounds(160, 464, 117, 29);
		add(logoutButton);
	}
	
	public void updateScholarshipList() {
		System.out.println("TEST");
	}
}