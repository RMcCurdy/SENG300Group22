import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Toolkit;

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
		Login panel = new Login(frame);
		frame.setContentPane(panel);
		frame.revalidate();
	}

}
