import org.json.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
 
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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

import java.io.FileReader; 
import java.util.Iterator; 
import java.util.Map; 

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
	private List <String> schoolYears;
	private List <String> semesters;
	private JComboBox facultyBox;
	private JComboBox schoolYearBox;
	private JComboBox semesterBox;
	private String selectedFaculty;
	private String selectedYear;
	private String selectedSemester;

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

		//Create an empty array list that calls faculties name from a file
		schoolYears = new ArrayList <String>();

		//Create an empty array list that calls faculties name from a file
		semesters = new ArrayList <String>();

		//Header of the system name
		JLabel header = new JLabel("Statistics");
		header.setBounds(screenWidth/4 - screenWidth/6, screenHeight/25, screenWidth/3, screenHeight/25);
		header.setForeground(Color.RED);
		header.setFont(new Font("Arial", Font.PLAIN, screenHeight/30));
		add(header);

		// Label to indicate to select a faculty
		JLabel selectFaculty = new JLabel("Select a faculty from below");
		selectFaculty.setForeground(Color.BLACK);
		selectFaculty.setBounds(screenWidth/4 - screenWidth/14 + screenWidth/55, screenHeight/7 + 5*screenHeight/30, screenWidth/7, screenHeight/35);
		selectFaculty.setFont(labelFontSize);
		add(selectFaculty);

		// Label to indicate to select a faculty
		JLabel selectYear = new JLabel("Select a year from below");
		selectYear.setForeground(Color.BLACK);
		selectYear.setBounds(screenWidth/4 - screenWidth/14 + screenWidth/55 + screenWidth/20, screenHeight/7 + 5*screenHeight/30, screenWidth/7, screenHeight/35);
		selectYear.setFont(labelFontSize);
		add(selectYear);

		// Label to indicate to select a faculty
		JLabel selectSemester = new JLabel("Select a semester from below");
		selectSemester.setForeground(Color.BLACK);
		selectSemester.setBounds(screenWidth/4 - screenWidth/14 + screenWidth/55 + 2*screenWidth/20, screenHeight/7 + 5*screenHeight/30, screenWidth/7, screenHeight/35);
		selectSemester.setFont(labelFontSize);
		add(selectSemester);

		// Label for error message
		JLabel invalidFaculty = new JLabel("Please select a faculty");
		invalidFaculty.setForeground(Color.RED);
		invalidFaculty.setBounds(screenWidth/4 - screenWidth/14 + screenWidth/60, screenHeight/7 + 5*screenHeight/30, screenWidth/7, screenHeight/35);
		invalidFaculty.setFont(labelFontSize);
		add(invalidFaculty);

		// Label for error message
		JLabel invalidYear = new JLabel("Please select a year");
		invalidYear.setForeground(Color.RED);
		invalidYear.setBounds(screenWidth/4 - screenWidth/14 + screenWidth/60 + screenWidth/20, screenHeight/7 + 5*screenHeight/30, screenWidth/7, screenHeight/35);
		invalidYear.setFont(labelFontSize);
		add(invalidYear);

		// Label for error message
		JLabel invalidSemester = new JLabel("Please select a semester");
		invalidSemester.setForeground(Color.RED);
		invalidSemester.setBounds(screenWidth/4 - screenWidth/14 + screenWidth/60 + 2*screenWidth/20, screenHeight/7 + 5*screenHeight/30, screenWidth/7, screenHeight/35);
		invalidSemester.setFont(labelFontSize);
		add(invalidSemester);

		invalidFaculty.setVisible(false);
		invalidSemester.setVisible(false);
		invalidYear.setVisible(false);

		//Add faculties to the drop down menu. Can be optimized to pull from a txt file.
		JSONParser parser = new JSONParser();

        try (Reader reader = new FileReader("data.json")) {

            JSONObject jsonObject = (JSONObject) parser.parse(reader);

            JSONArray facultyArrayJSON = (JSONArray) jsonObject.get("faculties");
            //System.out.println(msg.size());
            Iterator<String> iterator = facultyArrayJSON.iterator();
            while (iterator.hasNext()) {
                faculties.add(iterator.next());
			}
			
			reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
		//Set up the drop down menu and its properties
		DefaultComboBoxModel modelTemp = new DefaultComboBoxModel(faculties.toArray());
        facultyBox = new JComboBox(modelTemp);
		facultyBox.setBounds(screenWidth/4 - screenWidth/14, screenHeight/7 + 6*screenHeight/30, screenWidth/7, screenHeight/35);
		facultyBox.setFont(labelFontSize);
		add(facultyBox);

		//Will edit this action into the confirm button
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
					} else {
						// error message displayed
						invalidFaculty.setVisible(true);
						selectFaculty.setVisible(false);
					}
            	} catch (Exception e) {

				}
			}
		});

		//Add years to the drop down menu. Can be optimized to pull from a txt file.
		JSONParser parser1 = new JSONParser();

        try (Reader reader1 = new FileReader("data.json")) {

            JSONObject jsonObject1 = (JSONObject) parser1.parse(reader1);

            JSONArray yearArrayJSON1 = (JSONArray) jsonObject1.get("schoolYears");

            Iterator<String> iterator1 = yearArrayJSON1.iterator();
            while (iterator1.hasNext()) {
                schoolYears.add(iterator1.next());
			}
			
			reader1.close();

        } catch (IOException e) {
            System.out.println("IOException");
        } catch (ParseException e) {
            System.out.println("ParseException");
        }
		//Set up the drop down menu and its properties
		DefaultComboBoxModel schoolYearBoxModel = new DefaultComboBoxModel(schoolYears.toArray());
        schoolYearBox = new JComboBox(schoolYearBoxModel);
		schoolYearBox.setBounds(screenWidth/4 - screenWidth/14 + screenWidth/20, screenHeight/7 + 6*screenHeight/30, screenWidth/7, screenHeight/35);
		schoolYearBox.setFont(labelFontSize);
		add(schoolYearBox);

		//Will edit this action into the confirm button
		//On mouse click of the drop down menu, update what was selected
		schoolYearBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
				try {
					// if else statement to determine whether a faculty has been chosen
					if (schoolYearBox.getSelectedIndex() != 0) {
						invalidYear.setVisible(false);
						selectYear.setVisible(true);
						//Save the selected faculty to string
						selectedYear = (String)schoolYearBox.getSelectedItem();
					} else {
						// error message displayed
						invalidYear.setVisible(true);
						selectYear.setVisible(false);
					}
            	} catch (Exception e) {

				}
			}
		});

		//Add years to the drop down menu. Can be optimized to pull from a txt file.
		JSONParser parser2 = new JSONParser();

        try (Reader reader2 = new FileReader("data.json")) {

            JSONObject jsonObject2 = (JSONObject) parser2.parse(reader2);

            JSONArray yearArrayJSON2 = (JSONArray) jsonObject2.get("semesters");

            Iterator<String> iterator2 = yearArrayJSON2.iterator();
            while (iterator2.hasNext()) {
                semesters.add(iterator2.next());
			}
			
			reader2.close();

        } catch (IOException e) {
            System.out.println("IOException");
        } catch (ParseException e) {
            System.out.println("ParseException");
        }
		//Set up the drop down menu and its properties
		DefaultComboBoxModel semesterBoxModel = new DefaultComboBoxModel(semesters.toArray());
        semesterBox = new JComboBox(schoolYearBoxModel);
		semesterBox.setBounds(screenWidth/4 - screenWidth/14 + 3*screenWidth/20, screenHeight/7 + 6*screenHeight/30, screenWidth/7, screenHeight/35);
		semesterBox.setFont(labelFontSize);
		add(semesterBox);

		//Will edit this action into the confirm button
		//On mouse click of the drop down menu, update what was selected
		semesterBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
				try {
					// if else statement to determine whether a faculty has been chosen
					if (semesterBox.getSelectedIndex() != 0) {
						invalidSemester.setVisible(false);
						selectSemester.setVisible(true);
						//Save the selected faculty to string
						selectedSemester = (String)semesterBox.getSelectedItem();
					} else {
						// error message displayed
						invalidSemester.setVisible(true);
						selectSemester.setVisible(false);
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

		//Add a button to go back
		JButton btnConfirm = new JButton("Display");
		btnConfirm.setFont(labelFontSize);
		btnConfirm.setBounds(screenWidth/4 - screenWidth/14 + screenWidth/10, screenHeight/7 + 7*screenHeight/30, screenWidth/20, screenHeight/35);
		btnConfirm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//Actions and check for erros in order to display information correctly
			}
		});
		add(btnConfirm);

		//If statements to determine which picture to display stats for
        
	}
}
