/*
 * code burrowed from tutorial 
 * added a create account functionality 
 * which opens up a new window if user
 * does not have an account and 
 * wishes to create one
*/
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileWriter;
import java.io.BufferedWriter;

import java.awt.Color;

public class Create extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField fname;
	private JTextField lname;
	private JTextField email;
	private JTextField id;
	private JTextField fpwd;
	private JTextField spwd;
	
	

	/**
	 * Create the panel.
	 * @param auth 
	 * @param frame 
	 */
	public Create(JFrame frame, Authenticator auth) {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("First Name:");
		lblNewLabel.setBounds(6, 64, 87, 16);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Last name:");
		lblNewLabel_1.setBounds(6, 95, 87, 16);
		add(lblNewLabel_1);
		
		fname = new JTextField();
		fname.setBounds(105, 59, 188, 26);
		add(fname);
		fname.setColumns(10);
		
		lname = new JTextField();
		lname.setBounds(105, 90, 188, 26);
		add(lname);
		lname.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Email:");
		lblNewLabel_2.setBounds(6, 123, 61, 16);
		add(lblNewLabel_2);
		
		email = new JTextField();
		email.setBounds(105, 118, 188, 26);
		add(email);
		email.setColumns(10);
		
		JLabel studID = new JLabel("Student ID:");
		studID.setBounds(6, 148, 87, 16);
		add(studID);
		
		id = new JTextField();
		id.setBounds(105, 143, 188, 26);
		add(id);
		id.setColumns(10);
		
		JLabel pwd = new JLabel("Password:");
		pwd.setBounds(6, 176, 75, 16);
		add(pwd);
		
		fpwd = new JPasswordField();
		fpwd.setBounds(105, 171, 188, 26);
		add(fpwd);
		fpwd.setColumns(10);
		
		JLabel pwd_2 = new JLabel("Confirm Password: ");
		pwd_2.setBounds(6, 204, 147, 24);
		add(pwd_2);
		
		spwd = new JPasswordField();
		spwd.setBounds(137, 203, 156, 26);
		add(spwd);
		spwd.setColumns(10);
		
		JLabel verif = new JLabel("passwords dont match");
		verif.setBounds(148, 253, 163, 16);
		add(verif);
		
		
		
		JLabel inUse = new JLabel("ERROR: EMAIL/ID IN USE");
		inUse.setForeground(Color.RED);
		inUse.setBounds(105, 31, 203, 16);
		add(inUse);
		verif.setVisible(false);
		inUse.setVisible(false);
		
		JButton btnNewButton = new JButton("Create Account");
		btnNewButton.addMouseListener(new MouseAdapter() {
	

			@Override
			public void mouseClicked(MouseEvent e) {
				
				/*try {
				      File myObj = new File("users.txt");
				      Scanner myReader = new Scanner(myObj);
				      while (myReader.hasNextLine()) {
				    	  data = myReader.nextLine();
				    	  if (data.equals(email.getText()) || data.equals(id.getText())) {
				    		  inUse.setVisible(true);
				    	  }
				    	  else {
				    		  inUse.setVisible(false);
				    	  }
				    	  
				      }
				        
				      myReader.close();
				    } catch (FileNotFoundException e1) {
				      System.out.println("An error occurred.");
				      e1.printStackTrace();
				    }
				*/
				String f = fpwd.getText();
				String s = spwd.getText();
				if (!f.equals(s)) {
					verif.setVisible(true);
				}
				else if (f.equals(s)){
					verif.setVisible(false);
					/*
					 * Write inputs from the create account fields to two separate files
					 * first file users.txt stores the name, email and id of the person
					 * second file privateInfo.txt stores email and password (what users login with)
					 * if any of the fields is empty nothing is written 
					 * if confirm password doesn't match password field error message displayed 
					 */
					try {
						if (fname.getText().equals("") || lname.getText().equals("") || email.getText().equals("") || id.getText().equals("") || spwd.getText().equals("")) {
							//I think something should be here but what?
						}
						else { 
							BufferedWriter bw = new BufferedWriter(new FileWriter("users.txt", true));
							bw.write(fname.getText() + " " + lname.getText());
							bw.newLine();
							bw.write(email.getText());
							bw.newLine();
							bw.write(id.getText());
							bw.newLine();
							bw.close();
							BufferedWriter bw1 = new BufferedWriter(new FileWriter("privateInfo.txt", true));
							bw1.write(email.getText());
							bw1.newLine();
							bw1.write(f);
							bw1.newLine();
							bw1.write("");
							bw1.newLine();
							bw1.close();
						}
					} catch(Exception ex) {
						ex.printStackTrace();
					}
					frame.setBounds(100, 100, 450, 300);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					Login panel = new Login(frame, auth);
					frame.setContentPane(panel);
					frame.revalidate();
				}
				
				
				    
			}
		});
		
		btnNewButton.setBounds(6, 248, 130, 29);
		add(btnNewButton);
		
		JLabel profID = new JLabel("Prof ID:");
		profID.setBounds(6, 148, 75, 16);
		add(profID);
		profID.setVisible(false);
		
		JButton prof = new JButton("PROFESSOR");
		prof.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				profID.setVisible(true);
				studID.setVisible(false);
			}
			
		});
		prof.setBounds(340, 301, 117, 29);
		add(prof);
		
		JButton stud = new JButton("STUDENT");
		stud.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				profID.setVisible(false);
				studID.setVisible(true);
			}
		});
		stud.setBounds(165, 301, 117, 29);
		add(stud);
	
	}
}
