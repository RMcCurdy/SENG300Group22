import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class MainFrame {

	private JFrame frame;
	
	public static void main(String[] args) {
		Authenticator authen = new Authenticator();
		authen.loadStud();
		authen.loadDep();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame();
					window.frame.setVisible(true);
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public MainFrame() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		frame = new JFrame();
		frame.setBounds((screenWidth/2 - screenWidth/4), (screenHeight/2 - screenHeight/4), screenWidth/2, screenHeight/2);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		/*
		Scholarship a = new Scholarship("name1", "donor1", 1, "term1", "type1", "department1", "deadline1");
		Scholarship b = new Scholarship("name2", "donor2", 2, "term2", "type2", "department2", "deadline2");
		Scholarship c = new Scholarship("name3", "donor3", 3, "term3", "type3", "department3", "deadline3");

		ScholarshipsList list = new ScholarshipsList();
		list.addScholarship(a);
		list.addScholarship(b);
		list.addScholarship(c);
		
		ScholarshipsList list2 = new ScholarshipsList();
		list2.loadFromFile();
		Scholarship d = new Scholarship("name4", "donor4", 4, "term4", "type4", "department4", "deadline4");
		list2.addScholarship(d);
		*/
		//Login panel = new Login(frame);
		Account x = new Account("email", "firstName", "lastName", 12345678, 2, "Science");
		DepartmentHeadMenu panel = new DepartmentHeadMenu(frame, x);
		
		frame.setContentPane(panel);
		frame.revalidate();
	}

}
