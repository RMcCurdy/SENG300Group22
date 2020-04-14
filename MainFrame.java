import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.Dimension;
import java.awt.Toolkit;
import objects.Authenticator;
import login.Login;

public class MainFrame {

	private JFrame frame;
	
	
	//Main class that loads the entire program
	//Starts by displaying the login GUI and users can
	//navigate from there
	//loads the student and department serial 
	//files, which store login info from authenticator
	//class.
	public static void main(String[] args) {
		Authenticator.loadStud();
		Authenticator.loadDep();
		Authenticator.loadRoles();
		Authenticator.loadAdmin();
		Authenticator.loadNames();
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
	
	//function called by main that loads up the login window
	public MainFrame() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		frame = new JFrame();
		frame.setBounds((screenWidth/2 - screenWidth/4), (screenHeight/2 - screenHeight/4), screenWidth/2, screenHeight/2);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Login panel = new Login(frame);
		frame.setContentPane(panel);
		frame.revalidate();
	}

}
