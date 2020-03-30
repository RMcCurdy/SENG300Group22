import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Font;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;
import java.nio.channels.SelectableChannel;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.Color;
import javax.swing.DefaultComboBoxModel;

public class Statistics extends JPanel {

	private static final long serialVersionUID = 1L;

	private List <String> faculties;
	private JComboBox facultyBox;
	private String selectedFaculty;

	/**
	 * Create the panel.
	 * @param user
	 * @param frame 
	 */
	public Statistics(JFrame frame, Account user) {
		//Save the user's screen resolution to variables, used to format GUI correctly
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;

		setLayout(null);
		
		//Font size for remaining labels
		Font labelFontSize = new Font("Arial", Font.PLAIN, screenHeight/60);

		//Create an empty array list that calls faculties name from a file
		faculties = new ArrayList <String>();

		// Label to indicate to select a faculty
		JLabel selectFaculty = new JLabel("Select a faculty from below");
		selectFaculty.setForeground(Color.BLACK);
		selectFaculty.setBounds(screenWidth/4 - screenWidth/14 + screenWidth/55, screenHeight/7 + 5*screenHeight/30, screenWidth/7, screenHeight/35);
		selectFaculty.setFont(labelFontSize);
		add(selectFaculty);

		// Label for error message
		JLabel invalidFaculty = new JLabel("Please select a faculty");
		invalidFaculty.setForeground(Color.RED);
		invalidFaculty.setBounds(screenWidth/4 - screenWidth/14 + screenWidth/60, screenHeight/7 + 5*screenHeight/30, screenWidth/7, screenHeight/35);
		invalidFaculty.setFont(labelFontSize);
		add(invalidFaculty);

		invalidFaculty.setVisible(false);

		//Add faculties to the drop down menu. Can be optimized to pull from a txt file.
		faculties.add("");
		faculties.add("Arts");
		faculties.add("Medicine");
		faculties.add("Architecture");
		faculties.add("Business");
		faculties.add("Kinesiology");
		faculties.add("Law");
		faculties.add("Nursing");
		faculties.add("Engineering");
		faculties.add("Social Work");
		faculties.add("Education");
		faculties.add("Science");
		//Set up the drop down menu and its properties
		DefaultComboBoxModel modelTemp = new DefaultComboBoxModel(faculties.toArray());
        facultyBox = new JComboBox(modelTemp);
		facultyBox.setBounds(screenWidth/4 - screenWidth/14, screenHeight/7 + 6*screenHeight/30, screenWidth/7, screenHeight/35);
		facultyBox.setFont(labelFontSize);
		add(facultyBox);

		//On mouse click of the drop down menu, update what was selected
		facultyBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
				try {
					// if else statement to determine whether a faculty has been chosen
					if (facultyBox.getSelectedIndex() != 0) {
						invalidFaculty.setVisible(false);
						selectFaculty.setVisible(true);
						//Save the selected faculty to string
						selectedFaculty = (String)facultyBox.getSelectedItem();
						// On the case the selection is social work, change string name to open image properly
						if (selectedFaculty == "Social Work"){
							selectedFaculty = "SocialWork";
							// loadImage displays the chosen faculty's statistics as a pie chart image
							loadImage(selectedFaculty);
						} else {
							loadImage(selectedFaculty);
						}
					} else {
						// error message displayed
						invalidFaculty.setVisible(true);
						selectFaculty.setVisible(false);
					}
            	} catch (Exception e) {

				}
			}
		});

		//Add a button to go back
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setFont(labelFontSize);
		btnCancel.setBounds(screenWidth/4 - screenWidth/14 + screenWidth/20, screenHeight/7 + 7*screenHeight/30, screenWidth/20, screenHeight/35);
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.setBounds((screenWidth/2 - screenWidth/4), (screenHeight/2 - screenHeight/4), screenWidth/2, screenHeight/2);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				DepartmentHeadMenu dhm = new DepartmentHeadMenu(frame, user);
				frame.setContentPane(dhm);
				frame.revalidate();
			}
		});
		add(btnCancel);
	}

	//Save the user's screen resolution to variables, used to format GUI correctly
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	int screenHeight = screenSize.height;
	int screenWidth = screenSize.width;
	
	public void loadImage(String s) {
		try {
			BufferedImage image = ImageIO.read(getClass().getResource("pieChartImages/" + s + ".png"));
			JLabel label = new JLabel(new ImageIcon(image));
			JFrame f = new JFrame();
			f.getContentPane().add(new JScrollPane(label));
			f.setSize(screenWidth/2,screenHeight/2 + screenHeight/3);
			f.setLocation(screenWidth/4 - screenWidth/8, screenHeight/4 - screenWidth/8);
			f.setVisible(true);
		} catch (Exception e){

		}
		
	}

}
