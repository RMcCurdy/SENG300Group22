import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.awt.Dimension;
import java.awt.Toolkit;


public class AddScholarship extends JPanel {
	private JTextField txtName;
	private JTextField txtDonor;
	private JTextField txtValue;
	private JTextField txtDepartment;
	private JTextField txtDeadline;

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public AddScholarship(JFrame frame, Authenticator authen) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		
		setLayout(null);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(99, 53, 75, 23);
		add(lblName);
		
		txtName = new JTextField();
		txtName.setBounds(186, 53, 181, 22);
		add(txtName);
		txtName.setColumns(10);
		
		JLabel lblDonor = new JLabel("Donor:");
		lblDonor.setBounds(99, 89, 75, 30);
		add(lblDonor);
		
		txtDonor = new JTextField();
		txtDonor.setBounds(186, 93, 181, 22);
		add(txtDonor);
		txtDonor.setColumns(10);
		
		JLabel lblValue = new JLabel("Value:");
		lblValue.setBounds(99, 132, 75, 23);
		add(lblValue);
		
		txtValue = new JTextField();
		txtValue.setBounds(186, 132, 181, 22);
		add(txtValue);
		txtValue.setColumns(10);
		
		JComboBox term = new JComboBox();
		term.setModel(new DefaultComboBoxModel(new String[] {"Fall Term", "Winter Term", "Spring Term", "Summer Term", "Full-Year"}));
		term.setToolTipText("");
		term.setBounds(186, 167, 181, 22);
		add(term);
		
		JLabel lblTerm = new JLabel("Term:");
		lblTerm.setBounds(99, 167, 75, 23);
		add(lblTerm);
		
		JComboBox type = new JComboBox();
		type.setModel(new DefaultComboBoxModel(new String[] {"Undergraduate", "Graduate"}));
		type.setBounds(186, 202, 181, 22);
		add(type);
		
		JLabel lblType = new JLabel("Type:");
		lblType.setBounds(99, 199, 75, 25);
		add(lblType);
		
		JLabel lblDepartment = new JLabel("Department:");
		lblDepartment.setBounds(99, 237, 75, 23);
		add(lblDepartment);
		
		txtDepartment = new JTextField();
		txtDepartment.setBounds(186, 237, 181, 22);
		add(txtDepartment);
		txtDepartment.setColumns(10);
		
		JLabel lblDeadline = new JLabel("Deadline:");
		lblDeadline.setBounds(99, 273, 75, 16);
		add(lblDeadline);
		
		txtDeadline = new JTextField();
		txtDeadline.setBounds(186, 270, 83, 22);
		add(txtDeadline);
		txtDeadline.setColumns(10);
		
		JLabel lblFormat = new JLabel("(YYYYMMDD)");
		lblFormat.setBounds(186, 300, 95, 16);
		add(lblFormat);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				try {
					Scholarship toAdd = new Scholarship(txtName.getText(), txtDonor.getText(), Integer.parseInt(txtValue.getText()), (String)term.getSelectedItem(), (String)type.getSelectedItem(), txtDepartment.getText(), txtDeadline.getText());
					
					// "scholarships.txt" stores scholarships
					BufferedWriter bw = new BufferedWriter(new FileWriter("scholarships.txt", true));
					bw.write(toAdd.toString());
					bw.newLine();
					bw.close();
					} 
				catch(Exception ex) 
					{
					//Exception thrown if the above code can't proceed
						ex.printStackTrace();
					}
				//Set the frame size on the closing of the create account GUI
				frame.setBounds((screenWidth/2 - screenWidth/4), (screenHeight/2 - screenHeight/4), screenWidth/2, screenHeight/2);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				DepartmentHeadMenu dMenu = new DepartmentHeadMenu(frame, authen);
				frame.setContentPane(dMenu);
				frame.revalidate();
					
			}
		});
		btnCreate.setBounds(149, 348, 97, 25);
		add(btnCreate);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setBounds((screenWidth/2 - screenWidth/4), (screenHeight/2 - screenHeight/4), screenWidth/2, screenHeight/2);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				DepartmentHeadMenu dMenu = new DepartmentHeadMenu(frame, authen);
				frame.setContentPane(dMenu);
				frame.revalidate();
			}
		});
		btnCancel.setBounds(258, 348, 97, 25);
		add(btnCancel);

	}
}
